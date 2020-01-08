package study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import study.domain.UserBalance;
import study.mapper.UserBalanceMapper;
import study.service.UserBalanceService;
import study.service.UserService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description 
 * @author denny
 * @date 2018/8/31 下午6:30
 */
@Slf4j
@Service
public class UserBalanceImpl extends ServiceImpl<UserBalanceMapper, UserBalance> implements UserBalanceService {

    @Resource
    private UserService userService;
    @Resource
    private UserBalanceService userBalanceService;
    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 创建用户余额
     *
     * @param userBalance
     * @return
     */
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void addUserBalance(UserBalance userBalance) {
        this.userBalanceService.save(userBalance);
    }

    /**
     * 创建用户并创建账户余额
     *
     * @param name
     * @param balance
     * @return
     */
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void addUserBalanceAndUser(long id, String name, BigDecimal balance) {
        log.info("[addUserBalanceAndUser] begin!!!");
        //1.新增用户余额
        UserBalance userBalance = new UserBalance();
        userBalance.setName(name);
        userBalance.setBalance(new BigDecimal(1000));
        this.addUserBalance(userBalance);
        //2.新增用户，这里捕获嵌套事务的异常，不让外部事务获取到，不然外部事务肯定会回滚！
        try{
            // 嵌套事务NESTED
            userService.addUser(id, name);
        }catch (Exception e){
            // 这里可根据实际情况添加自己的业务！
            log.error("嵌套事务【addUser】异常！",e);
        }

        log.info("[addUserBalanceAndUser] end!!!");
    }

    /**
     * 创建用户并创建账户余额(手动事务，不带结果)
     *
     * @param name
     * @param balance
     * @return
     */
    @Override
    public void addUserBalanceAndUserWithinTT(long id, String name, BigDecimal balance) {
        //1.没有返回值的事务回调
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
        //try {
            log.info("[addUserBalanceAndUser] begin!!!");

            //1.新增用户
            userService.addUser(id, name);
            //2.新增用户余额
            UserBalance userBalance = new UserBalance();
            userBalance.setName(name);
            userBalance.setBalance(new BigDecimal(1000));
            userBalanceService.save(userBalance);
            int a = 1/0;
            log.info("[addUserBalanceAndUser] end!!!");
            //throw new Exception("自定义异常！！");
            //注意：这里catch住异常后，要不就再次Throw异常，要不就设置setRollbackOnly，否则事务不会滚。如果是声明式注解@Transactional ,捕获后可以TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();强制回滚
        //} catch (Exception e) {
        //    // 自定义异常回滚
        //    status.setRollbackOnly();
        //    log.error("异常回滚!,e={}",e);
        //}

            }
        });

        // 2.有返回结果的事务回调
        String result=transactionTemplate.execute(new TransactionCallback<String>() {

            @Override
            public String doInTransaction(TransactionStatus status) {
                //此处省略业务
                return "OK";
            }
        });
        System.out.println(result);
    }
}
