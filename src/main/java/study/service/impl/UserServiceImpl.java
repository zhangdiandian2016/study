package study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.domain.User;
import study.repository.UserRepository;
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

    @Transactional(propagation= Propagation.NESTED, rollbackFor = Exception.class)
    @Override
    public void addUser(String name) {
        log.info("[addUser] begin!!!");
        User user = new User();
        user.setName(name);
        userRepository.insert(user);

        log.info("[addUser] end!!! 手动构造异常！");
        //创造一个异常，看回滚情况
        throw new RuntimeException();
    }
}
