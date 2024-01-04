package binarysearch;

public class SearchInRotatedSortedArray {


    public static int search(int[] nums, int target) {

        int n = nums.length;
        int pivotIndex = partition(nums, n);

        int firstIndex = binarySearch1(nums, 0, pivotIndex, target);
        int secondIndex = binarySearch1(nums, pivotIndex + 1, n - 1, target);
        return Math.max(firstIndex, secondIndex);

    }

    private static int binarySearch1(int[] nums, int start, int end, int target) {
        if (start > end)
            return -1;

        int s, e, mid;
        s = start;
        e = end;
        mid = -1;
        int index = -1;
        while (e >= s) {
            mid = (e + s) / 2;
            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] > target)
                e = mid - 1;
            else
                s = mid + 1;
        }
        return index;
    }

    private static int partition(int[] nums, int n) {
        if (n == 1)
            return 0;
        int s, e, mid;
        mid = -1;
        s = 0;
        e = n - 1;
        while (e >= s) {
            mid = (e + s) / 2;
            if (nums[mid] >= nums[0])
                s = mid + 1;
            else
                e = mid - 1;
        }
        if ((mid != 0 && nums[mid] > nums[mid - 1]) | mid == 0)
            return mid;
        return mid - 1;

    }

    public static void main(String[] args) {

        int[] arr = {8, 9, 2, 3, 4};

        int ans = search(arr, 9);
        System.out.println(ans);

    }
}
