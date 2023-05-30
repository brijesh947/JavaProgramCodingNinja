import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Gas implements Comparable<Gas> {
    int gValue, cost;

    public Gas(int gas, int c) {
        gValue = gas;
        cost = c;
    }

    @Override
    public int compareTo(Gas o) {
        return cost - o.cost;
    }
}

public class Solution {
    public static int startingPoint(int[] gas, int[] cost, int n) {
        ArrayList<Gas> arrayList = new ArrayList<>(n);
        Gas ans = null;
        for (int i = 0; i < n; i++) {
            arrayList.add(i,new Gas(gas[i],cost[i]));
        }
        Collections.sort(arrayList);
        int count, stIndex;
        for (int i = 0; i < n; i++) {
            Gas gas1 = arrayList.get(i);
            if (gas[i]>=cost[i]) {
                count = gas1.gValue - gas1.cost;
                stIndex = i;
                int currIndex = (i + 1) % n;
                while ((currIndex != stIndex) && count >= 0) {
                    Gas temp = arrayList.get(currIndex);
                    count += (temp.gValue - temp.cost);
                    currIndex = (currIndex + 1) % n;
                }
                if (currIndex == stIndex) {
                   return currIndex;
                }
            }

        }
        if (ans == null)
            return -1;
        for (int i = 0; i < n; i++) {
            if ((ans.gValue == gas[i]) && (ans.cost == cost[i])) {
                return i;
            }
        }
        return -1;


    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());

            int[] gas = new int[n];
            String[] input = br.readLine().split("\\s");

            for (int i = 0; i < n; i++) {
                gas[i] = Integer.parseInt(input[i]);
            }

            int[] cost = new int[n];
            input = br.readLine().split("\\s");

            for (int i = 0; i < n; i++) {
                cost[i] = Integer.parseInt(input[i]);
            }

            System.out.println(Solution.startingPoint(gas, cost, n));

        }

    }
}