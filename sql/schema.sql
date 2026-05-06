USE academic_db;
GO

-- Hapus tabel lama jika ada (urutan: tabel anak dulu)
IF OBJECT_ID('enrollments', 'U') IS NOT NULL DROP TABLE enrollments;
IF OBJECT_ID('courses',     'U') IS NOT NULL DROP TABLE courses;
IF OBJECT_ID('students',    'U') IS NOT NULL DROP TABLE students;
IF OBJECT_ID('lecturers',   'U') IS NOT NULL DROP TABLE lecturers;
GO

CREATE TABLE lecturers (
    id         VARCHAR(10)  NOT NULL,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100),
    department VARCHAR(100),
    CONSTRAINT PK_lecturers PRIMARY KEY (id)
);
GO

CREATE TABLE students (
    id    VARCHAR(10)  NOT NULL,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    major VARCHAR(100),
    CONSTRAINT PK_students PRIMARY KEY (id)
);
GO

CREATE TABLE courses (
    code        VARCHAR(10)  NOT NULL,
    name        VARCHAR(100) NOT NULL,
    credits     INT          NOT NULL DEFAULT 2,
    lecturer_id VARCHAR(10),
    CONSTRAINT PK_courses    PRIMARY KEY (code),
    CONSTRAINT FK_courses_lc FOREIGN KEY (lecturer_id) REFERENCES lecturers(id)
);
GO

CREATE TABLE enrollments (
    student_id  VARCHAR(10) NOT NULL,
    course_code VARCHAR(10) NOT NULL,
    grade       FLOAT,
    semester    VARCHAR(10),
    CONSTRAINT PK_enrollments PRIMARY KEY (student_id, course_code),
    CONSTRAINT FK_enroll_std  FOREIGN KEY (student_id)  REFERENCES students(id),
    CONSTRAINT FK_enroll_crs  FOREIGN KEY (course_code) REFERENCES courses(code)
);
GO

PRINT 'Schema berhasil dibuat.';