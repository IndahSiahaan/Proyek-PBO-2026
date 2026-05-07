package service;

import mapper.MahasiswaMapper;
import mapper.DosenMapper;
import mapper.MataKuliahMapper;
import mapper.EnrollmentMapper;
import model.Mahasiswa;
import model.Dosen;
import model.MataKuliah;
import model.Enrollment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * KampusService - Service Layer
 * Anggota 4
 *
 * Logika bisnis utama program akademik.
 * Memakai ArrayList dan HashMap sebagai struktur data (JCF).
 * Semua operasi CRUD diteruskan ke masing-masing Mapper.
 */
public class KampusService {

    // ----------------------------------------------------------------
    // Mapper (akses ke database via Data Mapper Pattern)
    // ----------------------------------------------------------------
    private MahasiswaMapper  mahasiswaMapper;
    private DosenMapper      dosenMapper;
    private MataKuliahMapper mataKuliahMapper;
    private EnrollmentMapper enrollmentMapper;

    // ----------------------------------------------------------------
    // Cache lokal pakai JCF (ArrayList + HashMap)
    // ----------------------------------------------------------------
    private ArrayList<Mahasiswa>          cacheAllMahasiswa;
    private HashMap<String, Mahasiswa>    indexMahasiswa;   // key: id
    private ArrayList<Dosen>             cacheAllDosen;
    private HashMap<String, Dosen>        indexDosen;       // key: id
    private ArrayList<MataKuliah>        cacheAllMataKuliah;
    private HashMap<String, MataKuliah>  indexMataKuliah;  // key: code

    // ----------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------
    public KampusService() {
        this.mahasiswaMapper  = new MahasiswaMapper();
        this.dosenMapper      = new DosenMapper();
        this.mataKuliahMapper = new MataKuliahMapper();
        this.enrollmentMapper = new EnrollmentMapper();

        // Inisialisasi cache kosong
        this.cacheAllMahasiswa  = new ArrayList<>();
        this.indexMahasiswa     = new HashMap<>();
        this.cacheAllDosen      = new ArrayList<>();
        this.indexDosen         = new HashMap<>();
        this.cacheAllMataKuliah = new ArrayList<>();
        this.indexMataKuliah    = new HashMap<>();
    }

    // ================================================================
    //  MAHASISWA
    // ================================================================

    /** Tambah mahasiswa baru ke DB */
    public void tambahMahasiswa(String id, String nama, String email, String jurusan) {
        Mahasiswa mhs = new Mahasiswa(id, nama, email, jurusan);
        mahasiswaMapper.save(mhs);
        // Update cache
        cacheAllMahasiswa.add(mhs);
        indexMahasiswa.put(id, mhs);
    }

