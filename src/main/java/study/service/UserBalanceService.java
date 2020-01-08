package study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import study.domain.UserBalance;

import java.math.BigDecimal;

/**
 * @Description 
 * @author denny
 * @date 2018/8/31 下午6:28
 */
public interface UserBalanceService extends IService<UserBalance> {
    /**
     * 创建账户余额
     * @param userBalance
     * @return
     */
    void addUserBalance(UserBalance userBalance);

    /**
     * 创建用户并创建账户余额（申明式事务）
     * @param name
     * @param balance
     * @return
     */
    void addUserBalanceAndUser(long id, String name, BigDecimal balance);

    /**
     * 创建用户并创建账户余额(手动事务)
     * @param name
     * @param balance
     * @return
     */
    void addUserBalanceAndUserWithinTT(long id, String name, BigDecimal balance);
}
