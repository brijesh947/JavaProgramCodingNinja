import java.io.*;



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