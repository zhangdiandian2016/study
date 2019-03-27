package study.repository;

import com.okcoin.commons.mybatis.data.CrudRepository;
import study.domain.User;
import study.domain.example.UserExample;
import org.springframework.stereotype.Repository;

				/**
 *  数据访问类
 * @author denny.zhang
 * @date 2018-09-03
 */
@Repository
public interface UserRepository
    extends CrudRepository<User, UserExample, Long> {
}