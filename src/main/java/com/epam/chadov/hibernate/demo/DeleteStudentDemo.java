package com.epam.chadov.hibernate.demo;

import com.epam.chadov.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/27/2017.
 */
public class DeleteStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();
        try {
            //create a students objects
            System.out.println("Creating a student object...");
            Student tempStudent = new Student("Paul", "Liberty", "grob@mail.com");
            tempStudent.setId(33);

            Student tempStudent1 = new Student("Karl", "Karl", "karl@mail.com");
            tempStudent.setId(12);

            //start transaction
            session.beginTransaction();
            //save the student
            System.out.println("Saving the student...");
            session.save(tempStudent);
            session.save(tempStudent1);

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            //start transaction
            session.beginTransaction();

            //retrieve student
            Student myStudent = session.get(Student.class, tempStudent.getId());
            Student myStudent1 = session.get(Student.class, tempStudent1.getId());

            //delete student
            session.delete(myStudent);
            session.createQuery("delete from Student where id = " + tempStudent1.getId()).executeUpdate();
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
