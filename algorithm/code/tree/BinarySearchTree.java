package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2022/3/29 17:09
 * @Description: 二叉搜索树
 */

public class BinarySearchTree {

    static class BinaryTree {
        int val;
        BinaryTree leftchild;
        BinaryTree rightchild;

        public BinaryTree(int val) {
            this.val   = val;
            leftchild  = null;
            rightchild = null;
        }
    }

    public static int[] nums = {5, 3, 7, 2, 4, 6, 8};
    public static BinaryTree root = null;

    public static void main(String[] args) {
        root = createBinaryTree(nums, 0);
        System.out.println(levelOrder(root));
    }
    public static boolean verification() {

        return true;
    }
    public static BinaryTree createBinaryTree(int[] nums, int index) {
        BinaryTree binaryTree = null;
        if (index < nums.length) {
            binaryTree = new BinaryTree(nums[index]);
            binaryTree.leftchild	= createBinaryTree(nums, index * 2 + 1);
            binaryTree.rightchild = createBinaryTree(nums, index * 2 + 2);
        }
        return binaryTree;
    }
    public static List<List<Integer>> levelOrder(BinaryTree root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root != null) {
            LinkedList<BinaryTree> binaryTree = new LinkedList<>();

            binaryTree.add(root);
            while (!binaryTree.isEmpty()) {
                int count = binaryTree.size();
                List<Integer> list = new ArrayList<>();
                while (count > 0) {
                    root = binaryTree.poll();
                    list.add(root.val);
                    if (root.leftchild != null) {
                        binaryTree.add(root.leftchild);
                    }
                    if (root.rightchild != null) {
                        binaryTree.add(root.rightchild);
                    }
                    count--;
                }
                result.add(list);
            }
        }
        return result;
    }
}
