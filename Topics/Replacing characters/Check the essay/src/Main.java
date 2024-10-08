import java.util.Objects;
import java.util.Scanner;


public class SafeStringOperations {
    public static String processName(String name) {
        String upperName = Objects.requireNonNull(name, "Guest").toUpperCase();
        return "Hello, " + upperName + "!" + System.lineSeparator();
    }
}