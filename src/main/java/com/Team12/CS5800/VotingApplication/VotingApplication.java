package com.Team12.CS5800.VotingApplication;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.Team12.CS5800.VotingApplication.model.Candidate;
import com.Team12.CS5800.VotingApplication.model.County;
import com.Team12.CS5800.VotingApplication.model.VotingAndCandidateDAOImpl;

@SpringBootApplication
@ComponentScan("com.Team12.CS5800.VotingApplication")
public class VotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
		
	}
}