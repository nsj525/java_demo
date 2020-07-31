package com.demo.refactor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

public class Reactor {
    public String statement(Invoice invoice, Map<String, Play> playMap) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = "Statement for " + invoice.getCustomer() + "\n";
        DecimalFormat format = new DecimalFormat("0.00");
        for (Performance perf : invoice.getPerformances()) {
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
            if ("comedy" == play.getType()) {
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

        Invoice invoice = getInvoice();
        HashMap<String, Play> playHashMap = getStringPlayHashMap();

        String statement = reactor.statement(invoice, playHashMap);
        System.out.println(statement);
        // Statement for BigCo
        // Hamlet: $650 (55 seats)
        // As You Like It: $580 (35 seats)
        // Othello: $500 (40 seats)
        // Amount owed is ${1730}
        // You earned 47 credits
    }

    public static Invoice getInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomer("BigCo");
        ArrayList<Performance> performances = new ArrayList<>();

        Performance performance1 = new Performance();
        performance1.setPlayId("Hamlet");
        performance1.setAudience(55);
        performances.add(performance1);

        Performance performance2 = new Performance();
        performance2.setPlayId("As You Like It");
        performance2.setAudience(35);
        performances.add(performance2);

        Performance performance3 = new Performance();
        performance3.setPlayId("Othello");
        performance3.setAudience(40);
        performances.add(performance3);
        invoice.setPerformances(performances);
        return invoice;
    }

    public static HashMap<String, Play> getStringPlayHashMap() {
        HashMap<String, Play> playHashMap = new HashMap<>();
        Play play1 = new Play();
        Play play2 = new Play();
        Play play3 = new Play();

        play1.setName("Hamlet");
        play2.setName("As You Like It");
        play3.setName("Othello");

        play1.setType("tragedy");
        play2.setType("comedy");
        play3.setType("tragedy");

        playHashMap.put(play1.getName(), play1);
        playHashMap.put(play2.getName(), play2);
        playHashMap.put(play3.getName(), play3);
        return playHashMap;
    }
}

@Data
class Performance {
    private String playId;
    private Integer audience;
}

@Data
class Play {
    private String name;
    private String type;
}

@Data
class Invoice {
    private String customer;
    private List<Performance> performances;
}