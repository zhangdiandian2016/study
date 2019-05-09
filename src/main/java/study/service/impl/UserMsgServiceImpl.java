package study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.domain.UserMsg;
import study.repository.slave.UserMsgRepository;
import study.service.UserMsgService;
import study.service.UserService;

import javax.annotation.Resource;

/**
 * @author denny
 * @Description
 * @date 2018/8/27 下午5:31
 */
@Slf4j
@Service
public class UserMsgServiceImpl implements UserMsgService {

    @Resource
    private UserService userService;

    @Resource
    private UserMsgRepository userMsgRepository;

    /**
     * 新增带备注的用户：申明式分布式事务（atomikos）
     *
     * @param id
     * @param name
     * @param msg
     */
    @Transactional(transactionManager = "atomikosTransactionManager", rollbackFor = Exception.class)
    @Override
    public void addUserMsg(int id, String name, String msg) {
        log.info("[addUserMsg] begin!!!");

        // 1.插入用户
        userService.addUser(id, name);

        UserMsg userMsg = new UserMsg();
        userMsg.setUserId(id);
        userMsg.setMsg(msg);
        // 2.插入用户备注
        userMsgRepository.insert(userMsg);

        log.info("[addUserMsg] end!!! ");
        //创造一个异常，看回滚情况
        //throw new RuntimeException();
    }


}
