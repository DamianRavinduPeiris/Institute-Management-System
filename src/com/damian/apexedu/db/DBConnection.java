package com.damian.apexedu.db;

import com.damian.apexedu.control.MySqlAuthenticatorController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static DBConnection dbc;
    public static Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost/apex_institute","root","1234");
    }
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if(dbc==null){
            dbc = new DBConnection();
            return dbc;
        }
        return dbc;
    }
    public static Connection getConnection(){
        return connection;
    }
}
