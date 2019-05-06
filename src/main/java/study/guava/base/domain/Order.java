package study.guava.base.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 订单
 * @author denny
 * @date 2018/7/24 下午3:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Order {
    /**
     * id
     */
    private int id;
    /**
     * 订单状态
     */
    private int state;
}
