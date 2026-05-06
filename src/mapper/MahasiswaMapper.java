package Mapper;

import db.DatabaseConnection;
import model.Mahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Mapper untuk entitas Mahasiswa.
 * Tabel: students (id VARCHAR, name VARCHAR, email VARCHAR, major VARCHAR)
 */
public class MahasiswaMapper implements Mapper<Mahasiswa> {

    private Connection conn;

    public MahasiswaMapper() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("[MahasiswaMapper] Gagal koneksi ke database: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------

    @Override
    public void save(Mahasiswa mhs) {
        String sql = "INSERT INTO students (id, name, email, major) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mhs.getId());
            ps.setString(2, mhs.getName());
            ps.setString(3, mhs.getEmail());
            ps.setString(4, mhs.getMajor());
            ps.executeUpdate();
            System.out.println("[MahasiswaMapper] Berhasil disimpan: " + mhs.getName());
        } catch (SQLException e) {
            System.err.println("[MahasiswaMapper] Gagal save: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------

    @Override
    public Mahasiswa findById(String id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            System.err.println("[MahasiswaMapper] Gagal findById: " + e.getMessage());
        }
        return null;
    }

    public List<Mahasiswa> findByMajor(String major) {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE major = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, major);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[MahasiswaMapper] Gagal findByMajor: " + e.getMessage());
        }
        return list;
    }

    public List<Mahasiswa> findByName(String name) {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[MahasiswaMapper] Gagal findByName: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Mahasiswa> findAll() {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[MahasiswaMapper] Gagal findAll: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------

    @Override
    public void update(Mahasiswa mhs) {
        String sql = "UPDATE students SET name = ?, email = ?, major = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mhs.getName());
            ps.setString(2, mhs.getEmail());
            ps.setString(3, mhs.getMajor());
            ps.setString(4, mhs.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[MahasiswaMapper] Berhasil diupdate: " + mhs.getName());
            else          System.out.println("[MahasiswaMapper] ID tidak ditemukan: " + mhs.getId());
        } catch (SQLException e) {
            System.err.println("[MahasiswaMapper] Gagal update: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[MahasiswaMapper] Berhasil dihapus, ID: " + id);
            else          System.out.println("[MahasiswaMapper] ID tidak ditemukan: " + id);
        } catch (SQLException e) {
            System.err.println("[MahasiswaMapper] Gagal delete: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // Helper: ResultSet → Mahasiswa
    // ----------------------------------------------------------------

    private Mahasiswa mapRow(ResultSet rs) throws SQLException {
        return new Mahasiswa(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("major")
        );
    }
}