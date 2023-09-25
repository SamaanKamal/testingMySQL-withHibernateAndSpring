package HibernateOneToOneUniDirectional;

import HibernateOneToOneBiDirectional.Instructor;
import HibernateOneToOneBiDirectional.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MainDeleteInstructor {
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
                .addAnnotatedClass(HibernateOneToOneBiDirectional.InstructorDetail.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.openSession();

        try {
            // Start a transaction
            session.beginTransaction();
            // get instructor by primary key / id
            int theId = 1;
            Instructor tempInstructor =
                    session.get(Instructor.class, theId);

            System.out.println("Found instructor: " + tempInstructor);

            // delete the instructors
            if (tempInstructor != null) {

                System.out.println("Deleting: " + tempInstructor);

                // Note: will ALSO delete associated "details" object
                // because of CascadeType.ALL
                //
                session.delete(tempInstructor);
            }
            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
