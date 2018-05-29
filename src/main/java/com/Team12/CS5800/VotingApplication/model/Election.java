package com.Team12.CS5800.VotingApplication.model;

import java.util.ArrayList;

public class Election {
	private int electionID;
	private String electionName;
	private int dateStarted;
	private int dateEnded;
	private Candidate winner;
	private ArrayList<Candidate> candidateList;
	private int numberOfCandidates;
	
	public Election(int electionID, String electionName, int dateStarted, int dateEnded, int numberOfCandidates, boolean getCandidates) {
		this.electionID = electionID;
		this.electionName = electionName;
		this.dateStarted = dateStarted;
		this.dateEnded = dateEnded;
		this.numberOfCandidates = numberOfCandidates;
		if(getCandidates == true) {
			this.candidateList = fillCandidateList(electionID);
		}
	}
	public Election(int electionID, String electionName, int dateStarted, int dateEnded, int numberOfCandidates) {
		this.electionID = electionID;
		this.electionName = electionName;
		this.dateStarted = dateStarted;
		this.dateEnded = dateEnded;
		this.numberOfCandidates = numberOfCandidates;
		this.candidateList = fillCandidateList(electionID);
	}
	public Election( String electionName, int dateStarted, int dateEnded, int numberOfCandidates) {
		this.electionName = electionName;
		this.dateStarted = dateStarted;
		this.dateEnded = dateEnded;
		this.numberOfCandidates = numberOfCandidates;
		this.candidateList = fillCandidateList(electionID);
	}
	
	
	private static ArrayList<Candidate> fillCandidateList(int electionID) {
		ArrayList<Candidate> candidateList = new ArrayList<>();
		
		return candidateList;
	}
	public void addElectionID(int electionID) {
		this.electionID = electionID;
	}
	
	public int getElectionID() {
		return electionID;
	}
	
	public String getElectionName() {
		return electionName;
	}
	
	public boolean sameCheck(Election toCheck) {
		boolean match = false;
		
		if(this.electionID == toCheck.electionID) {
			if(this.electionName.equals(toCheck.electionName)) {
				if(this.dateStarted == toCheck.dateStarted) {
					if(this.dateEnded == toCheck.dateEnded) {
						if(this.numberOfCandidates == toCheck.numberOfCandidates) {
							match=true;
						}
					}
				}
			}
		}
		
		return match;
	}
	

}
