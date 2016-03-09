package pl.training.bank.operation;

import pl.training.bank.entity.Account;

public class DepositOperation extends Operation {

    public DepositOperation(String sourceAccountNumber, long funds) {
        super(sourceAccountNumber, funds);
    }

    @Override
    public void execute() {
       Account account = accountsRepository.getByNumber(sourceAccountNumber);
       account.deposit(funds);
       accountsRepository.update(account);
    }

}
