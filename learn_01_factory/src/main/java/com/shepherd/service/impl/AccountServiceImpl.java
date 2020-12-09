package com.shepherd.service.impl;

import com.shepherd.dao.IAccountDao;
import com.shepherd.factory.BeanFactory;
import com.shepherd.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

//    private IAccountDao accountDao = new AccountDaoImpl();

    private IAccountDao accountDao = (IAccountDao)BeanFactory.getBean("accountDao");

//    private int i = 1;

    public void  saveAccount(){
        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
