package com.epam.chadov.hibernate.employeedemo;

import com.epam.chadov.hibernate.entity.Employee;
import com.epam.chadov.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/27/2017.
 */
public class UpdateEmployeeDemo {


    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();
        try {

            //start transaction
            session.beginTransaction();

            //retrieve student based on the id : 1
            Employee myEmployee = session.get(Employee.class, 1);

            //update first name to Scooby
            myEmployee.setFirstName("Scooby");

            //update all emails to 'foo@mail.com'
            session.createQuery("update Employee set company = 'rog'").executeUpdate();

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
