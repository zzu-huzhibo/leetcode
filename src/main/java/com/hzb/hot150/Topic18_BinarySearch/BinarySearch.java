package com.hzb.hot150.Topic18_BinarySearch;

public class BinarySearch {
    /** -------------------------【二分查找】------------------------- */
    // 35. 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    // 74. 搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    // 162. 寻找峰值
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long mid_l = mid - 1 >= 0 ? nums[mid - 1] : Long.MIN_VALUE;
            long mid_r = mid + 1 < nums.length ? nums[mid + 1] : Long.MIN_VALUE;

            if (nums[mid] > mid_r && nums[mid] > mid_l) {
                ans = mid;
                break;
            } else if (nums[mid] < mid_r && nums[mid] < mid_l) {
                left = mid + 1;
            } else if (nums[mid] < mid_r && nums[mid] > mid_l) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // TODO 33. 搜索旋转排序数组


    // 34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        int lIndex = bs_L(nums, target);
        int rIndex = bs_R(nums, target);
        return new int[]{lIndex, rIndex};
    }

    private static int bs_R(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                ans = m;
                l = m + 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    private static int bs_L(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                ans = m;
                r = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    // TODO 153. 寻找旋转排序数组中的最小值


    // TODO 4. 寻找两个正序数组的中位数
}
