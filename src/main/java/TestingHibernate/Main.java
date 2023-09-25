package TestingHibernate;

import Hibernate.com.example.HibernateDemo.Students.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory factory = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernatestudents")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "samaan11")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true") // Enable SQL logging for debugging
//                .setProperty("current.session.context.class","thread")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.openSession();

        try {
            // Create a student object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Daffy", "DuckDuck", "daffy@luv2code.com");

            // Start a transaction
            session.beginTransaction();

            // Save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            // Commit transaction
            session.getTransaction().commit();

            // MY NEW CODE

            // Find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            // Now get a new session and start a transaction
            session = factory.openSession();
            session.beginTransaction();

            // Retrieve the student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());

            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get complete: " + myStudent);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
