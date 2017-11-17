package com.epam.chadov.hibernate.main;

import com.epam.chadov.hibernate.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/30/2017.
 */
public class CreateCourseAndStudentsDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()) {
            //start a transaction
            session.beginTransaction();

            //create a course
            Course tempCourse = new Course("One - Hot To Score One Million points");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            //create the students
            Student tempStudent1 = new Student("John", "Doe", "doe@mail.com");
            Student tempStudent2 = new Student("Marry", "Public", "marry@mail.com");

            //add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            //save the students
            System.out.println("Saving students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println(" Saved students: " + tempCourse.getStudents());


            //commit transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
