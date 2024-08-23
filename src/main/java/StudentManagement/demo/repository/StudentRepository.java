package StudentManagement.demo.repository;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentsCourses;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface StudentRepository {


  @Select("select * from students")
  List<Student> search();

  @Select("select * from students where id = #{id}")
  Student searchStudent(String id);

  @Select("select * from students_courses")
  List<StudentsCourses> searchStudentsCoursesList();

  @Select("select * from students_courses where student_id = #{studentId}")
  List<StudentsCourses> searchStudentsCourses(String studentId);

  @Insert("insert into students (name, kana_name, nickname, email, area, age, sex, remark, is_deleted)" +
      "VALUES (#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("insert into students_courses(student_id, course_name, course_start_at, course_end_at)"
       + "values (#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentsCourses);


  @Update("update students set name = #{name}, kana_name = #{kanaName}, nickname = #{nickname},"
    + "email = #{email}, area = #{area}, age = #{age}, sex = #{sex}, remark = #{remark}, is_deleted = #{isDeleted} where id = #{id}")
  void updateStudent(Student student);

  @Update("update students_courses set course_name = #{courseName} where id = #{id}")
  void updateStudentsCourses(StudentsCourses studentsCourses);
}