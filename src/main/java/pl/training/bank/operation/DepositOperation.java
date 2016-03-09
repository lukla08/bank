package pl.training.bank.operation;

import pl.training.bank.entity.Account;

public class DepositOperation extends Operation {

    @Override
    public void execute() {
       Account account = accountsRepository.getByNumber(sourceAccountNumber);
       account.deposit(funds);
       accountsRepository.update(account);
    }

}
