package study.guava.eventbus;

/**
 * @Description 事件2
 * @author denny
 * @date 2018/7/18 上午9:54
 */
public class TestEvent2 {

    private final int message;

    /**
     * 构造方法
     * @param message
     */
    public TestEvent2(int message) {
        this.message = message;
        System.out.println("TestEvent2 事件message:"+message);
    }

    public int getMessage() {
        return message;
    }
}