package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AccountsRepositoryImpl implements AccountsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account getByNumber(String number) {
        Account account = entityManager.createNamedQuery(Account.SELECT_BY_NUMBER_QL, Account.class)
                .setParameter("number", number)
                .getSingleResult();
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
