package study.jdk.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    // 初始化30个手机
     static volatile AtomicInteger integer = new AtomicInteger(3);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startlatch = new CountDownLatch(1);
        CountDownLatch endlatch = new CountDownLatch(10);
        //开启100个线程
        for(int i=0;i<10;i++){
           new Thread(()->{
               try {
                   startlatch.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               Lock lock = new ReentrantLock(false);

               //售卖手机 每次卖出一个
               lock.lock();
               System.out.println(Thread.currentThread().getName()+" -begin"+" 还剩: "+integer);
               if(integer.intValue()>0) {
                   System.out.println(Thread.currentThread().getName()+" -getone"+" 还剩: "+integer.decrementAndGet());
               }
               lock.unlock();
               endlatch.countDown();
           }).start();
        }
        // 确保所有线程启动后，再一起运行线程方法体
        startlatch.countDown();
        // 等待所有线程执行完毕
        endlatch.await();
        System.out.println("----------------------"+integer+"----------------------");
    }


}