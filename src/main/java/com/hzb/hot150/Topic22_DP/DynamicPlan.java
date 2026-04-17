package com.hzb.hot150.Topic22_DP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DynamicPlan {

    /** -------------------------【一维动态规划】------------------------- */
    // 70. 爬楼梯
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 198. 打家劫舍
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], // 当前位置不偷，那就是前一个物资的最大值
                    nums[i] + dp[i - 2] // 当前位置偷，就是隔一个屋子加上当前物资
            );
        }
        return dp[n - 1];
    }

    // 139. 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int maxLen = 0;

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String sub = s.substring(j, i);

                if (dp[j] && set.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // 322. 零钱兑换
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = 1 + amount;
        }
        //F(i)=min[F(i-c1),F(i-c2),F(i-c3),... F(i-cn)] + 1
        for (int i = 1; i <= amount; i++) { // 填dp数组
            for (int coin : coins) {
                if (coin > i) { // 如果硬币面额大于兑换的数字，跳过
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 300. 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        int res = 0;
        // 填充dp数组 dp数组的含义，dp[i] ---> nums[i+1]结尾的子序列最长递增子序列的长度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /** -------------------------【多维动态规划】------------------------- */
    // 120. 三角形最小路径和
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();  // (n,n)的主对角三角形 dp数组是(n+1, n+1)
        int[][] dp = new int[n + 1][n + 1];
        // 倒序遍历，因为从头开始向下与从下向上是一样的
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    // 64. 最小路径和
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // 初始化第0行，第0列
        for (int i = 1; i < m; i++) dp[i][0] = grid[i][0] + dp[i - 1][0];
        for (int i = 1; i < n; i++) dp[0][i] = grid[0][i] + dp[0][i - 1];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[i][j] 表示从上或者左边来的路径最小路径
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    // 63. 不同路径 II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 只有一个元素还是障碍物，返回0
        if (obstacleGrid[0][0] == 1) return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        // 初始化第0行
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = dp[0][i - 1];
            } else {
                break; // 遇到障碍，跳出循环，此处和后面不可达，都是0
            }
        }
        // 初始化第0列
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i - 1][0];
            } else {
                break; // 遇到障碍，跳出循环，此处和后面不可达，都是0
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        char[] chars = s.toCharArray();
        int length = chars.length;
        int l = 0, r = 0;
        for (int i = 0; i < length; i++) {
            // 以 i 为中心扩展
            int len1 = expandFromCenter(chars, i, i);
            // 以 i，i+1 为中心扩展
            int len2 = expandFromCenter(chars, i, i + 1);
            // 最大长度
            int len = Math.max(len1, len2);
            // 如果超过了之前的最大长度，更新l,r的值
            // 下面代码特殊处理奇偶情况
            // 0  1  2  3  4  5  6
            //    l     i     r
            //    l     i  i+1   r
            if (len > r - l + 1) {
                l = i - (len - 1) / 2;
                r = i + len / 2;
            }
        }
        // 左闭右开区间，所以[l, r+1) = [l, r]
        return s.substring(l, r + 1);
    }

    private int expandFromCenter(char[] chars, int l, int r) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            l--;
            r++;
        }
        return r - l + 1;
    }

    // 97. 交错字符串
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray(), cs3 = s3.toCharArray();
        int n = s1.length(), m = s2.length(), l = s3.length();
        if (n + m != l) return false;
        boolean[][] f = new boolean[n + 10][m + 10];
        f[0][0] = true;
        for (int i = 1; i <= n && f[i - 1][0]; i++)
            f[i][0] = cs1[i - 1] == cs3[i - 1];
        for (int i = 1; i <= m && f[0][i - 1]; i++)
            f[0][i] = cs2[i - 1] == cs3[i - 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (cs1[i - 1] == cs3[i + j - 1]) f[i][j] |= f[i - 1][j];
                if (cs2[j - 1] == cs3[i + j - 1]) f[i][j] |= f[i][j - 1];
            }
        }
        return f[n][m];
    }

    // 72. 编辑距离
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    // 123. 买卖股票的最佳时机 III
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    // 188. 买卖股票的最佳时机 IV
    public int maxProfitIV(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        // k很大时，是不可能做那么多此交易，最多就n/2，（买+卖）* K <= n
        k = Math.min(k, n / 2);
        int[][] buy = new int[n + 1][k + 1];
        int[][] sell = new int[n + 1][k + 1];

        // 初始化第零天，买进第k支股票收益，需要设置成很小的值，表示不合法状态，但是不影响运算过程
        for (int i = 0; i <= k; i++) {
            buy[0][i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i <= n; i++) {
            buy[i][0] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return sell[n][k];
    }

    // 221. 最大正方形
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int maxSide = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // 求得 该位置上方，左方，左上方的最小值； 然后 + 1，比较与maxSize的值
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
