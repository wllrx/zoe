package com.x.f;

/**
 * volatile关键字
 * 表示直接操作变量
 *
 * @author zoe
 * @date 2018-12-29
 */
public class Volatile{
    public static void main(String[] args) throws Exception{
        MyThread myThread = new MyThread();
        new Thread(myThread,"票贩子A").start();
        new Thread(myThread,"票贩子B").start();
        new Thread(myThread,"票贩子C").start();
    }

}
class MyThread implements Runnable{
    private volatile int ticket = 10;
    @Override
    public void run() {
        synchronized (this){
            while (ticket > 0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖票操作  ticket = "+ticket--);
            }
        }

    }
}