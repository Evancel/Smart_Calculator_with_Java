import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        Deque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < count; i++) {
            deq.addFirst(sc.nextInt());
        }

        for (int i = 0; i < count; i++) {
            System.out.println(deq.pop());
        }

        sc.close();
    }
}