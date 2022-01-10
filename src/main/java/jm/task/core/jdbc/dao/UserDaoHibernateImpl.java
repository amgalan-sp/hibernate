package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS(ID int NOT NULL AUTO_INCREMENT, "+
                    " NAME VARCHAR(45), LASTNAME VARCHAR(45), AGE INT NULL, PRIMARY KEY (ID))").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Table created");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS USERS").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }  catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Table droped");
                e.printStackTrace();
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Complete");
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM USERS WHERE ID = ?").addEntity(User.class);
            query.setParameter(1, id);
            System.out.println("User deleted");
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList = (List<User>) Util.getSessionFactory().openSession().createQuery("From User").list();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE USERS").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }  catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("User tables cleaned");
                e.printStackTrace();
            }
        }
    }
}