package com.test;

import com.demo.refactor.Reactor4;
import com.demo.refactor.vo.Invoice;
import com.demo.refactor.vo.Play;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static com.demo.utils.ClassInitUtil.getInvoice;
import static com.demo.utils.ClassInitUtil.getStringPlayHashMap;

public class ReactorTest extends BaseTest{

    @Autowired
    private Reactor4 reactor;

    @Test
    public void testMain() throws Exception {
        Reactor4 reactor = new Reactor4();

        Invoice invoice = getInvoice();
        HashMap<String, Play> playHashMap = getStringPlayHashMap();

        String statement = reactor.statement(invoice, playHashMap);
        System.out.println(statement);
    }

}
