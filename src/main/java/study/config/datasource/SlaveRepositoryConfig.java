package study.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author denny
 * @Description 从库持久化配置
 * @date 2019/5/5 下午3:12
 */
@Configuration
@MapperScan(basePackages = {SlaveRepositoryConfig.MASTER_PACKAGE}, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveRepositoryConfig {

    static final String MASTER_PACKAGE = "study.repository.slave";

    private static final String MAPPER_LOCATIONS = "classpath*:mybatis/mapper/slave/**/*.xml";

    /**
     * 从数据源：test2:user_msg表
     *
     * @return
     */
    @ConfigurationProperties(prefix = "study.datasource.slave")
    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() {
        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("url", "jdbc:mysql://localhost:3306/" + "test2");
        p.setProperty("user", "root");
        p.setProperty("password", "12345");

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("slaveDataSource");
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setXaProperties(p);
        ds.setPoolSize(5);
        return ds;
    }

    /**
     * 会话工厂
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "slaveSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        //指定基包
        fb.setTypeAliasesPackage(MASTER_PACKAGE);
        //指定xml文件位置
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATIONS));
        return fb.getObject();
    }

    /**
     * 基于sqlSession的操作模板类
     *
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "slaveSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
