//package study.config.datasource;
//
//import com.okcoin.commons.support.mybatis.AbstractDataSourceConfig;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import javax.sql.DataSource;
//
///**
// * C2C业务数据源配置类
// *
// * @author zhiwei.deng
// * @date 2017-03-28
// **/
//@Configuration
//@MapperScan(basePackages = {DataSourceConfig.MASTER_PACKAGE},
//    sqlSessionFactoryRef = "masterSqlSessionFactory")
//public class DataSourceConfig extends AbstractDataSourceConfig {
//    static final String MASTER_PACKAGE = "study.repository.master";
//
//    private static final String[] MAPPER_LOCATIONS = {
//        "classpath*:mybatis/mapper/master/**/*.xml"
//    };
//
//    @Value("${study.datasource.master.type}")
//    private Class<? extends DataSource> dataSourceType;
//
//    @Primary
//    @ConfigurationProperties(prefix = "study.datasource.master")
//    @Bean(name = "masterDataSource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create()
//            .type(this.dataSourceType)
//            .build();
//    }
//
//    @Primary
//    @Bean(name = "masterTransactionManager")
//    public DataSourceTransactionManager transactionManager(
//        @Qualifier("masterDataSource") final DataSource dataSource) {
//        return this.createTransactionManager(dataSource);
//    }
//
//    @Primary
//    @Bean(name = "masterSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") final DataSource dataSource)
//        throws Exception {
//        return this.createSqlSessionFactory(dataSource, MAPPER_LOCATIONS);
//    }
//
//    @Primary
//    @Bean(name = "masterSqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("masterSqlSessionFactory") final
//                                                 SqlSessionFactory sqlSessionFactory)
//        throws Exception {
//        return this.createSqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Primary
//    @Bean(name = "masterTransactionTemplate")
//    public TransactionTemplate transactionTemplate(@Qualifier("masterTransactionManager") final
//                                                   DataSourceTransactionManager transactionManager)
//        throws Exception {
//        return this.createTransactionTemplate(transactionManager);
//    }
//
//}
