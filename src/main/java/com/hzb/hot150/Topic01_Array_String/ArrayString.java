package com.hzb.hot150.Topic01_Array_String;

import java.util.*;

public class ArrayString {

    // 88. 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // 指向nums1的有效元素末尾
        int p2 = n - 1; // 指向nums2的末尾
        int p = m + n - 1; // // 指向合并后数组的末尾

        while (p1 >= 0 && p2 >= 0) {
            // 比较nums1[p1]和nums2[p2]，将较大值放在nums1[p]
            if (nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                nums1[p--] = nums2[p2--];
            }
        }
        // 如果nums2还有剩余元素，则将其复制到nums1前部
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    // 27. 移除元素
    public int removeElement(int[] nums, int val) {
        int left = -1; // 左边界：-1代表没有元素 0代表包含索引0的元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[++left] = nums[i];
            }
        }
        return left + 1; // 左边界+1 ，因为左边界是从 0开始计算的
    }

    // 26. 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        int left = 0; // 左边界
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[left]) { //遍历的元素不等于左边界的值，加入到左边界内
                nums[++left] = nums[i];
            }
        }
        return left + 1; // 左边界+1 ，因为左边界是从 0开始计算的
    }

    // 80. 删除有序数组中的重复项 II
    public int removeDuplicatesII(int[] nums) {
        int n = nums.length;
        if (n == 1 || n == 2) {
            return n;
        }
        int left = 1; // 左边界的值，
        for (int i = 2; i < n; i++) {
            //遍历的元素不等于左边界的前一个，这样可以保留两个重复的元素，加入到左边界内
            if (nums[i] != nums[left - 1]) {
                nums[++left] = nums[i];
            }
        }
        return left + 1; // 左边界+1 ，因为左边界是从 0开始计算的
    }

    // 169. 多数元素
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count == 0) candidate = num;
            if (num == candidate) count++;
            else count--;
        }
        return candidate;
    }

    // 189. 轮转数组
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverseArray(nums, 0, n - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, n - 1);
    }

    private static void reverseArray(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    // 121. 买卖股票的最佳时机
    public int maxProfitI(int[] prices) {
        // 核心思想：找到之前的最小数值，如果最小数值大于当前股票价格就不用更新利润，同时更新最低价格
        int maxProfit = 0;
        int cost = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > cost) {
                maxProfit = Math.max(maxProfit, prices[i] - cost);
            } else {
                cost = prices[i];
            }
        }
        return maxProfit;
    }

    // 122. 买卖股票的最佳时机 II
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        // buy表示持有股票的收益，sell表示卖出股票的收益
        int buy1 = -prices[0];
        int buy2 = 0;
        int sell1 = 0;
        int sell2 = 0;
        for (int i = 1; i < n; i++) {
            buy1 = Math.max(buy1, sell1 - prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
        }
        return sell1;
    }

    // 55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int k = 0; // 出发位置，也是能到达的最远位置
        for (int i = 0; i < nums.length; i++) {
            // 当前位置比能到达的最远位置还远，返回false
            if (i > k) return false;
            // 更新最远位置k
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

    // 45. 跳跃游戏 II
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        // 判断能否到达数组末尾，所以不需要遍历到数组末尾，所以是i < length - 1
        for (int i = 0; i < length - 1; i++) {
            // 更新能到达的最远位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 如果当前位置已经到达最远位置，步数+1
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    // 274. H 指数
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int citation : citations) {
            if (citation >= n) {
                count[n]++;
            } else {
                count[citation]++;
            }
        }
        int res = 0;
        for (int i = n; i >= 0; i--) {
            res += count[i];
            if (res >= i) {
                return i;
            }
        }
        return res;
    }

    // 380. O(1) 时间插入、删除和获取随机元素
    class RandomizedSet {
        // map记录记录数字和数组中的位置的映射，随机集合中的数字都是不重复的
        private final HashMap<Integer, Integer> map;
        private final List<Integer> list;
        private final Random random;

        //RandomizedSet() 初始化 RandomizedSet 对象
        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        //bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
        public boolean insert(int val) {
            boolean contains = map.containsKey(val);
            if (!contains) {
                int index = list.size();
                list.add(val);
                map.put(val, index);
                return true;
            } else {
                return false;
            }
        }

        //bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int index = map.get(val);
            // 删除一个元素需要把最后一个元素来顶替他的位置
            int last = list.get(list.size() - 1);
            list.set(index, last);
            map.put(last, index);
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }

        //int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
        public int getRandom() {
            int randomIndex = random.nextInt(list.size());
            return list.get(randomIndex);
        }
    }

    // 238. 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;

        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }
        int R = 1;
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = R * res[i];
            R = R * nums[i];
        }
        return res;
    }

    // 134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        // 窗口[l,r)
        for (int l = 0, r = 0; l < n; l = r + 1, r = l) {
            sum = 0;
            while (sum + gas[r % n] - cost[r % n] >= 0) {
                if (r - l + 1 == n) {
                    return l;
                }
                sum += gas[r % n] - cost[r % n];
                r++;
            }
        }
        return -1;
    }

    // 135. 分发糖果
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        // 初始化left[] 如果右边比左边大，那么右边的设置成左边的+1，否则右边的就是1
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            // 倒序遍历，前面比后面大，前面+1，否则前面的为1
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }

    // 42. 接雨水
    public int trap(int[] height) {
        int n = height.length;
        int res = 0;
        int l = 0, r = n - 1;
        int leftMax = 0, rightMax = 0;
        while (l < r) {
            leftMax = Math.max(leftMax, height[l]);
            rightMax = Math.max(rightMax, height[r]);
            if (height[l] > height[r]) {
                res += rightMax - height[r];
                r--;
            } else {
                res += leftMax - height[l];
                l++;
            }
        }
        return res;
    }

    // 13. 罗马数字转阿拉伯数字
    public int romanToInt(String s) {
        Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int value = symbolValues.get(s.charAt(i));
            // 当前值比下一个值要小，需要减去当前值
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }

    // TODO 12. 整数转罗马数字
    public String intToRoman(int num) {
        return "";
    }

    // 58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        String[] strings = s.split(" ");
        return strings[strings.length - 1].length();
    }

    // 14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int res = 0;
        StringBuilder builder = new StringBuilder();
        outer:
        for (int i = 0; i < minLen; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (c != strs[j].charAt(i)) {
                    break outer;
                }
            }
            builder.append(c);
        }
        return builder.toString();
    }

    // 151. 反转字符串中的单词
    public String reverseWords(String s) {
        String[] words = s.trim().split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = words.length - 1; i > 0; i--) {
            if (!words[i].isEmpty()) {
                builder.append(words[i]).append(" ");
            }
        }
        builder.append(words[0]);
        return builder.toString();
    }

    // TODO 6. Z 字形变换
    public String convert(String s, int numRows) {
        return "";
    }

    // 28. 找出字符串中第一个匹配项的下标 KMP
    public static int kmp(char[] s1, char[] s2) {
        int m = s1.length, n = s2.length;
        // x 表示 s1的指针
        // y 表示 s2的指针
        int x = 0, y = 0;
        int[] next = nextArray(s2, n);
        while(x < m && y < n) {
            if(s1[x] == s2[y]) {
                // 对应位置字符相等，都后移
                x++;
                y++;
            } else if(y == 0) {
                // 对应位置字符不相等，而且s2已经跳到头位置还不相等，表示之前的匹配全部都失效了，需要x往后移一位，重新开始匹配
                x++;
            } else {
                // 对应位置字符不相等，但是s2还没有跳到头位置，前面还有有效匹配位置，从部分有效位置重新开始匹配
                y = next[y];
            }
        }
        // 跳出循环表示有谁到头了，如果是y到头（此时y=n），说明s1中有s2，最开始的位置就是 x - y
        // 否则表示x到头了(此时x = m），说明s1中没有s2，返回-1
        return y ==n ? x - y : -1;
    }

    private static int[] nextArray(char[] s, int n) {
        if (n == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        // i表示当前要求next值的位置
        // cn表示当前要和前一个字符比对的下标
        int i = 2, cn = 0;
        while (i < n) {
            if (s[i - 1] == s[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    // TODO 68. 文本左右对齐


}
