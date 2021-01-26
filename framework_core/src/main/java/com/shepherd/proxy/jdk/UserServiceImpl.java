package com.shepherd.proxy.jdk;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2020/11/2 11:00
 */

public class UserServiceImpl implements UserService {
    @Override
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    @Override
    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}

