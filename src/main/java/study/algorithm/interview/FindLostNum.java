package study.algorithm.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 无序数组里有99个不重复整数，1-100，缺少一个。如何找到这个缺失的整数？
 *
 * @author denny
 * @date 2019/9/9 上午11:05
 */
public class FindLostNum {
    /**
     * 直接求和然后遍历减去全部元素即可 时间复杂度：O（1） 空间复杂度：
     *
     * @param array
     * @return
     */
    private static int findLostNum(Integer[] array) {
        // 1-100求和
        int sum = ((1 + 100) * 100) / 2;
        for (int a : array) {
            sum -= a;
        }
        return sum;
    }

    /**
     * 一个无序数组里有若干个正整数，范围是1~100，其中98个整数都出现了偶数次。只有2个整数出现了奇数次，求奇数次整数？ 利用异或运算的"相同为0，不同为1"，出现偶数次的偶数异或变0，最后只有奇数次的整数留下。 时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param array
     * @return
     */
    private static int[] findLostNum2(Integer[] array) {
        // 存储2个出现奇数次的整数
        int result[] = new int[2];
        // 第一次进行整体异或运算
        int xorResult = 0;
        for (int i = 0; i < array.length; i++) {
            xorResult ^= array[i];
        }
        //确定2个整数的不同位，以此来做分组
        int separtor = 1;
        while (0 == (xorResult & separtor)) {
            separtor <<= 1;
        }
        //第二次分组进行异或运算
        for (int i = 0; i < array.length; i++) {
            if (0 == (array[i] & separtor)) {
                result[0] ^= array[i];
            } else {
                result[1] ^= array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        // 除了85，其它赋值
        for (int i = 0; i < 100; i++) {
            list.add(i + 1);
        }
        list.remove(10);
        System.out.println("缺失的数=" + findLostNum(list.toArray(new Integer[99])));

        Integer[] array = new Integer[] {4, 1, 2, 2, 5, 1, 4, 3};
        int[] result = findLostNum2(array);
        System.out.println(result[0] + "," + result[1]);
    }
}
