package com.wooyoo.blog.algorithm.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 80, 72, 6, 57, 88, 60, 42, 83, 73, 48, 85 };
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 每次选出数组中最小的那个元素，然后放在开头
     */
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // swap
            swap(arr, i, minIndex);
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
