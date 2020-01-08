package study.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author denny.zhang
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_msg")
public class UserMsg extends Model<UserMsg> {

    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 消息
     */
    private String msg;

    /**
     * 用户ID
     */
    private Long userId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
