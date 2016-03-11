package pl.training.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.bank.dto.ExceptionDTO;
import pl.training.bank.operation.NoSuchOperationException;

import static pl.training.bank.dto.ExceptionDTO.*;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoSuchOperationException.class)
    public ResponseEntity<?> onNoSuchOperation() {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(ExceptionType.APPLICATION_EXCEPTION), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
