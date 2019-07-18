package study.algorithm.sorting.intSort;

import study.algorithm.sorting.base.MySort;

/**
 * @author denny
 * @Description 计数排序，利用数组下标有序，新构造一个bucket[max-min+1],计数后，把新数组的下标反向赋值给原数组。 为什么要求min? 避免不必要的空间浪费。比如90~99，如果开辟99+1=100，浪费太多空间了。现在只需要99-90+1=10的空间
 * 时间复杂度：O[n+k],k = max-min+1 空间复杂度：O[n+k]
 * @date 2019/7/8 上午10:21
 */
public class CountingSort extends MySort {

    public static void sort(Integer[] b) {
        // 1.遍历一遍，求最大值,最小值
        int max = b[0], min = b[0];
        for (int i : b) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int k = max - min + 1;
        // 构造长度为max+1的数组bucket
        int[] bucket = new int[k];

        // 2.遍历一遍b[], 以b元素-min值作为下标，统计出现次数赋值给bucket[]
        for (int value : b) {
            bucket[value - min]++;
        }

        int sortedIndex = 0;
        // 3.遍历一遍bucket[],反过来给b赋值，j=元素与最小值差值
        for (int j = 0; j < k; j++) {
            // 重复元素赋值，bucket[j]元素重复出现次数，bucket的下标是b的元素值
            while (bucket[j] > 0) {
                // bucket的下标是b的元素值，j+min=元素值
                b[sortedIndex++] = j + min;
                bucket[j]--;
            }
        }

    }

    public static void main(String[] args) {
        CountingSort.sort(b);
        show(b);
    }
}
