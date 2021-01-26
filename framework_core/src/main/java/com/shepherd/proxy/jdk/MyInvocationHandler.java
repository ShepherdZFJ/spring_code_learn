package com.shepherd.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2020/11/2 10:53
 */

public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    MyInvocationHandler() {
        super();
    }
    MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if ("getName".equals(method.getName())) {
            System.out.println("++++++before " + method.getName() + "++++++");
            Object result = method.invoke(target, args);
            System.out.println("++++++after " + method.getName() + "++++++");
            return result;
        } else {
            Object result = method.invoke(target, args);
            return result;
        }

    }
}