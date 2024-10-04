package StudentManagement.demo;

import StudentManagement.demo.repository.StudentRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "受講生管理システム"))
@SpringBootApplication
public class DemoApplication {
  private StudentRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}