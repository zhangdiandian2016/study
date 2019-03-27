package thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author denny.zhang
 * @ClassName: FutureDemo
 * @Description: Future多线程并发任务结果归集
 * @date 2016年11月4日 下午1:50:32
 */
public class FutureDemo {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        //开启多线程
        ExecutorService exs = Executors.newFixedThreadPool(10);
        try {
            //结果集
            List<Integer> list = new ArrayList<Integer>();
            List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
            //1.高速提交10个任务，每个任务返回一个Future入list
            for (int i = 0; i < 10; i++) {
                futureList.add(exs.submit(new CallableTask(i + 1)));
            }
            Long getResultStart = System.currentTimeMillis();
            System.out.println("结果归集开始时间=" + new Date());
            //2.结果归集，用迭代器遍历futureList,高速轮询（模拟实现了并发）
            while(futureList.size()>0){
                Iterator<Future<Integer>> iterable = futureList.iterator();
                //遍历一遍
                while(iterable.hasNext()){
                    Future<Integer> future = iterable.next();
                    //如果任务完成取结果，否则判断下一个任务是否完成
                    if (future.isDone() && !future.isCancelled()){
                        //获取结果
                        Integer i = future.get();
                        System.out.println("任务i=" + i + "获取完成，移出任务队列！" + new Date());
                        list.add(i);
                        //任务完成移除任务
                        iterable.remove();
                    }else{
                        Thread.sleep(1);
                    }
                }
            }
            System.out.println("list=" + list);
            System.out.println("总耗时=" + (System.currentTimeMillis() - start) + ",取结果归集耗时=" + (System.currentTimeMillis() - getResultStart));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }
    }
}