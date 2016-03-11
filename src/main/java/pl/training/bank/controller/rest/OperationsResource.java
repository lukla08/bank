package pl.training.bank.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.bank.dto.CustomObjectMapper;
import pl.training.bank.dto.ExceptionDTO;
import pl.training.bank.dto.OperationDTO;
import pl.training.bank.operation.InsufficientFundsException;
import pl.training.bank.operation.NoSuchOperationException;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.OperationResolver;
import pl.training.bank.service.AccountsService;
import pl.training.bank.service.repository.AccountNotFoundException;

import static pl.training.bank.dto.ExceptionDTO.*;

@RequestMapping("api/operations")
@RestController
public class OperationsResource {

    private AccountsService accountsService;
    private CustomObjectMapper objectMapper;
    private OperationResolver operationResolver;

    @Autowired
    public OperationsResource(AccountsService accountsService, CustomObjectMapper objectMapper, OperationResolver operationResolver) {
        this.accountsService = accountsService;
        this.objectMapper = objectMapper;
        this.operationResolver = operationResolver;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> process(@RequestBody OperationDTO operationDTO) {
        Operation operation = operationResolver.get(operationDTO.getName());
        operation.setSourceAccountNumber(operationDTO.getSourceAccountNumber());
        operation.setDestinationAccountNumber(operationDTO.getDestinationAccountNumber());
        operation.setFunds(operationDTO.getFunds());
        accountsService.process(operation);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> onAccountNotFound() {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(ExceptionType.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<?> onInsufficientFunds() {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(ExceptionType.INSUFFICIENT_FUNDS), HttpStatus.PRECONDITION_FAILED);
    }
//
//    @ExceptionHandler(NoSuchOperationException.class)
//    public ResponseEntity<?> onNoSuchOperation() {
//        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(ExceptionType.UNKNOWN_OPERATION), HttpStatus.BAD_REQUEST);
//    }

}
