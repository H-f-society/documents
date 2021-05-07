# [Home](../README.md)
# [LeetCode](https://leetcode-cn.com/)

## 25. [<font color=red>K个一组翻转链表</font>](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode result = new ListNode(0);
		result.next = head;
		ListNode p = result;
		ListNode e = result;
		while (e.next != null) {
			for (int i = 0; i < k && e != null; i++) e = e.next;
			if (e == null) break;
			ListNode s = p.next;
			ListNode n = e.next;
			e.next = null;
			p.next = flipNode(s);
			s.next = n;
			p = s;
			e = p;
		}
		return result.next;
	}
	public ListNode flipNode(ListNode head) {
		ListNode h = head;
		ListNode next = null;
		ListNode p = null;
		while (h != null) {
			next = h.next;
			h.next = p;
			p = h;
			h = next;
		}
		h = p;
		return h;
	}
}
```
## 32. [<font color=red>最长有效括号</font>](https://leetcode-cn.com/problems/longest-valid-parentheses/)
```java
class Solution {
	public int longestValidParentheses(String s) {
		int[] indexNum = new int[s.length()];
		int k = 0, count = 0, result = 0;
		Stack<Character> stack = new Stack<>();
		Stack<Integer>   index = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')' && !stack.isEmpty() && stack.peek() == '(') {
				indexNum[k++] = i;
				indexNum[k++] = index.pop();
				stack.pop();
			} else {
				stack.push(s.charAt(i));
				index.push(i);
			}
		}
		int[] num = new int[k];
		for (int i = 0; i < k; i++) {
			num[i] = indexNum[i];
		}
		Arrays.sort(num);
		for (int i = 0; i < num.length - 1; i++) {
			if (num[i] + 1 != num[i + 1]) {
				if (count > result) result = count + 1;
				count = 0;
			} else {
				count++;
			}
		}
		if (count > result) result = count + 1;
		return result;
	}
}
```
## 36. [<font color=yellow>有效的数独</font>](https://leetcode-cn.com/problems/valid-sudoku/)
![有效的数独](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714svg.png)

```java
class Solution {
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') continue;
				if (!isTrue(board, i, j, board[i][j]) || !trueIs(board, i, j, board[i][j]))
					return false;
			}
		}
		return true;
	}
	public static boolean isTrue(char[][] board, int x, int y, char num) {
		int X = x, Y = y;
		while (X + 1 < 9) {
			if (board[++X][y] == num) return false;
		}
		while (Y + 1 < 9) {
			if (board[x][++Y] == num) return false;
		}
		return true;
	}
	public static boolean trueIs(char[][] board, int x, int y, char num) {
		int X = (int)(x / 3) * 3, Y = (int)(y / 3) * 3, count = 0;
		for (int i = X; i < X + 3; i++) {
			for (int j = Y; j < Y + 3; j++) {
				if (board[i][j] == num) count++;
			}
		}
		if (count > 1) return false;
		return true;
	}
}
```
## 37. [<font color=red>解数独</font>](https://leetcode-cn.com/problems/sudoku-solver/)
![解数独](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714_solutionsvg.png)

```java
class Solution {
	public void solveSudoku(char[][] board) {
		dfs(board, 0, 0);
	}
	public boolean dfs(char[][] grid, int x, int y) {
		if (y == 9) {
			x++;
			y = 0;
			if (x == 9) return true;
		}
		if (grid[x][y] == '.') {
			for (int num = 1; num <= 9; num++) {
				if (isTrue(grid, new int[] {x, y}, (char)(num + '0'))) {
					grid[x][y] = (char)(num + '0');
					if (dfs(grid, x, y + 1)) return true;
					grid[x][y] = '.';
				}
			}
		} else {
			return dfs(grid, x, y + 1);
		}
		return false;
	}
	public boolean isTrue(char[][] grid, int[] point, char num) {
		for (int i = 0; i < 9; i++) {
			if (i != point[0] && grid[i][point[1]] == num) return false;
			if (i != point[1] && grid[point[0]][i] == num) return false;
		}
		int x = (point[0] / 3) * 3;
		int y = (point[1] / 3) * 3;
		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if ((i != point[0] && j != point[1]) && grid[i][j] == num) return false;
			}
		}
		return true;
	}
}
```
## 42. [<font color=red>接雨水</font>](https://leetcode-cn.com/problems/trapping-rain-water)
![接雨水](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png)

```java
class Solution {
	public int trap(int[] height) {
		if (height == null) return 0;
		LinkedList<Integer> stack = new LinkedList<>();
		int result = 0;
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
				int index = stack.pop();
				while (!stack.isEmpty() && height[stack.peek()] == height[index]) {
					stack.pop();
				}
				if (!stack.isEmpty()) {
					int stackTop = stack.peek();
					result += (Math.min(height[stackTop], height[i]) - height[index]) * (i - stackTop - 1);
				}
			}
			stack.push(i);
		}

		return result;
	}
}

