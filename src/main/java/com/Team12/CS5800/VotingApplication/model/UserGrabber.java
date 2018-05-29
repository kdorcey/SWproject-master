package com.Team12.CS5800.VotingApplication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.Team12.CS5800.VotingApplication.model.DataConnection.MyConnectionProvider;

public class UserGrabber {
	
	static Connection con;
    static PreparedStatement ps;
    
    private UserDAOImpl UDAO = new UserDAOImpl();
    
    public ArrayList<User> getAppliedUsersByPrecinct(String precinct) {
    		ArrayList<User> userList = new ArrayList<User>();
    		
    		try {
    		
	    		con = MyConnectionProvider.getCon();
	    		ps = con.prepareStatement("SELECT * FROM user_info WHERE precinct = ? AND voter_status = ?");
	    		ps.setString(1, precinct);
	    		ps.setInt(2, 1); //applied
	    		ResultSet rs = ps.executeQuery();
	
	    		if(!rs.first()) {
	    			return userList;
	    		}
	    		else {
	    			rs.first();
	    			int id = rs.getInt(1);
				userList.add(UDAO.getUserWithID(id));
	    			while(rs.next()) {
	    				id = rs.getInt(1);
	    				userList.add(UDAO.getUserWithID(id));
	    			} 
		        
		        rs.close();
		        ps.close();
		        con.close();
	    		}
    		}
    		catch (Exception e) {
    			System.out.println(e);
    		}
    		return userList;
    }
    

    public ArrayList<User> getAppliedUsers() {
    		ArrayList<User> userList = new ArrayList<User>();
    		
    		try {
    		
	    		con = MyConnectionProvider.getCon();
	    		ps = con.prepareStatement("SELECT * FROM user_info WHERE voter_status = ?");
	    		ps.setInt(1, 1); //applied
	    		ResultSet rs = ps.executeQuery();
	
	    		if(!rs.first()) {
	    			return userList;
	    		}
	    		else {
	    			rs.first();
	    			int id = rs.getInt(1);
				userList.add(UDAO.getUserWithID(id));
	    			while(rs.next()) {
	    				id = rs.getInt(1);
	    				userList.add(UDAO.getUserWithID(id));
	    			} 
		        
		        rs.close();
		        ps.close();
		        con.close();
	    		}
    		}
    		catch (Exception e) {
    			System.out.println(e);
    		}
    		return userList;
    }
    

	
}
