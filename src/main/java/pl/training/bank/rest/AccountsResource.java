package pl.training.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.training.bank.dto.AccountDTO;
import pl.training.bank.dto.AccountsListDTO;
import pl.training.bank.dto.CustomObjectMapper;
import pl.training.bank.entity.Account;
import pl.training.bank.service.AccountsService;
import pl.training.bank.service.repository.ResultPage;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "api/accounts", produces = {"application/xml", "application/json"})
@RestController
public class AccountsResource {

    private AccountsService accountsService;
    private CustomObjectMapper objectMapper;

    @Autowired
    public AccountsResource(AccountsService accountsService, CustomObjectMapper objectMapper) {
        this.accountsService = accountsService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create() {
        Account account = accountsService.createAccount();
        return ResponseEntity.created(createUriWithId(account.getId())).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public AccountsListDTO getAccounts(@RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10")int pageSize) {
        ResultPage<Account> accounts = accountsService.getAccounts(pageNumber, pageSize);
        List<AccountDTO> accountDTOs = objectMapper.map(accounts.getContent(), AccountDTO.class);
        return new AccountsListDTO(pageNumber, accounts.getTotalPages(), accountDTOs);
    }


    private URI createUriWithId(long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
