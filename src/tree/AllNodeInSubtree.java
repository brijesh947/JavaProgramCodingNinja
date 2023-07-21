package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AllNodeInSubtree {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    private static void dfsHelper(int s, int p, int[] ans, ArrayList<Integer>[] tree) {
        ans[s]++;

        for (Integer node : tree[s]) {
            //System.out.print("node is "  + node + "\n");
            if (node != p) {
                dfsHelper(node, s, ans, tree);
                ans[s] += ans[node];
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            ArrayList<Integer>[] tree = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }
            for (int i = 0; i < n - 1; i++) {
                String[] edge = reader.readLine().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                tree[u].add(v);
                tree[v].add(u);
            }

            int[] ans = new int[n + 1];
            for (int i = 0; i <= n; i++)
                ans[i] = 0;

            dfsHelper(0, 0, ans, tree);
            for (int i = 0; i <= n; i++) {
                if (ans[i] != 0) {
                    System.out.print(ans[i] + " ");
                }
            }
            System.out.print("\n");


        }

    }


}
