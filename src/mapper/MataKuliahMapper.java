package Mapper;

import db.DatabaseConnection;
import model.MataKuliah;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Mapper untuk entitas MataKuliah.
 * Tabel: courses (code VARCHAR PK, name VARCHAR, credits INT, lecturer_id VARCHAR)
 */
public class MataKuliahMapper implements Mapper<MataKuliah> {

    private Connection conn;

    public MataKuliahMapper() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("[MataKuliahMapper] Gagal koneksi ke database: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------

    @Override
    public void save(MataKuliah mk) {
        String sql = "INSERT INTO courses (code, name, credits, lecturer_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mk.getCode());
            ps.setString(2, mk.getName());
            ps.setInt(3, mk.getCredits());
            ps.setString(4, mk.getLecturerId());
            ps.executeUpdate();
            System.out.println("[MataKuliahMapper] Berhasil disimpan: " + mk.getName());
        } catch (SQLException e) {
            System.err.println("[MataKuliahMapper] Gagal save: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------

    @Override
    public MataKuliah findById(String code) {
        String sql = "SELECT * FROM courses WHERE code = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            System.err.println("[MataKuliahMapper] Gagal findById: " + e.getMessage());
        }
        return null;
    }

    public List<MataKuliah> findByLecturerId(String lecturerId) {
        List<MataKuliah> list = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE lecturer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lecturerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[MataKuliahMapper] Gagal findByLecturerId: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<MataKuliah> findAll() {
        List<MataKuliah> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            System.err.println("[MataKuliahMapper] Gagal findAll: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------

    @Override
    public void update(MataKuliah mk) {
        String sql = "UPDATE courses SET name = ?, credits = ?, lecturer_id = ? WHERE code = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mk.getName());
            ps.setInt(2, mk.getCredits());
            ps.setString(3, mk.getLecturerId());
            ps.setString(4, mk.getCode());
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[MataKuliahMapper] Berhasil diupdate: " + mk.getName());
            else          System.out.println("[MataKuliahMapper] Kode tidak ditemukan: " + mk.getCode());
        } catch (SQLException e) {
            System.err.println("[MataKuliahMapper] Gagal update: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------

    @Override
    public void delete(String code) {
        String sql = "DELETE FROM courses WHERE code = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("[MataKuliahMapper] Berhasil dihapus, kode: " + code);
            else          System.out.println("[MataKuliahMapper] Kode tidak ditemukan: " + code);
        } catch (SQLException e) {
            System.err.println("[MataKuliahMapper] Gagal delete: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // Helper: ResultSet → MataKuliah
    // ----------------------------------------------------------------

    private MataKuliah mapRow(ResultSet rs) throws SQLException {
        return new MataKuliah(
            rs.getString("code"),
            rs.getString("name"),
            rs.getInt("credits"),
            rs.getString("lecturer_id")
        );
    }
}