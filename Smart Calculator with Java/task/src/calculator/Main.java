package calculator;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        do{
            calculator.start();
        }while (calculator.getState() != Calculator.State.OFF);
    }
}