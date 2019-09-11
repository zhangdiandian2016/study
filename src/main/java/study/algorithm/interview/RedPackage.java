package study.algorithm.interview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 红包拆分算法
 * 要求：
 * 1.每个人至少抢到一分钱。
 * 2.所有人抢到金额之和等于红包金额，不能超过，也不能少于。
 * 3.要保证所有人抢到金额的几率相等。
 *
 * @author denny
 * @date 2019/9/11 上午10:37
 */
public class RedPackage {

    /**
     * 拆分红包：二分均值法（每次抢红包的平均值是相等的）
     *
     * @param totalAMount    总金额，单位：分
     * @param totalPeopleNum 总人数
     * @return
     */
    public static List<Integer> divideRedPackage(Integer totalAMount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>();
        // 余额
        Integer restAmount = totalAMount;
        // 没抢人数
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        // 遍历totalPeopleNum-1遍，最后一个人直接把余下的红包都给他
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            // [1,剩余人均金额的2倍-1]
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        // 最后一个人，余下的红包都给他
        amountList.add(restAmount);
        return amountList;
    }

    public static List<Integer> divideRedPackage2(Integer totalAmount, Integer totalPeopleNum) {
        // 切割点list
        List<Integer> indexList = new ArrayList<>();
        // 红包list
        List<Integer> amountList = new ArrayList<>();
        Random random = new Random();

        // 构造n-1个切割点
        while (indexList.size() <= totalPeopleNum - 1) {
            // 总金额随机+1分
            int i = random.nextInt(totalAmount - 1) + 1;
            // i不在list中，非重复切割添加到集合
            if (indexList.indexOf(i) < 0) {
                indexList.add(i);
            }
        }
        // 排序.升序排列，从小到大，刚好n-1个切割点把总金额切割成n份。
        Collections.sort(indexList);
        // 上一次index
        int flag = 0;
        // 红包之和
        int fl = 0;
        // 遍历全部切割点
        for (int i = 0; i < indexList.size(); i++) {
            // 当前红包=index-上一次index
            int temp = indexList.get(i) - flag;
            // 记录index
            flag = indexList.get(i);
            // 求和
            fl += temp;
            // 当前红包添加进list
            amountList.add(temp);
        }
        //最后一个红包=总金额-已发红包之和
        amountList.add(totalAmount - fl);
        return amountList;
    }

    public static void main(String[] args) {
        //1.=====二分均值法======
        System.out.println("========二分均值法===========");
        // 把10元红包拆分给10个人
        List<Integer> amountList = divideRedPackage(1000, 10);
        for (Integer amount : amountList) {
            System.out.println("抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }

        System.out.println("===================");
        //2.=====线段切割法======
        System.out.println("========线段切割法===========");
        List<Integer> amountList2 = divideRedPackage2(1000, 10);
        BigDecimal total = BigDecimal.ZERO;
        for (Integer amount : amountList2) {
            total = total.add(new BigDecimal(amount));
            System.out.println("抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
        System.out.println("总金额=" + total + "分");
    }


}
