package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
*
*
* You are given an integer N and are supposed to find the value of Euler totient function for N phi(N)
* Input Format: First line of input will contain T(number of test case)
* each test case follows as An integer N in new line.
* Output Format:
* For each test case print the answer in new line
* Constraints:
   1 <= T <= 100
   1 <= N <= 10^9
* */
public class NinjaTotient {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine().trim());


        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            long ans = getTotientOfNumber(n);
            System.out.println(ans);
        }

    }

    private static long getTotientOfNumber(long n) {
        long ans = n;
        /*
         * You can not use space to determine the totient value as Range of N is 10^9, You can't create array
         * of too large memory.
         * since Totient(n) = (p1^a)*(p2^b)*(p3^c).....(P^k) where p1,p2,p3 are prime numbers
         * we are starting from 2 which is prime ,so we will divide n till it is divisible by 2 now n = (2^k)*c
         * similarly we will continue to do for the rest (n=c) of the number   */

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                ans = (ans * (i - 1)) / i;
            }
        }
        /*
         * n>1 means it is the last prime number which is also a factor of Given (N) that's why  we are adding it to answer*/
        if (n > 1) {
            ans = (ans * (n - 1)) / n;
        }
        return ans;
    }
}
