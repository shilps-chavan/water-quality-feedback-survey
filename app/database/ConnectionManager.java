package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  static Connection getConnection()
    {
        Connection c;

        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/feedbacksurvey",
                            "postgres", "admin");
            c.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
        return c;

    }
}
