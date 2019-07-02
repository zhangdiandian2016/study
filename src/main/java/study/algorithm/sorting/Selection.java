package study.algorithm.sorting;

import study.algorithm.sorting.base.MySort;

/**
 * @author denny.zhang
 * @ClassName:Selection
 * @Description:选择排序：对于数组长度为N的数组<br> <ul> <li>比较次数：(n-1)+(n-2)+...2+1=n(n-1)/2,大约N²/2次</li> <li>交换次数：N次</li> </ul>
 * @date 2018年2月5日上午11:13:26
 */
public class Selection extends MySort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        //遍历n次，每次确定一个最小值
        for (int i = 0; i < n; i++) {
            //初始化最小值下标为i
            int min = i;
            //把i之后的每一项都和a[min]比较，求最小项
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            //a[i]和a[min]交换，第i位排序完毕
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Selection.sort(a);
        show(a);
    }
}