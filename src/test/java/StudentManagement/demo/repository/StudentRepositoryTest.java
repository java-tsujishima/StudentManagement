package StudentManagement.demo.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentCourse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索ができること(){
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生の登録が行えること(){
    Student student = new Student();
    student.setName("田中");
    student.setKanaName("たなか");
    student.setNickname("たなか");
    student.setEmail("tanaka@gmail.com");
    student.setArea("東京");
    student.setAge(34);
    student.setSex("男");
    student.setRemark("他のコースも受講中");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(7);
  }

  @Test
  void 受講生IDによる検索が行えること() {
    String studentId = "1";
    Student actual = sut.searchStudent(studentId);
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isEqualTo(studentId);
  }

  @Test
  void 受講生コース情報の全件検索ができること() {
    List<StudentCourse> actual = sut.searchStudentsCourseList();
    assertThat(actual).isNotEmpty();
  }

  @Test
  void 受講生IDに紐づく受講生コースが検索できること() {
    String studentId = "1";
    List<StudentCourse> actual = sut.searchStudentCourse(studentId);
    assertThat(actual).isNotEmpty();
  }

  @Test
  void 受講生の更新が行えること() {
    Student student = new Student();
    student.setName("田中");
    student.setKanaName("たなか");
    student.setNickname("たなか");
    student.setEmail("tanaka@gmail.com");
    student.setArea("東京");
    student.setAge(34);
    student.setSex("男");
    student.setRemark("他のコースも受講中");
    student.setDeleted(false);

    sut.updateStudent(student);

    Student updatedStudent = sut.searchStudent("2");
    assertThat(updatedStudent.getName()).isEqualTo("田中");
    assertThat(updatedStudent.getEmail()).isEqualTo("tanaka@gmail.com");
  }

  @Test
  void 受講生コースの更新が行えること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("1");
    studentCourse.setCourseName("JAVA");

    sut.updateStudentCourse(studentCourse);

    StudentCourse updatedCourse = sut.searchStudentCourse("1").get(0);
    assertThat(updatedCourse.getCourseName()).isEqualTo("JAVA");
  }
}