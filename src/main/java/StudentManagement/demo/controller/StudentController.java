package StudentManagement.demo.controller;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.Students_courses;
import StudentManagement.demo.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return service.serchStudentList();
  }

  @GetMapping("/studentCourseList")
  public List<Students_courses> getStudentCourseList() {
    return service.serchStudentCourseList();
  }
}
