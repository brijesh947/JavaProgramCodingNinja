import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * A cube free number is a number who’s none of the divisor is a cube number (A cube number is a cube of a integer like 8 (2 * 2 * 2) , 27 (3 * 3 * 3) ).
 * So cube free numbers are 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 17, 18 etc (we will consider 1 as cube free). 8, 16, 24, 27, 32 etc are not cube free number.
 * So the position of 1 among the cube free numbers is 1, position of 2 is 2, 3 is 3 and position of 10 is 9.
 * Given a positive number you have to say if its a cube free number and if yes then tell its position among cube free numbers.
 *
 * Note: we will consider 1 as the 1st cube free number
 *
 * Input Format:
 *   First line of the test case will be the number of test case T
 *   Each test case contain an integer N
 *
 * Output Format:
 *   For each test case print the position of N in cube free numbers and if its not a cube free number print "Not Cube Free" in a newline.
 *
 * Constraints:
 *   1 <= T <= 10^5
 *   1 <= N <= 10^6
 * */

public class NinjaCubeFreeNumbers {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static int MAX = 1000001;

    private static int[] getCubeFreeNumber() {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        int[] arr = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            arr[i] = i;
        }
        for (int i : prime) {
            int j = i * i * i;
            for (int k=j; k < MAX; k += j) {
                arr[k] = -1;
            }
        }
        int count = 0;
        for (int i = 0; i < MAX; i++) {
            if (arr[i] == -1) {
                count++;
            } else {
                arr[i] = i - count;
            }
        }

        return arr;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int[] ans = getCubeFreeNumber();
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            if (ans[n] == -1) {
                System.out.println("Not Cube Free");
            } else {
                System.out.println(ans[n]);
            }
        }
    }
}
