package study.config.datasource;

import com.okcoin.commons.support.mybatis.AbstractDataSourceConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * C2C业务数据源配置类
 *
 * @author zhiwei.deng
 * @date 2017-03-28
 **/
@Configuration
@MapperScan(basePackages = {DataSourceConfig.C2C_OPEN_PACKAGE},
    sqlSessionFactoryRef = "studySqlSessionFactory")
public class DataSourceConfig extends AbstractDataSourceConfig {
    static final String C2C_OPEN_PACKAGE = "study.repository";

    private static final String[] MAPPER_LOCATIONS = {
        "classpath*:mybatis/mapper/**/*.xml"
    };

    @Value("${study.datasource.master.type}")
    private Class<? extends DataSource> dataSourceType;

    @Primary
    @ConfigurationProperties(prefix = "study.datasource.master")
    @Bean(name = "studyDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .type(this.dataSourceType)
            .build();
    }

    @Primary
    @Bean(name = "studyTransactionManager")
    public DataSourceTransactionManager transactionManager(
        @Qualifier("studyDataSource") final DataSource dataSource) {
        return this.createTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "studySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("studyDataSource") final DataSource dataSource)
        throws Exception {
        return this.createSqlSessionFactory(dataSource, MAPPER_LOCATIONS);
    }

    @Primary
    @Bean(name = "studySqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("studySqlSessionFactory") final
                                                 SqlSessionFactory sqlSessionFactory)
        throws Exception {
        return this.createSqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "studyTransactionTemplate")
    public TransactionTemplate transactionTemplate(@Qualifier("studyTransactionManager") final
                                                   DataSourceTransactionManager transactionManager)
        throws Exception {
        return this.createTransactionTemplate(transactionManager);
    }
}
