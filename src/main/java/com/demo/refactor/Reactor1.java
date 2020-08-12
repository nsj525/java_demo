package com.demo.refactor;

import com.demo.refactor.vo.Invoice;
import com.demo.refactor.vo.Performance;
import com.demo.refactor.vo.Play;
import com.demo.utils.ClassInitUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 方法抽离
 * @author nijiejie
 */
public class Reactor1 {

    public String statement(Invoice invoice, Map<String, Play> playMap) throws Exception {
        //总金额
        int totalAmount = 0;
        //积分
        int volumeCredits = 0;

        String result = "Statement for " + invoice.getCustomer() + "\n";
        //金额格式化为两位小数
        DecimalFormat format = new DecimalFormat("0.00");

        //遍历参数进入的所有发票信息，包含的表演和价格
        for (Performance perf : invoice.getPerformances()) {

            //调用抽离的方法
            int thisAmount = this.amountFor(perf,playMap);

            volumeCredits += Math.max(perf.getAudience() - 30, 0);

            if ("comedy".equals(playFor(playMap,perf).getType()) ) {
                volumeCredits += Math.floor(perf.getAudience() / 5);
            }

            result += playFor(playMap,perf).getName() + ": $" + format.format(thisAmount / 100) + " (" + perf.getAudience() + " seats)\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is ${" + format.format(totalAmount / 100) + "}\n";
        result += "You earned " + volumeCredits + " credits\n";
        return result;
    }

    /**
     * 1、抽离的方法
     * 2、修改了返回值 thisAmount  =====>  result       (固定的方法返回，统一为 result / data 等)
     * 3、修改了参数名 perf  ======>   aPerformance     ('缩写' 改成 完整名词直译)
     * 4、参照上下文，减少具有局部作用域的临时变量，移除局部变量play，改成直接传入playMap
     * 5、定义方法playFor，获取play对象，内联调用
     *
     *
     *
     * @param aPerformance
     * @param playMap
     * @return
     * @throws Exception
     */
    public int amountFor(Performance aPerformance, Map<String, Play> playMap) throws Exception{
    // public int amountFor(Performance aPerformance,Play play) throws Exception{

        int result = 0;

        switch (playFor(playMap,aPerformance).getType()) {
            case "tragedy":
                result = 40000;
                if (aPerformance.getAudience() > 30) {
                    result += 1000 * (aPerformance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.getAudience() > 20) {
                    result += 10000 + 500 * (aPerformance.getAudience() - 20);
                }
                result += 300 * aPerformance.getAudience();
                break;
            default:
                throw new Exception("unknown type: $ {" + playFor(playMap,aPerformance) + "}");
        }

        return result;
    }

    public Play playFor(Map<String, Play> playMap,Performance aPerformance){

        return playMap.get(aPerformance.getPlayId());

    }

    public static void main(String[] args) throws Exception {
        Reactor reactor = new Reactor();

        Invoice invoice = ClassInitUtil.getInvoice();
        HashMap<String, Play> playHashMap = ClassInitUtil.getStringPlayHashMap();

        String statement = reactor.statement(invoice, playHashMap);
        System.out.println(statement);
    }

}
