package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2022/4/3 19:45
 * @Description: B-Tree
 */
public class BTree<T extends Comparable<T>> {

    private static final int MAX_DEGREE = 3;

    private static class TreeNode<T> {
        List<T> val = new ArrayList<>();
        List<TreeNode<T>> childs = new ArrayList<>();

        public TreeNode(T val) {
            this.val.add(val);
        }
    }

    public TreeNode<T> add(TreeNode<T> root, T val) {
        if (root == null) {
            return new TreeNode<>(val);
        }
        if (root.val.size() < MAX_DEGREE - 1) {
            root.val.add(val);
        } else {
            if (root.childs.size() < MAX_DEGREE - 2) {
                root.childs.add(new TreeNode<>(root.val.remove(0)));
                root.childs.add(new TreeNode<>(val));
            } else {
                int mid = getMid(0, root.childs.size() - 1);
                add(searchChilds(root.childs, val, mid), val);
            }
        }
        return root;
    }

    public TreeNode<T> searchChilds(List<TreeNode<T>> childs, T val, int mid) {
        if (childs.size() == 0) {
            return null;
        }
        List<T> node = childs.get(mid).val;
        if (comparaTo(searchNode(node, val, getMid(0, node.size())), val) == 0) {

        }
        return childs.get(0);
    }
    public T searchNode(List<T> node, T val, int mid) {
        if (node == null) {
            return null;
        }
        if (comparaTo(node.get(mid), val) == 0) {
            return node.get(mid);
        }
        if (comparaTo(node.get(mid), val) > 1) {
            searchNode(node, val, getMid(mid, node.size() - 1));
        }
        return node.get(mid);
    }

    public int getMid(int begin, int end) {
        return (begin + end) / 2 + 1;
    }

    private int comparaTo(T x, T y) {
        return Integer.compare(x.compareTo(y), 0);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        BTree<Integer> bTree = new BTree<>();
        TreeNode<Integer> root = null;

        for (int num : nums) {
            root = bTree.add(root, num);
        }

        System.out.println(root);
    }
}
