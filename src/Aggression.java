
import java.io.*;
import java.util.*;

class Aggression {

    private static boolean isPossible(int[] stalls, int k, int dis) {

        int n = stalls.length;
        int prev_dis = stalls[0];
       // System.out.println("calculating for " + dis + " distance");
        k--;
        for (int i = 1; i < n; i++) {
            if ((stalls[i] - prev_dis) >= dis) {
                k--;
                if (k == 0)
                    return true;
                prev_dis = stalls[i];
            }
        }

        return false;

    }

    public static int aggressiveCows(int[] stalls, int k) {
        long e = 100;
        long s = 0;
        int ans = -1;
        Arrays.sort(stalls);
        while (e > s) {
            long mid = (e + s) / 2;
            if(e==(s+1)){
                mid = e;
            }
            if (isPossible(stalls, k, (int) mid)) {
               // System.out.println("ans found " + mid);
                s = mid;
                ans = (int) mid;

            } else {

                e = mid - 1;
              //  System.out.println("ans not found " + e);
            }
        }

        return ans;

    }

}

class Runner {
    public static class FastReader {

        BufferedReader br;
        StringTokenizer root;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (root == null || !root.hasMoreTokens()) {
                try {
                    root = new StringTokenizer(br.readLine());
                } catch (Exception r) {
                    r.printStackTrace();
                }
            }
            return root.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static FastReader sc = new FastReader();

    private static int t;
    private static int[] n;
    private static int[] k;
    private static int[][] stalls;

    private static void takeInput() {
        t = 1;
        n = new int[t];
        k = new int[t];
        stalls = new int[t][];
        for (int i = 0; i < t; ++i) {
            n[i] = sc.nextInt();
            k[i] = sc.nextInt();
            stalls[i] = new int[n[i]];
            for (int j = 0; j < n[i]; j++) {
                stalls[i][j] = sc.nextInt();
            }
        }
    }

    private static void execute() {
        for (int i = 0; i < t; ++i) {
            int[][] stallsCpy = stalls.clone();
            int ans = Aggression.aggressiveCows(stallsCpy[i], k[i]);
        }
    }

    private static void executeAndPrintOutput() {
        for (int i = 0; i < t; ++i) {
            int ans = Aggression.aggressiveCows(stalls[i], k[i]);
            System.out.println(ans);
        }
    }

    public static void main(String[] args) {
        takeInput();
        executeAndPrintOutput();
        out.close();
    }
}
