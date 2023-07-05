package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*      Given a number, find the total number of divisors of the factorial of the number.
 *      Since the answer can be very large, print answer modulo 10^9+7.
 *
 *      Input Format:
 *        The first line contains T, number of test cases.
 *        T lines follow each containing the number N.
 *
 *      Output Format:
 *        Print T lines of output each containing the answer.
 *        Constraints
 *        1 <= T <= 500
 *        0 <= N <= 50000
 */

public class DivisorsOfFactorial {

    private static final int MAX = 50001;
    public static final int MOD = 1000000007;


    /* Here calculating the prime number till MAX=50001 since
     * any divisor in N! will always be less than N
     * */
    private static ArrayList<Integer> sieve() {
        boolean[] isPrime = new boolean[MAX];
        for (int i = 0; i < MAX; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        ArrayList<Integer> prime = new ArrayList<>();
        for (int i = 2; i < MAX; i++) {
            if (isPrime[i])
                prime.add(i);
        }
        return prime;

    }

    private static long divisors(int n, ArrayList<Integer> prime) {
        long result = 1;
        for (Integer primes : prime) {
            if ((primes <= n)) {
                long count = 0;
                long temp = primes;
                /*
                * since N! will be divisible by all prime number in the range of 2 to N
                * suppose number N=12 so prime factors are 2,3,5,7,11
                * any number N = (p1^a)*(p2^b)..(pn^k) so total number of divisor is (a+1)*(b+1)*(c+1)....(k+1)
                * here starts for p=2 and N=12
                * here number of factors of 2 in 12 = 12/2 =6 taking 2 at a time
                * Now it is also possible that 2*2*3 =12 is also a solution to 12, so we have to take care taking 2 ,2 at time
                * which is for any p is p*p and 3 at a time will be p*p*p and so on.
                * and that's what we are doing in this loop.   */
                while (temp <= n) {
                    count = (count + ((n / temp) % MOD)) % MOD;
                    temp = (temp * primes) % MOD;
                }
                result = (result * ((count + 1) % MOD)) % MOD;

            }
        }
        return result;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> prime = sieve();
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            System.out.println(divisors(n, prime));
        }
    }
}
