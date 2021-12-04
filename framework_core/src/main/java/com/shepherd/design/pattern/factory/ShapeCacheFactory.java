package com.shepherd.design.pattern.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/12/3 17:09
 */
public class ShapeCacheFactory {
    private static final Map<String, Shape> SHAPE_MAP = new HashMap<>();

    static {
        SHAPE_MAP.put("circle", new Circle());
        SHAPE_MAP.put("square", new Square());
        SHAPE_MAP.put("rectangle", new Rectangle());
    }

    public static Shape createShape(String type) {
        return SHAPE_MAP.get(type);
    }
}
