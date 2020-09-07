package com.demo.leecode;

import com.alibaba.fastjson.JSONObject;

/**
 * 选择排序: 第一轮遍历，选出最小值，放到和第一个元素位 ---》 第二轮遍历，再取出最小值，和第二个元素互换位置  --->  以此类推
 * @author nijiejie
 */
public class Choose {

    private static int[] arr = new int[]{7,4,11,8,3,23,1};

    public static void main(String[] args) {

        method();

    }

    public static void method(){

        for (int j = 0; j<arr.length-1;j++){

            int minIndex = j;
            int min = arr[j];
            for (int i=j+1;i<arr.length;i++){

                if (arr[i] < min){
                    min = arr[i];
                    minIndex = i;
                }

            }

            arr[minIndex] = arr[j];
            arr[j] = min;

        }

        System.out.println("方法获取的数组为："+ JSONObject.toJSONString(arr));

    }

}
