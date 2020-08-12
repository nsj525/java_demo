package com.demo.refactor;

import com.demo.refactor.vo.Invoice;
import com.demo.refactor.vo.Performance;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 方法抽离
 * @Author nijiejie
 */
public class MethodPullAway {

    /**
     * 原始方法
     * @param invoice
     */
    public static void firstMethod(Invoice invoice){

        int outstanding = 0;

        System.out.println("************************************************************");
        System.out.println("********************* Customer owes ************************");
        System.out.println("************************************************************");

        for (Performance performance : invoice.getPerformances()){
            outstanding += performance.getAudience();
        }

        Date today = new Date();
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 30);
        today = ca.getTime();
        invoice.setDueDate(today);

        System.out.println("name:"+invoice.getCustomer());
        System.out.println("amount:"+outstanding);
        System.out.println("due:"+invoice.getDueDate());


    }

    /***************    我    ***********    是   *****************   分   ****************   割   **************   线   **************/

    /**
     * 第一步简单的抽离打印语句，分别放到不同的方法中
     * @param invoice
     */
    public static void secondMethod(Invoice invoice){

        int outstanding = 0;

        printLogo();

        for (Performance performance : invoice.getPerformances()){
            outstanding += performance.getAudience();
        }

        recordDueDate(invoice);

        printDetail(invoice,outstanding);

    }

    public static void printLogo(){

        System.out.println("************************************************************");
        System.out.println("********************* Customer owes ************************");
        System.out.println("************************************************************");

    }

    public static void recordDueDate(Invoice invoice){
        Date today = new Date();
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 30);
        today = ca.getTime();
        invoice.setDueDate(today);
    }

    public static void printDetail(Invoice invoice,int outstanding){
        System.out.println("name:"+invoice.getCustomer());
        System.out.println("amount:"+outstanding);
        System.out.println("due:"+invoice.getDueDate());
    }



    /***************    我    ***********    是   *****************   分   ****************   割   **************   线   **************/


    /**
     * 模拟存在一个方法，对现有的值做出了修改，那就需要尝试把这个值的定义和改变值的动作，放到一个新的方法中，避免
     * @param invoice
     */
    public static void thirdMethod(Invoice invoice){

        printLogo();

        //不允许修改
        final int outstanding = productOutstanding(invoice);

        recordDueDate(invoice);

        printDetail(invoice,outstanding);

    }

    public static int productOutstanding(Invoice invoice){
        int result = 0;
        for (Performance performance : invoice.getPerformances()){
            result += performance.getAudience();
        }
        return result;
    }


    public static void main(String[] args) {

        Invoice invoice = new Invoice();
        invoice.setCustomer("测试小杰");

        Performance performance1 = new Performance();
        performance1.setAudience(1);

        Performance performance2 = new Performance();
        performance2.setAudience(2);

        Performance performance3 = new Performance();
        performance3.setAudience(3);

        List<Performance> list = Arrays.asList(performance1,performance2,performance3);

        invoice.setPerformances(list);

        firstMethod(invoice);
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println();
        secondMethod(invoice);
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println();
        thirdMethod(invoice);

    }

}
