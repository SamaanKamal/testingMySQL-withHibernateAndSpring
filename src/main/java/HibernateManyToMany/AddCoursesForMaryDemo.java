package HibernateManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {

    public static void main(String[] args) {
        // Create session factory
        SessionFactory factory = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hb-05-many-to-many")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "samaan11")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true") // Enable SQL logging for debugging
                .setProperty("current.session.context.class","thread")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.openSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // get the student mary from database
            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);

            System.out.println("\nLoaded student: " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Development");

            // add student to courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            // save the courses
            System.out.println("\nSaving the courses ...");

            session.save(tempCourse1);
            session.save(tempCourse2);

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            // handle connection leak issue
            session.close();
            factory.close();
        }
    }
}
