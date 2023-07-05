package NumberTheory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * You are given a positive integer ‘N’, your task is to return an array/list of integers such that the sum of Euler Totient Function values of each integer of the array is equal to ‘N’.
 * Euler Totient Function:
 * In number theory, Euler's totient function counts the positive integers up to a given integer ‘N’ that is relatively prime to ‘N’.
 * More details about Euler’s Totient Function can be found here

 * For Example:
 * For ‘N’ = 10, if we take the array as 1, 2, 5, 10,
 * The Euler totient function value of 1 is 1.
 * The Euler totient function value of 2 is 1.
 * The Euler totient function value of 5 is 4.
 * The Euler totient function value of 10 is 4.
 * So, the summation will be 1 + 1 + 4 + 4 = 10.
 * Hence this is a valid array for ‘N’ = 10.
 *
 * Note:
 * The solution will be verified by the actual value of the Euler Totient Function of 'N' and the sum of the Euler Totient Function of all the values returned.
 *  In the output, only the 'YES' or 'NO' is printed, according to the correctness of the solution.
 *
 * Input Format:
 * The first line contains a single integer ‘T’, representing the number of test cases.

 * The first line of each test case contains a single integer ‘N’, representing the sum of the Euler Totient Function values of all the elements of the output array.
 *
 * Output Format:
 * For each test case, Return all the elements of the array satisfying the condition given in the problem statement.

 * If there are multiple answers possible print any one of them.

 * Output for each test case will be printed in a separate line.
 *
 * Note
 * You are not required to print anything; it has already been taken care of. Just implement the function.
 * Constraints:
 * 1 <= T <= 100
 * 1 <= N <= 10^3

 * Time Limit: 1 sec.
 * */

class TotientHelper {


    /*
     * basically totient value of it's divisor(including and 1 and N) and all it's sum is equal to n
     * suppose we have a number n = p1*p2 where p1,p2 is prime number
     * then it's total number of factor's are 1,p1,p2,p1*p1(N)
     * eular value N = N*(1-1/p1)*(1-1/p2) if you simplify this N*(p1-1)*(p2-1)/(p1*p2) since N = p1*p2 hence
     * phi(N) = (p1-1)*(p2-1) = p1*p2-p1-p2+1;
     * phi(p1) = (p1-1);
     * phi(p2) = (p2-1);
     * phi(1) = 1;
     * sumof all values = p1*p2;
     *
     * */
    public static List<Integer> eulerTotientFunction(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if ((n % i) == 0) {
                ans.add(i);
                if (i != (n / i)) {
                    ans.add((n / i));
                }
            }
        }
        return ans;
    }

}

public class EulerTotientSum {
    public static class FastReader {

        BufferedReader br;
        StringTokenizer root;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (root == null || !root.hasMoreTokens()) {
                try {
                    root = new StringTokenizer(br.readLine());
                } catch (Exception r) {
                    r.printStackTrace();
                }
            }
            return root.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static FastReader sc = new FastReader();

    private static int t;
    private static int[] input;

    private static void takeInput() {
        t = sc.nextInt();
        input = new int[t];
        for (int i = 0; i < t; ++i) {
            input[i] = sc.nextInt();
        }
    }

    private static void execute() {
        for (int i = 0; i < t; ++i) {
            TotientHelper.eulerTotientFunction(input[i]);
        }
    }

    private static void executeAndPrintOutput() {
        for (int i = 0; i < t; ++i) {
            List<Integer> ans = TotientHelper.eulerTotientFunction(input[i]);
            int correct = 0;
            for (int a : ans) {
                correct += testEulerTotientFunction(a);
            }
            if (input[i] == correct)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    private static int testEulerTotientFunction(int a) {
        int ans = 0;
        for (int i = 1; i <= a; i++) {
            if (gcd(i, a) == 1) {
                ans++;
            }
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    public static void main(String[] args) {
        takeInput();
        executeAndPrintOutput();
        out.close();
    }
}
