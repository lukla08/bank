package pl.training.bank.operation;

import pl.training.bank.service.repository.AccountsRepository;

public abstract class Operation {

    protected AccountsRepository accountsRepository;
    protected String name;
    protected String sourceAccountNumber;
    protected String destinationAccountNumber;
    protected long funds;

    public String getName() {
        String className = getClass().getSimpleName();
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public long getFunds() {
        return funds;
    }

    public void setFunds(long funds) {
        this.funds = funds;
    }

    public void setAccountsRepository(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public abstract void execute();

}
