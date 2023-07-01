import java.util.Scanner;

/*
 *
 *  You are given two integers 'n' and 'r' and a prime number 'p'.
 *  Your task is to find (nCr) % p where nCr can be calculated as n! / (r! * (n - r)!).
 *  Note :
 *  N!  =  1 * 2 * 3 *... N
 *
 *  Input format :
 *  The first line of input contains a single integer 'T', representing the number of test cases.
 *
 *  The first line of each test case contains three space-separated integers 'n', 'r', and 'p'.
 *
 *  Output format :
 *  For each test case, print a single line containing an integer representing the value of (nCr) % p.
 *
 *  The output of each test case will be printed on a separate line.
 *
 *  Note:
 *  You don't need to input or print anything, it has already been taken care of. Just implement the given function.
 *
 *  Constraints :
 *  1 <= T <= 5
 *  1 <= n, r, p <= 5 * 10 ^ 2
 *  p is prime number.

 *  Time limit: 1 sec.
 *  */

class SolutionHelper {


    private static TripletM multiplicativeModuloInverse(long a, long b) {
        if (b == 0) {
            TripletM ans = new TripletM();
            ans.x = 1;
            ans.y = 0;
            ans.gcd = a;
            return ans;
        }
        TripletM temp = multiplicativeModuloInverse(b, a % b);
        TripletM fans = new TripletM();
        fans.x = temp.y;
        fans.y = (temp.x - (a / b) * (temp.y));
        fans.gcd = temp.gcd;
        return fans;

    }

    private static class TripletM {
        public long x, y, gcd;

        TripletM() {
            x = y = gcd = 0;
        }
    }

    private static long getMMI(long a, long m) {
        long ans = multiplicativeModuloInverse(a, m).x;
        return (ans % m + m) % m;
    }

    public static int fermatLittle(int a, int b, int c) {
        int ans = 1;
        if (b > a || a >= c)
            return 0;

        int mx = Math.max(b, a - b);
        for (int i = mx + 1; i <= a; i++) {
            ans = (ans * i) % c;
        }
        int mn = Math.min(b, a - b);
        for (int i = 2; i <= mn; i++) {
            int mmi = (int) getMMI(i, c);
            ans = (ans * mmi) % c;
        }
        return ans;
    }

}


public class FermatLittleTheorem {
    static int t;
    static int allA[];
    static int allB[];
    static int allC[];

    public static void takeInput() {
        Scanner s = new Scanner(System.in);
        t = s.nextInt();
        allA = new int[t];
        allB = new int[t];
        allC = new int[t];
        for (int i = 0; i < t; i++) {
            allA[i] = s.nextInt();
            allB[i] = s.nextInt();
            allC[i] = s.nextInt();
        }
        s.close();
    }

    public static void execute() {
        for (int i = 0; i < t; i++) {

            // Get the answer from scaffold/solution
            SolutionHelper.fermatLittle(allA[i], allB[i], allC[i]);
        }
    }

    public static void executeAndPrintOutput() {
        for (int i = 0; i < t; i++) {
            int result = SolutionHelper.fermatLittle(allA[i], allB[i], allC[i]);
            System.out.println(result);
        }

    }

    public static void main(String[] args) {
        FermatLittleTheorem.takeInput();
        FermatLittleTheorem.executeAndPrintOutput();
    }
};

