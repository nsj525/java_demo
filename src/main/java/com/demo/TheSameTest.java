package com.demo;


import java.util.Arrays;
import java.util.List;

/**
 * How to stop that?
 *
 * 测试return 效果
 * @author nijiejie
 */
public class TheSameTest {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);


        /**
         * for 循环中，可以通过 continue 跳过本次循环，通过 break 或 return 结束整个循环
         */
        for (int a : list){
            if (a == 5){
                return;
            }
            System.out.println("--------"+a);
        }

        System.out.println();
        System.out.println("---------------lambda表达式---------------");

        /**
         * forEach 方法不支持 continue 和 break,只能通过 return 结束本次循环，无法结束整个循环
         */
        list.forEach(a -> {

            if (a == 5){
                return;
            }
            System.out.println("----lam----"+a);

        });

    }

}
