package com.demo.design.patterns.factory.method;

import com.demo.design.patterns.factory.Benz;
import com.demo.design.patterns.factory.Car;

/**
 * Benz工厂
 * @author nijiejie
 */
public class BenzFactory implements MethodFactory {
    @Override
    public Car createMyCar() {
        return new Benz();
    }
}
