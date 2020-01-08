package study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import study.domain.User;

/**
 * @Description 
 * @author denny
 * @date 2018/8/27 下午5:27
 */
public interface UserService extends IService<User> {

    /**
     * 新增用户
     * @param name
     */
    void addUser(long id, String name);

}
