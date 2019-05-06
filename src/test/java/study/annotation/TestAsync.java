package study.annotation;

import org.junit.Test;
import study.BaseTest;
import study.service.AsyncService;

import javax.annotation.Resource;

/**
 * @author denny
 * @Description @Async异步注解测试类
 * @date 2018/5/11 下午6:35
 */
public class TestAsync extends BaseTest {

    @Resource
    private AsyncService asyncTest;

    @Test
    public void asyncTest() {
        System.out.println("主线程，线程名=" + Thread.currentThread().getName());
        asyncTest.b();
        System.out.println("检测是否异步！");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程，线程名=" + Thread.currentThread().getName());
    }

}
