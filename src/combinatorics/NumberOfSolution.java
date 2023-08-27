package combinatorics;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * You are given the following equation:
 * X1 + X2 + ⋯ + Xk = N
 * Your task is to find the number of solutions of this equation, such that xi ≥ 0.
 * Input Format:
 * First line of input contain an integer T(number of test case).
 * Next T lines contains two space-separated integers N and K
 * Constraints:
 * 1 <= T <= 10^5
 * 1 <= N , k <= 10^5
 * Output Format:
 * For each test case, print the number of solutions for the equation.
 * Answer can be very huge, so print its modulo with 10^9 + 7
 *
 * */

public class NumberOfSolution {


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
    private static final int MOD = 1000000007;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        int MAX = 200001;
        long[] factorial = new long[MAX];
        factorial[0] = 1;
        for (int i = 1; i < MAX; i++) {
            factorial[i] = (i * factorial[i - 1]) % MOD;
        }
        while (t-- > 0) {
            String[] num = reader.readLine().split(" ");
            int n = Integer.parseInt(num[0]);
            int k = Integer.parseInt(num[1]);
            long N = (n + k - 1);
            long M = (k - 1);
            /*
             * this problem can  solve using star and bars technique
             * suppose here we have n chocolate and wanted to distribute it among k children
             * suppose chocolate on a horizontal plane, so we need k-1 bar to distribute n chocolate to k people
             * so total places including bar is (n+k-1) and among these places we have to find that
             * in how many ways we can choose (k-1) bars in (n+k-1) places
             * so answer is (n+k-1)Choose(k-1) = (n+k-1)!/(n!*(k-1)!) where ! is factorial.
             * */
            N = factorial[(int) N];
            long n1 = getMMI(factorial[(int) M], MOD);
            long n2 = getMMI(factorial[n], MOD);
            long ans = N % MOD;
            ans = (((ans * n1) % MOD) * n2) % MOD;
            System.out.print(ans + "\n");

        }

    }

}
