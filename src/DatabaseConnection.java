import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=academic_db;encrypt=true;trustServerCertificate=true";

    private static final String USER = "sa";
    private static final String PASSWORD = "laura";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}