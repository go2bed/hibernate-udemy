package com.epam.chadov.hibernate.demo;

import com.epam.chadov.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/26/2017.
 */
public class PrimaryKeyDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();
        try {
            //create 3 student objects
            System.out.println("Creating 3 student objects...");
            Student tempStudent = new Student("John", "Doe", "john@mail.com");
            Student tempStudent2 = new Student("Marry", "Public", "marry@mail.com");
            Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@mail.com");


            //start transaction
            session.beginTransaction();

            //save the student
            System.out.println("Saving the students...");
            session.save(tempStudent);
            session.save(tempStudent2);
            session.save(tempStudent3);


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
