package com.epam.chadov.hibernate.demo;

import com.epam.chadov.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * @author Andrey_Chadov on 10/27/2017.
 */
public class QueryStudentDemo {

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

            //query  student
            List<Student> studentList = session.createQuery("from Student").getResultList();

             //display the students
            displayTheStudents(studentList);


            //query students: lastName = 'Doe'
            studentList = session.createQuery("from Student s where  s.lastName = 'Doe'").getResultList();
            displayTheStudents(studentList);


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

    private static void displayTheStudents(List<Student> studentList) {
        System.out.println(studentList);
    }
}
