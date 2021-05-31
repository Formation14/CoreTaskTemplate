package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {


    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Pavel","Telezhevich", (byte) 25);
        userService.saveUser("Andrey","Iaroschuk", (byte) 45);
        userService.saveUser("Sasha","Eglit", (byte) 32);
        userService.saveUser("Sergey","Korolev", (byte) 19);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