```
## 51. [<font color=red>N皇后</font>](https://leetcode-cn.com/problems/n-queens)
![N皇后](https://assets.leetcode.com/uploads/2020/11/13/queens.jpg)

```java
class Solution {
	private HashSet<Integer> rows    = new HashSet<>();
	private HashSet<Integer> toRight = new HashSet<>();
	private HashSet<Integer> toLeft  = new HashSet<>();

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new LinkedList<>();
		int[] queens = new int[n];
		Arrays.fill(queens, -1);
		dfs(result, queens, n, 0);
		return result;
	}
	public void dfs(List<List<String>> result, int[] queens, int n, int row) {
		if (n == row) {
			result.add(paintBoard(queens, n));
		} else {
			for (int i = 0; i < n; i++) {
				int right = row - i;
				int left = row + i;
				if (rows.contains(i) || toRight.contains(right) || toLeft.contains(left)) {
					continue;
				}
				queens[row] = i;
				rows.add(i);
				toRight.add(right);
				toLeft.add(left);

				dfs(result, queens, n, row + 1);

				queens[row] = -1;
				rows.remove(i);
				toRight.remove(right);
				toLeft.remove(left);
			}
		}
	}
	public List<String> paintBoard(int[] queens, int n) {
		List<String> list = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			char[] row = new char[n];
			Arrays.fill(row, '.');
			row[queens[i]] = 'Q';
			list.add(new String(row));
		}
		return list;
	}

}
```
## 52. [<font color=red>N皇后II</font>](https://leetcode-cn.com/problems/n-queens-ii)
```java
class Solution {
	private HashSet<Integer> rows    = new HashSet<>();
	private HashSet<Integer> toRight = new HashSet<>();
	private HashSet<Integer> toLeft  = new HashSet<>();
	private int result = 0;

