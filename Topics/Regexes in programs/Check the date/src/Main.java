import java.util.*;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();
        String dateRegex1 = "\\b(19|20)\\d{2}[-/.](0[1-9]|1[0-2])[-/.]([0-2][1-9]|3[0-1])\\b";
        String dateRegex2 = "\\b([0-2][1-9]|3[0-1])[-/.](0[1-9]|1[0-2])[-/.](19|20)\\d{2}\\b";
        boolean isValid = date.matches(dateRegex1) || date.matches(dateRegex2);
        System.out.println(isValid ? "Yes" : "No");

        scn.close();
    }
}