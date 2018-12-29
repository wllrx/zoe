package com.x.f;

public class Producer {
        public static void main(String[] args) throws Exception{
            Message message = new Message();
            new Thread(new Product(message),"生产者").start();//启动生产者线程
            new Thread(new Consumer(message),"消费者").start();//启动消费者线程
        }
}
class Message{ //消息中心
    private String title;
    private String content;
    private boolean flag = true;//表示生产或者消费的形式
    // flag = true 允许生产,不允许消费  flag= false 允许消费,不允许生产
    synchronized void set(String title, String content){
        if (!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.title = title;
        this.content = content;
        this.flag = false; //生产完成
        notify();//唤醒等待的线程
    }
    synchronized String get(){
        if (flag == true){
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            return this.title + "           ------------       "+this.content;
        }finally {
            this.flag = true; // 可继续生产
            notify();//唤醒等待线程
        }
    }
}
class Product implements Runnable{//生产者

    private Message message;

    Product(Message message) {
        this.message = message;
    }

    public void run() {
        for (int x = 0 ; x < 100 ; x++){
            if (x % 2 == 0){
                this.message.set("生产者","生产");
            }else {
                this.message.set("消费者","消费");
            }
        }
    }
}
class Consumer implements Runnable{//消费者

private Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    public void run() {
        for (int x = 0 ; x < 100 ; x++){
            System.out.println(this.message.get());
        }
    }
}