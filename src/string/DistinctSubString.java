package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


/*
 * You are given a string S. You are supposed to find the total number of different substrings of the string S.
 *
 * Input Format:
 * First line of input contains an integer T, representing the number of test cases.
 * Next T lines contain the string S
 *
 * Constraints:
 * 1 <= T <= 100
 * 1 <= |S| <= 100
 *
 * Output Format:
 * For each query, print the number of distinct substrings in a new line.
 *
 * */

public class DistinctSubString {

    private static final long MOD = (long) (1e18 + 7);
    private static final long PRIME = 31;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException, NumberFormatException {


        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            String str = reader.readLine().trim();
            Set<Long> distinctSubstring = new HashSet<>();
            int n = str.length();
            for (int i = 0; i < n; i++) {
                long hash = 0;
                for (int j = i; j < n; j++) {
                    /*
                     * we have generated the hashcode instead of
                     * just comparing the string itself
                     * */
                    hash = ((hash * PRIME) % MOD + (str.charAt(j) - 'a' + 1)) % MOD;
                    if (!distinctSubstring.contains(hash)) {
                        distinctSubstring.add(hash);
                    }
                }

            }
            int totalSubString = distinctSubstring.size();
            System.out.println(totalSubString);

        }


    }
}
