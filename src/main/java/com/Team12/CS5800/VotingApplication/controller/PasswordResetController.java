package com.Team12.CS5800.VotingApplication.controller;

import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.Team12.CS5800.VotingApplication.model.SessionGrabber;
import com.Team12.CS5800.VotingApplication.model.TestLoginFunctions;
import com.Team12.CS5800.VotingApplication.model.User;
import com.Team12.CS5800.VotingApplication.model.UserDAOImpl;
import com.Team12.CS5800.VotingApplication.service.RequestPasswordResetService;



@Controller
@SessionAttributes("name")
public class PasswordResetController {

    @Autowired
    RequestPasswordResetService service;

    @RequestMapping(value="/forgotPassword", method = RequestMethod.GET)
    public String showLoginPage(ModelAndView model){
        return "forgotPassword";
    }

    @RequestMapping(value="/forgotPassword", method = RequestMethod.POST)
    public ModelAndView showHomePage(ModelAndView model, @RequestParam String request, @RequestParam String answer1, @RequestParam String answer2, @RequestParam String newPassword, HttpServletResponse response){
    		UserDAOImpl udao = new UserDAOImpl();
    		int userID = udao.getUserIDByRequestKey(request);
    		
    		if(userID == -1) { // not found
    			model.addObject("errorMessage", "There is no password request matching your key.");
    			return model;
    		}
    		else {
    			User currentUser = udao.getUserWithID(userID);
    			String email = currentUser.getEmail();
    			
    			System.out.println(userID);
    			System.out.println(email);
    			
    			if (service.validateQuestions(email, answer1, answer2)) { // they were right
    				
    				if (TestLoginFunctions.passwordRequirementCheck(newPassword) && !newPassword.equals("")) {
    					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        	    			String hashedPassword = passwordEncoder.encode(newPassword);
        	    			currentUser.setPassword(hashedPassword);
        	    			SessionGrabber sg = new SessionGrabber();
        	    			sg.removePasswordRequest(request);
        	    			model.addObject("successMessage", "Finished");
        	    			return model;
    				}
    				else { // Password didn't meet our criteria
    					model.addObject("errorMessage", "Your password must contain at least one uppercase character and at least one special character and cannot contain spaces.");
    					return model;
    				}
    				
    			} else { // they guessed the questions wrong
    				model.addObject("errorMessage", "Your answers to at least one of the questions didn't match what we have stored. Remember the answers are case sensitive!");
    				return model;
    			}
    		}
    		 
    }

}