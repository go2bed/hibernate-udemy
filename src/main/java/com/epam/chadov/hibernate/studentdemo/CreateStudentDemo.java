package com.epam.chadov.hibernate.studentdemo;

import com.epam.chadov.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/24/2017.
 */
public class CreateStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();
        try {
            //create a student object
            System.out.println("Creating a student object...");
            Student tempStudent = new Student("Paul", "Wall", "grob@mail.com");

            //start transaction
            session.beginTransaction();

            //save the student
            System.out.println("Saving the student...");
            session.save(tempStudent);

            //commit the transaction
            System.out.println("Commiting...");
            session.getTransaction().commit();


            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            System.out.println("Factory is closed");
        }

    }
}
