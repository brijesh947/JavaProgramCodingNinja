package rangequery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class DistinctQuery {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int N = (int) (1e5 + 5);
    private static final int M = (int) (1e6 + 5);

    private static int ans = 0;
    private static int B = 0;

    public static void add(int i, int[] freq, int[] arr) {
        freq[arr[i]] += 1;
        if (freq[arr[i]] == 1) {
            ans++;
        }
    }

    public static void remove(int i, int[] freq, int[] arr) {
        freq[arr[i]] -= 1;
        if (freq[arr[i]] == 0) {
            ans--;
        }
    }


    public static void main(String[] args) throws IOException, NumberFormatException {
        int n = Integer.parseInt(reader.readLine().trim());
        int[] arr = new int[N];
        String[] num = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(num[i]);
        }
        B = (int) Math.sqrt(n);
        int[] freq = new int[M];


        int q = Integer.parseInt(reader.readLine().trim());
        ArrayList<Query> queries = new ArrayList<>(q);
        int i = 0;
        for (; i < q; i++) {
            String[] range = reader.readLine().split(" ");
            int beg = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            beg--;
            end--;
            queries.add(new Query(beg, end, B, i));
        }
        Collections.sort(queries);
        int[] fans = new int[q];

        int L = queries.get(0).l, R = queries.get(0).l - 1;
        for (i = 0; i < q; i++) {
            while (R < queries.get(i).r) {
                R++;
                add(R, freq, arr);
            }

            while (R > queries.get(i).r) {
                remove(R, freq, arr);
                R--;

            }

            while (L > queries.get(i).l) {
                L--;
                add(L, freq, arr);
            }

            while (L < queries.get(i).l) {
                remove(L, freq, arr);
                L++;
            }
            fans[queries.get(i).index] = ans;
        }

        for (i = 0; i < q; i++) {
            System.out.println(fans[i]);
        }


    }

    private static class Query implements Comparable<Query> {
        int l, r, blockNo, index;

        Query(int L, int R, int B, int idx) {
            l = L;
            r = R;
            blockNo = L / B;
            index = idx;
        }

        @Override
        public int compareTo(Query b) {
            if (this.blockNo != b.blockNo) return b.blockNo - this.blockNo;
            return b.r - this.r;
        }
    }


}
