package com.Team12.CS5800.VotingApplication.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Team12.CS5800.VotingApplication.model.User;
import com.Team12.CS5800.VotingApplication.model.UserDAOImpl;

@Service
public class RegisterService {
	
	private UserDAOImpl UDAO = new UserDAOImpl();
	
	public boolean registerUser(String username, String password, String email, String first_name, String last_name, String socialSecurity, String address, String city, String state, String zipcode, String precinct, String gender, int age, String education, String securityQuestion1, String securityAnswer1, String securityQuestion2, String securityAnswer2) {
		int voterStatus = 0;
		int adminStatus = 0;
		
		int status = UDAO.insertUser(username, password, adminStatus, email, voterStatus, first_name, last_name, socialSecurity, address, city, state, zipcode, precinct, gender, age, education, securityQuestion1, securityAnswer1, securityQuestion2, securityAnswer2);

		if (status == 0) {
			return false;
		}
		else {
			return true;
		}

    }
	
   public User getUser(String username) {
	   return UDAO.getUserWithUsername(username);
   }

}
