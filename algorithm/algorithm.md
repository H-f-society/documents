# [Home](../README.md)
## [数据结构与算法](https://www.cs.usfca.edu/~galles/visualization/)

## 最短路径 - A Star 算法
![]()

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