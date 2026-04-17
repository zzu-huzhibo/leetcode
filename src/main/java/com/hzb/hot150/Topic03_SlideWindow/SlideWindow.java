package com.hzb.hot150.Topic03_SlideWindow;

import java.util.Arrays;

public class SlideWindow {
    // 209. 长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0, sum = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum - nums[l] >= target) {
                sum -= nums[l++];
            }
            if (sum >= target) {
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        // 字符都是ascii    char需要2字节，   2^16 - 1 = 255
        int[] pos = new int[255];
        Arrays.fill(pos, -1);
        int ans = 0;
        for (int l = 0, r = 0; r < chars.length; r++) {
            l = Math.max(pos[chars[r]] + 1, l);
            ans = Math.max(ans, r - l + 1);
            pos[chars[r]] = r;
        }
        return ans;
    }

    // TODO 30. 串联所有单词的子串


    // 76. 最小覆盖子串
    public String minWindow(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int[] arr = new int[256];
        // arr表示欠债表
        for (char c : tArr) {
            arr[c]--;
        }
        // 欠的字母的数量
        int debt = t.length();
        int start = 0;
        int len = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < s.length(); r++) {
            // 当前字母是欠的字母，并且没还完
            if (arr[sArr[r]]++ < 0) {
                debt--; // 还款加1
            }
            // 还完了。开始收缩
            if (debt == 0) {
                // 当前位置数量大于0，可以减去，l++;更新arr
                while (arr[sArr[l]] > 0) {
                    arr[sArr[l++]]--;
                }
                // 找到了更小的长度，更新最小长度len，和开始位置start
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    start = l;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

}
