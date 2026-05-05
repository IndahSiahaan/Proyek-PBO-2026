USE academic_db;

CREATE TABLE students (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    major VARCHAR(100)
);

CREATE TABLE lecturers (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100)
);

CREATE TABLE courses (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    credits INT
);

CREATE TABLE enrollments (
    student_id VARCHAR(10),
    course_code VARCHAR(10),
    grade FLOAT,
    PRIMARY KEY (student_id, course_code)
);