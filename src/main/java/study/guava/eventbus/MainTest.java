package study.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @Description 主测试类
 * @author denny
 * @date 2018/7/18 上午9:54
 */
public class MainTest {


    public static void main(String[] args) {
        // 1.构造一个事件总线
        EventBus eventBus = new EventBus("test");

        // 2.构造一个事件监听器
        EventListener listener = new EventListener();

        // 3.把事件监听器注册到事件总线上
        eventBus.register(listener);

        // 4.事件总线发布事件，触发监听器方法
        eventBus.post(new TestEvent1(1));
        eventBus.post(new TestEvent2(2));
        // 事件3是事件2子类，虽然监听器只订阅了父类事件2，一样可以监听到子类
        eventBus.post(new TestEvent3(3));
        // deadEvent未被订阅的事件，供用户自行处理
        eventBus.post(new TestEvent4(4));

    }
}