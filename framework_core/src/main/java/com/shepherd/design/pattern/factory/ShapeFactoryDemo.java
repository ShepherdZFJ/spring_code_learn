package com.shepherd.design.pattern.factory;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/12/3 16:56
 */
public class ShapeFactoryDemo {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.createShape("circle");
        circle.draw();
    }
}
