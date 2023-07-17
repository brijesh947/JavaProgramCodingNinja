package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *
 * Gary is bored and wants to play an interesting but tough game . So he figured out a new board game called "destroy the neighbours" .
 * In this game there are N integers on a board.
 * In one move, he can pick any integer x from the board and then all the integers with value x+1 and x-1 gets destroyed .This move will give him x points.
 * He plays the game until the board becomes empty .
 * But as he want show this game to his friend Steven, he wants to learn techniques to maximise the points to show off .
 * Can you help Gary in finding out the maximum points he receive grab from the game ?
 *
 * Input Format :
 * First line will contain T (number of test case), each test case is consists of two line.
 * Line 1: Integer N
 * Line 2: A list of N integers
 *
 * Output Format :
 * For each test case print maximum points, Gary can receive from the Game setup in a newline.
 *
 * Constraints :
 * 1 <= T <= 50
 * 1 <= N <= 10^5
 * 1 <= A[i] <= 1000
 * */

public class NinjaBoreDam {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, NumberFormatException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] arr = new int[n];
            String[] num = reader.readLine().split(" ");
            int m = -1;
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(num[i]);
                if (arr[i] > m) {
                    m = arr[i];
                }
            }
            int[] freq = new int[m + 1];
            int[] dp = new int[m + 1];
            for (int i = 0; i <= m; i++) {
                freq[i] = 0;
                dp[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                freq[arr[i]] += arr[i];
            }

            /*
             * since the range of integer is 1000 only that's why we have created the array with maximum_element+1 size
             * and in freq array we have store the total sum of the given number
             * after that we have created the dp array which is basically contain the maximum answer till any particular index
             * hence dp[0] and dp[1] will be equal to their freq index
             * from 2 onwards we will check whether freq[i]+ dp[i-2]>dp[i-1] bcz if i is selected then i-1 can not be selected as per the question
             * here we are not  considering the i+1 bcz we are only concerned till i for every index that's why we don't have to consider i+1
             * if freq[i]+ dp[i-2]>dp[i-1] this hold it means dp[i] = freq[i]+ dp[i-2] otherwise dp[i-1] will be dp[i] bcz dp[i-1] is the maximum we get till ith index.
             *  */
            dp[0] = freq[0];
            dp[1] = freq[1];

            for (int i = 2; i <= m; i++) {
                int fans = freq[i] + dp[i - 2];
                dp[i] = Math.max(fans, dp[i - 1]);
            }

            System.out.println(dp[m]);

        }
    }
}
