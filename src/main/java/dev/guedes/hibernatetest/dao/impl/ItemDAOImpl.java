package dev.guedes.hibernatetest.dao.impl;

import dev.guedes.hibernatetest.dao.ItemDAO;
import dev.guedes.hibernatetest.database.DatabaseException;
import dev.guedes.hibernatetest.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
public class ItemDAOImpl implements ItemDAO {

    private final SessionFactory sessionFactory;

    public ItemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Item item) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (item.getId() == null) {
                session.persist(item);
            } else {
                session.merge(item);
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
    public Optional<Item> findById(Long id) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Item.class, id));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Item> findAllByOrderId(Long orderId) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            Query<Item> query = session.createNamedQuery("Item.findAllByOrderId", Item.class);
            query.setParameter("orderId", orderId);
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Item item) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

}
