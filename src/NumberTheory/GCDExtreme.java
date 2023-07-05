package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 *
 *  Given the value of N, you will have to find the value of G. The meaning of G is given in the following code
 *  G=0;
 *   for(i = 1 ; i < N ; i++)
 *      for(j = i+1 ; j <= N ; j++)
 *        G+=gcd(i,j);
 *  Here gcd() is a function that finds the greatest common divisor of the two input numbers.
 *  Input Format:
 *   The first line of input will contain T(number of the test case). Each test case contains an integer N.
 *  Output Format:
 *   For each test case print the answer in a new line.
 *  Constraints:
 *   1 <= T <= 10^5
 *   1 <= N <= 10^5
 *  */
public class GCDExtreme {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 100001;


    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        long[] gcdExtreme = preComputeExtremeGCD();
        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            //since we have precomputed the result in above array just printing it.
            System.out.println(gcdExtreme[n]);
        }
    }

    /*
     * Here the question is suppose F(n) is the answer of this question then F(n) is equal to
     * F(n) = sumOf(gcd(1,i) 2<=i<=N)  + sumOf(gcd(2,i) 3<=i<=N) + .... sumOf(gcd(i,N) N-1<=i<N)
     * upon distributing for 3 ,4 ,5 cases you will find that it can be written as follows
     * F(n) = F(n-1) + sumOf(gcd(i,N) 1<=i<=N);
     * this is what we are doing in this method
     * */
    private static long[] preComputeExtremeGCD() {

        int[] eulerArray = getTotient();
        long[] ans = new long[MAX];
        for (int i = 0; i < MAX; i++) {
            ans[i] = 0;
        }
        /*
         * Here we are calculating sumOf(gcd(i,N) 1<=i<=N)
         * for any particular i it can be written  as (this is formula which i writing)
         * sumOf(d*phi(i/d))  where d is the distinct divisor of i and phi(i/d) is euler's totient value of (i/d)
         * */
        for (int i = 1; i < MAX; i++) {
            for (int j = 2; j * i < MAX; j++) {
                ans[i * j] += (i * eulerArray[j]);
            }
        }
        /*
         * F(n) = F(n-1) + sumOf(gcd(i,N) 1<=i<=N);
         * here we are doing the above formula
         * */
        for (int i = 2; i < MAX; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }


    /*Here we are calculating the Euler's toteint function for Integer upto 10^5 */
    private static int[] getTotient() {
        int[] euler = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            euler[i] = i;
        }
        for (int i = 2; i < MAX; i++) {
            if (euler[i] == i) {
                euler[i] = i - 1;
                for (int j = 2 * i; j < MAX; j += i) {
                    euler[j] -= euler[j] / i;
                }
            }
        }
        return euler;
    }
}
