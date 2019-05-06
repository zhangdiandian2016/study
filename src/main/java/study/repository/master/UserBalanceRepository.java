package study.repository.master;

import com.okcoin.commons.mybatis.data.CrudRepository;
import org.springframework.stereotype.Repository;
import study.domain.UserBalance;
import study.domain.example.UserBalanceExample;

/**
 *  数据访问类
 * @author denny.zhang
 * @date 2018-09-03
 */
@Repository
public interface UserBalanceRepository
    extends CrudRepository<UserBalance, UserBalanceExample, Integer> {
}