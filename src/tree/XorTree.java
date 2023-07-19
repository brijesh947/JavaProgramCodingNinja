package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class XorTree {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX = 100005;

    static ArrayList<Integer>[] tree = new ArrayList[MAX];

    static int[] subTreeXor = new int[MAX];


    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            String[] num = reader.readLine().split(" ");
            int n = Integer.parseInt(num[0]);
            int q = Integer.parseInt(num[1]);

            for (int i = 0; i < MAX; i++) {
                if(tree[i]!=null)
                  tree[i].clear();
                tree[i] = new ArrayList<>();
                subTreeXor[i] = 0;
            }
            for (int i = 0; i < n - 1; i++) {
                String[] edge = reader.readLine().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                tree[u].add(v);
                tree[v].add(u);
            }

            dfs(0, 0);

            String[] query = reader.readLine().split(" ");
            for (int i = 0; i < q; i++) {
                int q1 = Integer.parseInt(query[i]);
                System.out.print(subTreeXor[q1]+ " ");
            }
            System.out.print("\n");

        }

    }

    private static void dfs(int s, int p) {
        subTreeXor[s] = s;
        for (Integer children : tree[s]) {
            if (children != p) {
                dfs(children, s);
                subTreeXor[s] ^= subTreeXor[children];
            }
        }
    }


}
