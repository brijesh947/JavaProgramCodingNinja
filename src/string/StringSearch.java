package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * You are given a string S and a test string T. You have to find whether string S is a substring of the string T.
 * Input Format:
 * First line of input will contain an integer T, representing the number of test cases
 * Each test case is as follows:
 * Line 1: contains the string S.
 * Line 2: contains the string T.
 * Constraints:
 * 1 <= T <= 100
 * 1 <= |S|, |T| <= 10^5
 * Output Format:
 * For each test case print "Yes" if S is present in T or "No" otherwise.
 * */

public class StringSearch {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, NumberFormatException {
        int t = Integer.parseInt(reader.readLine().trim());

        while (t-- > 0) {
            String pattern = reader.readLine().trim();
            String text = reader.readLine().trim();
            String finalText = pattern + "$" + text;
            int n = finalText.length();
            int[] Z = new int[n + 1];

            buildZArray(finalText, Z, n);
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (Z[i] == pattern.length()) {
                    flag = true;
                    break;
                }
            }

            System.out.println(flag ? "Yes" : "No");

        }

    }


    /*
     * I have uses the Z-Algorithm to get the pattern in a given string
     * Please go through the coding-ninja youtube channel and watch Z-algorithm video
     * or Search on youtube about Z-algo and watch coding ninja video.
     * */
    private static void buildZArray(String finalText, int[] Z, int n) {
        Z[0] = 0;
        int l = 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            if (i > r) {
                l = i;
                r = i;
                while (r < n && finalText.charAt((r - l)) == finalText.charAt(r))
                    r++;
                Z[i] = r - l;
                r--;
            } else {
                int k = i - l;
                if (Z[k] <= (r - i)) {
                    Z[i] = Z[k];
                } else {
                    l = i;
                    while (r < n && finalText.charAt((r - l)) == finalText.charAt(r))
                        r++;
                    Z[i] = r - l;
                    r--;
                }
            }
        }

    }
}
