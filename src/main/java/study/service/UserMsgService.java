package study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import study.domain.UserMsg;

/**
 * @author denny
 * @Description
 * @date 2018/8/27 下午5:27
 */
public interface UserMsgService extends IService<UserMsg> {

    /**
     * 新增带备注的用户
     * @param id
     * @param name
     * @param msg
     */
    void addUserMsg(long id, String name, String msg);

    void addMsg(UserMsg userMsg);
}
