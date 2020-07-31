package com.demo.generic;

import com.demo.enums.StatusEnum;

/**
 * 泛型使用
 * web业务统一返回
 * @author nijiejie
 */
public class WebResponse<T> {

    private Integer status;

    private String msg;

    private T data;

    WebResponse ok(){
        this.status = StatusEnum.OK.getType();
        this.msg = StatusEnum.OK.getMasg();
        return this;
    }

    WebResponse ok(T data){
        this.status = StatusEnum.OK.getType();
        this.msg = StatusEnum.OK.getMasg();
        this.data = data;
        return this;
    }

    WebResponse ok(StatusEnum statusEnum ,T data ){
        this.status = statusEnum.getType();
        this.msg = statusEnum.getMasg();
        this.data = data;
        return this;
    }

    WebResponse fail(){
        this.status = StatusEnum.FAIL.getType();
        this.msg = StatusEnum.FAIL.getMasg();
        return this;
    }

    WebResponse fail(StatusEnum statusEnum){
        this.status = statusEnum.getType();
        this.msg = statusEnum.getMasg();
        return this;
    }

    /**
     * .................等等类似的方法..................
     */

}
