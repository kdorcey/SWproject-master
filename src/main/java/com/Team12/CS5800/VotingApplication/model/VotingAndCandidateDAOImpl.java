package com.Team12.CS5800.VotingApplication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.Team12.CS5800.VotingApplication.model.DataConnection.MyConnectionProvider;

public class VotingAndCandidateDAOImpl implements VotingAndCandidateDAO {
	static Connection con;
	static PreparedStatement ps;
	private UserDAOImpl UDAO = new UserDAOImpl();
	
	
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
	public int createElection(String electionName, int dateStarted, int dateEnded, int numberOfCandidates, int onGoing) {
		int status = -1;
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("insert into elections (electionName, electionStartDate, electionEndDate, numberOfCandidates, onGoing)values(?,?,?,?,?)");
			ps.setString(1, electionName);
			ps.setInt(2, dateStarted);
			ps.setInt(3, dateEnded);
			ps.setInt(4, numberOfCandidates);
			ps.setInt(5, onGoing);
			ps.executeUpdate();
			ps.close();
			
			status = 1;
		
		}catch(Exception e) {
			System.out.println(e);
			status = 0;
		}
		
		return status;
	}

	
	public ArrayList<Candidate> getElectionCandidates(int electionID) {
		
		ArrayList<Candidate> candidateHolder = new ArrayList<Candidate>();
		
		try {
			int candidateID;
			String candidateName = new String();
			int winnerStatusInt;
			boolean winnerStatus;
			int votesRecieved;
			String party;
			
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from candidate_info where electionID = ?");
			ps.setInt(1, electionID);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			boolean lastRowCheck = rs.isLast();
			boolean atLastRowFlag = lastRowCheck;
			while(!lastRowCheck){
				
				candidateID = rs.getInt(1);
				candidateName = rs.getString(2);
				party = rs.getString(4);
				winnerStatusInt = rs.getInt(5);
				votesRecieved = rs.getInt(6);
				
				
				if(winnerStatusInt ==1) {
					winnerStatus = true;
				}
				else {
					winnerStatus = false;
				}
				
				candidateHolder.add(new Candidate(candidateID,candidateName, electionID, winnerStatus, votesRecieved, party)); //name, electionID, winnerStatus, votesRecieved, party
				rs.next();
				
				if(atLastRowFlag == true) {
					lastRowCheck = true;
				}
				
				if(rs.isLast() ==true) {
					atLastRowFlag = true;
				}
				
			}
			
			
			rs.close();
			ps.close();
			con.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return candidateHolder;
	}


	@Override
	public Election getElection(int electionID) {
		
		try {
			String electionName = new String();
			int startDate;
			int endDate;
			int numberOfCandidates;
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from elections where electionID = ?");
			ps.setInt(1, electionID);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			electionName = rs.getString(2);
			startDate = rs.getInt(3);
			endDate = rs.getInt(4);
			numberOfCandidates = rs.getInt(5);
			
			rs.close();
			ps.close();
			con.close();
			
			
			Election results = new Election(electionID, electionName, startDate, endDate, numberOfCandidates);
			return results;
			
		} catch(Exception e) {
			System.out.println(e);
			Election results = new Election(1, "Test", 1,2,3);
			return results;
		}
		
	}
	
	public Election getElectionWithName(String electionName) {
		try {
			int electionID;
			int startDate;
			int endDate;
			int numberOfCandidates;
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from elections where electionName = ?");
			ps.setString(1, electionName);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			electionID = rs.getInt(1);
			startDate = rs.getInt(3);
			endDate = rs.getInt(4);
			numberOfCandidates = rs.getInt(5);
			
			rs.close();
			ps.close();
			con.close();
			
			
			Election results = new Election(electionID, electionName, startDate, endDate, numberOfCandidates);
			return results;
			
		} catch(Exception e) {
			System.out.println(e);
			Election results = new Election(1, "Test", 1,2,3);
			return results;
		}
	}
	
	
	public boolean castVote(int userID, int candidateID) {
		boolean voteSuccessfullyCast = true;
		
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from candidate_info where candidateID = ?");
			ps.setInt(1, candidateID);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			int candidateElectionID = rs.getInt(3);
			
			//checks that user has not already voted
			if(canUserVote(userID, candidateElectionID)){
				//increments vote count
				con = MyConnectionProvider.getCon();
				
				ps = con.prepareStatement("UPDATE candidate_info SET votes = votes + 1 WHERE candidateID = ?");
				ps.setInt(1, candidateID);
				ps.executeUpdate();
				System.out.println("here");
				//adds user ID to paper trail to track users who have voted for each candidate to verify voting results (sorry Russia)
				ps = con.prepareStatement("INSERT INTO paper_trail (candidateID, voterID) VALUES ( ?, ?)");
				ps.setInt(1, candidateID);
				ps.setInt(2, userID);
				ps.executeUpdate();
				
				
				//adds user ID to election_voter_data to assure that the user can only vote once per election
				ps = con.prepareStatement("INSERT INTO election_voter_data (electionID, voterID) VALUES ( ?, ?)");
				ps.setInt(1, candidateElectionID);
				ps.setInt(2, userID);
				ps.executeUpdate();
				
				
				rs.close();
				ps.close();
				
				voteSuccessfullyCast = true;
			}
			else {
				voteSuccessfullyCast = false;
				rs.close();
				ps.close();
			}
			
		}catch(Exception e) {
			voteSuccessfullyCast = false;
			System.out.println(e);
		}
		
		
		return voteSuccessfullyCast;
	}
	public boolean Vote(int userID, int candidateID, String precinct) {
		boolean voteSuccessfullyCast = true;
		
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from candidate_info where candidateID = ?");
			ps.setInt(1, candidateID);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			
			
			
				//increments vote count
				con = MyConnectionProvider.getCon();
				
				ps = con.prepareStatement("UPDATE candidate_info SET votes = votes + 1 WHERE candidateID = ?");
				ps.setInt(1, candidateID);
				ps.executeUpdate();
				System.out.println("here");
				//adds user ID to paper trail to track users who have voted for each candidate to verify voting results (sorry Russia)
				
				ps = con.prepareStatement("INSERT INTO paper_trail (candidateID, voterID,electionID,precinct) VALUES ( ?, ?, ?,?)");
				ps.setInt(1, candidateID);
				ps.setInt(2, userID);
				ps.setInt(3, 1);
				ps.setString(4, precinct);
				ps.executeUpdate();
				
			
				
				
				rs.close();
				ps.close();
				
				voteSuccessfullyCast = true;
		
		}catch(Exception e) {
			voteSuccessfullyCast = false;
			System.out.println(e);
		}
		
		
		return voteSuccessfullyCast;
	}
	//checks that the user hasn't already voted. Returns true if the user has not. False if they have.
	private boolean canUserVote(int userID, int electionID) {
		boolean voterStatusCheck = true;
		
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from election_voter_data where electionID = ?");
			ps.setInt(1, electionID);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			boolean lastRowFlag = rs.isLast();
			boolean exitLoop = lastRowFlag;
			
			while(!exitLoop) {
				int userIDToCheck = rs.getInt(3);
				
				
				if(userIDToCheck == userID) {
					voterStatusCheck = false;
					break;
				}
				
				rs.next();
				
				if(lastRowFlag) {
					exitLoop = true;
				}
				if(rs.isLast()) {
					lastRowFlag = true;
				}
				
			}
			rs.close();
			ps.close();
			con.close();
			
		}catch(Exception e) {
			System.out.println(e);
			voterStatusCheck = false;
		}

		
		return voterStatusCheck;
	}
	
	public boolean addCandidatesToElection(ArrayList<Candidate> candidateListToAdd, int electionID) {
		boolean successfullyAdded = false;
		
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("INSERT INTO candidate_info (candidateName, electionID, party, votes) VALUES (?,?,?,?)");

	
			for(Candidate toAdd:candidateListToAdd){
				ps.setString(1, toAdd.getCandidateName());
				ps.setInt(2, electionID);
				ps.setString(3, toAdd.getParty());
				ps.setInt(4, toAdd.getVotesRecieved());
				ps.executeUpdate();
			}
	
			ps.close();
			successfullyAdded = true;
		}catch(Exception e) {
			System.out.println(e);
			successfullyAdded = false;
		}
			
		return successfullyAdded;
	}
	
	public boolean addOneCandidateToElection(String candidateName, int electionID, String party) {
		boolean successfullyAdded = false;
	
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("INSERT INTO candidate_info (candidateName, electionID, party, isWinner, votes) VALUES (?,?,?,?,?)");
			ps.setString(1, candidateName);
			ps.setInt(2, electionID);
			ps.setString(3, party);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.executeUpdate();
			ps.close();
			
		}catch(Exception e) {
			System.out.println(e);
			successfullyAdded = false;
		}		
		return successfullyAdded;
	}
	
	public boolean includePrecinct(int electionID, String precinctName) { //used to flag users from specific precincts for elections
		boolean flagCompleteStatus = false;
		
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("SELECT id from user_info WHERE precinct = ?");
			ps.setString(1, precinctName);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			boolean lastRowCheck = rs.isLast();
			boolean atLastRowFlag = lastRowCheck;
			ArrayList<Integer> userIDToAdd = new ArrayList<Integer>();
			while (!lastRowCheck){
				
				int userID = rs.getInt(1);
				userIDToAdd.add(userID);
				
				
				rs.next();
				if(atLastRowFlag == true) {
					lastRowCheck = true;
				}
				
				if(rs.isLast() ==true) {
					atLastRowFlag = true;
				}
			}
			
			
			
			
			for(int idToAdd:userIDToAdd) {
				if(onGoingElectionVoterCheck(electionID, idToAdd)) {
					ps = con.prepareStatement("INSERT INTO ongoing_election_voters (electionID, userID) VALUES (?,?)");
					ps.setInt(1, electionID);
					ps.setInt(2, idToAdd);
					ps.executeUpdate();
				}
			}
			rs.close();
			ps.close();
			
		}catch(Exception e) {
			System.out.println(e);
			flagCompleteStatus = false;
		}
		
		
		return flagCompleteStatus;
		
	}
	
	
	private boolean onGoingElectionVoterCheck(int electionID, int userID) {
		boolean addUserStatus = true;
		//checks that a user isn't already flagged to vote in a specific election
		try {
			ps = con.prepareStatement("select * from ongoing_election_voters where userID = ?");
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			boolean lastRowCheck = rs.isLast();
			boolean atLastRowFlag = lastRowCheck;
			while (!lastRowCheck){
				
				int electionIDToCheck = rs.getInt(2);
				
				if(electionIDToCheck == electionID) {
					addUserStatus = false;
					break;
				}
				
				
				rs.next();
				if(atLastRowFlag == true) {
					lastRowCheck = true;
				}
				
				if(rs.isLast() ==true) {
					atLastRowFlag = true;
				}
			}
			
			rs.close();
			ps.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		return addUserStatus;
	}
	
	public ArrayList<County> getCountyList(){
		
		ArrayList<County> listOfCounties = new ArrayList<County>();
		
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("select * from counties");
			
			ResultSet rs= ps.executeQuery();
			rs.first();
			System.out.println(rs.first());
			
			boolean lastRowCheck = rs.isLast();
			System.out.println(rs.isLast());
			boolean atLastRowFlag = lastRowCheck;
			
			while(!lastRowCheck) {
				
				int countyID =rs.getInt(1);
				String countyName = rs.getString(2);
				System.out.println(countyName);
				int congressionalDistrict = rs.getInt(3);
				
				listOfCounties.add(new County(countyID, countyName, congressionalDistrict));
				
				rs.next();
				if(atLastRowFlag == true) {
					lastRowCheck = true;
				}
				
				if(rs.isLast() ==true) {
					atLastRowFlag = true;
				}
			}
			rs.close();
			ps.close();
			con.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return listOfCounties;
		
	}
	
	//retrieves the 
	public boolean updateCountyCongressionalDistrict(int countyID, int newCongressionalDistrict) {
		boolean successfullyChanged = false;
		
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("UPDATE counties SET congressional_district = ? WHERE id = ?");
			ps.setInt(1, newCongressionalDistrict);
			ps.setInt(2, countyID);
			ps.executeUpdate();
			
			ps.close();
			con.close();
			successfullyChanged = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return successfullyChanged;
		
	}
	
	public ArrayList<Candidate> getElectionResultsByPrecinct(String precinctName, int electionID){
		ArrayList<Candidate> resultsToReturn = new ArrayList<Candidate>();
		
		try {
			con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("GET ");
		}catch(Exception e) {
			
		}
		
		return resultsToReturn;
		
	}

}
