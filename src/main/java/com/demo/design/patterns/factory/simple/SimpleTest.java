package com.demo.design.patterns.factory.simple;

import com.demo.design.patterns.factory.Car;

public class SimpleTest {

    public static void main(String[] args) {
        Car car = CarFactory.createMyCar("benz");
        car.useCar();
    }

}
