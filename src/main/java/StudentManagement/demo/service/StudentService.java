package StudentManagement.demo.service;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.Students_courses;
import StudentManagement.demo.repository.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;
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
    return repository.search().stream()
        .filter(student -> student.getAge() >=30 && student.getAge() <40)
        .collect(Collectors.toList());
  }


  public List<Students_courses> serchStudentCourseList() {
    return repository.searchStudentsCourses().stream()
        .filter(studentsCourses -> "java".equals(studentsCourses.getCourseName()))
        .collect(Collectors.toList());
    //aaaa
  }
}
