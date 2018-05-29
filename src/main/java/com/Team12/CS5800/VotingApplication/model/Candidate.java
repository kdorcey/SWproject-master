package com.Team12.CS5800.VotingApplication.model;

public class Candidate {
	
	private int candidateID;
	private String candidateName;
	private int electionID; 
	private boolean isWinner;
	private int votesRecieved;
	private String party;
	
	public Candidate(int candidateID, String candidateName, int electionID, int votesRecieved, String party) {
		this.candidateID = candidateID;
		this.candidateName = candidateName;
		this.electionID = electionID;
		this.votesRecieved = votesRecieved;
		this.party = party;
	}
	public Candidate(String candidateName, int electionID, int votesRecieved, String party) {
		this.candidateName = candidateName;
		this.electionID = electionID;
		this.votesRecieved = votesRecieved;
		this.party = party;
	}
	public Candidate(int candidateID, String candidateName, int electionID, boolean isWinner, int votesRecieved, String party) {
		this.isWinner = isWinner;
		this.candidateID = candidateID;
		this.candidateName = candidateName;
		this.electionID = electionID;
		this.votesRecieved = votesRecieved;
		this.party = party;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public int electionID() {
		return electionID;
	}

	

	public boolean isWinner() {
		return isWinner;
	}

	public int getVotesRecieved() {
		return votesRecieved;
	}


	public String getParty() {
		return party;
	}
	
	@Override
	public boolean equals(Object toCheck) {
		Candidate candidateToCheck = (Candidate)toCheck;
		
		boolean match = false;
		if(this.candidateName.equals(candidateToCheck.candidateName)) {
				if(this.electionID == candidateToCheck.electionID) {
						if(this.votesRecieved == candidateToCheck.votesRecieved) {
							if(this.party.equals(candidateToCheck.party)) {
								match = true;
							}
						}
					}
				}
			
		
		return match;
	}


	
	
	

}
