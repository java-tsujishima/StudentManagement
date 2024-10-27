package StudentManagement.demo.service;

import StudentManagement.demo.controller.converter.StudentConverter;
import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentCourse;
import StudentManagement.demo.domain.StudentDetail;
import StudentManagement.demo.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や更新処理を行います。
 */

@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  public List<StudentDetail> searchStudentList(){
    List<Student> studentList = repository.search();
    List<StudentCourse> studentsCourseList = repository.searchStudentsCourseList();
    return converter.convertStudentDetails(studentList, studentsCourseList);
  }

  /**
   * 受講生検索
   * IDに紐ずく受講生情報を取得したあと、その受講生に紐ずく受講生コース情報を取得して設定します。
   * @param id
   * @return
   */
  public StudentDetail searchStudent(String id) {
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentsCourse = repository.searchStudentCourse(student.getId());
    return new StudentDetail(student, studentsCourse);
  }

  public List<StudentCourse> serchStudentCourseList() {
    return repository.searchStudentsCourseList();
  }

  /**
   * 受講生詳細の登録を行います
   * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報には受講生情報を紐づける値とコース開始日、終了日を設定
   * @param studentDetail　受講生詳細
   * @return　登録情報を付与した受講生詳細
   */

  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentsCourse -> {
      initStudentCourse(studentsCourse, student);
      repository.registerStudentCourse(studentsCourse);
    });
    return studentDetail;
  }

  /**
   *
   * @param studentsCourse 受講生コース情報
   * @param student　受講生
   */
  private void initStudentCourse(StudentCourse studentsCourse, Student student) {
    LocalDateTime now = LocalDateTime.now();

    studentsCourse.setStudentId(student.getId());
    studentsCourse.setCourseStartAt(now.toLocalDate());
    studentsCourse.setCourseEndAt(now.plusYears(1).toLocalDate());
  }

  /**
   * 受講生詳細の更新を行います。受講生と受講生コース情報をそれぞれ更新します。
   * @param studentDetail
   */

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList()
        .forEach(studentCourse -> repository.updateStudentCourse(studentCourse));
  }
}
