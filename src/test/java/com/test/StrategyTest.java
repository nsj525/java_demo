package com.test;

import com.demo.design.patterns.strategy.CoverResolver;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StrategyTest extends BaseTest {

    @Autowired
    private CoverResolver coverResolver;

    @Test
    public void testCover(){

        coverResolver.doCover("策略A","haha");

    }

}
