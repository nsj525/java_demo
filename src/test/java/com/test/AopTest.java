package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aop.TestDemoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:*.xml")
public class AopTest {

    @Autowired
    private TestDemoDto testDemoDto;

    @Test
    public void testMethod(){
        System.out.println("打印结果为："+testDemoDto.tryImport());
    }

}
