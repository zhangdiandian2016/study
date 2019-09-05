package study.algorithm.interview;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

/**
 * 寻找全排列的下一个数，又叫字典序算法，时间复杂度为O（n） 全排列：12345->54321
 * 核心原理：
 * 1.最后2位交换行不行？不行再最后3位.....从右往左找相邻array[index]>array[index-1] ，
 * 2.index-1和逆序列，从右往左中第一个比它大的值，交换 因为越往左边，交换后数越大，只有第一个才满足相邻。
 * 例如 12345-》12354   12354-第一步找到54数列，交换3和4-》12453--》12435
 * 12765->15762->15267
 * @author denny
 * @date 2019/9/5 下午2:18
 */
public class FindNextSortedNumber {

    private static int[] findNextSortedNumber(int[] numbers) {
        // 1.找到置换边界：从后向前查看逆序区域，找到逆序区域的第一位
        int index = findTransferPoint(numbers);
        System.out.println("index=" + index);
        // 整个数组逆序，没有更大的数了
        if (index == 0) {
            return null;
        }

        // copy一个新的数组，避免修改入参
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        // 2.把逆序区域的前一位和逆序区域中大于它的最小数交换位置
        exchangHead(numbersCopy, index);

        // 3.把原来的逆序转为顺序
        reverse(numbersCopy, index);
        return numbersCopy;
    }

    /**
     * 找到置换边界
     *
     * @param numbers
     * @return
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 把逆序区域的前一位和逆序区域中大于它的最小数交换位置
     *
     * @param numbers
     * @param index
     * @return
     */
    private static int[] exchangHead(int[] numbers, int index) {
        // 逆序区域前一位
        int head = numbers[index - 1];
        // 从后往前遍历
        for (int i = numbers.length - 1; i > 0; i--) {
            // 找到第一个大于head的数，和head交换。因为是逆序区域，第一个数就是最小数，所以找到第一个大于head的数，就是比head大的数中的最小数
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 逆序
     *
     * @param num
     * @param index
     * @return
     */
    private static int[] reverse(int[] num, int index) {
        for (int i = index, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        return num;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 5, 4};

        numbers = findNextSortedNumber(numbers);
        System.out.println(JSONObject.toJSONString(numbers));

    }
}
