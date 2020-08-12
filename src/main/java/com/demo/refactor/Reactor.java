package com.demo.refactor;


import com.demo.refactor.vo.Invoice;
import com.demo.refactor.vo.Performance;
import com.demo.refactor.vo.Play;
import com.demo.utils.ClassInitUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Reactor {
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
            //获取对应的表演
            Play play = playMap.get(perf.getPlayId());
            int thisAmount = 0;
            switch (play.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                default:
                    throw new Exception("unknown type: $ {" + play.getType() + "}");
            }
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.getType()) ) {
                volumeCredits += Math.floor(perf.getAudience() / 5);
            }
            // print line for this order
            result += play.getName() + ": $" + format.format(thisAmount / 100) + " (" + perf.getAudience() + " seats)\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is ${" + format.format(totalAmount / 100) + "}\n";
        result += "You earned " + volumeCredits + " credits\n";
        return result;
    }


    public static void main(String[] args) throws Exception {
        Reactor reactor = new Reactor();

        Invoice invoice = ClassInitUtil.getInvoice();
        HashMap<String, Play> playHashMap = ClassInitUtil.getStringPlayHashMap();

        String statement = reactor.statement(invoice, playHashMap);
        System.out.println(statement);
    }



}