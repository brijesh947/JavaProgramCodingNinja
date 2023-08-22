
package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


/*
 * Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
 * Find and print the total weight of Minimum Spanning Tree (MST) using Kruskal's algorithm.
 *
 * Input Format :
 * First line will contain T(number of test case), each test case follows as.
 * Line 1: Two Integers V and E (separated by space)
 * Next E lines : Three integers ei, ej and wi, denoting that there exists an edge between vertex ei and vertex ej with weight wi (separated by space)
 *
 * Output Format :
 * Weight of MST for each test case in new line.
 *
 * Constraints :
 * 1 <= T <= 10
 * 2 <= V, E <= 10^5
 * 1 <= wt <= 10^4
 *
 * */

public class KruskalAlgorithm {


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws NumberFormatException, IOException {

        int t = Integer.parseInt(reader.readLine().trim());

        while (t-- > 0) {
            String[] graph = reader.readLine().split(" ");
            int n = Integer.parseInt(graph[0]);
            int e = Integer.parseInt(graph[1]);
            ArrayList<Edge> edges = new ArrayList<>(e);

            for (int i = 0; i < e; i++) {
                String[] vertex = reader.readLine().split(" ");
                Edge newEdge = new Edge(Integer.parseInt(vertex[0]), Integer.parseInt(vertex[1]), Integer.parseInt(vertex[2]));
                edges.add(i, newEdge);
            }
            Collections.sort(edges);
            int[] parent = new int[n + 1];
            for (int i = 0; i <= n; i++)
                parent[i] = i;
            ArrayList<Edge> temp = kruskal(edges, n, e, parent);
            int sum = 0;
            for (Edge edge : temp) {
                // System.out.print(edge.src + " " + edge.dest + " " + edge.weight + " \n");
                sum += edge.weight;
            }
            System.out.println(sum);
        }

    }

    private static int getParent(int[] parent, int node) {
        while (node != parent[node]) {
            node = parent[node];
        }
        return node;
    }

    private static ArrayList<Edge> kruskal(ArrayList<Edge> edges, int n, int e, int[] parent) {
        int i = 0;
        int count = 0;
        ArrayList<Edge> temp = new ArrayList<>(n - 1);
        while (count < n - 1) {
            Edge edge = edges.get(i);
            int srcParent = getParent(parent, edge.src);
            int destParent = getParent(parent, edge.dest);
            if (srcParent != destParent) {
                temp.add(edge);
                count += 1;
                parent[destParent] = srcParent;
            }
            i += 1;
        }
        return temp;
    }

    static class Edge implements Comparable<Edge> {
        private int src;
        private int dest;
        private int weight;


        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return weight - edge.weight;
        }
    }

}