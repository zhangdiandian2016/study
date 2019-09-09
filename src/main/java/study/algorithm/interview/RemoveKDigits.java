package study.algorithm.interview;

/**
 * 删除整数的k个数字，使得留下的数字最小
 * 时间复杂度：O（n）
 *
 * @author denny
 * @date 2019/9/5 下午4:43
 */
public class RemoveKDigits {

    /**
     * 删除整数的k个数字，使得留下的数字最小
     *
     * @param num
     * @param k
     * @return
     */
    private static String removeKDigits(String num, int k) {
        // 新长度
        int newLength = num.length() - k;
        // 创建栈，接收所有数字
        char[] stack = new char[num.length()];
        int top = 0;
        // 遍历，一开始先入栈第一个数字。第一轮循环先给stack入栈一个数，且top++,往后循环，top-1才是栈顶
        for (int i = 0; i < num.length(); ++i) {
            // 当前数字
            char c = num.charAt(i);
            // 当栈顶数字 > 当前数字时，栈顶数字出栈，只要没删除够K个就一直往左边删除
            while (top > 0 && stack[top - 1] > c && k > 0) {
                // 这里top-1，就是出栈，忽略top
                top -= 1;
                k -= 1;
            }
            // 后一个数字入栈
            stack[top++] = c;
        }
        // 找到栈中第一个非0数字的位置，以此构建新的字符串0000123->123
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits("1593212", 3));
        System.out.println(removeKDigits("10", 2));
    }
}
