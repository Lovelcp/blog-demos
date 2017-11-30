package com.wooyoo.blog.algorithm.sort;

import java.util.Arrays;

public class Test {
    public static void mergeSort(int[] a){
        int[] TR = new int[a.length];//用于存放归并结果

        int k=1;//起始，子序列长度为1
        while(k<a.length){
            mergePass(a, TR, k, a.length);//将原先无序的数据两两归并入TR
            k = 2*k;//子序列长度加倍
            mergePass(TR, a, k, a.length);//将TR中已经两两归并的有序序列再归并回数组a
            k = 2*k;//子序列长度加倍
        }
    }

    public static void mergePass(int[] SR, int [] TR,int s,int len){

        int i=0;
        while (i < len-2*s+1) {//8
            merge(SR,TR,i,i+s-1,i+2*s-1);//两两归并
            i=i+2*s;
        }

        //处理最后的尾数
        //i=8
        if(i< len-s+1){//9
            merge(SR, TR, i, i+s-1, len-1);//归并最后两个序列
        }else {
            for (int j = i; j < len; j++) {//若最后只剩下单个子序列
                TR[j] = SR[j];
            }
        }
    }

    public static void merge(int[] SR,int[] TR,int i,int m,int n){
        int j,k,l;

        //i(0~4) j(5~8)
        for(j=m+1,k=i; i<=m && j<=n; k++){

            if(SR[i]<SR[j]){
                TR[k] = SR[i++];
            }else{
                TR[k] = SR[j++];
            }
        }


        if(i<=m){
            for (l = 0; l <= m-i ; l++) {
                TR[k+l] = SR[i+l];
            }
        }

        if(j<=n){
            for (l = 0; l <= n-j; l++) {
                TR[k+l] = SR[j+l];
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,5,4,1,6,9,8,7,10,20,45,32,28,44,31,55,43,23,21,23,21,33,21};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
