package StudentManagement.demo.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseStatus {

    private int id;

    private int studentCourseId;

    @NotBlank
    private String status;

    public CourseStatus() {
    }

    // 引数付きコンストラクタ
    public CourseStatus(int id, int studentCourseId, String status) {
        this.id = id;
        this.studentCourseId = studentCourseId;
        this.status = status;
    }
  }