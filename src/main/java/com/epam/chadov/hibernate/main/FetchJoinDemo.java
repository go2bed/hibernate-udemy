package com.epam.chadov.hibernate.main;

import com.epam.chadov.hibernate.entities.Course;
import com.epam.chadov.hibernate.entities.Instructor;
import com.epam.chadov.hibernate.entities.InstructorDetail;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Andrey_Chadov on 10/30/2017.
 */
public class FetchJoinDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            //start a transaction
            session.beginTransaction();

            //option 2 : Hibernate query with HQL

            //get the instructor from db
            int theId = 1;

            Query<Instructor> query =
                    session.createQuery(" select i from Instructor AS i " +
                            "JOIN FETCH i.courses " +
                            "WHERE i.id =:theInstructorId", Instructor.class);


            //set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("My some : Instructor: " + tempInstructor);

            //commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();
            System.out.println("\nThe session is now closed!\n");

            //since courses are lazy loaded...this should fail
            //get courses from the instructor
            System.out.println("Courses: " + tempInstructor.getCourses());


            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
