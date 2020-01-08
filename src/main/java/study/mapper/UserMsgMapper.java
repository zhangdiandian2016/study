package study.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import study.domain.UserMsg;

/**
 * <p>
 * 消息表 Mapper 接口
 * </p>
 *
 * @author denny.zhang
 * @since 2020-01-07
 */
@DS("slave") //这里是配置数据源注解，默认是master
public interface UserMsgMapper extends BaseMapper<UserMsg> {

}
