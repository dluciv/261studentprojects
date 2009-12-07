/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ConnectionProvider {

    private static Connection connection;
    public static String HOST = "localhost";
    public static String PORT = "1521";
    public static String DATABASE = "leidbase";
    public static String LOGIN = "db_practice";
    public static String PASSWORD = "";


    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException ex) {
                throw new ConfigurationException(ex);
            }
            connection = DriverManager.getConnection("jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + DATABASE, LOGIN, PASSWORD);
        }
        return connection;
    }
}
