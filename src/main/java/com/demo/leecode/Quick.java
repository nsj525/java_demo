package com.demo.leecode;

import com.alibaba.fastjson.JSONObject;

/**
 * 快速排序
 *
 * PS:从小到大排序为例
 *
 * 思路：从数组中随机选择一个数，作为基础值（默认选第一位元素）
 *
 * 通过双指针，分别从数组第一位和最后一位遍历数组，如果存在前面的值大于基础值，并且后面的值小于基础值，则互换这两个位置的数值，直到前后遍历到同一个位置的值为止
 * 此时替换当前位置的值x为基本值，并且设置对应基础值取值位置上的值为x
 * 从而实现所有小于基础值的数据都在基本值左侧，大于基本值的都在基本值右侧
 *
 * 递归：基本值左侧的数据 && 基本值右侧的数据 ，重复上述，直到数据变成有序位置
 *
 * 所以啊，递归不一定效率低，详细图解可以看md文件
 *
 *
 *
 * @author nijiejie
 */
public class Quick {

    public static final int[] NUM = new int[]{45,78,64,3,52,11,64,55,99,11,18};
    /*
        45 18 11 3 11 52 99 64 78
     */

    public static void main(String[] args) {
        quickSort(NUM,0,NUM.length-1);
        // sort(NUM,0,NUM.length-1);
        System.out.println("排序后结果为："+ JSONObject.toJSONString(NUM));
    }

    public static void quickSort(int[] num , int left ,int right){

        if (left >= right){
            return;
        }

        int temp = num[left];
        int start = left;
        int end = right;

        while(start < end){

            /**
             * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！很重要！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
             *
             * 快排一定要先排右侧数组，优先执行减法操作
             * 原因是，每完成一轮排序（左右根据基本值分组）前一次循环时
             * 指针分别指向的是： start--->最靠右的小于基础值的数，end ----> 最靠左的大于基础值的数
             * 此时需要替换基本值到最中间，就是把start指向的值和基础值做互换（pssssss：只要你有点脑子，你就不会拿基础值和end指向的互换吧？不会吧？）
             * 但是此时仍满足start < end 条件，会再次进入循环
             * 如果先执行 start++ 操作，则指针就会指向下一位，而下一位是end指针指向的位置，他的值是大于基本值的，肯定不能互换
             * 所以！！！！所以要先执行 end--
             * 这样end = start ，下一个执行条件不生效，此时互换值时完美达成基本值和中间值互换的目的
             *
             */

            while(start < end && num[end] >= temp){
                end --;
            }

            while(start < end && num[start] <= temp){
                start ++;
            }

            //45,78,64,3,52,11,64,55,99,11,18
            if (start < end){
                int base = num[start];
                num[start] = num[end];
                num[end] = base;
            }

        }

        //完成一次遍历后，需要把基准元素放到数组的最中间（互换基准元素和最中间的元素的位置）
        num[left] = num[start];
        num[start] = temp;

        //递归左右数组
        quickSort(num,left,start-1);
        quickSort(num,start+1, right);

    }

}
