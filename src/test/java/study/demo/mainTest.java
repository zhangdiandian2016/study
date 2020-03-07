package study.demo;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/***
 * @Description 反射调用测试类：action的className
 * @author denny.zhang
 * @date 2020/3/6 8:58 下午
 */
public class mainTest {
    public static void main(String[] args) {
        // 1.前端传入参 业务类型：实体店订单
        int businessType=0;

        // 2.(接口入参可以写成json字符串，这样接口入参不用变) json: {type=0,orderData="name:'花店订单',amount:100"}
        // 根据第一步的type，构造不同实体,这里没有办法，必须写枚举（if/case/...）
        FlowerOrder flowerOrder = new FlowerOrder(1L,2L);
        flowerOrder.setAmount(new BigDecimal(100));
        flowerOrder.setCount(10L);
        flowerOrder.setName("花店订单");
        flowerOrder.setFromUserId(11L);

        // 2.2 要执行的action
        String className = "study.demo.FlowerOrderAction";
        // 3.调用执行器利用Java反射构造类实例，并调用方法
        PlaceOrderActionExecute.placeOrder(className,"placeOrder", flowerOrder);
    }


}
