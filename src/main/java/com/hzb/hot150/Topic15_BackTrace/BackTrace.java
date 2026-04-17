package com.hzb.hot150.Topic15_BackTrace;

import java.util.*;

public class BackTrace {
    /** -------------------------【回溯】------------------------- */
    // 17. 电话号码的字母组合
    static class LetterCombinations {
        List<String> list = new LinkedList<>();

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return list;
            }
            String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            traceback(digits, numString, 0);
            return list;
        }

        StringBuilder temp = new StringBuilder();

        private void traceback(String digits, String[] numString, int num) {
            if (num == digits.length()) {
                list.add(temp.toString());
                return;
            }
            String str = numString[digits.charAt(num) - '0'];
            for (int i = 0; i < str.length(); i++) {
                temp.append(str.charAt(i));
                traceback(digits, numString, num + 1);
                //剔除末尾的继续尝试
                temp.deleteCharAt(temp.length() - 1);
            }
        }

    }

    // 77. 组合
    static class Combine {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();

        public List<List<Integer>> combine(int n, int k) {
            trace(n, k, 1);
            return ret;
        }


        private void trace(int n, int k, int startIndex) {
            //终止条件
            if (path.size() == k) {
                ret.add(new ArrayList<>(path));
                return;
            }
            for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
                path.add(i);
                trace(n, k, i + 1);
                path.removeLast();
            }
        }
    }

    // 46. 全排列
    static class Permute {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();

        boolean[] used;

        public List<List<Integer>> permute(int[] nums) {
            used = new boolean[nums.length];
            traceback(nums);
            return ret;
        }

        private void traceback(int[] nums) {
            if (nums.length == path.size()) {
                ret.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                path.add(nums[i]);
                traceback(nums);
                path.removeLast();
                used[i] = false;
            }
        }
    }

    // 39. 组合总和
    static class CombinationSum {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(candidates); // 先进行排序
            traceback(res, new ArrayList<>(), candidates, target, 0, 0);
            return res;
        }

        private void traceback(List<List<Integer>> res, ArrayList<Integer> path, int[] candidates, int target, int startIndex, int curSum) {
            if (curSum == target) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = startIndex; i < candidates.length; i++) {
                if (curSum + candidates[i] > target) break;
                path.add(candidates[i]);
                traceback(res, path, candidates, target, i, curSum + candidates[i]);
                path.remove(path.size() - 1);
            }

        }
    }

    // 52. N 皇后 II
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        return nqueenHelper(0, new int[n], n);
    }

    private int nqueenHelper(int i, int[] path, int n) {
        if (n == i) return 1;
        int res = 0;
        for (int j = 0; j < n; j++) {
            //  检查(i, j)是否能放下
            if (check(path, i, j)) {
                // (i,j)能放下，继续下一行
                path[i] = j;
                res += nqueenHelper(i + 1, path, n);
            }
        }
        return res;
    }

    private boolean check(int[] path, int i, int j) {
        for (int a = 0; a <= i - 1; a++) {
            if (path[a] == j || Math.abs(i - a) == Math.abs(j - path[a])) {
                return false;
            }
        }
        return true;
    }

    // 22. 括号生成
    static class GenerateParenthesis {
        List<String> res = new ArrayList<>();
        int n = 0;

        public List<String> generateParenthesis(int n) {
            String s = "";
            this.n = n;
            helper(s, 0, 0);
            return res;
        }

        private void helper(String s, int left, int right) {
            if (left + right == n * 2) {
                res.add(s);
                return;
            }
            if (left < n) {
                helper(s + "(", left + 1, right);
            }
            if (right < left) {
                helper(s + ")", left, right + 1);
            }
        }
    }

    // TODO 79. 单词搜索

}
