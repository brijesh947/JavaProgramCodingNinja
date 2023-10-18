package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConnectedHorses {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final int MOD = (int) (1e9 + 7);
    public static final int MAX = (int) (1e6 + 7);

    static int[] x = {2, 2, 1, 1, -1, -1, -2, -2};
    static int[] y = {1, -1, 2, -2, 2, -2, 1, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        long[] fact = new long[MAX];
        fact[0] = 1;

        for (long i = 1; i < MAX; i++) {
            fact[(int) i] = ((fact[(int) i - 1] % MOD) * (i % MOD)) % MOD;
        }
        while (t-- > 0) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int q = Integer.parseInt(input[2]);
            int[][] graph = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    graph[i][j] = 0;
                }
            }

            while (q-- > 0) {
                String[] edge = reader.readLine().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                graph[u][v] = 1;
            }
            boolean[][] visited = new boolean[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    if (graph[i][j] == 0)
                        visited[i][j] = true;
                    else
                        visited[i][j] = false;
                }
            }
            ArrayList<ArrayList<Integer>> components = new ArrayList<>();
            helper(graph, visited, components, n, m);
            long ans = 1;
            for (ArrayList<Integer> component : components) {
                ans = ((ans % MOD) * (fact[component.size()] % MOD)) % MOD;
            }
            System.out.println(ans);

        }
    }


    private static void helper(int[][] graph, boolean[][] visited, ArrayList<ArrayList<Integer>> components, int n, int m) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (!visited[i][j]) {
                    ArrayList<Integer> component = dfsHelper(graph, visited, i, j, n, m, new ArrayList<Integer>());
                    if (component.size() > 1)
                        components.add(component);
                }
            }
        }
    }

    private static ArrayList<Integer> dfsHelper(int[][] graph, boolean[][] visited, int i, int j, int n, int m, ArrayList<Integer> ans) {
        if (i > n || j > m || i < 1 || j < 1 || visited[i][j])
            return ans;

        if (i <= n && j <= m && !visited[i][j])
            ans.add(1);
        visited[i][j] = true;


        for (int k = 0; k < 8; k++) {
            if (check(i + x[k], j + y[k], visited, n, m))
                dfsHelper(graph, visited, i + x[k], j + y[k], n, m, ans);
        }
        return ans;

    }

    private static boolean check(int i, int j, boolean[][] visited, int n, int m) {
        if (i > n || j > m || i < 1 || j < 1 || visited[i][j])
            return false;
        return true;
    }
}
