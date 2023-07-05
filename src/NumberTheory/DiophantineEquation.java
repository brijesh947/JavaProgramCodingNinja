package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
* Diophantine equation  state that ax + by =c has only integral solution if and only if c = k*gcd(a,b) where k = [1,LONG_MAX]
* so in this given problem you have give a and b and c is just equal to gcd(a,b) so you have to find out that integral solution
* means value of x and y.
*
**/
public class DiophantineEquation {


    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = reader.readLine().split(" ");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);

        Triplet ans = extendedEuclid(a, b);
        System.out.println("x is " + ans.x + " and y is " + ans.y + " and gcd is " + ans.gcd);
    }

    private static Triplet extendedEuclid(int a, int b) {
        if (b == 0) {
            Triplet ans = new Triplet();
            ans.gcd = a;
            ans.x = 1;
            ans.y = 0;
            return ans;
        }

        Triplet temp = extendedEuclid(b, a % b);
        /*
        * concept here is gcd(a,b) = gcd(b,a%b)
        * if ax+ by = gcd(a,b) = gcd(b,a%b) = bx1 + (a%b)y1
        * so if we calculate the x1 and y1 then we can also calculate the x and y as well
        * since a%b = a - (a/b)*b ,so after applying this formula then both side will look like this
        * b(x1 - (a/b)*y1) + ay1 = ax + by , considering the coefficient of a and b so
        * x = y1
        * y = x1 - (a/b)*y1
        * and that's it
        * */
        Triplet ans = new Triplet();
        ans.x = temp.y;
        ans.y = temp.x - (a / b) * temp.y;
        ans.gcd = temp.gcd;
        return ans;
    }


}

class Triplet {
    public int x, y, gcd;

    Triplet() {
        x = y = gcd = 0;
    }
}
