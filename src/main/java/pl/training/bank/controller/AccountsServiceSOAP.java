package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import pl.training.bank.entity.Account;
import pl.training.bank.operation.Operation;
import pl.training.bank.service.AccountsService;
import pl.training.bank.service.repository.ResultPage;

import javax.jws.WebService;

@WebService(serviceName = "AccountsService", name = "AccountsService")
public class AccountsServiceSOAP extends SpringBeanAutowiringSupport {

    @Autowired
    private AccountsService accountsService;

    public Account createAccount() {
        return accountsService.createAccount();
    }

    public void process(Operation operation) {
        accountsService.process(operation);
    }

    public void init() {
        accountsService.init();
    }

    public void destroy() {
        accountsService.destroy();
    }

    public ResultPage<Account> getAccounts(int pageNumber, int pageSize) {
        return accountsService.getAccounts(pageNumber, pageSize);
    }

}
