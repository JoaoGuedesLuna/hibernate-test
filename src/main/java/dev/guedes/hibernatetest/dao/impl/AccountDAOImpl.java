package dev.guedes.hibernatetest.dao.impl;

import dev.guedes.hibernatetest.dao.AccountDAO;
import dev.guedes.hibernatetest.database.DatabaseException;
import dev.guedes.hibernatetest.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
public class AccountDAOImpl implements AccountDAO {

    private final SessionFactory sessionFactory;

    public AccountDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Account account) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (account.getId() == null) {
                session.persist(account);
            } else {
                session.merge(account);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Optional<Account> findById(Long id) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Account.class, id));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Optional<Account> findByEmail(String email) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            Query<Account> query = session.createNamedQuery("Account.findByEmail", Account.class);
            query.setParameter("email", email);
            List<Account> accounts = query.getResultList();
            if (!accounts.isEmpty()) {
                return Optional.ofNullable(accounts.get(0));
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Account account) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

}
