package com.shepherd.ui;

import com.shepherd.entity.User;
import com.shepherd.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取Bean对象
        IAccountService as  = (IAccountService)ac.getBean("accountService");
        as.saveAccount();

        //下面测试依赖注入DI
        //1.构造方法注入
        User user = (User)ac.getBean("user");
        System.out.println("user:" + user);

        //2.setter方法注入
        User user2 = (User)ac.getBean("user2");
        System.out.println("user2:"+ user2);

        //集合类型注入
        User user3 = (User)ac.getBean("user3");
        System.out.println("user3:" + user3);


        //手动关闭容器
        ac.close();
    }
}
