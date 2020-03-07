package study.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @Description 花店订单
 * @author denny.zhang
 * @date 2020/3/6 8:55 下午
 */
@Data
@AllArgsConstructor
public class FlowerOrder extends BaseOrder{

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 门店ID
     */
    private Long storeId;

}
