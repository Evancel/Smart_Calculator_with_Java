import java.util.Scanner;

class RemoveExtraSpacesProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String result = text.replaceAll("[ \t]{2,}", " ");
        System.out.println(result);

        scanner.close();
    }
}