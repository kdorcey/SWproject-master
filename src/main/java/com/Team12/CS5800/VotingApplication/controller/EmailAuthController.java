package com.Team12.CS5800.VotingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("name")
public class EmailAuthController {
	
	@RequestMapping(value="/emailAuth", method = RequestMethod.GET)
	public String showLogoutPage(ModelAndView model,
			@CookieValue(value = "sessionID", defaultValue = "") String sessionID) 
	{
		return "emailAuth";
	}

}