package com.x.f;

/**
 * 线程停止
 *
 * @author zoe
 * @date 2018-12-29
 */
public class StopThread {
    public static boolean flag = true;
    public static void main(String[] args) throws Exception{
        new Thread(() -> {
           long num = 0;
            while (flag){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在执行  num = " + num++);
            }
        },"执行线程").start();
        Thread.sleep(200);//线程运行200毫秒
        flag = false;//停止线程
    }
}
