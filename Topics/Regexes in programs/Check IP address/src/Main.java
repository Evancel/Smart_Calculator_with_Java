import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String dig = "([0-1]\\d{0,2}|2\\d{0,1}|2[0-5][0-5]|[3-9]\\d{0,1})";
        String split = "\\.";
        String regexp = String.format("%s%s%s%s%s%s%s",
                dig, split, dig, split, dig, split, dig);
        boolean isValid = input.matches(regexp);
        System.out.println(isValid ? "YES" : "NO");
        scanner.close();
    }
}