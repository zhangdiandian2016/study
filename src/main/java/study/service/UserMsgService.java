package study.service;

/**
 * @author denny
 * @Description
 * @date 2018/8/27 下午5:27
 */
public interface UserMsgService {

    /**
     * 新增带备注的用户
     * @param id
     * @param name
     * @param msg
     */
    void addUserMsg(int id, String name, String msg);

}
