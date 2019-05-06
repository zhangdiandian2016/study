package study.guava.eventbus;

/**
 * @Description 事件4
 * @author denny
 * @date 2018/7/18 上午9:54
 */
public class TestEvent4 {

    private final int message;

    /**
     * 构造方法
     * @param message
     */
    public TestEvent4(int message) {
        this.message = message;
        System.out.println("TestEvent4 事件message:"+message);
    }

    public int getMessage() {
        return message;
    }
}