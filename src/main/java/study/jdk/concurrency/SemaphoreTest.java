package study.jdk.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * @ClassName: SemaphoreTest
 * @Description: 信号量
 * @author denny.zhang
 * @date 2016年11月4日 下午1:14:05
 *
 */
public class SemaphoreTest {

     public static void main(String[] args) {  
        // 线程池 
        ExecutorService exec = Executors.newCachedThreadPool();  
        // 只能5个线程同时访问 
        final Semaphore semp = new Semaphore(5);  
        // 模拟20个客户端访问 
        for (int index = 0; index < 20; index++) {
            final int NO = index;  
            Runnable run = new Runnable() {  
                public void run() {  
                    try {  
                        //获取许可 
                        semp.acquire();  
                        System.out.println("Accessing: " + NO);  
                        Thread.sleep(2000);  
                    } catch (InterruptedException e) { 
                    	e.printStackTrace();
                    } finally{
                    	//释放 
                        semp.release();
                        //可获得许可数
                        System.out.println("-----------------"+semp.availablePermits());
                    } 
                }  
            };  
            exec.execute(run);  
        }  
        // 退出线程池 
        exec.shutdown();  
    }  
}