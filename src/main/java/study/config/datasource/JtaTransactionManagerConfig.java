package study.config.datasource;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

/**
 * 事务管理器配置类
 *
 * @author denny
 */
@Configuration
public class JtaTransactionManagerConfig {

    @Bean(name = "atomikosTransactionManager")
    public JtaTransactionManager regTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}