package StudentManagement.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import StudentManagement.demo.controller.converter.StudentConverter;
import StudentManagement.demo.data.Student;
import StudentManagement.demo.service.StudentService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;
  @MockBean
  private StudentConverter studentConverter;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生の一覧検索が実行出来て空のリストが返ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));

    verify(service, times(1)).searchStudentList();
  }


  @Test
  void 受講生詳細の受講生で入力チェックに異常が発生しないこと() {
    Student student = new Student("1","田中","たなか","たなか","tanaka@gmail.com",
        "東京",34,"男","他のコースも受講中");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いたときにチェックがかかること() {
    Student student = new Student("テスト","田中","たなか","たなか","tanaka@gmail.com",
        "東京",34,"男","他のコースも受講中");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
        .containsOnly("数字のみ入力するようにしてください。");
  }

  @Test
  void 受講生詳細の検索が実行できて空のリストが返ってくること() throws Exception {
    String id = "999";
    mockMvc.perform(get("/student/{id}", id))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(id);
  }

  @Test
  void 受講生詳細の登録ができて空で返ってくること() throws Exception {
    mockMvc.perform(post("/registerStudent")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""           
                   {
                   "student" : {
                   "name" : "田中",
                   "kanaName" : "たなか",
                   "nickname" : "たなか",
                   "email" : "tanaka@gmail.com",
                   "area" : "東京",
                   "age" : "34",
                   "sex" : "男",
                   "remark" : "他のコースも受講中"
                 },
                 "studentCourseList" : [
                    {
                       "courseName" : "Javaコース"
                    }
                  ]
                }
                """))
        .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }
}
