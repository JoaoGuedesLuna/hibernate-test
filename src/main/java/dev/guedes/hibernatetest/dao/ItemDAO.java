package dev.guedes.hibernatetest.dao;

import dev.guedes.hibernatetest.database.DatabaseException;
import dev.guedes.hibernatetest.model.Item;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
public interface ItemDAO {

    void save(Item item) throws DatabaseException;

    Optional<Item> findById(Long id) throws DatabaseException;

    List<Item> findAllByOrderId(Long orderId) throws DatabaseException;

    void delete(Item item) throws DatabaseException;

}
