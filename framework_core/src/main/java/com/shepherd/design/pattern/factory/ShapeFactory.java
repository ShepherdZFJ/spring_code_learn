package com.shepherd.design.pattern.factory;

import java.util.Objects;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/12/3 16:52
 */
public class ShapeFactory {

    public static Shape createShape(String type) {
        if (Objects.equals("circle", type)) {
            return new Circle();
        } else if (Objects.equals("square", type)) {
            return new Square();
        } else if (Objects.equals("rectangle", type)) {
            return new Rectangle();
        }
        return null;

    }
}
