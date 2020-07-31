package com.demo.strategy.impl;

import com.demo.strategy.service.CoverService;

import org.springframework.stereotype.Service;

/**
 * 对象转化类DemoB
 * @author nijiejie
 */
@Service("demoBCoverServiceImpl")
public class DemoBCoverServiceImpl implements CoverService {
    @Override
    public String getSign() {
        return "策略B";
    }

    @Override
    public Object cover(Object param) {
        System.out.println("进入了B的方法");
        return null;
    }
}
