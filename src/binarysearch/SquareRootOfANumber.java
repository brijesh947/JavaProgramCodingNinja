package binarysearch;


/*
* https://leetcode.com/problems/sqrtx/description/
* how to calculate the nearest squre root of any given non-negative number.
* */
public class SquareRootOfANumber {

    public static int mySqrt(int x) {
        if (x < 1)
            return 0;
        if (x < 4)
            return 1;

        long low = 0;
        long high = (x / 2);
        int ans = -1;
        while (high >= low) {
            long mid = low + (high - low) / 2;
            if ((mid * mid) == x)
                return (int) mid;
            else if ((mid * mid) < x) {
                ans = (int) mid;
                low = mid + 1;
            } else
                high = mid - 1;

        }
        return ans;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            System.out.println("number is " + i + " and root is " + mySqrt(i));
        }

        System.out.println(mySqrt(10000079));
    }
}
