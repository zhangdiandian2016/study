package study.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 类 名 称：PlaceOrderActionExecute
 * 类 描 述：TODO
 * 创建时间：2020/3/6 9:59 下午
 * 创 建 人：zyn
 */
public class PlaceOrderActionExecute {
    /**
     * 下单核心解析器
     * @param baseOrder
     * @return
     */
    public static Object placeOrder(String className,String methodName,BaseOrder baseOrder){
        Object result = null;
        // 3. 核心封装方法 入参  className、methodName--》执行下单方法
        try {
            // 类
            Class myClass = Class.forName(className);
            // 类实例
            Constructor constructor = (Constructor) myClass.getDeclaredConstructor(baseOrder.getClass());
            Object obj = (Object)constructor.newInstance(baseOrder);
            // 获得这个类的所有方法
            Method[] methods = myClass.getMethods();
            // 遍历方法
            for(Method method : methods) {
                // 找到需要发方法
                if(methodName.equals(method.getName())) {
                    // 第一个参数是类名，后面是方法需要的参数
                    result =  method.invoke(obj);
                }
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }
}
