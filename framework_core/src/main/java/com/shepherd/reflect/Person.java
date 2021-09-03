package com.shepherd.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/8/20 11:22
 */
@Data
public class Person {
    private String name;
    private Integer age;
    public Date birthday;
    private BigDecimal amount;

    public Person(){}

    public Person(String name, Integer age, Date birthday, BigDecimal amount) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.amount = amount;
    }

    public void eat(String food) {
        System.out.println("eat " + food);
    }

    public String getAddress(String province, String city, String region) throws IOException {
        StringBuilder address = new StringBuilder();
        address.append(province).append(city).append(region);
        return address.toString();
    }
}
