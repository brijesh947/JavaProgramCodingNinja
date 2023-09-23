package rangequery;

import java.io.IOException;
import java.util.Scanner;

public class MaximumQuery {


    static Scanner reader = new Scanner(System.in);

    /*
     * this is getting TLE when it is being run on large test cases in java.
     * but same code after converting in c++ submitted successfully.
     * */
    public static void main(String[] args) throws IOException, NumberFormatException {
        int N = reader.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = reader.nextInt();
        }
        int[] tree = new int[4 * N];
        for (int i = 0; i < 4 * N; i++)
            tree[i] = 0;
        buildSegmentTree(arr, tree, 0, N - 1, 0);
        int query = reader.nextInt();
        while (query-- > 0) {
            int start = reader.nextInt();
            int end = reader.nextInt();
            int ans = getAns(tree, start, end, 0, 0, N - 1);
            System.out.print(ans + "\n");
        }
    }

    private static int getAns(int[] tree, int start, int end, int currNodeIndex, int startRange, int endRange) {
        if (startRange > endRange)
            return 0;
        //completely outside the range
        if (start > endRange || end < startRange)
            return 0;
        //completely inside the range.
        if (startRange >= start && endRange <= end)
            return tree[currNodeIndex];

        //partially inside and outside
        int mid = startRange + (endRange - startRange) / 2;
        int temp1 = 0, temp2 = 0;

        if (mid >= end) {
            temp1 = getAns(tree, start, end, 2 * currNodeIndex + 1, startRange, mid);
        } else if (start > mid) {
            temp2 = getAns(tree, start, end, 2 * currNodeIndex + 2, mid + 1, endRange);
        } else {
            temp1 = getAns(tree, start, end, 2 * currNodeIndex + 1, startRange, mid);
            temp2 = getAns(tree, start, end, 2 * currNodeIndex + 2, mid + 1, endRange);
        }
        return Math.max(temp2, temp1);
    }

    private static void buildSegmentTree(int[] arr, int[] tree, int start, int end, int currNodeIndex) {
        if (start == end) {
            tree[currNodeIndex] = arr[start];
            return;
        }
        int mid = start + (end - start) / 2;
        buildSegmentTree(arr, tree, start, mid, 2 * currNodeIndex + 1);
        buildSegmentTree(arr, tree, mid + 1, end, 2 * currNodeIndex + 2);
        tree[currNodeIndex] = Math.max(tree[2 * currNodeIndex + 1], tree[2 * currNodeIndex + 2]);
    }
}
