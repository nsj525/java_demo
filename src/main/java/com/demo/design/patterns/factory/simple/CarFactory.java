package com.demo.design.patterns.factory.simple;

import com.demo.design.patterns.factory.Audi;
import com.demo.design.patterns.factory.Benz;
import com.demo.design.patterns.factory.Car;

/**
 * 汽车造梦工厂（  简单工厂！！ ）
 * @author nijiejie
 */
public class CarFactory {

    /**
     * 工厂模式，通常都是以这种方式，声明一个工厂类和方法，通过暴露这个方法，影藏具体的实例创建的过程
     *
     * 1、一般只有需要特殊处理的类的实例化需要通过工厂模式（like：单例），类似于下面这种直接new出来的，不建议使用
     * 2、从下面代码可以看到，虽然我们可以创建不同的Car，但是每当我们新增子类的时候，都需要对生产方法进行改造，具体的缺点如下：
     *****2.1违背“开放 - 关闭原则”，一旦添加新产品就不得不修改工厂类的逻辑，这样就会造成工厂逻辑过于复杂。
     *****2.2简单工厂模式由于使用了静态工厂方法，静态方法不能被继承和重写，会造成工厂角色无法形成基于继承的等级结构
     *
     * 作者：Carson_Ho
     * 链接：https://www.jianshu.com/p/e55fbddc071c
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param type
     * @return
     */
    public static Car createMyCar(String type){

        Car car;
        switch (type){
            case "audi":
                car = new Audi();
                break;
            case "benz":
                car = new Benz();
                break;
            default:
                car = null;
        }
        return car;

    }

}
