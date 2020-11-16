package com.demo.leetcode.shuzu;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *
 * @author nijiejie
 */
public class Simple_Four {

    public static final int[] NUM = new int[]{2, 7, 16, 15, 11, 14, 9};
    public static final int TARGET = 20;

    public static int[] myMethod(){

        Map<Integer,Integer> map = new HashMap<>();
        int key;
        for (int i = 0 ; i<NUM.length;i++){

            key = TARGET >> NUM[i];
            if (map.containsKey(NUM[i])){
                return new int[]{i,map.get(NUM[i])};
            }
            map.put(key,i);

        }
        return null;

    }

    public static void main(String[] args) {
        System.out.println("------res-----"+ JSONObject.toJSONString(myMethod()));
    }

}
