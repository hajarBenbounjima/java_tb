package monproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tp_jdbc";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connexion établie.");
            Ex1.selectData(connection);
            Ex1.insertData(connection, "malika", "el", 45);
            Ex1.updateData(connection, 1, "tanina","chi",67);
            Ex1.deleteData(connection, 2);
            Ex1.selectData(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
