package study;

import lombok.Data;
import org.junit.Test;
import study.service.UserBalanceService;
import study.service.UserMsgService;

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

    @Resource
    private UserMsgService userMsgService;

    /**
     * 申明式事务
     */
    @Test
    public void testAddUserBalanceAndUser(){
        userBalanceService.addUserBalanceAndUser(114, "赵六114", new BigDecimal(1000));
    }

    /**
     * 编程式事务transactionTemplate
     */
    @Test
    public void testAddUserBalanceAndUserWithinTT(){
        userBalanceService.addUserBalanceAndUserWithinTT(100, "赵六", new BigDecimal(1000));
    }


    /**
     * 动态数据源
     */
    @Test
    public void testDynamicDataSource(){

        userMsgService.addUserMsg(121,"121","消息121");
    }

    public static void main(String[] args) {
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
