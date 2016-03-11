package pl.training.bank.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExceptionDTO {

    public enum ExceptionType {

        APPLICATION_EXCEPTION, ACCOUNT_NOT_FOUND, INSUFFICIENT_FUNDS,UNKNOWN_OPERATION

    }

    private ExceptionType exceptionType;

    public ExceptionDTO(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

}
