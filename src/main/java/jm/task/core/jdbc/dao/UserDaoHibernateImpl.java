package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String SQL_CREATE_ESERS_TABLE = "CREATE TABLE IF NOT EXISTS `testkata`.`users` (" +
            "`id` BIGINT(255) NOT NULL AUTO_INCREMENT, " +
            "`name` VARCHAR(45) NULL , " +
            "`lastName` VARCHAR(45) NULL, " +
            "`age` TINYINT NULL, " +
            "PRIMARY KEY (`id`));";
    private final String SQL_DROP_USERS_TABLE = "DROP TABLE IF EXISTS testkata.users;";
    Session session = Util.getSession();

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery(SQL_CREATE_ESERS_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                    System.out.println("Session closed");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery(SQL_DROP_USERS_TABLE).executeUpdate();
            transaction.commit();
            System.out.println("Table has been deleted.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                    System.out.println("Session closed");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(new User(name,lastName,age));
            transaction.commit();
            System.out.println("User has been added.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                    System.out.println("Session closed");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                    System.out.println("Session closed");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        System.out.println("User has been added.");
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return session.createQuery("from User", User.class).list();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    System.out.println("Session closed");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if (session != null) {
                    session.close();
                    System.out.println("Session closed");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Table has been cleaned");
    }
}
