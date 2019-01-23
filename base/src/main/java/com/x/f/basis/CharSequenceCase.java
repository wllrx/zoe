package com.x.f.basis;

/**
 * CharSequence 接口
 * @author zoe
 * @date 2019-01-23
 */
public class CharSequenceCase {
    public static void main(String[] args) {
        CharSequence charSequence = "hello word";//子类实例向父接口转型
        CharSequence sub = charSequence.subSequence(3,6);
        System.out.println(sub);
    }
}
