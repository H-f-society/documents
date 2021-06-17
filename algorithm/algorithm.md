# [Home](../README.md)
## [数据结构与算法](https://www.cs.usfca.edu/~galles/visualization/)

### 树
#### 二叉数
``` java
import java.util.*;

public class BinaryTree {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	public static void main(String[] args) {
		BinaryTree binTree = new BinaryTree();
		int[] nums = {1, 2, 3, 4, 5, 6, 7};
		TreeNode root = binTree.createTree(nums, 0);
		System.out.println(binTree.levelOrder(root));

		List<List<Integer>> result = new ArrayList<>();
		binTree.getAllPath(root, new ArrayList<>(), result);
		System.out.println(result);
	}
	// 创建二叉树
	public TreeNode createTree(int[] nums, int index) {
		TreeNode node = null;
		if (index < nums.length) {
			node = new TreeNode(nums[index]);
			node.left  = createTree(nums, index * 2 + 1);
			node.right = createTree(nums, index * 2 + 2);
		}
		return node;
	}
	// 二叉树的层次遍历
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		LinkedList<TreeNode> que = new LinkedList<>();
		que.offer(root);
		while (!que.isEmpty()) {
			int size = que.size();
			List<Integer> list = new ArrayList<>();
			while (size > 0) {
				root = que.poll();
				list.add(root.val);
				if (root.left != null)
					que.offer(root.left);
				if (root.right != null)
					que.offer(root.right);
				size--;
			}
			result.add(list);
		}
		return result;
	}
	// 二叉树的全路径
	public void getAllPath(TreeNode root, List<Integer> list, List<List<Integer>> result) {
		if (root == null) return;
		list.add(root.val);
		if (root.left == null && root.right == null)
			result.add(new ArrayList<>(list));
		getAllPath(root.left, list, result);
		getAllPath(root.right, list, result);
		list.remove(list.size() - 1);
	}
}
```
#### 二叉搜索数
#### B数
#### 红黑树

### 链表
#### 单链表
#### 双链表
#### 循环链表

### 栈

### 队列

### 堆
#### 大顶堆
#### 小顶堆

### 图
#### 深度优先搜索
#### 广度优先搜索
#### 最短路径
##### Dijkstra 算法
##### Floyd 算法
##### AStar 算法
```java
import java.util.*;

public class AStar {
	class Pos {
		int[] ps;
		int f, g, h;
		Pos parent;
		public Pos(int[] ps) {
			this.ps = ps;
		}
	}
	public static int[] start = {0, 0};
	public static int[] end = {4, 4};
	public static int[][] dires = {{ -1, 0}, {1, 0}, {0, -1}, {0, 1}, { -1, -1}, { -1, 1}, {1, 1}, {1, -1}};
	private static final int STRAIGHT_COST = 10;
	private static final int OBLIQUE_COST = 14;
	public static int[][] map = { {0, 0, 0, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 1}, {1, 0, 0, 0, 0}, {0, 0, 1, 0, 0} };
	public static void main(String[] args) {
		AStar astar = new AStar();

		List<Pos> result = astar.searchPath(start, end);
		System.out.println(result.size());
		for (int i = result.size() - 1; i >= 0; i--) {
			System.out.print("[" + result.get(i).ps[0] + ", " + result.get(i).ps[1] + "]-->");
			map[result.get(i).ps[0]][result.get(i).ps[1]] = 5;
		}
		printMap(map);
	}
	public List<Pos> searchPath(int[] start, int[] end) {
		List<Pos> result = new ArrayList<>();
		HashSet<String> hash = new HashSet<>();
		LinkedList<Pos> que = new LinkedList<>();
		que.offer(new Pos(start));
		hash.add(start[0] + "," + start[1]);
		while (!que.isEmpty()) {
			Pos point = que.pop();
			if (point.ps[0] == end[0] && point.ps[1] == end[1]) {
				do {
					result.add(point);
					point = point.parent;
				} while (point.ps[0] != start[0] || point.ps[1] != start[1]);
				result.add(point);
				return result;
			}
			for (int i = 0; i < dires.length; i++) {
				int x = point.ps[0] + dires[i][0];
				int y = point.ps[1] + dires[i][1];
				Pos pos = new Pos(new int[] {x, y});
				if (yuejie(x, y) && map[x][y] == 0 && !hash.contains(x + "," + y)) {
					pos.h = Math.abs(pos.ps[0] - end[0]) * Math.abs(pos.ps[1] - end[1]);
					pos.g = point.g + ((point.ps[0] - pos.ps[0]) == (point.ps[1] - pos.ps[1])  ? STRAIGHT_COST : OBLIQUE_COST);
					pos.f = pos.g + pos.h;
					pos.parent = point;
					que.offer(pos);
					hash.add(x + "," + y);
				}
			}
			for (int i = 0; i < que.size(); i++) {
				if (que.get(i).f < que.get(0).f) {
					Pos tmp = que.get(i);
					que.set(i, que.get(0));
					que.set(0, tmp);
				}
			}
		}
		return result;
	}
	public static boolean yuejie(int x, int y) {
		if (x >= 0 && x < map.length && y >= 0 && y < map[0].length)
			return true;
		return false;
	}
	public static void printMap(int[][] nums) {
		System.out.println();
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				System.out.print(nums[i][j] + " ");
			}
			System.out.println();
		}
	}
}
```
### 排序算法
#### 冒泡排序
#### 选择排序
#### 插入排序
#### 希尔排序
#### 堆排序
#### 桶排序
#### 睡眠排序（搞笑）
#### 计数排序
#### 基数排序
#### 归并排序
#### 快速排序