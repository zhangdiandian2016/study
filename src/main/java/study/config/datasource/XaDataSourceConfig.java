package study.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 分布式数据源配置类
 *
 * @author denny
 */
@Configuration
public class XaDataSourceConfig {

    /**
     * 主数据源：test:user表
     *
     * @return
     */
    @ConfigurationProperties(prefix = "study.datasource.master")
    @Bean(name = "masterDataSource")
    public AtomikosDataSourceBean masterDataSource() {
        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("url", "jdbc:mysql://localhost:3306/" + "test");
        p.setProperty("user", "root");
        p.setProperty("password", "12345");

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("atomikosDataSource1");
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setXaProperties(p);
        ds.setPoolSize(5);
        return ds;
    }

    /**
     * 从数据源：test2:user_msg表
     *
     * @return
     */
    @ConfigurationProperties(prefix = "study.datasource.slave")
    @Bean(name = "slaveDataSource")
    public AtomikosDataSourceBean slaveDataSource() {
        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("url", "jdbc:mysql://localhost:3306/" + "test2");
        p.setProperty("user", "root");
        p.setProperty("password", "12345");

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("atomikosDataSource2");
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setXaProperties(p);
        ds.setPoolSize(5);
        return ds;
    }

}
