package com.x.f.basis;

/**
 * AutoCloseable 接口 需跟异常结合才能使用
 * @author zoe
 * @date 2019-01-23
 */
public class AutoCloseableCase {
    public static void main(String[] args) throws Exception {
        try(Message message = new Message("hello")) {
            message.send();  //消息发送
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Message implements IMessage{ //实现消息处理机制
    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public boolean open(){
        System.out.println("获取消息发送连接资源");
        return true;
    }
    public void close() throws Exception{
        System.out.println("关闭消息发送通道");
    }

    @Override
    public void send() {  //方法复写
        if (this.open()){
            System.out.println("***   发送消息   ***" + msg);
        }
    }
}
interface IMessage extends AutoCloseable{
    public void send(); //消息发送
}