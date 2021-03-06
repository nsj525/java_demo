package com.demo.design.patterns.strategy.impl;

import com.demo.design.patterns.strategy.service.CoverService;

import org.springframework.stereotype.Service;

/**
 * 对象转化类DemoA
 *
 * @author nijiejie
 */
@Service("demoACoverServiceImpl")
public class DemoACoverServiceImpl implements CoverService {


    @Override
    public String getSign() {
        return "策略A";
    }

    @Override
    public Object cover(Object param) {

        System.out.println("进入了A的方法");
        return null;
    }


}
