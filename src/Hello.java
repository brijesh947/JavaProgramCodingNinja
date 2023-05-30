import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class InputArray {
    private long[] arr1;
    private long[] arr2;

    public InputArray(long[] arr1, long[] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
    }

    public long[] getArr1() {
        return this.arr1;
    }

    public long[] getArr2() {
        return this.arr2;
    }
}

class Interval implements Comparable<Interval> {
    long startTime, endTime;

    public Interval(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Interval o) {
        return Long.compare(this.startTime, o.startTime);
    }
}

public class Hello {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            InputArray arr = takeInput();
            System.out.println(Solution.checkOverlappingIntervals(arr.getArr1(), arr.getArr2()));
        }
        br.close();
    }

    public static InputArray takeInput() throws IOException {
        int size = Integer.parseInt(br.readLine().trim());
        long[] input = new long[size];
        long[] input1 = new long[size];

        String[] strNums;
        strNums = br.readLine().split("\\s");


        for (int i = 0; i < size; ++i) {
            input[i] = Long.parseLong(strNums[i]);
        }

        strNums = br.readLine().split("\\s");


        for (int i = 0; i < size; ++i) {
            input1[i] = Long.parseLong(strNums[i]);
        }

        return new InputArray(input, input1);
    }


    private static class Solution {
        public static boolean checkOverlappingIntervals(long[] startTime, long[] endTime) {
            int n = startTime.length;
            ArrayList<Interval> arrayList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arrayList.add(new Interval(startTime[i], endTime[i]));
            }

            Collections.sort(arrayList);
            for (int i = 0; i < n - 1; i++) {
                if (arrayList.get(i).endTime > arrayList.get(i + 1).startTime)
                    return true;
            }


            return false;
        }
    }
}