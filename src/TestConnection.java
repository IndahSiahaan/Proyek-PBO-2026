public class TestConnection {
    public static void main(String[] args) {
        try {
            System.out.println("Mencoba koneksi...");
            var conn = DatabaseConnection.getConnection();
            System.out.println("Koneksi berhasil!");

            // TEST INSERT
            StudentDAO.updateStudent("S01", "Laura", "Sistem Informasi");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}