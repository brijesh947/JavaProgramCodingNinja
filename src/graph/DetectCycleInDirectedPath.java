package graph;

import java.io.*;
import java.util.*;


/*
 * Given a directed graph, check whether the graph contains a cycle or not.
 * Your function should return true if the given graph contains at least one cycle, else return false.
 *
 * Input Format:
 * The first line of input contains an integer T, the number of test cases.
 *
 * The first line of each test case contains two single space-separated integers ‘V’, and ‘E’.
 * ‘V’ represents the number of nodes and ‘E’ represents the number of edges in the graph.
 *
 * From the second line onwards of each test case,
 * the next 'E' lines will denote the edges of the graph where every edge is defined by two single space-separated integers 'A' and 'B',
 * which signifies an edge from vertex 'A’ to vertex 'B'.
 *
 * Output Format:
 * For each test case print "true" if a cycle exists, else "false".
 *
 * Constraints:
 * 1 <= T <= 10
 * 1 <= V <= 10^3
 * 0 <= E <= 10^3
 * 0 <= A, B < V

 * Time Limit: 1 sec
 * */


class Solution {


    /*
    * logic written in textbook
    * */
    private static boolean helper(int[][] edge, int v, int node, int[] vis) {

        if (vis[node] == 1)
            return true;

        if (vis[node] == 2)
            return false;

        vis[node] = 1;
        for (int j = 0; j < v; j++) {
            if (edge[node][j] == 1) {
                boolean flag = helper(edge, v, j, vis);
                if (flag)
                    return true;
            }
        }

        vis[node] = 2;
        return false;

    }

    public static Boolean isCyclic(int[][] edges, int v, int e) {
        int[] vis = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            vis[i] = 0;
        }

        int[][] edge = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                edge[i][j] = 0;
            }
        }
        for (int i = 0; i < e; i++) {
            int u = edges[i][0];
            int v1 = edges[i][1];
            if (u != v1)
                edge[u][v1] = 1;
            else
                return true;
        }

        boolean ans = helper(edge, v, 0, vis);

        /*
         * this is the case when graph is disconnected.
         * */
        while (ans) {
            boolean flag = false;
            for (int i = 0; i < v; i++) {
                if (vis[i] == 0) {
                    flag = true;
                    ans |= helper(edge, v, i, vis);
                    if (ans)
                        break;
                }
            }
            if (!flag)
                break;
        }
        return ans;
    }
}

public class DetectCycleInDirectedPath {
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

    private static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    private static FastReader sc = new FastReader();

    private static int t;
    private static int[][][] edges;
    private static int[] V;
    private static int[] E;

    private static void takeInput() {
        t = sc.nextInt();
        edges = new int[t][][];
        V = new int[t];
        E = new int[t];

        for (int i = 0; i < t; i++) {
            V[i] = sc.nextInt();
            int n = sc.nextInt();
            E[i] = n;
            edges[i] = new int[n][2];

            for (int j = 0; j < n; j++) {
                edges[i][j][0] = sc.nextInt();
                edges[i][j][1] = sc.nextInt();
            }
        }
    }


    private static void execute() {
        for (int i = 0; i < t; ++i) {
            Boolean ans = Solution.isCyclic(edges[i], V[i], E[i]);
        }
    }

    private static void executeAndPrintOutput() {
        for (int i = 0; i < t; ++i) {
            Boolean ans = Solution.isCyclic(edges[i], V[i], E[i]);
            if (ans) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

    public static void main(String[] args) {
        takeInput();
        executeAndPrintOutput();
        out.close();
    }
}