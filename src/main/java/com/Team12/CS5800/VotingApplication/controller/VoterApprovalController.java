package com.Team12.CS5800.VotingApplication.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.Team12.CS5800.VotingApplication.service.VoterApprovalService;

@Controller
@SessionAttributes("name")
public class VoterApprovalController {
	
	@Autowired
	VoterApprovalService service;
	
	@RequestMapping(value="/voterApproval", method = RequestMethod.GET)
	public String showLogoutPage(ModelAndView model) {
		return "voterApproval";
	}
	
	@RequestMapping(value="/voterApproval", method = RequestMethod.POST)
	public ModelAndView processRequest(ModelAndView model, @RequestParam int userID, @RequestParam String command, @RequestParam String sessionID, HttpServletRequest request) {
		
		if(service.verifyAccount(sessionID)) {
			
			if(command.equals("approve")) {
				service.approveUser(userID);
			}
			else if (command.equals("decline")){
				service.denyUser(userID);
			}
			
		}
		else {
			model.addObject("errorMessage", "You don't have the rights to access this page.");
	        return model;
		}
				
		
		return model;
	}
	
}