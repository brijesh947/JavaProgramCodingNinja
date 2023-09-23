package rangequery;

public class SegmentTree {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        int[] tree = new int[arr.length * 2];
        buildTree(arr, tree, 0, 4, 1);
        for (int i = 1; i < 10; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.print("\n");

        updateIndexValue(arr, tree, 0, 4, 0, 6, 1);
        for (int i = 1; i < 10; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.print("\n");
    }

    private static void updateIndexValue(int[] arr, int[] tree, int start, int end, int idx, int value, int treeIndex) {
        if (start == end) {
            arr[idx] = value;
            tree[treeIndex] = value;
            return;
        }
        int mid = (start + end) / 2;
        if (idx > mid) {
            updateIndexValue(arr, tree, mid + 1, end, idx, value, 2 * treeIndex + 1);
        } else {
            updateIndexValue(arr, tree, start, mid, idx, value, 2 * treeIndex);
        }

        tree[treeIndex] = tree[2 * treeIndex] + tree[2 * treeIndex + 1];
    }

    private static void buildTree(int[] arr, int[] tree, int start, int end, int treeIndex) {
        if (start == end) {
            tree[treeIndex] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTree(arr, tree, start, mid, 2 * treeIndex);
        buildTree(arr, tree, mid + 1, end, 2 * treeIndex + 1);
        tree[treeIndex] = tree[2 * treeIndex] + tree[2 * treeIndex + 1];
    }
}
