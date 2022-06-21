package com.jdbc.day1;

import com.jdbc.utilities.DBUtility;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSet_DataStructures {

    public static void main(String[] args) throws SQLException {

        ResultSet result = DBUtility.getResult("SELECT * FROM employees");

        List<String> employeeNames = new ArrayList<>();

        //we want to get first names and last names of the employees
        while (result.next()) {
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");

            String fullName = firstName + " " + lastName;
            employeeNames.add(fullName);
        }

        System.out.println(employeeNames);

        //we need to verify that name Steven King exists in the employees database
        //Assert.assertTrue(employeeNames.contains("Steven King"));
        System.out.println(employeeNames.contains("Steven King"));

        DBUtility.tearDown();
    }
}
