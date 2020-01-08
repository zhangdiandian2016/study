package study.jdk.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
    
/** 
 * 循环栅栏（屏障）
 * 问题：一个池塘，有很多鸟和很多鱼，鸟每分钟产生一个后代，鱼每30秒钟产生2个后代。
 * 鸟每10秒钟要吃掉一条鱼。建一个池塘，初始化一些鱼和鸟，看看什么时候鸟把鱼吃光。 
 * 
 */  
public class CyclicBarrierDemo {  
  
    long time ;  
    long birdNum ;  
    long fishNum ;  
    Object lock = new Object() ;  
    CyclicBarrier barrier  ;  
      
    public CyclicBarrierDemo(long birdNum , long fishNum){  
        this.birdNum = birdNum ;  
        this.fishNum = fishNum ;  
    }  
  
    /**
     * 入口
     * @param args
     */
    public static void main(String[] args) { 
    	//构造demo,初始化5只秒，20条鱼
        CyclicBarrierDemo bf = new CyclicBarrierDemo(5 , 20) ;  
        //生态圈开启
        bf.start();   
    }  
  
    //生态圈开启
    public void start(){  
    	//构造鱼，鸟，时间线
        FishThread fish = new FishThread() ;  
        BirdThread bird = new BirdThread() ;  
        TimeLine tl = new TimeLine() ;  
  
        //初始化环形屏障，当barrier对象的await方法被调用两次之后，将会执行tl线程  
        barrier = new CyclicBarrier(2, tl) ;//这里要注意第一个参数，如果大于调用await的线程数，会死锁。  
  
        //鱼、鸟动起来
        fish.start();  
        bird.start();  
  
    }  
  
    public void printInfo(String source){  
        System.out.printf(source+"time[%d]:birdNum[%d] ,fishNum[%d]\n" ,time , birdNum , fishNum);  
    }  
  
    private class TimeLine implements Runnable {  
        @Override  
        public void run() { //所有子任务都调用了await方法后，将会执行该方法， 然后所有子线程继续执行  
        	System.out.println("TimeLine start!");
            //如果鱼数量<=0,结束程序
        	if(fishNum <= 0){  
                System.exit(-1);     
            }
        	//时间加10秒
            time += 10 ; 
            System.out.println("TimeLine end，时间加10秒！");
        }  
    }  
  
    private class FishThread extends Thread {  
        @Override  
        public void run() {  
            //循环
        	while(true){  
                try { 
                	System.out.println("鱼已经就位！到达await!");
                    barrier.await() ;   //进入睡眠， 等待所有子任务都进入睡眠  然后再继续  
                } catch (InterruptedException | BrokenBarrierException e) {  
                    e.printStackTrace();  
                }  
                synchronized (lock) {
                	//鱼每30秒钟产生2个后代
                    if(time % 30 == 0){
                        fishNum += fishNum * 2;  
                        printInfo("鱼动作执行！");
                    }  
                }  
            }  
        }  
    }  
  
    private class BirdThread extends Thread{  
        @Override  
        public void run() {
        	//循环
            while(true){  
                try {  
                	System.out.println("鸟已经就位！到达await!");
                    barrier.await() ;  //进入睡眠， 等待所有子任务都进入睡眠  然后再继续  
                } catch (InterruptedException | BrokenBarrierException e) {  
                    e.printStackTrace();  
                }    
                synchronized (lock) {
                	//鸟每10秒钟要吃掉一条鱼
                    if(time % 10 == 0){  
                        fishNum = fishNum >= birdNum ? fishNum - birdNum : 0 ;    
                        //鸟每分钟产生一个后代
                        if(time % 60 == 0){  
                            birdNum += birdNum ;  
                        }  
                        printInfo("鸟动作执行！");  
                    }  
                }  
  
            }  
  
        }  
  
    }  
  
}  