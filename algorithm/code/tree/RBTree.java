/**
 * 每个节点要么黑色、要么红色
 * 根节点为黑色
 * 叶子节点（NIL）为黑色
 * 每个红色节点的两个子节点，必须为黑色
 * 每次插入的节点都为红色，插入Z节点时的几种情况 {
 *      情况1：Z是根节点（即Z插入前是一个空树：
 *          将其重新变色为黑色
 *      情况2：Z的叔节点是红色的：
 *          对Z的父节点、祖父节点、叔节点进行便是
 *      情况3：Z的叔节点是黑色的，并且局部呈现直线：
 *          旋转Z的祖父节点
 *          原来的父节点和祖父节点变色
 *      情况4：Z的叔节点是黑色的，并且局部呈现三角形：
 *          旋转Z的父节点，转换成情况3，再通过情况3方法来修复
 * }
 *
 */

public class RBTree<T extends Comparable<T>> {

    private RBTNode<T> root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class RBTNode<T> {
        T key;
        boolean color;
        RBTNode<T> left;
        RBTNode<T> right;
        RBTNode<T> parent;

        public RBTNode(T key, boolean color, RBTNode<T> left, RBTNode<T> right, RBTNode<T> parent) {
            this.key    = key;
            this.color  = color;
            this.left   = left;
            this.right  = right;
            this.parent = parent;
        }
    }

    public void leftRotate(RBTNode<T> x) {
        RBTNode<T> y = x.right;

        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            this.root = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }

        y.left = x;
        x.parent = y;
    }

    public void rightRotate(RBTNode<T> y) {
        RBTNode<T> x = y.left;

        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == null) {
            this.root = x;
        } else {
            if (y.parent.right == y) {
                y.parent.right = x;
            } else {
                y.parent.left = x;
            }
        }

        x.right = y;
        y.parent = x;
    }

    public static void main(String[] args) {
        
    }
}