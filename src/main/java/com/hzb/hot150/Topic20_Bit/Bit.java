package com.hzb.hot150.Topic20_Bit;

public class Bit {
    /** -------------------------【位运算】------------------------- */
    // 67. 二进制求和
    public String addBinary(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

    // 190. 颠倒二进制位
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    // 191. 位1的个数
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    // 136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    // 137. 只出现一次的数字 II
    public int singleNumberII(int[] nums) {
        return find(nums, 3);
    }

    private int find(int[] arr, int m) {
        // 取出32位的状态,求出数组中所有数字的每一位中1的和
        int[] cnt = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                cnt[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // 根据每一位的位信息拼出来ans的值
            // 如果某一位的位信息中1的数量小于m，说明答案中没有该位置的1.反之，就拼上该位置的信息
            if (cnt[i] % m != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    // 201. 数字范围按位与
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            // 抹去最右边的 1
            right = right & (right - 1);
        }
        return right;
    }
}
