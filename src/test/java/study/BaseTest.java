package study;

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
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        BigDecimal bigDecimal = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(bigDecimal);
    }
}
