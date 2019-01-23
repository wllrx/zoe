package com.x.f.basis;

/**
 *
 * @author zoe
 * @date 2019-01-23
 */
public class StringAndBuffer {
    public static void main(String[] args) {
        String str = "hello";
        change(str);
        System.out.println(str);
    }
    public static void change(String temp){
        temp += "world"; //内容无改变
    }
}

/**
 * 使用StringBuffer内容改变
 */
class Buffer{
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("hello");
        change(stringBuffer);
        System.out.println(stringBuffer.toString());
    }
    public static void change(StringBuffer temp){
        temp.append("world");
    }
}