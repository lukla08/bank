package pl.training.bank.service;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

public class MySQLIncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER_SQL = "select max(number) from accounts a";

    private AtomicLong counter = new AtomicLong();

    public MySQLIncrementalAccountNumberGenerator(DataSource dataSource) {
        String lastAccountNumber = new JdbcTemplate(dataSource).queryForObject(SELECT_LAST_ACCOUNT_NUMBER_SQL, String.class);
        if (lastAccountNumber != null) {
            counter = new AtomicLong(Long.parseLong(lastAccountNumber));
        }
    }

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }

}