	public int totalNQueens(int n) {
		dfs(n, 0);
		return result;
	}
	public void dfs(int n, int row) {
		if (n == row) {
			result++;
		} else {
			for (int i = 0; i < n; i++) {
				int right = row - i;
				int left = row + i;
				if (rows.contains(i) || toRight.contains(right) || toLeft.contains(left)) {
					continue;
				}
				rows.add(i);
				toRight.add(right);
				toLeft.add(left);

				dfs(n, row + 1);

				rows.remove(i);
				toRight.remove(right);
				toLeft.remove(left);
			}
		}
	}
}
```
## 79. [<font color=yellow>单词搜索</font>](https://leetcode-cn.com/problems/word-search)
![单词搜索](https://assets.leetcode.com/uploads/2020/11/04/word2.jpg)

```java
class Solution {
	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (dfs(board, word, i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	private boolean dfs(char[][] map, String word, int x, int y, int index) {
		if (!yuejie(map, x, y) || map[x][y] != word.charAt(index)) {
			return false;
		}
		if (index == word.length() - 1) {
			return true;
		}
		char tmp = map[x][y];
		map[x][y] = '\0';
		boolean result =
		    dfs(map, word, x + 1, y, index + 1) || dfs(map, word, x, y + 1, index + 1) ||
		    dfs(map, word, x - 1, y, index + 1) || dfs(map, word, x, y - 1, index + 1);
		map[x][y] = tmp;
		return result;
	}
	private boolean yuejie(char[][] map, int x, int y) {
		if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
			return true;
		}
		return false;
	}
}

```
## 150. [<font color=yellow>逆波兰表达式求值</font>](https://leetcode-cn.com/problems/evaluate-reverse-polish-notation)
```java
class Solution {
	public int evalRPN(String[] tokens) {
		LinkedList<Integer> stack = new LinkedList<>();
		for (int i = 0; i < tokens.length; i++) {
			if ("+".equals(tokens[i])) {
				stack.add(stack.pollLast() + stack.pollLast());
			} else if ("-".equals(tokens[i])) {
				int num1 = stack.pollLast();
				int num2 = stack.pollLast();
				stack.add(num2 - num1);
			} else if ("*".equals(tokens[i])) {
				stack.add(stack.pollLast() * stack.pollLast());
			} else if ("/".equals(tokens[i])) {
				int num1 = stack.pollLast();
				int num2 = stack.pollLast();
				stack.add(num2 / num1);
			} else {
				stack.add(Integer.valueOf(tokens[i]));
			}
		}
		return stack.get(0);
	}
}
```
## 212. [<font color=red>单词搜索II</font>](https://leetcode-cn.com/problems/word-search-ii)
![单词搜索](https://assets.leetcode.com/uploads/2020/11/07/search1.jpg)

```java
class Solution {
	public List<String> findWords(char[][] board, String[] words) {
		List<String> result = new LinkedList<>();
		HashMap<Character, List<String>> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			if (!map.containsKey(words[i].charAt(0))) {
				List<String> list = new LinkedList<>();
				list.add(words[i]);
				map.put(words[i].charAt(0), list);
			} else {
				List<String> list = map.get(words[i].charAt(0));
				list.add(words[i]);
				map.put(words[i].charAt(0), list);
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (map.containsKey(board[i][j])) {
					for (int k = 0; k < map.get(board[i][j]).size(); k++) {
						if (dfs(board, map.get(board[i][j]).get(k), i, j, 0)) {
							result.add(map.get(board[i][j]).get(k));
							map.get(board[i][j]).remove(k);
							k--;
						}
					}
				}
			}
		}
		return result;
	}
	private boolean dfs(char[][] map, String word, int x, int y, int index) {
		if (!yuejie(map, x, y) || map[x][y] != word.charAt(index)) {
			return false;
		}
		if (index == word.length() - 1) {
			return true;
		}
		char tmp = map[x][y];
		map[x][y] = '\0';
		boolean result =
		    dfs(map, word, x + 1, y, index + 1) || dfs(map, word, x, y + 1, index + 1) ||
		    dfs(map, word, x - 1, y, index + 1) || dfs(map, word, x, y - 1, index + 1);
		map[x][y] = tmp;
		return result;
	}
	private boolean yuejie(char[][] map, int x, int y) {
		if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
			return true;
		}
		return false;
	}
}
```
## 224. [<font color=red>基本计算器</font>](https://leetcode-cn.com/problems/basic-calculator/)
```java
class Solution {
    private LinkedList<String> nums = new LinkedList<>();
	private LinkedList<Character> symbol = new LinkedList<>();
    public int calculate(String s) {
        return create(s);
    }

