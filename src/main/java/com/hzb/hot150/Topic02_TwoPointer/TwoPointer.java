package com.hzb.hot150.Topic02_TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointer {
    // 125. 验证回文串
    public static boolean isPalindrome(String s) {
        int n = s.length();
        s = s.toLowerCase();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && isChar(s.charAt(left))) left++;
            while (left < right && isChar(s.charAt(right))) right--;
            if (left < right && s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    private static boolean isChar(char c) {
        return (c < 'a' || c > 'z') && (c < '0' || c > '9');
    }

    // 392. 判断子序列
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) return false;
        int sPtr = 0, tPtr = 0;
        while (sPtr < s.length() && tPtr < t.length()) {
            if (s.charAt(sPtr) == t.charAt(tPtr)) {
                sPtr++;
                tPtr++;
            } else {
                tPtr++;
            }
        }
        return sPtr == s.length();
    }

    // 167. 两数之和 II - 输入有序数组
    public int[] twoSumII(int[] numbers, int target) {
        int L = 0;
        int R = numbers.length - 1;
        while (L < R) {
            if (numbers[L] + numbers[R] > target) {
                R--;
            } else if (numbers[L] + numbers[R] == target) {
                return new int[]{L + 1, R + 1};
            } else {
                L++;
            }
        }
        return null;
    }

    // 11. 盛最多水的容器
    public int maxArea(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, area);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    // 15. 三数之和
    public List<List<Integer>> threeSum_1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) continue; // 跳过重复数字
            if (x + nums[i + 1] + nums[i + 2] > 0) break; // 优化一
            if (x + nums[n - 2] + nums[n - 1] < 0) continue; // 优化二
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int s = x + nums[j] + nums[k];
                if (s > 0) {
                    k--;
                } else if (s < 0) {
                    j++;
                } else { // 三数之和为 0
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    ans.add(list);
                    for (j++; j < k && nums[j] == nums[j - 1]; j++) ; // 跳过重复数字
                    for (k--; k > j && nums[k] == nums[k + 1]; k--) ; // 跳过重复数字
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum_2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) break;
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                while (left < right && nums[left] + nums[right] < -nums[i]) {
                    left++;
                }
                while (left < right && nums[left] + nums[right] > -nums[i]) {
                    right--;
                }
                while (left < right && nums[left] + nums[right] == -nums[i]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    ans.add(list);
                    do left++; while (left < right && nums[left] == nums[left - 1]);
                    do right--; while (left < right && nums[right] == nums[right + 1]);
                }
            }
        }
        return ans;
    }

}
