package StudentManagement.demo.controller.converter;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentCourse;
import StudentManagement.demo.domain.StudentDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    /**
     * 受講生に紐ずく受講生コース情報をマッピングする。
     * 受講生コース情報は受講生に対して複数存在するのでループを回して受講生情報詳細を組み立てる
     * @param studentList　受講生一覧
     * @param studentCourseList　受講生コース情報のリスト
     * @return　受講生詳細情報のリスト
     */
    public List<StudentDetail> convertStudentDetails(List<Student> studentList,
        List<StudentCourse> studentCourseList) {
      List<StudentDetail> studentDetails = new ArrayList<>();
      studentList.forEach(student -> {
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);

        List<StudentCourse> convertStudentCourseList = studentCourseList.stream()
            .filter(studentCourse -> student.getId().equals(studentCourse.getStudentId()))
            .collect(Collectors.toList());
        studentDetail.setStudentCourseList(convertStudentCourseList);
        studentDetails.add(studentDetail);
      });
      return studentDetails;
    }
}
