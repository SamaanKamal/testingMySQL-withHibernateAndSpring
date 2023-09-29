package EagerAndLazyLoading;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCourses {
    public static void main(String[] args) {
        // Create session factory
        SessionFactory factory = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hb-03-one-to-many")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "samaan11")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true") // Enable SQL logging for debugging
                .setProperty("current.session.context.class","thread")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try
        {
            session.beginTransaction();
            // get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class,theId);

            System.out.println("EagerInstructor: "+ tempInstructor);
            // get courses from instructor
            System.out.println("Courses: "+ tempInstructor.getCourses());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
