package study.jdk.override;

/**
 * 类 名 称：Chile
 * 类 描 述：TODO
 * 创建时间：2020/7/29 2:34 下午
 * 创 建 人：zyn
 */
public class Child extends Father {

    @Override
    public synchronized void fun(){
        System.out.println("child");
    }

}
