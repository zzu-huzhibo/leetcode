package com.hzb.hot150.Topic12_Map;

import com.hzb.hot150.dataStructure.Node;

import java.util.HashMap;

public class MapProblem {
    /** -------------------------【图】------------------------- */
    // 200. 岛屿数量
    static class NumIslands {
        public int numIslands(char[][] map) {
            int n = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == '1') {
                        affect(i, j, map);
                        n++;
                    }
                }
            }
            return n;
        }

        private void affect(int i, int j, char[][] map) {
            if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j] != '1') {
                return;
            }
            map[i][j] = '0';
            affect(i - 1, j, map);
            affect(i + 1, j, map);
            affect(i, j - 1, map);
            affect(i, j + 1, map);
        }
    }

    // 130. 被围绕的区域
    static class SurroundedArea {

        /**
         * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：
         * 连接：一个单元格与水平或垂直方向上相邻的单元格连接。
         * 区域：连接所有 'O' 的单元格来形成一个区域。
         * 围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X' 单元格围绕。
         * 通过将输入矩阵 board 中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。
         */
        public void solve(char[][] board) {
            int m = board.length;
            int n = board[0].length;
            // 处理边界上的O
            for (int i = 0; i < m; i++) {
                //  处理第一列
                if (board[i][0] == 'O') {
                    affect(i, 0, board);
                }
                // 处理最后一列
                if (board[i][n - 1] == 'O') {
                    affect(i, n - 1, board);
                }
            }
            for (int i = 0; i < n; i++) {
                // 第一行的边界上有O
                if (board[0][i] == 'O') {
                    affect(0, i, board);
                }
                // 最后一行有O
                if (board[m - 1][i] == 'O') {
                    affect(m - 1, i, board);
                }
            }
            // 更改内部的O为X
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
            // 把变成F的改回去
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'F') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        // 把边界上所有为O的改为F
        private void affect(int i, int j, char[][] board) {
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'X') {
                return;
            }
            board[i][j] = 'F';
            affect(i - 1, j, board);
            affect(i + 1, j, board);
            affect(i, j - 1, board);
            affect(i, j + 1, board);
        }
    }

    // 133. 克隆图
    static class CloneGraph {
        // 记录原始节点到复制节点的映射，复制时候不会复制邻居节点
        HashMap<Node, Node> visited = new HashMap<>();

        // 复制当前节点并返回
        public Node cloneGraph(Node node) {
            if (node == null) return null;
            // 如果已经复制过直接返回
            if (visited.containsKey(node)) {
                return visited.get(node);
            }
            // 复制节点当前节点已经访问过，添加到映射表
            Node newNode = new Node(node.val);
            visited.put(node, newNode);
            // 更新邻居节点关系
            for (Node neighbor : node.neighbors) {
                Node tmp = cloneGraph(neighbor);
                newNode.neighbors.add(tmp);
            }
            return newNode;
        }
    }

    // TODO 399. 除法求值


    // TODO 207. 课程表


    // TODO 210. 课程表 II


    /** -------------------------【图的广度优先搜索】------------------------- */
    // TODO 909. 蛇梯棋


    // TODO 433. 最小基因变化


    // TODO 127. 单词接龙
}
