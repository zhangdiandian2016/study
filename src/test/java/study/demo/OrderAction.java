package study.demo;

import java.math.BigDecimal;

/**
 * 类 名 称：orderAction
 * 类 描 述：共用订单抽象接口集合
 * 创建时间：2020/3/6 7:46 下午
 * 创 建 人：zyn
 */
public interface OrderAction<T extends BaseOrder> {
    void dataReady(T t);
    boolean checkPrice(T t);
    int addDBU(T t);

}
