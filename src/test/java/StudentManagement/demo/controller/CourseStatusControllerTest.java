package StudentManagement.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import StudentManagement.demo.controller.converter.StudentConverter;
import StudentManagement.demo.data.CourseStatus;
import StudentManagement.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(StudentController.class)
class CourseStatusControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;
  @MockBean
  private StudentConverter studentConverter;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void courseStatusをIDで検索できること() throws Exception {
    // Arrange
    CourseStatus mockCourseStatus = new CourseStatus(1, 1,"仮申込");
    when(service.searchCourseStatus(1)).thenReturn(mockCourseStatus);

    // Act & Assert
    mockMvc.perform(get("/CourseStatus/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(mockCourseStatus)));

    verify(service).searchCourseStatus(1);
  }

  @Test
  void courseStatusを登録できること() throws Exception {
    CourseStatus courseStatus = new CourseStatus();
    courseStatus.setId(1);
    courseStatus.setStudentCourseId(1);
    courseStatus.setStatus("仮申込");

    MvcResult result = mockMvc.perform(post("/registerCourseStatus")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("CourseStatusが正常に登録されました。"))
        .andReturn();

    // サービスが呼び出されたことを検証 (戻り値がないのでモック設定は不要)
    verify(service).registerCourseStatus(courseStatus);
  }

  @Test
  void courseStatusを更新できること() throws Exception {
    // Arrange
    CourseStatus updatedCourseStatus = new CourseStatus(1,1, "受講終了");
    Mockito.doNothing().when(service).updateCourseStatus(eq(1), any(CourseStatus.class));

    // Act & Assert
    mockMvc.perform(put("/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedCourseStatus)))
        .andExpect(status().isOk())
        .andExpect(content().string("CourseStatusが正常に更新されました。"));

    verify(service).updateCourseStatus(eq(1), any(CourseStatus.class));
  }
}
