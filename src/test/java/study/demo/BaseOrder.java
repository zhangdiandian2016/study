package study.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/***
 * @Description 订单基础类
 * @author denny.zhang
 * @date 2020/3/6 7:53 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseOrder {


    /**
     * 订单商品数量
     */
    private Long count;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 发起人ID
     */
    private Long fromUserId;

}
