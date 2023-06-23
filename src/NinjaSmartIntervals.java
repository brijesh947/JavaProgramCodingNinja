import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


/*
 * You are given an array of intervals, where intervals[i] = [start(i), end(i)] and each start(i) is unique.
 * The smart interval for an interval ‘i’ is an interval ‘j’ such that start(j) is greater than or equal to end(i) and start(j) should be minimum.
 * Your task is to return an array of smart interval indices for each interval. If no smart interval exists for an interval ‘i’, then place -1 at index ‘i’ in the output array.
 *
 * Input Format:
 *   The first line contains a single integer T representing the number of test cases.

 * The first line of the test case contains an integer ‘N’ denoting the number of intervals.

 * Each of the following ‘N’ lines contains 2 space-separated integers denoting the start and end of an interval.
 *
 * Output Format :
 *   The first and only line of output contains ‘N’ space-separated integers as described above.

 * The output of every test case is printed in a separate line.
 * Note :
 *   You do not need to print anything; it has already been taken care of. Just implement the given function.
 * Constraints:
 *   1 <= T <= 10
 *   1 <= N <= 10^4
 *   1 <= start, end <= 10^4

 *  Time Limit : 1sec
 * */

class Helper {

    private static int isPossible(MyTriplet[] arr, int n, int k) {
        int s = 0;
        int e = n - 1;
        while (e >= s) {
            int mid = (e + s) / 2;
            if (arr[mid].start >= k) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return s >= n ? -1 : arr[s].index;

    }

    public static ArrayList<Integer> smartInterval(ArrayList<ArrayList<Integer>> intervals, int n) {
        ArrayList<MyTriplet> arr = new ArrayList<>(n);
        int i = 0;
        for (ArrayList<Integer> temp : intervals) {
            arr.add(new MyTriplet(i, temp.get(0), temp.get(1)));
            i++;
        }
        Collections.sort(arr);
        MyTriplet[] fans = new MyTriplet[n];
        int x = 0;
        for (MyTriplet triplet : arr) {
            //System.out.println("index is " + triplet.index + " s= " + triplet.start + " e= " + triplet.end);
            fans[x++] = triplet;
        }


        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> temp : intervals) {
            int currIndex = isPossible(fans, n, temp.get(1));
            ans.add(currIndex);
        }
        return ans;
    }

    static class MyTriplet implements Comparable<MyTriplet> {
        public int index, start, end;

        MyTriplet(int i, int s, int e) {
            index = i;
            start = s;
            end = e;
        }

        @Override
        public int compareTo(MyTriplet prev) {
            return start - prev.start;
        }
    }

}


public class NinjaSmartIntervals {
    private static int t;

    private static ArrayList<ArrayList<ArrayList<Integer>>> arr1;

    private static void takeInput() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine().trim());

        arr1 = new ArrayList<>();

        for (int a = 0; a < t; a++) {
            String line = br.readLine().trim();
            String[] temp = line.trim().split("\\s+");
            // String[] temp = br.readLine().split(" ");
            int n1 = Integer.parseInt(temp[0]);

            ArrayList<ArrayList<Integer>> arr = new ArrayList<>(n1);

            for (int i = 0; i < n1; i++) {
                line = br.readLine().trim();
                temp = line.trim().split("\\s+");
                int a1 = Integer.parseInt(temp[0]);
                int b1 = Integer.parseInt(temp[1]);

                ArrayList<Integer> temp1 = new ArrayList<>(2);
                temp1.add(a1);
                temp1.add(b1);

                arr.add(temp1);

            }

            arr1.add(arr);

        }

    }

    private static void executeAndPrintOutput() {

        for (int i = 0; i < t; i++) {
            ArrayList<Integer> ans = Helper.smartInterval(arr1.get(i), arr1.get(i).size());

            for (int j = 0; j < ans.size(); j++) {
                System.out.print(ans.get(j) + " ");
            }
            System.out.print("\n");

        }

    }

    public static void main(String[] args) throws Exception {
        takeInput();
        executeAndPrintOutput();
        /*
         * int execCount = Integer.parseInt(System.getenv("EXECUTION_COUNT")); if
         * (execCount == 1) { executeAndPrintOutput(); } else { FileWriter writer = new
         * FileWriter(System.getenv("EXEC_COUNTER_FILE")); for (int i = 0; i <
         * execCount; i++) { execute(); writer.write(String.valueOf(i + 1) + "\n");
         * writer.flush(); } writer.close(); }
         */
    }

}


