package com.demo.java_extends;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nijiejie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    /**
     * 姓名
     */
    private String name;

    /**
     * 住址
     */
    private String address;

    void doSomething(){

        System.out.println("-----挣钱养家-------");

    }


}
