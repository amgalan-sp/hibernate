package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserServiceImpl implements UserDao {

    Connection connection = Util.getConnection();
    public void createUsersTable() throws SQLException {
        PreparedStatement create = null;
        String sql = "CREATE TABLE IF NOT EXISTS USERS(ID int NOT NULL AUTO_INCREMENT, NAME VARCHAR(45), " +
                "LASTNAME VARCHAR(45), AGE INT NULL, PRIMARY KEY (ID))";
        try {
            create = connection.prepareStatement(sql);
            create.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            System.out.println("Complete");
            if (create != null) {
                create.close();
            }
            if (connection != null) {
                connection.close();
            }
            connection.close();
        }
    }

    public void dropUsersTable() throws SQLException {
        PreparedStatement create = null;
        String sql = "DROP TABLE IF EXISTS USERS";
        try {
            create = connection.prepareStatement(sql);
            create.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            System.out.println("Droped");
            if (create != null) {
                create.close();
            }
            if (connection != null) {
                connection.close();
            }
            connection.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE)  VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            connection.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            System.out.println("User droped by ID");
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            connection.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE from USERS";
        Statement statement = null;
        try {
            statement =  connection.createStatement();
            ResultSet  resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge((byte) resultSet.getInt("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "TRUNCATE TABLE USERS";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            System.out.println("TABLE CLEANES UP");
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            connection.close();
        }
    }
}
