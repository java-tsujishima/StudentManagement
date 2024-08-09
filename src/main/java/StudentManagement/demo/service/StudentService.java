package StudentManagement.demo.service;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentsCourses;
import StudentManagement.demo.domain.StudentDetail;
import StudentManagement.demo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> serchStudentList(){
    return repository.search();
  }

  public List<StudentsCourses> serchStudentCourseList() {
    return repository.searchStudentsCourse();
  }

  public void saveStudentDetail(StudentDetail studentDetail) {
    repository.insertStudentDetail(studentDetail.getStudent());
  }
}
