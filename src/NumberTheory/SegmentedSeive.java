package NumberTheory;

import java.util.ArrayList;
import java.util.Scanner;

public class SegmentedSeive {

    private final static int MAX = 100001;

    private static ArrayList<Long> sieve() {
        boolean[] prime = new boolean[MAX];
        for (int i = 0; i < MAX; i++) {
            prime[i] = true;
        }
        prime[0] = false;
        prime[1] = false;

        ArrayList<Long> temp = new ArrayList<>();
        for (int i = 2; i * i < MAX; i++) {
            if (prime[i]) {
                for (long j = (long) i * (long) i; j < MAX; j += i) {
                    prime[(int) j] = false;
                }
            }
        }
        for (int i = 0; i < MAX; i++) {
            if (prime[i]) {
                temp.add((long) i);
            }
        }

        return temp;

    }

    private static void printPrime(long start, long end, ArrayList<Long> primeNumbers) {

        boolean[] primes = new boolean[(int) (end - start) + 1];

        for (int i = 0; i <= (end - start); i++) {
            primes[i] = true;
        }
        for (Long item : primeNumbers) {
            long base = 0;
            if ((start / item) != 0) {
                base += ((start / item) * item);
            }
            if (base < start) {
                base += item;
            }
            for (long j = base; j <= end; j += item) {
                primes[(int) (j - start)] = false;

            }
            if (base == item && base >= start && base <= end) {
                primes[(int) (base - start)] = true;
            }

        }

        for (int i = 0; i <= (end - start); i++) {
            if (primes[i] && (i + start) != 1) {
                System.out.print((i + start) + " ");
            }
        }

    }

    private static boolean isPrime(int number) {
        if (number < 2)
            return false;
        for (int i = 2; i * i <= number; i++) {
            if ((number % i) == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        ArrayList<Long> primeNumbers = sieve();
        while (t-- > 0) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            printPrime(start, end, primeNumbers);
            System.out.print("\n");
        }
    }
}
