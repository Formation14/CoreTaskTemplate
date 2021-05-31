package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String createTable =
                    "CREATE TABLE users (id INT(5) NOT NULL AUTO_INCREMENT," +
                            " name VARCHAR(50)," +
                            " lastName VARCHAR(50)," +
                            " age INT(3)," +
                            " PRIMARY KEY (id))";
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE users");
            System.out.println("Таблица users была удалина");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            String saveUser = "INSERT INTO users (name, lastName, age) Values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(saveUser);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " --- User " + name + " был добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String removeUsersById = "DELETE FROM users WHERE id = " + id;
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(removeUsersById);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAllUsers = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(getAllUsers);
            while (rs.next()) {
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                int age = rs.getInt(4);
                users.add(new User(name, lastName, (byte) age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("Таблица Users была очищина!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}