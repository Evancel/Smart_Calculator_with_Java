import java.util.Scanner;

class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
//        String openTag = "(<.+>)+";
//        String content = ".+";
//        String closeTag = "(</.+>)+";
//        String regex = openTag + content + closeTag;
////        input = input.replaceAll("[<>]","");
////        String tag = input.split("\\s+")[0];
//        System.out.println(input.matches(regex));
////        System.out.println(tag);
//        sc.close();
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = sc.nextLine().toCharArray();
        StringBuilder sb = null;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == '<'){
                int j = ++i;
                for( ;arr[j] != ' ' && arr[j] != '>'; j++ ){
                    System.out.println(arr[j]);
                    sb = new StringBuilder();
                    sb.append(arr[j]);
                }
                System.out.println(sb);
                i = ++j;
            }
        }
        sc.close();
    }
}