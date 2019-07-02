package study.algorithm.sorting;

import edu.princeton.cs.algs4.StdRandom;
import study.algorithm.sorting.base.MySort;

/**
 * @author denny.zhang
 * @ClassName:Quick
 * @Description:快速排序 <ul> <li>两向切分</li> <li>三向切分：重复元素多的时候</li> </ul>
 * @date 2018年2月7日下午5:50:56
 */
@SuppressWarnings("rawtypes")
public class Quick extends MySort {

    public static void sort(Comparable[] a) {
        //随机排列，避免数组有序出现最差排序
        StdRandom.shuffle(a);
        show(a);
        //两向切分
        sort(a, 0, a.length - 1);
        //三向切分
        //sort3Way(a, 0, a.length - 1);
        assert isSorted(a);
    }

    /**
     * @param a
     * @param lo
     * @param hi
     * @Description 两向切分快排
     * @author denny.zhang
     * @date 2018年2月7日下午4:36:48
     * @since JDK1.8
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;//
        }
        //拆分，找到拆分下标
        int j = partition(a, lo, hi);
        System.out.println("j=" + j);
        show(a);
        //左边排序，递归
        sort(a, lo, j - 1);
        //右边排序，递归
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    // 两向切分数组，找到拆分下标并返回，保证a[lo..j-1] <= a[j] <= a[j+1..hi]
    private static int partition(Comparable[] a, int lo, int hi) {
        System.out.println("lo=" + lo + ",hi=" + hi);
        //左指针
        int i = lo;
        //右指针
        int j = hi + 1;
        //初始化一个切分元素
        Comparable v = a[lo];
        //一个大的自循环
        while (true) {

            // 分支1：如果左指针元素小于v,++i，一直到右边界退出，或者左边有不小于v的元素停下，执行分支2
            while (less(a[++i], v)) { if (i == hi) { break; } }

            // 分支2：如果右指针元素大于v,j--,一直到到左边界退出，或者右边有不大于v的元素为止，执行分支3
            while (less(v, a[--j])) { if (j == lo) { break; } }

            // 分支3：如果左右指针碰撞，甚至左指针大于右指针，退出
            if (i >= j) { break; }
            // 交换需要交换的a[i]>=v>=a[j],使得a[i]<a[j]
            System.out.println("交换 a[i] a[j] i=" + i + ",a[i]=" + a[i] + ",j=" + j + ",a[j]=" + a[j]);
            exch(a, i, j);

        }
        System.out.println("i=" + i + ",j=" + j + ",设置 a[j]=" + a[j] + ",替换为a[lo]=" + a[lo]);
        // a[j]=a[lo],即v
        exch(a, lo, j);

        // 返回下标j
        return j;
    }

    //三向切分快排
    private static void sort3Way(Comparable[] a, int lo, int hi) {
        if (hi <= lo) { return; }
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) { exch(a, lt++, i++); } else if (cmp > 0) { exch(a, i, gt--); } else { i++; }
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
        assert isSorted(a, lo, hi);
    }

    public static void main(String[] args) {
        Quick.sort(a);
        show(a);
    }
}