package disjointsetunion;

import java.util.Scanner;


/*
* This program is all about detecting cycle in the given graph.
* */
class DSUHelper {
    private int[] parent;
    private int[] subTrees;

    DSUHelper(int n) {
        parent = new int[n + 1];
        subTrees = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            subTrees[i] = 1;
        }
    }

    int findParent(int node) {
        while (node != parent[node]) {
            //doing path compression
            node = parent[parent[node]];
        }
        return node;
    }

    boolean combine(int node1, int node2) {
        int parent1 = findParent(node1);
        int parent2 = findParent(node2);
        //means they both belong to the same group
        if (parent1 == parent2)
            return false;
        //shifting less no subtree parent to major no of subtrees so that no of operation can be optimized.
        if (subTrees[parent1] > subTrees[parent2]) {
            parent[parent2] = parent1;
            subTrees[parent1] += subTrees[parent2];
        } else {
            parent[parent1] = parent2;
            subTrees[parent2] += subTrees[parent1];
        }

        return true;
    }

}

public class DisjointSetUnion {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            Pair[] edges = new Pair[m];

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                edges[i] = new Pair(u, v);
            }

            DSUHelper dsu = new DSUHelper(n);
            boolean isCycleFound = false;

            for (int i = 0; i < m; i++) {
                int u = edges[i].first;
                int v = edges[i].second;

                if (!dsu.combine(u, v))
                    isCycleFound = true;
            }

            if (isCycleFound)
                System.out.println("YES");
            else
                System.out.println("NO");
        }

    }

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
