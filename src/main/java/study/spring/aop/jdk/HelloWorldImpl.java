package study.spring.aop.jdk;
/**
 * 
 * @ClassName: HelloWorldImpl
 * @Description: 
 * @author denny.zhang
 * @date 2017年2月23日 下午5:23:11
 */
 public class HelloWorldImpl implements HelloWorld{  
  
	@Override
	public void sayHello() {
		 System.out.println("HelloWorld!");  
	}  
  
}