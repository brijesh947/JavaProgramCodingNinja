import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
*
* A child is running up a staircase with n steps and can hop either 1 step, 2 steps or 3 steps at a time.
*  Implement a method to count how many possible ways the child can run up to the stairs. You need to return all possible number of ways.
* Time complexity of your code should be O(n).
* Since the answer can be pretty large soo print the answer with mod(10^9 +7)
*
* Input Format:
* First line will contain T (number of test case).
* Each test case is consists of a single line containing an integer N.
*
* Output Format:
* For each test case print the answer in new line
*
* Constraints :
* 1 <= T < = 10
* 1 <= N <= 10^5
* */

public class StairCaseProblem {
    private static final int MOD = 1000000007;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] arr = new int[n + 1];
            if (n <= 2) {
                System.out.println(n);
                continue;
            }
            if (n == 3) {
                System.out.println(n + 1);
                continue;
            }
            arr[0] = 0;
            arr[1] = 1;
            arr[2] = 2;
            arr[3] = 4;
            for (int i = 4; i <= n; i++) {
                arr[i] = (arr[i - 1] % MOD + (arr[i - 2] % MOD + arr[i - 3] % MOD) % MOD) % MOD;
            }

            System.out.println(arr[n]);

        }
    }
}
