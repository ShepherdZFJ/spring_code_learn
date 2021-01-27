package com.shepherd.entity;

import lombok.Data;

import java.util.*;

/**
 * @author fjZheng
 * @version 1.0
 * @date 2021/1/27 20:29
 */
@Data
public class User {
    private String name;
    private Integer age;
    private Date birthday;

    //集合类型
    private String[] arrays;
    private List<String> list;
    private Set<String> set;
    private Map<String,String> map;
    private Properties properties;

    public User() {}

    public User(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
}
