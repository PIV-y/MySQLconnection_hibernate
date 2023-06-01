package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String sqlCreateUsersTable = "CREATE TABLE IF NOT EXISTS `testkata`.`users` (" +
            "`id` BIGINT(255) NOT NULL AUTO_INCREMENT, " +
            "`name` VARCHAR(45) NULL , " +
            "`lastName` VARCHAR(45) NULL, " +
            "`age` TINYINT NULL, " +
            "PRIMARY KEY (`id`));";
    private final String sqlDropUsersTable = "DROP TABLE IF EXISTS testkata.users;";
    private static final SessionFactory sessionFactory = Util.getSession();

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlCreateUsersTable).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlDropUsersTable).executeUpdate();
            transaction.commit();
            System.out.println("Table has been deleted.");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(new User(name,lastName,age));
            transaction.commit();
            System.out.println("User has been added.");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        }
        System.out.println("User has been added.");
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            transaction.commit();
        }
        System.out.println("Table has been cleaned");
    }
}
