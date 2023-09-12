package trie;

import java.io.IOException;
import java.util.*;


/*
 * Let us see how search engines work.
 * Consider the following simple auto complete feature.
 * When you type some characters in the text bar,
 * the engine automatically gives best matching options among it's database.
 * Your job is simple. Given an incomplete search text, output the best search result.
 * Each entry in engine's database has a priority factor attached to it.
 * We consider a result / search suggestion best if it has maximum weight and completes the given incomplete search query.
 * For each query in the input, print the maximum weight of the string in the database, that completes the given incomplete search string.
 * In case no such string exists, print -1.
 * Note: All the strings in database will be unique.
 *
 * Input Format:
 *  First line contains two integers n and q, which represent number of database entries and number of search queries need to be completed.
 *  Next n lines contain a string s and an integer weight, which are the database entry and it's corresponding priority.
 *  Next q lines follow, each line having a string t, which needs to be completed.
 *
 * Constraints:
 *  1 ≤ n, weight, len(s), len(t) ≤ 10^6
 *  1 ≤ q ≤ 10^5
 *  Total length of all strings in database entries ≤ 10^6
 *  Total length of all query strings ≤ 10^6
 *
 * Output Format:
 * Output q lines, each line containing the maximum possible weight of the match for given query,
 * else -1, in case no valid result is obtained.
 * */
class Entry {
    public String str;
    public int weight;

    public Entry() {
        this.str = new String();
    }
};

class Solution {

    private static class Trie {

        ArrayList<Trie> child;
        int weight;

        Trie() {
            child = new ArrayList<>(26);
            for (int i = 0; i < 26; i++) {
                child.add(i, null);
            }
            weight = 0;
        }
    }

    private static void insert(Trie root, String word, int weight) {
        if (word.isEmpty())
            return;
        int index = word.charAt(0) - 'a';
        Trie curr = root;

        if (curr.child.get(index) == null) {
            Trie temp = new Trie();
            temp.weight = weight;
            curr.child.set(index, temp);
        }
        curr = curr.child.get(index);
        if (curr.weight < weight) {
            curr.weight = weight;
        }
        insert(curr, word.substring(1), weight);
    }

    private static int search(Trie root, String pattern) {
        Trie curr = root;
        int weight = -1;
        while (curr != null && !pattern.isEmpty()) {
            int index = pattern.charAt(0) - 'a';
            Trie temp = curr.child.get(index);
            if (temp == null)
                return -1;

            weight = temp.weight;

            curr = temp;
            pattern = pattern.substring(1);
        }
        return weight;

    }

    static ArrayList<Integer> searchEngine(ArrayList<Entry> database, ArrayList<String> text) {
        Trie root = new Trie();
        for (Entry item : database) {
            insert(root, item.str, item.weight);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (String pattern : text) {
            ans.add(search(root, pattern));
        }
        return ans;
    }
}

public class SearchEngine {
    private
    static int t;
    static ArrayList<ArrayList<Integer>> s = new ArrayList<>();
    static ArrayList<Entry> database = new ArrayList<>();
    static ArrayList<String> text = new ArrayList<>();

    private static void takeInput() throws IOException {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int q = scn.nextInt();

        for (int i = 0; i < n; ++i) {
            String str = scn.next();
            int weight = scn.nextInt();

            Entry e = new Entry();

            e.str = str;
            e.weight = weight;

            database.add(e);
        }

        for (int i = 0; i < q; ++i) {
            String str = scn.next();
            text.add(str);
        }

    }

    private
    static void execute() {

        ArrayList<Entry> databaseCopy = new ArrayList<>(database);
        ArrayList<String> textCopy = new ArrayList<>(text);

        ArrayList<Integer> ans = Solution.searchEngine(databaseCopy, textCopy);
    }

    private
    static void executeAndPrintOutput() {
        ArrayList<Integer> ans = Solution.searchEngine(database, text);

        for (int x : ans) {
            System.out.println(x);
        }
    }

    public static void main(String[] args) throws IOException {
        takeInput();
        executeAndPrintOutput();
    }
}