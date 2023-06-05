import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * Varun and you are talking about a movie that you have recently watched while Sachin is busy teaching Number Theory.
 * Sadly, Sachin caught Varun talking to you and asked him to answer his question in order to save himself from punishment.
 * The question says:Given two weights of a and b units, in how many different ways you can achieve a weight of d units using only the given weights?
 * Any of the given weights can be used any number of times (including 0 number of times).Since Varun is not able to answer the question,
 * he asked you to help him otherwise he will get punished.
 * Note: Two ways are considered different if either the number of times a is used or a number of times b is used is different in the two ways.
 *
 *
 * Constraints:
 * 1 ≤ T ≤ 10 ^ 5
 * 1 ≤ a < b ≤ 10 ^ 9
 * 0 ≤ d ≤ 10 ^ 9
 *
 * Sample Input 1
 *4
 *2 3 7
 *4 10 6
 *6 14 0
 *2 3 6
 *Sample Output 1
 *1
 *0
 *1
 *2
 */
public class VarunAndSachinCodingNinja {

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

    private static long getMMI(long a, long m) {
        long ans = multiplicativeModuloInverse(a, m).x;
        return (ans % m + m) % m;
    }


    /*
     * in the given question ax+by =d range of x (0<=x<=d/a,y=0) and y is (0<=y<=d/b,x=0)
     * and x = (d-by)/a for integral x ,(d-by)%a==0
     * suppose smallest value y = y1 such that (d-by1)%a==0 so the first solution is y1 and the next solution will be (y1+a) ,(y1+2*a) .. ans so on
     * range of y = d/b so (y1 +na) = (d/b)
     * so n = ((d/b)-y1)/a ,so this is the final answer we will hold including y1 to the solution total possible solution is n+1
     * So main task is to find the y1 the smallest value for which (d-b*y1)%a==0
     * ax+by =d so y = (d-ax)/b and (d-by1)%a==0 exploring it (d%a) - ((b%a)*(y1%a)) = 0
     * since y = (d-ax)/b taking the mod a both sides
     * y%a = ((d/b)%a - ((ax)/b)%a),  ((ax)/b)%a will always zero so minimum value of y%a = (d/b)%a
     * hence the minimum value of y1=  (d/b)%a
     * ans final answer is((d/b)-y1)/a and that's it. */
    private static long totalPossibleSolution(long a, long b, long d) {
        long mmi = getMMI(b, a);
        long firstY = ((d % a) * mmi) % a;
        long firstValue = d / b;
        if (d / b < firstY)
            return 0;
        long ans = (firstValue - firstY) / a;
        return ans + 1;
    }

    private static long getGCD(long a, long b) {
        if (b == 0)
            return a;
        return getGCD(b, a % b);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            String[] input = reader.readLine().split(" ");
            long a = Long.parseLong(input[0]);
            long b = Long.parseLong(input[1]);
            long d = Long.parseLong(input[2]);

            long gcd = getGCD(b, a);
            /*
             * here ax+by =d the Integral solution to this equation only exist,
             * when d is the Integral multiple of gcd(a,b) , this is diophantine equation.*/
            if ((d % gcd) != 0) {
                System.out.println(0);
                continue;
            }
            a /= gcd;
            b /= gcd;
            d /= gcd;
            /*
             * here we are dividing the a,b,d with the gcd(b,a)
             * bcz we are calculating the Multiplicative modulo Inverse of (b,a) which is only possible when a and b are coprime
             * thus by dividing a &b with it's gcd will make them co-prime and we can easily calculate the MMI.*/
            System.out.println(totalPossibleSolution(a, b, d));
        }

    }


}

class TripletM {
    public long x, y, gcd;

    TripletM() {
        x = y = gcd = 0;
    }
}
