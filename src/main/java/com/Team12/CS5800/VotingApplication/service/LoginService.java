package com.Team12.CS5800.VotingApplication.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.Team12.CS5800.VotingApplication.model.User;
import com.Team12.CS5800.VotingApplication.model.UserDAOImpl;


@Service
public class LoginService {
	
	private UserDAOImpl UDAO = new UserDAOImpl();
	
	public boolean validateUser(String username, String password) {
		return UDAO.validateUser(username, password);
	}
	
	public User getUser(String username) {
		return UDAO.getUserWithUsername(username);
	}


}
