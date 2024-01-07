package binarysearch;

import java.util.Arrays;

/*
* https://leetcode.com/problems/maximum-candies-allocated-to-k-children/description/
* this is standard problem on binary search.
* */

public class MaximumCandies {


    public static int maximumCandies(int[] candies, long k) {

        Arrays.sort(candies);
        long count = 0;
        int start = 1;
        int n = candies.length;
        int end = candies[n - 1];
        int ans = 0;
        while (end >= start) {
            int mid = start + (end - start) / 2;
            count=0;
            for (int candy : candies) {
                count += (candy / mid);
            }
            if(count >=k){
                ans = mid;
                start = mid+1;
            }else {
                end = mid-1;
            }
        }

        return ans;

    }

    public static void main(String[] args) {
       int[] candies = {5,8,6};
        System.out.println(maximumCandies(candies,19));
    }
}
