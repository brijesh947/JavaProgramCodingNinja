import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/*
 *
 * A number is called n-factor-full if it has exactly n distinct prime factors.
 * Given positive integers a, b, and n, your task is to find the number of integers between a and b, inclusive, that are n-factor-full.
 * We consider 1 to be 0-factor-full.
 *
 *
 * 1 <= T <= 10000
 * 1 ≤ a ≤ b ≤ 10^6
 * 0 <= b - a <= 100
 * 0 ≤ n ≤ 10
 *
 */
public class SuperPrime {
    private final static int MAX = 1000001;

    private static int[] sieve() {
        boolean[] prime = new boolean[MAX];
        int[] primeList = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            prime[i] = true;
            primeList[i] = 0;
        }


        for (int i = 2; i < MAX; i++) {
            if (prime[i]) {
                primeList[i] = 1;
                for (int j = 2; j * i < MAX; j++) {
                    prime[i * j] = false;
                    primeList[i * j]++;
                }
            }
        }
        return primeList;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        //Buffer reader is generally faster than the scanner in java.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t;
        t = Integer.parseInt(reader.readLine().trim());
        int[] primeList = sieve();
        while (t-- > 0) {
            String[] numbers = reader.readLine().split(" ");
            int a, b, n;
            a = Integer.parseInt(numbers[0]);
            b = Integer.parseInt(numbers[1]);
            n = Integer.parseInt(numbers[2]);
            int count = 0;
            for (int i = a; i <= b; i++) {
                if (primeList[i] == n) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
