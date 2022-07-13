package other;

import java.util.*;

/**
 * @Author: root
 * @Date: 2022/4/3 19:47
 * @Description: N皇后
 */
public class NQueens {

    private static final Set<Integer> rows    = new HashSet<>();
    private static final Set<Integer> toRight = new HashSet<>();
    private static final Set<Integer> toLeft  = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(solveNQueens(8));
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        dfs(result, queens, n, 0);
        return result;
    }
    public static void dfs(List<List<String>> result, int[] queens, int n, int row) {
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
    public static List<String> paintBoard(int[] queens, int n) {
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
