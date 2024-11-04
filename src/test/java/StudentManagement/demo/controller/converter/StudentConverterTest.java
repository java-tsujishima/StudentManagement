package StudentManagement.demo.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentCourse;
import StudentManagement.demo.domain.StudentDetail;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentConverterTest {

  private StudentConverter sut;

  @BeforeEach
  void before() {
    sut = new StudentConverter();
  }

  @Test
  void 受講生のリストと受講生コース情報を渡して受講生詳細のリストが作成できること() {
    Student student = createStudent();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("java");
    studentCourse.setCourseStartAt(LocalDate.now());
    studentCourse.setCourseEndAt(LocalDate.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡したときに紐づかないコースは除外されること() {
    Student student = createStudent();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("1");
    studentCourse.setStudentId("2");
    studentCourse.setCourseName("java");
    studentCourse.setCourseStartAt(LocalDate.now());
    studentCourse.setCourseEndAt(LocalDate.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEmpty();
  }

  private static Student createStudent() {
    Student student = new Student("1", "田中", "たなか", "たなか", "tanaka@gmail.com", "東京", 34,
        "男", "他のコースも受講中");
    student.setDeleted(false);
    return student;
  }
}
