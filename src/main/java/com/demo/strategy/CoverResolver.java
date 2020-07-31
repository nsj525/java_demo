package com.demo.strategy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.demo.strategy.service.CoverService;

/**
 * 策略
 * @author nijiejie
 */
@Component
@Lazy(value=false)
public class CoverResolver implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<String, CoverService> logisticsHandlerMap = new HashMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        //拿到所有的ServiceImpl对象
        Map<String, CoverService> beanMap = applicationContext.getBeansOfType(CoverService.class);
        for (String key : beanMap.keySet()) {
            this.logisticsHandlerMap.put(beanMap.get(key).getSign(), beanMap.get(key));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void doCover(String sign,String o){

        CoverService coverService = logisticsHandlerMap.get(sign);
        coverService.cover(o);

    }

}
