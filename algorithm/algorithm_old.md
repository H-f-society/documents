# [Home](../README.md)
## [(visualization)](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html) [(github)](https://github.com/H-f-society/algorithm)

### 树
#### Binary Trees
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
#### Binary Search Trees
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
#### Binary Indexed Tree
```java
/*  
*     树状数组 / 二叉索引数 / binary indexed tree
*---------------------------------------------------------
* 将 [1, 2, 3, 4, 5, 6, 7, 8] 维护成下图所示的树形结构数组
* --------------------------------------------------------
*    len = 8 |                          36                                              
*    len = 4 |             10              
*    len = 2 |      3            11        
*    len = 1 |   1     3     5       7       
* --------------------------------------------------------
*  下标          0  1  2  3  4   5   6   7
* --------------------------------------------------------
*/
/*
       ---------36   
   ----10   |    |
   3   |    11   |
   |   |    |    |
   1   3    5    7

*/

public class BinaryIndexedTree {

    private static int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
    private static int[] bitTree = new int[nums.length];

    public static void main(String[] args) {
        BinaryIndexedTree bit = new BinaryIndexedTree();

        bit.createBitTree(nums);

        bit.print(bitTree);

        bit.updateNodeVal(4, 6);
        bit.updateNodeVal(0, 2);
        bit.updateNodeVal(0, 9);
        System.out.println(bit.sumRange(4, 4));
        bit.updateNodeVal(3, 8);
        System.out.println(bit.sumRange(0, 4));
        bit.updateNodeVal(4, 1);
        System.out.println(bit.sumRange(0, 3));
        System.out.println(bit.sumRange(0, 4));

        bit.print(bitTree);
    }

    /**
     * 构建树状数组
     * Tree[x] 的父节点为 Tree[x + lowBit(i)]
     * 父节点值 = 父节点+ 所有子节点
     */
    public void createBitTree(int[] nums) {
        bitTree = nums.clone();
        for (int i=0; i<nums.length - 1; i++) {
            int tmp = i + lowBit(i + 1);
            if (tmp < bitTree.length) {
                bitTree[tmp] += bitTree[i];
            }
        }
    }

    /**
     * 修改某个节点的值，并向上更新其父节点的值
     * 时间复杂度: O(logn)
     */
    public void updateNodeVal(int index, int val) {
        int tmpVal = nums[index] - val;
        nums[index] = val;
        while (index < bitTree.length) {
            bitTree[index] -= tmpVal;
            index = index + lowBit(index + 1);
        }
    }

    /**
     * 下标 0 ~ index 的和
     * 时间复杂度: O(logn)
     */
    public int sumRange(int right) {
        int sum = 0;
        right++;
        while (right > 0) {
            sum += bitTree[right - 1];
            right -= lowBit(right);
        }
        return sum;
    }

    /**
     * 给定某区间下标，计算该区间内的和
     * 时间复杂度: O(logn)
     */
    public int sumRange(int left, int right) {
        return sumRange(right) - sumRange(left - 1);
    }

    /**
     * lowBit(x)是x的二进制表达式中最低位的1所对应的值
     * -x = x取反 + 1
     */
    public int lowBit(int n) {
        return n & -n;
    }

    public void print(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            System.out.print( i == 0 ? "[ " +nums[i] : ", " + nums[i] + (i == nums.length - 1 ? " ]" : "") );
        }
        System.out.println();
    }
}
```
#### Huffman Tree
```java
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: root
 * @Date: 2022/4/1 10:07
 * @Description: 哈夫曼编码 - huffman - tree / code / decode
 */
public class HuffmanTree {

    static class TreeNode<T extends Comparable<T>> {
        T value;
        int count;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T value, Integer count) {
            this.value = value;
            this.count = count;
        }

        public TreeNode(TreeNode<T> left, TreeNode<T> right) {
            this.count = (left != null ? left.count : 0) + (right != null ? right.count : 0);
            this.left = left;
            this.right = right;
        }

    }

    /**
     * 构建huffman数
     * @param content 字符串文本内容
     * @return huffman树
     */
    public static TreeNode<Character> createHuffmanTree(String content) {
        // 对content内容中每个字符计数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            map.put(ch, map.containsKey(ch) ? map.get(ch) + 1 : 1);
        }

        // 优先队列 - 小顶堆
        PriorityQueue<TreeNode<Character>> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.count)
        );
        for (Character key : map.keySet()) {
            priorityQueue.add(new TreeNode<>(key, map.get(key)));
        }

        // 开始构建huffman编码，每两个为一组
        int index = 0;
        TreeNode<Character> left = null;
        TreeNode<Character> right = null;
        while (priorityQueue.size() > 1) {
            index++;
            left = index == 1 ? priorityQueue.remove() : left;
            right = index == 2 ? priorityQueue.remove() : right;
            if (index == 2) {
                priorityQueue.add(new TreeNode<>(left, right));
                index = 0;
                left = null;
                right = null;
            }
        }
        priorityQueue.add(new TreeNode<>(left, priorityQueue.remove()));
        return priorityQueue.remove();
    }

    /**
     * 遍历huffman tree的叶子结点全路径，向左为0，向右为1
     *
     * @param root huffman树
     * @param map  huffman编码表
     * @param path huffman叶子节点全路径
     */
    public static void huffmanCode(TreeNode<Character> root, Map<Character, String> map, String path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            map.put(root.value, path);
        } else {
            huffmanCode(root.left, map, path + "0");
            huffmanCode(root.right, map, path + "1");
        }
    }

    /**
     * huffman编码解码，还原字符串文本
     *
     * @param root huffman树
     * @param code huffman编码
     * @param result huffman编码解码后文本
     */
    public static void huffmanDecode(TreeNode<Character> tree, TreeNode<Character> root, String code, int index, StringBuilder result) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            result.append(root.value);
            huffmanDecode(tree, tree, code, index, result);
        }
        if (index >= code.length()) return;
        if (code.charAt(index) == '0') {
            huffmanDecode(tree, root.left, code, index + 1, result);
        }
        if (code.charAt(index) == '1') {
            huffmanDecode(tree, root.right, code, index + 1, result);
        }
    }

    public static void main(String[] args) {

        String content = "huffmantree";

        TreeNode<Character> huffmanTree = createHuffmanTree(content);

        Map<Character, String> huffmanCodeMap = new HashMap<>();
        huffmanCode(huffmanTree, huffmanCodeMap, "");

        for (Character code : huffmanCodeMap.keySet()) {
            System.out.print("[" + code + ":" + huffmanCodeMap.get(code) + "], ");
        }
        System.out.println();
        StringBuilder hfcode = new StringBuilder();
        for (int i=0; i<content.length(); i++) {
            hfcode.append(huffmanCodeMap.get(content.charAt(i)));
        }
        System.out.println(hfcode);

        StringBuilder hfdecode = new StringBuilder();
        huffmanDecode(huffmanTree, huffmanTree, hfcode.toString(), 0, hfdecode);
        System.out.println(hfdecode);
    }
}

```
#### Trie Tree
```java
public class TrieTree {
	class TrieNode {
		private boolean isEnd;
		private TrieNode[] next;
		public TrieNode() {
			isEnd = false;
			next = new TrieNode[26];
		}
	}
	private TrieNode root = new TrieNode();
	public static void main(String[] args) {
		TrieTree trieTree = new TrieTree();

		trieTree.insert("helloworld");
		System.out.println(
		    trieTree.search("helloworld") + ", " +
		    trieTree.startsWith("hellow") + ", " +
		    trieTree.search("hello")
		);
	}
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0, len = word.length(); i < len; i++) {
			int ch = word.charAt(i) - 'a';
			if (node.next[ch] == null) {
				node.next[ch] = new TrieNode();
			}
			node = node.next[ch];
		}
		node.isEnd = true;
	}
	public boolean search(String word) {
		TrieNode node = root;
		for (int i = 0, len = word.length(); i < len; i++) {
			int ch = word.charAt(i) - 'a';
			if (node.next[ch] == null)
				return false;
			node = node.next[ch];
		}
		return node.isEnd;
	}
	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (int i = 0, len = prefix.length(); i < len; i++) {
			int ch = prefix.charAt(i) - 'a';
			if (node.next[ch] == null)
				return false;
			node = node.next[ch];
		}
		return true;
	}
}
```
#### AVL Tree
```java
```
#### B-Tree
```java
```
#### B+Tree
```java
```
#### Red Black Tree
```java
```

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
```java
```
#### 循环链表
```java
```
### 栈
#### 单调递增栈
```java
```
#### 单调递减栈
```java
```
### 队列
```java
```
### 堆
#### 优先队列 - 大顶堆 / 小顶堆
```java
import java.util.ArrayList;

/**
 * 优先队列 - 大顶堆 - 堆排序
 */
public class MaxPriorityQueue<T extends Comparable<T>> {

    // 存放堆节点
    private final ArrayList<T> queue;

    public MaxPriorityQueue() {
        this.queue = new ArrayList<>();
    }

    // 当前堆大小
    public int size() {
        return queue.size();
    }

    public String toString() {
        return queue.toString();
    }

    /**
     * 元素添加到堆中，并调整堆序
     * @param val 新节点元素
     */
    public void add(T val) {
        queue.add(val);
        if (size() <= 1) {
            return;
        }
        // 新节点插入队列末尾, 下标为 size() - 1;
        // 左节点的父节点为 (index - 1) / 2, 右节点的父节点为 (index - 2) / 2
        int index = size() - 1;
        int pIndex = (index - (index % 2 != 0 ? 1 : 2)) / 2;
        // 堆末尾节点与父级节点比较，决定是否上移保持堆序
        while (compareTo(val, queue.get(pIndex))) {
            swap(index, pIndex);
            index = pIndex;
            pIndex = (index - (index % 2 != 0 ? 1 : 2)) / 2;
            if (pIndex < 0) {
                break;
            }
        }
    }

    /**
     * 移除堆中最值，并调整堆序
     * @return 堆顶最值
     */
    public T remove() {
        if (size() == 0) {
            return null;
        }
        // 堆顶和最后一个节点交换
        swap(0, size() - 1);
        T result = queue.remove(size() - 1);

        // 堆顶节点向下和子节点比较，决定是否下移保持堆序
        int index = 0;
        while ((index * 2) + 1 < size()) {
            int first;
            int left = (index * 2) + 1;
            int right = (index * 2) + 2;

            if (right < size()) {
                // 如果存在右子节点，取左、右节点最值，与当前节点比较。
                first = compareTo(queue.get(left), queue.get(right)) ? left : right;
                first = compareTo(queue.get(first), queue.get(index)) ? first : index;
            } else {
                // 如果只存在左子节点，与当前节点比较
                first = compareTo(queue.get(left), queue.get(index)) ? left : index;
            }
            // 如果最值为当前节点，则结束堆节点下移
            if (first == index) {
                break;
            }
            swap(index, first);
            index = first;
        }
        return result;
    }

    public boolean compareTo(T x, T y) {
        return x.compareTo(y) > 0;
    }

    private void swap(int x, int y) {
        T temp = queue.get(x);
        queue.set(x, queue.get(y));
        queue.set(y, temp);
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> maxPriorityQueue = new MaxPriorityQueue<>();
        int[] nums = {3, 1, 2, 4, 6, 0, 9, 7, 8, 5};

        for (int num : nums) {
            maxPriorityQueue.add(num);
        }
        System.out.println(maxPriorityQueue);

        while (maxPriorityQueue.size() > 0) {
            System.out.print(maxPriorityQueue.remove() + " ");
        }
        System.out.println();
    }
}

```


