package other;

/**
 * @Author: root
 * @Date: 2022/4/3 19:54
 * @Description: 生命游戏
 */
public class GameOfLife {

    public static void main(String[] args) {
        gameOfLife(new int[8][8]);
    }

    public static void gameOfLife(int[][] board) {
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
                if ((count < 2 || count > 3) && (board[i][j] == 1 || board[i][j] == -1)) {
                    board[i][j] = -1;
                }
                if ((count == 2 || count == 3) && (board[i][j] == 1 || board[i][j] == -1)) {
                    board[i][j] = 1;
                }
                if (count == 3 && (board[i][j] == 0 || board[i][j] == -2)) {
                    board[i][j] = -2;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len0; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
                if (board[i][j] == -2) {
                    board[i][j] = 1;
                }
            }
        }
    }
    public static boolean yuejie(int[][] nums, int x, int y) {
        return x >= 0 && x < nums.length && y >= 0 && y < nums[0].length;
    }
}
