import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String myRegex = "([-+]?[1-9]\\d*([.,]0*[1-9]+)*)|([-+]?[1-9]\\d*([.,]0)*)|([-+]?0([.,]0*[1-9]+))|0|0.0";
        String betterRegex = "[-+]?(([1-9]\\d*)|0)([.,]((\\d*[1-9])|0))?";

        String number = scanner.nextLine();
        System.out.println(number.matches(myRegex) ? "YES" : "NO");
        scanner.close();
    }
}