package com.x.f;

/**
 * 生产电脑示例
 * @author zoe
 * @date 2019-01-17
 */
public class ComputerCase {
    public static void main(String[] args) {
        Resource1 resource1 = new Resource1();
        new Thread(new Pro(resource1)).start();
        new Thread(new Con(resource1)).start();
    }
}
class Pro implements Runnable{

    private Resource1 resource;

    public Pro(Resource1 resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int x = 0;x < 50;x ++){
            try {
                this.resource.make();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class Con implements Runnable{
    private Resource1 resource;

    public Con(Resource1 resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int x = 0;x < 50;x ++){
            try {
                this.resource.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class Resource1{
    private Computer computer;
    public synchronized void make() throws Exception{
        while (computer != null){
            wait();
        }
        Thread.sleep(100);
        this.computer = new Computer("三星牌",5200.03);
        System.out.println("生产电脑:" + computer);
        notifyAll();
    }
    public synchronized void get() throws Exception{
        while (computer==null){
            wait();
        }
        Thread.sleep(10);
        System.out.println("取走电脑:"+computer);
        computer = null;
        notifyAll();
    }
}
class Computer{
    private static int count = 0;//生产的个数
    private String name;
    private Double price;

    public Computer(String name, Double price) {
        this.name = name;
        this.price = price;
        count ++;
    }

    public Computer() {
    }

    @Override
    public String toString() {
        return "第"+count+"台电脑"+
                "电脑名字='" + name + '\'' +
                ", 价格=" + price +
                '}';
    }
}
