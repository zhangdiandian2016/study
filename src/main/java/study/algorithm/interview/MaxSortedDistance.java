package study.algorithm.interview;

/**
 * 无序数组排序后的最大相邻差： 使用桶排序思想，每个桶元素遍历一遍即可，不需要再排序，时间复杂度O（n）
 *
 * @author denny
 * @date 2019/9/4 下午5:38
 */
public class MaxSortedDistance {

    private static class Bucket {
        Integer max;
        Integer min;
    }

    private static int getMaxSortedDistance(int[] array) {
        //1.求最大值最小值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        // 如果max=min,所有元素都相等，直接返回0
        if (d == 0) {
            return 0;
        }

        // 2. 初始化桶
        int bucketNum = array.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }
        // 3.遍历原始数组，确定每个桶的最大值最小值
        for (int i = 0; i < array.length; i++) {
            // 桶下标=当前元素偏移量/跨度  跨度=总偏移量/桶数-1
            int index = (array[i] - min) / (d / (bucketNum - 1));
            if (buckets[index].min == null || buckets[index].min > array[i]) {
                buckets[index].min = array[i];
            }
            if (buckets[index].max == null || buckets[index].max > array[i]) {
                buckets[index].max = array[i];
            }
        }
        // 4.遍历桶，找到最大差值
        int leftMax = buckets[0].max;
        int maxDistance = 0;
        // 从第二个桶开始计算
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i].min == null) {
                continue;
            }
            // 桶最大差值=右边最小值-左边最大值
            if (buckets[i].min - leftMax > maxDistance) {
                maxDistance = buckets[i].min - leftMax;
            }
            // 更新左边最大值为当前桶max
            leftMax = buckets[i].max;
        }
        return maxDistance;
    }

    public static void main(String[] args) {
        int[] array = new int[] {3, 4, 5, 9, 5, 6, 8, 1, 2};
        System.out.println(getMaxSortedDistance(array));
    }
}
