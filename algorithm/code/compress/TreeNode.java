package compress;

/**
 * @Author: root
 * @Date: 2022/3/31 11:17
 * @Description: 树
 */
public class TreeNode<T extends Comparable<T>> {

    private int nodeSum;

    private T value;

    private final TreeNode<T> left = null;

    private final TreeNode<T> right = null;

    public TreeNode() {
        this.nodeSum = 0;
    }

}
