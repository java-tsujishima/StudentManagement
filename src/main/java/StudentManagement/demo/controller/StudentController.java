package StudentManagement.demo.controller;

import StudentManagement.demo.controller.converter.StudentConverter;
import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentsCourses;
import StudentManagement.demo.domain.StudentDetail;
import StudentManagement.demo.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Student> students = service.serchStudentList();
    List<StudentsCourses> studentsCourses = service.serchStudentCourseList();

    model.addAttribute("studentList",converter.convertStudentDetails(students, studentsCourses));
    return "studentList";
  }

  @GetMapping("/studentCourseList")
  public List<StudentsCourses> getStudentCourseList() {
    return service.serchStudentCourseList();
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model){
    model.addAttribute("studentDetail", new StudentDetail());
    return "registerStudent";
  }


  @PostMapping("/registerStudent")
  public String  registerStudent(@ModelAttribute StudentDetail  studentDetail, BindingResult result) {
    if(result.hasErrors()){
      return "registerStudent";
    }

    service.saveStudentDetail(studentDetail);
    return "redirect:/studentList";
  }

}
