package com.Team12.CS5800.VotingApplication.model;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.Team12.CS5800.VotingApplication.model.DataConnection.MyConnectionProvider;

public class SessionGrabber {
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	
	static Connection con;
    static PreparedStatement ps;
    
    private UserDAOImpl UDAO = new UserDAOImpl();
	
	public String generateSessionID() {
		return randomString(25);
	}
	
	public int storeSession(String sessionID, int userID) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("insert into sessions (sessionid, userid) values (?,?)");
			ps.setString(1, sessionID);
			ps.setInt(2, userID);
			status = ps.executeUpdate();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public int removeSession(String sessionID) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("delete from sessions where sessionid=?");
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

	public String checkAdminStatus(String sessionID) {
		AdminStatus aStatus = UDAO.getUser(sessionID).getAdminStatus();
		if (aStatus == AdminStatus.USER) {
			return "user";
		}
		else if (aStatus == AdminStatus.MANAGER) {
			return "manager";
		}
		else { // admin
			return "admin";
		}
	}
	
	public int getVoterStatus(String sessionID) {
		VoterStatus vStatus = UDAO.getUser(sessionID).getVoterStatus();
		if (vStatus == VoterStatus.NOT_APPLIED) {
			return 0;
		}
		else if (vStatus == VoterStatus.APPLIED) {
			return 1;
		}
		else { // approved
			return 2;
		}
	}

	public int getID(String sessionID) {
		return UDAO.getUser(sessionID).getID();
	}
	public String getFirstName(String sessionID) {
		return UDAO.getUser(sessionID).getFirstName();
	}
	
	public String getLastName(String sessionID) {
		return UDAO.getUser(sessionID).getLastName();
	}
	
	public String getAddress(String sessionID) {
		return UDAO.getUser(sessionID).getAddress();
	}
	
	public String getSSN(String sessionID) {
		return UDAO.getUser(sessionID).getSSN();
	}
	
	public String getCity(String sessionID) {
		return UDAO.getUser(sessionID).getCity();
	}
	
	public String getState(String sessionID) {
		return UDAO.getUser(sessionID).getState();
	}

	public String getZip(String sessionID) {
		return UDAO.getUser(sessionID).getZipCode();
	}
	
	public String getUsername(String sessionID) {
		return UDAO.getUser(sessionID).getUsername();
	}
	
	public String getPrecinct(String sessionID) {
		return UDAO.getUser(sessionID).getPrecinct();
	}
	
	public String getEmail(String sessionID) {
		return UDAO.getUser(sessionID).getEmail();
	}
	public String getGender(String sessionID) {
		return UDAO.getUser(sessionID).getGender();
	}
	public int getAge(String sessionID) {
		return UDAO.getUser(sessionID).getAge();
	}
	public String getEducation(String sessionID) {
		return UDAO.getUser(sessionID).getEducation();
	}
	
	public String getSecurityQuestion1(String sessionID) {
		return UDAO.getUser(sessionID).getSecurityQuestion1();
	}
	
	public String getSecurityQuestion2(String sessionID) {
		return UDAO.getUser(sessionID).getSecurityQuestion2();
	}
	
	public String getSecurityAnswer1(String sessionID) {
		return UDAO.getUser(sessionID).getSecurityAnswer1();
	}
	
	public String getSecurityAnswer2(String sessionID) {
		return UDAO.getUser(sessionID).getSecurityAnswer2();
	}
	
	
	public int storePasswordRequest(String requestID, int userID) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("insert into forgot_password_requests (requestid, userid) values (?,?)");
			ps.setString(1, requestID);
			ps.setInt(2, userID);
			status = ps.executeUpdate();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public int removePasswordRequest(String requestID) {
		int status = 0;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("delete from forgot_password_requests where requestid=?");
			ps.setString(1, requestID);
			status = ps.executeUpdate();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	


	
}
