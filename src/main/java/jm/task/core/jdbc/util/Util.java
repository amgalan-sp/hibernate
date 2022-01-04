package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/springDB";
        String username = "taiphing";
        String password = "20081990";

        Connection connection = null;
        System.out.println("Connecting database...");
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return connection;
    }

//
//    public static Connection getMySQLConnection(String hostName, String dbName,
//                                                String userName, String password) throws SQLException,
//            ClassNotFoundException {
//        // Declare the class Driver for MySQL DB
//        // This is necessary with Java 5 (or older)
//        // Java6 (or newer) automatically find the appropriate driver.
//        // If you use Java> 5, then this line is not needed.
//        Class.forName("com.mysql.jdbc.Driver");
//
//        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
//
//        Connection conn = DriverManager.getConnection(connectionURL, userName,
//                password);
//        return conn;
//    }
}
