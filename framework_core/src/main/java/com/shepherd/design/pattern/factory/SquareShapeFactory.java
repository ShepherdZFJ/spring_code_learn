package com.shepherd.design.pattern.factory;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/12/3 17:27
 */
public class SquareShapeFactory implements IShapeFactory{
    @Override
    public Shape createShape() {
        return new Square();
    }
}
