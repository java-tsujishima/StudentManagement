package StudentManagement.demo.repository;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.Students_courses;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 受講生情報を扱うリポジトリ
 *
 * 全検索や単一条件での、コース情報の検索ができるクラス
 */
@Mapper
public interface StudentRepository {

  /**
   * 全権検索します。
   *
   * @return　全権検索した受講生情報の一覧
   */

  @Select("select * from students")
  List<Student> search();

  @Select("select * from students_courses")
  List<Students_courses> searchStudentsCourses();

}