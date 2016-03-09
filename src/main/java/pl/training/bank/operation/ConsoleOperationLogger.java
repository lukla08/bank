package pl.training.bank.operation;

import pl.training.bank.BankException;

import java.text.NumberFormat;

public class ConsoleOperationLogger {

    private static final String SEPARATOR = "####################################################################";

    public void beforeProcessOperation(Operation operation) {
        System.out.println(SEPARATOR);
        System.out.println(operation);
    }

    public void onSuccess(Operation operation) {
        System.out.format("Status: SUCCESS\n%s\n", SEPARATOR);
    }

    public void onException(Operation operation, BankException ex) {
        System.out.format("Status: EXCEPTION (%s)\n%s\n", ex.getClass().getSimpleName(), SEPARATOR);
    }

}
