package study.service;

import study.domain.UserBalance;

import java.math.BigDecimal;

/**
 * @Description 
 * @author denny
 * @date 2018/8/31 下午6:28
 */
public interface UserBalanceService {
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
    void addUserBalanceAndUser(int id, String name, BigDecimal balance);

    /**
     * 创建用户并创建账户余额(手动事务)
     * @param name
     * @param balance
     * @return
     */
    void addUserBalanceAndUserWithinTT(int id, String name, BigDecimal balance);
}
