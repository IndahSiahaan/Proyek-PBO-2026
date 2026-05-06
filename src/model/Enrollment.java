package model;

/**
 * Enrollment - Model class
 * Anggota 2: Kelvin Marpaung
 *
 * Representasi tabel enrollments di database.
 * Menyimpan data mahasiswa yang mengambil mata kuliah beserta nilainya.
 */
public class Enrollment {

    private String studentId;
    private String courseCode;
    private double grade;
    private String semester;

    // Constructor
    public Enrollment(String studentId, String courseCode, double grade, String semester) {
        this.studentId  = studentId;
        this.courseCode = courseCode;
        this.grade      = grade;
        this.semester   = semester;
    }

    // Getter & Setter
    public String getStudentId()           { return studentId; }
    public String getCourseCode()          { return courseCode; }
    public double getGrade()               { return grade; }
    public String getSemester()            { return semester; }
    public void setStudentId(String id)    { this.studentId  = id; }
    public void setCourseCode(String code) { this.courseCode = code; }
    public void setGrade(double grade)     { this.grade      = grade; }
    public void setSemester(String sem)    { this.semester   = sem; }

    @Override
    public String toString() {
        return "Enrollment {" +
               "Mahasiswa ID: " + studentId +
               ", Kode MK: " + courseCode +
               ", Nilai: " + grade +
               ", Semester: " + semester +
               "}";
    }
}