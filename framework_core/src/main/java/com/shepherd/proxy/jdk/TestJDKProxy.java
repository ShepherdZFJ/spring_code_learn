package com.shepherd.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2020/11/2 10:58
 */

/**
 * ��̬������⣺
 * AOP�����ع�������java�еĶ�̬������ʵ�ֵġ�˵���ˣ�������Ŀ����Ļ��������������߼���������ǿ��Ŀ���ࣨ�������߼�������Ŀ����
 * ����ִ��֮ǰ������Ŀ���ຯ��ִ��֮�󣬻�����Ŀ���ຯ���׳��쳣ʱ��ִ�С���ͬ������ʱ����Ӧ��ͬ��Interceptor�����࣬
 * ��BeforeAdviseInterceptor��AfterAdviseInterceptor�Լ�ThrowsAdviseInterceptor�ȣ�
 * AOP��Դ�����õ������ֶ�̬������ʵ���������빦�ܣ�jdk��̬�����cglib��̬�������ַ���ͬʱ���ڣ��������ӡ�
 * jdk��̬��������java�ڲ��ķ��������ʵ�ֵģ�cglib��̬����ײ����ǽ���asm��ʵ�ֵġ�
 * �ܵ���˵�����������������Ĺ����бȽϸ�Ч����asm��������֮������ִ�й����бȽϸ�Ч������ͨ����asm���ɵ�����л��棬�������
 * asm��������̵�Ч���⣩������һ�����ע�⣺jdk��̬�����Ӧ��ǰ�ᣬ������Ŀ�������ͳһ�Ľӿڡ����û������ǰ�ᣬjdk��̬������
 * Ӧ�á��ɴ˿��Կ�����jdk��̬������һ���ľ����ԣ�cglib���ֵ��������ʵ�ֵĶ�̬����Ӧ�ø��ӹ㷺������Ч���ϸ������ơ���
 */


/**
 * jdk��̬����ʵ��
 * jdk��̬������jdkԭ����֧�ֵ�һ�ִ���ʽ������ʵ��ԭ������ͨ����target��ʹ�����ʵ��ͬһ�ӿڣ����������target�������ﵽ
 * �������ص����ã�����ͨ���ӿڵķ�ʽ�������׶ˣ�һ���Ǳ��뱣֤target���нӿڣ��ڶ����������Ҫ��target��ķ������д������أ�
 * ��ô��Ҫ��֤��Щ������Ҫ�ڽӿ���������ʵ������΢�е����ơ�
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

