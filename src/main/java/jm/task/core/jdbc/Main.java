package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        User user1 = new User((long)2,"Алекс","Болдуин",(byte)(42));
        User user2 = new User((long)3,"Док","Диккенс",(byte)(44));
        User user3 = new User((long)4,"Врунгель","Сан",(byte)(42));
        User user4 = new User((long)5,"Оуэн","Суинни",(byte)(32));

//        Util util = new Util();
//        util.getConnection();
        UserServiceImpl userServiceImpl = new UserServiceImpl();
//        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
//        userServiceImpl.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
//        userServiceImpl.saveUser(user3.getName(),user3.getLastName(),user3.getAge());

//        System.out.println(userServiceImpl.getAllUsers());
//        userServiceImpl.cleanUsersTable();
    }
}