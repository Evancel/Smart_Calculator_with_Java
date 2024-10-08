package calculator;

import java.math.BigInteger;
import java.util.*;

public class Calculator {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, BigInteger> variables = new HashMap<>();
    private final Map<String, Integer> operatorsPrecedence  = new HashMap<>(Map.of(
            "+",1,
            "-",1,
            "*",2,
            "/",2,
            "^",3));
    private final List<String> result  = new ArrayList<>();
    private final Deque<String> stack = new ArrayDeque<>();
    private State currentState;

    private final String HELP = "/help";
    private final String EXIT = "/exit";

    private final String COMMAND_REGEX = "/.*?";
    final String CALCULATION_REGEX =
            "(^|=)[-+]*[(]*([a-zA-Z]+|[0-9]+)(((([-+]+|[*/^])[(]*([a-zA-Z]+|[0-9]+)[)]*)*)|([)]*))$";
    final String ASSIGNMENT_REGEX = "[a-zA-Z]+" + CALCULATION_REGEX;

    private final String W_INVALID_IDENTIFIER = "Invalid identifier";
    private final String W_INVALID_EXPRESSION = "Invalid expression";
    private final String W_INVALID_ASSIGNMENT = "Invalid assignment";
    private final String W_UNKNOWN_COMMAND = "Unknown command";
    private final String W_UNKNOWN_VARIABLE = "Unknown variable";
    private final String E_INTERNAL = "Internal error. Unexpected behaviour.";

    enum State {ON, OFF}
    public Calculator() {
        currentState = State.ON;
    }

    public State getState() {
        return this.currentState;
    }

    public void start() {
        do {
            String inputLine = scanner.nextLine();
            inputLine = inputLine.replaceAll("\\s*", "");

            if (inputLine.isBlank()) {
                continue;
            } else if (inputLine.matches(COMMAND_REGEX)) {
                manageCommands(inputLine);
            } else if (inputLine.matches(ASSIGNMENT_REGEX)) {
                assignVariables(inputLine);
            } else if (inputLine.matches(CALCULATION_REGEX)) {
                printTheResult(inputLine);
            } else {
                System.out.println(W_INVALID_EXPRESSION);
            }
        } while (currentState != State.OFF);
        scanner.close();
    }

    private void manageCommands(String input) {
        if (input.equalsIgnoreCase(HELP)) {
            help();
        } else if (input.equalsIgnoreCase(EXIT)) {
            exit();
        } else {
            System.out.println(W_UNKNOWN_COMMAND);
        }
    }

    private void assignVariables(String input) {
        //remove the check block (^|=) in the CALCULATION_REGEX
        String calculationPattern = CALCULATION_REGEX.substring(5);
        String[] variable = input.split("=");
        String key = variable[0];
        String value = variable[1];
        BigInteger bigValue;
        if (value.matches("[+]*\\d+")) {
            bigValue = new BigInteger(value);
        } else if(value.matches("-\\d+")){
            bigValue = new BigInteger(value).negate();
        } else if (value.matches("[+]?[a-zA-Z]+")) {
            if (variables.containsKey(value)) {
                bigValue = variables.get(value);
            } else {
                System.out.println(W_UNKNOWN_VARIABLE);
                return;
            }
        } else if (value.matches("-[a-zA-Z]+")) {
            if (variables.containsKey(value.substring(1))) {
                bigValue = variables.get(value.substring(1)).negate();
            } else {
                System.out.println(W_UNKNOWN_VARIABLE);
                return;
            }
        } else if (value.matches(calculationPattern)){
            bigValue = calculateTheResult(value);
        }else {
            System.out.println(W_INVALID_ASSIGNMENT);
            return;
        }

        variables.put(key, bigValue);
    }

    private List<String> processInputToList(String input) {
        input = input.replaceAll("-{2}+", "+");
        input = input.replaceAll("\\+{2,}", "+");
        input = input.replaceAll("(\\+-)|(-\\+)", "-");
        //adding spaces to separate operators and operands
        input = input.replaceAll("\\+", " + ");
        input = input.replaceAll("-", " - ");
        input = input.replaceAll("\\*", " * ");
        input = input.replaceAll("/", " / ");
        input = input.replaceAll("\\^", " ^ ");
        input = input.replaceAll("\\(", "( ");
        input = input.replaceAll("\\)", " )");
        input = input.replaceFirst("^\\s+","");

        List<String> expression = new ArrayList<>(List.of(input.split("\\s+")));

        if(!areBracketsBalanced(expression)){
            System.out.println(W_INVALID_EXPRESSION);
            return new ArrayList<>();
        }

        return expression;
    }

