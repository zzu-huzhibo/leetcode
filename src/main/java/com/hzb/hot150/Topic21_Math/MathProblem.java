package com.hzb.hot150.Topic21_Math;

/** -------------------------【数学】------------------------- */
public class MathProblem {


    // 9. 回文数
    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    // 50. Pow(x, n)
    public double myPow(double x, int n) {
        if (n > 0) return recur(x, n);
        else return 1.0 / recur(x, -n);
    }

    private double recur(double x, long n) {
        if (n == 0) return 1.0;
        double y = recur(x, n / 2);
        if (n % 2 == 0) return y * y;
        else return y * y * x;
    }

    // 66. 加一
    public int[] plusOne(int[] digits) {
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i];
            if (i == digits.length - 1) {
                digit++;
            }
            int sum = digit + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }

        if (carry > 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for (int i = 1; i < res.length; i++) {
                res[i] = digits[i - 1];
            }
            return res;
        }
        return digits;
    }

    // 69. x 的平方根
    public int mySqrt_binarySearch(int x) {
        long res = 0, l = 0, r = x;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (mid * mid <= x) {
                res = mid;
                l = mid + 1;
            } else if (mid * mid > x) {
                r = mid - 1;
            }
        }
        return (int) res;
    }


    //快速平方根取倒数算法 https://www.cnblogs.com/MeltingPot/p/19165461
    public static double invSqrt(double x) {
        double x_half = 0.5d * x;
        long i = Double.doubleToLongBits(x); // 转成long
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i); // 转成double
        x *= (1.5d - x_half * x * x);
        return x;
    }

    // 172. 阶乘后的零
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            if (n % 5 == 0) {
                res += n / 5;
                n /= 5;
            } else {
                n--;
            }
        }
        return res;
    }


    // TODO 149. 直线上最多的点数
}
