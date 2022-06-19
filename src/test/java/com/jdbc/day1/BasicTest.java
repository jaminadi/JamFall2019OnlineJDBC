package com.jdbc.day1;

import java.sql.*;


public class BasicTest {

    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:oracle:thin:@23.20.33.199:1521:xe";
        String username = "hr";
        String password = "hr";
        //to establish connection with database
        Connection connection = DriverManager.getConnection(URL, username, password);

        //ResultSet.TYPE_SCROLL_INSENSITIVE:
        //the constant indicating the type for a <code>ResultSet</code> object
        //  * that is scrollable but generally not sensitive to changes to the data
        // * that underlies the <code>ResultSet</code> object
        //ResultSet.CONCUR_READ_ONLY: the constant indicating the concurrency mode for a  ResultSet</code> object
        // that may NOT be updated.
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // in executeQuery method, we provide query as a parameter
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        //resultSet.next() - returns true until it reaches last row and jumps to next row , if there is some row with data
        while (resultSet.next()) {
            //get data from 2nd column for every row
            //2nd column is first name info(column) from our SQL Dev table
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        }

        resultSet.beforeFirst(); //to go back to the beginning of the result set because in order to execute the command below,
        //we have to go back to the very beginning

        while (resultSet.next()) {
            System.out.println(resultSet.getString("salary"));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

}
