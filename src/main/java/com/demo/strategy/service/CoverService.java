package com.demo.strategy.service;


/**
 * @author nijiejie
 */
public interface CoverService {

    /**
     * 获取策略的标志
     * @return
     */
    String getSign();

    /**
     * 自定义转化方法
     * @param param
     * @return
     */
    Object cover(String param);

}
