package jdk.annotation.service.impl;

import jdk.annotation.service.AsyncTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author denny
 * @ClassName TestServiceImpl
 * @Description
 * @date 2018/5/11 下午6:35
 */
@Configuration
@EnableAsync
public class TestAsync {



    public static void main(String[] args) {
        System.out.println("主线程，线程名="+Thread.currentThread().getName());
        AsyncTest testAsync = new AsyncTest();
        testAsync.b();
    }
}
