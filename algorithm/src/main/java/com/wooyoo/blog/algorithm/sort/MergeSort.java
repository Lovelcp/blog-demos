package com.wooyoo.blog.algorithm.sort;

import java.util.Arrays;
import java.util.Stack;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 80, 72, 6, 57, 88, 60, 42, 83, 73, 48, 85 };
        //        mergeSort(arr);
        mergeIteration(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 递归归并
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    public static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);//左边归并排序，使得左子序列有序
            sort(arr, mid + 1, right, temp);//右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp);//将两个有序子数组合并操作
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            }
            else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    /**
     * 迭代归并
     *
     * @param arr
     */
    private static void mergeIteration(int[] arr) {
        int[] temp = new int[arr.length];
        int rightMax = arr.length - 1;

        Stack<Integer> splitStack = new Stack<>(); // 用于计算中间合并数组的区间
        Stack<Integer> mergeStack = new Stack<>(); // 存放递归路径
        splitStack.push(0);
        splitStack.push(rightMax);

        // 计算递归路径
        int mid;
        while (!splitStack.empty()) {
            int right = splitStack.pop();
            int left = splitStack.pop();
            mergeStack.push(left);
            mergeStack.push(right);
            mid = (left + right) / 2;
            if (right - left > 1) {
                // split
                splitStack.push(left);
                splitStack.push(mid);
                splitStack.push(mid + 1);
                splitStack.push(right);
                continue;
            }
            merge(arr, left, mid, right, temp);
        }

        // 迭代归并
        while (!mergeStack.isEmpty()) {
            int right = mergeStack.pop();
            int left = mergeStack.pop();
            mid = (left + right) / 2;
            merge(arr, left, mid, right, temp);
        }
    }
}
