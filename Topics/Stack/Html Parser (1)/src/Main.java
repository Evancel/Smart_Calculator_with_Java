import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = sc.nextLine().toCharArray();
        List<String> tokens = createList(arr);
        processElement(tokens);
        sc.close();
    }

    private static List<String> createList(char[] arr) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb_context = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '<') {
                if (!sb_context.isEmpty()) {
                    tokens.add(sb_context.toString());
                    sb_context = new StringBuilder();
                }

                int j = i;
                StringBuilder sb_tag = new StringBuilder();
                do {
                    sb_tag.append(arr[j]);
                    j++;
                } while (arr[j] != '>');
                i = j;

                if (sb_tag.indexOf(" ") != -1) {
                    String tag = sb_tag.substring(0, sb_tag.indexOf(" ")) + '>';
                    tokens.add(tag);
                } else {
                    String tag = sb_tag.toString() + '>';
                    tokens.add(tag);
                }
            } else {
                sb_context.append(arr[i]);
            }
        }

//        System.out.println("created List = " + tokens);
        return new ArrayList<>(tokens);
    }

    private static void processElement(List<String> tokens) {
        Deque<String> tagStack = new ArrayDeque<>();
        Deque<Deque<String>> contentStack = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.matches("</.*>")) {
                Deque<String> stackLast = contentStack.getLast();
                System.out.println(contentStack.removeLast().toString().replaceAll("[, \\[\\]]", ""));
                if (!contentStack.isEmpty()) {
                    contentStack.getLast().addLast(tagStack.getLast() + stackLast + token);
                }
                if (!tagStack.isEmpty()) {
                    tagStack.removeLast();
                }
            } else if (token.matches("<.*>")) {
                contentStack.addLast(new ArrayDeque<>());
                tagStack.addLast(token);
            } else {
                contentStack.getLast().addFirst(token);
            }

//            System.out.println("tagStack = " + tagStack);
//            System.out.println("contentStack = " + contentStack);
        }
    }


}