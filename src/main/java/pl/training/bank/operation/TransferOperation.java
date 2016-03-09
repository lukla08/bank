package pl.training.bank.operation;

public class TransferOperation extends Operation {

    private WithdrawOperation withdrawOperation;
    private DepositOperation depositOperation;

    public TransferOperation(WithdrawOperation withdrawOperation, DepositOperation depositOperation) {
        this.withdrawOperation = withdrawOperation;
        this.depositOperation = depositOperation;
    }

    @Override
    public void execute() {
        withdrawOperation.setSourceAccountNumber(sourceAccountNumber);
        withdrawOperation.setFunds(funds);
        withdrawOperation.execute();

        depositOperation.setSourceAccountNumber(destinationAccountNumber);
        depositOperation.setFunds(funds);
        depositOperation.execute();
    }

}
