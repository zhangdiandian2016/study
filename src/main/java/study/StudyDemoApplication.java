package study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author: denny
 * Date: 2017-10-12 上午11:28
 */
@SpringBootApplication
@EnableAsync
public class StudyDemoApplication {
    public static void main(final String[] args) {
        SpringApplication.run(StudyDemoApplication.class, args);
    }
}