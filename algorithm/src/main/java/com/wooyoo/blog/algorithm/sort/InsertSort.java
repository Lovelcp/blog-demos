package com.wooyoo.blog.algorithm.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 80, 72, 6, 57, 88, 60, 42, 83, 73, 48, 85 };
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序优化点：
     * 1. 从后往前遍历，速度更快，效率更高
     * 2. 不调用swap方法，减少交换次数
     *
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int t = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1] > t; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = t;
        }
    }
}
