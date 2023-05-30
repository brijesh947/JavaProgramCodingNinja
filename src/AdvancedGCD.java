import java.io.*;

/**
 * Varun explained its friend Sanchit the algorithm of Euclides to calculate the GCD of two numbers. Then Sanchit implements the algorithm
 * int gcd(int a, int b)
 * {
 *
 *     if (b==0)
 *     return a;
 *     else
 *     return gcd(b,a%b);
 * }
 * and challenges to Varun to calculate gcd of two integers, one is a little integer and another integer can have 10^4 digits.
 * Your task is to help Varun an efficient code for the challenge of Sanchit
 *
 *
 * Input Format
 *
 * The first line of input will contain T(number of the test case), each test case follows as.
 * Each test case consists of two number A and B.
 *
 * 1 <= T <= 100
 * 1 <= A <= 4*10^5
 * 1 <= |B| <= 10^4
 * where |B| is the length of the integer B
 *
 * Output Format:
 * Print for each pair (A,B) in the input one integer representing the GCD of A and B.
 */
/

public class AdvancedGCD {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] numbers = br.readLine().split(" ");
            int a = Integer.parseInt(numbers[0]);
            String number = numbers[1];
            int b = parseTheString(number, a);
            int ans = gcd(a, b);
            System.out.println(ans);
        }


    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    private static int parseTheString(String number, int mod) {

        int prev_answer = 0;
        char[] item = number.toCharArray();
        int curr_answer = 0;
        for (char digit : item) {
            int curr_digit = Character.getNumericValue(digit);
            curr_answer = ((prev_answer * 10) % mod + curr_digit % mod) % mod;
            prev_answer = curr_answer;
        }
        return prev_answer;
    }
}