package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionManager {
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public  static Connection getConnection()
    {
        Connection c;
        String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

        try {
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
        return c;

    }
}
