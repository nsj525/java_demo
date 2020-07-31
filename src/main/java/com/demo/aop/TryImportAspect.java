package com.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author nijiejie
 */
@Component
@Order(30)
@Aspect
public class TryImportAspect {

    @Pointcut("@annotation(com.demo.aop.TryImport)")
    public void a(){
        System.out.println("-------注解式定义切点------");
    }

    @Around("@annotation(com.demo.aop.TryImport)")
    private Object importSomething(ProceedingJoinPoint pjp) throws Throwable{

        //获取添加了此注解的方法
        Method m = ((MethodSignature) pjp.getSignature()).getMethod();
        Method method = pjp.getTarget().getClass().getDeclaredMethod(pjp.getSignature().getName(),m.getParameterTypes());
        //获取注解对象
        TryImport tryImport = method.getDeclaredAnnotation(com.demo.aop.TryImport.class);
        String key = tryImport.key();
        String value = tryImport.value();
        Boolean isSet = tryImport.isSet();
        if (isSet){
            String str = key + '_' + value;
            return str;
        }
        //继续执行此方法，可通过Object对象接收方法的返回值
        // Object obj = pjp.proceed();
        return null;
    }

}
