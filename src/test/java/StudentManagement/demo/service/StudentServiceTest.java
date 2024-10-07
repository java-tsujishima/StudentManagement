package StudentManagement.demo.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import StudentManagement.demo.controller.converter.StudentConverter;
import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentCourse;
import StudentManagement.demo.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private  StudentRepository repository;

  @Mock
  private StudentConverter converter;

  @Test
  void 受講生詳細の一覧検索＿リポジトリとコンバーターの処理が適切に呼び出せていること(){
    StudentService sut = new StudentService(repository, converter);
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentsCourseList()).thenReturn(studentCourseList);

    sut.searchStudentList();

    List<Student> test = List.of(new Student());

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentsCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }
}