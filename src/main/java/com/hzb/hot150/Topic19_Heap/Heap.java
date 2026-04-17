package com.hzb.hot150.Topic19_Heap;

import java.util.PriorityQueue;

public class Heap {
    /** -------------------------【堆】------------------------- */
    // 215. 数组中的第K个最大元素
    public int findKthLargest(int[] arr, int k) {
        return quickSort(arr, 0, arr.length - 1, arr.length - k);
    }

    private int quickSort(int[] arr, int lo, int hi, int k) {
        if (lo == hi) return arr[k];
        int less = lo - 1, more = hi + 1;
        int pivot = arr[(int) (Math.random() * (hi - lo + 1)) + lo];
        int idx = lo;
        while (idx < more) {
            if (arr[idx] < pivot) {
                swap(arr, idx++, ++less);
            } else if (arr[idx] > pivot) {
                swap(arr, idx, --more);
            } else {
                idx++;
            }
        }
        if (k > less && k < more) return arr[more - 1];
        if (k <= less) return quickSort(arr, lo, less, k);
        else return quickSort(arr, more, hi, k);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // TODO 502. IPO


    // TODO 373. 查找和最小的 K 对数字


    // 295. 数据流的中位数
    class MedianFinder {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            // 大根堆为空 或者大根堆的堆顶元素大于num
            if (maxHeap.isEmpty() || maxHeap.peek() > num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            if (Math.abs(maxHeap.size() - minHeap.size()) == 2) {
                if (maxHeap.size() > minHeap.size()) minHeap.add(maxHeap.poll());
                else maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) return (maxHeap.peek() + minHeap.peek()) / 2.0;
            else return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }
}
