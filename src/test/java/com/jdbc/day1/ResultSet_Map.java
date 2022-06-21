package com.jdbc.day1;

import com.jdbc.utilities.DBUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultSet_Map {

    public static void main(String[] args) throws SQLException {

        ResultSet result = DBUtility.getResult("SELECT * FROM employees");
        Map<String, Integer> employeeInfo = new LinkedHashMap<>(); //because LinkedHashMap keeps the result in the original order

        //find out the name of employee who makes 9000
        //String nameOfEmployee9000 = "";
        while (result.next()) {
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");

            //here, we got the employee names
            String fullName = firstName + " " + lastName;
            //now we need to get salary of the employees
            int salary = result.getInt("salary");

            employeeInfo.put(fullName, salary);

//            if (salary == 9000){
//                System.out.println(fullName + " is making 9000");
//            }
        }
        System.out.println(employeeInfo);

        System.out.println(employeeInfo.get("Steven King") == 24000);


    }
}
