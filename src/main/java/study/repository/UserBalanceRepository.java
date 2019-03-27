package study.repository;

import com.okcoin.commons.mybatis.data.CrudRepository;
import study.domain.UserBalance;
import study.domain.example.UserBalanceExample;
import org.springframework.stereotype.Repository;

/**
 *  数据访问类
 * @author denny.zhang
 * @date 2018-09-03
 */
@Repository
public interface UserBalanceRepository
    extends CrudRepository<UserBalance, UserBalanceExample, Integer> {
}