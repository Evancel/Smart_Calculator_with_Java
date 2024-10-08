import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number
        String validPattern = "[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}";
        boolean isValid = regNum.matches(validPattern);
        System.out.println(isValid);

        scanner.close();
    }
}