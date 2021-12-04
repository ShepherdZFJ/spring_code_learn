package com.shepherd.design.pattern.factory;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/12/3 16:50
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("I can draw square....");
    }
}
