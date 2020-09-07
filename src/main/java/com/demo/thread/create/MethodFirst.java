package com.demo.thread.create;

/**
 * new thread 方式创建
 * @author nijiejie
 */
public class MethodFirst extends Thread {

    @Override
    public void run(){
        for (int i = 0;i<10;i++){
            System.out.println(this.getName()+"-------"+i);
        }
    }

    public static void main(String[] args) {

        new MethodFirst().start();
        new MethodFirst().start();

    }

}
