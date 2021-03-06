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

        resultSet.beforeFirst(); //to go back to the beginning of the result set

        //some technical information about database
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        //some technical information about resultset
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("JDBC driver: " + databaseMetaData.getDriverName());
        System.out.println("JDBC driver version: " + databaseMetaData.getDriverVersion());
        System.out.println("Database name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("Database version: " + databaseMetaData.getDatabaseProductVersion());

        System.out.println("Number of columns: " + resultSetMetaData.getColumnCount());
        System.out.println("First column name: " + resultSetMetaData.getColumnName(1));
        System.out.println("Data type of the 1st column: " + resultSetMetaData.getColumnTypeName(1));

        System.out.println("###################################");

        //resultset returns column names

        /*
        The % means that what follows is an argument that will be formatted.
        Then follows a - resulting in left alignment.
        20 fills the string up to a length of 15 characters (adding spaces at the end).
        Finally the s means, that you are formatting a string.
         */

        //this loop will iterate through columns
        for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
            System.out.printf("%-20s", resultSetMetaData.getColumnName(columnIndex));
        }

        System.out.println("");

        //iterate through rows
        while (resultSet.next()) {
            //iterate through columns
            for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
                System.out.printf("%-20s", resultSet.getString(columnIndex) + " ");
            }
            System.out.println("");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

}
