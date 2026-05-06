package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
        "jdbc:sqlserver://localhost:1433;" +
        "databaseName=academic_db;" +
        "encrypt=true;" +
        "trustServerCertificate=true";

    private static final String USER     = "sa";
    private static final String PASSWORD = "laura";

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("[DB] Koneksi berhasil ke SQL Server.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver tidak ditemukan.", e);
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[DB] Koneksi ditutup.");
            }
        } catch (SQLException e) {
            System.err.println("[DB] Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
