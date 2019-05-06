package study.guava.eventbus;

/**
 * @Description 事件3
 * @author denny
 * @date 2018/7/18 上午9:54
 */
public class TestEvent3 extends TestEvent2 {

    private final int message;

    /**
     * 构造方法
     * @param message
     */
    public TestEvent3(int message) {
        super(message);
        this.message = message;
        System.out.println("TestEvent2 事件message:"+message);
    }

    @Override
    public int getMessage() {
        return message;
    }
}