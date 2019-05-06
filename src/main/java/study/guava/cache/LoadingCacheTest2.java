package study.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName study.guava.cache.LoadingCacheTest
 * @Description 注意refresh并不会主动刷新，而是被检索触发更新value,且随时可返回旧值
 * @Author denny
 * @Date 2018/4/28 下午12:10
 */
public class LoadingCacheTest2 {

    // guava线程池,用来产生ListenableFuture
    @SuppressWarnings("AlibabaThreadPoolCreation")
    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(
        Executors.newFixedThreadPool(10));

    /**
     * 1.expireAfterWrite:指定时间内没有创建/覆盖时，会移除该key，下次取的时候触发"同步load"(一个线程执行load)
     * 2.refreshAfterWrite:指定时间内没有被创建/覆盖，则指定时间过后，再次访问时，会去刷新该缓存，在新值没有到来之前，始终返回旧值
     * "异步reload"（也是一个线程执行reload）
     * 3.expireAfterAccess:指定时间内没有读写，会移除该key，下次取的时候从loading中取
     * 区别：指定时间过后，expire是remove该key，下次访问是同步去获取返回新值；
     * 而refresh则是指定时间后，不会remove该key，下次访问会触发刷新，新值没有回来时返回旧值
     *
     * 同时使用：可避免定时刷新+定时删除下次访问载入
     */
    private static final LoadingCache<String, String> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .refreshAfterWrite(5, TimeUnit.SECONDS)
        //.expireAfterWrite(1, TimeUnit.SECONDS)
        //.expireAfterAccess(1,TimeUnit.SECONDS)
        .build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                System.out.println(Thread.currentThread().getName() +"==load start=="+"，时间=" + new Date());
                // 模拟同步重载耗时2秒
                Thread.sleep(10000);
                String value = "load-" + new Random().nextInt(10);
                System.out.println(
                    Thread.currentThread().getName() + "==load end==同步耗时2秒重载数据-key=" + key + ",value="+value+"，时间=" + new Date());
                return value;
            }

            //@Override
            //public ListenableFuture<String> reload(final String key, final String oldValue)
            //    throws Exception {
            //    System.out.println(
            //        Thread.currentThread().getName() + "==reload ==异步重载-key=" + key + "，时间=" + new Date()+",old="+oldValue);
            //    return service.submit(new Callable<String>() {
            //        public String call() throws Exception {
            //            /* 模拟异步重载耗时2秒 */
            //            Thread.sleep(2000);
            //            String value = "reload-" + new Random().nextInt(10);
            //            System.out.println(Thread.currentThread().getName() + "==reload-callable-result="+value+ "，时间=" + new Date());
            //            return value;
            //        }
            //    });
            //}
        });

    //倒计时器
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        System.out.println("启动-设置缓存" + "，时间=" + new Date());
        cache.put("name", "123456");
        System.out.println("缓存是否存在=" + cache.getIfPresent("name"));

        //System.out.println("2秒后"+"，时间="+new Date());
        System.out.println("refresh!!!!!!");

        cache.refresh("name");
        System.out.println(Thread.currentThread().getName() + "refresh，读缓存="+cache.get("name")+"，时间=" + new Date());
        Thread.sleep(7000);
        System.out.println(Thread.currentThread().getName() + "距离上一次读7秒后，读缓存="+cache.get("name")+"，时间=" + new Date());
    }


}
