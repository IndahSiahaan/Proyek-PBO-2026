package model;

/**
 * Mahasiswa - extends Person (Inheritance)
 * Anggota 2: Kelvin Marpaung
 *
 * Turunan dari Person, punya atribut tambahan: major (jurusan)
 */
public class Mahasiswa extends Person {

    private String major; // Jurusan mahasiswa

    // Constructor
    public Mahasiswa(String id, String name, String email, String major) {
        super(id, name, email); // Panggil constructor Person
        this.major = major;
    }

    // Getter & Setter
    public String getMajor()        { return major; }
    public void setMajor(String m)  { this.major = m; }

    /**
     * Override method abstract dari Person
     * Wajib diimplementasikan!
     */
    @Override
    public String getInfo() {
        return "Mahasiswa {" +
               "ID: " + id +
               ", Nama: " + name +
               ", Email: " + email +
               ", Jurusan: " + major +
               "}";
    }
}
