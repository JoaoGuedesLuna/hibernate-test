package dev.guedes.hibernatetest.dao;

import dev.guedes.hibernatetest.dao.impl.AccountDAOImpl;
import dev.guedes.hibernatetest.dao.impl.ItemDAOImpl;
import dev.guedes.hibernatetest.dao.impl.OrderDAOImpl;
import dev.guedes.hibernatetest.dao.impl.ProductDAOImpl;
import dev.guedes.hibernatetest.database.Database;
import dev.guedes.hibernatetest.database.DatabaseException;

/**
 * @author João Guedes
 */
public class DAOFactory {

    public static AccountDAO createAccountDAO() throws DatabaseException {
        return new AccountDAOImpl(Database.getSessionFactory());
    }

    public static ProductDAO createProductDAO() throws DatabaseException {
        return new ProductDAOImpl(Database.getSessionFactory());
    }

    public static OrderDAO createOrderDAO() throws DatabaseException {
        return new OrderDAOImpl(Database.getSessionFactory());
    }

    public static ItemDAO createItemDAO() throws DatabaseException {
        return new ItemDAOImpl(Database.getSessionFactory());
    }

}
