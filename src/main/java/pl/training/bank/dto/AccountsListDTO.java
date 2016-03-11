package pl.training.bank.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class AccountsListDTO {

    private int pageNumber;
    private int totalPages;
    private List<AccountDTO> accounts;

    public AccountsListDTO() {
    }

    public AccountsListDTO(int pageNumber, int totalPages, List<AccountDTO> accounts) {
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.accounts = accounts;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
