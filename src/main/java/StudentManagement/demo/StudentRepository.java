package StudentManagement.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {

  @Select("select * from student where name = #{name}")
  Student searchByName(String name);
}
