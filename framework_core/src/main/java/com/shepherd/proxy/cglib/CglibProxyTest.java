package com.shepherd.proxy.cglib;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2021/1/26 18:00
 */
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
public class CglibProxyTest {

    static class CglibProxyService {
        public  CglibProxyService(){
        }
        void sayHello(){
            System.out.println(" hello !");
        }
    }

    static class CglibProxyInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object sub, Method method,
                                Object[] objects, MethodProxy methodProxy)
                throws Throwable {
            System.out.println("before hello");
            Object object = methodProxy.invokeSuper(sub, objects);
            System.out.println("after hello");
            return object;
        }
    }

    public static void main(String[] args) {
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(CglibProxyService.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new CglibProxyInterceptor());
        // 创建代理对象
        CglibProxyService proxy= (CglibProxyService)enhancer.create();
        System.out.println(CglibProxyService.class);
        System.out.println(proxy.getClass());
        // 通过代理对象调用目标方法
        proxy.sayHello();
    }
}
