package StudentManagement.demo.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  @NotBlank
  @Pattern(regexp = "^\\d+$")
  private String id;

  @NotBlank
  private String studentId;

  @NotBlank
  private String courseName;
  private LocalDate courseStartAt;
  private LocalDate courseEndAt;

}
