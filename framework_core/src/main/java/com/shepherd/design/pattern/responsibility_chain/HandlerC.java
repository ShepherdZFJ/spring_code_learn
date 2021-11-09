package com.shepherd.design.pattern.responsibility_chain;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/11/9 15:34
 */

/**
 * 这里可以写handlerC具体处理逻辑，最终返回处理是否成功
 */
public class HandlerC extends Handler{
    @Override
    protected Boolean doHandle() {
        System.out.println("handlerC execute.....");
        return true;
    }
}
