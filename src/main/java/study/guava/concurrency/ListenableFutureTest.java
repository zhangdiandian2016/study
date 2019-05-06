package study.guava.concurrency;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author denny
 * @Description 可监听的任务
 * @date 2018/8/1 上午10:43
 */
public class ListenableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /** 1.典型用法：可监听的future,带回调方法 */
        // 定义监听执行服务
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        // 定义可监听的带返回值的任务
        ListenableFuture<String> callableListenableFuture1 = listeningExecutorService.submit(() -> {
            System.out.println("callable1 call!");
            return "1";
        });
        // 添加回调，由指定监听执行服务来执行，监听可监听的future,监听到事件时执行对应回调方法。
        Futures.addCallback(callableListenableFuture1, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("success,result=" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("fail!");
            }
        }, listeningExecutorService);

        /** 2.非典型用法：异步转换 */
        // 异步转换，参数1是需要转换的listenableFuture,参数2是转换方法，参数3是执行转换的线程执行器（Runnable）。
        ListenableFuture<Integer> transform = Futures.transformAsync(callableListenableFuture1, new AsyncFunction<String, Integer>() {
            @Override
            public ListenableFuture<Integer> apply(@Nullable String input) {
                return Futures.immediateFuture(Integer.parseInt(input));
            }
        }, MoreExecutors.directExecutor());
        // 阻塞线程得到结果
        System.out.println("阻塞获取转换后任务的结果："+transform.get());

        /** 3.典型用法：多任务并发执行取结果list */
        // 可监听不带返回值的任务

        ListenableFuture<String> callableListenableFuture2 = listeningExecutorService.submit(() -> {
            Thread.sleep(3000);
            System.out.println("callable2 call!");
            return "2";
        });
        ListenableFuture<String> callableListenableFuture3 = listeningExecutorService.submit(() -> {
            System.out.println("callable3 call!");
            return "3";
        });
        ListenableFuture<List<String>> listListenableFuture = Futures.allAsList(
            Lists.newArrayList(callableListenableFuture1, callableListenableFuture2, callableListenableFuture3));
        // 返回结果list就是添加任务的顺序
        System.out.println("多任务并发执行取结果list result=" + listListenableFuture.get());
    }
}
