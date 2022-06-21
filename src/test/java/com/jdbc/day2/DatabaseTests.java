package com.jdbc.day2;

import org.junit.Test;

import java.sql.*;
import java.util.*;

public class DatabaseTests {

    final String DB_URL = "jdbc:oracle:thin:@23.20.33.199:1521:xe";
    final String DB_USER = "hr";
    final String DB_PASSWORD = "hr";

    @Test
    public void getEmployeesData() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // ResultSet.CONCUR_READ_ONLY creates ResultSet object that cannot be updated but can be read (disable editing)
        //ResultSet.TYPE_SCROLL_INSENSITIVE - make ResultSet scrollable

        String QUERY = "SELECT * FROM employees";
        ResultSet resultSet = statement.executeQuery(QUERY);

        //we want to get data as an arraylist
        List<Integer> employeeIDs = new ArrayList<>();
        List<String> names = new ArrayList<>();

        List<Map<String, Integer>> employeeIDsMap = new ArrayList<>();


        //while loop stops working once the condition becomes not true
        while (resultSet.next()) {
            Map<String, Integer> map = new HashMap<>(); //created a map
            map.put("employee_id", resultSet.getInt("employee_id")); // put the value inside
            employeeIDsMap.add(map); // added to the list

            employeeIDs.add(resultSet.getInt("employee_id"));
            names.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }
        System.out.println(employeeIDs);
        System.out.println(names);
        System.out.println(employeeIDsMap);

        resultSet.close();
        statement.close();
        connection.close();
    }
}
