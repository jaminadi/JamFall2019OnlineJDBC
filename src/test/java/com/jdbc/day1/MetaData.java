package com.jdbc.day1;

import com.jdbc.utilities.DBUtility;

import java.sql.SQLException;

public class MetaData {

    public static void main(String[] args) throws SQLException {

        System.out.println("User: " + DBUtility.metaData.getUserName());
        System.out.println("Version: " + DBUtility.metaData.getDriverVersion());
        System.out.println("Database type: " + DBUtility.metaData.getDatabaseProductName());
    }
}
