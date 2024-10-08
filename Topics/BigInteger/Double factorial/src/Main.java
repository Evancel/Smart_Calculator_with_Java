import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        if(n < 0){
            return BigInteger.ZERO;
        }

        if(n == 0){
            return BigInteger.ONE;
        }

        if (n == 1 || n == 2) {
            return BigInteger.valueOf(n);
        }
        BigInteger temp = BigInteger.valueOf(n).multiply(calcDoubleFactorial(n - 2));
//        System.out.println("temp = " + temp);

        return temp;
    }

//    public static void main(String[] args) {
//        System.out.println(calcDoubleFactorial(100));
//    }
}