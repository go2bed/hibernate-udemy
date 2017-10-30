package com.epam.chadov.hibernate.main;

import com.epam.chadov.hibernate.entities.Instructor;
import com.epam.chadov.hibernate.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/30/2017.
 */
public class CreateDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create the objects
        System.out.println("Creating instructor...");
        Instructor tempInstructor = new Instructor("Andrew", "Doe", "fi@mail.com");

        InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/go2bed", "guitar");

        //associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        try (Session session = sessionFactory.getCurrentSession()) {
            //start a transaction
            session.beginTransaction();

            //save the instructor
            //
            //Note : this will also save the details object
            //because of CascadeType.ALL
            //
            System.out.println("Saving instructor : " + tempInstructor);
            session.save(tempInstructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
