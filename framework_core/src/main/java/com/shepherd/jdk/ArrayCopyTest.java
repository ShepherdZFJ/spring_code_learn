package com.shepherd.jdk;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/2/8 14:09
 */
public class ArrayCopyTest {
    /**
     *   复制数组
     *  src 源数组
     *  srcPos 源数组中的起始位置
     *  dest 目标数组
     *  destPos 目标数组中的起始位置
     *  length 要复制的数组元素的数量
     *     public static native void arraycopy(Object src,  int  srcPos,
     *                                         Object dest, int destPos,
     *                                         int length);
     */

    public static void main(String[] args) {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        System.arraycopy(a, 2, a, 3, 3);
        a[2]=99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
