package model;

public class MataKuliah {

    private String code;
    private String name;
    private int    credits;
    private String lecturerId;

    public MataKuliah(String code, String name, int credits, String lecturerId) {
        this.code       = code;
        this.name       = name;
        this.credits    = credits;
        this.lecturerId = lecturerId;
    }

    public String getCode()              { return code; }
    public String getName()              { return name; }
    public int    getCredits()           { return credits; }
    public String getLecturerId()        { return lecturerId; }
    public void setCode(String code)     { this.code = code; }
    public void setName(String name)     { this.name = name; }
    public void setCredits(int credits)  { this.credits = credits; }
    public void setLecturerId(String id) { this.lecturerId = id; }

    @Override
    public String toString() {
        return "MataKuliah {" +
               "Kode: " + code +
               ", Nama: " + name +
               ", SKS: " + credits +
               ", Dosen ID: " + lecturerId +
               "}";
    }
}