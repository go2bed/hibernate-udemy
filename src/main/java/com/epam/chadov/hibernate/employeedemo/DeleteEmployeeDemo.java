package com.epam.chadov.hibernate.employeedemo;

import com.epam.chadov.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Andrey_Chadov on 10/27/2017.
 */
public class DeleteEmployeeDemo {

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

            session = factory.getCurrentSession();
            //start transaction
            session.beginTransaction();

            //retrieve student
            Employee myEmployee = session.get(Employee.class, tempEmployee.getId());
            Employee myEmployee1 = session.get(Employee.class, tempEmployee1.getId());

            //delete student
            session.delete(myEmployee);
            session.createQuery("delete from Employee where id = " + myEmployee1.getId()).executeUpdate();
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
