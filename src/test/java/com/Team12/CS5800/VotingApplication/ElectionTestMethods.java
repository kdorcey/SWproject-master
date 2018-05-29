//package com.Team12.CS5800.VotingApplication;

//import java.util.ArrayList;

//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

//import com.Team12.CS5800.VotingApplication.model.Candidate;
//import com.Team12.CS5800.VotingApplication.model.Election;
//import com.Team12.CS5800.VotingApplication.model.VotingAndCandidateDAO;
//import com.Team12.CS5800.VotingApplication.model.VotingAndCandidateDAOImpl;


//public class ElectionTestMethods {
	//public static void main(String[] args) {	
		
		/***
		 * You need to initialize this object to use any of the election/candidate/voting 
		 * logic
		 */
		//VotingAndCandidateDAOImpl DAO = new VotingAndCandidateDAOImpl();
		
		/*
		 * to create a new election call this method
		 * 
		 * returns either -1, 1, or 0. 
		 * -1 means something really wrong happened. You will hopefully never
		 * see this output. 1 means that it successfully wrote to the database
		 * 0 means that an exception of some kind was thrown and it didn't write to the database
		 * 
		 * election name: written name of the election
		 * date started: date election starts
		 * date ended: date election ends
		 * number of candidates: number of candidates who will be running
		 * onGoin: boolean saying whether or not the election is currently happening
		 * (this isn't permanent. I plan on adding something to auto detect this
		 */
		 
		//DAO.createElection(electionName, dateStarted, dateEnded, numberOfCandidates, onGoing);
		 
		 
		 
		 /***
		  * Use this function to retrieve an array list of all candidates in a specified election
		  * (see the Candidate class for what information and Candiate object holds)
		  * 
		  * electionID: unique ID from the election/candidate_info tables
		  */
		
		//ArrayList<Candidate> ListOfCandidates = DAO.getElectionCandidates(electionID);
		 
		 /***
		  * use to retrieve a specific election based on election ID
		  * 
		  * electionID: same as above
		  */
		 
		//Election election = DAO.getElection(electionID);
		  
		  /***
		   * Function identically to ".getElection(electionID), but instead of taking
		   * an int as its argument it takes the election name as a string 
		   * (something like: "2016 Presidential election)
		   * 
		   * electionName: String containing the written name of a specified election
		   */
		 
		//Election electionWithName = DAO.getElectionWithName(electionName);
		 
		
		/***
		 * Call this method to allow the user to cast a vote for a specific candidate.
		 * As "candidateID" is unique based on election, you don't have to worry about 
		 * specifying what election a user is voting in.
		 * 
		 *  returns: boolean. Returns true if the user's vote was cast, returns
		 *  false if the user is not allowed to vote. If the user is not allowed to vote
		 *  it is either because their userID didn't exist or because he has already voted
		 *  
		 *  userID: uniqueID of the user who is casting the vote
		 *  candidateId: uniqueID of the candidate a user is voting for
		 * 
		 */
		 //DAO.castVote(userID, candidateID);
		
		/***
		 * Call this to add candidates to an election. (Used when an admin is creating
		 * a new election)
		 * 
		 * returns boolean: true if candidates are successfully added to database, false
		 * if something goes wrong and an exception is thrown
		 * 
		 * candidateListToAdd: an ArrayList of candidates that an Admin would like to add to an election
		 * electionID: unique ID of the election candidates are being added to
		 */
		//DAO.addCandidatesToElection(ArrayList<Candidate> candidateListToAdd, electionID);
		
		/***
		 * ~~VERY IMPORTANT~~ THIS METHOD ONLY ALLOWS ADMINS TO ADD PRECINCTS
		 * THAT USERS ARE ALREADY REGISTERED UNDER.
		 * 
		 * 
		 * Use this method to allow a specific precinct to vote on an election
		 * 
		 * This method will parse through the "user_info" table to retrieve the userID
		 * of any user in the specified precinct "precinctName". It will then add
		 * these users to the "ongoing_election_voters" table
		 * 
		 * returns boolean: true when everything is read and published to the database
		 * successfully. False when something goes wrong and an exception is caught.
		 * This is almost always because the precinct that the Admin is trying to add
		 * is not available
		 * 
		 * electionID: uniqueID of the election the admin wants to add precincts to.
		 * precinct: String containing the EXACT WRITTEN NAME AS APPEARS IN THE DATABASE
		 * of the precinct an admin would like to add
		 */
		  //DAO.includePrecinct(int electionID, String precinctName);

		/***
		 * Use to get a list of every county in iowa
		 * 
		 * returns: ArrayList<County> which holds a county object of every county in iowa
		 * 
		 * 
		 */
		//ArrayList<County> countyHolder = DAO.getCountyList();

		/**
		 * Use to update a counties congressional district
		 * 
		 * returns: true if database updated properly, otherwise false
		 * 
		 * countyID: Unique ID of the county you wish to update
		 * 
		 * newCongressionalDistrict: int of the congressional district you 
		 * wish to change the county to 
		 */
		//DAO.updateCountyCongressionalDistrict(int countyID, int newCongressionalDistrict);
		
//	}
//}
