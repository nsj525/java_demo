package com.demo.design.patterns.factory.method;

import com.demo.design.patterns.factory.Car;

/**
 * 工厂方法接口类
 * @author nijiejie
 */
public interface MethodFactory {

    /**
     * 制造汽车
     * @return
     */
    Car createMyCar();

}
