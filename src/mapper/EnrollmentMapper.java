package mapper;

import db.DatabaseConnection;
import model.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Mapper untuk entitas Enrollment.
 * Tabel: enrollments (student_id VARCHAR, course_code VARCHAR, grade FLOAT, semester VARCHAR)
 * Primary key: composite (student_id + course_code + semester)
 */
public class EnrollmentMapper implements Mapper<Enrollment> {

    private Connection conn;

    public EnrollmentMapper() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal koneksi ke database: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------

    @Override
    public void save(Enrollment enr) {
        String sql = "INSERT INTO enrollments (student_id, course_code, grade, semester) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, enr.getStudentId());
            ps.setString(2, enr.getCourseCode());
            ps.setDouble(3, enr.getGrade());
            ps.setString(4, enr.getSemester());
            ps.executeUpdate();
            System.out.println("[EnrollmentMapper] Berhasil disimpan: " +
                enr.getStudentId() + " - " + enr.getCourseCode());
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal save: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------

    // findById → pakai studentId, kembalikan enrollment pertama
    @Override
    public Enrollment findById(String studentId) {
        String sql = "SELECT TOP 1 * FROM enrollments WHERE student_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal findById: " + e.getMessage());
        }
        return null;
    }

    public List<Enrollment> findByStudentId(String studentId) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM enrollments WHERE student_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal findByStudentId: " + e.getMessage());
        }
        return list;
    }

    public List<Enrollment> findByCourseCode(String courseCode) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM enrollments WHERE course_code = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal findByCourseCode: " + e.getMessage());
        }
        return list;
    }

    public List<Enrollment> findBySemester(String semester) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM enrollments WHERE semester = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, semester);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal findBySemester: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Enrollment> findAll() {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM enrollments";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal findAll: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------

    @Override
    public void update(Enrollment enr) {
        String sql = "UPDATE enrollments SET grade = ? WHERE student_id = ? AND course_code = ? AND semester = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, enr.getGrade());
            ps.setString(2, enr.getStudentId());
            ps.setString(3, enr.getCourseCode());
            ps.setString(4, enr.getSemester());
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[EnrollmentMapper] Nilai berhasil diupdate.");
            else          System.out.println("[EnrollmentMapper] Data tidak ditemukan.");
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal update: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------

    @Override
    public void delete(String studentId) {
        String sql = "DELETE FROM enrollments WHERE student_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[EnrollmentMapper] Berhasil dihapus, student ID: " + studentId);
            else          System.out.println("[EnrollmentMapper] Student ID tidak ditemukan: " + studentId);
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal delete: " + e.getMessage());
        }
    }

    // Hapus satu enrollment spesifik berdasarkan composite key
    public void deleteByKey(String studentId, String courseCode, String semester) {
        String sql = "DELETE FROM enrollments WHERE student_id = ? AND course_code = ? AND semester = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ps.setString(2, courseCode);
            ps.setString(3, semester);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[EnrollmentMapper] Enrollment dihapus: " + studentId + " - " + courseCode);
            else          System.out.println("[EnrollmentMapper] Data tidak ditemukan.");
        } catch (SQLException e) {
            System.err.println("[EnrollmentMapper] Gagal deleteByKey: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // Helper: ResultSet → Enrollment
    // ----------------------------------------------------------------

    private Enrollment mapRow(ResultSet rs) throws SQLException {
        return new Enrollment(
            rs.getString("student_id"),
            rs.getString("course_code"),
            rs.getDouble("grade"),
            rs.getString("semester")
        );
    }
}