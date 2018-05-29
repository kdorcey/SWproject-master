package com.Team12.CS5800.VotingApplication.controller;

import java.util.Map;

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
public class VoteController {
	@Autowired
    PollService service;
	
	@RequestMapping(value="/vote", method = RequestMethod.GET)
	public String showVotePage(ModelAndView model){
        return "vote";
    }
	@RequestMapping(value="/vote", method = RequestMethod.POST)
	public String showVoteDetails(ModelAndView model, @RequestParam int userID, @RequestParam int candidateID, @RequestParam String precinct, HttpServletResponse response){
		boolean successful = service.vote(userID, candidateID, precinct);
		if (!successful) {
    		model.addObject("errorMessage", "cannot vote.");
    		
    	}
		model.setViewName("redirect:/");
		return "successvote";
    }

}