package com.Team12.CS5800.VotingApplication.controller;

import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.Team12.CS5800.VotingApplication.model.AdminStatus;
import com.Team12.CS5800.VotingApplication.model.EmailAuthGrabber;
import com.Team12.CS5800.VotingApplication.model.GetPrecinct;
import com.Team12.CS5800.VotingApplication.model.SessionGrabber;
import com.Team12.CS5800.VotingApplication.model.TestLoginFunctions;
import com.Team12.CS5800.VotingApplication.model.User;
import com.Team12.CS5800.VotingApplication.model.VoterStatus;
import com.Team12.CS5800.VotingApplication.service.LoginService;
import com.Team12.CS5800.VotingApplication.service.RegisterService;

@Controller
@SessionAttributes("name")
public class ProfileController {
	
	@Autowired
    RegisterService service;
	
	@Autowired
	LoginService loginService;

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String showRegisterPage(ModelAndView model){
        return "profile";
    }

    @RequestMapping(value="/profile", method = RequestMethod.POST)
    public ModelAndView showWelcomePage(ModelAndView model, @RequestParam String username, @RequestParam String password, @RequestParam String newpassword, @RequestParam String ssn, @RequestParam String address, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String city, @RequestParam String state, @RequestParam String zipcode, @RequestParam String email, @RequestParam String gender, @RequestParam int age, @RequestParam String education, @RequestParam String securityQuestion1, @RequestParam String securityQuestion1Answer, @RequestParam String securityQuestion2, @RequestParam String securityQuestion2Answer,HttpServletResponse response){

    	boolean isValidLogin = loginService.validateUser(username, password);
    	
	if (!isValidLogin) {
			model.addObject("errorMessage", "Your password was incorrect -- we can't process these changes.");
			return model;
	} 
    	
	
    	if (!newpassword.equals("") && !TestLoginFunctions.passwordRequirementCheck(newpassword)) {
    		model.addObject("errorMessage", "Your new password must contain at least one uppercase character and at least one special character and cannot contain spaces.");
    		return model;
    	}
    	
    	if (!TestLoginFunctions.socialSecurityNumberCheck(ssn)) {
    		model.addObject("errorMessage", "Please input your social security number in the following format XXXXXXXXX. A social security number should be 9 digits in a row.");
    		return model;
    	}
    	
    	User thisUser = loginService.getUser(username);
    	
    	String precinct = "";
    	if(!city.equals(thisUser.getCity()) || !address.equals(thisUser.getAddress()) || !zipcode.equals(thisUser.getZipCode())) {
    		try {
        		precinct = GetPrecinct.precinctLookup(address, city, state);
        	} catch (Exception e) {
        		model.addObject("errorMessage", "We can't find your given address. Please enter a valid address, city, and state combination.");
        		return model;
        	}
    	}

    	else { // if I didn't change anything, I can use my old precinct
    		precinct = thisUser.getPrecinct();
    	}
    	
    	
    
    	
    
    	
    	if(!newpassword.equals("")) {
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    		String hashedPassword = passwordEncoder.encode(newpassword);
    		thisUser.setPassword(hashedPassword);
    	}
    	
    	if(!precinct.equals(thisUser.getPrecinct()) && thisUser.getAdminStatus() == AdminStatus.USER) {
    		thisUser.setVoterStatus(VoterStatus.NOT_APPLIED);
    	}
    	
    	thisUser.setZipCode(zipcode);
    	thisUser.setState(state);
    	thisUser.setSSN(ssn);
    	thisUser.setPrecinct(precinct);
    	thisUser.setLastName(last_name);
    	thisUser.setFirstName(first_name);
    	thisUser.setCity(city);
    	thisUser.setAddress(address);
    	thisUser.setGender(gender);
    	thisUser.setAge(age);
    	thisUser.setEducation(education);
    	thisUser.setSecurityAnswer1(securityQuestion1Answer);
    	thisUser.setSecurityAnswer2(securityQuestion2Answer);
    	thisUser.setSecurityQuestion1(securityQuestion1);
    	thisUser.setSecurityQuestion2(securityQuestion2);
    	
    	model.addObject("successMessage", "Your information has been updated!");

    return model;
    
    }

}
