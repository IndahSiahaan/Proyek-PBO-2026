package model;

public class Mahasiswa extends Person {

    private String major; // Jurusan mahasiswa

    public Mahasiswa(String id, String name, String email, String major) {
        super(id, name, email); // Panggil constructor Person
        this.major = major;
    }

    public String getMajor()        { return major; }
    public void setMajor(String m)  { this.major = m; }

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
