package tree;

/**
 * @Author: root
 * @Date: 2022/4/3 19:45
 * @Description: 自平衡二叉树 / AVL - Tree
 */
public class AvlTree<T extends Comparable<T>> {

    private static final int MAX_HEIGHT_DIFFERENCE = 1;

    static class TreeNode<T> {
        T val;
        int height;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T val, int height) {
            this.val = val;
            this.height = height;
        }
    }

    /**
     * 新增树节点
     */
    public TreeNode<T> add(TreeNode<T> root, T val) {
        if (root == null) {
            return new TreeNode<>(val, 1);
        }
        if (compareTo(root.val, val)) {
            root.left = add(root.left, val);
        } else {
            root.right = add(root.right, val);
        }
        root.height = updateHeight(root);
        return rotate(root);
    }

    /**
     * 对树节点进行旋转，根据左右节点高度差判定应该采用哪种旋转方式
     * @param root 树节点
     * @return 旋转后树节点
     */
    public TreeNode<T> rotate(TreeNode<T> root) {
        if (isBalance(root)) {
            return root;
        }
        int height = getHeight(root.left) - getHeight(root.right);
        if (height > 0) {
            if (getHeight(root.left) == getHeight(root.left.left) + 1) {
                root = rotateLL(root);
            } else {
                root = rotateLR(root);
            }
        } else {
            if (getHeight(root.right) == getHeight(root.right.right) + 1) {
                root = rotateRR(root);
            } else {
                root = rotateRL(root);
            }
        }

        return root;
    }

    /**
     * 左旋
     */
    public TreeNode<T> rotateLL(TreeNode<T> root) {
        TreeNode<T> head = root.left;
        root.left = head.right;
        head.right = root;

        root.height = updateHeight(root);
        head.height = updateHeight(head);
        return head;
    }

    /**
     * 右旋
     */
    public TreeNode<T> rotateRR(TreeNode<T> root) {
        TreeNode<T> head = root.right;
        root.right = head.left;
        head.left = root;

        root.height = updateHeight(root);
        head.height = updateHeight(head);
        return head;
    }

    /**
     * 先右旋再左旋
     */
    public TreeNode<T> rotateLR(TreeNode<T> root) {
        root.left = rotateRR(root.left);
        return rotateLL(root);
    }

    /**
     * 先左旋再右旋
     */
    public TreeNode<T> rotateRL(TreeNode<T> root) {
        root.right = rotateLL(root.right);
        return rotateRR(root);
    }

    /**
     * 当前节点的子树是否平衡
     */
    public boolean isBalance(TreeNode<T> root) {
        return Math.abs(getHeight(root.left) - getHeight(root.right)) <= MAX_HEIGHT_DIFFERENCE;
    }

    /**
     * 更新当前树节点的高度值
     */
    public int updateHeight(TreeNode<T> root) {
        // 当前节点高度取左右子节点高度最大值 + 1
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * 获取当前树节点高度值
     */
    public int getHeight(TreeNode<T> root) {
        return root == null ? 0 : root.height;
    }

    private boolean compareTo(T x, T y) {
        return x.compareTo(y) > 0;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4, 6, 0, 9, 7, 8, 5};

        AvlTree<Integer> avlTree = new AvlTree<>();
        TreeNode<Integer> root = null;

        for (int num : nums) {
            root = avlTree.add(root, num);
        }

        System.out.println(root);
        System.out.println(avlTree.isBalance(root));

    }
}
