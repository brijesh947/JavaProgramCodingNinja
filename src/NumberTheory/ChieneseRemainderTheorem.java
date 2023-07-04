package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *
 * Ninja is learning about the mathematical division and remainders. So in curiosity, he forms two lists of numbers ‘ARR’ and ‘REM’ both having ‘N’ numbers.
 * Now Ninja is interested in finding the minimum positive number ‘X’ such that ‘X’ satisfies the following condition for all ‘N’ values:
 * X % ‘ARR’[i] = ‘REM’[i] (Remainder of X / ARR[i] should be equal to  ‘REM’[i]).
 * You are given two arrays ‘ARR’ and ‘REM’ both having ‘N’ values corresponding to the numbers and remainders.
 * Your task is to find the smallest positive integer ‘X’ fulfilling the given conditions.
 *
 * Note:
 * All numbers in ‘ARR’ are pairwise co-prime.
 * For Example
 * For the given if N = ‘3’, ‘ARR’ = [4,5,7] and ‘REM’ = [3,2,2].
 * The answer will be 107.
 *
 * Input Format:
 * The first line of the input contains an integer, 'T,’ denoting the number of test cases.
 * The first line of each test case contains an integer ‘N’ denoting the size of both arrays.
 * The second line of each test case contains ‘ARR’ array.
 * The third line contains the ‘REM’ array.
 *
 * Output Format:
 * For each test case, print a single integer denoting the smallest integer satisfying the given conditions.
 *
 * Note:
 * You do not need to print anything. It has already been taken care of. Just implement the given function.
 *
 * Constraints:
 * 1 <= T <= 10
 * 1 <= N <= 1000.
 * 1 <= ARR[i] , REM[i] <= 10^5.
 * For each i,  ARR[i] > REM[i].
 * Time limit: 1 sec*/


class CRTHelper {

    private static Triplet calculateMMI(long a, long b) {
        if (b == 0) {
            Triplet curr = new Triplet();
            curr.x = 1;
            curr.y = 0;
            curr.gcd = a;
            return curr;
        }

        Triplet temp = calculateMMI(b, a % b);

        Triplet fans = new Triplet();
        fans.x = temp.y;
        fans.y = (temp.x - (a / b) * (temp.y));
        fans.gcd = temp.gcd;
        return fans;
    }

    private static long getMMI(long a, long m) {
        Triplet ans = calculateMMI(a, m);
        return (ans.x + m) % m;
    }

    private static long[] getMultipliedArray(int[] arr, int n) {
        long count = 1;
        for (int i = 0; i < n; i++) {
            count = count * ((long) arr[i]);
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] = count / arr[i];
        }
        return ans;
    }

    public static long numbersAndRemainders(int n, int[] arr, int[] rem) {
        long[] ans = getMultipliedArray(arr, n);
        long N = ans[0] * arr[0];
        long count = 0;
        for (int i = 0; i < n; i++) {
            long inverse = getMMI(ans[i], arr[i]);
            count += ((long) rem[i] * ans[i] * inverse) % N;
            count = count % N;
        }
        return count;
    }

    static class Triplet {
        public long x, y, gcd;

        Triplet() {
            x = y = gcd = 0;
        }
    }
}


public class ChieneseRemainderTheorem {

    private static int t;
    private static int tn[];
    private static int nums[][];
    private static int rems[][];

    private static void takeInput() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine().split(" ")[0]);
        nums = new int[t][];
        rems = new int[t][];
        tn = new int[t];

        for (int c = 0; c < t; c++) {
            String nk[] = br.readLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            nums[c] = new int[n];
            rems[c] = new int[n];
            tn[c] = n;
            nk = br.readLine().split(" ");
            for (int k = 0; k < n; k++) {
                nums[c][k] = Integer.parseInt(nk[k]);
            }
            nk = br.readLine().split(" ");
            for (int k = 0; k < n; k++) {
                rems[c][k] = Integer.parseInt(nk[k]);
            }

        }
    }

    private static void execute() {
        int[][] numsCopy = nums;
        int[][] remsCopy = rems;
        for (int i = 0; i < t; ++i) {
            long ans = CRTHelper.numbersAndRemainders(tn[i], numsCopy[i], remsCopy[i]);
        }
    }

    private static void executeAndPrintOutput() {
        for (int i = 0; i < t; ++i) {
            long ans = CRTHelper.numbersAndRemainders(tn[i], nums[i], rems[i]);
            System.out.println(ans);

        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        takeInput();
        executeAndPrintOutput();
    }
}
