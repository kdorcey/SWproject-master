package com.Team12.CS5800.VotingApplication.model;

import java.util.ArrayList;

public interface VotingAndCandidateDAO {
	//use to create a new election
	//test case done
	public int createElection(String electionName, int dateStarted, int dateEnded, int candidateCount, int onGoing);
	
	//retrieves election from database given the parameter
	//test case done
	public Election getElection(int electionID);
	public Election getElectionWithName(String electionName);
	
	//retrieves candidates who ran in a specified election
	//test case done
	public ArrayList<Candidate> getElectionCandidates(int electionID);
	
	//adds candidate to the running of a specific election
	//test case done
	public boolean addCandidatesToElection(ArrayList<Candidate> candidateListToAdd, int electionID);
	
	//adds one candidate to the running of a specific election
	//test case done
	public boolean addOneCandidateToElection(String candidateName, int electionID, String party);
		
	//casts a vote for a candidate from a user
	//test case done
	public boolean castVote(int userID, int candidateID);
	public boolean Vote(int userID, int candidateID,String precinct);
	
	//flags all users from a specified precinct to allow them to vote in a specified election
	public boolean includePrecinct(int electionID, String precinctName); 
	
	//retrieves list of all counties in Iowa
	//test case done
	public ArrayList<County> getCountyList();
	
	//changes the counties congressional district 
	public boolean updateCountyCongressionalDistrict(int countyID, int newCongressionalDistrict);
	
	public ArrayList<Candidate> getElectionResultsByPrecinct(String precinctName, int electionID);
	
	
}
