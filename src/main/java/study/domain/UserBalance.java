package study.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author denny.zhang
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_balance")
public class UserBalance extends Model<UserBalance> {

    private static final long serialVersionUID=1L;

    private Long id;

    private String name;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 账户余额
     */
    private BigDecimal balance;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
