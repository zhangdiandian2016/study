package study.spring.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 
 * @ClassName: HelloWorldTest
 * @Description: 测试jdk动态代理:利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。(只能代理实现了接口的类)
 * @author denny.zhang
 * @date 2017年2月23日 下午5:26:07
 */
public class HelloWorldTest {  
  
    public static void main(String[] args) {  
        HelloWorld helloWorld=new HelloWorldImpl();  
        InvocationHandler handler=new HelloWorldHandler(helloWorld);  
  
        //创建动态代理对象  
        HelloWorld proxy=(HelloWorld)Proxy.newProxyInstance(  
            helloWorld.getClass().getClassLoader(),  
            helloWorld.getClass().getInterfaces(),  
            handler);  
        proxy.sayHello();  
    }  
}  