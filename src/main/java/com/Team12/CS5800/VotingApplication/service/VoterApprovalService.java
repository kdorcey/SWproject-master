package com.Team12.CS5800.VotingApplication.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Team12.CS5800.VotingApplication.model.AdminStatus;
import com.Team12.CS5800.VotingApplication.model.User;
import com.Team12.CS5800.VotingApplication.model.UserDAOImpl;
import com.Team12.CS5800.VotingApplication.model.VoterStatus;


@Service
public class VoterApprovalService {
	
	public void approveUser(int id) {
		UserDAOImpl UDAO = new UserDAOImpl();
		User currentUser = UDAO.getUserWithID(id);
		currentUser.setVoterStatus(VoterStatus.APPROVED);
		
	}
	
	public void denyUser(int id) { 
		UserDAOImpl UDAO = new UserDAOImpl();
		User currentUser = UDAO.getUserWithID(id);
		currentUser.setVoterStatus(VoterStatus.NOT_APPLIED);
	}
	
	public boolean verifyAccount(String sessionCode) {
		UserDAOImpl UDAO = new UserDAOImpl();
		User currentUser = UDAO.getUser(sessionCode);
		
		if(currentUser.getAdminStatus() == AdminStatus.MANAGER || currentUser.getAdminStatus() == AdminStatus.ADMIN ) {
			return true;
		}
		return false;
	}

}
