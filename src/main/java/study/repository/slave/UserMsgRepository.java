package study.repository.slave;

import com.okcoin.commons.mybatis.data.CrudRepository;
import org.springframework.stereotype.Repository;
import study.domain.UserMsg;
import study.domain.example.UserMsgExample;

/**
 * 数据访问类
 *
 * @author cang.chen
 * @date 2019-04-04
 */
@Repository
public interface UserMsgRepository
    extends CrudRepository<UserMsg, UserMsgExample, Integer> {
}