package disjointsetunion;

import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


/*
 * Ninja has built his team of ninjas to fight with the enemies in his city. Ninja made a plan of attacking all his enemies.
 * In his team, every ninja has his own range of hitting and they had secretly got the hitting range of their enemies as well.
 * So Ninja allowed some swaps between his ninjas so that they can minimize the hamming distance that is the number of positions where the hitting range of enemies and ninjas are different.
 * Your task is to write a code that can minimize the hamming distance.
 * You are being provided with two arrays ‘ninja’ and ‘enemies’ both of the same size
 * And an array ‘allowedSwaps’ where each allowedSwaps[i] = [ ai, bi ] indicates that you are allowed to swap the elements at index ai and index bi.
 * The Hamming distance of two arrays of the same length, ninja, and enemies, is the number of positions where the elements are different.
 * Example :
 * Consider the case ‘ninja’array is [ 1, 2, 3, 4 ], ‘enemies’array is [ 2, 1, 4, 5 ] and ‘allowedSwaps’ are  = [ [ 0, 1 ], [ 2, 3 ] ] so after swapping in best manner according to ‘allowedSwaps’ our ‘ninja’ array becomes [ 2, 1, 4, 3 ].
 * So minimum Hamming distance is ‘1’ as now there is only one different element as compared to ‘ninja’ and ‘enemies’ array index.
 *
 * Note :
 * 1. You are allowed to do as many swap operations on the ‘ninja’ array as you want but according to the ‘allowedSwap’ array.
 * 2. You are not required to print anything explicitly. It has already been taken care of. Just implement the function.
 *
 * Input Format :
 * The first line of input contains a ‘T’ number of test cases.

 * The first line of each test case contains an integer ‘n’, which represents the size of the array ‘ninja’ and ‘enemies’.

 * The second line of each test case contains the ‘n’ space-separated integer of array ‘ninja’.

 * The third line of each test case contains the ‘n’ space-separated integer of array ‘enemies’.

 * The fourth line of each test case contains an integer ‘m’ denoting the number of rows in array ‘allowedSwap’. Then, ‘m’ lines follow.

 * Each line contains two space-separated integers denoting the array values.
 *
 * Output Format :
 * For each test case, return the minimum hamming distance of the ‘ninja’ and ‘enemies’ array.
 *
 * Constraints :
 * 1 <= T <= 100
 * 1 <= N <=  10^3
 * 0 <= ninja[i], enemies[i] < 10^5
 * 0 <= allowedSwaps[i] <=10^5

 * Where ‘T’ represents the number of test cases, ‘N’ represents the size of the ‘ninja’ and ‘enemies’ array and ninja[i], enemies[i], and allowedSwaps[i] represents the element in the array.

 * Time Limit: 1 second
 * */


class NinjaHelper {

    public static int ninjaAttack(ArrayList<Integer> ninja, ArrayList<Integer> enemies, ArrayList<ArrayList<Integer>> allowedSwaps) {
        DSUHelper dsuHelper = new DSUHelper(ninja.size());
        int n = ninja.size();
        for (ArrayList<Integer> temp : allowedSwaps) {
            int u = temp.get(0);
            int v = temp.get(1);
            dsuHelper.combine(u, v);
        }
        //  dsuHelper.printParent();
        ArrayList<Integer>[] list = new ArrayList[ninja.size()];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        /*
         * Here we are grouping the index that are connected to each other
         * suppose 0-1, 2-1 is added in allowed swaps then (0,1,2) belongs to same
         * set it means that index at 0,1,2 can be swaped easily.
         * now after grouping we'll go through all the groups and compare the ninja element to enemies element
         * if enemies element is not present in ninja element then only that element  will be considered in answer
         * and if ninja element is present in enemies list then it's position can be swapped accordingly and will not count in the answer
         * or one more case if freq(n) in ninja is different in freq(n) in enemies then this case will also need to be considered
         * that's why while calculating the answer we have used frequency map.
         * */


        for (int i = 0; i < n; i++) {
            list[dsuHelper.findParent(i)].add(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!list[i].isEmpty()) {
                ArrayList<Integer> temp = list[i];
                ArrayList<Integer> ninjaList = getNinjaList(ninja, list[i]);
                ArrayList<Integer> enemyList = getNinjaList(enemies, list[i]);
                ans += calculateAns(ninjaList, enemyList);
            }

        }
        return ans;
    }

