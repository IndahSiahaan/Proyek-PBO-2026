package service;

import mapper.MahasiswaMapper;
import mapper.DosenMapper;
import mapper.MataKuliahMapper;
import mapper.EnrollmentMapper;
import model.Mahasiswa;
import model.Dosen;
import model.MataKuliah;
import model.Enrollment;

import java.util.*;

public class KampusService {

    private MahasiswaMapper mahasiswaMapper;
    private DosenMapper dosenMapper;
    private MataKuliahMapper mataKuliahMapper;
    private EnrollmentMapper enrollmentMapper;

    public KampusService() {
        this.mahasiswaMapper  = new MahasiswaMapper();
        this.dosenMapper      = new DosenMapper();
        this.mataKuliahMapper = new MataKuliahMapper();
        this.enrollmentMapper = new EnrollmentMapper();
    }

    public ArrayList<Mahasiswa> getAllMahasiswa() {
        return new ArrayList<>(mahasiswaMapper.findAll());
    }

    public void tambahMahasiswa(String id, String nama, String email, String jurusan) {
        Mahasiswa mhs = new Mahasiswa(id, nama, email, jurusan);
        mahasiswaMapper.save(mhs);
    }

    public Mahasiswa cariMahasiswaById(String id) {
        return mahasiswaMapper.findById(id);
    }

    public TreeSet<String> getMahasiswaUrutNama() {
        TreeSet<String> sorted = new TreeSet<>();
        for (Mahasiswa m : mahasiswaMapper.findAll()) {
            sorted.add(m.getName() + " (" + m.getId() + ")");
        }
        return sorted;
    }

    public HashMap<String, ArrayList<Mahasiswa>> getMahasiswaPerJurusan() {
        HashMap<String, ArrayList<Mahasiswa>> map = new HashMap<>();
        for (Mahasiswa m : mahasiswaMapper.findAll()) {
            String jurusan = m.getMajor();
            if (!map.containsKey(jurusan)) {
                map.put(jurusan, new ArrayList<>());
            }
            map.get(jurusan).add(m);
        }
        return map;
    }

    public ArrayList<Dosen> getAllDosen() {
        return new ArrayList<>(dosenMapper.findAll());
    }

    public void tambahDosen(String id, String nama, String email, String dept) {
        Dosen dosen = new Dosen(id, nama, email, dept);
        dosenMapper.save(dosen);
    }

    public HashMap<String, ArrayList<Dosen>> getDosenPerDepartemen() {
        HashMap<String, ArrayList<Dosen>> map = new HashMap<>();
        for (Dosen d : dosenMapper.findAll()) {
            String dept = d.getDepartment();
            if (!map.containsKey(dept)) {
                map.put(dept, new ArrayList<>());
            }
            map.get(dept).add(d);
        }
        return map;
    }

    public ArrayList<MataKuliah> getAllMataKuliah() {
        return new ArrayList<>(mataKuliahMapper.findAll());
    }

    public void tambahMataKuliah(String kode, String nama, int sks, String dosenId) {
        MataKuliah mk = new MataKuliah(kode, nama, sks, dosenId);
        mataKuliahMapper.save(mk);
    }

    public void daftarkanMahasiswa(String mhsId, String kode, String semester) {
        Enrollment enr = new Enrollment(mhsId, kode, 0.0, semester);
        enrollmentMapper.save(enr);
    }

    public ArrayList<Enrollment> getNilaiMahasiswa(String mhsId) {
        return new ArrayList<>(enrollmentMapper.findByStudentId(mhsId));
    }

    public double getRataRataNilai(String mhsId) {
        ArrayList<Enrollment> list = getNilaiMahasiswa(mhsId);
        if (list.isEmpty()) return 0.0;
        double total = 0;
        for (Enrollment e : list) total += e.getGrade();
        return total / list.size();
    }

    public LinkedHashMap<String, Double> getRankingMahasiswa() {
        ArrayList<Mahasiswa> semua = getAllMahasiswa();
        HashMap<String, Double> rataMap = new HashMap<>();
        for (Mahasiswa m : semua) {
            rataMap.put(m.getName(), getRataRataNilai(m.getId()));
        }
        LinkedHashMap<String, Double> ranking = new LinkedHashMap<>();
        rataMap.entrySet()
               .stream()
               .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
               .forEach(e -> ranking.put(e.getKey(), e.getValue()));
        return ranking;
    }
}
