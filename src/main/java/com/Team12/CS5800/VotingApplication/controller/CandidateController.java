package com.Team12.CS5800.VotingApplication.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.Team12.CS5800.VotingApplication.service.PollService;

@Controller
@SessionAttributes("name")
public class CandidateController {
	
	@Autowired
    PollService service;
	
	@RequestMapping(value="/candidate", method = RequestMethod.GET)
	public String showPollPage(ModelAndView model){
        return "candidate";
    }
	@RequestMapping(value="/candidate", method = RequestMethod.POST)
	public ModelAndView showCandidatePage(ModelAndView model, @RequestParam String candidateName, @RequestParam int electionID, @RequestParam String party, HttpServletResponse response){
		boolean successful = service.addCandidate(candidateName, electionID, party);
		if (!successful) {
    		model.addObject("errorMessage", "cannot add the candidate.");
    		return model;
    	}
		model.setViewName("redirect:/");
		return model;
    
    }

}