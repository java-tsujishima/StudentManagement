package StudentManagement.demo.controller.converter;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentsCourses;
import StudentManagement.demo.domain.StudentDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

  @Component
public class StudentConverter {

    public List<StudentDetail> convertStudentDetails(List<Student> students,
        List<StudentsCourses> studentsCourses) {
      List<StudentDetail> studentDetails = new ArrayList<>();
      students.forEach(student -> {
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);

        List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
            .filter(studentsCourse -> student.getId().equals(studentsCourse.getId()))
            .collect(Collectors.toList());
        studentDetail.setStudentsCourses(convertStudentCourses);
        studentDetails.add(studentDetail);
      });
      return studentDetails;
    }
}
