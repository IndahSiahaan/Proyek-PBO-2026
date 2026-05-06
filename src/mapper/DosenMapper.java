package Mapper;

import db.DatabaseConnection;
import model.Dosen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Mapper untuk entitas Dosen.
 * Tabel: lecturers (id VARCHAR, name VARCHAR, email VARCHAR, department VARCHAR)
 */
public class DosenMapper implements Mapper<Dosen> {

    private Connection conn;

    public DosenMapper() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("[DosenMapper] Gagal koneksi ke database: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------

    @Override
    public void save(Dosen dosen) {
        String sql = "INSERT INTO lecturers (id, name, email, department) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dosen.getId());
            ps.setString(2, dosen.getName());
            ps.setString(3, dosen.getEmail());
            ps.setString(4, dosen.getDepartment());
            ps.executeUpdate();
            System.out.println("[DosenMapper] Berhasil disimpan: " + dosen.getName());
        } catch (SQLException e) {
            System.err.println("[DosenMapper] Gagal save: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------

    @Override
    public Dosen findById(String id) {
        String sql = "SELECT * FROM lecturers WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            System.err.println("[DosenMapper] Gagal findById: " + e.getMessage());
        }
        return null;
    }

    public List<Dosen> findByDepartment(String department) {
        List<Dosen> list = new ArrayList<>();
        String sql = "SELECT * FROM lecturers WHERE department = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, department);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[DosenMapper] Gagal findByDepartment: " + e.getMessage());
        }
        return list;
    }

    public List<Dosen> findByName(String name) {
        List<Dosen> list = new ArrayList<>();
        String sql = "SELECT * FROM lecturers WHERE name LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[DosenMapper] Gagal findByName: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Dosen> findAll() {
        List<Dosen> list = new ArrayList<>();
        String sql = "SELECT * FROM lecturers";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[DosenMapper] Gagal findAll: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------

    @Override
    public void update(Dosen dosen) {
        String sql = "UPDATE lecturers SET name = ?, email = ?, department = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dosen.getName());
            ps.setString(2, dosen.getEmail());
            ps.setString(3, dosen.getDepartment());
            ps.setString(4, dosen.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[DosenMapper] Berhasil diupdate: " + dosen.getName());
            else          System.out.println("[DosenMapper] ID tidak ditemukan: " + dosen.getId());
        } catch (SQLException e) {
            System.err.println("[DosenMapper] Gagal update: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM lecturers WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[DosenMapper] Berhasil dihapus, ID: " + id);
            else          System.out.println("[DosenMapper] ID tidak ditemukan: " + id);
        } catch (SQLException e) {
            System.err.println("[DosenMapper] Gagal delete: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // Helper: ResultSet → Dosen
    // ----------------------------------------------------------------

    private Dosen mapRow(ResultSet rs) throws SQLException {
        return new Dosen(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("department")
        );
    }
}