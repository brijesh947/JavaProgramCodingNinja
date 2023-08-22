package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


/*
 *
 * Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
 * Find and print the total weight of Minimum Spanning Tree (MST) using Prim's algorithm.
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
 *2 <= V, E <= 10^5
 * 1 <= wt <= 10^4
 * */

public class PrimsAlgorithm {


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {

        int t = Integer.parseInt(reader.readLine().trim());

        while (t-- > 0) {
            String[] graph = reader.readLine().split(" ");
            int n = Integer.parseInt(graph[0]);
            int e = Integer.parseInt(graph[1]);
            HashMap<Integer, ArrayList<Edge>> edges = new HashMap<>(e);


            for (int i = 0; i < e; i++) {
                String[] vertex = reader.readLine().split(" ");
                Edge newEdge = new Edge(Integer.parseInt(vertex[0]), Integer.parseInt(vertex[1]), Integer.parseInt(vertex[2]));
                Edge secondEdge = new Edge(Integer.parseInt(vertex[1]), Integer.parseInt(vertex[0]), Integer.parseInt(vertex[2]));
                if (edges.get(Integer.parseInt(vertex[0])) == null) {
                    edges.put(Integer.parseInt(vertex[0]), new ArrayList<>());
                }
                if (edges.get(Integer.parseInt(vertex[1])) == null) {
                    edges.put(Integer.parseInt(vertex[1]), new ArrayList<>());
                }
                edges.get(Integer.parseInt(vertex[0])).add(newEdge);
                edges.get(Integer.parseInt(vertex[1])).add(secondEdge);
            }

            int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                if (i == 0)
                    parent[i] = -1;
                else
                    parent[i] = Integer.MIN_VALUE;
            }
            int[] weight = new int[n];
            for (int i = 0; i < n; i++) {
                if (i == 0)
                    weight[i] = 0;
                else
                    weight[i] = Integer.MAX_VALUE;
            }

            int temp = prims(edges, n, e, parent, weight);

            System.out.println(temp);
        }

    }

    private static int prims(HashMap<Integer, ArrayList<Edge>> edges, int n, int e, int[] parent, int[] weight) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        while (true) {
            int index = getNextEligibleEdge(n, visited, weight);
            if (index == -1)
                break;

            visited[index] = true;
            if (edges.get(index) != null) {
                for (Edge edge : edges.get(index)) {
                    int u = edge.src;
                    int v = edge.dest;
                    int w = edge.weight;
                    int temp = weight[v];
                    if (!visited[v]) {
                        weight[v] = Math.min(weight[v], w);
                        //System.out.print("u is " + u + " v is " + v + " and weight is " + w + " and old  weight is " + temp + "\n");

                        if (temp != weight[v]) {
                            parent[v] = u;
                        }
                    }
                }
            }

        }
        int ans = 0;
        for (int temp : weight) {
            if (temp != Integer.MAX_VALUE)
                ans += temp;
        }
        return ans;
    }

    private static int getNextEligibleEdge(int n, boolean[] visited, int[] weight) {

        int minWeight = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (weight[i] < minWeight && !visited[i]) {
                minWeight = weight[i];
                index = i;
            }
        }
        return index;
    }


    private static class Edge implements Comparable<Edge> {
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
