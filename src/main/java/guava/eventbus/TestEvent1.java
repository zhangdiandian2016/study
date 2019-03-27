package guava.eventbus;

/**
 * @Description 事件1
 * @author denny
 * @date 2018/7/18 上午9:54
 */
public class TestEvent1 {

    private final int message;

    /**
     * 构造方法
     * @param message
     */
    public TestEvent1(int message) {
        this.message = message;
        System.out.println("TestEvent1 事件message:"+message);
    }

    public int getMessage() {
        return message;
    }
}