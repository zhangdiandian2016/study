package study.algorithm.sorting.intSort;

import study.algorithm.sorting.base.MyIntSort;

/**
 * 插入排序-整数版
 *
 * @author denny.zhang
 */
public class InsertionIntSort extends MyIntSort {

    public static void sort(int[] a) {
        int n = a.length;
        //遍历n-1次，a[i]插入前i个数
        for (int i = 1; i < n; i++) {
            //i之前的每两项比较，出现逆序，立即交换
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        InsertionIntSort.sort(a);
        show(a);
    }
}