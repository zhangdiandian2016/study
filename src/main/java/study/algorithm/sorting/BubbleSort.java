package study.algorithm.sorting;

import study.algorithm.sorting.base.MySort;

/**
 * @author denny
 * @Description 冒泡排序
 * @date 2019/7/2 下午6:19
 */
public class BubbleSort extends MySort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        // 标记内层遍历是否交换过
        int flag = 0;
        // 外层遍历n-1次，每次确定一个最大值 a[i]
        for (int i = n - 1; i > 0; i--) {
            // 内层遍历:比较a[0]a[1]...a[n-1-1]a[n-1]
            for (int j = 0; j < i; j++) {
                // 相邻元素两两对比，如果后<前，交换
                if (less(a[j + 1], a[j])) {
                    // 元素交换
                    exch(a, j + 1, j);
                    flag = 1;
                }
            }
            //  flag=0，说明数列已有序不需要再交换了！！！
            if (flag == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort.sort(a);
        show(a);
    }
}
