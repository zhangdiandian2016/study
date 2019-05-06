package study.guava.base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import study.guava.base.domain.Order;

/**
 * @Description 简化对象的一些方法
 * @author denny
 * @date 2018/7/24 下午4:08
 */
public class ObjectsTest {
    public static void main(String[] args) {
        /** 1.equals */
        //false
        System.out.println(Objects.equal("a",null));
        System.out.println(Objects.equal("a","b"));
        // true
        System.out.println(Objects.equal("a","a"));
        System.out.println(Objects.equal(null,null));

        /** 2.hashCode */
        Order order1 = Order.builder().id(1).state(2).build();
        Order order2 = Order.builder().id(2).state(1).build();
        System.out.println(Objects.hashCode(order1,order2));

        /** 2.toString */
        String str = MoreObjects.toStringHelper(order1).add("x",1).toString();
        System.out.println(str);

        /** 2.compare/compareTo */
        // 这里比较订单大小，比较顺序：id,状态
        int result = ComparisonChain.start()
            .compare(order1.getId(),order2.getId())
            .compare(order1.getState(),order2.getState())
            .result();
        System.out.println(result);
    }
}
