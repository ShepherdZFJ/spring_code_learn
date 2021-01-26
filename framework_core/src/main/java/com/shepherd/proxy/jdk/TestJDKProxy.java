package com.shepherd.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2020/11/2 10:58
 */

/**
 * 动态代理详解：
 * AOP的拦截功能是由java中的动态代理来实现的。说白了，就是在目标类的基础上增加切面逻辑，生成增强的目标类（该切面逻辑或者在目标类
 * 函数执行之前，或者目标类函数执行之后，或者在目标类函数抛出异常时候执行。不同的切入时机对应不同的Interceptor的种类，
 * 如BeforeAdviseInterceptor，AfterAdviseInterceptor以及ThrowsAdviseInterceptor等）
 * AOP的源码中用到了两种动态代理来实现拦截切入功能：jdk动态代理和cglib动态代理。两种方法同时存在，各有优劣。
 * jdk动态代理是由java内部的反射机制来实现的，cglib动态代理底层则是借助asm来实现的。
 * 总的来说，反射机制在生成类的过程中比较高效，而asm在生成类之后的相关执行过程中比较高效（可以通过将asm生成的类进行缓存，这样解决
 * asm生成类过程低效问题）。还有一点必须注意：jdk动态代理的应用前提，必须是目标类基于统一的接口。如果没有上述前提，jdk动态代理不能
 * 应用。由此可以看出，jdk动态代理有一定的局限性，cglib这种第三方类库实现的动态代理应用更加广泛，且在效率上更有优势。。
 */


/**
 * jdk动态代理实现
 * jdk动态代理是jdk原生就支持的一种代理方式，它的实现原理，就是通过让target类和代理类实现同一接口，代理类持有target对象，来达到
 * 方法拦截的作用，这样通过接口的方式有两个弊端，一个是必须保证target类有接口，第二个是如果想要对target类的方法进行代理拦截，
 * 那么就要保证这些方法都要在接口中声明，实现上略微有点限制。
 */
public class TestJDKProxy {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        InvocationHandler invocationHandler = new MyInvocationHandler(userService);
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), invocationHandler);
        System.out.println(userServiceProxy.getName(1));
        System.out.println(userServiceProxy.getAge(1));
    }
}

