import java.util.Scanner;

/*
 *
 *  Given an integer matrix of size m*n,
 *  you need to find out the value of minimum cost to reach from the cell (0, 0) to (m-1, n-1).
 *  From a cell (i, j), you can move in three directions : (i+1, j), (i, j+1) and (i+1, j+1).
 *  Cost of a path is defined as the sum of values of each cell through which path passes.
 *
 *  Input Format :
 *  Line 1 : Two integers, m and n
 *  Next m lines : n integers of each row (separated by space)
 *
 *  Output Format :
 *  Minimum cost
 *
 *  Constraints :
 *  1 <= m, n <= 100
 * */

class MinCostPathHelper {
    public static int minCostPath(int input[][]) {
        int n = input.length;
        int m = input[0].length;
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = input[n - 1][m - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i][m - 1] = dp[i + 1][m - 1] + input[i][m - 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1][i] = dp[n - 1][i + 1] + input[n - 1][i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                dp[i][j] = input[i][j] + Math.min(dp[i + 1][j + 1], Math.min(dp[i + 1][j], dp[i][j + 1]));
            }
        }
        return dp[0][0];

    }
}

public class MinCostPath {

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int m = s.nextInt();
        int n = s.nextInt();
        int input[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                input[i][j] = s.nextInt();
            }
        }
        System.out.println(MinCostPathHelper.minCostPath(input));
    }
}
