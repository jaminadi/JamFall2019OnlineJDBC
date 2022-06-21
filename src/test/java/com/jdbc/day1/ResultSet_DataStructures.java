package com.jdbc.day1;

import com.jdbc.utilities.DBUtility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSet_DataStructures {

    public static void main(String[] args) throws SQLException {

        ResultSet result = DBUtility.getResult("SELECT * FROM employees");

        //we want to get first names and last names of the employees
        while (result.next()) {
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");

            String fullName = firstName + " " + lastName;
            System.out.println(fullName);
        }
    }
}
