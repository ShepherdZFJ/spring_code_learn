package com.shepherd.proxy.cglib;


import com.shepherd.proxy.jdk.UserService;
import com.shepherd.proxy.jdk.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2020/11/2 13:43
 */
public class TestCglibProxy {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);
        UserService o = (UserService)enhancer.create();
        o.getName(1);
        o.getAge(1);
    }

}
