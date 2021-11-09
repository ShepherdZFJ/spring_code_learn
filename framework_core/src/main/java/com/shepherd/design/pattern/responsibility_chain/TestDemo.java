package com.shepherd.design.pattern.responsibility_chain;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/11/9 15:18
 */

/**
 * 这里模拟一个产品在流水线上加工流程，如果在某个加工人员出错，就不再流向下一个加工人员
 * 往责任链chain中添加加工人员handler
 */
public class TestDemo {
    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.addHandler(new HandlerC());
        chain.handle();
    }
}
