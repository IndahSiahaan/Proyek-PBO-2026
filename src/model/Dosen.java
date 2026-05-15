package model;

public class Dosen extends Person {

    private String department; // Departemen dosen

    public Dosen(String id, String name, String email, String department) {
        super(id, name, email); // Panggil constructor Person
        this.department = department;
    }

    public String getDepartment()         { return department; }
    public void setDepartment(String dep) { this.department = dep; }

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