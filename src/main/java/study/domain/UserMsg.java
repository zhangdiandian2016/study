package study.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author denny.zhang
 * @date 2019-04-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMsg {
    /**
     * ID主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private Integer userId;
    /**
     * 消息
     */
    private String msg;
}