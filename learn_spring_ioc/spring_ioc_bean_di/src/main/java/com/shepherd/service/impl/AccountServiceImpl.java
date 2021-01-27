package com.shepherd.service.impl;

import com.shepherd.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {



    public AccountServiceImpl(){
        System.out.println("AccountServiceImpl对象创建了");
    }

    public void  saveAccount(){
        System.out.println("AccountServiceImpl中的saveAccount方法执行了。。。");
    }

    public void  init(){
        System.out.println("AccountServiceImpl对象初始化了。。。");
    }
    public void  destroy(){
        System.out.println("AccountServiceImpl对象销毁了。。。");
    }

}
