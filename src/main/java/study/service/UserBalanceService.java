package study.service;

import org.springframework.stereotype.Service;
import study.domain.UserBalance;

import java.math.BigDecimal;

/**
 * @Description 
 * @author denny
 * @date 2018/8/31 下午6:28
 */
@Service
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
    void addUserBalanceAndUser(String name, BigDecimal balance);

    /**
     * 创建用户并创建账户余额(手动事务)
     * @param name
     * @param balance
     * @return
     */
    void addUserBalanceAndUserWithinTT(String name, BigDecimal balance);
}
