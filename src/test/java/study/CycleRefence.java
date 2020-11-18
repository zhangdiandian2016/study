package study;

import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

/**
 * 类 名 称：CycleRefence
 * 类 描 述：TODO
 * 创建时间：2020/7/23 12:01 下午
 * 创 建 人：zyn
 */
public class CycleRefence {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.bb=b;
        b.aa=a;

        a=null;
        b=null;
    }
}

     class A {
        public B bb;
    }

    class B {
        public A aa;
    }

