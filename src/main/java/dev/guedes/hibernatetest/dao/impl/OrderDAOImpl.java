package dev.guedes.hibernatetest.dao.impl;

import dev.guedes.hibernatetest.dao.OrderDAO;
import dev.guedes.hibernatetest.database.DatabaseException;
import dev.guedes.hibernatetest.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
public class OrderDAOImpl implements OrderDAO {

    private final SessionFactory sessionFactory;

    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Order order) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (order.getId() == null) {
                session.persist(order);
            } else {
                session.merge(order);
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
    public Optional<Order> findById(Long id) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Order.class, id));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Order> findAllByAccountId(Long accountId) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            Query<Order> query = session.createNamedQuery("Order.findAllByAccountId", Order.class);
            query.setParameter("accountId", accountId);
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Order order) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

}
