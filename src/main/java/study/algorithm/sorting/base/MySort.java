package study.algorithm.sorting.base;

/***
 * @Description 自定义排序基类，封装常用方法
 * @author denny
 * @date 2019/7/2 上午10:33
 */
public class MySort {
    protected static String[] a = new String[] {"a", "d", "c", "b"};

    /**
     * @param a
     * @Description 打印排序后结果
     * @author denny.zhang
     * @date 2018年2月5日下午3:11:46
     * @since JDK1.8
     */
    protected static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * @param v
     * @param w
     * @return
     * @Description 比较是否v小于w
     * @author denny.zhang
     * @date 2018年2月5日下午3:11:14
     * @since JDK1.8
     */
    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * @param a
     * @param i
     * @param j
     * @Description 对于数组a, 交换a[i]和a[j]。
     * @author denny.zhang
     * @date 2018年2月5日下午3:09:41
     * @since JDK1.8
     */
    protected static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        //System.out.println("交换a["+i+"]"+",a["+j+"]");
    }

    /**
     * @param a
     * @return
     * @Description 用于校验是否升序
     * @author denny.zhang
     * @date 2018年2月5日下午3:10:57
     * @since JDK1.8
     */
    protected static boolean isSorted(Comparable[] a) {
        //只要有一个后数<前数，返回失败
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) { return false; }
        }
        //都没问题，返回成功
        return true;
    }

    /**
     * @param a
     * @param lo 起始下标
     * @param hi 结束下标
     * @return
     * @Description 用于校验是否升序
     * @author denny.zhang
     * @date 2018年2月6日下午5:19:43
     * @since JDK1.8
     */
    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) { if (less(a[i], a[i - 1])) { return false; } }
        return true;
    }
}