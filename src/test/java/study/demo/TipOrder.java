package study.demo;

import lombok.Data;

import java.math.BigDecimal;

/***
 * @Description 小费订单
 * @author denny.zhang
 * @date 2020/3/6 8:58 下午
 */
@Data
public class TipOrder extends BaseOrder {

    /**
     * 小费备注
     */
    private String mark;

}
