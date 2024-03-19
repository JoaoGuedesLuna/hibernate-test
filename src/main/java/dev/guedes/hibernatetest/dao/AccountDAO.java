package dev.guedes.hibernatetest.dao;

import dev.guedes.hibernatetest.database.DatabaseException;
import dev.guedes.hibernatetest.model.Account;
import java.util.Optional;

/**
 * @author João Guedes
 */
public interface AccountDAO {

    void save(Account account) throws DatabaseException;

    Optional<Account> findById(Long id) throws DatabaseException;

    Optional<Account> findByEmail(String email) throws DatabaseException;

    void delete(Account account) throws DatabaseException;

}
