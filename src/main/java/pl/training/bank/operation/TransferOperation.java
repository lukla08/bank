package pl.training.bank.operation;

public class TransferOperation extends Operation {

    private WithdrawOperation withdrawOperation;
    private DepositOperation depositOperation;

    public TransferOperation(String sourceAccountNumber, String destinationAccountNumber, long funds) {
        this.withdrawOperation = new WithdrawOperation(sourceAccountNumber, funds);
        this.depositOperation = new DepositOperation(destinationAccountNumber, funds);
    }

    @Override
    public void execute() {
        withdrawOperation.setAccountsRepository(accountsRepository);
        withdrawOperation.execute();

        depositOperation.setAccountsRepository(accountsRepository);
        depositOperation.execute();
    }

}
