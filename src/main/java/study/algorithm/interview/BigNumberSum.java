package study.algorithm.interview;

/**
 * 大整数相加求和：可优化点：int -21-21亿，9位数妥妥的计算。拆分大整数每9位数一个元素，分别求和。效率可极大提升。
 * 时间复杂度：O（n）
 *
 * @author denny
 * @date 2019/9/5 下午5:50
 */
public class BigNumberSum {
    private static String bigNumberSum(String bigA, String bigB) {
        // 1.把2个大整数用数组逆序存储，数组长度等于较大整数位数+1
        int maxLength = bigA.length() > bigB.length() ? bigA.length() : bigB.length();
        int[] arrayA = new int[maxLength + 1];
        for (int i = 0; i < bigA.length(); i++) {
            arrayA[i] = bigA.charAt(bigA.length() - 1 - i) - '0';
        }
        int[] arrayB = new int[maxLength + 1];
        for (int i = 0; i < bigB.length(); i++) {
            arrayB[i] = bigB.charAt(bigB.length() - 1 - i) - '0';
        }
        // 2. 构建result数组
        int[] result = new int[maxLength + 1];

        // 3. 遍历数组，按位相加
        for (int i = 0; i < result.length; i++) {
            int temp = result[i];
            temp += arrayA[i];
            temp += arrayB[i];
            //是否进位
            if (temp >= 10) {
                temp = temp - 10;
                result[i + 1] = 1;
            }
            result[i] = temp;
        }

        //4.转成数组
        StringBuilder stringBuilder = new StringBuilder();
        // 是否找到大整数的最高有效位
        boolean findFirst = false;
        // 倒序遍历，即从最高位开始找非零数，找到一个就可以开始append了
        for (int i = result.length - 1; i >= 0; i--) {
            if (!findFirst) {
                if (result[i] == 0) {
                    continue;
                }
                findFirst = true;
            }
            stringBuilder.append(result[i]);
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        System.out.println(bigNumberSum("4534647452342423", "986568568789664"));
    }
}
