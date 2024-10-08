import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        if(input.length != 4){
            return;
        }

        BigInteger a = new BigInteger(input[0]);
        BigInteger b = new BigInteger(input[1]);
        BigInteger c = new BigInteger(input[2]);
        BigInteger d = new BigInteger(input[3]);
        BigInteger result = a.negate().multiply(b).add(c).subtract(d);

        scanner.close();
        System.out.println(result);
    }
}