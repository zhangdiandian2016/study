package study.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

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

    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;

    /**
     * 会话工厂
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "slaveSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(slaveDataSource);
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
