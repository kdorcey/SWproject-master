package com.Team12.CS5800.VotingApplication.controller;

import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.Team12.CS5800.VotingApplication.model.EmailAuthGrabber;
import com.Team12.CS5800.VotingApplication.model.GetPrecinct;
import com.Team12.CS5800.VotingApplication.model.SessionGrabber;
import com.Team12.CS5800.VotingApplication.model.TestLoginFunctions;
import com.Team12.CS5800.VotingApplication.service.RegisterService;

@Controller
@SessionAttributes("name")
public class RegisterController {
	
	@Autowired
    RegisterService service;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegisterPage(ModelAndView model){
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView showWelcomePage(ModelAndView model, @RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String ssn, @RequestParam String address, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String city, @RequestParam String state, @RequestParam String zipcode, @RequestParam String email, @RequestParam String gender, @RequestParam int age, @RequestParam String education, @RequestParam String securityQuestion1, @RequestParam String securityQuestion1Answer, @RequestParam String securityQuestion2, @RequestParam String securityQuestion2Answer, HttpServletResponse response){

    	if (!password.equals(password2)) {
    		model.addObject("errorMessage", "Passwords must match.");
    		return model;
    	}
    	
    	if (!TestLoginFunctions.passwordRequirementCheck(password)) {
    		model.addObject("errorMessage", "Your password must contain at least one uppercase character and at least one special character and cannot contain spaces.");
    		return model;
    	}
    	
    	if (!TestLoginFunctions.usernameRequirementCheck(username)) {
    		model.addObject("errorMessage", "Your username must contain three characters and cannot consist of any special characters or spaces.");
    		return model;
    	}
    	
    	if (!TestLoginFunctions.socialSecurityNumberCheck(ssn)) {
    		model.addObject("errorMessage", "Please input your social security number in the following format XXXXXXXXX. A social security number should be 9 digits in a row.");
    		return model;
    	}
    	
    	String precinct = "";
    	
    	try {
    		precinct = GetPrecinct.precinctLookup(address, city, state);
    	} catch (Exception e) {
    		model.addObject("errorMessage", "We can't find your given address. Please enter a valid address, city, and state combination.");
    		return model;
    	}
    	
    	
    	boolean successful = service.registerUser(username, password, email, first_name, last_name, ssn, address, city, state, zipcode, precinct, gender, age, education, securityQuestion1, securityQuestion1Answer, securityQuestion2, securityQuestion2Answer);

    	int userID;
    	if (!successful) {
    		model.addObject("errorMessage", "That username is not available.");
    		return model;
    	}
    	else {
    		userID = service.getUser(username).getID();
    	}
    	
    	model.setViewName("redirect:/");
    	
    	SessionGrabber sg = new SessionGrabber();
    	
    	String cookieToAdd = sg.generateSessionID() + LocalDateTime.now();
    	sg.storeSession(cookieToAdd, userID);
    	
    	response.addCookie(new Cookie("sessionID", cookieToAdd));
    	
    	EmailAuthGrabber eag = new EmailAuthGrabber();
    	String emailAuthKey = eag.generateEmailAuthID() + email;
    	eag.storeEmailAuthKey(emailAuthKey, userID);
    	
    
    	
    	TestLoginFunctions.sendEmailVerification(email, username, emailAuthKey);

    return model;
    
    }

}
