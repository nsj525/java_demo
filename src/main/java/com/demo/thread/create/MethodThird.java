package com.demo.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *
 * @author nijiejie
 */
public class MethodThird {

    public static void main(String[] args) {

        // method1();
        method();

    }

    public static void method(){

        FutureTask futureTask = new FutureTask<Integer>((Callable<Integer>)()->{
            int i = 0;
            for(;i < 50;i++) {
                System.out.println(Thread.currentThread().getName() +
                        "  的线程执行体内的循环变量i的值为：" + i);
            }
            return i;
        });
        for (int m = 0 ; m < 2 ; m++){
            new Thread(futureTask).start();
        }
    }

    public static void method1(){

        for (int m = 0 ; m<2 ;m++){
            new Thread(() -> {

                new FutureTask<Integer>((Callable<Integer>)()->{
                    int i = 0;
                    for(;i < 50;i++) {
                        System.out.println(Thread.currentThread().getName() +
                                "  的线程执行体内的循环变量i的值为：" + i);
                    }
                    return i;
                });
            }).start();
        }

    }

}
