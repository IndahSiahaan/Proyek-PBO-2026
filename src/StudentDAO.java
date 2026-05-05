import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAO {

    // INSERT STUDENT
    public static void insertStudent(String id, String name, String major) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "INSERT INTO students (id, name, major) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, major);

            ps.executeUpdate();

            System.out.println("Data berhasil ditambahkan!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE STUDENT
    public static void updateStudent(String id, String name, String major) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "UPDATE students SET name = ?, major = ? WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, major);
            ps.setString(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Data berhasil diupdate!");
            } else {
                System.out.println("ID tidak ditemukan.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}