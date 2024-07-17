package StudentManagement.demo;

import StudentManagement.demo.data.Student;
import StudentManagement.demo.data.Students_courses;
import StudentManagement.demo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

  private StudentRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}

