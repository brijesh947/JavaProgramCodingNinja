package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


/*
* You are given a tree with N vertex and N - 1 edge and you are supposed to find the diameter of the tree.
* Diameter of a tree is defined as the maximum distance between any pair of leaves of a tree.
*
* Input Format:
* The first line of input will contain T(number of test case), each test case follows as.
* Line1: contain an integer N denoting the number of a vertex in the tree.
* Next, N - 1 line contains two space-separated integers u and v denoting the edge between the vertex u and v.
*
* Output Format:
* For each test case print the diameter of the tree in a new line.
*
* Constraints:
* 1 <= T <= 100
* 2 <= N <= 10^4
* 1 <= u, v <= N
* */
public class TreeDiameter {


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());

        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            ArrayList<Integer>[] tree = new ArrayList[n + 1];
            boolean[] vis = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                tree[i] = new ArrayList<>();
                vis[i] = false;
            }
            for (int i = 0; i < n - 1; i++) {
                String[] edges = reader.readLine().split(" ");
                int u = Integer.parseInt(edges[0]);
                int v = Integer.parseInt(edges[1]);
                tree[u].add(v);
                tree[v].add(u);
            }
//            for (int i = 1; i <= n; i++) {
//                for (Integer child : tree[i]) {
//                    System.out.print(child + " ");
//                }
//                System.out.print("\n");
//            }
            int[] maxHeight = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                maxHeight[i] = 0;
            }
            dfs(1, tree, vis, maxHeight);
            int ans = -1;
            for (int i = 1; i <= n; i++) {
                if (maxHeight[i] >= ans)
                    ans = maxHeight[i];
            }
            System.out.println(ans);


        }

    }

    private static int dfs(int node, ArrayList<Integer>[] tree, boolean[] vis, int[] maxHeight) {
        if (tree[node].size() == 0)
            return 0;
        ArrayList<Integer> height = new ArrayList<>();
        vis[node] = true;

        for (Integer child : tree[node]) {
            if (!vis[child])
                height.add(1 + dfs(child, tree, vis, maxHeight));
        }
        int max_height_1, max_height_2;
        max_height_2 = 0;
        max_height_1 = 0;
        Collections.sort(height, Collections.reverseOrder());
        if (height.size() >= 2) {
            max_height_1 = height.get(0);
            max_height_2 = height.get(1);
            maxHeight[node] = max_height_1 + max_height_2;
        } else if (height.size() == 1) {
            max_height_1 = height.get(0);
        }

        //System.out.println("node is " + node + " height_1 is " + max_height_1 + "height_2 is " + max_height_2);
        return max_height_1;
    }
}
