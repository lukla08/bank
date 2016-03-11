package pl.training.bank.dto;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class AccountDTO {

    private long balance;
    private String number;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
