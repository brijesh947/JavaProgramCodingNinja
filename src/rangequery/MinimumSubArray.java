package rangequery;

import java.util.Scanner;


/*
 * You are given a sequence A[1], A[2], ..., A[N] of N numbers ( 0 ≤ A[i] ≤ 10^8 , 2 ≤ N ≤ 10^5 ).
 * There are Q queries, and each query is defined in either of the following two ways:
 * Query on range:
 * You are given two numbers i and j; the answer to each query is the minimum number between the range (i, j) (including both i and j).
 *  Note that in this query, i and j lies in the range: [1, N].
 * Update query:
 * You are given two numbers i and B; update A[i] to B. Note that in this query, i lies in the range: [1, N].
 *
 * Input Format:
 * The first line of input contains two integers: N and Q, representing the length of the sequence and the number of queries.
 * The second line of input contains N space-separated integers, A[i].
 * Next, Q lines contain the queries. Each line contains one character, followed by two space-separated integers.
 * For the query on range, the character will be q and for the update query, the character will be u.
 *
 * Constraints:
 * 1 ≤ i ≤ N
 * 0 ≤ B ≤ 10^8
 * 1 ≤ i <= j ≤ N
 *
 * Output Format:
 * For each query on range, print the minimum number between the range, in a new line.*/

public class MinimumSubArray {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int N, Q;
        N = scanner.nextInt();
        Q = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] tree = new int[3 * N];
        buildTree(arr, tree, 0, N - 1, 1);
        while (Q-- > 0) {
            char c = scanner.next().charAt(0);
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            if (c == 'u') {
                int index = start;
                int value = end;
                updateSegmentTree(arr, tree, 0, N - 1, 1, index - 1, value);
            } else if (c == 'q') {
                int ans = getQueryAns(tree, start - 1, end - 1, 1, 0, N - 1);
                System.out.println(ans);
            }
        }
        scanner.close();
    }

    private static int getQueryAns(int[] tree, int start, int end, int currIndex, int startRange, int endRange) {
        if (startRange > endRange)
            return Integer.MAX_VALUE;
        //completely outside the tree range.
        if (endRange < start || end < startRange)
            return Integer.MAX_VALUE;

        //completely inside the tree range.
        if (startRange >= start && endRange <= end)
            return tree[currIndex];

        //partially inside and outside the tree.
        int mid = startRange + (endRange - startRange) / 2;
        int temp1 = Integer.MAX_VALUE, temp2 = Integer.MAX_VALUE;
        if (mid >= end) {
            temp1 = getQueryAns(tree, start, end, 2 * currIndex, startRange, mid);
        } else if (start > mid) {
            temp2 = getQueryAns(tree, start, end, 2 * currIndex + 1, mid + 1, endRange);
        } else {
            temp1 = getQueryAns(tree, start, end, 2 * currIndex, startRange, mid);
            temp2 = getQueryAns(tree, start, end, 2 * currIndex + 1, mid + 1, endRange);
        }
        return Math.min(temp2, temp1);

    }

    private static void updateSegmentTree(int[] arr, int[] tree, int start, int end, int currIndex, int index, int value) {
        if (start == end) {
            tree[currIndex] = value;
            arr[index] = value;
            return;
        }
        int mid = (start + end) / 2;
        if (mid >= index) {
            updateSegmentTree(arr, tree, start, mid, 2 * currIndex, index, value);
        } else {
            updateSegmentTree(arr, tree, mid + 1, end, 2 * currIndex + 1, index, value);
        }
        tree[currIndex] = Math.min(tree[2 * currIndex], tree[2 * currIndex + 1]);
    }


    /*
     * building  the arr value in the form of tree
     * at each level we are dividing the tree in two parts and parent node
     * store the minimum in that given range according to the condition
     *  */
    private static void buildTree(int[] arr, int[] tree, int start, int end, int currIndex) {

        if (start == end) {
            tree[currIndex] = arr[start];
            return;
        }
        int mid = (start + end) / 2;

        buildTree(arr, tree, start, mid, 2 * currIndex);
        buildTree(arr, tree, mid + 1, end, 2 * currIndex + 1);
        tree[currIndex] = Math.min(tree[2 * currIndex], tree[2 * currIndex + 1]);

    }
}
