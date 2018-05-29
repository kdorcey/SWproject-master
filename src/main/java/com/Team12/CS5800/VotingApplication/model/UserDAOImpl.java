package com.Team12.CS5800.VotingApplication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Team12.CS5800.VotingApplication.model.DataConnection.MyConnectionProvider;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class UserDAOImpl implements UserDAO {
	
	static Connection con;
	static PreparedStatement ps;
	
	
	public int insertUser( String username, String password, int adminStatus, String email, int voterstatus, String firstName, String lastName, String ssn, String address, String city, String state, String zipcode, String precinct, String gender, int age, String education, String securityQuestion1, String securityQuestion1Answer, String securityQuestion2, String securityQuestion2Answer ) {
		int status = 0;
        try {
        	
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    		String hashedPassword = passwordEncoder.encode(password);
    		

        	// Create new user entry: username, password, adminStatus
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("insert into users (username, password,adminStatus)values(?,?,?)");
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.setInt(3, 0);
            ps.executeUpdate();
            ps.close();
            
            // get id back
            ps = con.prepareStatement("select * from users where username = ?");
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            rs.first();
            int id = rs.getInt(1);
            rs.close();
            ps.close();
            
            ps = con.prepareStatement("INSERT INTO user_info (id, email, voter_status, first_name, last_name, ssn, address, city, state, zipcode, precinct, gender, age, education, securityQuestion1, securityAnswer1, securityQuestion2, securityAnswer2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        // 
            ps.setInt(1, id);
            ps.setString(2, email);
            ps.setInt(3, voterstatus);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setString(6, ssn);
            ps.setString(7, address);
            ps.setString(8, city);
            ps.setString(9, state);
            ps.setString(10, zipcode);
            ps.setString(11, precinct);
            ps.setString(12, gender);
            ps.setInt(13, age);
            ps.setString(14, education);
            ps.setString(15, securityQuestion1);
            ps.setString(16, securityQuestion1Answer);
            ps.setString(17, securityQuestion2);
            ps.setString(18, securityQuestion2Answer);
            ps.executeUpdate();

            status = 1;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            status = 0;
        }
        return status;
	}
	
	public boolean validateUser(String username, String password) {
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from users where username = ?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			String dbPassword = rs.getString(3);
			ps.close();
			con.close();
			rs.close();
			if (dbPassword != null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	    			if (passwordEncoder.matches(password, dbPassword)) {
	    				return true;
	    			}
			}
		} catch (Exception e) {
            System.out.println(e);
        }
		return false;
	}
	
	public User getUserWithID(int id) {
		//SELECT * FROM db.users WHERE id = 18;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from users where id = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			String username = rs.getString(2);
			String password = rs.getString(3);
			int adminStatus = rs.getInt(4);
			rs.close();
			ps.close();
			
			ps = con.prepareStatement("select * from user_info where id = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			rs.first();
			String email = rs.getString(2);
			int voterStatus = rs.getInt(3);
			String firstName = rs.getString(4);
			String lastName = rs.getString(5);
			String ssn = rs.getString(6);
			String address = rs.getString(7);
			String city = rs.getString(8);
			String state = rs.getString(9);
			String zipcode = rs.getString(10);
			String precinct = rs.getString(12);
			String gender = rs.getString(13);
			int age = rs.getInt(14);
			String education = rs.getString(15);
			String securityQuestion1 = rs.getString(16);
			String securityAnswer1 = rs.getString(17);
			String securityQuestion2 = rs.getString(18);
			String securityAnswer2 = rs.getString(19);
			rs.close();
			ps.close();
			con.close();
			
			return new User(id, username, password, adminStatus, email, voterStatus, firstName, lastName, ssn, address, city, state, zipcode, precinct, gender, age, education, securityQuestion1, securityAnswer1, securityQuestion2, securityAnswer2);
			
			} catch (Exception e) {
	             System.out.println(e);
	         }
			
			return new User(0, "", "", 0, "", 0, "", "", "", "", "", "", "", "", "", 0, "", "", "", "", "");
	}
	
	public User getUserWithUsername(String username) {
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from users where username = ?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			int id = rs.getInt(1);
			String password = rs.getString(3);
			int adminStatus = rs.getInt(4);
			rs.close();
			ps.close();
			
			ps = con.prepareStatement("select * from user_info where id = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			rs.first();
			String email = rs.getString(2);
			int voterStatus = rs.getInt(3);
			String firstName = rs.getString(4);
			String lastName = rs.getString(5);
			String ssn = rs.getString(6);
			String address = rs.getString(7);
			String city = rs.getString(8);
			String state = rs.getString(9);
			String zipcode = rs.getString(10);
			String precinct = rs.getString(12);
			String gender = rs.getString(13);
			int age = rs.getInt(14);
			String education = rs.getString(15);
			String securityQuestion1 = rs.getString(16);
			String securityAnswer1 = rs.getString(17);
			String securityQuestion2 = rs.getString(18);
			String securityAnswer2 = rs.getString(19);
			rs.close();
			ps.close();
			con.close();
			
			return new User(id, username, password, adminStatus, email, voterStatus, firstName, lastName, ssn, address, city, state, zipcode, precinct, gender, age, education, securityQuestion1, securityAnswer1, securityQuestion2, securityAnswer2);
			
			} catch (Exception e) {
	             System.out.println(e);
	         }
			
			return new User(0, "", "", 0, "", 0, "", "", "", "", "", "", "", "", "", 0, "", "", "", "", "");
	}
			
	// Returns a user object based on the session id
	// SELECT * FROM db.users WHERE db.users.username = "username";
	public User getUser(String sessionID) {
		
		try {
		
		con = MyConnectionProvider.getCon();
		ps = con.prepareStatement("select * from sessions where sessionid = ?");
		ps.setString(1, sessionID);
		
		ResultSet rs = ps.executeQuery();
		rs.first();
		int id = rs.getInt(2);
		rs.close();
		ps.close();
		
		ps = con.prepareStatement("select * from users where id = ?");
		ps.setInt(1, id);
		
		rs = ps.executeQuery();
		rs.first();
		String username = rs.getString(2);
		String password = rs.getString(3);
		int adminStatus = rs.getInt(4);
		rs.close();
		ps.close();
		
		ps = con.prepareStatement("select * from user_info where id = ?");
		ps.setInt(1, id);
		
		rs = ps.executeQuery();
		rs.first();
		String email = rs.getString(2);
		int voterStatus = rs.getInt(3);
		String firstName = rs.getString(4);
		String lastName = rs.getString(5);
		String ssn = rs.getString(6);
		String address = rs.getString(7);
		String city = rs.getString(8);
		String state = rs.getString(9);
		String zipcode = rs.getString(10);
		String precinct = rs.getString(12);
		String gender = rs.getString(13);
		int age = rs.getInt(14);
		String education = rs.getString(15);
		String securityQuestion1 = rs.getString(16);
		String securityAnswer1 = rs.getString(17);
		String securityQuestion2 = rs.getString(18);
		String securityAnswer2 = rs.getString(19);
		rs.close();
		ps.close();
		con.close();
		
		return new User(id, username, password, adminStatus, email, voterStatus, firstName, lastName, ssn, address, city, state, zipcode, precinct, gender, age, education, securityQuestion1, securityAnswer1, securityQuestion2, securityAnswer2);
		
		} catch (Exception e) {
             System.out.println(e);
         }
		
		return new User(0, "", "", 0, "", 0, "", "", "", "", "", "", "", "", "", 0, "", "", "", "", "");
		
	}
	
	// Updates all fields to match the user object, returns int 1 if successful 0 if not
	public int updateUser(User user) {
		
		int status = 0;
        try {

        	// Create new user entry: username, password, adminStatus
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("UPDATE users SET username = ?, password = ?, adminStatus = ? WHERE id = ?;");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            if(user.getAdminStatus() == AdminStatus.USER) {
            		ps.setInt(3, 0);
            }
            else if (user.getAdminStatus() == AdminStatus.MANAGER) {
            		ps.setInt(3, 1);
            }
            else { // admin
            		ps.setInt(3, 2);
            }
            ps.setInt(4, user.getID());
            ps.executeUpdate();
            ps.close();
            
            
            ps = con.prepareStatement("UPDATE user_info SET email = ?, voter_status = ?, first_name = ?, last_name = ?, ssn = ?, address = ?, city = ?, state = ?, zipcode = ?, precinct = ?, gender = ?, age = ?, education = ?, securityQuestion1 = ?, securityAnswer1 = ?, securityQuestion2 = ?, securityAnswer2 = ? WHERE id = ?");
        // INSERT INTO user_info (id, email, voter_status, first_name, last_name, ssn, address, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
            
            
            ps.setString(1, user.getEmail());
            if (user.getVoterStatus() == VoterStatus.NOT_APPLIED) {
            		ps.setInt(2, 0);
            }
            else if (user.getVoterStatus() == VoterStatus.APPLIED) {
            		ps.setInt(2, 1);
            }
            else { // Approved
            		ps.setInt(2, 2);
            }
            
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getSSN());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getZipCode());
            ps.setString(10, user.getPrecinct());
            ps.setString(11, user.getGender());
            ps.setInt(12, user.getAge());
            ps.setString(13, user.getEducation());
            ps.setString(14, user.getSecurityQuestion1());
            ps.setString(15, user.getSecurityAnswer1());
            ps.setString(16, user.getSecurityQuestion2());
            ps.setString(17, user.getSecurityAnswer2());
            ps.setInt(18, user.getID());

            ps.executeUpdate();

            status = 1;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            status = 0;
        }
        return status;

	}
	
	public int verifyEmail(String emailAuthKey) {
		int status = 0;
		try {
		 
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from emailtokens where emailAuthKey = ?");
			ps.setString(1, emailAuthKey);
			
			ResultSet rs = ps.executeQuery();
			if(rs.first()) {
				rs.first();
			
				int userID = rs.getInt(2);
				rs.close();
				ps.close();
		
			
				ps = con.prepareStatement("UPDATE user_info SET emailAuth=1 WHERE id=?");

				ps.setInt(1, userID);
				ps.executeUpdate();
         
				status = 1;
				ps.close();
				con.close();
			}

		} catch (Exception e) {
			System.out.println(e);
			status = 0;
		}

		return status;
	}
	
