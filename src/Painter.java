import java.util.ArrayList;

public class Painter {
    public static boolean isPossible(ArrayList<Integer> boards, int k, int sum) {
        int temp = 0;
        int i = 0;
        while (i < boards.size()) {
            if (k <= 0)
                return false;
            int j = i;
            temp = 0;
            while (j < boards.size() && temp <= sum) {
                temp += boards.get(j);
                j++;
            }
            if (temp > sum) {
                j--;
            }
            k -= 1;
            i = j;
        }
        return true;

    }

    public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {
        int e = 0;
        for (Integer item : boards) {
            e += item;
        }
        int ans = -1;
        int s = 0;
        while (e >= s) {
            int mid = (e + s) / 2;
            if (isPossible(boards, k, mid)) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;

    }
}