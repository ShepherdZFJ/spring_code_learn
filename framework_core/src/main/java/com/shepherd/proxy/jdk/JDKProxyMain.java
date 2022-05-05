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
        /**
         * 要求：被代理类至少实现一个接口 为什么？？？
         * 为什么只能代理有接口的类？
         * 是因为JDK动态代理本身机制决定的，首先在java里面动态代理是Proxy.newProxyInstance()这个方法来实现的，
         * 它需要传入被动态代理的一个接口类，还是取决于JDK动态代理的的一个底层实现，JDK动态代理会在程序运行期间，
         * 去动态生产一个代理类，叫$ProxyO,那么这个动态生成的代理类会去继承一个java.lang.reflect.Proxy这样一个类，
         * 同时还会去实现被代理类的接口，在java里面不支持多重继承的，而每个动态代理都继承一个Proxy，所以就导致的JDK里面的动态代理只能代理接口，
         * 而不能代理实现类

         * newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
         * 参数含义：
         *         ClassLoader：和被代理对象使用相同的类加载器。
         *         Interfaces：和被代理对象具有相同的行为。实现相同的接口。
         *         InvocationHandler：如何代理，使用反射执行接口原方法，同时可扩展增强方法，如下面的示例JDKProxyTestInvocationHandler。
         */
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

    //InvocationHandler实现类，使用反射增强方法
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