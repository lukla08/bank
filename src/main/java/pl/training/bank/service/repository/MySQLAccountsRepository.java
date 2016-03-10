package pl.training.bank.service.repository;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.training.bank.entity.Account;

import javax.sql.DataSource;

public class MySQLAccountsRepository implements AccountsRepository {

    private static final String INSERT_SQL = "insert into accounts values(null,:number,:balance)";
    private static final String UPDATE_SQL = "update accounts set balance = :balance where id = :id";
    private static final String SELECT_BY_NUMBER_SQL = "select * from accounts where number = :number";
    private static final int NO_UPDATES = 0;

    private NamedParameterJdbcTemplate jdbcTemplate;
    private AccountExtractor accountExtractor = new AccountExtractor();

    public MySQLAccountsRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Account save(Account account) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_SQL, new BeanPropertySqlParameterSource(account), keyHolder);
        account.setId(keyHolder.getKey().longValue());
        return account;
    }

    @Override
    public Account getByNumber(String sourceAccountNumber) {
        Account account = jdbcTemplate.query(SELECT_BY_NUMBER_SQL, new MapSqlParameterSource("number", sourceAccountNumber), accountExtractor);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    @Override
    public void update(Account account) {
        if (NO_UPDATES == jdbcTemplate.update(UPDATE_SQL, new BeanPropertySqlParameterSource(account))) {
            throw new AccountNotFoundException();
        }
    }

}
