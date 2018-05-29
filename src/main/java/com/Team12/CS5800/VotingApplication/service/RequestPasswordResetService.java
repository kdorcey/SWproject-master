package com.Team12.CS5800.VotingApplication.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.Team12.CS5800.VotingApplication.model.User;
import com.Team12.CS5800.VotingApplication.model.UserDAOImpl;
import com.Team12.CS5800.VotingApplication.model.TestLoginFunctions;


@Service
public class RequestPasswordResetService {
	
	private UserDAOImpl UDAO = new UserDAOImpl();
	
	public boolean validateQuestions(String email, String answer1, String answer2) {
		return UDAO.verifySecurityQuestion1(email, answer1) && UDAO.verifySecurityQuestion2(email, answer2);
	}
	
	public void sendRecoveryEmail(String email, String requestCode) {
		TestLoginFunctions.sendPasswordRecoveryEmail(email, requestCode);
	}
	


}
