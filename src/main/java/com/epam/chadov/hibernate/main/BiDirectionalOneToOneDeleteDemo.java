package com.epam.chadov.hibernate.main;

import com.epam.chadov.hibernate.entities.Instructor;
import com.epam.chadov.hibernate.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/31/2017.
 */
public class BiDirectionalOneToOneDeleteDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.getCurrentSession()) {
            //start a transaction
            session.beginTransaction();

            //get the instructor detail object
            int theId = 2;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            //print the associated instructor
            System.out.println("the associated instructor : " + tempInstructorDetail.getInstructor());


            //now delete the instructor detail
            System.out.println("Deleting tempInstructorDetail : " + tempInstructorDetail);
            session.delete(tempInstructorDetail);

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
