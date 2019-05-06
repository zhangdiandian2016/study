package study;

import lombok.Data;
import org.junit.Test;
import study.service.UserBalanceService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Description 用户余额测试类（事务）
 * @author denny
 * @date 2018/9/4 上午11:38
 */
public class UserBalanceTest extends BaseTest{

    @Resource
    private UserBalanceService userBalanceService;

    /**
     * 申明式事务
     */
    @Test
    public void testAddUserBalanceAndUser(){
        userBalanceService.addUserBalanceAndUser(100, "赵六", new BigDecimal(1000));
    }

    /**
     * 编程式事务transactionTemplate
     */
    @Test
    public void testAddUserBalanceAndUserWithinTT(){
        userBalanceService.addUserBalanceAndUserWithinTT(100, "赵六", new BigDecimal(1000));
    }

    public static void main(String[] args) {

        //BigDecimal bigDecimal =new BigDecimal(0.0000000000000000);
        //System.out.println(BigDecimal.ZERO.equals(bigDecimal));

        //List<String> list = Arrays.asList("aaaaaa","bbbb","cc","dd","f");
        //list.sort(Comparator.comparingInt(String::length));
        //System.out.println(list);
        ////AbstractPlatformTransactionManager
        //Double d = 20000000D;
        //System.out.println(new BigDecimal(d).toPlainString());
        //BigDecimal bigDecimal = new BigDecimal(0.00009);
        //System.out.println(bigDecimal);
        //System.out.println(bigDecimal+"");
        //System.out.println(bigDecimal.setScale(10, RoundingMode.DOWN));
        //System.out.println(bigDecimal.setScale(10, RoundingMode.DOWN).doubleValue());
        //System.out.println(bigDecimal.setScale(10, RoundingMode.DOWN).stripTrailingZeros());
        //System.out.println(bigDecimal.toString());
        //System.out.println(bigDecimal.toPlainString());
        //System.out.println(bigDecimal.stripTrailingZeros().toPlainString());

        //Boy1 boy1 = new Boy1();
        //boy1.setId(111L);
        ////boy1.setIsBuy(true);
        //
        //Boy2 boy2 = new Boy2();
        ////boy2.setId(222L);
        //// boy-->tod
        //try {
        //    BeanUtils.copyProperties(boy2, boy1);
        //} catch (IllegalAccessException e) {
        //    e.printStackTrace();
        //} catch (InvocationTargetException e) {
        //    e.printStackTrace();
        //}
        //
        //System.out.println("boy1  id="+boy1.getId());
        //System.out.println("boy2  id="+boy2.getId());

        // System.out.println("date="+new Date(1545993487000L));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date a = null;
        try {
            a = sdf.parse("2019-02-21 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(a.after(new Date()));

    }

    @Data
    static class Boy1{

        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    @Data
    static class Boy2{

        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

}
