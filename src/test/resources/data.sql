INSERT INTO students (name, kana_name, nickname,  email, area, age, sex, remark, is_deleted)
VALUES ('辻嶋泰士', 'ツジシマタイシ', 'ツジシマ', 'aaaa@bbbbbcom', '九州', 26, '男', '他のコースも受講中', false),
       ('田中', 'たなか', 'たなか', 'tanaka@gmail.com', '東京', 34, '男', '他のコースも受講中', false),
       ('伊藤', 'いとう', 'いとう', 'itou@gmail.com', '大阪', 29, '男', '特になし', false),
       ('佐藤', 'さとう', 'さとう', 'sato@gmail.com', '福岡', 24, '女', '他のコースも受講中', false),
       ('鈴木', 'すずき', 'すずき', 'suzuki@gmail.com', '北海道', 28, '女', '特になし', false),
       ('吉田', 'ヨシダ', 'ヨシ', 'yosida@gmail.com', '沖縄', 30, '男', '特になし', false);

INSERT INTO students_courses (student_id, course_name, course_start_at, course_end_at)
VALUES (1, 'java', '2024-05-27 00:00:00', '2025-02-26 00:00:00'),
       (2, 'HTML', '2024-05-07 00:00:00', '2025-01-07 00:00:00'),
       (3, 'PHP', '2024-06-27 00:00:00', '2025-03-30 00:00:00'),
       (4, 'JAVA', '2024-04-10 00:00:00', '2025-02-14 00:00:00'),
       (5, 'Ruby', '2024-10-10 00:00:00', '2025-07-05 00:00:00'),
       (6, 'Ruby', '2024-08-16 00:00:00', '2025-08-16 00:00:00');
