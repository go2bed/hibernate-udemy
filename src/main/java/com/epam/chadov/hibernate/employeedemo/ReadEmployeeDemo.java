package com.epam.chadov.hibernate.employeedemo;

import com.epam.chadov.hibernate.entity.Employee;
import com.epam.chadov.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/24/2017.
 */
public class ReadEmployeeDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();
        try {
            //create a Employees objects
            System.out.println("Creating a Employee object...");
            Employee tempEmployee = new Employee("Paul", "Liberty", "epam");
            tempEmployee.setId(33);

            Employee tempEmployee1 = new Employee("Karl", "Karl", "epam");
            tempEmployee.setId(12);

            //start transaction
            session.beginTransaction();
            //save the student
            System.out.println("Saving the Employee...");
            session.save(tempEmployee);
            session.save(tempEmployee1);

            session.getTransaction().commit();

            //new code

            //find out the employee's id: primary key

            System.out.println("Saved employee. Generated if : " + tempEmployee.getId());


            //get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id : primary key
            System.out.println("\n Getting student with id : " + tempEmployee.getId() );
            Employee myEmployee = session.get(Employee.class, tempEmployee.getId());

            System.out.println("Get complete : " + myEmployee);
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