    private boolean areBracketsBalanced(List<String> expression){
        Deque<String> deq = new ArrayDeque<>();
        for (String elem : expression) {
            if (elem.equals("(")) {
                deq.addLast(elem);
            } else if (!deq.isEmpty() && elem.equals(")")) {
                deq.removeLast();
            } else if (deq.isEmpty() && elem.equals(")")){
                //case 4+3)
                return false;
            }
        }

        return deq.isEmpty();
    }

    private void convertInfixToPostfix(List<String> expression){
        if(expression.isEmpty()){
            return;
        }

        //cases like: -10, -3 + 2 + 6
        if(expression.get(0).equals("-")){
            expression.add(0,"0");
        }

        for(String item : expression){
            if(operatorsPrecedence.containsKey(item)) {
                if (stack.isEmpty()){
                    stack.addLast(item);
                }else if(stack.getLast().equals("(")){
                    stack.addLast(item);
                }else if (operatorsPrecedence.get(item) > operatorsPrecedence.get(stack.getLast())) {
                    stack.addLast(item);
                }else if (operatorsPrecedence.get(item) <= operatorsPrecedence.get(stack.getLast())){
                    do {
                        result.add(stack.removeLast());
                    } while (!stack.isEmpty() && !(stack.getLast().equals("(")) &&
                            !(operatorsPrecedence.get(item) > operatorsPrecedence.get(stack.getLast())));
                    stack.addLast(item);
                }

            }else if(item.matches("[()]")){

                if(item.equals("(")){
                    stack.addLast(item);
                }else if(item.equals(")")){
                    do{
                        result.add(stack.removeLast());
                    }while(!(stack.getLast().equals("(")));
                    //Discard the pair of parentheses: item = ")" - ничего не делаем, и удаляем из стека элемент = "("
                    stack.removeLast();
                }

            }else if(item.matches("[a-zA-Z]+")) {
                if (variables.containsKey(item)) {
                    result.add(String.valueOf(variables.get(item)));
                } else {
                    System.out.println(W_UNKNOWN_VARIABLE);
                }
            }else if(item.matches("[0-9]+")){
                result.add(item);
            }else{
                System.out.println(E_INTERNAL);
            }
        }

        //At the end of the expression, pop the stack and add all operators to the result.
        while(!stack.isEmpty()){
            result.add(stack.removeLast());
        }
    }

    private void printTheResult(String input){
        BigInteger sum = calculateTheResult(input);
        System.out.println(sum);
    }

    private BigInteger calculateTheResult(String input) {
        result.clear();
        stack.clear();

        convertInfixToPostfix(processInputToList(input));

        if (result.isEmpty()) {
            System.out.println("The list of numbers is empty");
            return BigInteger.ZERO;
        }

        BigInteger sum;
        for (String item : result) {
            if(item.matches("-?[0-9]+")){
                stack.addLast(item);
            } else if(stack.size()>=2){
                BigInteger num2 = new BigInteger(stack.removeLast());
                BigInteger num1 = new BigInteger(stack.removeLast());
                stack.addLast(String.valueOf(calculate(num1,num2,item)));
            }
        }

        sum = new BigInteger(stack.getFirst());
        return sum;
    }

    private BigInteger calculate(BigInteger num1, BigInteger num2, String operator){
        BigInteger tempResult;

        if(num2.equals(BigInteger.ZERO)){
            System.out.println("Division by zero is impossible");
            return BigInteger.ZERO;
        }

        tempResult = switch (operator){
            case "+" -> num1.add(num2);
            case "-" -> num1.subtract(num2);
            case "*" -> num1.multiply(num2);
            case "/" -> num1.divide(num2);
            case "^" -> num1.pow(num2.intValue());
            default -> BigInteger.ZERO;
        };
        return  tempResult;
    }

    private void help() {
        System.out.println("The program calculates the sum of numbers");
    }

    private void exit() {
        System.out.println("Bye!");
        currentState = State.OFF;
    }
}
