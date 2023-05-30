import java.io.*;
import java.util.Scanner;

public class PrimeNumber {


    private static boolean isPrime(int number) {
        int root = (int) Math.sqrt(number);
        for (int i = 2; i <= root; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        Scanner scanner = new Scanner(System.in);
        int n;
        n = scanner.nextInt();
        if (n < 2) {
            System.out.println(0);
        } else {
            int count = 0;
            for (int i = 2; i <= n; i++) {
                if (isPrime(i)) {
                    count++;
                }
            }
            System.out.println(count);
        }

    }
}