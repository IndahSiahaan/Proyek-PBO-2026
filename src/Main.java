import service.KampusService;
import model.Mahasiswa;
import model.Dosen;
import model.MataKuliah;
import model.Enrollment;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main.java - Entry Point / CLI Menu
 * Anggota 4
 *
 * Menu interaktif sistem manajemen akademik.
 * Alur: Main → KampusService → Mapper → JDBC → SQL Server
 */
public class Main {

    private static KampusService service = new KampusService();
    private static Scanner       sc      = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEM MANAJEMEN AKADEMIK - PBO 2026   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            tampilMenuUtama();
            int pilihan = bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1  -> menuMahasiswa();
                case 2  -> menuDosen();
                case 3  -> menuMataKuliah();
                case 4  -> menuEnrollment();
                case 5  -> menuNilaiIPK();
                case 0  -> { running = false; System.out.println("\nTerima kasih! Program selesai."); }
                default -> System.out.println("[!] Pilihan tidak valid.\n");
            }
        }
        sc.close();
    }

    // ================================================================
    //  MENU UTAMA
    // ================================================================
    private static void tampilMenuUtama() {
        System.out.println("\n══════════════ MENU UTAMA ══════════════");
        System.out.println("  1. Manajemen Mahasiswa");
        System.out.println("  2. Manajemen Dosen");
        System.out.println("  3. Manajemen Mata Kuliah");
        System.out.println("  4. Enrollment (Ambil Mata Kuliah)");
        System.out.println("  5. Nilai & IPK");
        System.out.println("  0. Keluar");
        System.out.println("════════════════════════════════════════");
    }

    // ================================================================
    //  MENU MAHASISWA
    // ================================================================
    private static void menuMahasiswa() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n─── MANAJEMEN MAHASISWA ───");
            System.out.println("  1. Tambah Mahasiswa");
            System.out.println("  2. Lihat Semua Mahasiswa");
            System.out.println("  3. Cari Mahasiswa by ID");
            System.out.println("  4. Update Mahasiswa");
            System.out.println("  5. Hapus Mahasiswa");
            System.out.println("  0. Kembali");
            int p = bacaInt("Pilih: ");
            switch (p) {
                case 1 -> tambahMahasiswa();
                case 2 -> lihatSemuaMahasiswa();
                case 3 -> cariMahasiswaById();
                case 4 -> updateMahasiswa();
                case 5 -> hapusMahasiswa();
                case 0 -> loop = false;
                default -> System.out.println("[!] Pilihan tidak valid.");
            }
        }
    }

    private static void tambahMahasiswa() {
        System.out.println("\n[+] TAMBAH MAHASISWA");
        String id      = bacaString("ID Mahasiswa   : ");
        String nama    = bacaString("Nama           : ");
        String email   = bacaString("Email          : ");
        String jurusan = bacaString("Jurusan        : ");
        service.tambahMahasiswa(id, nama, email, jurusan);
    }

    private static void lihatSemuaMahasiswa() {
        System.out.println("\n[*] DAFTAR MAHASISWA");
        ArrayList<Mahasiswa> list = service.getAllMahasiswa();
        if (list.isEmpty()) {
            System.out.println("    (Belum ada data mahasiswa)");
        } else {
            System.out.printf("  %-10s %-25s %-30s %-20s%n", "ID", "Nama", "Email", "Jurusan");
            System.out.println("  " + "─".repeat(85));
            for (Mahasiswa m : list) {
                System.out.printf("  %-10s %-25s %-30s %-20s%n",
                    m.getId(), m.getName(), m.getEmail(), m.getMajor());
            }
        }
    }

    private static void cariMahasiswaById() {
        String id  = bacaString("ID Mahasiswa: ");
        Mahasiswa m = service.getMahasiswaById(id);
        if (m != null) System.out.println("  Ditemukan → " + m.getInfo());
        else           System.out.println("[!] Mahasiswa dengan ID " + id + " tidak ditemukan.");
    }

    private static void updateMahasiswa() {
        System.out.println("\n[~] UPDATE MAHASISWA");
        String id      = bacaString("ID Mahasiswa yang diupdate: ");
        String nama    = bacaString("Nama baru    : ");
        String email   = bacaString("Email baru   : ");
        String jurusan = bacaString("Jurusan baru : ");
        service.updateMahasiswa(id, nama, email, jurusan);
    }

    private static void hapusMahasiswa() {
        String id = bacaString("ID Mahasiswa yang dihapus: ");
        System.out.print("Yakin hapus? (y/n): ");
        String konfirm = sc.nextLine().trim();
        if (konfirm.equalsIgnoreCase("y")) service.hapusMahasiswa(id);
        else System.out.println("Dibatalkan.");
    }

    // ================================================================
    //  MENU DOSEN
    // ================================================================
    private static void menuDosen() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n─── MANAJEMEN DOSEN ───");
            System.out.println("  1. Tambah Dosen");
            System.out.println("  2. Lihat Semua Dosen");
            System.out.println("  3. Cari Dosen by ID");
            System.out.println("  4. Update Dosen");
            System.out.println("  5. Hapus Dosen");
            System.out.println("  0. Kembali");
            int p = bacaInt("Pilih: ");
            switch (p) {
                case 1 -> tambahDosen();
                case 2 -> lihatSemuaDosen();
                case 3 -> cariDosenById();
                case 4 -> updateDosen();
                case 5 -> hapusDosen();
                case 0 -> loop = false;
                default -> System.out.println("[!] Pilihan tidak valid.");
            }
        }
    }

    private static void tambahDosen() {
        System.out.println("\n[+] TAMBAH DOSEN");
        String id   = bacaString("ID Dosen       : ");
        String nama = bacaString("Nama           : ");
        String email= bacaString("Email          : ");
        String dep  = bacaString("Departemen     : ");
        service.tambahDosen(id, nama, email, dep);
    }

    private static void lihatSemuaDosen() {
        System.out.println("\n[*] DAFTAR DOSEN");
        ArrayList<Dosen> list = service.getAllDosen();
        if (list.isEmpty()) {
            System.out.println("    (Belum ada data dosen)");
        } else {
            System.out.printf("  %-10s %-25s %-30s %-20s%n", "ID", "Nama", "Email", "Departemen");
            System.out.println("  " + "─".repeat(85));
            for (Dosen d : list) {
                System.out.printf("  %-10s %-25s %-30s %-20s%n",
                    d.getId(), d.getName(), d.getEmail(), d.getDepartment());
            }
        }
    }

    private static void cariDosenById() {
        String id = bacaString("ID Dosen: ");
        Dosen d   = service.getDosenById(id);
        if (d != null) System.out.println("  Ditemukan → " + d.getInfo());
        else           System.out.println("[!] Dosen dengan ID " + id + " tidak ditemukan.");
    }

    private static void updateDosen() {
        System.out.println("\n[~] UPDATE DOSEN");
        String id   = bacaString("ID Dosen yang diupdate: ");
        String nama = bacaString("Nama baru       : ");
        String email= bacaString("Email baru      : ");
        String dep  = bacaString("Departemen baru : ");
        service.updateDosen(id, nama, email, dep);
    }

    private static void hapusDosen() {
        String id = bacaString("ID Dosen yang dihapus: ");
        System.out.print("Yakin hapus? (y/n): ");
        String konfirm = sc.nextLine().trim();
        if (konfirm.equalsIgnoreCase("y")) service.hapusDosen(id);
        else System.out.println("Dibatalkan.");
    }

    // ================================================================
    //  MENU MATA KULIAH
    // ================================================================
    private static void menuMataKuliah() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n─── MANAJEMEN MATA KULIAH ───");
            System.out.println("  1. Tambah Mata Kuliah");
            System.out.println("  2. Lihat Semua Mata Kuliah");
            System.out.println("  3. Cari Mata Kuliah by Kode");
            System.out.println("  4. Update Mata Kuliah");
            System.out.println("  5. Hapus Mata Kuliah");
            System.out.println("  0. Kembali");
            int p = bacaInt("Pilih: ");
            switch (p) {
                case 1 -> tambahMataKuliah();
                case 2 -> lihatSemuaMataKuliah();
                case 3 -> cariMataKuliahByKode();
                case 4 -> updateMataKuliah();
                case 5 -> hapusMataKuliah();
                case 0 -> loop = false;
                default -> System.out.println("[!] Pilihan tidak valid.");
            }
        }
    }

    private static void tambahMataKuliah() {
        System.out.println("\n[+] TAMBAH MATA KULIAH");
        String kode   = bacaString("Kode MK    : ");
        String nama   = bacaString("Nama MK    : ");
        int    sks    = bacaInt("SKS        : ");
        String dosenId= bacaString("ID Dosen   : ");
        service.tambahMataKuliah(kode, nama, sks, dosenId);
    }

    private static void lihatSemuaMataKuliah() {
        System.out.println("\n[*] DAFTAR MATA KULIAH");
        ArrayList<MataKuliah> list = service.getAllMataKuliah();
        if (list.isEmpty()) {
            System.out.println("    (Belum ada data mata kuliah)");
        } else {
            System.out.printf("  %-10s %-30s %-5s %-12s%n", "Kode", "Nama MK", "SKS", "Dosen ID");
            System.out.println("  " + "─".repeat(57));
            for (MataKuliah mk : list) {
                System.out.printf("  %-10s %-30s %-5d %-12s%n",
                    mk.getCode(), mk.getName(), mk.getCredits(), mk.getLecturerId());
            }
        }
    }

    private static void cariMataKuliahByKode() {
        String kode   = bacaString("Kode MK: ");
        MataKuliah mk = service.getMataKuliahByKode(kode);
        if (mk != null) System.out.println("  Ditemukan → " + mk);
        else            System.out.println("[!] MK dengan kode " + kode + " tidak ditemukan.");
    }

    private static void updateMataKuliah() {
        System.out.println("\n[~] UPDATE MATA KULIAH");
        String kode   = bacaString("Kode MK yang diupdate: ");
        String nama   = bacaString("Nama baru    : ");
        int    sks    = bacaInt("SKS baru     : ");
        String dosenId= bacaString("Dosen ID baru: ");
        service.updateMataKuliah(kode, nama, sks, dosenId);
    }

    private static void hapusMataKuliah() {
        String kode = bacaString("Kode MK yang dihapus: ");
        System.out.print("Yakin hapus? (y/n): ");
        String konfirm = sc.nextLine().trim();
        if (konfirm.equalsIgnoreCase("y")) service.hapusMataKuliah(kode);
        else System.out.println("Dibatalkan.");
    }

    // ================================================================
    //  MENU ENROLLMENT
    // ================================================================
    private static void menuEnrollment() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n─── ENROLLMENT (PENGAMBILAN MK) ───");
            System.out.println("  1. Daftarkan Mahasiswa ke Mata Kuliah");
            System.out.println("  2. Lihat Enrollment Mahasiswa");
            System.out.println("  3. Batalkan Enrollment");
            System.out.println("  4. Lihat Semua Enrollment");
            System.out.println("  0. Kembali");
            int p = bacaInt("Pilih: ");
            switch (p) {
                case 1 -> enrollMahasiswa();
                case 2 -> lihatEnrollmentMahasiswa();
                case 3 -> batalEnrollment();
                case 4 -> lihatSemuaEnrollment();
                case 0 -> loop = false;
                default -> System.out.println("[!] Pilihan tidak valid.");
            }
        }
    }

    private static void enrollMahasiswa() {
        System.out.println("\n[+] DAFTARKAN MAHASISWA KE MATA KULIAH");
        String studentId  = bacaString("ID Mahasiswa : ");
        String courseCode = bacaString("Kode MK      : ");
        String semester   = bacaString("Semester     (contoh: 2024/1): ");
        service.enrollMahasiswa(studentId, courseCode, semester);
    }

    private static void lihatEnrollmentMahasiswa() {
        String studentId = bacaString("ID Mahasiswa: ");
        ArrayList<Enrollment> list = service.getEnrollmentMahasiswa(studentId);
        Mahasiswa mhs = service.getMahasiswaById(studentId);
        String namaMhs = (mhs != null) ? mhs.getName() : studentId;

        System.out.println("\n[*] ENROLLMENT - " + namaMhs);
        if (list.isEmpty()) {
            System.out.println("    (Belum ada enrollment)");
        } else {
            System.out.printf("  %-12s %-30s %-8s %-10s %-6s%n",
                "Kode MK", "Nama MK", "Semester", "Nilai", "Grade");
            System.out.println("  " + "─".repeat(66));
            for (Enrollment enr : list) {
                MataKuliah mk = service.getMataKuliahByKode(enr.getCourseCode());
                String namaMK = (mk != null) ? mk.getName() : "-";
                double bobot  = service.konversiNilai(enr.getGrade());
                String huruf  = service.bobotKeHuruf(bobot);
                System.out.printf("  %-12s %-30s %-8s %-10.1f %-6s%n",
                    enr.getCourseCode(), namaMK, enr.getSemester(), enr.getGrade(), huruf);
            }
        }
    }

    private static void batalEnrollment() {
        System.out.println("\n[-] BATALKAN ENROLLMENT");
        String studentId  = bacaString("ID Mahasiswa : ");
        String courseCode = bacaString("Kode MK      : ");
        String semester   = bacaString("Semester     : ");
        service.hapusEnrollment(studentId, courseCode, semester);
    }

    private static void lihatSemuaEnrollment() {
        System.out.println("\n[*] SEMUA DATA ENROLLMENT");
        ArrayList<Enrollment> list = service.getAllEnrollment();
        if (list.isEmpty()) {
            System.out.println("    (Belum ada data enrollment)");
        } else {
            System.out.printf("  %-12s %-12s %-10s %-8s%n",
                "Student ID", "Kode MK", "Semester", "Nilai");
            System.out.println("  " + "─".repeat(44));
            for (Enrollment enr : list) {
                System.out.printf("  %-12s %-12s %-10s %-8.1f%n",
                    enr.getStudentId(), enr.getCourseCode(),
                    enr.getSemester(), enr.getGrade());
            }
        }
    }

    // ================================================================
    //  MENU NILAI & IPK
    // ================================================================
    private static void menuNilaiIPK() {
        boolean loop = true;
        while (loop) {
            System.out.println("\n─── NILAI & IPK ───");
            System.out.println("  1. Input/Update Nilai Mahasiswa");
            System.out.println("  2. Hitung IPK Mahasiswa");
            System.out.println("  0. Kembali");
            int p = bacaInt("Pilih: ");
            switch (p) {
                case 1 -> inputNilai();
                case 2 -> hitungIPK();
                case 0 -> loop = false;
                default -> System.out.println("[!] Pilihan tidak valid.");
            }
        }
    }

    private static void inputNilai() {
        System.out.println("\n[~] INPUT NILAI");
        String studentId  = bacaString("ID Mahasiswa : ");
        String courseCode = bacaString("Kode MK      : ");
        String semester   = bacaString("Semester     : ");
        double nilai      = bacaDouble("Nilai (0-100): ");
        service.inputNilai(studentId, courseCode, semester, nilai);
        double bobot = service.konversiNilai(nilai);
        System.out.println("  Grade: " + service.bobotKeHuruf(bobot) + " (" + bobot + ")");
    }

    private static void hitungIPK() {
        String studentId = bacaString("ID Mahasiswa: ");
        Mahasiswa mhs    = service.getMahasiswaById(studentId);
        if (mhs == null) {
            System.out.println("[!] Mahasiswa tidak ditemukan.");
            return;
        }
        double ipk = service.hitungIPK(studentId);
        System.out.println("\n══════════════════════════════");
        System.out.println("  Mahasiswa : " + mhs.getName());
        System.out.println("  Jurusan   : " + mhs.getMajor());
        System.out.printf("  IPK       : %.2f%n", ipk);
        System.out.println("══════════════════════════════");

        // Tampilkan detail nilai pakai ArrayList
        ArrayList<Enrollment> enrollments = service.getEnrollmentMahasiswa(studentId);
        if (!enrollments.isEmpty()) {
            System.out.println("  Detail Nilai:");
            for (Enrollment enr : enrollments) {
                MataKuliah mk = service.getMataKuliahByKode(enr.getCourseCode());
                String namaMK = (mk != null) ? mk.getName() : enr.getCourseCode();
                double bobot  = service.konversiNilai(enr.getGrade());
                System.out.printf("    • %-25s: %.1f → %s%n",
                    namaMK, enr.getGrade(), service.bobotKeHuruf(bobot));
            }
        }
    }

    // ================================================================
    //  HELPER INPUT
    // ================================================================

    private static String bacaString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("[!] Masukkan angka yang valid.");
            }
        }
    }

    private static double bacaDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("[!] Masukkan angka desimal yang valid.");
            }
        }
    }
}
