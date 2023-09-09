package trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * Given an array of N integers, find the subarray whose XOR is maximum.
 *
 * Input Format:
 * First line of input contains an integer N, representing number of elements in array.
 * Next line contains N space-separated integers.
 *
 * Constraints:
 * 1 <= N <= 10^6
 * 1 <= A[i] <=10^5
 *
 * Output Format:
 * Print XOR of the subarray whose XOR of all elements in subarray is maximum over all subarrays.
 * */

public class MaximumXorSubArray {

    static class Tree {
        Tree left;
        Tree right;

        Tree() {
            left = null;
            right = null;
        }
    }


    /*
     * here we are making the trie in form bit of the number
     * */
    private static void insert(Tree head, int num) {
        Tree curr = head;
        for (int i = 31; i >= 0; i--) {
            int b = (num >> i) & 1;
            if (b == 0) {
                if (curr.left == null) {
                    curr.left = new Tree();
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new Tree();
                }
                curr = curr.right;
            }
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, NumberFormatException {
        int n = Integer.parseInt(reader.readLine().trim());
        int[] arr = new int[n];
        String[] num = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(num[i]);
        }
        Tree head = new Tree();
        int ans = 0;
        int highestXor = 0;
        /*
         * we will keep inserting the xor of sub-array for every i index
         * suppose we have array of [1,8,2,5,3] and we are at index i=3
         * so in our trie 3 item will be there (1),(1^8),(1^8^2)
         * and our current element will be ans = (1^8^2^5)
         * ans^1 = (8^2^5)
         * ans^(1^8) = (2^5)
         * ans^(1^8^2) = 5
         * ans = 1^8^2^5
         * so far answer we will check in the trie which Xor is maximum possible
         * and we can do the same by checking the bit, suppose our answer's 1st bit is 1 so in trie we will search for zero
         * bcz in xor if two element will be different then their xor will be 1 hence for every bit in answer we will check
         * for it's reverse bit if it is there  then we will add this to answer otherwise(if opposite bit is not present) we'll go in the
         * same direction and if any move(bit) is not present we'll break.
         * and for every index we'll keep updating our maximumXor
         * */
        for (int i = 0; i < n; i++) {
            ans = (ans ^ arr[i]);
            Tree curr = head;
            int temp = 0;
            for (int j = 31; j >= 0; j--) {
                int b = (ans >> j) & 1;
                if (b == 0) {
                    if (curr.right != null) {
                        temp += (1 << j);
                        curr = curr.right;
                    } else if (curr.left != null) {
                        curr = curr.left;
                    } else
                        break;
                } else {
                    if (curr.left != null) {
                        temp += (1 << j);
                        curr = curr.left;
                    } else if (curr.right != null) {
                        curr = curr.right;
                    } else
                        break;
                }
            }
            if (temp > highestXor) {
                highestXor = temp;
            }
            insert(head, ans);
        }
        System.out.println(highestXor);
    }
}
