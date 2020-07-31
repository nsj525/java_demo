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
    public Object cover(String param) {
        System.out.println("-------进入方法B-------");
        return null;
    }
}
