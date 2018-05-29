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
import com.Team12.CS5800.VotingApplication.model.*;
import com.Team12.CS5800.VotingApplication.service.PollService;


@Controller
@SessionAttributes("name")
public class PollController {
	
	@Autowired
    PollService service;
	
	@RequestMapping(value="/poll", method = RequestMethod.GET)
	public String showPollPage(ModelAndView model){
        return "poll";
    }
	@RequestMapping(value="/poll", method = RequestMethod.POST)
	public ModelAndView showPollPage(ModelAndView model, @RequestParam String electionName, @RequestParam int dateStarted, @RequestParam int dateEnded, @RequestParam int candidateCount, @RequestParam int onGoing, HttpServletResponse response){
		boolean successful = service.registerElection(electionName, dateStarted, dateEnded, candidateCount, onGoing);
		if (!successful) {
    		model.addObject("errorMessage", "cannot add a poll.");
    		return model;
    	}
		model.setViewName("redirect:/");
		return model;
    
    }

}