package com.demo.leetcode;

import com.alibaba.fastjson.JSONObject;

/**
 * 冒泡排序
 * @author nijiejie
 */
public class Maopao {

    /**
     * 给定一个数组[7,4,11,8,3,23,1],按照从小到大排序
     *
     * 冒泡思路：从左到右，用第i个元素和下一个元素作比较，如果i较大，互换位置
     * 第一轮遍历后，最后一个元素肯定是最大的，所以第二轮遍历，可以不对最后一个元素进行比较 --->  同理每轮遍历，都可以少比较一个元素  ↓↓↓
     * --------> 内层循环判断条件  i < arr.length-j-1
     *
     */

    private static int[] arr = new int[]{7,4,11,8,3,23,1};

    public static void main(String[] args) {
        method1();
    }

    public static void method1(){

        for (int j = 0 ; arr.length>j ; j++){
            for (int i = 0 ; i < arr.length-j-1 ;i++){

                if (arr[i] > arr[i+1]){

                    int step = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = step;

                }

            }
        }

        System.out.println("方法1获取的数组为："+ JSONObject.toJSONString(arr));

    }

}
