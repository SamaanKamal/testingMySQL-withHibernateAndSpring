package HibernateOneToOneBiDirectional;

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
                .setProperty("current.session.context.class","thread")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.openSession();

        try {
            // Start a transaction
            session.beginTransaction();
            // get the instructor detail object
            int theId = 1;
            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            // print  the associated instructor
            System.out.println("the associated instructor: " +
                    tempInstructorDetail.getInstructor());

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
