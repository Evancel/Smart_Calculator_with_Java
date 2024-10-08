import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = sc.nextLine().toCharArray();
        System.out.println(areBracketsBalanced(arr));

        sc.close();
    }

    private static boolean areBracketsBalanced(char[] arr) {
        Map<Character, Character> brackets = new HashMap<>(Map.of(
                ')', '(',
                ']', '[',
                '}', '{'));
        Deque<Character> deq = new ArrayDeque<>();

        for (char elem : arr) {
            if (brackets.containsValue(elem)) {
                deq.addLast(elem);
            } else if (!deq.isEmpty() && brackets.get(elem).equals(deq.getLast())) {
                deq.removeLast();
            } else {
                return false;
            }
        }

        return deq.isEmpty();
    }

}