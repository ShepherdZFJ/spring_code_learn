package com.shepherd.design.pattern.factory;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/12/3 16:48
 */
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println(" I can draw circle....");
    }
}
