import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        boolean isSafe = false;

        String regUpper = "^.*[A-Z].*$";
        String regLower = "^.*[a-z].*$";
        String regDigits = "^.*[0-9].*$";

        isSafe = password.length() >= 12 &&
                password.matches(regUpper) &&
                password.matches(regLower) &&
                password.matches(regDigits);

        System.out.println(isSafe ? "YES" : "NO");
        scanner.close();

    }
}