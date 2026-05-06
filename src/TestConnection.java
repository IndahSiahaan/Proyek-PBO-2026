import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class TestConnection {

    public static void main(String[] args) {
        System.out.println("=== Test Koneksi SQL Server ===");
        System.out.println("Mencoba koneksi...");

        try {
            DatabaseConnection dbInstance = DatabaseConnection.getInstance();
            Connection conn = dbInstance.getConnection();

            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("Koneksi BERHASIL!");
            System.out.println("Database : " + meta.getDatabaseProductName());
            System.out.println("Versi    : " + meta.getDatabaseProductVersion());
            System.out.println("User     : " + meta.getUserName());

            dbInstance.closeConnection();

        } catch (Exception e) {
            System.err.println("Koneksi GAGAL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}