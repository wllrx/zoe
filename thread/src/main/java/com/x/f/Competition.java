package com.x.f;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 竞争抢答示例  因为有返回值 使用callable
 * @author zoe
 * @date 2019-01-23
 */
public class Competition {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TheThread theThread = new TheThread();
        FutureTask<String> futureTaskA = new FutureTask<>(theThread);
        FutureTask<String> futureTaskB = new FutureTask<>(theThread);
        FutureTask<String> futureTaskC = new FutureTask<>(theThread);
        new Thread(futureTaskA,"竞赛者A").start();
        new Thread(futureTaskB,"竞赛者B").start();
        new Thread(futureTaskC,"竞赛者C").start();
        System.out.println(futureTaskA.get());
        System.out.println(futureTaskB.get());
        System.out.println(futureTaskC.get());
    }
}

class TheThread implements Callable<String>{

    private boolean flag = false;
    @Override
    public String call() throws Exception {
        synchronized (this){
            if (this.flag==false){
                this.flag = true;
                return Thread.currentThread().getName()+"抢答成功";
            }else {
                return Thread.currentThread().getName()+"抢答失败";
            }
        }
    }
}