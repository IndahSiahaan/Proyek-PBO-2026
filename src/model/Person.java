package model;

/**
 * Person - Abstract Class (Inheritance)
 * Anggota 2: Kelvin Marpaung
 *
 * Class induk untuk Mahasiswa dan Dosen.
 * Tidak bisa di-instansiasi langsung (abstract).
 */
public abstract class Person {

    // Atribut umum semua orang
    protected String id;
    protected String name;
    protected String email;

    // Constructor
    public Person(String id, String name, String email) {
        this.id    = id;
        this.name  = name;
        this.email = email;
    }

    // Getter
    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getEmail() { return email; }

    // Setter
    public void setId(String id)       { this.id    = id; }
    public void setName(String name)   { this.name  = name; }
    public void setEmail(String email) { this.email = email; }

    /**
     * Method abstract - WAJIB di-override oleh class turunan.
     * Tiap turunan punya cara sendiri menampilkan info.
     */
    public abstract String getInfo();

    /**
     * Override toString() bawaan Java
     * Dipanggil otomatis saat objek di-print
     */
    @Override
    public String toString() {
        return getInfo();
    }
}