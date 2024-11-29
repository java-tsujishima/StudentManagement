package StudentManagement.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import StudentManagement.demo.controller.converter.StudentConverter;
import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentCourse;
import StudentManagement.demo.domain.StudentDetail;
import StudentManagement.demo.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検索＿リポジトリとコンバーターの処理が適切に呼び出せていること() {
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentsCourseList()).thenReturn(studentCourseList);

    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentsCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生詳細の更新_リポジトリとコンバーターの処理が適切に呼び出せていること() {
    StudentDetail studentDetail = new StudentDetail();
    Student student = new Student();
    List<StudentCourse> studentCourseList = new ArrayList<>();

    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(studentCourseList);

    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(student);
    studentCourseList.forEach(studentCourse -> {
      verify(repository, times(1)).updateStudentCourse(studentCourse);
    });
  }

  @Test
  void 受講生詳細の登録_リポジトリとコンバーターの処理が適切に呼び出せていること() {
    StudentDetail studentDetail = new StudentDetail();
    Student student = new Student();
    List<StudentCourse> studentCourseList = new ArrayList<>();

    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(studentCourseList);

    sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    studentCourseList.forEach(studentCourse -> {
      verify(repository, times(1)).registerStudentCourse(studentCourse);
    });
  }

  @Test
  void 受講生検索の単一検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {
    String studentId = "1";
    Student student = new Student();
    student.setId(studentId);
    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.searchStudent(studentId)).thenReturn(student);
    when(repository.searchStudentCourse(studentId)).thenReturn(studentCourseList);

    sut.searchStudent(studentId);

    verify(repository, times(1)).searchStudent(studentId);
    verify(repository, times(1)).searchStudentCourse(studentId);
  }
}