package guava.base;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import guava.base.domain.Order;

import java.util.List;

/**
 * @Description 排序(JDK8直接使用Stream,Comparator)
 * @author denny
 * @date 2018/7/24 下午6:01
 */
public class OrderingTest {
    public static void main(String[] args) {
        /**自然排序: 数字升序，时间升序*/
        // 简单数据排序
        Ordering<Integer> integerOrdering = Ordering.natural().nullsFirst();
        List<Integer> list = Lists.newArrayList(1,3,null,5,4,2);
        // 自然排序，空前置
        System.out.println("1.自然排序："+integerOrdering.sortedCopy(list));
        System.out.println("2.自然反转排序："+integerOrdering.reverse().sortedCopy(list));

        // 根据apply返回值排序
        Ordering<Order> orderOrdering = Ordering.natural().onResultOf(new Function<Order, Integer>() {
            public Integer apply(Order order){
                /* 订单ID自然排序 */
                return order.getId();
            }
        });
        List<Order> orders = Lists.newArrayList(new Order(1,0),new Order(3,1),new Order(2,2));
        System.out.println("3.根据订单ID自然排序："+orderOrdering.sortedCopy(orders));
        System.out.println("4.根据订单ID自然排序,求最大值："+orderOrdering.max(orders));
        System.out.println("5.根据订单ID自然排序,求最小值："+orderOrdering.min(orders));
        System.out.println("6.求ID最小的k个元素："+orderOrdering.leastOf(orders,2));
        System.out.println("7.求ID最大的k个元素："+orderOrdering.greatestOf(orders,2));
    }
}
