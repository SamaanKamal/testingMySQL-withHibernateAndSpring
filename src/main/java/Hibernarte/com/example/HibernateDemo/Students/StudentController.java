package Hibernarte.com.example.HibernateDemo.Students;

import Hibernarte.com.example.HibernateDemo.Students.Student;
import Hibernarte.com.example.HibernateDemo.Students.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/students")
public class StudentController {

        private final StudentService studentService;

        @Autowired
        public StudentController(StudentService studentService) {
            this.studentService = studentService;
        }

        @RequestMapping("/")
        public String saveStudent() {
            // create a student object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

            // save the student using the service
            System.out.println("Saving the student...");
            studentService.saveStudent(tempStudent);

            System.out.println("Done!");

            return "HibernateConfirmation"; // Redirect to a list page
        }
}
