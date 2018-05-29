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

import com.Team12.CS5800.VotingApplication.model.SessionGrabber;
import com.Team12.CS5800.VotingApplication.service.RequestPasswordResetService;
import com.Team12.CS5800.VotingApplication.model.UserDAOImpl;



@Controller
@SessionAttributes("name")
public class RequestPasswordResetController {

    @Autowired
    RequestPasswordResetService service;

    @RequestMapping(value="/forgotPasswordRequest", method = RequestMethod.GET)
    public String showLoginPage(ModelAndView model){
        return "forgotPasswordRequest";
    }

    @RequestMapping(value="/forgotPasswordRequest", method = RequestMethod.POST)
    public ModelAndView showHomePage(ModelAndView model, @RequestParam String email, HttpServletResponse response){
    		
    	UserDAOImpl udao = new UserDAOImpl();
    	SessionGrabber sg = new SessionGrabber();
    	
    int id = udao.getUserIDWithEmail(email);
    
    model.addObject("successMessage", "Done");
    
    if (id == -1) { // user doesn't have an account
    		return model;
    } else {
    		String requestID = sg.generateSessionID() + LocalDateTime.now();
    		sg.storePasswordRequest(requestID, id);
    		service.sendRecoveryEmail(email, requestID);
    		return model;
    }
    	
    }

}