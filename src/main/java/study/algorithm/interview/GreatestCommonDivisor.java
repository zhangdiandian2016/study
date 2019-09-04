package study.algorithm.interview;

/**
 * 求2个整数的最大公约数 <p>1.暴力枚举法：时间复杂度O(min(a,b))</> <p>2.辗转相除法（欧几里得算法）: O(log(max(a,b))),但是取模运算性能较差</> <p>3.更相减损术：避免了取模运算，但性能不稳定，最坏时间复杂度：O（max(a,b)）</>
 * <p>4.更相减损术与位移结合:避免了取模运算，算法稳定，时间复杂度O（log(max(a,b))）</>
 *
 * @author denny
 * @date 2019/9/4 下午3:22
 */
public class GreatestCommonDivisor {

    /**
     * 暴力枚举法
     *
     * @param a
     * @param b
     * @return
     */
    private static int getGCD(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        // 能整除，直接返回
        if (big % small == 0) {
            return small;
        }
        // 从较小整数的一半开始~1，试图找到一个整数i，能被a和b同时整除。
        for (int i = small / 2; i > 1; i--) {
            if (small % i == 0 && big % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /**
     * 辗转相除法(欧几里得算法):两个正整数a>b,最大公约数=a/b的余数c和b之间的最大公约数，一直到可以整除为止
     *
     * @param a
     * @param b
     * @return
     */
    private static int getGCD2(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;

        // 能整除，直接返回
        if (big % small == 0) {
            return small;
        }

        return getGCD2(big % small, small);
    }

    /**
     * 更相减损术：两个正整数a>b,最大公约数=a-b和b之间的最大公约数,一直到两个数相等为止。
     *
     * @param a
     * @param b
     * @return
     */
    private static int getGCD3(int a, int b) {
        if (a == b) {
            return a;
        }

        int big = a > b ? a : b;
        int small = a < b ? a : b;

        return getGCD3(big - small, small);
    }

    /**
     * 更相减损术结合位移
     *
     * @param a
     * @param b
     * @return
     */
    private static int getGCD4(int a, int b) {
        if (a == b) {
            return a;
        }
        // 都是偶数,gcd(a,b)=2*gcd(a/2,b/2)=gcd(a>>1,b>>1)<<1
        if ((a & 1) == 0 && (b & 1) == 0) {
            return getGCD4(a >> 1, b >> 1) << 1;
            // a是偶数，b是奇数,gcd(a,b)=gcd(a/2,b)=gcd(a>>1,b)
        } else if ((a & 1) == 0 && (b & 1) == 1) {
            return getGCD4(a >> 1, b);
            // a是奇数，b是偶数
        } else if ((a & 1) == 1 && (b & 1) == 0) {
            return getGCD4(a, b >> 1);
            // 都是奇数
        } else {
            int big = a > b ? a : b;
            int small = a < b ? a : b;
            return getGCD4(big - small, small);
        }

    }

    public static void main(String[] args) {
        System.out.println("最大公约数=" + getGCD4(99, 21));
    }
}
