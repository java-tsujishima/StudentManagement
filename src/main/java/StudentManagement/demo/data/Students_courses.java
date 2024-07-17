package StudentManagement.demo.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Students_courses {

  private String id;
  private String studentId;
  private String courseName;
  private LocalDate courseStartAt;
  private LocalDate courseEndAt;

}
