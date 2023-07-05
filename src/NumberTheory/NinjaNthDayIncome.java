package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/*
* Daulat Ram is an affluent businessman. After demonetization, IT raid was held at his accommodation in which all his money was seized. He is very eager to gain his money back.
*  he started investing in certain ventures and earned out of them. On the first day, his income was Rs. X, followed by Rs. Y on the second day.
* Daulat Ram observed his growth as a function and wanted to calculate his income on the Nth day.
* The function he found out was FN = FN-1 + FN-2 + FN-1×FN-2
* Given his income on day 0 and day 1, calculate his income on the Nth day (yeah Its that simple).

* Input Format:
* The first line of input consists of a single integer T denoting the number of test cases.
* Each of the next T lines consists of three integers F0, F1 and N respectively.

* Output Format:
* For each test case, print a single integer FN, as the output can be large, calculate the answer modulo 10^9+7.

* Constraints:
* 1 ≤ T ≤ 10^5
* 0 ≤ F0, F1, N ≤ 10^9
* */

public class NinjaNthDayIncome {

    private static final int MOD = 1000000007;
    private static final int MOD2 = MOD - 1;

    private static void getFibonacci(long[][] arr, long n) {
        if (n <= 1)
            return;
        getFibonacci(arr, n / 2);
        multiplyMatrix(arr, arr);
        if ((n % 2) != 0) {
            long[][] temp = {{1, 1}, {1, 0}};
            multiplyMatrix(arr, temp);
        }

    }

    private static void multiplyMatrix(long[][] a, long[][] b) {
        long[][] ans = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    ans[i][j] = (ans[i][j] + a[i][k] * b[k][j]) % MOD2;
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                a[i][j] = ans[i][j];
            }
        }
    }

    private static long getPower(long a, long n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return a % MOD;

        long ans = getPower(a, n / 2) % MOD;

        ans = (ans * ans) % MOD;
        if (n % 2 != 0) {
            ans = (ans * a) % MOD;
        }
        return ans;
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            String[] num = reader.readLine().split(" ");
            long f0 = Long.parseLong(num[0]);
            long f1 = Long.parseLong(num[1]);
            long n = Long.parseLong(num[2]);
            long[][] arr = {{1, 1}, {1, 0}};
            getFibonacci(arr, n - 2);
            long power1 = arr[0][0];
           // System.out.println("power1 is " + power1);
            long[][] arr2 = {{1, 1}, {1, 0}};
            getFibonacci(arr2, n - 1);
            long power2 = arr2[0][0];
            //System.out.println("power2 is " + power2);
            long a = f0 + 1;
            long b = f1 + 1;

            long ans1 = getPower(a, power1) % MOD;
            long ans2 = getPower(b, power2) % MOD;
            long fans = (ans2 * ans1) % MOD;
            System.out.println((fans - 1));


        }
    }
}
