# Sistem Manajemen Kampus
> Tugas Proyek Pemrograman Berorientasi Objek (PBO) - 2026

## Anggota Kelompok

| No | Nama | NIM | Bagian |
|----|------|-----|--------|
| 1  | Laura Awise | 12S24057 | Database Setup, JDBC, Singleton, Schema SQL |
| 2  | Kelvin Marpaung | 12S24018 | Model & Inheritance (Person, Mahasiswa, Dosen) |
| 3  | Indah Siahaan | 12S24052 | ORM Data Mapper |
| 4  | Dea Hutapea | 12S24053 | JCF, Service Layer, Main App |

---

## Deskripsi Proyek

Aplikasi **Sistem Manajemen Kampus** berbasis Java CLI yang mengelola data mahasiswa, dosen, mata kuliah, dan enrollment nilai. Dibangun menggunakan konsep OOP lengkap dengan koneksi ke SQL Server 2025.

Fitur aplikasi:
- Lihat, tambah, dan cari data mahasiswa
- Lihat dan tambah data dosen
- Lihat dan tambah mata kuliah
- Daftarkan mahasiswa ke mata kuliah
- Lihat nilai dan rata-rata mahasiswa
- Ranking mahasiswa berdasarkan nilai tertinggi

---

## Teknologi yang Digunakan

- Java 17+
- SQL Server 2025
- JDBC — mssql-jdbc-13.4.0.jre11
- VS Code

---

## Konsep OOP yang Diterapkan

| Konsep | Implementasi | File |
|--------|-------------|------|
| JDBC | Koneksi Java ke SQL Server | `DatabaseConnection.java` |
| Singleton Pattern | Satu instance koneksi DB | `DatabaseConnection.java` |
| Inheritance | Person sebagai abstract class | `Person.java` → `Mahasiswa.java`, `Dosen.java` |
| Abstract Class | Method getInfo() wajib di-override | `Person.java` |
| ORM Data Mapper | Mapping ResultSet ke objek Java | `MahasiswaMapper.java`, `DosenMapper.java`, dll |
| Java Collection Framework | Olah data dengan koleksi | `KampusService.java` |
| ArrayList | Menyimpan list mahasiswa/dosen | `KampusService.java` |
| HashMap | Kelompokkan per jurusan/departemen | `KampusService.java` |
| TreeSet | Urutkan nama mahasiswa A-Z | `KampusService.java` |
| LinkedHashMap | Ranking nilai mahasiswa | `KampusService.java` |

---

## Struktur Folder
Proyek-PBO-2026/
├── src/
│   ├── db/
│   │   └── DatabaseConnection.java   ← Singleton JDBC (Anggota 1)
│   ├── model/
│   │   ├── Person.java               ← Abstract class (Anggota 2)
│   │   ├── Mahasiswa.java            ← extends Person (Anggota 2)
│   │   ├── Dosen.java                ← extends Person (Anggota 2)
│   │   ├── MataKuliah.java           ← (Anggota 2)
│   │   └── Enrollment.java           ← (Anggota 2)
│   ├── mapper/
│   │   ├── Mapper.java               ← Interface generik (Anggota 3)
│   │   ├── MahasiswaMapper.java      ← (Anggota 3)
│   │   ├── DosenMapper.java          ← (Anggota 3)
│   │   ├── EnrollmentMapper.java     ← (Anggota 3)
│   │   └── MataKuliahMapper.java     ← (Anggota 3)
│   ├── service/
│   │   └── KampusService.java        ← JCF logic (Anggota 4)
│   ├── Main.java                     ← Entry point CLI (Anggota 4)
│   ├── TestConnection.java           ← Test JDBC
│   ├── TestModel.java                ← Test Inheritance
│   └── TestMapper.java               ← Test Data Mapper
├── sql/
│   ├── schema.sql                    ← DDL: struktur tabel + foreign key
│   └── seed.sql                      ← DML: data awal
└── README.md

---

## Cara Menjalankan

### 1. Siapkan Database di SSMS
```sql
CREATE DATABASE academic_db;
-- Jalankan sql/schema.sql
-- Jalankan sql/seed.sql
```

### 2. Letakkan Driver JDBC
Taruh `mssql-jdbc-13.4.0.jre11.jar` di folder `lib/`.

### 3. Compile Semua File
javac -cp "lib\mssql-jdbc-13.4.0.jre11.jar" -d out src\db\DatabaseConnection.java src\model\Person.java src\model\Mahasiswa.java src\model\Dosen.java src\model\MataKuliah.java src\model\Enrollment.java src\mapper\Mapper.java src\mapper\MahasiswaMapper.java src\mapper\DosenMapper.java src\mapper\EnrollmentMapper.java src\mapper\MataKuliahMapper.java src\service\KampusService.java src\Main.java

### 4. Jalankan Aplikasi
java -cp "lib\mssql-jdbc-13.4.0.jre11.jar;out" Main

### 5. Test Koneksi Saja
javac -cp "lib\mssql-jdbc-13.4.0.jre11.jar;out" -d out src\TestConnection.java
java -cp "lib\mssql-jdbc-13.4.0.jre11.jar;out" TestConnection

---

## Tampilan Aplikasi
╔════════════════════════════════════╗
║   SISTEM MANAJEMEN KAMPUS v1.0     ║
╚════════════════════════════════════╝
╔════════════════════════════╗
║        MENU UTAMA          ║
╠════════════════════════════╣
║ 1. Data Mahasiswa          ║
║ 2. Data Dosen              ║
║ 3. Data Mata Kuliah        ║
║ 4. Nilai & Enrollment      ║
║ 5. Ranking Mahasiswa       ║
║ 0. Keluar                  ║
╚════════════════════════════╝

---

