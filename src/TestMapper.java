import mapper.MahasiswaMapper;
import mapper.DosenMapper;
import mapper.MataKuliahMapper;
import model.Mahasiswa;
import model.Dosen;
import model.MataKuliah;
import java.util.List;

public class TestMapper {
    public static void main(String[] args) {
        System.out.println("=== Test Data Mapper ===\n");

        System.out.println("--- Semua Mahasiswa ---");
        MahasiswaMapper mhsMapper = new MahasiswaMapper();
        List<Mahasiswa> mahasiswaList = mhsMapper.findAll();
        for (Mahasiswa m : mahasiswaList) {
            System.out.println(m.getInfo());
        }

        System.out.println("\n--- Semua Dosen ---");
        DosenMapper dosenMapper = new DosenMapper();
        List<Dosen> dosenList = dosenMapper.findAll();
        for (Dosen d : dosenList) {
            System.out.println(d.getInfo());
        }

        System.out.println("\n--- Semua Mata Kuliah ---");
        MataKuliahMapper mkMapper = new MataKuliahMapper();
        List<MataKuliah> mkList = mkMapper.findAll();
        for (MataKuliah mk : mkList) {
            System.out.println(mk);
        }

        System.out.println("\n=== Semua mapper berhasil! ===");
    }
}
