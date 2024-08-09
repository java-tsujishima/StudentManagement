package StudentManagement.demo.domain;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentsCourses;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;

}
