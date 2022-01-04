package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Алекс","Болдуин",(byte)(42));
        userServiceImpl.saveUser("Док","Диккенс",(byte)(44));
        userServiceImpl.saveUser("Врунгель","Санни",(byte)(42));
        userServiceImpl.saveUser("Оуэн","Суинни",(byte)(32));
        System.out.println(userServiceImpl.getAllUsers());
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}