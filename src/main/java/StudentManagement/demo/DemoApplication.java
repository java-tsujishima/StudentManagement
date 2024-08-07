package StudentManagement.demo;

import StudentManagement.demo.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

  private StudentRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}