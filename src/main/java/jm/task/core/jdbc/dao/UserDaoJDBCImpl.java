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
        String createTable =
                "CREATE TABLE users (id INT(5) NOT NULL AUTO_INCREMENT," +
                        " name VARCHAR(50)," +
                        " lastName VARCHAR(50)," +
                        " age INT(3)," +
                        " PRIMARY KEY (id))";
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTable);
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("Таблица users была удалина");
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser =
                "INSERT users (name, lastName, age) VALUES('"
                        + name + "', '"
                        + lastName + "', '"
                        +  age + "')";
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(saveUser);
            System.out.println("User " + name + " был добавлен");
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String removeUsersById = "DELETE FROM users WHERE id = " + id;
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(removeUsersById);
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAllUsers = "SELECT * FROM users";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(getAllUsers);
            while (rs.next()) {
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                int age = rs.getInt(4);
                users.add(new User(name, lastName, (byte) age));
            }
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("Таблица Users была очищина!");
        } catch (SQLSyntaxErrorException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
