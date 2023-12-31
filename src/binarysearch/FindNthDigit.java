package binarysearch;



/*
*
* https://leetcode.com/problems/nth-digit/
* Given an integer n, return the nth digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]
*
* Example 1:
* Input: n = 3
* Output: 3
*
* Example 2:
* Input: n = 11
* Output: 0
*
* Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10
*/



public class FindNthDigit {


    public static int findNthDigit(int n) {
        if (n < 10)
            return n;

        int digitNo = 2;
        long mul = 9;
        n -= 9;
        int[] arr = {0, 0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
        while (true) {
            long temp = (mul * 10 * digitNo);
            if (n - temp < 0)
                break;
            n -= temp;
            digitNo++;
            mul = mul * 10;
        }

        int currentIndex = n % digitNo == 0 ? (n / digitNo) : (n / digitNo) + 1;
        int currentNumber = arr[digitNo] + currentIndex - 1;
        int offset = n % digitNo == 0 ? digitNo : n % digitNo;
        offset = digitNo - offset;
        currentNumber = currentNumber/(int) (Math.pow(10.0, (double) offset));
        System.out.println(currentNumber%10);
        return currentNumber%10;
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(1000000000));
    }
}