### 图
#### 深度优先搜索
```java
```

#### 广度优先搜索
```java
```

#### 最短路径
##### Dijkstra 算法
```java
```

##### Floyd 算法
```java
```

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
![排序算法](../images/算法复杂度.png)

```java
```

#### 冒泡排序
![冒泡排序](../images/bubblesort.gif)

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
![选择排序](../images/selectionsort.gif)

```java
```

#### 插入排序
![插入排序](../images/insertionsort.gif)

```java
```

#### 希尔排序
![希尔排序](../images/shellsort.gif)

```java
```

#### 堆排序
![堆排序](../images/heapsort.gif)

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
![桶排序](../images/bucketsort.png)
#### 睡眠排序（史上最强）
```java
public class SleepSort {
    public static void main(String[] args) {

        int[] nums = new int[]{2, 1, 5, 4, 6, 3, 9, 7, 8};

        for (int num : nums) {
            new Thread(() -> {
                try {
                    Thread.sleep(num * 10);
                    System.out.print(num + ", ");
                } catch (InterruptedException ignored) {}
            }).start();
        }
    }
}
```
#### 计数排序
![计数排序](../images/countingsort.gif)

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
![基数排序](../images/radixsort.gif)

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
![归并排序](../images/mergesort.gif)

```java
import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> {

	public static void main(String[] args) {
		Integer[] nums   = new Integer[] {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
		Integer[] result = new Integer[nums.length];

		MergeSort<Integer> mergeSort = new MergeSort<>();
		mergeSort.merge(nums, result, 0, nums.length - 1);
		System.out.println(Arrays.toString(result));


		String[] strs = new String[] {"Java", "Python", "JavaScript", "C++", "PHP", "C"};
		String[] strRes = new String[strs.length];

		MergeSort<String> strSort = new MergeSort<>();
		strSort.merge(strs, strRes, 0, strs.length - 1);
		System.out.println(Arrays.toString(strRes));

	}

	public void merge(T[] nums, T[] result, int start, int end) {
		if (start >= end) return;

		int mid = ((end - start) / 2) + start;
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		merge(nums, result, start1, end1);
		merge(nums, result, start2, end2);
		int i = start;
		while (start1 <= end1 && start2 <= end2) {
			result[i++] = nums[start1].compareTo(nums[start2]) < 0 ? nums[start1++] : nums[start2++];
		}
		while (start1 <= end1) {
			result[i++] = nums[start1++];
		}
		while (start2 <= end2) {
			result[i++] = nums[start2++];
		}
		for (i = start; i <= end; i++) {
			nums[i] = result[i];
		}
	}
}
```
#### 快速排序
![快速排序](../images/quicksort.gif)

