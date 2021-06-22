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
```java
import java.util.ArrayList;

public class LinkList<T> {
	private T val;
	private LinkList next;

	private LinkList<T> head;
	private LinkList<T> tail;
	private int size;

	public LinkList() {}
	public LinkList(T val) {
		this.val = val;
	}

	public void addNode(T val) {
		LinkList<T> node = new LinkList<>(val);
		if (size() == 0) {
			head = node;
			tail = head;
			this.size = 1;
		} else {
			tail.next = node;
			tail = tail.next;
			this.size++;
		}
	}
	public void addNode(Integer index, T val) {
		if (head == null) {
			head = new LinkList<T>(val);
			return;
		}
		LinkList<T> node = new LinkList<>(val);
		node.next = head;
		LinkList<T> h = node;
		while (h.next != null) {
			if (index == 0) {
				LinkList<T> temp = h.next;
				h.next = new LinkList<T>(val);
				h.next.next = temp;
				head = node.next;
				this.size++;
				return;
			}
			h = h.next;
			index--;
		}
	}
	public void delNode(T val) {
		if (head == null) return;
		LinkList<T> node = new LinkList<>(val);
		node.next = head;
		LinkList<T> h = node;
		while (h.next != null) {
			if (val.equals(h.next.val)) {
				h.next = h.next.next;
				head = node.next;
				this.size--;
				return;
			}
			h = h.next;
		}
	}
	public void updateNode(T oldVal, T val) {
		if (head == null) return;
		LinkList<T> h = head;
		while (h != null) {
			if (oldVal.equals(h.val)) {
				h.val = val;
				return;
			}
			h = h.next;
		}
	}
	public Integer indexOf(T val) {
		if (head == null) return -1;
		Integer index = 0;
		LinkList<T> h = head;
		while (h != null) {
			if (val.equals(h.val)) {
				return index;
			}
			h = h.next;
			index++;
		}
		return -1;
	}
	public int size() {
		return this.size;
	}
	public ArrayList<T> toArrayList() {
		ArrayList<T> result = new ArrayList<>();
		if (head == null) return result;
		LinkList<T> h = head;
		for (int i = 0; i < size(); i++) {
			result.add(h.val);
			h = h.next;
		}
		return result;
	}

	public static void main(String[] args) {
		LinkList<Integer> list = new LinkList<>();
		for (int i = 0; i < 5; i++) {
			list.addNode(i);
		}
		list.delNode(1);
		list.updateNode(2, 6);
		list.addNode(3, 8);
		list.addNode(7);
		System.out.println(
		    "size: " + list.size() +
		    "\nlist: " + list.toArrayList() +
		    "\nindexOf: " + list.indexOf(8)
		);
	}

}
```
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
```java
public static void bubbleSort() {
	int[] nums = {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
	for (int i = 0; i < nums.length - 1; i++) {
		for (int j = 0; j < nums.length - 1 - i; j++) {
			if (nums[j] > nums[j + 1]) {
				int temp  = nums[j + 1];
				nums[j + 1] = nums[j];
				nums[j]   = temp;
			}
		}
	}
	printArr(nums, "冒泡排序");
}
```
#### 选择排序
#### 插入排序
#### 希尔排序
#### 堆排序
```java
public class Heap {
	private int[] data;
	private int size;
	private int capacity;

	public Heap(int capacity) {
		this.data = new int[capacity + 1];
		this.size = 0;
		this.capacity = capacity;
	}
	private void insert(int num) {
		if (size == capacity) return;
		data[++size] = num;
		shiftUp(size);
	}
	private void shiftUp(int index) {
		while (index > 1 && data[index] > data[index / 2]) {
			int tmp = data[index];
			data[index] = data[index / 2];
			data[index / 2] = tmp;
			index = index / 2;
		}
	}
	private int delete() {
		if (size == 0) return -1;
		int tmp = data[1];
		data[1] = data[size--];
		shiftDown(1);
		return tmp;
	}
	private void shiftDown(int index) {
		while (2 * index <= size) {
			int i = 2 * index;
			if (i + 1 <= size && data[i] < data[i + 1]) i = i + 1;
			if (data[index] > data[i]) break;
			int tmp = data[index];
			data[index] = data[i];
			data[i] = tmp;
			index = i;
		}
	}

	public static void main(String[] args) {
		int[] nums = {2, 4, 1, 6, 5, 3, 9, 0, 8, 7};
		Heap heap = new Heap(10);
		for (int i = 0; i < nums.length; i++) {
			heap.insert(nums[i]);
		}
		for (int i = nums.length - 1; i >= 0; i--) {
			nums[i] = heap.delete();
		}

		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ", ");
		}
	}
}
```
#### 桶排序
#### 睡眠排序（搞笑）
#### 计数排序
```java
public static void countSort() {
	int[] nums = {6, 7, 4, 9, 6, 1, 5, 3, 0, 1, 2, 9, 6, 8};
	int[] temp = new int[10];
	for (int i = 0; i < nums.length; i++)
		temp[nums[i]]++;
	int k = 0;
	for (int i = 0; i < temp.length; i++) {
		while ((temp[i]--) > 0)
			nums[k++] = i;
	}
	printArr(nums, "计数排序");
}
```
#### 基数排序
```java
public static void RadixSort() {
	int[] nums = {3, 44, 38, 5, 47, 35, 36, 26, 27, 2, 46, 4, 19, 50, 48};
	List<LinkedList<Integer>> list = new ArrayList<>();
	for (int i = 0; i < 10; i++)
		list.add(new LinkedList<>());
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < nums.length; j++) {
			if (i == 0) list.get(nums[j] % 10).offer(nums[j]);
			if (i == 1) list.get(nums[j] / 10).offer(nums[j]);
		}
		int n = 0;
		for (int j = 0; j < 10; j++) {
			//System.out.println(list.get(j));
			while (!list.get(j).isEmpty())
				nums[n++] = list.get(j).poll();
		}
		//System.out.println("------------");
	}
	printArr(nums, "基数排序");
}
```
#### 归并排序
```java
public static void mergeSort(int[] nums, int[] result, int start, int end) {
	if (start >= end) return;
	int mid = ((end - start) >> 1) + start;
	int start1 = start, end1 = mid;
	int start2 = mid + 1, end2 = end;
	mergeSort(nums, result, start1, end1);
	mergeSort(nums, result, start2, end2);
	int i = start;
	while (start1 <= end1 && start2 <= end2)
		result[i++] = nums[start1] < nums[start2] ? nums[start1++] : nums[start2++];
	while (start1 <= end1)
		result[i++] = nums[start1++];
	while (start2 <= end2)
		result[i++] = nums[start2++];
	for (i = start; i <= end; i++)
		nums[i] = result[i];
}
```
#### 快速排序