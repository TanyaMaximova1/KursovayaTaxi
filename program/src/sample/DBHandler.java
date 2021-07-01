package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Подключение к базе данных.
 */
public class DBHandler {
    protected Connection connection;
    private static String LOGIN = "root";
    private static String PASSWORD = "tAtA05072000";
    private static String DB_URL = "jdbc:mysql://localhost:3306/taxi";

    /**
     * Подключается к базе данных.
     * @return подключение к базе данных.
     */
    public Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        try
        {
            connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        } catch (SQLException throwables)
        {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, throwables);
        }
        return connection;
    }
}