    private static ArrayList<Integer> getNinjaList(ArrayList<Integer> ninja, ArrayList<Integer> indexes) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer index : indexes) {
            ans.add(ninja.get(index));
        }
        return ans;
    }

    private static int calculateAns(ArrayList<Integer> ninja, ArrayList<Integer> enemies) {
        int count = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (Integer number : ninja) {
            if (cnt.get(number) == null) {
                cnt.put(number, 0);
            }
            cnt.put(number, cnt.get(number) + 1);
        }
        for (Integer number : enemies) {
            if (cnt.get(number) == null)
                continue;
            if (cnt.get(number) > 0) {
                cnt.put(number, cnt.get(number) - 1);
                count++;
            }
        }
        return ninja.size() - count;
    }

    private static class DSUHelper {
        private int[] parent;

        DSUHelper(int size) {
            parent = new int[size + 1];
            for (int i = 0; i <= size; i++) {
                parent[i] = i;
            }
        }

        public int findParent(int node) {
            while (node != parent[node]) {
                node = parent[parent[node]];
            }
            return node;
        }

        public boolean combine(int u, int v) {
            int parentU = findParent(u);
            int parentV = findParent(v);
            if (parentV == parentU)
                return false;

            parent[parentU] = parentV;

            return true;
        }

        public void printParent() {
            for (int i = 0; i < parent.length; i++) {
                System.out.print(i + " " + parent[i] + "\n");
            }

        }
    }
}


public class NinjaAttack {

    private static int t;
    private static ArrayList<ArrayList<ArrayList<Integer>>> arr;
    private static ArrayList<ArrayList<Integer>> ninja;
    private static ArrayList<ArrayList<Integer>> enemies;

    private static void takeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine().trim().split(" ")[0]);

        arr = new ArrayList<ArrayList<ArrayList<Integer>>>(t);
        ninja = new ArrayList<ArrayList<Integer>>(t);
        enemies = new ArrayList<ArrayList<Integer>>(t);

        String[] data;

        for (int i = 0; i < t; i++) {
            int n;
            data = br.readLine().trim().split(" ");

            n = Integer.parseInt(data[0]);
            ninja.add(new ArrayList<Integer>(n));
            enemies.add(new ArrayList<Integer>(n));

            data = br.readLine().trim().split(" ");

            for (int j = 0; j < n; j++) {
                ninja.get(i).add(Integer.parseInt(data[j]));
            }

            data = br.readLine().trim().split(" ");
            for (int j = 0; j < n; j++) {
                enemies.get(i).add(Integer.parseInt(data[j]));
            }


            data = br.readLine().trim().split(" ");
            int s = Integer.parseInt(data[0]);

            arr.add(new ArrayList<ArrayList<Integer>>(s));

            for (int j = 0; j < s; j++) {
                arr.get(i).add(new ArrayList<Integer>(2));
                data = br.readLine().trim().split(" ");

                for (int k = 0; k < 2; k++) {
                    arr.get(i).get(j).add(Integer.parseInt(data[k]));
                }
            }

        }
    }

    private static void execute() {
        ArrayList<ArrayList<ArrayList<Integer>>> arrCopy = new ArrayList<ArrayList<ArrayList<Integer>>>(arr);
        ArrayList<ArrayList<Integer>> ninjaCopy = new ArrayList<ArrayList<Integer>>(ninja);
        ArrayList<ArrayList<Integer>> enemiesCopy = new ArrayList<ArrayList<Integer>>(enemies);
        for (int i = 0; i < t; i++) {
            int ans = NinjaHelper.ninjaAttack(ninjaCopy.get(i), enemiesCopy.get(i), arrCopy.get(i));
        }
    }

    private static void executeAndPrintOutput() {
        for (int i = 0; i < t; i++) {

            int ans = NinjaHelper.ninjaAttack(ninja.get(i), enemies.get(i), arr.get(i));

            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        takeInput();
        executeAndPrintOutput();
    }
}