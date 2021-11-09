package com.shepherd.design.pattern.responsibility_chain;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/11/9 15:17
 */

/**
 * HandlerChain 是加工人员处理链，从数据结构的角度来看，它就是一个记录了链头、链尾的链表。其中，记录链尾是为了方便添加加工人员
 */
public class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        handler.setNext(null);
        //第一次添加具体处理类时，头、尾都等于该类
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        //不是第一添加，该类设置tail的下一环节，将尾部设置该类，最终形成一个链表结构
        tail.setNext(handler);
        tail = handler;
    }

    public void handle() {
        //从第一个开始执行
        if (head != null) {
            head.handle();
        }
    }
}