package study;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyDemoApplication.class)
public class BaseTest {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args)  {

        //System.out.println("线程："+Thread.currentThread().getName()+"，count="+count);
        //for(int i=0;i<100;i++){
        //    new Thread(new Runnable() {
        //        @Override
        //        public void run() {
        //            try {
        //                Thread.sleep(1000);
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            System.out.println("线程："+Thread.currentThread().getName()+"，count="+count.incrementAndGet());
        //        }
        //    }).start();
        //}
        //System.out.println("线程："+Thread.currentThread().getName()+"，count="+count);

        //Set<Integer> set = Arrays.stream(StringUtils.split("1000,20,30", ",")).map(x -> Integer.parseInt(x)).collect(Collectors.toSet());
        ////System.out.println(set.contains(1000));
        //Integer a=10;
        //Integer a1=10;
        //Integer b=10000;
        //Integer b1=10000;
        //System.out.println(a.equals(a1));
        //System.out.println(b.equals(b1));
        //System.out.println(b==b1);
        //System.out.println(a.hashCode());
        //System.out.println(a1.hashCode());
        //System.out.println(b.hashCode());
        //System.out.println(b1.hashCode());
        //List<Integer> list= Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        //list.remove(7);
        //list.add(0,8);
        //System.out.println(JSON.toJSON(list));

        BigDecimal cny = new BigDecimal("68439.2");
        BigDecimal rub = new BigDecimal("440486.5405");
        BigDecimal thb = new BigDecimal("2495.8");
        BigDecimal vnd = new BigDecimal("51962323.7580");
        BigDecimal cnyUsd = cny.divide(getRate(new BigDecimal("6.72")),2, RoundingMode.HALF_UP);
        BigDecimal rubUsd = rub.divide(new BigDecimal("65.87"),2, RoundingMode.HALF_UP);
        BigDecimal thbUsd = thb.divide(new BigDecimal("31.25"),2, RoundingMode.HALF_UP);
        BigDecimal vndUsd = vnd.divide(getRate(new BigDecimal("23190")),2, RoundingMode.HALF_UP);
        System.out.println(cnyUsd.add(vndUsd));

        // USD兑换法币的比率:USD/CNY = (USDT/CNY) / (USDT/USD)
    }

    private  static BigDecimal getRate(BigDecimal usdt2legal){
        return usdt2legal.divide(new BigDecimal("1"), 2, RoundingMode.HALF_UP);
    }

}
