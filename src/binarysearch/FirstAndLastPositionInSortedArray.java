package binarysearch;

public class FirstAndLastPositionInSortedArray {


    public static int[] searchRange(int[] nums, int target) {
        int n = nums.length;

        int firstIndex = lowerBound(nums, target, 0, n - 1);
        int lastIndex = upperBound(nums, target, 0, n - 1);

        int[] ans = new int[2];
        ans[0] = firstIndex;
        ans[1] = lastIndex;
        return ans;

    }

    private static int upperBound(int[] nums, int target, int start, int end) {
        if (start > end)
            return -1;

        int a1, a2, a3;
        a1 = a2 = a3 = -1;
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            a1 = mid;
            a2 = upperBound(nums, target, mid + 1, end);
        } else if (nums[mid] > target) {
            a3 = upperBound(nums, target, start, mid - 1);
        } else {
            a2 = upperBound(nums, target, mid + 1, end);
        }

        if (a1 == -1 && a2 == -1 && a3 == -1)
            return -1;

        if ((a1 == -1 && a2 == -1 && a3 != -1) || (a2 == -1 && a3 == -1 && a1 != -1) || (a1 == -1 && a3 == -1 && a2 != -1))
            return Math.max(Math.max(a1, a2), a3);

        if ((a1 == -1 && a2 != -1 && a3 != -1))
            return Math.max(a2, a3);

        if ((a1 != -1 && a2 != -1 && a3 == -1))
            return Math.max(a2, a1);

        if ((a2 == -1 && a1 != -1 && a3 != -1))
            return Math.max(a1, a3);

        return Math.max(Math.max(a1, a2), a3);
    }

    private static int lowerBound(int[] nums, int target, int start, int end) {
        if (start > end)
            return -1;

        int a1, a2, a3;
        a1 = a2 = a3 = -1;
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            a1 = mid;
            a2 = lowerBound(nums, target, start, mid - 1);
        } else if (nums[mid] > target) {
            a2 = lowerBound(nums, target, start, mid - 1);
        } else {
            a3 = lowerBound(nums, target, mid + 1, end);
        }
        if (a1 == -1 && a2 == -1 && a3 == -1)
            return -1;

        if ((a1 == -1 && a2 == -1 && a3 != -1) || (a2 == -1 && a3 == -1 && a1 != -1) || (a1 == -1 && a3 == -1 && a2 != -1))
            return Math.max(Math.max(a1, a2), a3);

        if ((a1 == -1 && a2 != -1 && a3 != -1))
            return Math.min(a2, a3);

        if ((a1 != -1 && a2 != -1 && a3 == -1))
            return Math.min(a2, a1);

        if ((a2 == -1 && a1 != -1 && a3 != -1))
            return Math.min(a1, a3);

        return Math.min(Math.min(a1, a2), a3);


    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 4, 4, 4};
        int[] ans = searchRange(arr, 4);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
