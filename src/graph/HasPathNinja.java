package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Given an undirected graph G(V, E) and two vertices v1 and v2(as integers).
 * check if there exists any path between them or not. Print true or false.
 * V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
 * E is the number of edges present in graph G.
 *
 * Input Format :
 * First line will contain T(number of test cases), each test case as follow.
 * Line 1: Two Integers V and E (separated by space)
 * Next E lines : Two integers a and b, denoting that there exists an edge between vertex a and vertex b (separated by space)
 * Line (E+2) : Two integers v1 and v2 (separated by space)
 *
 * Output Format :
 * true or false for each test case in a newline.
 *
 * Constraints :
 * 1 <= T <= 10
 * 2 <= V <= 1000
 * 1 <= E <= 1000
 * 0 <= v1, v2 <= V-1
 * */

public class HasPathNinja {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        while (t-- > 0) {
            String[] num = reader.readLine().split(" ");
            int n = Integer.parseInt(num[0]);
            int vertex = Integer.parseInt(num[1]);
            int[][] edge = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    edge[i][j] = 0;
                }
            }

            for (int i = 0; i < vertex; i++) {
                String[] edges = reader.readLine().split(" ");
                int u = Integer.parseInt(edges[0]);
                int v = Integer.parseInt(edges[1]);
                edge[u][v] = 1;
                edge[v][u] = 1;
            }
            String[] edges = reader.readLine().split(" ");
            int u1 = Integer.parseInt(edges[0]);
            int v1 = Integer.parseInt(edges[1]);
            if (edge[u1][v1] == 1) {
                System.out.println(true);
            } else {
                boolean[] visited = new boolean[n];
                for (int i = 0; i < n; i++)
                    visited[i] = false;
                System.out.println(dfs(u1, v1, visited, edge, n));
            }
        }

    }

    private static boolean dfs(int u, int v, boolean[] visited, int[][] edge, int n) {

        boolean flag = false;
        visited[u] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && edge[u][i] == 1 && i == v) {
                return true;
            }
            if (!visited[i] && edge[u][i] == 1)
                flag |= dfs(i, v, visited, edge, n);
        }
        return flag;
    }
}
