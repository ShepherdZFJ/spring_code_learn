package com.shepherd.design.pattern.factory;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/12/3 17:28
 */
public class RectangleShapeFactory implements IShapeFactory{
    @Override
    public Shape createShape() {
        return new Rectangle();
    }
}
