package other;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2022/4/3 19:51
 * @Description: 解数独
 */
public class Sudoku {
    public static void main(String[] args) {
        int[][] grid = new int[9][9];
        dfs(grid, 0, 0);
        System.out.println(getPoint(grid));
    }

    public static boolean getPoint(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (!isTrue(grid, new int[]{i, j}, grid[i][j])) {
                    System.out.println(grid[i][j] + "[" + i + ", " + j + "]");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean dfs(int[][] grid, int x, int y) {
        if (y == 9) {
            x++;
            y = 0;
            if (x == 9) {
                printGrid(grid);
                return true;
            }
        }
        if (grid[x][y] == 0) {
            for (int num = 1; num <= 9; num++) {
                if (isTrue(grid, new int[]{x, y}, num)) {
                    grid[x][y] = num;
                    if (dfs(grid, x, y + 1)) {
                        return true;
                    }
                    grid[x][y] = 0;
                }
            }
        } else {
            return dfs(grid, x, y + 1);
        }
        return false;
    }

    public static boolean isTrue(int[][] grid, int[] point, int num) {
        for (int i = 0; i < 9; i++) {
            if (i != point[0] && grid[i][point[1]] == num) {
                return false;
            }
            if (i != point[1] && grid[point[0]][i] == num) {
                return false;
            }
        }
        int x = (point[0] / 3) * 3;
        int y = (point[1] / 3) * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if ((i != point[0] && j != point[1]) && grid[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                list.add(grid[i][j]);
            }
            System.out.println(list);
        }
        System.out.println();
    }

}