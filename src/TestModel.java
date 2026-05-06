
import model.Mahasiswa;
import model.Dosen;
import model.MataKuliah;
import model.Enrollment;
import model.Person;

public class TestModel {
    public static void main(String[] args) {

        // ── Test Mahasiswa ──────────────────────────────────────────────────
        System.out.println("=== Test Mahasiswa ===");
        Mahasiswa m = new Mahasiswa("12S24057", "Laura Awise", "laura@student.ac.id", "Teknik Informatika");
        System.out.println("ID      : " + m.getId());
        System.out.println("Nama    : " + m.getName());
        System.out.println("Email   : " + m.getEmail());
        System.out.println("Jurusan : " + m.getMajor());
        System.out.println("getInfo : " + m.getInfo());
        System.out.println("toString: " + m);

        // ── Test Dosen ──────────────────────────────────────────────────────
        System.out.println("\n=== Test Dosen ===");
        Dosen d = new Dosen("D001", "Dr. Budi Santoso", "budi@kampus.ac.id", "Ilmu Komputer");
        System.out.println("ID         : " + d.getId());
        System.out.println("Nama       : " + d.getName());
        System.out.println("Email      : " + d.getEmail());
        System.out.println("Departemen : " + d.getDepartment());
        System.out.println("getInfo    : " + d.getInfo());
        System.out.println("toString   : " + d);

        // ── Test MataKuliah ─────────────────────────────────────────────────
        System.out.println("\n=== Test MataKuliah ===");
        MataKuliah mk = new MataKuliah("MK001", "Pemrograman Berorientasi Objek", 3, "D001");
        System.out.println("Kode      : " + mk.getCode());
        System.out.println("Nama      : " + mk.getName());
        System.out.println("SKS       : " + mk.getCredits());
        System.out.println("Dosen ID  : " + mk.getLecturerId());
        System.out.println("toString  : " + mk);

        // ── Test Enrollment ─────────────────────────────────────────────────
        System.out.println("\n=== Test Enrollment ===");
        Enrollment e = new Enrollment("12S24057", "MK001", 87.5, "Ganjil 2024");
        System.out.println("Student ID : " + e.getStudentId());
        System.out.println("Kode MK    : " + e.getCourseCode());
        System.out.println("Nilai      : " + e.getGrade());
        System.out.println("Semester   : " + e.getSemester());
        System.out.println("toString   : " + e);

        // ── Test Inheritance ────────────────────────────────────────────────
        System.out.println("\n=== Test Inheritance ===");
        Person p1 = m;
        Person p2 = d;
        System.out.println("Mahasiswa instanceof Person : " + (m instanceof Person));
        System.out.println("Dosen instanceof Person     : " + (d instanceof Person));
        System.out.println("getName() via Person (Mahasiswa) : " + p1.getName());
        System.out.println("getName() via Person (Dosen)     : " + p2.getName());
        System.out.println("getInfo() via Person (Mahasiswa) : " + p1.getInfo());
        System.out.println("getInfo() via Person (Dosen)     : " + p2.getInfo());
    }
}
