package study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.domain.User;
import study.repository.master.UserRepository;
import study.service.UserService;

import javax.annotation.Resource;

/**
 * @Description 
 * @author denny
 * @date 2018/8/27 下午5:31
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserRepository userRepository;

    //@Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void addUser(int id, String name) {
        log.info("[addUser] begin!!!");
        User user = new User();
        user.setId(id);
        user.setName(name);
        userRepository.insert(user);

        log.info("[addUser] end!!! ");
        //创造一个异常，看回滚情况
        //throw new RuntimeException();
    }

}
