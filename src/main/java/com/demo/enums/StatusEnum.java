package com.demo.enums;

import lombok.Getter;

/**
 * 系统状态枚举
 * @author nijiejie
 */
public enum StatusEnum {

    OK(0,"成功"),
    FAIL(-1,"失败");

    @Getter
    private Integer type;

    @Getter
    private String masg;

    StatusEnum(Integer type ,String masg) {
        this.type = type;
        this.masg = masg;
    }
}
