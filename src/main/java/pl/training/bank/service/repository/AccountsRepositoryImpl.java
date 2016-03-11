package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class AccountsRepositoryImpl implements AccountsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account getByNumber(String number) {
        try {
            return entityManager.createNamedQuery(Account.SELECT_BY_NUMBER_QL, Account.class)
                    .setParameter("number", number)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new AccountNotFoundException();
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
