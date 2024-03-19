package dev.guedes.hibernatetest.dao;

import dev.guedes.hibernatetest.database.DatabaseException;
import dev.guedes.hibernatetest.model.Order;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
public interface OrderDAO {

    void save(Order order) throws DatabaseException;

    Optional<Order> findById(Long id) throws DatabaseException;

    List<Order> findAllByAccountId(Long accountId) throws DatabaseException;

    void delete(Order order) throws DatabaseException;

}
