package com.jdbc.utilities;

import java.sql.*;

public class DBUtility {

        private final static String username = ConfigurationReader.getProperty("JDBC_username");
        private final static String password = ConfigurationReader.getProperty("JDBC_password");
        public final static String url = ConfigurationReader.getProperty("JDBC_URL");

        private static Connection connection;
        private static Statement statement;

        public static DatabaseMetaData metaData;

        static {
                try {
                        connection = DriverManager.getConnection(url, username, password);
                        statement = connection.createStatement();
                        metaData = connection.getMetaData();
                } catch (Exception e) {
                }
        }

        //this method creates the result set
        public static ResultSet getResult(String sql) {
                ResultSet result = null; //null will be replaced later on, it is just temporary value for initialization
                try {
                        result = statement.executeQuery(sql);
                } catch (Exception e) {
                }
                return result;
        }

        //this will close the connection at the last step
        public static void tearDown() {
                try {
                        connection.close();
                } catch (Exception e) {
                }
        }

        public static void updateQuery(String sql) {
                //INSERT, UPDATE, DELETE, ALTER, TRUNCATE, DROP
                try {
                        statement.executeUpdate(sql);
                } catch (SQLException e) {
                }
        }

}
