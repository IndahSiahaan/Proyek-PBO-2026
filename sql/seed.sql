USE academic_db;
GO

INSERT INTO lecturers (id, name, email, department) VALUES
('D001', 'Dr. Andi Pratama',   'andi@kampus.ac.id',  'Teknik Informatika'),
('D002', 'Dr. Siti Rahma',     'siti@kampus.ac.id',  'Sistem Informasi'),
('D003', 'Prof. Budi Santoso', 'budi@kampus.ac.id',  'Teknik Informatika'),
('D004', 'Dr. Maya Indah',     'maya@kampus.ac.id',  'Sistem Informasi');
GO

INSERT INTO students (id, name, email, major) VALUES
('M001', 'Laura Awise',  'laura@mahasiswa.ac.id', 'Teknik Informatika'),
('M002', 'Indah Siahaan', 'indah@mahasiswa.ac.id',  'Teknik Informatika'),
('M003', 'Dea Anggreany',     'dea@mahasiswa.ac.id', 'Sistem Informasi'),
('M004', 'Kelvin Marpaung',     'kelvin@mahasiswa.ac.id',  'Sistem Informasi'),
('M005', 'Jay Park',  'jay@mahasiswa.ac.id',   'Teknik Informatika');
GO

INSERT INTO courses (code, name, credits, lecturer_id) VALUES
('PBO001', 'Pemrograman Berorientasi Objek', 3, 'D001'),
('BD001',  'Basis Data',                     3, 'D002'),
('ASD001', 'Algoritma dan Struktur Data',    3, 'D003'),
('RPL001', 'Rekayasa Perangkat Lunak',       3, 'D004'),
('WEB001', 'Pemrograman Web',                2, 'D001');
GO

INSERT INTO enrollments (student_id, course_code, grade, semester) VALUES
('M001', 'PBO001', 88.5, '2024/1'),
('M001', 'BD001',  91.0, '2024/1'),
('M001', 'WEB001', 85.0, '2024/1'),
('M002', 'PBO001', 75.0, '2024/1'),
('M002', 'ASD001', 80.5, '2024/1'),
('M003', 'BD001',  95.0, '2024/1'),
('M003', 'RPL001', 88.0, '2024/1'),
('M004', 'RPL001', 72.5, '2024/1'),
('M004', 'WEB001', 78.0, '2024/1'),
('M005', 'PBO001', 90.0, '2024/1'),
('M005', 'ASD001', 85.5, '2024/1');
GO

PRINT 'Seed data berhasil dimasukkan.';