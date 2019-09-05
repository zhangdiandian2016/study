package study.algorithm.interview;

/**
 * 判断是否是2的整数次幂：时间复杂度
 * 时间复杂度是O（1）
 *
 * @author denny
 * @date 2019/9/4 下午5:18
 */
public class PowerOf2 {

    /**
     * 判断是否是2的整数次幂： 2的整数次幂转换成二进制（1+n个0）& 二进制-1（n个1）=0
     * @param num
     * @param a
     * @return boolean
     * @author denny
     * @date 2019/9/5 上午11:14
     */
    private static boolean isPowerOf2(int num, int a) {
        return (num & (num - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println("是否2的整数次幂=" + isPowerOf2(16, 1));
    }
}