	public int create(String str) {
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

	public int calculate(LinkedList<String> nums) {
		if (nums.isEmpty()) return 0;
		LinkedList<Integer> stack = new LinkedList<>();
		for (int i = 0; i < nums.size(); i++) {
			if ("+".equals(nums.get(i))) {
				stack.add(stack.pollLast() + stack.pollLast());
			} else if ("-".equals(nums.get(i))) {
				int num1 = stack.pollLast();
				int num2 = stack.pollLast();
				stack.add(num2 - num1);
			} else if ("*".equals(nums.get(i))) {
				stack.add(stack.pollLast() * stack.pollLast());
			} else if ("/".equals(nums.get(i))) {
				int num1 = stack.pollLast();
				int num2 = stack.pollLast();
				stack.add(num2 / num1);
			} else {
				stack.add(Integer.valueOf(nums.get(i)));
			}
		}
		return stack.get(0);
	}

	public boolean isNumber(char ch) {
		if (ch == '.') return true;
		if (ch >= '0' && ch <= '9') return true;
		return false;
	}

	public int priority(char ch) {
		if ('+' == ch) return 0;
		else if ('-' == ch) return 0;
		else if ('*' == ch) return 1;
		else if ('/' == ch) return 1;
		return 0;
	}

	public boolean compare(char ch1, char ch2) {
		if ('(' == ch1 || ')' == ch2) return false;
		return priority(ch1) <= priority(ch2);
	}
}
```
## 233. [<font color=red>数字1的个数</font>](https://leetcode-cn.com/problems/number-of-digit-one)
```java
class Solution {
	public int countDigitOne(int n) {
		int count = 0;
		int num = n;
		long i = 1;
		while (num != 0) {
			if (num % 10 == 0) count += (num / 10) * i;
			if (num % 10 == 1) count += (num / 10) * i + (n % i) + 1;
			if (num % 10 > 1) count += Math.ceil(num / 10.0) * i;
			num /= 10;
			i *= 10;
		}
		return count;
	}
}
```
## 239. [<font color=red>滑动窗口最大值</font>](https://leetcode-cn.com/problems/sliding-window-maximum/)
```java
class Solution {
	public int[] maxSlidingWindow(int[] nums, int k) {
		int index = 0;
		int[] result = new int[nums.length - k + 1];
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < k; i++) {
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 0);
			}
		}
		result[index++] = map.lastKey();
		for (int i = k, j = 0; i < nums.length; i++, j++) {
			if (map.get(nums[j]) >= 1) {
				map.put(nums[j], map.get(nums[j]) - 1);
			} else {
				map.remove(nums[j]);
			}
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 0);
			}
			result[index++] = map.lastKey();
		}

		return result;
	}
}

```
## 289. [<font color=yellow>生命游戏</font>](https://leetcode-cn.com/problems/game-of-life)
```java
class Solution {
	public void gameOfLife(int[][] board) {
		int[][] direction = {{ -1, 0}, {1, 0}, {0, -1}, {0, 1}, { -1, 1}, {1, -1}, { -1, -1}, {1, 1}};
		int len = board.length, len0 = board[0].length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len0; j++) {
				int count = 0;
				for (int k = 0; k < direction.length; k++) {
					if (yuejie(board, i + direction[k][0], j + direction[k][1]) &&
					        (board[i + direction[k][0]][j + direction[k][1]] == 1 ||
					         board[i + direction[k][0]][j + direction[k][1]] == -1)) {
						count++;
					}
				}
				if ((count < 2 || count > 3) && (board[i][j] == 1 || board[i][j] == -1))
					board[i][j] = -1;
				if ((count == 2 || count == 3) && (board[i][j] == 1 || board[i][j] == -1))
					board[i][j] = 1;
				if (count == 3 && (board[i][j] == 0 || board[i][j] == -2))
					board[i][j] = -2;
			}
		}
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len0; j++) {
				if (board[i][j] == -1)
					board[i][j] = 0;
				if (board[i][j] == -2)
					board[i][j] = 1;
			}
		}
	}
	public static boolean yuejie(int[][] nums, int x, int y) {
		if (x >= 0 && x < nums.length && y >= 0 && y < nums[0].length)
			return true;
		return false;
	}
}
```
## 315. [<font color=red>计算右侧小于当前元素的个数</font>](https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self)
```java
class Solution {
	private int[] num;
	private int[] count;

	public List<Integer> countSmaller(int[] nums) {
		List<Integer> result = new LinkedList<>();
		discretization(nums);
		for (int i = nums.length - 1; i >= 0; i--) {
			result.add(0, updateCount(nums[i]));
		}
		return result;
	}

	public int updateCount(int n) {
		int i;
		for ( i = count.length - 1; i >= 0; i--) {
			count[i]++;
			if (num[i] == n) break;
		}
		if (i == 0) return 0;
		return count[i - 1];
	}

