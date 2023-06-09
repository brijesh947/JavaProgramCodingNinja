package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *
 * You are given a magrid S ( a magic grid ) having R rows and C columns.
 * Each cell in this magrid has either a Hungarian horntail dragon that our intrepid hero has to defeat, or a flask of magic potion that his teacher Snape has left for him.
 * A dragon at a cell (i,j) takes away |S[i][j]| strength points from him, and a potion at a cell (i,j) increases Harry's strength by S[i][j].
 * If his strength drops to 0 or less at any point during his journey, Harry dies, and no magical stone can revive him.
 * Harry starts from the top-left corner cell (1,1) and the Sorcerer's Stone is in the bottom-right corner cell (R,C).
 * From a cell (i,j), Harry can only move either one cell down or right i.e., to cell (i+1,j) or cell (i,j+1) and he can not move outside the magrid.
 * Harry has used magic before starting his journey to determine which cell contains what,
 * but lacks the basic simple mathematical skill to determine what minimum strength he needs to start with to collect the Sorcerer's Stone. Please help him once again.
 *
 * Input Format :
 * The first line contains the number of test cases T. T cases follow.
 * Each test case consists of R C in the first line followed by the description of the grid in R lines, each containing C integers.

 * Rows are numbered 1 to R from top to bottom and columns are numbered 1 to C from left to right. Cells with S[i][j] < 0 contain dragons, others contain magic potions.
 *
 * Output Format :
 * Output T lines, one for each case containing the minimum strength Harry should start with from the cell (1,1) to have a positive strength through out his journey to the cell (R,C).
 *
 * Constraints:
 * 1 ≤ T ≤ 5

 * 2 ≤ R, C ≤ 500

 * -10^3 ≤ S[i][j] ≤ 10^3

 * S[1][1] = S[R][C] = 0
 * */

public class PowerGrid {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            String[] num = reader.readLine().split(" ");
            int r = Integer.parseInt(num[0]);
            int c = Integer.parseInt(num[1]);
            int[][] arr = new int[r][c];
            for (int i = 0; i < r; i++) {
                String[] temp = reader.readLine().split(" ");
                for (int j = 0; j < c; j++) {
                    arr[i][j] = Integer.parseInt(temp[j]);
                }
            }
            int[][] dp = new int[r][c];
            dp[r - 1][c - 1] = dp[r - 2][c - 1] = dp[r - 1][c - 2] = 1;

            for (int i = r - 3; i >= 0; i--) {
                dp[i][c - 1] = Math.max((dp[i + 1][c - 1] - arr[i + 1][c - 1]), 1);
            }
            for (int i = c - 3; i >= 0; i--) {
                dp[r - 1][i] = Math.max(((dp[r - 1][i + 1] - arr[r - 1][i + 1])), 1);
            }
            for (int i = r - 2; i >= 0; i--) {
                for (int j = c - 2; j >= 0; j--) {
                    int temp = Math.min((dp[i + 1][j] - arr[i + 1][j]), (dp[i][j + 1] - arr[i][j + 1]));
                    dp[i][j] = Math.max(temp, 1);
                }
            }
            System.out.println(dp[0][0]);
        }

    }
}
