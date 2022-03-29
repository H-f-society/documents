import java.util.Set;
import java.util.List;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class BFS {
	private static final int[][] dir = { {0, 1}, {1, 0}, {0, -1}, { -1, 0} };
	private static final int[][] map = {
		{1,  2,  3,  4,   5},
		{6,  7,  8,  9,  10},
		{11, 12, 13, 14, 15},
		{16, 17, 18, 19, 20},
		{21, 22, 23, 24, 25},
	};

	public static void main(String[] args) {
		List<Integer> result = new LinkedList<>();
		result = search(map, new int[] {2, 2});
		System.out.println(result);
	}
	public static List<Integer> search(int[][] map, int[] start) {
		List<Integer> rst = new LinkedList<>();
		Deque<int[]> que  = new LinkedList<>();
		Set<String> flag  = new HashSet<>();
		que.add(start);
		rst.add(map[start[0]][start[1]]);
		flag.add(start[0] + "," + start[1]);

		while (!que.isEmpty()) {
			int[] point = que.removeFirst();
			for (int i = 0; i < dir.length; i++) {
				int x = point[0] + dir[i][0];
				int y = point[1] + dir[i][1];
				if (transboundary(map, x, y) && !flag.contains(x + "," + y)) {
					rst.add(map[x][y]);
					que.add(new int[] {x, y});
					flag.add(x + "," + y);
				}
			}
		}
		return rst;
	}
	public static boolean transboundary(int[][] map, int x, int y) {
		if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
			return true;
		}
		return false;
	}
}