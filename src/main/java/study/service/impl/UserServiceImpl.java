package study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.domain.User;
import study.mapper.UserMapper;
import study.service.UserService;

/**
 * @Description 
 * @author denny
 * @date 2018/8/27 下午5:31
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    //@Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void addUser(long id, String name) {
        log.info("[addUser] begin!!!");
        User user = new User();
        user.setId(id);
        user.setName(name);
        this.save(user);

        log.info("[addUser] end!!! ");
        //创造一个异常，看回滚情况
        //throw new RuntimeException();
    }

}