    /** Ambil semua mahasiswa dari DB, simpan ke cache ArrayList */
    public ArrayList<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> list = mahasiswaMapper.findAll();
        cacheAllMahasiswa = new ArrayList<>(list);
        indexMahasiswa.clear();
        for (Mahasiswa m : cacheAllMahasiswa) {
            indexMahasiswa.put(m.getId(), m);
        }
        return cacheAllMahasiswa;
    }

    /** Cari mahasiswa by ID – cek cache HashMap dulu, baru ke DB */
    public Mahasiswa getMahasiswaById(String id) {
        if (indexMahasiswa.containsKey(id)) {
            return indexMahasiswa.get(id);
        }
        Mahasiswa mhs = mahasiswaMapper.findById(id);
        if (mhs != null) indexMahasiswa.put(id, mhs);
        return mhs;
    }

    /** Update data mahasiswa */
    public void updateMahasiswa(String id, String namaBaru, String emailBaru, String jurusanBaru) {
        Mahasiswa mhs = new Mahasiswa(id, namaBaru, emailBaru, jurusanBaru);
        mahasiswaMapper.update(mhs);
        // Refresh cache
        indexMahasiswa.put(id, mhs);
        for (int i = 0; i < cacheAllMahasiswa.size(); i++) {
            if (cacheAllMahasiswa.get(i).getId().equals(id)) {
                cacheAllMahasiswa.set(i, mhs);
                break;
            }
        }
    }

    /** Hapus mahasiswa */
    public void hapusMahasiswa(String id) {
        mahasiswaMapper.delete(id);
        indexMahasiswa.remove(id);
        cacheAllMahasiswa.removeIf(m -> m.getId().equals(id));
    }

    // ================================================================
    //  DOSEN
    // ================================================================

    public void tambahDosen(String id, String nama, String email, String departemen) {
        Dosen dosen = new Dosen(id, nama, email, departemen);
        dosenMapper.save(dosen);
        cacheAllDosen.add(dosen);
        indexDosen.put(id, dosen);
    }

    public ArrayList<Dosen> getAllDosen() {
        List<Dosen> list = dosenMapper.findAll();
        cacheAllDosen = new ArrayList<>(list);
        indexDosen.clear();
        for (Dosen d : cacheAllDosen) {
            indexDosen.put(d.getId(), d);
        }
        return cacheAllDosen;
    }

    public Dosen getDosenById(String id) {
        if (indexDosen.containsKey(id)) return indexDosen.get(id);
        Dosen dosen = dosenMapper.findById(id);
        if (dosen != null) indexDosen.put(id, dosen);
        return dosen;
    }

    public void updateDosen(String id, String namaBaru, String emailBaru, String depBaru) {
        Dosen dosen = new Dosen(id, namaBaru, emailBaru, depBaru);
        dosenMapper.update(dosen);
        indexDosen.put(id, dosen);
        for (int i = 0; i < cacheAllDosen.size(); i++) {
            if (cacheAllDosen.get(i).getId().equals(id)) {
                cacheAllDosen.set(i, dosen);
                break;
            }
        }
    }

    public void hapusDosen(String id) {
        dosenMapper.delete(id);
        indexDosen.remove(id);
        cacheAllDosen.removeIf(d -> d.getId().equals(id));
    }

    // ================================================================
    //  MATA KULIAH
    // ================================================================

    public void tambahMataKuliah(String kode, String nama, int sks, String dosenId) {
        MataKuliah mk = new MataKuliah(kode, nama, sks, dosenId);
        mataKuliahMapper.save(mk);
        cacheAllMataKuliah.add(mk);
        indexMataKuliah.put(kode, mk);
    }

    public ArrayList<MataKuliah> getAllMataKuliah() {
        List<MataKuliah> list = mataKuliahMapper.findAll();
        cacheAllMataKuliah = new ArrayList<>(list);
        indexMataKuliah.clear();
        for (MataKuliah mk : cacheAllMataKuliah) {
            indexMataKuliah.put(mk.getCode(), mk);
        }
        return cacheAllMataKuliah;
    }

    public MataKuliah getMataKuliahByKode(String kode) {
        if (indexMataKuliah.containsKey(kode)) return indexMataKuliah.get(kode);
        MataKuliah mk = mataKuliahMapper.findById(kode);
        if (mk != null) indexMataKuliah.put(kode, mk);
        return mk;
    }

    public void updateMataKuliah(String kode, String namaBaru, int sksBaru, String dosenIdBaru) {
        MataKuliah mk = new MataKuliah(kode, namaBaru, sksBaru, dosenIdBaru);
        mataKuliahMapper.update(mk);
        indexMataKuliah.put(kode, mk);
        for (int i = 0; i < cacheAllMataKuliah.size(); i++) {
            if (cacheAllMataKuliah.get(i).getCode().equals(kode)) {
                cacheAllMataKuliah.set(i, mk);
                break;
            }
        }
    }

    public void hapusMataKuliah(String kode) {
        mataKuliahMapper.delete(kode);
        indexMataKuliah.remove(kode);
        cacheAllMataKuliah.removeIf(mk -> mk.getCode().equals(kode));
    }

    // ================================================================
    //  ENROLLMENT
    // ================================================================

    /** Daftarkan mahasiswa ke mata kuliah */
    public void enrollMahasiswa(String studentId, String courseCode, String semester) {
        // Validasi: pastikan mahasiswa & mk ada
        Mahasiswa mhs = getMahasiswaById(studentId);
        MataKuliah mk = getMataKuliahByKode(courseCode);
        if (mhs == null) {
            System.out.println("[Service] Mahasiswa ID tidak ditemukan: " + studentId);
            return;
        }
        if (mk == null) {
            System.out.println("[Service] Kode MK tidak ditemukan: " + courseCode);
            return;
        }
        Enrollment enr = new Enrollment(studentId, courseCode, 0.0, semester);
        enrollmentMapper.save(enr);
    }

    /** Input/update nilai mahasiswa */
    public void inputNilai(String studentId, String courseCode, String semester, double nilai) {
        Enrollment enr = new Enrollment(studentId, courseCode, nilai, semester);
        enrollmentMapper.update(enr);
    }

    /** Lihat semua enrollment mahasiswa tertentu */
    public ArrayList<Enrollment> getEnrollmentMahasiswa(String studentId) {
        List<Enrollment> list = enrollmentMapper.findByStudentId(studentId);
        return new ArrayList<>(list);
    }

    /** Hitung IPK mahasiswa berdasarkan nilai enrollment */
    public double hitungIPK(String studentId) {
        ArrayList<Enrollment> enrollments = getEnrollmentMahasiswa(studentId);
        if (enrollments.isEmpty()) return 0.0;

        double totalBobot = 0.0;
        int    totalSKS   = 0;

        for (Enrollment enr : enrollments) {
            MataKuliah mk = getMataKuliahByKode(enr.getCourseCode());
            int sks = (mk != null) ? mk.getCredits() : 2; // default 2 SKS
            double nilaiHuruf = konversiNilai(enr.getGrade());
            totalBobot += nilaiHuruf * sks;
            totalSKS   += sks;
        }
        return (totalSKS == 0) ? 0.0 : totalBobot / totalSKS;
    }

    /** Konversi nilai angka (0–100) ke bobot (0–4) */
    public double konversiNilai(double nilai) {
        if (nilai >= 85) return 4.0;
        if (nilai >= 75) return 3.0;
        if (nilai >= 65) return 2.0;
        if (nilai >= 55) return 1.0;
        return 0.0;
    }

    /** Konversi bobot ke huruf */
    public String bobotKeHuruf(double bobot) {
        if (bobot == 4.0) return "A";
        if (bobot == 3.0) return "B";
        if (bobot == 2.0) return "C";
        if (bobot == 1.0) return "D";
        return "E";
    }

    /** Hapus enrollment */
    public void hapusEnrollment(String studentId, String courseCode, String semester) {
        enrollmentMapper.deleteByKey(studentId, courseCode, semester);
    }

    /** Lihat semua enrollment */
    public ArrayList<Enrollment> getAllEnrollment() {
        return new ArrayList<>(enrollmentMapper.findAll());
    }
}
