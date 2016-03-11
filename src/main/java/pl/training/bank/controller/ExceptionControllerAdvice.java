package pl.training.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.bank.dto.ExceptionDTO;

import static pl.training.bank.dto.ExceptionDTO.ExceptionType;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> onNoSuchOperation() {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO(ExceptionType.APPLICATION_EXCEPTION), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
