package com.epam.chadov.hibernate.studentdemo;

import com.epam.chadov.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/24/2017.
 */
public class ReadStudentDemo {

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
            Student tempStudent = new Student("Daffy", "Duck", "duffy@mail.com");

            //start transaction
            session.beginTransaction();

            //save the student
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit the transaction
            System.out.println("Commiting...");
            session.getTransaction().commit();


            //new code

            //find out the student's id: primary key

            System.out.println("Saved student. Generated if : " + tempStudent.getId());


            //get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id : primary key
            System.out.println("\n Getting student with id : " + tempStudent.getId() );
            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get complete : " + myStudent);
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
