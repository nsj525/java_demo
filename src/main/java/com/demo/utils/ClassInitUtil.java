package com.demo.utils;

import com.demo.refactor.vo.Invoice;
import com.demo.refactor.vo.Performance;
import com.demo.refactor.vo.Play;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassInitUtil {

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

}