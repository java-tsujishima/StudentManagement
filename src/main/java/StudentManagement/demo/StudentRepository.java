package StudentManagement.demo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {

  @Select("select * from students_courses")
  List<Students_courses> search();

}