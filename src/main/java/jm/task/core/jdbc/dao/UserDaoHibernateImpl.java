package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction t = null;
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            t = session.beginTransaction();
            String sql = "CREATE TABLE users (id INT(5) NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(50)," +
                    " lastName VARCHAR(50)," +
                    " age INT(3)," +
                    " PRIMARY KEY (id))";
            session.createSQLQuery(sql).executeUpdate();
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction t = null;
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            t = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";

            session.createSQLQuery(sql).executeUpdate();

            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction t = null;
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            t = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction t = null;
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            t = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction t = null;
        List<User> result = null;
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            t = session.beginTransaction();
            result = session.createQuery("from User").list();
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Transaction t = null;
        try {
            session = Util.getInstance().getSessionFactory().openSession();
            t = session.beginTransaction();

            String sql = "TRUNCATE TABLE users";

            session.createSQLQuery(sql).executeUpdate();

            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }
}