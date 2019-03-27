package guava.eventbus;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @Description 事件监听器
 * @author denny
 * @date 2018/7/18 上午9:53
 */
public class EventListener {

    private int message = 0;

    /**
     * @Description 订阅事件1
     * @param event 事件1
     * @return void
     * @author denny
     * @date 2018/7/18 上午9:46
     */
    @Subscribe
    public void onEvent1(TestEvent1 event) {
        message = event.getMessage();
        System.out.println("EventListener onEvent1 监听器接收到消息:"+message);
    }

    /**
     * @Description 订阅事件2
     * @param event 事件2
     * @return void
     * @author denny
     * @date 2018/7/18 上午9:59
     */
    @Subscribe
    public void onEvent2(TestEvent2 event) {
        message = event.getMessage();
        System.out.println("EventListener onEvent2 监听器接收到消息:"+message);
    }

    /**
     * @Description 死亡事件（该事件没有被订阅会触发）
     * @param event 未订阅事件
     * @return void
     * @author denny
     * @date 2018/7/18 上午9:59
     */
    @Subscribe
    public void onDeadEvent(DeadEvent event) {
        System.out.println("EventListener DeadEvent 有消费没有被订阅！！！！event="+ event.toString());
    }
}