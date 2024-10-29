CREATE TABLE IF NOT EXISTS students
(
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    kana_name VARCHAR(50) NOT NULL,
    nickname VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    area VARCHAR(50),
    age INT,
    sex VARCHAR(10),
    remake VARCHAR(255),
    is_deleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
(
     id INT PRIMARY KEY,
     student_id VARCHAR(36) NOT NULL,
     course_name(50) NOT NULL,
     course_start_at TIMESTAMP,
     course_end_at TIMESTAMP,
);