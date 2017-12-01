package com.wooyoo.blog.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 80, 72, 6, 57, 88, 60, 42, 83, 73, 48, 85 };
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 每一轮循环，前后两者比较，如果前者比后者大，则交换
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        boolean hasSwapped = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    hasSwapped = true;
                }
            }

            if (!hasSwapped) {
                // 如果没有交换，说明已经有序，则退出
                break;
            }
        }
    }

    /**
     * 交换数组元素
     *
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        if (a != b) {
            arr[a] = arr[a] + arr[b];
            arr[b] = arr[a] - arr[b];
            arr[a] = arr[a] - arr[b];
        }
    }
}
