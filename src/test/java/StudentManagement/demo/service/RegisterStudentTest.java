package StudentManagement.demo.service;

import static org.junit.jupiter.api.Assertions.*;
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
class RegisterStudentTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  @Mock
  private StudentService sut;

  @BeforeEach
  void before(){
    sut = new StudentService(repository, converter);
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
}