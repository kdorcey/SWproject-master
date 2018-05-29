package com.Team12.CS5800.VotingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("name")
public class HomeController {

	@RequestMapping(value="/home", method = RequestMethod.GET)
    public String showWelcomePage(ModelAndView model){
        return "home";
    }
	
}
