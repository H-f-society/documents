package tree;

import java.util.*;

/**
 * @Author: root
 * @Date: 2022/4/6 14:25
 * @Description: 二叉树
 */

public class BinaryTree<T extends Comparable<T>> {

    static class TreeNode<T> {
        int val;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binTree = new BinaryTree<>();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        TreeNode<Integer> root = binTree.createTree(nums, 0);
        System.out.println(binTree.levelOrder(root));

        List<List<Integer>> result = new ArrayList<>();
        binTree.getAllPath(root, new ArrayList<>(), result);
        System.out.println(result);
    }

    /**
     * 创建二叉树
     */
    public TreeNode<T> createTree(int[] nums, int index) {
        TreeNode<T> node = null;
        if (index < nums.length) {
            node = new TreeNode(nums[index]);
            node.left = createTree(nums, index * 2 + 1);
            node.right = createTree(nums, index * 2 + 2);
        }
        return node;
    }

    /**
     * 层次遍历
     */
    public List<List<Integer>> levelOrder(TreeNode<T> root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode<T>> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                root = que.poll();
                list.add(root.val);
                if (root.left != null) {
                    que.offer(root.left);
                }
                if (root.right != null) {
                    que.offer(root.right);
                }
                size--;
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 叶子节点的全路径
     */
    public void getAllPath(TreeNode<T> root, List<Integer> list, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left == null && root.right == null) {
            result.add(new ArrayList<>(list));
        }
        getAllPath(root.left, list, result);
        getAllPath(root.right, list, result);
        list.remove(list.size() - 1);
    }
}
