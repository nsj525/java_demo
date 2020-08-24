package com.demo.design.patterns.factory;

import java.math.BigDecimal;

/**
 * 父类Car
 */
public abstract class Car {

    public String name;
    public BigDecimal money;

    /**
     * 抽象方法
     */
    public abstract void useCar();

}
