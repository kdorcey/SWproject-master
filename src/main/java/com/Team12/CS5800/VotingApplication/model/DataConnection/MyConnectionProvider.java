package com.Team12.CS5800.VotingApplication.model.DataConnection;

import java.sql.*;

public class MyConnectionProvider implements MyProvider {

    static Connection con = null;


    public static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver") ;
            con = DriverManager.getConnection(connUrl, username, pwd);
            
            Statement stmt = con.createStatement(
               ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            

            
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
