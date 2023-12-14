package monproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex1 {
    private static final String URL = "jdbc:mysql://localhost:3306/tp_jdbc";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
    public static void selectData(Connection connection) throws SQLException {
        System.out.println("\nRécupération des données de la table etudiants:");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM etudiants")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                int age = resultSet.getInt("age");

                System.out.println("ID: " + id + ", Nom: " + nom + ", Prénom: " + prenom + ", Âge: " + age);
            }
        }
    }

    // Fonction pour insérer de nouvelles données
    public static void insertData(Connection connection, String nom, String prenom, int age) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO etudiants (nom, prenom, age) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setInt(3, age);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nouvel étudiant inséré avec succès.");
            } else {
                System.out.println("Échec de l'insertion de l'étudiant.");
            }
        }
    }

    public static void updateData(Connection connection, int id, String nom, String prenom, int age) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE etudiants SET nom = ?, prenom = ?, age = ? WHERE id = ?")) {

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Les informations de l'étudiant ont été mises à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour des informations de l'étudiant.");
            }
        }
    }

    public static void deleteData(Connection connection, int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM etudiants WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("L'étudiant a été supprimé avec succès.");
            } else {
                System.out.println("Échec de la suppression de l'étudiant.");
            }
        }
    }
}

