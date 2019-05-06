package study.service;

/**
 * @author denny
 * @Description
 * @date 2018/8/27 下午5:27
 */
public interface UserMsgService {

    /**
     * 新增用户
     *
     * @param name
     */
    void addUserMsg(int id, String name, String msg);

    /**
     * 新增用户
     *
     * @param name
     */
    void addUserMsgWithTT(int id, String name, String msg);
}
