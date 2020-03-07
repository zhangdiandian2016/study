package study.alarm;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/***
 * @Description 用于后台异步处理请求
 * @author denny.zhang
 * @date 2020/1/14 5:15 下午
 */
public class Worker {

    /**
     * 默认核心线程池个数
     */
    private static int CORE_SIZE = 5;
    /**
     * 任务队列的容量
     */
    private static int QUEUE_CAPACITY = 10000;

    private static ThreadPoolExecutor threadPoolExecutor;

    private static Logger logger = LoggerFactory.getLogger(Worker.class);

    static {
        init(15, QUEUE_CAPACITY);
    }

    /**
     * 初始化异步任务处理，默认核心线程5个，线程名称格式：denny-worker-%d
     *
     * @param poolSize 最大线程数，应当大于5
     * @param capacity  工作队列的容量
     */
    public static void init(int poolSize, int capacity) {
        init(CORE_SIZE, poolSize, new LinkedBlockingQueue<>(capacity));
    }

    /**
     * 初始化异步任务处理，线程名称格式：denny-worker-%d
     *
     * @param coreSize 核心线程数
     * @param poolSize 最大线程数
     * @param capacity  工作队列的容量
     */
    public static void init(int coreSize, int poolSize, int capacity) {
        init(coreSize, poolSize, new LinkedBlockingQueue<>(capacity));
    }

    /**
     * 初始化异步任务处理，默认核心线程5个，线程名称格式：denny-worker-%d
     *
     * @param poolSize  最大线程数，应当大于5
     * @param workQueue 工作队列
     */
    public static void init(int poolSize, BlockingQueue<Runnable> workQueue) {
        init(CORE_SIZE, poolSize, workQueue);
    }

    /**
     * 初始化异步任务处理，线程名称格式：denny-worker-%d
     *
     * @param coreSize  核心线程数
     * @param poolSize  最大线程数
     * @param workQueue 工作队列
     */
    public static void init(int coreSize, int poolSize, BlockingQueue<Runnable> workQueue) {
        poolSize = Integer.max(coreSize, poolSize);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("denny-worker-%d").build();
        threadPoolExecutor = new ThreadPoolExecutor(coreSize, poolSize, 10, TimeUnit.MINUTES, workQueue, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        logger.info("...............................初始化后台任务调度器，coreSize={}, maxSize={}", coreSize, poolSize);
    }


    /**
     * 使用外部自定义线程池
     *
     * @param executor 自定义线程池
     */
    public static void init(ThreadPoolExecutor executor) {
        Worker.threadPoolExecutor = executor;
    }

    public static void shutdown() {
        if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
            threadPoolExecutor.shutdownNow();
        }
    }

    public static void submit(final Runnable task) {
        if (task == null) {
            logger.warn("空任务");
            return;
        }
        threadPoolExecutor.execute(() -> {
            try {
                task.run();
                logger.info("任务处理中，activeCount={}, queueSize={}", threadPoolExecutor.getActiveCount(), threadPoolExecutor.getQueue().size());

            } catch (Exception e) {
                logger.error("occur exception", e);
            }
        });
    }
}
