package com.demo.design.patterns.factory.method;

import com.demo.design.patterns.factory.Audi;
import com.demo.design.patterns.factory.Car;

/**
 * Audi工厂
 * @author nijiejie
 */
public class AudiFactory implements MethodFactory {
    @Override
    public Car createMyCar() {
        return new Audi();
    }
}
