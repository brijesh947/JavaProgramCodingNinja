package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/*
 * Given an undirected graph G(V, E) and two vertices v1 and v2(as integers), find and print the path from v1 to v2 (if exists).
 * Print nothing if there is no path between v1 and v2.
 * Find the path using DFS and print the first path that you encountered.
 * V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
 * E is the number of edges present in graph G.
 * Print the path in reverse order. That is, print v2 first, then intermediate vertices and v1 at last.
 * Note : Save the input graph in Adjacency Matrix.
 *
 * Input Format :
 * First line will contain T(number of test case), each test follow as.
 * Line 1: Two Integers V and E (separated by space)
 * Next E lines : Two integers a and b, denoting that there exists an edge between vertex a and vertex b (separated by space)
 * Line (E+2) : Two integers v1 and v2 (separated by space)
 *
 * Output Format :
 * Path from v1 to v2 in reverse order (separated by space) for each test case in newline.
 *
 * Constraints :
 * 1 <= T <= 10
 * 2 <= V <= 1000
 * 1 <= E <= 1000
 * 0 <= v1, v2 <= V-1
 * */

public class GetPathNinja {

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
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++)
                visited[i] = false;
            ArrayList<Integer> path = new ArrayList<>();

            boolean flag = dfs(u1, v1, visited, edge, n, path);

            if (flag) {
                path.add(u1);
                for (Integer temp : path) {
                    System.out.print(temp + " ");
                }
                System.out.print("\n");
            }


        }

    }

    private static boolean dfs(int u, int v, boolean[] visited, int[][] edge, int n, ArrayList<Integer> path) {

        if (u == v)
            return true;
        visited[u] = true;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && edge[u][i] == 1 && u != i) {
                flag = dfs(i, v, visited, edge, n, path);
                if (flag) {
                    path.add(i);
                    break;
                }
            }
        }
        return flag;


    }

}
