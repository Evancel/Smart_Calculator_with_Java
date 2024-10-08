import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        String regex = String.format("\\b[a-zA-Z0-9]{%d}\\b",size);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        System.out.println(matcher.find() ? "YES" : "NO");

        scanner.close();
    }
}