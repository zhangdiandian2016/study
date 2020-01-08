package study.spring.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;  
import net.sf.cglib.proxy.MethodInterceptor;  
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @ClassName: BookFacadeCglib
 * @Description: 使用cglib动态代理
 * @author denny.zhang
 * @date 2017年2月23日 下午8:06:59
 */
public class BookFacadeCglib implements MethodInterceptor {  
    private Object target;  
  
    /** 
     * 创建代理对象 
     *  
     * @param target 
     * @return 
     */  
    public Object getInstance(Object target) {  
        this.target = target;  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();  
    }  
  
    @Override  
    // 回调方法  
    public Object intercept(Object obj, Method method, Object[] args,  
            MethodProxy proxy) throws Throwable {  
        System.out.println("事物开始");  
        proxy.invokeSuper(obj, args);  
        System.out.println("事物结束");  
        return null;  
  
  
    }  
  
}  