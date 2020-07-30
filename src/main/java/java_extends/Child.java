package java_extends;


/**
 * @author nijiejie
 */
public class Child extends Parent {

    /**
     * 这里是重写父类方法，使用super关键字调用父类方法
     */
    @Override
    void doSomething(){

        super.doSomething();
        System.out.println("快乐的生活着");

    }

    /**
     * 重载本类同名方法，如有相同逻辑，可使用this关键字复用
     * @param str
     */
    void doSomething(String str){
        this.doSomething();
        System.out.println("每天都在快乐的"+str);
    }

    /**
     * 重载本类方法，注意，此方法返回值不是void类型，原则上重载和方法返回值无关
     * @param a
     * @return
     */
    String doSomething(int a ){

        String str = "快乐指数+"+a;
        return str;

    }

}