```java
import java.util.Arrays;

public class QuickSort<T extends Comparable<T>> {

	public static void main(String[] args) {
		Integer[] nums = new Integer[] {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
		String[]  strs = new String[] {"Java", "Python", "JavaScript", "C++", "PHP", "C"};

		QuickSort<Integer> numSort = new QuickSort<>();
		numSort.sort(nums, 0, nums.length - 1);

		QuickSort<String> strSort  = new QuickSort<>();
		strSort.sort(strs, 0, strs.length - 1);

		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(strs));
	}
	public void sort(T[] arr, int left, int right) {
		if (arr == null || arr.length == 0) {
			return;
		}
		if (left > right) {
			return;
		}
		// 取中间值pivot
		T pivot = arr[left + (right - left) / 2];
		int i = left;
		int j = right;
		while (i <= j) {
			// 找到左边大于pivot的值
			while (pivot.compareTo(arr[i]) > 0) {
				i++;
			}
			// 找到右边小于pivot的值
			while (pivot.compareTo(arr[j]) < 0) {
				j--;
			}
			//把左右两边找到的值交换
			if (i <= j) {
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		// 对左边子数组做排序处理
		if (j > left) {
			sort(arr, left, j);
		}
		// 对右边子数组做排序处理
		if (i < right) {
			sort(arr, i, right);
		}

	}
	public void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
```

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
### 串的模式匹配
#### KMP
```java
public class KMP {
	public static int[] next;

	public static void main(String[] args) {
		String word = "fuck you";
		String key  = "ck yo";
		next = new int[key.length()];

		getNext(key, next);
		System.out.println(indexOf(word, key));
	}
	public static void getNext(String key, int[] next) {
		int i = 0, j = -1;
		next[0] = -1;
		while (i < key.length() - 1) {
			if (j == -1 || key.charAt(i) == key.charAt(j)) {
				i++;
				j++;
				next[i] = j;
			} else {
				j = next[j];
			}
		}
	}
	public static boolean indexOf(String word, String key) {
		int i = 0, j = 0;
		while (i < word.length() && j < key.length()) {
			if (j == -1 || word.charAt(i) == key.charAt(j)) {
				i++;
				j++;
			} else {
				j = next[j];
			}
			if (j == key.length() - 1 && word.charAt(i) == key.charAt(j)) {
				return true;
			}
		}
		return false;
	}
}
```

#### Boyer-Moore
```java
```