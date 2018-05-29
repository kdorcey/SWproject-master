package com.Team12.CS5800.VotingApplication.model;

public interface UserDAO {
	
	public int insertUser( String username, String password, int adminStatus, String email, int voterstatus, String firstName, String lastName, String ssn, String address, String city, String state, String zipcode, String precinct, String gender, int age, String education, String securityQuestion1, String securityAnswer1, String securityQuestion2, String securityAnswer2 );
	
	public User getUser(String sessionID);
	
	public int updateUser(User user);


}
