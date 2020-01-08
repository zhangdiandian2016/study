package study.spring.aop.cglib;

/**
 * 
 * @ClassName: TestCglib
 * @Description: 利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * (指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。)
 * @author denny.zhang
 * @date 2017年2月23日 下午8:14:24
 */
public class TestCglib {  
      
    public static void main(String[] args) {  
        BookFacadeCglib cglib=new BookFacadeCglib();  
        BookFacadeImpl1 bookCglib=(BookFacadeImpl1)cglib.getInstance(new BookFacadeImpl1());  
        bookCglib.addBook();  
    }  
}  