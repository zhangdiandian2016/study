package guava.base;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import guava.base.domain.Order;

import java.util.List;

/**
 * @Description 前置条件校验
 * @author denny
 * @date 2018/7/24 下午3:14
 */
public class PreconditionsTest {
    public static void main(String[] args) {

        /** 1.空指针校验 */
        Integer a = null;
        // 直接抛出空指针异常
        Preconditions.checkNotNull(a);
        // 抛出指定错误消息的空指针异常
        Preconditions.checkNotNull(a,"a is null!");
        // 抛出指定错误消息(指定参数替换掉%s)的空指针异常
        Preconditions.checkNotNull(a,"a is null ,a=%s",a);

        /** 2.方法入参校验 */
        // 方法入参校验：校验第一参是否true
        Preconditions.checkArgument(a!=null && a>=0,"参数a不满足条件! a=%s",a);

        /** 3.检查对象的状态 */
        // 模拟：订单号作为方法入参，修改订单状态为已完成。
        Order order = Order.builder().id(1).build();
        // 状态校验，非入参校验
        Preconditions.checkState(order.getState()>0,"订单状态非法! status=%s",order.getState());

        /** 4.下标越界校验 */
        List list = Lists.newArrayList(1,2,3);
        //Preconditions.checkElementIndex(5,list.size(),"下标非法！");

        /** 5.下标越界、start<end 校验 */
        Preconditions.checkPositionIndexes(0,5,list.size());
    }
}
