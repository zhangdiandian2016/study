package study.algorithm.sorting.intSort;

import study.algorithm.sorting.base.MyIntSort;

/**
 * @author denny
 * @Description 基数排序：从低位到高位，一遍一遍排序。
 * @date 2019/7/10 上午11:40
 */
public class RadixIntSort extends MyIntSort {

    public static void sort(int[] arr) {
        // 求最高位
        int maxDigit = getMaxDigit(arr);
        // 基数排序
        radixSort(arr, maxDigit);

    }

    /**
     * 基数排序核心方法
     *
     * @param arr
     * @param maxDigit
     */
    private static void radixSort(int[] arr, int maxDigit) {
        // 取模数
        int mod = 10;
        // 除数
        int dev = 1;
        // 依次是：个位有序、十位有序（十位相同的个位有序）、百位有序（百位相同的个位、十位先后有序）...
        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // int[行][列] 行=桶 ,列=元素，如果要支持负数：mod->mod*2 其中 [0~(mod-1)]对应负数，[mod~(2mod-1)]对应正数 (bucket + 10),
            int[][] buckets = new int[mod][0];
            // 遍历数组arr
            for (int j = 0; j < arr.length; j++) {
                // 求arr[j]在counter中的index,如果要支持负数：index还得+mod，例如mod=10,-1+10=9，在负数里面是最大的。
                int index = ((arr[j] % mod) / dev);
                // 把arr[j]追加进桶buckets[index]
                buckets[index] = arrAppend(buckets[index], arr[j]);
            }
            int pos = 0;
            // 遍历所有桶
            for (int[] bucket : buckets) {
                // 每个桶元素赋值给arr
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }
    }

    /**
     * 求最高位数
     *
     * @param arr
     * @return
     */
    protected static int getMaxDigit(int[] arr) {
        // 最大值
        int maxValue = getMaxValue(arr);
        // 求长
        return getNumLenght(maxValue);
    }

    /**
     * 求数长度
     *
     * @param num
     * @return
     */
    protected static int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    public static void main(String[] args) {
        int[] a = new int[] {321, 60, 1, 21, 577, 11, 10, 743, 127};
        RadixIntSort.sort(a);
        show(a);
    }
}
