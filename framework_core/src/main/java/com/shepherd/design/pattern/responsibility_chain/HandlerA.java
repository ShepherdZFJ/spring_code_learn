package com.shepherd.design.pattern.responsibility_chain;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/11/9 15:16
 */

/**
 * 这里可以写handlerA具体处理逻辑，最终返回处理是否成功
 */
public class HandlerA extends Handler {
    @Override
    public Boolean doHandle() {
        System.out.println("handlerA execute.....");
        return true;

    }
}
