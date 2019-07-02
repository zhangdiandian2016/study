package study.algorithm.sort;

/**
 * @author denny.zhang
 * @ClassName:Heap
 * @Description:堆排序：对于数组长度为N的数组<br> <ul> <li>比较次数：2NlgN+2N</li> <li>交换次数：NlgN+N</li> </ul>
 * @date 2018年2月5日上午11:13:26
 */
public class Heap extends MySort {

    /**
     * @param pq
     * @Description 排序
     * @author denny.zhang
     * @date 2018年3月2日下午2:28:38
     * @since JDK1.8
     */
    @SuppressWarnings("rawtypes")
    public static void sort(Comparable[] pq) {
        //这样数组下标好算一点。原来下标0~6==>1~6
        int n = pq.length - 1;
        //1.构造有序堆（父节点大于等于子节点）,从k=n/2--》1
        for (int k = n / 2; k >= 1; k--) { sink(pq, k, n); }
        //2.下沉排序，升序排序
        while (n > 1) {
            //交换根节点和首节点，后n--交换完一次就剔除最后那个已排序的元素
            exch(pq, 1, n--);
            //修复有序堆
            sink(pq, 1, n);
        }
    }

    /**
     * @param pq
     * @param k  根节点
     * @param n  堆长度
     * @Description 从上至下的堆有序化的实现
     * @author denny.zhang
     * @date 2018年3月2日上午10:28:36
     * @since JDK1.8
     */
    @SuppressWarnings("rawtypes")
    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            //左子节点下标
            int j = 2 * k;
            //找到子节点大值（pq[j]如果小于pq[j+1]就j++一直找，一直到大于等于的j），即pq[j]为左右子节点最大值
            if (j < n && less(pq[j], pq[j + 1])) { j++; }
            //如果父节点大于等于子节点大值，则堆有序，退出当前循环
            if (!less(pq[k], pq[j])) { break; }
            //否则交换根节点和子节点大值
            exch(pq, k, j);
            //跟节点下标变为j,即下沉到j
            k = j;
        }
    }

    public static void main(String[] args) {
        //第一个元素空着不用排序，这样数组下标好算一点。排序结果：0 a b c d e
        String[] a = new String[] {"0", "d", "c", "b", "e", "a"};
        Heap.sort(a);
        show(a);
    }
}