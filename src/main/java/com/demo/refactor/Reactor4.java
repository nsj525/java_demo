package com.demo.refactor;

import com.demo.refactor.vo.Invoice;
import com.demo.refactor.vo.Performance;
import com.demo.refactor.vo.Play;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.demo.utils.ClassInitUtil.getInvoice;
import static com.demo.utils.ClassInitUtil.getStringPlayHashMap;


/**
 * @author:zhaoxiaodong
 * @date: 2020/7/31
 * @time: 11:15 上午
 */

public class Reactor4 {

    DecimalFormat format = new DecimalFormat("0.00");

    /**
     * 实现接口 策略化
     * @param invoice
     * @param playMap
     * @return
     * @throws Exception
     */
    public String statement(Invoice invoice, Map<String, Play> playMap) throws Exception {
        StateMentData stateMentData = new StateMentData();

        stateMentData.setCustomer(invoice.getCustomer());
        stateMentData.setPerformances(invoice.getPerformances());
        stateMentData.setTotalAmount(totalAmount(invoice, playMap));
        stateMentData.setTotalVolumeCredits(totalVolumeCredits(stateMentData.getPerformances(), playMap));

        return renderPainText(stateMentData, playMap);
    }

    private String renderPainText(StateMentData statement, Map<String, Play> playMap) throws Exception {
        String result = "Statement for " + statement.getCustomer() + "\n";
        for (Performance perf : statement.getPerformances()) {
            result += playMap.get(perf.getPlayId()).getName() + ": $" + format.format(amountFor(perf, playMap.get(perf.getPlayId())) / 100) + " (" + perf.getAudience() + " seats)\n";
        }
        result += "Amount owed is ${" + format.format(statement.getTotalAmount() / 100) + "}\n";
        result += "You earned " + totalVolumeCredits(statement.getPerformances(), playMap) + " credits\n";
        return result;
    }

    private int totalVolumeCredits(List<Performance> performances, Map<String, Play> playMap) {
        int volumeCredits = 0;
        for (Performance perf : performances) {
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
        Reactor4 reactor = new Reactor4();

        Invoice invoice = getInvoice();
        HashMap<String, Play> playHashMap = getStringPlayHashMap();

        String statement = reactor.statement(invoice, playHashMap);
        System.out.println(statement);
    }


}

interface coculator{

    int coculateAmount(Performance perf);

    int coculateCredit(Play play,Performance perf);

}

class Comedy extends Play implements coculator{

    @Override
    public int coculateAmount(Performance perf) {

        int thisAmount = 40000;
        if (perf.getAudience() > 30) {
            thisAmount += 1000 * (perf.getAudience() - 30);
        }
        return thisAmount;
    }

    @Override
    public int coculateCredit(Play play, Performance perf) {
        int volumeCredits = Math.max(perf.getAudience() - 30, 0);
        // add extra credit for every ten comedy attendees
        volumeCredits += Math.floor(perf.getAudience() / 5);
        return volumeCredits;
    }
}


class Tragedy extends Play implements coculator{

    @Override
    public int coculateAmount(Performance perf) {

        int thisAmount = 30000;
        if (perf.getAudience() > 20) {
            thisAmount += 10000 + 500 * (perf.getAudience() - 20);
        }
        thisAmount += 300 * perf.getAudience();
        return thisAmount;
    }

    @Override
    public int coculateCredit(Play play, Performance perf) {
        int volumeCredits = Math.max(perf.getAudience() - 30, 0);
        // add extra credit for every ten comedy attendees
        volumeCredits += Math.floor(perf.getAudience() / 5);
        return volumeCredits;
    }
}