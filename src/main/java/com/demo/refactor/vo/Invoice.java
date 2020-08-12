package com.demo.refactor.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 发票
 * @author nijiejie
 */
@Data
public class Invoice {

    /**
     * 顾客
     */
    private String customer;

    /**
     * 表演
     */
    private List<Performance> performances;

    /**
     * 用于方法抽离类中的时间属性
     */
    private Date dueDate;

}
