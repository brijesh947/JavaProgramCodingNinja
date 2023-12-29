package binarysearch;


/*
* https://leetcode.com/problems/median-of-two-sorted-arrays/description/
* this is the problem from leetcode
* you can check solution on youtube
*/

public class MedianOfTwoSortedArrays {


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) return findMedianSortedArrays(nums2, nums1);
        int n = nums1.length;
        int m = nums2.length;

        if (n == 0 && m != 0) {
            if (m < 2)
                return nums2[0] / 1.0;
            int mid = m / 2;
            if (m % 2 != 0)
                return (nums2[mid]) / 1.0;
            return (nums2[mid] + nums2[mid - 1]) / 2.0;
        }
        if (n != 0 && m == 0) {
            if (n < 2)
                return nums1[0] / 1.0;
            int mid = n / 2;
            if (n % 2 != 0)
                return (nums1[mid]) / 1.0;
            return (nums1[mid] + nums1[mid - 1]) / 2.0;
        }

        int low = 0;
        int high = n;
        int middle = (n + m + 1) / 2;
        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = middle - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = cut2 ==0  ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r1 = cut1 == n ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = cut2 == m ? Integer.MAX_VALUE : nums2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                if ((n + m) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2) / 1.0;
                }
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[] num1 = {4};
        int[] num2 = { 1, 2,3,5,6};
        System.out.println(findMedianSortedArrays(num1, num2));
    }
}
