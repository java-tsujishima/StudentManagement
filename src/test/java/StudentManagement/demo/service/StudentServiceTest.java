package StudentManagement.demo.service;

import StudentManagement.demo.controller.converter.StudentConverter;
import StudentManagement.demo.domain.StudentDetail;
import StudentManagement.demo.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
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


  @Test
  void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {
    StudentService sut = new StudentService(repository, converter);
    List<StudentDetail> expected = new ArrayList<>();

    List<StudentDetail> actual = sut.searchStudentList();

    Assertions.assertEquals(expected, actual);
  }
}