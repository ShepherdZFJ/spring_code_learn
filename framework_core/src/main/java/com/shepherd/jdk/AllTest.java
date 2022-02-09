package com.shepherd.jdk;

import com.shepherd.reflect.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/2/8 14:18
 */
public class AllTest {

    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };





    public static void main(String[] args) {
        int n = 4;
        int hash = 11;
        int ind = (n-1) & hash;
        System.out.println(ind);
        System.out.println(6<<1);
//        Map<String, String> map = new ConcurrentHashMap<>(16);
//        map.put("hello", null);
//        System.out.println(map);
        User user = new User();
        user.setAge(null);
        System.out.println(user.getAge() == 200);




    }
}
