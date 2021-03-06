package study.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.domain.UserMsg;
import study.mapper.UserMsgMapper;
import study.service.UserMsgService;
import study.service.UserService;

import javax.annotation.Resource;


/***
 * @Description 用户消息服务（slave库）
 * @author denny.zhang
 * @date 2020/1/8 10:28 上午
 */
@Slf4j
@Service
public class UserMsgServiceImpl extends ServiceImpl<UserMsgMapper, UserMsg> implements UserMsgService {

    @Resource
    private UserService userService;

    @Resource
    private UserMsgService userMsgService;

    @Resource
    private UserMsgMapper userMsgMapper;

    /**
     * 新增带备注的用户：申明式分布式事务（atomikos）
     *
     * @param id
     * @param name
     * @param msg
     */
    //@Transactional(transactionManager = "atomikosTransactionManager", rollbackFor = Exception.class)
    //@Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)

    @Override
    public void addUserMsg(long id, String name, String msg) {
        log.info("[addUserMsg] begin!!!");

        // 1.插入用户-主库
        userService.addUser(id, name);

        UserMsg userMsg = new UserMsg();
        userMsg.setUserId(id);
        userMsg.setMsg(msg);
        // 2.插入用户备注-从库
        userMsgService.addMsg(userMsg);

        log.info("[addUserMsg] end!!! ");
        //创造一个异常，看回滚情况
        //throw new RuntimeException();
    }

    @Override
    @DS("slave")
    public void addMsg(UserMsg userMsg) {
        userMsgMapper.addMsg(userMsg);
    }
}
