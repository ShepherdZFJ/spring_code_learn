package com.shepherd.design.pattern.responsibility_chain;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2021/11/9 15:15
 */

public abstract class Handler {
    //successor代表责任链中下一环节，eg：这里代表流水线上下一加工人员
    protected Handler next = null;

    public void setNext(Handler next) {
        this.next = next;
    }

    //final修饰不能重写覆盖此方法
    public final void handle() {
        //执行责任链的具体处理类方法
        boolean handled = doHandle();
        //判断chain中当前环节是否执行成功和chain是有下一环节
        if (next != null && handled) {
            next.handle();
        }
    }

    protected abstract Boolean doHandle();
}










