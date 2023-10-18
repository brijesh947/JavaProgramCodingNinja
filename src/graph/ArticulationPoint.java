package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArticulationPoint {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static int timer = 0;

    static class Node {
        ArrayList<Integer> child;

        Node() {
            child = new ArrayList<>();
        }
    }


    public static void main(String[] args) throws IOException, NumberFormatException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            int V, E;
            String[] input = reader.readLine().split(" ");
            V = Integer.parseInt(input[0]);
            E = Integer.parseInt(input[1]);
            Node[] graph = new Node[V];
            int[] in = new int[V];
            int[] low = new int[V];
            boolean[] vis = new boolean[V];
            boolean[] count = new boolean[V];
            for (int i = 0; i < V; i++) {
                graph[i] = new Node();
                in[i] = 0;
                low[i] = 0;
                vis[i] = false;
                count[i] = false;
            }
            for (int i = 0; i < E; i++) {
                String[] edge = reader.readLine().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                u--;
                v--;
                graph[u].child.add(v);
                graph[v].child.add(u);
            }


            for (int i = 0; i < V; i++) {
                if (!vis[i])
                    countAPUsingDfs(i, -1, count, graph, V, 0, in, low, vis);
            }

            for (int node = 0; node < V; node++) {
                if (count[node])
                    System.out.print((node + 1) + " ");
            }
            System.out.print("\n");
        }
    }

    private static void countAPUsingDfs(int node, int parent, boolean[] count, Node[] graph, int V, int time, int[] in, int[] low, boolean[] vis) {
        in[node] = low[node] = timer++;
        vis[node] = true;
        int child = 0;
        for (int v : graph[node].child) {
            if (v == parent)
                continue;
            if (vis[v]) {
                low[node] = Math.min(low[node], in[v]);
            } else {
                child++;
                countAPUsingDfs(v, node, count, graph, V, time, in, low, vis);
                low[node] = Math.min(low[node], low[v]);
                if (low[v] >= in[node] && parent != -1) {
                    count[node] = true;
                }
            }
        }
        if (parent == -1 && child > 1) {
            count[node] = true;
        }
    }
}
