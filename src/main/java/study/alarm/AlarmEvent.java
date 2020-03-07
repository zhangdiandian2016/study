package study.alarm;

/***
 * @Description 简单报警事件定义
 * @author denny.zhang
 * @date 2020/1/14 5:44 下午
 */
public class AlarmEvent {

    /**
     * 事件场景
     */
    private String scene;

    /**
     * 事件描述
     */
    private String desc;

    /**
     * 用户编号
     */
    private long userId;

    /**
     * 业务主键
     */
    private String rfId;


    /**
     * 业务数据
     */
    private Object data;


    private AlarmEvent(String scene, String desc, String rfId, long userId, Object data) {
        this.scene = scene;
        this.desc = desc;
        this.userId = userId;
        this.rfId = rfId;
        this.data = data;
    }

    /**
     * 构造告警事件
     *
     * @param scene  发生场景
     * @param desc   事件描述
     * @param rfId   业务主键
     * @param userId 用户编号或者操作账号
     * @param data   业务数据
     * @return
     */
    public static AlarmEvent of(String scene, String desc, String rfId, long userId, Object data) {
        return new AlarmEvent(scene, desc, rfId, userId, data);
    }

    /**
     * 构造告警事件
     *
     * @param scene  发生场景
     * @param desc   事件描述
     * @param rfId   业务主键
     * @param userId 用户编号
     * @return
     */
    public static AlarmEvent of(String scene, String desc, String rfId, long userId) {
        return new AlarmEvent(scene, desc, rfId, userId, null);
    }

    /**
     * 构造告警事件
     *
     * @param scene 发生场景
     * @param desc  事件描述
     * @param rfId  业务主键
     * @param data  业务数据
     * @return
     */
    public static AlarmEvent of(String scene, String desc, String rfId, Object data) {
        return new AlarmEvent(scene, desc, rfId, 0L, data);
    }

    /**
     * 构造告警事件
     *
     * @param scene 发生场景
     * @param desc  事件描述
     * @param rfId  业务主键
     * @return
     */
    public static AlarmEvent of(String scene, String desc, String rfId) {
        return new AlarmEvent(scene, desc, rfId, 0L, null);
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
