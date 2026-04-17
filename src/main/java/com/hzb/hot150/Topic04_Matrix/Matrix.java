package com.hzb.hot150.Topic04_Matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    // 36. 有效的数独
    public boolean isValidSudoku(char[][] board) {
        int[][] column = new int[9][9];
        int[][] row = new int[9][9];
        int[][][] sub = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '1';
                    row[i][index]++;
                    column[j][index]++;
                    sub[i / 3][j / 3][index]++;
                    if (row[i][index] > 1 || column[j][index] > 1 || sub[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 54. 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int m = matrix.length; // 行数
        int n = matrix[0].length; // 列数
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (left <= right && top <= bottom) {
            if (left <= right && top <= bottom) {
                for (int i = left; i <= right; i++) {
                    list.add(matrix[top][i]);
                }
                top++;
            }
            if (left <= right && top <= bottom) {
                for (int i = top; i <= bottom; i++) {
                    list.add(matrix[i][right]);
                }
                right--;
            }
            if (left <= right && top <= bottom) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right && top <= bottom) {
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
            }

        }
        return list;
    }

    // 48. 旋转图像
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[m - 1 - i][j];
                matrix[m - 1 - i][j] = temp;
            }
        }
        // 主对角翻转
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // 73. 矩阵置零
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // 289. 生命游戏
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] tmp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int live = 0;
                // 判断该细胞周围八个位置的情况
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k < 0 || k >= m || l < 0 || l >= n || (k == i && l == j)) {
                            continue;
                        }
                        if (board[k][l] == 1) {
                            live++;
                        }
                    }
                }
                if (board[i][j] == 1) {
                    // 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
                    // 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
                    if (live < 2 || live > 3) {
                        tmp[i][j] = 0;
                    } else {
                        // 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
                        tmp[i][j] = 1;
                    }
                } else {
                    // 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
                    if (live == 3) tmp[i][j] = 1;
                }
            }
        }
        // 更新表格
        for (int i = 0; i < m; i++) {
            System.arraycopy(tmp[i], 0, board[i], 0, n);
        }
    }
}
