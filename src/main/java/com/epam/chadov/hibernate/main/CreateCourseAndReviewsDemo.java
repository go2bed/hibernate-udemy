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
public class CreateCourseAndReviewsDemo {

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

            //create a course
            Course tempCourse = new Course("Pacman - Hot To Score One Million points");

            //add some reviews
            tempCourse.addReview(new Review("Great course...love it!"));
            tempCourse.addReview(new Review("Good course., job well done!"));
            tempCourse.addReview(new Review("What a dumb course?!"));

            //save the course...and leverage the cascade all
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());

            session.save(tempCourse);

            //commit transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
