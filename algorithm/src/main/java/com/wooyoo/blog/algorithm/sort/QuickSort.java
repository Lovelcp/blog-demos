package com.wooyoo.blog.algorithm.sort;

import java.util.Arrays;
import java.util.Stack;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 72, 6, 57, 88, 60, 42, 83, 73, 48, 85 };
        //        quickSort(arr, 0, arr.length - 1);
        quickSortIteration(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr.length <= 0)
            return;
        if (low >= high)
            return;
        int left = low;
        int right = high;
        int pivot = arr[left];   //挖坑1：保存基准的值
        System.out.println("基准值：" + pivot);
        while (left < right) {
            while (left < right && arr[right] >= pivot) {  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;   //基准值填补到坑3中，准备分治递归快排
        System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    public static void quickSortIteration(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);

        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();

            int right = high;
            int left = low;

            if (left >= right) {
                continue;
            }

            int pivot = arr[left];

            while (left < right) {
                while (left < right && arr[right] >= pivot) {
                    right--;
                }
                arr[left] = arr[right];

                while (left < right && arr[left] <= pivot) {
                    left++;
                }
                arr[right] = arr[left];
            }

            arr[left] = pivot;

            stack.push(low);
            stack.push(left - 1);
            stack.push(left + 1);
            stack.push(high);
        }
    }
}
