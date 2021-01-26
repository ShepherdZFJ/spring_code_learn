package com.shepherd.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2021/1/26 17:41
 */
public class JDKProxyMain {

    public static void main(String[] args) {
        JDKProxyTestInterface target = new JDKProxyTestInterfaceImpl();
        // 根据目标对象创建代理对象
        JDKProxyTestInterface proxy = (JDKProxyTestInterface) Proxy
                        .newProxyInstance(target.getClass().getClassLoader(),
                                          target.getClass().getInterfaces(),
                                          new JDKProxyTestInvocationHandler(target));
        // 调用代理对象方法
        proxy.testProxy();
    }

    //接口
    interface JDKProxyTestInterface {
        void testProxy();
    }

    //接口实现类
    static class JDKProxyTestInterfaceImpl
            implements JDKProxyTestInterface {
        @Override
        public void testProxy() {
            System.out.println("testProxy");
        }
    }

    static class JDKProxyTestInvocationHandler implements InvocationHandler {
        private Object target;
        public JDKProxyTestInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method,
                             Object[] args) throws Throwable {
            System.out.println("执行前：method before");
            Object result = method.invoke(this.target, args);
            System.out.println("执行后：method after");
            return result;
        }
    }
}