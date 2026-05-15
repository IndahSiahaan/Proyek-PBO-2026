import service.KampusService;

import model.Mahasiswa;
import model.Dosen;
import model.MataKuliah;
import model.Enrollment;

import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static KampusService service = new KampusService();

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║   SISTEM MANAJEMEN KAMPUS v1.0     ║");
        System.out.println("╚════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            tampilkanMenuUtama();
            int pilihan = inputAngka("Pilih menu: ");
            switch (pilihan) {
                case 1 -> menuMahasiswa();
                case 2 -> menuDosen();
                case 3 -> menuMataKuliah();
                case 4 -> menuNilai();
                case 5 -> menuRanking();
                case 0 -> {
                    System.out.println("\nTerima kasih! Program selesai.");
                    running = false;
                }
                default -> System.out.println("Pilihan tidak valid!\n");
            }
        }
        scanner.close();
    }

    static void tampilkanMenuUtama() {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║        MENU UTAMA          ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1. Data Mahasiswa          ║");
        System.out.println("║ 2. Data Dosen              ║");
        System.out.println("║ 3. Data Mata Kuliah        ║");
        System.out.println("║ 4. Nilai & Enrollment      ║");
        System.out.println("║ 5. Ranking Mahasiswa       ║");
        System.out.println("║ 0. Keluar                  ║");
        System.out.println("╚════════════════════════════╝");
    }

    static void menuMahasiswa() {
        System.out.println("\n--- MENU MAHASISWA ---");
        System.out.println("1. Lihat semua mahasiswa");
        System.out.println("2. Lihat mahasiswa per jurusan");
        System.out.println("3. Lihat mahasiswa urut nama");
        System.out.println("4. Cari mahasiswa by ID");
        System.out.println("5. Tambah mahasiswa baru");
        System.out.println("0. Kembali");

        int pilihan = inputAngka("Pilih: ");
        switch (pilihan) {
            case 1 -> {
                System.out.println("\n=== Semua Mahasiswa ===");
                ArrayList<Mahasiswa> list = service.getAllMahasiswa();
                if (list.isEmpty()) { System.out.println("Tidak ada data."); break; }
                for (Mahasiswa m : list) System.out.println(m.getInfo());
            }
            case 2 -> {
                System.out.println("\n=== Mahasiswa per Jurusan ===");
                HashMap<String, ArrayList<Mahasiswa>> map = service.getMahasiswaPerJurusan();
                for (String jurusan : map.keySet()) {
                    System.out.println("\n[" + jurusan + "]");
                    for (Mahasiswa m : map.get(jurusan)) {
                        System.out.println("  - " + m.getName() + " (" + m.getId() + ")");
                    }
                }
            }
            case 3 -> {
                System.out.println("\n=== Mahasiswa Urut Nama (A-Z) ===");
                TreeSet<String> sorted = service.getMahasiswaUrutNama();
                int no = 1;
                for (String s : sorted) System.out.println(no++ + ". " + s);
            }
            case 4 -> {
                String id = inputString("Masukkan ID mahasiswa: ");
                Mahasiswa m = service.cariMahasiswaById(id);
                if (m != null) System.out.println("\nDitemukan: " + m.getInfo());
                else System.out.println("Mahasiswa tidak ditemukan.");
            }
            case 5 -> {
                System.out.println("\n=== Tambah Mahasiswa Baru ===");
                String id      = inputString("ID       : ");
                String nama    = inputString("Nama     : ");
                String email   = inputString("Email    : ");
                String jurusan = inputString("Jurusan  : ");
                service.tambahMahasiswa(id, nama, email, jurusan);
            }
            case 0 -> {}
            default -> System.out.println("Pilihan tidak valid!");
        }
    }

    static void menuDosen() {
        System.out.println("\n--- MENU DOSEN ---");
        System.out.println("1. Lihat semua dosen");
        System.out.println("2. Lihat dosen per departemen");
        System.out.println("3. Tambah dosen baru");
        System.out.println("0. Kembali");

        int pilihan = inputAngka("Pilih: ");
        switch (pilihan) {
            case 1 -> {
                System.out.println("\n=== Semua Dosen ===");
                ArrayList<Dosen> list = service.getAllDosen();
                if (list.isEmpty()) { System.out.println("Tidak ada data."); break; }
                for (Dosen d : list) System.out.println(d.getInfo());
            }
            case 2 -> {
                System.out.println("\n=== Dosen per Departemen ===");
                HashMap<String, ArrayList<Dosen>> map = service.getDosenPerDepartemen();
                for (String dept : map.keySet()) {
                    System.out.println("\n[" + dept + "]");
                    for (Dosen d : map.get(dept)) {
                        System.out.println("  - " + d.getName() + " (" + d.getId() + ")");
                    }
                }
            }
            case 3 -> {
                System.out.println("\n=== Tambah Dosen Baru ===");
                String id    = inputString("ID         : ");
                String nama  = inputString("Nama       : ");
                String email = inputString("Email      : ");
                String dept  = inputString("Departemen : ");
                service.tambahDosen(id, nama, email, dept);
            }
            case 0 -> {}
            default -> System.out.println("Pilihan tidak valid!");
        }
    }

    static void menuMataKuliah() {
        System.out.println("\n--- MENU MATA KULIAH ---");
        System.out.println("1. Lihat semua mata kuliah");
        System.out.println("2. Tambah mata kuliah baru");
        System.out.println("0. Kembali");

        int pilihan = inputAngka("Pilih: ");
        switch (pilihan) {
            case 1 -> {
                System.out.println("\n=== Semua Mata Kuliah ===");
                ArrayList<MataKuliah> list = service.getAllMataKuliah();
                if (list.isEmpty()) { System.out.println("Tidak ada data."); break; }
                for (MataKuliah mk : list) System.out.println(mk);
            }
            case 2 -> {
                System.out.println("\n=== Tambah Mata Kuliah Baru ===");
                String kode    = inputString("Kode     : ");
                String nama    = inputString("Nama     : ");
                int sks        = inputAngka("SKS      : ");
                String dosenId = inputString("ID Dosen : ");
                service.tambahMataKuliah(kode, nama, sks, dosenId);
            }
            case 0 -> {}
            default -> System.out.println("Pilihan tidak valid!");
        }
    }

    static void menuNilai() {
        System.out.println("\n--- MENU NILAI & ENROLLMENT ---");
        System.out.println("1. Lihat nilai mahasiswa");
        System.out.println("2. Daftarkan mahasiswa ke mata kuliah");
        System.out.println("0. Kembali");

        int pilihan = inputAngka("Pilih: ");
        switch (pilihan) {
            case 1 -> {
                String id = inputString("Masukkan ID mahasiswa: ");
                Mahasiswa m = service.cariMahasiswaById(id);
                if (m == null) { System.out.println("Mahasiswa tidak ditemukan."); break; }
                System.out.println("\n=== Nilai " + m.getName() + " ===");
                ArrayList<Enrollment> list = service.getNilaiMahasiswa(id);
                if (list.isEmpty()) { System.out.println("Belum ada nilai."); break; }
                for (Enrollment e : list) {
                    System.out.println("  " + e.getCourseCode() +
                                       " | Nilai: " + e.getGrade() +
                                       " | Semester: " + e.getSemester());
                }
                System.out.printf("  Rata-rata: %.2f%n", service.getRataRataNilai(id));
            }
            case 2 -> {
                String mhsId    = inputString("ID Mahasiswa  : ");
                String kode     = inputString("Kode MK       : ");
                String semester = inputString("Semester      : ");
                service.daftarkanMahasiswa(mhsId, kode, semester);
            }
            case 0 -> {}
            default -> System.out.println("Pilihan tidak valid!");
        }
    }

    static void menuRanking() {
        System.out.println("\n=== RANKING MAHASISWA (Rata-rata Nilai) ===");
        LinkedHashMap<String, Double> ranking = service.getRankingMahasiswa();
        int peringkat = 1;
        for (Map.Entry<String, Double> entry : ranking.entrySet()) {
            System.out.printf("%d. %-25s %.2f%n", peringkat++, entry.getKey(), entry.getValue());
        }
    }

    static int inputAngka(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static String inputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
