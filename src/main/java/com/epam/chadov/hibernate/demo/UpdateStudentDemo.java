package com.epam.chadov.hibernate.demo;

import com.epam.chadov.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/27/2017.
 */
public class UpdateStudentDemo {


    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();
        try {

            //start transaction
            session.beginTransaction();

            //retrieve student based on the id : 1
            Student myStudent = session.get(Student.class, 1);

            //update first name to Scooby
            myStudent.setFirstName("Scooby");

            //update all emails to 'foo@mail.com'
            session.createQuery("update Student set email = 'foo@mail.com'").executeUpdate();

            //commit the transaction
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
