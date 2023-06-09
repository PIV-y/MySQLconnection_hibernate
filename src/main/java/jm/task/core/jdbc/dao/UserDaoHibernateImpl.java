package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String SQL_CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS `testkata`.`users` (" +
            "`id` BIGINT(255) NOT NULL AUTO_INCREMENT, " +
            "`name` VARCHAR(45) NULL , " +
            "`lastName` VARCHAR(45) NULL, " +
            "`age` TINYINT NULL, " +
            "PRIMARY KEY (`id`));";
    private static final String SQL_DROP_USERS_TABLE = "DROP TABLE IF EXISTS testkata.users;";
    private static final SessionFactory SESSION_FACTORY = Util.getSession();

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(SQL_CREATE_USERS_TABLE).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(SQL_DROP_USERS_TABLE).executeUpdate();
            transaction.commit();
            System.out.println("Table has been deleted.");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            session.persist(new User(name,lastName,age));
            transaction.commit();
            System.out.println("User has been added.");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        System.out.println("User has been added.");
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = new LinkedList<>();
        try (Session session = SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            list = session.createQuery("from User", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        System.out.println("Table has been cleaned");
    }
}
