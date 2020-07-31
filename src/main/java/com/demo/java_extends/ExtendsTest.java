package com.demo.java_extends;

/**
 * @author nijiejie
 */
public class ExtendsTest {

    public static void main(String[] args) {
        Child child = new Child();
        //调用重写后的方法
        child.doSomething();
        System.out.println();
        System.out.println("---------进入重载方法-------");
        child.doSomething("学习");
        String str = child.doSomething(100);
        System.out.println("有返回值的方法返回结果为："+str);
    }

}
