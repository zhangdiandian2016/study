package study.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import study.service.AsyncService;

/**
 * @author denny
 * @ClassName AsyncService
 * @Description
 * @date 2018/5/11 下午6:36
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Async
    @Override
    public void b() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步线程，线程名=" + Thread.currentThread().getName());
    }

}
