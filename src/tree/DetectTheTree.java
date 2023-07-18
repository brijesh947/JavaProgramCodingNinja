package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * you have given a set of vertex you have to tell that whether it is tree or not
 * */
public class DetectTheTree {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final int MAX = 2001;


    public static void main(String[] args) throws NumberFormatException, IOException {

        String[] num = reader.readLine().trim().split(" ");
        int n = Integer.parseInt(num[0]);
        int m = Integer.parseInt(num[1]);
        if (m != (n - 1)) {
            System.out.println("NO");
            return;
        }

        DSU dsu = new DSU();

        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().trim().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            if (!dsu.combine(u, v)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static class DSU {

        int[] parent;
        int[] subtree;


        DSU() {
            parent = new int[MAX];
            subtree = new int[MAX];
            for (int i = 1; i < MAX; i++) {
                parent[i] = i;
                subtree[i] = 1;
            }
        }

        int getParent(int v) {
            while (parent[v] != v) {
                v = parent[parent[v]];
            }
            return v;
        }

        boolean combine(int u, int v) {
            int parent1 = getParent(v);
            int parent2 = getParent(u);
            if (parent1 == parent2)
                return false;
            if (subtree[parent1] > subtree[parent2]) {
                subtree[parent1] += subtree[parent2];
                parent[parent2] = parent1;
            } else {
                subtree[parent2] += subtree[parent1];
                parent[parent1] = parent2;
            }
            return true;
        }


    }


}
