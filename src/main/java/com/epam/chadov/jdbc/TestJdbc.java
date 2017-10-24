package com.epam.chadov.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Andrey_Chadov on 10/24/2017.
 */
public class TestJdbc {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        String user = "hbstudent";
        String password = "hbstudent";
        try {
            System.out.println("Connection to database: " + jdbcUrl);

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, password);

            System.out.println("Connection is successful");

            myConn.close();

            System.out.println("Connection is closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
