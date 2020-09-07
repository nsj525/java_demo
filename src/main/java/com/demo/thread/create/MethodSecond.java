package com.demo.thread.create;

/**
 * 实现runnable接口方式：线程的启动，是通过Thread类的start()方法，实现Runnable接口的方式，直接new出来的对象，并不能直接启动，需要先创建一个Thread对象，如下↓↓↓
 * @author nijiejie
 */
public class MethodSecond implements Runnable {
    @Override
    public void run() {
        int i = 0;
        do{
            System.out.println(Thread.currentThread().getName() + "--------" + i);
            i++;
        }while(i<10);
    }


    public static void main(String[] args) {
        method1();
        // method2();
        // method3();
    }

    /**
     * 实现Runnable接口的类的实例对象仅仅作为Thread对象的target
     * Runnable实现类里包含的run()方法仅仅作为线程执行体
     * 而实际的线程对象依然是Thread实例
     * 这里的Thread实例负责执行其target的run()方法；
     *
     */
    public static void method1(){
        MethodSecond methodSecond = new MethodSecond();
        new Thread(methodSecond).start();
        new Thread(methodSecond).start();
    }

    /**
     * 大多时候，创建线程都不需要新建一个集成Runnable接口的线程类来处理，可以直接通过下面的方式创建
     */
    public static void method2(){

        for (int m = 0 ; m <2 ; m++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 0;
                    do{
                        System.out.println(Thread.currentThread().getName() + "--------" + i);
                        i++;
                    }while(i<10);
                }
            }).start();
        }

    }

    /**
     * 通过lambda表达式创建线程实例，无需显式的声明Runnable的run()方法，格式类似于↓↓
     * new Thread( () -> { do something })
     * 这里{}中内容相当于run()方法
     *
     * 从JAVA8开始，Runnable接口使用了@FunctionlInterface修饰，也就是说Runnable接口是函数式接口，可使用lambda表达式创建对象
     */
    public static void method3(){

        for (int m = 0 ; m <2 ; m ++){
            new Thread(() -> {

                int i = 0;
                do{
                    System.out.println(Thread.currentThread().getName() + "--------" + i);
                    i++;
                }while(i<10);

            }).start();
        }

    }


}
