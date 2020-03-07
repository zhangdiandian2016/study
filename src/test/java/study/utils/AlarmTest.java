package study.utils;


import org.junit.Test;
import study.BaseTest;
import study.alarm.Alarmer;

/***
 * @Description 预警工具类测试
 * @author denny.zhang
 * @date 2020/1/15 1:55 下午
 */
public class AlarmTest  extends BaseTest {
    @Test
    public void alarmTest() {
        Alarmer.defaultAlarmer().send("1111");
//        for(int i=0;i<100;i++){
//            Alarmer.defaultAlarmer().send("1111");
//        }
//        try {
//            Thread.sleep(10000);
//            System.out.println("end");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
