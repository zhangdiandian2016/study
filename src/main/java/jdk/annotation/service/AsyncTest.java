package jdk.annotation.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author denny
 * @ClassName AsyncTest
 * @Description
 * @date 2018/5/11 下午6:36
 */
@Component
public class AsyncTest {

    @Async
    public void b(){
        System.out.println("异步任务，线程名="+Thread.currentThread().getName());
    }
}
