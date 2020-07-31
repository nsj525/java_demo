package com.demo.aop;

import org.springframework.stereotype.Component;

/**
 * !!!  Spring Aop 必须在xml配置中配置切点 | 或在切面中通过@Pointcat("@annotation(com.demo.aop.TryImport)")
 * 相对xml配置较为通用，切面中注解配置只适用于此切面方法
 *
 * @author nijiejie
 */
@Component
public class TestDemoDto {

    @TryImport(key = "ceshi",value = "123")
    public String tryImport() {
        return "返回值是我";
    }

}
