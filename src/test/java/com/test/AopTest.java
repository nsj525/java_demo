package com.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.aop.TestDemoDto;

public class AopTest extends BaseTest {

    @Autowired
    private TestDemoDto testDemoDto;

    @Test
    public void testMethod(){
        System.out.println("打印结果为："+testDemoDto.tryImport());
    }

}
