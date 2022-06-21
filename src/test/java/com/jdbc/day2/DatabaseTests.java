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

        List<Map<String, Integer>> employeeIDsMap = new ArrayList<>(); //list of map for employee_id column
        //here, we were trying to add employee_id as key, and id number as value
        //but in the map, we can only store one employee_id, that is why we create a list of maps, because does not
        // allow duplicates
        //it is called column_name value combination

        List<Map<String, String>> namesMap = new ArrayList<>();


        //while loop stops working once the condition becomes not true
        while (resultSet.next()) {
            Map<String, Integer> map = new HashMap<>(); //created a map
            map.put("employee_id", resultSet.getInt("employee_id")); // put the value inside
            employeeIDsMap.add(map); // added to the list

            employeeIDs.add(resultSet.getInt("employee_id"));

            String fullName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");

            names.add(fullName);
            Map<String, String> name = new HashMap<>();
            name.put("full_name", fullName);
            namesMap.add(name);
        }

        System.out.println(employeeIDs);
        System.out.println(names);
        System.out.println(employeeIDsMap);
        System.out.println(namesMap);

        //get 5th employee
        String fifthEmployee = namesMap.get(4).get("full_name");
        System.out.println("5th employee = " + fifthEmployee);

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void insertTest() throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String QUERY_GET_LAST_EMPLOYEE_ID = "SELECT MAX(employee_id) FROM EMPLOYEES"; //returns last employee_id
        ResultSet resultSet = statement.executeQuery(QUERY_GET_LAST_EMPLOYEE_ID); //this object contains result set data
        //since employee_id is an integer, we use getInt("column index") method and +1 to increment
        //basically, we need to add 1 to the last employee_id
        int employeeId = resultSet.getInt(1) + 1;

        //to check if email exists
        String QUERY_TO_CHECK_IF_EMAIL_EXISTS = "SELECT COUNT(*) FROM employees WHERE email = 'marat@cybertek.com'";
        //to check if email exists
        ResultSet resultSet2 = statement.executeQuery(QUERY_TO_CHECK_IF_EMAIL_EXISTS);

        boolean emailExists = resultSet2.getInt(1) > 0; //if count is positive, it will be true, meaning email exists

        //we need to check all employee_ids, so that we dont duplicate id number.
        String QUERY = "INSERT INTO employees VALUES (228, 'Marat', 'Mamedov', 'marat@cybertek.com', '666-777-7777', SYSDATE, 'IT_PROG', 70000, 0, NULL, NULL)";

        resultSet.close();
        statement.close();
        connection.close();
    }
}