	public void discretization(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}
		int index = 0;
		num = new int[set.size()];
		count = new int[set.size()];
		Arrays.fill(count, 0);
		for (int n : set) {
			num[index++] = n;
		}
		Arrays.sort(num);
	}
}
```
## 529. [<font color=yellow>扫雷游戏</font>](https://leetcode-cn.com/problems/minesweeper)
![扫雷游戏](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/minesweeper_example_1.png)

```java
class Solution {
	public static int[][] dires = {{ -1, 0}, {1, 0}, {0, -1}, {0, 1}, { -1, -1}, { -1, 1}, {1, -1}, {1, 1}};
	public char[][] updateBoard(char[][] board, int[] click) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		} else {
			createNum(board, click);
			if (board[click[0]][click[1]] == 'B') {
				int[][] flag = new int[board.length][board[0].length];
				LinkedList<int[]> que = new LinkedList<>();
				que.offer(click);
				flag[click[0]][click[1]] = 1;
				while (!que.isEmpty()) {
					int[] ps = que.poll();
					for (int i = 0; i < dires.length; i++) {
						int x = ps[0] + dires[i][0];
						int y = ps[1] + dires[i][1];
						if (yuejie(board, x, y) && board[x][y] != 'M' && flag[x][y] == 0) {
							createNum(board, new int[] {x, y});
							if (dires[i][0] * dires[i][1] != 0 && board[x][y] == 'B') continue;
							if (board[x][y] == 'B') que.offer(new int[] {x, y});
							flag[x][y] = 1;
						}
					}
				}
			}
		}
		return board;
	}
	public static void createNum(char[][] map, int[] point) {
		int MineCount = 0;
		for (int i = 0; i < dires.length; i++) {
			int x = point[0] + dires[i][0];
			int y = point[1] + dires[i][1];
			if (yuejie(map, x, y) && map[x][y] == 'M') MineCount++;
		}
		map[point[0]][point[1]] = (char)(MineCount + 48);
		if (MineCount == 0) map[point[0]][point[1]] = 'B';
	}
	public static boolean yuejie(char[][] map, int x, int y) {
		if (x >= 0 && x < map.length && y >= 0 && y < map[0].length)
			return true;
		return false;
	}
}
```
## 509. [<font color="green">斐波那契数</font>](https://leetcode-cn.com/problems/fibonacci-number/)
```java
class Solution {
	public int fib(int N) {

		if (N == 0 || N == 1) return N;

		int[] result = new int[N + 1];
		result[0] = 0;
		result[1] = 1;
		for (int i = 2; i <= N; i++) {
			result[i] = result[i - 1] + result[i - 2];
		}
		return result[N];

		// 递归
		// return fib(N-1) + fib(N-2);
	}
}
```
## 1138. [<font color=yellow>字母版上的路径</font>](https://leetcode-cn.com/problems/alphabet-board-path/)
![字母板上的路径](https://assets.leetcode.com/uploads/2019/07/28/azboard.png)

```java
class Solution {
	public String alphabetBoardPath(String target) {
		target = 'a' + target;
		int x1, y1, x2, y2, t1, t2;
		StringBuilder result = new StringBuilder();
		int len = target.length() - 1;
		for (int j = 0; j < len; j++) {
			char ch1 = target.charAt(j);
			char ch2 = target.charAt(j + 1);
			x1 = (ch1 - 97) / 5;
			y1 = (ch1 - 97) % 5;
			x2 = (ch2 - 97) / 5;
			y2 = (ch2 - 97) % 5;

			t1 = x1 - x2;
			t2 = y1 - y2;

			if (t1 > 0) for (int i = 0; i < t1 ; i++) result.append("U");
			if (t2 < 0) for (int i = 0; i < -t2; i++) result.append("R");
			if (t2 > 0) for (int i = 0; i < t2 ; i++) result.append("L");
			if (t1 < 0) for (int i = 0; i < -t1; i++) result.append("D");
			result.append("!");
		}
		return result.toString();
	}
}
```