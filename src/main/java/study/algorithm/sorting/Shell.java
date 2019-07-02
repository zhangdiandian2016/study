package study.algorithm.sorting;

import study.algorithm.sorting.base.MySort;

/**
 * @author denny.zhang
 * @ClassName:Shell
 * @Description:希尔排序:改进自插入排序，交换不相邻元素以对数组的局部进行排序，并最终用插入排序将局部有序的数组排序<br>
 * @date 2018年2月5日上午11:13:26
 */
public class Shell extends MySort {

    /**
     * @param a
     * @Description 更加符合要求的算法
     * @author denny.zhang
     * @date 2018年3月7日下午5:35:07
     * @since JDK1.8
     */
    public static void sort(Comparable[] a) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        //这里确保了每个子数组有>=3个元素,h间隔过大，每个子数组元素太少就没有排序的意义了
        while (h < n / 3) {
            //1.如果小于n/3,h变大,直到h>=n/3为止
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // h-sorting the array 2.遍历从h->n
            for (int i = h; i < n; i++) {
                //3.从j开始往左每h间隔比较一次，逆序就交换
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            assert isHsorted(a, h);
            //排完序再把h降低回来
            h /= 3;
        }
        assert isSorted(a);
    }

    /**
     * @param a
     * @Description d=n/2序列（向上取整）简单算法
     * @author denny.zhang
     * @date 2018年3月7日下午5:33:38
     * @since JDK1.8
     */
    @SuppressWarnings("rawtypes")
    public static void sort2(Comparable[] a) {
        int n = a.length;
        int h = n;
        //1.第一层循环 是h值计算
        while (h >= 1) {
            //向上取整
            h = (int)Math.ceil(h / 2);
            //2.第二层循环i++ 从h->n-1
            for (int i = h; i < n; i++) {
                //3.第三层循环 从j开始往左每h间隔比较一次，逆序就交换,做到局部有序
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            assert isHsorted(a, h);
        }
        assert isSorted(a);
    }

    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++) { if (less(a[i], a[i - h])) { return false; } }
        return true;
    }

    public static void main(String[] args) {
        Shell.sort(a);
        show(a);
    }
}