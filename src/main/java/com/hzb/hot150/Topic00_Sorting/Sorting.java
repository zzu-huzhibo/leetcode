package com.hzb.hot150.Topic00_Sorting;

public class Sorting {

    /** 冒泡排序 选择排序 插入排序 */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) {
                break; // 没有元素交换，直接退出循环
            }
        }
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {  // minIndex不是开始的位置，交换
                swap(arr, i, minIndex);
            }
        }
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 10, 4, 1, 5, 6, 2, 7, 3, 8, 9 };
        insertionSort(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }


    /** 堆排序 快排 归并排序 */
    private static void mergeSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hi);

            int[] help = new int[hi - lo + 1];
            int i = lo, j = mid + 1, k = 0;

            while (i <= mid && j <= hi) {
                if (arr[i] <= arr[j]) {
                    help[k++] = arr[i++];
                } else {
                    help[k++] = arr[j++];
                }
            }
            while (i <= mid) {
                help[k++] = arr[i++];
            }
            while (j <= hi) {
                help[k++] = arr[j++];
            }
            k = 0;
            while (k < help.length) {
                arr[lo++] = help[k++];
            }
        }
    }

    private static void quickSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int pivot = arr[(int) (Math.random() * (hi - lo + 1) + lo)];
            int cur = lo, less = lo, more = hi;
            while (cur <= more) {
                if (arr[cur] < pivot) {
                    swap(arr, cur++, less++);
                } else if (arr[cur] > pivot) {
                    swap(arr, cur, more--);
                } else {
                    cur++;
                }
            }
            quickSort(arr, lo, less - 1);
            quickSort(arr, more + 1, hi);
        }
    }

    private static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            heapInsert(arr, i);
        }

        while (n > 1) {
            swap(arr, 0, --n);
            heapify(arr, 0, n);
        }
    }

    private static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void heapify(int[] nums, int idx, int size) {
        int l = idx * 2 + 1;
        while (l < size) { // 有左孩子，进入循环
            // 左孩子=l    右孩子=l + 1
            // 有右孩子 && 右孩子较大 best = 右孩子
            // 只有左孩子 best = 左孩子
            int larger = l + 1 < size && nums[l + 1] > nums[l] ? l + 1 : l;
            // 比较孩子节点与父节点的大小
            larger = nums[larger] > nums[idx] ? larger : idx;
            // 最大的是父节点，说明已经符合大根堆的特性，可以退出循环
            if (larger == idx) {
                break;
            }
            // 调整父节点和孩子节点的位置
            swap(nums, larger, idx);
            // 修改指针 父节点指向孩子中较大的
            idx = larger;
            // 父节点的左孩子为 i * 2 + 1
            l = idx * 2 + 1;
        }
    }


    /** 计数排序 基数排序 */
    private static void countingSort(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }

        int bucketLen = max + 1;
        // bucket[i] = i
        int[] bucket = new int[bucketLen];

        for (int value : arr) {
            bucket[value]++; // bucket[value] = value 的数量＋1
        }

        int cur = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
                arr[cur++] = j; // cur位置放置 j的值 cur++
                bucket[j]--; // 桶内元素数量 - 1
            }
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
