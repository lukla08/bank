package pl.training.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.training.bank.entity.Account;
import pl.training.bank.operation.Operation;
import pl.training.bank.service.repository.AccountsRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountsService {

    private AccountsRepository accountsRepository;
    private AccountNumberGenerator accountNumberGenerator;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository, @Generator AccountNumberGenerator accountNumberGenerator) {
        this.accountsRepository = accountsRepository;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public Account createAccount() {
        Account account = new Account(accountNumberGenerator.getNext());
        accountsRepository.save(account);
        return account;
    }

    public void process(Operation operation) {
        operation.setAccountsRepository(accountsRepository);
        operation.execute();
    }

    public List<Account> getAccounts(int pageNumber, int pageSize) {
        return new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        System.out.println("AccountsService init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("AccountsService destroy...");
    }

}
