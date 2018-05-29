package com.Team12.CS5800.VotingApplication.model;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Team12.CS5800.VotingApplication.model.DataConnection.MyConnectionProvider;

public class EmailAuthGrabber {
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	
	static Connection con;
    static PreparedStatement ps;
    
    private UserDAOImpl UDAO = new UserDAOImpl();
	
	public String generateEmailAuthID() {
		return randomString(25);
	}
	
	public int storeEmailAuthKey(String emailAuthID, int userID) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("insert into emailtokens (emailAuthKey, userid) values (?,?)");
			ps.setString(1, emailAuthID);
			ps.setInt(2, userID);
			status = ps.executeUpdate();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public int removeEmailKey(String sessionID) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("delete from emailtokens where emailAuthKey = ?");
			ps.setString(1, sessionID);
			status = ps.executeUpdate();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}


	public int updateEmailStatus(String emailAuthKey) {
		return UDAO.verifyEmail(emailAuthKey);
	}
	
	public int checkValidatedEmail(String sessionID) {
		try {
			
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from sessions where sessionid = ?");
			ps.setString(1, sessionID);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			int id = rs.getInt(2);
			rs.close();
			ps.close();
			
			ps = con.prepareStatement("select * from user_info where id = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			rs.first();
			int emailStatus = rs.getInt(11);
			rs.close();
			ps.close();
			con.close();
			
			return emailStatus;
					
			} catch (Exception e) {
	             System.out.println(e);
	             return 0;
	         }
	}
	
}
