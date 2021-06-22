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
```java
import java.util.*;
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
		if(index < nums.length) {
			BinaryTree = new BinaryTree(nums[index]);
			BinaryTree.leftchild	= createBinaryTree(nums, index * 2 + 1);
			BinaryTree.rightchild = createBinaryTree(nums, index * 2 + 2);
		}
		return BinaryTree;
	}
	public static List<List<Integer>> levelOrder(BinaryTree root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(root != null) {
            LinkedList<BinaryTree> BinaryTree = new LinkedList<BinaryTree>();
            
            BinaryTree.add(root);
            while(!BinaryTree.isEmpty()) {
                int count = BinaryTree.size();
                List<Integer> list = new ArrayList<Integer>();
                while(count > 0) {
                    root = BinaryTree.poll();
                    list.add(root.val);
                    if(root.leftchild != null)
                        BinaryTree.add(root.leftchild);
                    if(root.rightchild != null)
                        BinaryTree.add(root.rightchild);
                    count--;
                }
                result.add(list);
            }
        }
        return result;
    }
}
```
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
import java.util.*;

public class MergeSort {
	public static void main(String[] args) {
		int[] nums   = {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
		int[] result = new int[nums.length];

		merge(nums, result, 0, nums.length - 1);
		printArr(result);
	}
	public static void merge(int[] nums, int[] result, int start, int end) {
		if (start >= end) return;

		int mid = ((end - start) / 2) + start;
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		merge(nums, result, start1, end1);
		merge(nums, result, start2, end2);
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
	public static void printArr(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) list.add(nums[i]);
		System.out.println(list);
	}
}
```
#### 快速排序

### 逆波兰表达式
```java
/*
* 逆波兰表达式
* @Author: root
* @Date:   2021-05-03 18:49:28
* @Last Modified by:   f-society
* @Last Modified time: 2021-05-06 13:56:48
*/
import java.util.*;

public class ReversePolishNotation {
	private static LinkedList<String> nums = new LinkedList<>();
	private static LinkedList<Character> symbol = new LinkedList<>();

	public static void main(String[] args) {
		float result = create("5 * ((1 + 2) / 4.0) - 3");
		System.out.println(result);
	}
	/**
	 * 将中缀表达式转换为后缀表达式（构建逆波兰表达式）
	 * @DateTime 2021-05-03T19:58:57+0800
	 * @param    str                      [运算表达式]
	 * @return                            [description]
	 */
	public static float create(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (' ' == str.charAt(i)) continue;
			if (isNumber(str.charAt(i))) {
				StringBuilder sb = new StringBuilder();
				sb.append(str.charAt(i) + "");
				while (i < str.length() - 1 && isNumber(str.charAt(i + 1))) {
					sb.append(str.charAt(++i));
				}
				nums.add(sb.toString());
			} else {
				// 判断当前运算符优先级是否低于站内运算符
				while (!symbol.isEmpty() && '(' != symbol.peekLast() && compare(str.charAt(i), symbol.peekLast())) {
					nums.add(symbol.pollLast() + "");
				}
				if (')' != str.charAt(i)) {
					symbol.add(str.charAt(i));
				} else {
					while (!symbol.isEmpty() && '(' != symbol.peekLast()) {
						nums.add(symbol.pollLast() + "");
					}
					if (!symbol.isEmpty() && '(' == symbol.peekLast()) {
						symbol.pollLast();
					}
				}
			}
		}
		while (!symbol.isEmpty()) {
			nums.add(symbol.pollLast() + "");
		}
		System.out.println(nums);
		return calculate(nums);
	}
	/**
	 * 计算逆波兰表达式
	 * @DateTime 2021-05-03T19:59:22+0800
	 * @param    nums                     [逆波兰表达式]
	 * @return                            [description]
	 */
	public static float calculate(LinkedList<String> nums) {
		if (nums.isEmpty()) return 0;
		LinkedList<Float> stack = new LinkedList<>();
		for (int i = 0; i < nums.size(); i++) {
			if ("+".equals(nums.get(i))) {
				stack.add(stack.pollLast() + stack.pollLast());
			} else if ("-".equals(nums.get(i))) {
				float num1 = stack.pollLast();
				float num2 = stack.pollLast();
				stack.add(num2 - num1);
			} else if ("*".equals(nums.get(i))) {
				stack.add(stack.pollLast() * stack.pollLast());
			} else if ("/".equals(nums.get(i))) {
				float num1 = stack.pollLast();
				float num2 = stack.pollLast();
				stack.add(num2 / num1);
			} else {
				stack.add(Float.valueOf(nums.get(i)));
			}
		}
		return stack.get(0);
	}
	/**
	 * 判断字符为有效数字，可能带小数点
	 * @DateTime 2021-05-03T23:53:59+0800
	 * @param    ch                       [字符数字]
	 * @return                            [description]
	 */
	public static boolean isNumber(char ch) {
		if (ch == '.') return true;
		if (ch >= '0' && ch <= '9') return true;
		return false;
	}
	/**
	 * 运算符优先级
	 * @DateTime 2021-05-03T22:55:41+0800
	 * @param    ch                       [运算符]
	 * @return                            [description]
	 */
	public static int priority(char ch) {
		if ('+' == ch) return 0;
		else if ('-' == ch) return 0;
		else if ('*' == ch) return 1;
		else if ('/' == ch) return 1;
		return 0;
	}
	/**
	 * 比较优先级
	 * @DateTime 2021-05-03T23:54:28+0800
	 * @param    ch1                      [当前运算符]
	 * @param    ch2                      [栈内运算符]
	 * @return                            [description]
	 */
	public static boolean compare(char ch1, char ch2) {
		if ('(' == ch1 || ')' == ch2) return false;
		return priority(ch1) <= priority(ch2);
	}
}
```