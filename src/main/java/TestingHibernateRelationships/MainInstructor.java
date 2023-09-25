package TestingHibernateRelationships;

import Hibernate.com.example.HibernateDemo.Students.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MainInstructor {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory factory = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "samaan11")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true") // Enable SQL logging for debugging
//                .setProperty("current.session.context.class","thread")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.openSession();

        try {
            // Create a student object
            System.out.println("Creating new Instructor object...");
            Instructor tempInstructor = new Instructor("Samaan", "Kamal", "Samaan@luv2code.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.luv2code.com/youtube", "Luv 2 code!!!");

            tempInstructor.setInstructorDetail(tempInstructorDetail);
            // Start a transaction
            session.beginTransaction();

            // Save the student object
            System.out.println("Saving the Instructor...");
            System.out.println(tempInstructor);
            session.save(tempInstructor);

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
