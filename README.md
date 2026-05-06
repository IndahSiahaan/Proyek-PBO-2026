# Proyek-PBO-2026
# Sistem Manajemen Kampus
> Tugas Proyek Pemrograman Berorientasi Objek (PBO) - 2026

## Anggota Kelompok

| No | Nama | NIM | Bagian |
|----|------|-----|--------|
| 1  | Laura Awise   | *(12S24057)* | Database Setup, JDBC, Singleton, Schema SQL |
| 2  | *(Nama)*      | *(NIM)* | Model & Inheritance (Person, Mahasiswa, Dosen) |
| 3  | *(Nama)*      | *(NIM)* | ORM Data Mapper |
| 4  | *(Nama)*      | *(NIM)* | JCF, Service Layer, Main App |

---

## Deskripsi Proyek

Aplikasi **Sistem Manajemen Kampus** berbasis Java CLI yang mengelola data mahasiswa, dosen, mata kuliah, dan enrollment. Dibangun menggunakan konsep OOP lengkap dengan koneksi ke SQL Server.

## Teknologi

- Java 17+
- SQL Server 2022/2025
- JDBC (mssql-jdbc-13.4.0)
- VS Code

## Konsep OOP yang Diterapkan

| Konsep | Implementasi |
|--------|-------------|
| JDBC | `DatabaseConnection.java` |
| Singleton Pattern | `DatabaseConnection` satu instance |
| Inheritance | `Person` → `Mahasiswa`, `Dosen` |
| ORM / Data Mapper | `MahasiswaMapper`, `DosenMapper` |
| Java Collection Framework | `ArrayList`, `HashMap`, `TreeSet` |

## Struktur Folder
Proyek-PBO-2026/
├── src/
│   ├── db/
│   │   └── DatabaseConnection.java
│   ├── model/
│   │   ├── Person.java
│   │   ├── Mahasiswa.java
│   │   ├── Dosen.java
│   │   ├── MataKuliah.java
│   │   └── Enrollment.java
│   ├── mapper/
│   │   ├── Mapper.java
│   │   ├── MahasiswaMapper.java
│   │   └── DosenMapper.java
│   ├── service/
│   │   └── KampusService.java
│   └── Main.java
├── sql/
│   ├── schema.sql
│   └── seed.sql
├── lib/
│   └── mssql-jdbc-13.4.0.jre11.jar
└── README.md

## Cara Menjalankan

### 1. Siapkan Database (SSMS)
```sql
CREATE DATABASE academic_db;
-- Jalankan sql/schema.sql
-- Jalankan sql/seed.sql
```

### 2. Compile
javac -cp "lib\mssql-jdbc-13.4.0.jre11.jar" -d out src\db\DatabaseConnection.java
javac -cp "lib\mssql-jdbc-13.4.0.jre11.jar;out" -d out src\TestConnection.java

### 3. Test Koneksi
java -cp "lib\mssql-jdbc-13.4.0.jre11.jar;out" TestConnection

### 4. Jalankan Aplikasi
java -cp "lib\mssql-jdbc-13.4.0.jre11.jar;out" Main
