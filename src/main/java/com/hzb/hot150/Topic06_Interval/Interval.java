package com.hzb.hot150.Topic06_Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Interval {
    // 228. 汇总区间
    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        int left = 0, right = 0;
        while (right < nums.length) {
            // 下一个没有越界且下一个数比当前大1，可以往后遍历
            while (right + 1 < n && nums[right] + 1 == nums[right + 1]) {
                right++;
            }
            // 如果只有一个数组
            if (left == right) {
                res.add("" + nums[left]);
            } else {
                // 当前区间有多个数
                res.add(nums[left] + "->" + nums[right]);
            }
            // 下标在left~right的区间处理完毕，更新left，right
            left = right + 1;
            right++;
        }
        return res;
    }

    // 56. 合并区间
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][2];
        int n = intervals.length;
        // 按照区间起始位置从小到大排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        // 初始情况：最小就是第一个开头，最大就是第一个末尾
        int min = intervals[0][0], max = intervals[0][1];
        // 遍历剩下的区间
        for (int i = 1; i < n; i++) {
            // 当前区间起始位置大于之前的最大值，说明前面的区间和后面的区间是断开的，可以收集前一个区间。
            // 更新最小值：当前区间的起始位置，
            // 更新最大值：当前区间的结束位置
            if (intervals[i][0] > max) {
                res.add(new int[]{min, max});
                min = intervals[i][0];
                max = intervals[i][1];
            } else {
                // 可以合并，更新区间的最大值最小值
                min = Math.min(min, intervals[i][0]);
                max = Math.max(max, intervals[i][1]);
            }
        }
        // 添加最后一个
        res.add(new int[]{min, max});
        // 转化为数组返回
        return res.toArray(new int[res.size()][]);
    }

    // 57. 插入区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {

            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    // 如果没有添加过newInterval，就添加，同时更新placed
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    // 452. 用最少数量的箭引爆气球
    public int findMinArrowShots(int[][] points) {
        // 右端点从小到大排序
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        long pre = Long.MIN_VALUE;
        int cnt = 0;
        for (int[] point : points) {
            if (point[0] > pre) {
                pre = point[1];
                cnt++;
            }
        }
        return cnt;
    }
}
