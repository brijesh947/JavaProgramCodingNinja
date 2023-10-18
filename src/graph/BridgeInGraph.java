package graph;

import java.io.IOException;
import java.util.*;


class BridgeInGraphHelper {
    public static List<List<Integer>> findBridges(int[][] edges, int v, int e) {
        int[][] graph = new int[v][v];
        int[] vis = new int[v];
        for (int i = 0; i < v; i++) {
            vis[i] = 0;
            for (int j = 0; j < v; j++) {
                graph[i][j] = 0;
            }
        }
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < 2; j++) {
                int u = edges[i][0];
                int v1 = edges[i][1];
                graph[u][v1] = 1;
                graph[v1][u] = 1;
            }
        }

        int[] in = new int[v];
        int[] low = new int[v];
        for (int i = 0; i < v; i++) {
            in[i] = low[i] = 0;
        }
        int T = 0;
        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (vis[i] == 0)
                dfs(graph, i, -1, in, low, T, ans, vis, v);
        }

        return ans;

    }

    private static void dfs(int[][] graph, int u, int parent, int[] in, int[] low, int T, ArrayList<List<Integer>> ans, int[] vis, int v) {
        in[u] = low[u] = T++;
        vis[u] = 1;
        for (int i = 0; i < v; i++) {
            if (graph[u][i] == 1 && i != parent) {
                if (vis[i] != 1) {
                    dfs(graph, i, u, in, low, T, ans, vis, v);
                    low[u] = Math.min(low[u], low[i]);
                    if (low[i] > in[u]) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(u);
                        temp.add(i);
                        ans.add(temp);
                    }

                } else {
                    low[u] = Math.min(low[u], in[i]);
                }
            }
        }

    }
}


public class BridgeInGraph {

    private static StringBuilder printans = new StringBuilder();

    public static void main(String[] args) throws IOException {

        Scanner inp = new Scanner(System.in);

        int t = Integer.parseInt(inp.nextLine());

        while (t-- > 0) {

            int v = inp.nextInt();

            int e = inp.nextInt();

            int[][] edges = new int[e][2];

            for (int ei = 0; ei < e; ei++) {

                int a = inp.nextInt();
                int b = inp.nextInt();
                edges[ei][0] = a;
                edges[ei][1] = b;

            }

            List<List<Integer>> allBridges = BridgeInGraphHelper.findBridges(edges, v, e);
            for (List<Integer> x : allBridges) {
                int a = x.get(0);
                int b = x.get(1);
                x.set(0, Math.min(a, b));
                x.set(1, Math.max(a, b));
            }

            Collections.sort(allBridges, new Comparator<List<Integer>>() {
                public int compare(List<Integer> a, List<Integer> b) {

                    if (a.get(0).equals(b.get(0))) {
                        return a.get(1) - b.get(1);
                    } else {
                        return a.get(0) - b.get(0);
                    }
                }
            });

            printans.append(allBridges.size() + "\n");

            for (List<Integer> x : allBridges) {
                printans.append(x.get(0) + " " + x.get(1) + "\n");
            }

        }
        System.out.print(printans);

    }

}

