package study;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyDemoApplication.class)
public class BaseTest {

    public static void main(String[] args) {
        //java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        //BigDecimal bigDecimal = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN);
        //System.out.println(bigDecimal);

        //User user1 = new User(1, "张三", new BigDecimal(1));
        //User user2 = new User(2, "李四", new BigDecimal(2));
        //List<User> userList = Lists.newArrayList(user1, user2);
        //System.out.println("max id=" + userList.stream().max(Comparator.comparing(User::getMoney)).get().getMoney());

        BigDecimal bigDecimal = new BigDecimal("7.0799");

        //System.out.println(bigDecimal.setScale(2, RoundingMode.DOWN).doubleValue());
        String a = JSON.toJSONString(null);
        System.out.println(a.equals("null"));

    }
}
