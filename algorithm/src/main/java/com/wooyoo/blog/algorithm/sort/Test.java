package com.wooyoo.blog.algorithm.sort;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = new int[] { 5, 10, 8, 3, 1, 7, 2, 2, 0, -1 };
        new Test().test(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void test(int[] arr, int left, int right) {
        if (arr.length == 0) {
            return;
        }

        int oldLeft = left;
        int oldRight = right;

        if (left >= right) {
            return;
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
        System.out.println(Arrays.toString(arr));

        test(arr, oldLeft, left - 1);
        test(arr, left + 1, oldRight);
    }
}
