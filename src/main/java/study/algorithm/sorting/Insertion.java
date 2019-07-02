package study.algorithm.sorting;

import study.algorithm.sorting.base.MySort;

/**
 * @author denny.zhang
 * @ClassName:Insertion
 * @Description:插入排序：对于数组长度为N的数组<br>就像扑克牌插纸牌一样 <ul> <li>比较次数：N-1~N²/2  平均N²/4 </li> <li>交换次数：0~N²/2 平均N²/4</li> </ul>
 * @date 2018年2月5日上午11:13:26
 */
public class Insertion extends MySort {

    @SuppressWarnings("rawtypes")
    public static void sort(Comparable[] a) {
        int n = a.length;
        //遍历n-1次，a[i]插入前i个数
        for (int i = 1; i < n; i++) {
            //System.out.println("i="+i);
            //i之前的每两项比较，出现逆序，立即交换
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Insertion.sort(a);
        show(a);
    }
}