public boolean verifySecurityQuestion1(String email, String theirAnswer) {
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from user_info where email = ?");
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			String dbAnswer = rs.getString(17);
			ps.close();
			con.close();
			rs.close();
			if (dbAnswer != null) {
				if (theirAnswer.equals(dbAnswer) ) {
	    				return true;
	    			}
			}
		} catch (Exception e) {
            System.out.println(e);
        }
		return false;
	
}

public boolean verifySecurityQuestion2(String email, String theirAnswer) {
	try {
		con = MyConnectionProvider.getCon();
		ps = con.prepareStatement("select * from user_info where email = ?");
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		rs.first();
		String dbAnswer = rs.getString(19);
		ps.close();
		con.close();
		rs.close();
		if (dbAnswer != null) {
			if (theirAnswer.equals(dbAnswer) ) {
				return true;
			}
		}
	} catch (Exception e) {
        System.out.println(e);
    }
	return false;

}
	
public int getUserIDWithEmail(String email) {
	try {
		
		con = MyConnectionProvider.getCon();

		ps = con.prepareStatement("select * from user_info where email = ?");
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		rs.first();
		
		int id = -1;
		
		if(rs.first()) {
			id = rs.getInt(1);
			
		}
		
		rs.close();
		ps.close();
		con.close();
		
		return id;
		
		} catch (Exception e) {
             System.out.println(e);
         }
		
		return -1;
}

public int getUserIDByRequestKey(String requestKey) {
	int userID = -1;
	try {
	 
		con = MyConnectionProvider.getCon();
		ps = con.prepareStatement("select * from forgot_password_requests where requestid = ?");
		ps.setString(1, requestKey);
		
		ResultSet rs = ps.executeQuery();
		if(rs.first()) {
			rs.first();
		
			userID = rs.getInt(2);
			rs.close();
			ps.close();
			con.close();
		}

	} catch (Exception e) {
		System.out.println(e);
	}

	return userID;
}
	
}
