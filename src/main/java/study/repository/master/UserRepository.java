package study.repository.master;

import com.okcoin.commons.mybatis.data.CrudRepository;
import org.springframework.stereotype.Repository;
import study.domain.User;
import study.domain.example.UserExample;

				/**
 *  数据访问类
 * @author denny.zhang
 * @date 2018-09-03
 */
@Repository
public interface UserRepository
    extends CrudRepository<User, UserExample, Long> {
}