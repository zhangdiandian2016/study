package study.algorithm.sorting.intSort;

import study.algorithm.sorting.base.MyIntSort;

import java.util.Arrays;

/**
 * @author denny
 * @Description 桶排序
 * @date 2019/7/9 下午4:03
 */
public class BucketSort extends MyIntSort {

    public static void sort(int[] arr) {
        // 每个桶能存储5个元素
        bucketSort(arr, 5);
    }

    /**
     * 桶排序
     *
     * @param arr        需要排序的数组
     * @param bucketSize 每个桶的容量
     */
    public static void bucketSort(int[] arr, Integer bucketSize) {
        // 1.遍历一遍，求最大值,最小值
        int max = arr[0], min = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        // 桶个数
        int bucketCount = (int)Math.floor((max - min) / bucketSize) + 1;
        // 构造一个二维数组,行=桶个数，列=桶容量，初始为0
        int[][] buckets = new int[bucketCount][0];

        // 2.利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.length; i++) {
            // arr[i]在哪个桶中
            int index = (int)Math.floor((arr[i] - min) / bucketSize);
            // 把arr[i]追加进桶 buckets[index]
            buckets[index] = arrAppend(buckets[index], arr[i]);
        }

        int arrIndex = 0;
        // 3.遍历所有桶，赋值
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            // 对每个桶内部元素排序，这里使用了插入排序
            InsertionIntSort.sort(bucket);
            // 遍历每个桶中的元素，赋值给原始数组
            for (int value : bucket) {
                arr[arrIndex++] = value;
            }
        }

    }

    /**
     * 自动扩容1位，并保存数据
     *
     * @param arr
     * @param value
     */
    public static int[] arrAppend(int[] arr, int value) {
        // 扩容1
        arr = Arrays.copyOf(arr, arr.length + 1);
        // 最后一位赋值
        arr[arr.length - 1] = value;
        return arr;
    }

    public static void main(String[] args) {
        // 桶排序
        BucketSort.sort(a);
        // 查看排序结果
        show(a);
    }

}
