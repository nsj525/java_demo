package com.demo.refactor;

import com.demo.refactor.vo.Employee;

import java.util.Map;

/**
 * 测试"简化条件逻辑" 相关方法实践
 *
 * @author nijiejie
 */
public class TestDemo {

    public static final int NUM = 9;

    /**
     * 假设这是一个学生评分的代码，根据学生的分，给出对应的等级
     *
     * @param temp
     * @param temp2
     * @param temp3
     */
    private void demo1(int temp,int temp2,int temp3){

        if (temp > 9){
            System.out.println("S");
        }else{
            if (temp > 8 && temp2 > 8){
                System.out.println("A");
            }else{
                if (temp > 7){
                   if (temp2 > 8){
                       if (temp3 > 6){
                           System.out.println("B");
                       }else{
                           System.out.println("C");
                       }
                   }else{
                       if (temp3 > 9){
                           System.out.println("B");
                       }else{
                           System.out.println("C");
                       }
                   }
                }else {
                    if (temp > 6){
                        System.out.println("C");
                    }else{
                        System.out.println("D");
                    }
                }
            }
        }

    }

    /**
     * 上述代码，各种逻辑判断嵌套，很容易就会让人看懵逼，拿我们需要如何去优化他呢😏😏😏😏😏
     * @param temp
     * @param temp2
     * @param temp3
     */
    private void demo2(int temp,int temp2,int temp3){

        /**************************************************  step1  *****************************************************
         拆分逻辑，可以整合成下面5个分支走向，分别输出S、A、B、C、D，所以定义下面四个方法 judge1()、judge2()、judge3()、judge4()、judge5()
         if(判断0){ return S}
         if(判断1){ return A}
         if(判断2){ return B}
         if(判断3){ return C}
         if(判断4){ return D}

         这一步的操作，就是一个分解&&合并条件表达式的功能，用过&& 和 || ，把相同结果导向的的判断，拆分合并到同一个地方（提炼函数）
         ****************************************************************************************************************/
        if (judge1(temp)){
            System.out.println("S");
        }
        if (judge2(temp,temp2)){
            System.out.println("A");
        }
        if (judge3(temp,temp2,temp3)){
            System.out.println("B");
        }
        if (judge4(temp,temp2,temp3)){
            System.out.println("C");
        }
        if (judge1(temp)){
            System.out.println("D");
        }


    }

    //这一步逻辑过于简单，本质上可以不抽取方法，这里是为了演示
    private boolean judge1(int temp){
        return temp>9;
    }

    //输出A
    private boolean judge2(int temp,int temp2){
        return (temp > 8 && temp2 > 8);
    }

    //B 必须满足temp>7，temp2>8 && temp3 >6  ,temp2<=8 && temp3>9
    private boolean judge3(int temp,int temp2,int temp3){
        return (temp>7 && ((temp2>8 && temp3>6) || (temp2<=8 && temp3 > 9)));
    }

    //C 6<temp<7 或 temp>7&&((temp2>8 && temp3<=6) || (temp<=8 && temp3>9))
    private boolean judge4(int temp,int temp2,int temp3){
        return ((6<temp && temp<7) || (temp>7 && ((temp2>8 && temp3<=6) || (temp<=8 && temp3>9))));
    }

    //D
    private boolean judge5(int temp){
        return temp<6;
    }


    /*********************************    所谓"卫语句"    *********************************/
    /**
     *  很多时候，我们在写条件语句的时候，会有一个专门的分支来处理某个特定情形下触发的条件
     *  此时，我们大可不必遵循单一出口的实际
     *  而是专门用一段代码处理这种特定的场景，并直接返回数据
     *  音标匹配的问题还需要再对一下，后台传过来的是/ɑ:/,   原本是/ɑː/，两个点号变了
     */

    private int payMoney(Employee employee){

        //单一出口？？？？？？？？
        int result;
        if (employee.isSeparated()){
            result = 0;
        }else if (employee.isRetired()){
            result = 200;
        }else{
            result=500;
        }
        return result;

    }


    private int payMoney1(Employee employee){

        if (employee.isSeparated()){
            return 0;
        }
        if (employee.isRetired()){
            return 200;
        }
        return 500;

    }


    //示例2   capital资本  interestRate贷款利率  duration持续时间  income收入  adjustmentFactor附加费
    private int getMoney(Map<String,Integer> map){

        int result = 0 ;
        if (map.get("capital") > 0){
            if (map.get("interestRate") > 0 && map.get("duration") > 0){
                result = map.get("income") / map.get("duration") * map.get("adjustmentFactor");
            }
        }
        return result;

    }

    //先拆分分支走向
    private int getMoney2(Map<String,Integer> map){
        int result = 0;
        if (map.get("capital")<=0){return result;}
        if (map.get("interestRate") > 0 && map.get("duration") > 0){
            result = map.get("income") / map.get("duration") * map.get("adjustmentFactor");
        }
        return result;
    }

    //翻转条件，并合并
    private int getMoney3(Map<String,Integer> map){
        if (map.get("capital")<=0 || map.get("interestRate") <=0 || map.get("duration")<=0){return 0;}
        return map.get("income") / map.get("duration") * map.get("adjustmentFactor");
    }


}
