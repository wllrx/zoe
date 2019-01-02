package com.x.f;

/**
 * 数字加减示例
 *
 * @author zoe
 * @date 2018-12-29
 */
public class AddSubtractCase {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(new AddThread(resource), "加法线程A").start();
        new Thread(new AddThread(resource), "加法线程B").start();
        new Thread(new SubThread(resource), "减法线程C").start();
        new Thread(new SubThread(resource), "减法线程D").start();
    }
}

/**
 * 注:多线程中使用while做判断,不使用if,wait()表示获取到该对象的锁之后,主动释放该对象的锁,同时让本线程休眠
 * wait()释放同步锁,让其他线程进来,当唤醒的线程是多个线程,if()经过单次的判断后不会再进行判断,因为线程被唤醒时就已经处于if的方法体内
 * while()循环则会每次进行判断,直到false跳出循环
 */
class Resource {//定义操作的资源
    private int num = 0;//进行加减的操作数据
    private boolean flag = true; //加减的切换
    //flag = true  进行加法,无法进行减法操作
    //flag = false 进行减法操作,无法进行加法操作
    synchronized void add() throws Exception {
        while (!flag) {
            wait();
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "加法操作  num = " + this.num++);
        this.flag = false;
        notifyAll();
    }

    synchronized void sub() throws Exception {
        while (flag) {
            wait();
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "减法操作  num = " + this.num--);
        this.flag = true;
        notifyAll();
    }
}

class AddThread implements Runnable {

    private Resource resource;

    public AddThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int x = 0; x < 50; x++) {
            try {
                resource.add();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class SubThread implements Runnable {
    private Resource resource;

    public SubThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int x = 0; x < 50; x++) {
            try {
                resource.sub();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}