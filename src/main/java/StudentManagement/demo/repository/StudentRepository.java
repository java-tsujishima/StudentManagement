package StudentManagement.demo.repository;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentsCourses;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface StudentRepository {


  @Select("select * from students")
  List<Student> search();

  @Select("select * from students_courses")
  List<StudentsCourses> searchStudentsCourse();

  @Insert("insert into students (id, name, kana_name, nickname, email, area, age, sex, remark)" +
      "VALUES (#{id}, #{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark})")
  void insertStudentDetail(Student student);
}