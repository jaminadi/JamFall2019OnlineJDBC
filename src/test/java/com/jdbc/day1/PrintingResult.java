package com.jdbc.day1;

import java.sql.*;

public class PrintingResult {

    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:oracle:thin:@23.20.33.199:1521:xe";
        String username = "hr";
        String password = "hr";

        Connection connection = DriverManager.getConnection(URL, username, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM employees");

        while (result.next()) {
            // String firstname = result.getString("first_name");
            // String lastname = result.getString("last_name");
            String firstname = result.getString(2);
            String lastname = result.getString(3);
            int salary = result.getInt("salary");
            System.out.println(firstname + " " + lastname + "  $" + salary);
        }


    }
}
