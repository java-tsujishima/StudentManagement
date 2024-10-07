package StudentManagement.demo.repository;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.StudentCourse;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐ずくrepositoryです
 * @return
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を行います。
   * @return　受講生一覧（全件）
   */
  List<Student> search();

  /**
   * 受講生の検索を行います。
   * @param id　受講生ID
   * @return　受講生
   */
  Student searchStudent(String id);

  /**
   * 受講生のコース情報の全件検索を行います。
   * @return　受講生のコース情報（全件）
   */
  @Select("select * from students_courses")
  List<StudentCourse> searchStudentsCourseList();

  /**
   * 受講生IDに紐ずく受講生コースを検索します。
   * @param studentId　受講生ID
   * @return　受講生IDに紐ずく受講生コース情報
   */
  @Select("select * from students_courses where student_id = #{studentId}")
  List<StudentCourse> searchStudentCourse(String studentId);

  /**
   * 受講生新規登録をします。IDに関しては新規採番を行う
   * @param student　受講生
   */

  @Insert("insert into students (name, kana_name, nickname, email, area, age, sex, remark, is_deleted)" +
      "VALUES (#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("insert into students_courses(student_id, course_name, course_start_at, course_end_at)"
       + "values (#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentCourse(StudentCourse studentsCourse);

  @Update("update students set name = #{name}, kana_name = #{kanaName}, nickname = #{nickname},"
    + "email = #{email}, area = #{area}, age = #{age}, sex = #{sex}, remark = #{remark}, is_deleted = #{isDeleted} where id = #{id}")
  void updateStudent(Student student);

  @Update("update students_courses set course_name = #{courseName} where id = #{id}")
  void updateStudentCourse(StudentCourse studentCourse);
}