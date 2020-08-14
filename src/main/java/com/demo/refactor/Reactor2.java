package com.demo.refactor;

import com.demo.refactor.vo.Invoice;
import com.demo.refactor.vo.Performance;
import com.demo.refactor.vo.Play;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static com.demo.utils.ClassInitUtil.getInvoice;
import static com.demo.utils.ClassInitUtil.getStringPlayHashMap;

/**
 * @author nijiejie
 * @date: 2020/7/31
 * @time: 11:15 上午
 */
public class Reactor2 {

    private final DecimalFormat format = new DecimalFormat("0.00");

    /**
     * 提炼函数 + 拆分循环
     * 主函数只剩下7行了，呜哇！！！
     * @param invoice
     * @param playMap
     * @return
     * @throws Exception
     */
    public String statement(Invoice invoice, Map<String, Play> playMap) throws Exception {
        String result = "Statement for " + invoice.getCustomer() + "\n";
        for (Performance perf : invoice.getPerformances()) {
            result += playMap.get(perf.getPlayId()).getName() + ": $" + format.format(amountFor(perf, playMap.get(perf.getPlayId())) / 100) + " (" + perf.getAudience() + " seats)\n";
        }
        result += "Amount owed is ${" + format.format(totalAmount(invoice, playMap) / 100) + "}\n";
        result += "You earned " + totalVolumeCredits(invoice, playMap) + " credits\n";
        return result;
    }

    private int totalVolumeCredits(Invoice invoice, Map<String, Play> playMap) {
        int volumeCredits = 0;
        for (Performance perf : invoice.getPerformances()) {
            volumeCredits += volumeCreditFor(playMap, perf);
        }
        return volumeCredits;
    }

    private int totalAmount(Invoice invoice, Map<String, Play> playMap) throws Exception {
        int totalAmount = 0;
        for (Performance perf : invoice.getPerformances()) {
            int thisAmount = amountFor(perf, playMap.get(perf.getPlayId()));
            totalAmount += thisAmount;
        }
        return totalAmount;
    }

    private int volumeCreditFor(Map<String, Play> playMap, Performance perf) {
        int volumeCredits = Math.max(perf.getAudience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy" == playMap.get(perf.getPlayId()).getType()) {
            volumeCredits += Math.floor(perf.getAudience() / 5);
        }
        return volumeCredits;
    }

    /**
     * 提炼函数
     * @param perf
     * @param play
     * @return
     * @throws Exception
     */
    private int amountFor(Performance perf, Play play) throws Exception {
        int thisAmount;
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
        return thisAmount;
    }


    public static void main(String[] args) throws Exception {
        Reactor2 reactor = new Reactor2();

        Invoice invoice = getInvoice();
        HashMap<String, Play> playHashMap = getStringPlayHashMap();

        String statement = reactor.statement(invoice, playHashMap);
        System.out.println(statement);
        // Statement for BigCo
        // Hamlet: $650 (55 seats)
        // As You Like It: $580 (35 seats)
        // Othello: $500 (40 seats)
        // Amount owed is ${1730}
        // You earned 47 credits
    }

}