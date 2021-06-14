package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getInstance().getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction t = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users (id INT(5) NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(50)," +
                    " lastName VARCHAR(50)," +
                    " age INT(3)," +
                    " PRIMARY KEY (id))";
            session.createSQLQuery(sql).executeUpdate();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException ignored) {

            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction t = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).executeUpdate();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException ignored) {

            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction t = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            t.commit();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException ignored) {

            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction t = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            t.commit();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException ignored) {

            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction t = null;
        Session session = null;
        List<User> result = null;
        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();
            result = session.createQuery("from User").list();
            t.commit();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException ignored) {

            }
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Transaction t = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();

            String sql = "TRUNCATE TABLE users";
            session.createSQLQuery(sql).executeUpdate();
        } catch (HibernateException e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException ignored) {

            }
        }
    }
}