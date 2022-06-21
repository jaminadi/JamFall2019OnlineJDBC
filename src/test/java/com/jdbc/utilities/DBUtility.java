package com.jdbc.utilities;

import jdk.nashorn.internal.runtime.regexp.joni.Config;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtility {

        final static String username = ConfigurationReader.getProperty("JDBC_username"),
                password = ConfigurationReader.getProperty("JDBC_password"),
                url = ConfigurationReader.getProperty("JDBC_URL");

        static Connection connection;
        static Statement statement;

        static {
                try {
                        connection = DriverManager.getConnection(url, username, password);
                        statement = connection.createStatement();
                } catch (Exception e) {
                }
        }

        public static ResultSet getResult(String sql) {
                ResultSet result = null; //null will be replaced later on, it is just temporary value for initialization
                try {
                        result = statement.executeQuery(sql);
                } catch (Exception e) {
                }

                return result;
        }

}
