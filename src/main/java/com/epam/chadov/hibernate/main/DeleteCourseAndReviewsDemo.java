package com.epam.chadov.hibernate.main;

import com.epam.chadov.hibernate.entities.Course;
import com.epam.chadov.hibernate.entities.Instructor;
import com.epam.chadov.hibernate.entities.InstructorDetail;
import com.epam.chadov.hibernate.entities.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/30/2017.
 */
public class DeleteCourseAndReviewsDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            //start a transaction
            session.beginTransaction();

            //get the course
            int theId = 12;
            Course tempCourse = session.get(Course.class, theId);

            System.out.println("Deleting the course ..." + tempCourse);
            System.out.println("reviews : " + tempCourse.getReviews());
            session.delete(tempCourse);

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
