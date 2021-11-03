import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

class BinaryTree {
	int val;
	BinaryTree leftchild;
	BinaryTree rightchild;

	public BinaryTree(int val) {
		this.val   = val;
		leftchild  = null;
		rightchild = null;
	}
}
public class BinarySearchTree {
	public static int[] nums = {5, 3, 7, 2, 4, 6, 8};
	public static BinaryTree root = null;
	public static void main(String[] args) {
		root = createBinaryTree(nums, 0);
		System.out.println(levelOrder(root));
	}
	public static boolean Verification() {

		return true;
	}
	public static BinaryTree createBinaryTree(int[] nums, int index) {
		BinaryTree BinaryTree = null;
		if (index < nums.length) {
			BinaryTree = new BinaryTree(nums[index]);
			BinaryTree.leftchild	= createBinaryTree(nums, index * 2 + 1);
			BinaryTree.rightchild = createBinaryTree(nums, index * 2 + 2);
		}
		return BinaryTree;
	}
	public static List<List<Integer>> levelOrder(BinaryTree root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (root != null) {
			LinkedList<BinaryTree> BinaryTree = new LinkedList<BinaryTree>();

			BinaryTree.add(root);
			while (!BinaryTree.isEmpty()) {
				int count = BinaryTree.size();
				List<Integer> list = new ArrayList<Integer>();
				while (count > 0) {
					root = BinaryTree.poll();
					list.add(root.val);
					if (root.leftchild != null)
						BinaryTree.add(root.leftchild);
					if (root.rightchild != null)
						BinaryTree.add(root.rightchild);
					count--;
				}
				result.add(list);
			}
		}
		return result;
	}
}