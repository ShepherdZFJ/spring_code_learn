package com.shepherd.reflect;

import lombok.Data;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/2/2 15:57
 */
//@Data
public class User implements Cloneable{
    private Long id;
    private String name;
    private Integer age;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User();
        user.setAge(10);
        user.setName("shepherd");
        System.out.println(user);
        Object clone = user.clone();
        System.out.println(clone);
    }
}
