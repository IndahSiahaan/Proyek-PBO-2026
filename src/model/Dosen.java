package model;

/**
 * Dosen - extends Person (Inheritance)
 * Anggota 2: Kelvin Marpaung
 *
 * Turunan dari Person, punya atribut tambahan: department (departemen)
 */
public class Dosen extends Person {

    private String department; // Departemen dosen

    // Constructor
    public Dosen(String id, String name, String email, String department) {
        super(id, name, email); // Panggil constructor Person
        this.department = department;
    }

    // Getter & Setter
    public String getDepartment()         { return department; }
    public void setDepartment(String dep) { this.department = dep; }

    /**
     * Override method abstract dari Person
     * Wajib diimplementasikan!
     */
    @Override
    public String getInfo() {
        return "Dosen {" +
               "ID: " + id +
               ", Nama: " + name +
               ", Email: " + email +
               ", Departemen: " + department +
               "}";
    }
}