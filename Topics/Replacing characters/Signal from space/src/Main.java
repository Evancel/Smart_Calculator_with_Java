import java.util.Scanner;

public class Main {
    public static String[] decipherCosmicSignal(String[] spaceSignalArray) {
        for (int i = 0; i < spaceSignalArray.length; i++) {
            spaceSignalArray[i] = spaceSignalArray[i].replaceAll("~", "");
        }
        return spaceSignalArray;
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] encryptedSpaceSignalArray = scanner.nextLine().split("\\s");
        String[] spaceSignalArray = decipherCosmicSignal(encryptedSpaceSignalArray);
        StringBuilder spaceMessage = new StringBuilder();
        for (String signal : spaceSignalArray) {
            spaceMessage.append(signal).append(" ");
        }
        System.out.println(spaceMessage.toString().trim());
        scanner.close();
    }
}