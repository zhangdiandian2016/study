package study.algorithm.interview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 红包拆分算法
 *
 * @author denny
 * @date 2019/9/11 上午10:37
 */
public class RedPackage {

    /**
     * 拆分红包
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
        // 遍历totalPeopleNum-1遍，最后一个人直接都给他
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            // [1,剩余人均金额的2倍-1]
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }

    public static void main(String[] args) {
        // 把10元红包拆分给10个人
        List<Integer> amountList = divideRedPackage(1000, 10);
        for (Integer amount : amountList) {
            System.out.println("抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
    }
}
