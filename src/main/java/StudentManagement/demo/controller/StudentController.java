package StudentManagement.demo.controller;

import StudentManagement.demo.controller.converter.StudentConverter;
import StudentManagement.demo.domain.StudentDetail;
import StudentManagement.demo.service.StudentService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**受講生の検索や登録、更新等を行うREST　APIとして実行されるcontrollerです。
 *
 */
@Validated
@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
  }

  /**
   * 受講生詳細の一覧検索です。
   * 全件検索を行うので、条件指定は行わないものになります。
   *
   * @return　受講生詳細一覧（全件）
   */

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }
  /**
   * 受講生検索です。IDに紐ずく受講生情報を取得した後、その受講生に紐ずく受講生コース情報を取得して設定します。
   * IDに紐ずく任意の受講生の情報を検索します。
   *
   * @param id 受講生id
   * @return　受講生詳細検索
   */
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id) {
    return service.searchStudent(id);
  }

  /**
   * 受講生詳細の登録を行います。
   * @param studentDetail　受講生詳細
   * @return　実行結果
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail>  registerStudent(@RequestBody StudentDetail  studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。キャンセルフラグの更新もここで行います（論理削除）
   * @param studentDetail　受講生詳細
   * @return　実行結果
   */

    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
      service.updateStudent(studentDetail);
      return ResponseEntity.ok("更新処理が成功しました。");
    }
  }
