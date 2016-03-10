package pl.training.bank.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import pl.training.bank.operation.ConsoleOperationLogger;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.AccountsService;
import pl.training.bank.service.MySQLIncrementalAccountNumberGenerator;
import pl.training.bank.service.repository.AccountsRepository;

import javax.sql.DataSource;

@Import(Persistence.class)
@EnableAspectJAutoProxy
@Configuration
public class Beans {

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean
    public AccountNumberGenerator accountNumberGenerator(DataSource dataSource) {
        return new MySQLIncrementalAccountNumberGenerator(dataSource);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AccountsService accountsService(AccountsRepository accountsRepository, AccountNumberGenerator accountNumberGenerator) {
        return new AccountsService(accountsRepository, accountNumberGenerator);
    }

    @Bean
    public ConsoleOperationLogger operationLogger() {
        return new ConsoleOperationLogger();
    }

}
