package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.UtilHb;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session = UtilHb.getSession();

    private final String sqlCreateUsersTable = "CREATE TABLE IF NOT EXISTS `testkata`.`users` (" +
            "`id` BIGINT(255) NOT NULL AUTO_INCREMENT, " +
            "`name` VARCHAR(45) NULL , " +
            "`lastName` VARCHAR(45) NULL, " +
            "`age` TINYINT NULL, " +
            "PRIMARY KEY (`id`));";

    @Override
    public void createUsersTable() {



    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session ses = session) {
            Transaction transaction = ses.beginTransaction();
            ses.persist(new User(name,lastName,age));
            transaction.commit();
        }
        System.out.println("User has been added.");
    }

    @Override
    public void removeUserById(long id) {
        try (Session ses = session) {
            Transaction transaction = ses.beginTransaction();
            ses.remove(ses.get(User.class, id));
            transaction.commit();
        }
        System.out.println("User has been added.");
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
