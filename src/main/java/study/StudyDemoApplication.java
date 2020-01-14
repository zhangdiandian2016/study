package study;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * springboot启动 入口
 * @author: denny
 * Date: 2017-10-12 上午11:28
 */
@SpringBootApplication(exclude= {DruidDataSourceAutoConfigure.class})
@EnableAsync
@MapperScan("study.mapper")
public class StudyDemoApplication {
    public static void main(final String[] args) {
        SpringApplication.run(StudyDemoApplication.class, args);
    }
}