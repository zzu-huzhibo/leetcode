package com.hzb.hot150.Topic05_HashTable;

import java.util.*;

public class HashMapProblem {
    // 383. 赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] hash = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            hash[c - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            hash[c - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (hash[i] < 0) return false;
        }
        return true;
    }

    // 205. 同构字符串
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c_s = s.charAt(i);
            char c_t = t.charAt(i);
            // a:b
            // a:a
            // b:a
            // b:b
            if (map.containsKey(c_s)) {
                Character c = map.get(c_s);
                if (c != c_t) {
                    return false;
                }
            } else {
                if (map.containsValue(c_s)) {
                    return false;
                }
                map.put(c_s, c_t);
            }
        }
        return true;
    }

    // 290. 单词规律
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> map = new HashMap<>();
        int len = pattern.length();
        String[] str = s.split(" ");
        if (len != str.length) return false;
        for (int i = 0; i < len; i++) {
            char key = pattern.charAt(i);
            if (!map.containsKey(key)) {
                if (map.containsValue(str[i])) {
                    return false;
                }
                map.put(key, str[i]);
            } else if (!map.get(key).equals(str[i])) {
                return false;
            }
        }
        return true;
    }

    // 242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            Integer count = map.get(c);
            if (count > 0) {
                map.put(c, --count);
            } else {
                return false;
            }
        }
        return true;
    }

    // 49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            List<String> strings = map.getOrDefault(key, new ArrayList<>());
            strings.add(s);
            map.put(key, strings);
        }
        return new ArrayList<>(map.values());
    }

    // 1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            }
            map.put(num, i);
        }
        return null;
    }

    // 202. 快乐数
    public boolean isHappy(int n) {
        int slow = n;
        int fast = newN(n);
        while (fast != 1 && slow != fast) {
            slow = newN(slow);
            fast = newN(newN(fast));
        }
        return fast == 1;
    }

    private int newN(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    // 219. 存在重复元素 II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                Integer index = map.get(num);
                if (Math.abs(i - index) <= k) {
                    return true;
                }
                map.put(num, i);
            } else {
                map.put(num, i);
            }
        }
        return false;
    }

    // 128. 最长连续序列
    public int longestConsecutive(int[] nums) {
        int res = 0;    // 记录最长连续序列的长度
        Set<Integer> numSet = new HashSet<>();  // 记录所有的数值
        for (int num : nums) {
            numSet.add(num);    // 将数组中的值加入哈希表中
        }
        int seqLen;     // 连续序列的长度
        for (int num : numSet) {
            // 如果当前的数是一个连续序列的起点，统计这个连续序列的长度
            if (!numSet.contains(num - 1)) {
                seqLen = 1;
                while (numSet.contains(++num)) seqLen++;  // 不断查找连续序列，直到num的下一个数不存在于数组中
                res = Math.max(res, seqLen);    // 更新最长连续序列长度
            }
        }
        return res;
    }
}
