package study.algorithm.sorting;

import study.algorithm.sorting.base.MySort;

/**
 * @author denny.zhang
 * @ClassName:Merge
 * @Description:归并排序：自顶向下+自底向上。性能差不多：比较次数：1/2NlgN-NlgN 访问次数6NlgN
 * @date 2018年2月6日下午5:09:57
 */
public class MergeSort extends MySort {

    /**
     * @param a
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     * @Description 归并
     * @author denny.zhang
     * @date 2018年2月6日下午4:55:25
     * @since JDK1.8
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // 复制数组a->aux
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // 归并进a数组
        int i = lo, j = mid + 1;
        //从aux数组找到小值并赋值给数组a
        for (int k = lo; k <= hi; k++) {
            //左半边用尽，取右半边元素aux[j]->赋值给a[k],并j++
            if (i > mid) { a[k] = aux[j++]; }
            //右半边用尽，取左半边元素aux[i]->赋值给a[k],并i++
            else if (j > hi) { a[k] = aux[i++]; }
            //aux[j]小->赋值给a[k],并j++
            else if (less(aux[j], aux[i])) { a[k] = aux[j++]; }
            //aux[i]小->赋值给a[k],并i++
            else { a[k] = aux[i++]; }
        }
    }

    // 自顶向下归并排序 a[lo..hi] 使用辅助数组 aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) { return; }
        int mid = lo + (hi - lo) / 2;
        //左半边排序
        sort(a, aux, lo, mid);
        //右半边排序
        sort(a, aux, mid + 1, hi);
        //归并结果
        merge(a, aux, lo, mid, hi);
    }

    /**
     * @param a
     * @Description 自底向上归并排序
     * @author denny.zhang
     * @date 2018年2月6日下午6:46:01
     * @since JDK1.8
     */
    public static void sortBU(Comparable[] a, Comparable[] aux) {
        int n = a.length;
        //len子数组大小，有多少个子数组遍历多少回
        for (int len = 1; len < n; len *= 2) {
            //子数组从2个元素开始自己归并：22归并->44归并->88归并...最后一个大归并
            //lo子数组起始下标
            for (int lo = 0; lo < n - len; lo += len + len) {
                int mid = lo + len - 1;
                //最后一个数组可能小于len,即不能按照2的倍数分小数组，最后一个取最小值
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    /**
     * @param a
     * @Description 归并排序
     * @author denny.zhang
     * @date 2018年2月6日下午4:52:45
     * @since JDK1.8
     */
    public static void sort(Comparable[] a) {
        //定义一个辅助数组
        Comparable[] aux = new Comparable[a.length];
        //自顶向下归并
        sort(a, aux, 0, a.length - 1);
        //自底向上归并
        //sortBU(a,aux);
        //校验是否排序成功
        assert isSorted(a);
    }

    public static void main(String[] args) {
        MergeSort.sort(a);
        show(a);
    }
}