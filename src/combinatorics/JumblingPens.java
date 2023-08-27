package combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 *
 * You are given a total of ‘N’ pens. Out of which ‘R’ pens are Red, ‘G’ pens are green, and ‘B’ pens are blue.
 * Pens of the same color are identical.
 * We need to tell the distinct number of orderings of these pens we can achieve by jumbling them in any possible way since the answer can be huge print it modulo (10^9+7).
 * For Example:
 * ‘N’ = 4, ‘R’ = 3, ‘G’ = 1, and ‘B’ = 0
 * All the possible distinct ordering are as follows:
 *
 * G R R R
 * R G R R
 * R R G R
 * R R R G

 * Note that swapping or jumbling pens of the same colors doesn’t create a new ordering.
 * As the total number of distinct arrangements is 4. Hence the output will be 4.
 * Input Format:
 * The first line of the input contains a single integer ‘T’ representing the no. of test cases.
 *
 * The first line of each test case contains four space-separated integers, ‘N’, ‘R’, ‘G’, and ‘B’, denoting the integers described in the problem statement.
 *Output Format:
 * For each test case, print a single integer value denoting the number of distinct orderings of the pens (modulo 10^9+7).
 * Print a separate line for each test case.
 * */

public class JumblingPens {

    private static Triplet MMI(long a, long b) {

        if (b == 0) {
            Triplet ans = new Triplet(1, 0, a);
            return ans;
        }

        Triplet temp = MMI(b, a % b);
        long x = temp.y;
        long y = (temp.x - (a / b) * temp.y);
        long gcd = temp.gcd;
        return new Triplet(x, y, gcd);

    }


    private static long getMMI(long a, long b) {
        return (MMI(a, b).x + b) % b;
    }

    private static class Triplet {
        long x;
        long y;
        long gcd;

        Triplet(long x, long y, long gcd) {
            this.x = x;
            this.y = y;
            this.gcd = gcd;
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 10001;
    private static final int MOD = 1000000007;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());

        long[] fact = new long[MAX];
        fact[0] = 1;
        for (int i = 1; i < MAX; i++) {
            fact[i] = (i * fact[i - 1]) % MOD;
        }

        while (t-- > 0) {

            String[] num = reader.readLine().split(" ");
            int n = Integer.parseInt(num[0]);
            int r = Integer.parseInt(num[1]);
            int g = Integer.parseInt(num[2]);
            int b = Integer.parseInt(num[3]);


            long numerator = 1;
            for (int i = 2; i <= n; i++) {
                numerator = (numerator * i) % MOD;
            }

            long denominator = 1;
            for (int i = 2; i <= r; i++) {
                denominator = (denominator * i) % MOD;
            }

            for (int i = 2; i <= g; i++) {
                denominator = (denominator * i) % MOD;
            }

            for (int i = 2; i <= b; i++) {
                denominator = (denominator * i) % MOD;
            }

            long divisor = getMMI(denominator, MOD);
            numerator = (numerator * divisor) % MOD;
            System.out.print(numerator + "\n");

        }

    }
}
