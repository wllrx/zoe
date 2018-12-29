package com.x.f;

/**
 * @author zoe
 * @date 2018-12-29
 */
public class Daemon {
    public static void main(String[] args) throws Exception{
       Thread userThread = new Thread(() -> {
           for (int x = 0 ; x < 10 ; x++) {
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread().getName() + "正在执行  x = " + x);
              }
            },"用户执行线程");
        Thread daemonThread = new Thread(() -> {
            for (int x = 0 ; x < Integer.MAX_VALUE ; x++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在执行  x = " + x);
            }
        },"用户守护线程");
        daemonThread.setDaemon(true);//将daemonThread
        userThread.start();
        daemonThread.start();
    }
}
