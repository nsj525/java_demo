package com.demo.leetcode.shuzu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * @author nijiejie
 */
public class Simple_Three {

    public static final int[] NUM= new int[]{2,2,1,1,1,2,2};

    public static Integer myMethod(){

        Map<Integer,Integer>  map = new HashMap<>(16);
        for (int a : NUM){

            if (null == map.get(a)){
                map.put(a,1);
            }else{
                map.put(a,map.get(a)+1);
            }
            if (map.get(a)>NUM.length/2){
                return a;
            }
        }
        return null;

    }

    /**
     * Hash表的方式，和我的实现差不多，但是他这走了两次循环，原则上应该是O（n）和O（m） ps:m表示Map的长度
     * 我的实现只有一次循环，O(n)
     * 但是实际执行中，无论是耗时和内存占用都是他的写法较为优秀
     *
     *
     *
     * @return
     */
    public static Integer method1(){
        Map<Integer, Long> map = Arrays.stream(NUM).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long limit = NUM.length >> 1;
        for (Map.Entry<Integer, Long> entry : map.entrySet()){
            if (entry.getValue() > limit) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("多数元素为："+myMethod());
    }

}
