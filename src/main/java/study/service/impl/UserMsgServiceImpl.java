package study.service.impl;

import com.atomikos.icatch.jta.UserTransactionImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.domain.UserMsg;
import study.repository.slave.UserMsgRepository;
import study.service.UserMsgService;
import study.service.UserService;

import javax.annotation.Resource;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

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
    private UserMsgRepository UserMsgRepository;

    /**
     * 申明式分布式事务（atomikos）
     *
     * @param id
     * @param name
     * @param msg
     */
    @Transactional(transactionManager = "atomikosTransactionManager", rollbackFor = Exception.class)
    @Override
    public void addUserMsg(int id, String name, String msg) {
        log.info("[addUserMsg] begin!!!");

        UserTransaction userTransaction = new UserTransactionImp();

        try {
            // 开启事务
            userTransaction.begin();

            // 1.插入用户
            userService.addUser(id, name);

            UserMsg userMsg = new UserMsg();
            userMsg.setUserId(id);
            userMsg.setMsg(msg);
            // 2.插入用户备注
            UserMsgRepository.insert(userMsg);

        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }

        log.info("[addUserMsg] end!!! ");
        //创造一个异常，看回滚情况
        throw new RuntimeException();
    }

    /**
     * 编程式分布式事务（atomikos）
     *
     * @param id
     * @param name
     * @param msg
     */
    @Transactional(transactionManager = "atomikosTransactionManager", rollbackFor = Exception.class)
    @Override
    public void addUserMsgWithTT(int id, String name, String msg) {
        log.info("[addUserMsg] begin!!!");

        // 1.插入用户
        userService.addUser(id, name);

        UserMsg userMsg = new UserMsg();
        userMsg.setUserId(id);
        userMsg.setMsg(msg);
        // 2.插入用户备注
        UserMsgRepository.insert(userMsg);

        log.info("[addUserMsg] end!!! ");
        //创造一个异常，看回滚情况
        throw new RuntimeException();
    }
}
