package com.demo.refactor.vo;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试"简化条件逻辑-引入特例【对象封装】"
 *
 * @author nijiejie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentBody {

    public Integer A;
    public Integer B;
    public String C;
    public Integer D;

    public ParentBody checkIsExit(Integer type){

        if (null == type){
            return new ChildBody();
        }

        return new ParentBody(type,1,"haha",1);

    }

    @Data
    @Builder
    class ChildBody extends ParentBody{

        public ChildBody() {
            this.setA(-1);
            this.setB(-1);
            this.setC("未知类型");
            this.setA(-1);
            Arrays.sort(new int[]{1,2,3});
        }

        // TODO: 2020/11/13 设置行为...


    }

}
