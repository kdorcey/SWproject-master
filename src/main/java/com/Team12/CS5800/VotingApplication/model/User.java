package com.Team12.CS5800.VotingApplication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Team12.CS5800.VotingApplication.model.DataConnection.MyConnectionProvider;

public class User {
	
	private class UserInfo {
		
		 private String email;
		 private VoterStatus voterstatus;
		 private String firstName;
		 private String lastName;
		 private String ssn;
		 private String address;
		 private String city;
		 private String state;
		 private String zipcode;
		 private String precinct;
		 private String gender;
		 private int age;
		 private String education;
		 private String securityQuestion1;
		 private String securityAnswer1;
		 private String securityQuestion2;
		 private String securityAnswer2;
		 
		 public UserInfo( String email, int voterstatus, String firstName, String lastName, String ssn, String address, String city, String state, String zipcode, String precinct, String gender, int age, String education, String securityQuestion1, String securityAnswer1, String securityQuestion2, String securityAnswer2) {
			 this.email = email;
			 if(voterstatus == 0) {
				 this.voterstatus = VoterStatus.NOT_APPLIED;
			 }
			 else if (voterstatus == 1) {
				 this.voterstatus = VoterStatus.APPLIED;
			 }
			 else {
				 this.voterstatus = VoterStatus.APPROVED;
			 }
			 this.firstName = firstName;
			 this.lastName = lastName;
			 this.ssn = ssn;
			 this.address = address;
			 this.city = city;
			 this.state = state;
			 this.zipcode = zipcode;
			 this.precinct = precinct;
			 this.gender = gender;
			 this.age = age;
			 this.education = education;
			 this.securityQuestion1 = securityQuestion1;
			 this.securityAnswer1 = securityAnswer1;
			 this.securityQuestion2 = securityQuestion2;
			 this.securityAnswer2 = securityAnswer2;
		 }
		 
		 public String getEmail() {
			 return email;
		 }
		 
		 public VoterStatus getVoterStatus() {
			 return voterstatus;
		 }
		 
		 public String getFirstName() {
			 return firstName;
		 }
		 
		 public String getLastName() {
			 return lastName;
		 }
		 
		 public String getSSN() {
			 return ssn;
		 }
		 
		 public String getAddress() {
			 return address;
		 }
		 
		 public String getCity() {
			 return city;
		 }
		 
		 public String getState() {
			 return state;
		 }
		 
		 public String getZipCode() {
			 return zipcode;
		 }
		 
		 public String getGender() {
			 return gender;
		 }
		 
		 public int getAge() {
			 return age;
		 }
		 
		 public String getEducation() {
			 return education;
		 }
		 
		 public String getSecurityQuestion1() {
			 return securityQuestion1;
			 
		 }
		 public String getSecurityQuestion2() {
			 return securityQuestion2;
		 }
		 public String getSecurityAnswer1() {
			 return securityAnswer1;
		 }
		 public String getSecurityAnswer2() {
			 return securityAnswer2;
		 }
		 
		 public void setEmail(String newEmail) {
			 this.email = newEmail;
		 }
		 
		 public void setVoterStatus(VoterStatus newStatus) {
			 this.voterstatus = newStatus;
		 }
		 
		 public void setFirstName(String newFirstName) {
			 this.firstName = newFirstName;
		 }
		 
		 public void setLastName(String newLastName) {
			 this.lastName = newLastName;
		 }
		 
		 public void setSSN(String newSSN) {
			 this.ssn = newSSN;
		 }
		 
		 public void setAddress(String newAddress) {
			 this.address = newAddress;
		 }
		 
		 public void setCity(String newCity) {
			 this.city = newCity;
		 }
		 
		 public void setState(String newState) {
			 this.state = newState;
		 }
		 
		 public void setZipCode(String newZip) {
			 this.zipcode = newZip;
		 }
		 
		 public String getPrecinct() {
			 return this.precinct;
		 }
		 
		 public void setPrecinct(String precinct) {
			 this.precinct = precinct;
		 }
		 
		 public void setGender(String newGender) {
			 this.gender = newGender;
		 }
		 
		 public void setAge(int newAge) {
			 this.age = newAge;
		 }
		 
		 public void setEducation(String newEducation) {
			 this.education = newEducation;
		 }
		 
		 public void setSecurityQuestion1(String newQuestion) {
			 this.securityQuestion1 = newQuestion;
			 
		 }
		 public void setSecurityQuestion2(String newQuestion) {
			 this.securityQuestion2 = newQuestion;
		 }
		 public void setSecurityAnswer1(String newAnswer) {
			 this.securityAnswer1 = newAnswer;
		 }
		 public void setSecurityAnswer2(String newAnswer) {
			 this.securityAnswer2 = newAnswer;
		 }

	}
	
	
	private int id;
    private String username;
    private String password;
    private AdminStatus adminStatus;
    private UserInfo userInfo;
    
    private UserDAOImpl UDAO = new UserDAOImpl();
    
    public User( int id, String username, String password, int adminStatus, String email, int voterstatus, String firstName, String lastName, String ssn, String address, String city, String state, String zipcode, String precinct, String gender, int age, String education, String securityQuestion1, String securityAnswer1, String securityQuestion2, String securityAnswer2) {
    		this.id = id;
    		this.username = username;
    		this.password = password;
    		if (adminStatus == 0) {
    			this.adminStatus = AdminStatus.USER;
    		}
    		else if (adminStatus == 1) {
    			this.adminStatus = AdminStatus.MANAGER;
    		}
    		else {
    			this.adminStatus = AdminStatus.ADMIN;
    		}
    		this.userInfo = new UserInfo(email, voterstatus, firstName, lastName, ssn, address, city, state, zipcode, precinct, gender, age, education, securityQuestion1, securityAnswer1, securityQuestion2, securityAnswer2);
    }
    
    public int getID() {
    		return id;
    }
    
    public String getUsername() {
    		return username;
    }
    
    public void setUsername(String newUsername) {
    		username = newUsername;
    		UDAO.updateUser(this);
    }
    
    public String getPassword() {
    		return password;
    }
    
    public void setPassword(String newPassword) {
    		password = newPassword;
    		UDAO.updateUser(this);
    }
    
    public AdminStatus getAdminStatus() {
    		return adminStatus;
    }
    
    public void setAdminStatus(AdminStatus newStatus) {
    		adminStatus = newStatus;
    		UDAO.updateUser(this);
    }
    
    public String getEmail() {
    		return userInfo.getEmail();
    }
    
    public void setEmail(String newEmail) {
    		userInfo.setEmail(newEmail);
    		UDAO.updateUser(this);
    }
    
    public VoterStatus getVoterStatus() {
    		return userInfo.getVoterStatus();
    };
    
    public void setVoterStatus(VoterStatus newStatus) {
    		userInfo.setVoterStatus(newStatus);
    		UDAO.updateUser(this);
    }
    
    public String getFirstName() {
    		return userInfo.getFirstName();
    }
    
    public void setFirstName(String newFirstName) {
    		userInfo.setFirstName(newFirstName);
    		UDAO.updateUser(this);
    }
    
    public String getLastName() {
    		return userInfo.getLastName();
    }
    
    public void setLastName(String newLastName) {
    		userInfo.setLastName(newLastName);
    		UDAO.updateUser(this);
    }
    
    public String getSSN() {
    		return userInfo.getSSN();
    }
    
    public void setSSN(String newSSN) {
    		userInfo.setSSN(newSSN);
    		UDAO.updateUser(this);
    }
    
    public String getAddress() {
    		return userInfo.getAddress();
    }
    
    public void setAddress(String newAddress) {
    		userInfo.setAddress(newAddress);
    		UDAO.updateUser(this);
    }
    
    public String getCity() {
    		return userInfo.getCity();
    }
    
    public void setCity(String newCity) {
    		userInfo.setCity(newCity);
    		UDAO.updateUser(this);
    }
    
    public String getState() {
    		return userInfo.getState();
    }
    
    public void setState(String newState) {
    		userInfo.setState(newState);
    		UDAO.updateUser(this);
    }
    
    public String getZipCode() {
    		return userInfo.getZipCode();
    }
    
    public void setZipCode(String newZipCode) {
    		userInfo.setZipCode(newZipCode);
    		UDAO.updateUser(this);
    }
    
    public String getPrecinct() {
    		return userInfo.getPrecinct();
    }
    
    public void setPrecinct(String precinct) {
    		userInfo.setPrecinct(precinct);
    		UDAO.updateUser(this);
    }
    
    public String getGender() {
		return userInfo.getGender();
    }

    public void setGender(String gender) {
		userInfo.setGender(gender);
		UDAO.updateUser(this);
    }
    
    public int getAge() {
    	return userInfo.getAge();
    }
    
    public void setAge(int age) {
    	userInfo.setAge(age);
    	UDAO.updateUser(this);
    }
    
    public String getEducation() {
    	return userInfo.getEducation();
    }
    
    public void setEducation(String education) {
    	userInfo.setEducation(education);
    	UDAO.updateUser(this);
    }
    
    public String getSecurityQuestion1() {
    		return userInfo.getSecurityQuestion1();
    	
    }
    public void setSecurityQuestion1(String newQuestion) {
    		userInfo.setSecurityQuestion1(newQuestion);
    		UDAO.updateUser(this);
    }
    public String getSecurityQuestion2() {
    		return userInfo.getSecurityQuestion2();
    }
    public void setSecurityQuestion2(String newQuestion) {
    	userInfo.setSecurityQuestion2(newQuestion);
		UDAO.updateUser(this);
    }
    public String getSecurityAnswer1() {
    		return userInfo.getSecurityAnswer1();
    }
    public void setSecurityAnswer1(String newAnswer) {
    		userInfo.setSecurityAnswer1(newAnswer);
		UDAO.updateUser(this);
    }
    public String getSecurityAnswer2() {
    		return userInfo.getSecurityAnswer2();
    }
    public void setSecurityAnswer2(String newAnswer) {
    		userInfo.setSecurityAnswer2(newAnswer);
		UDAO.updateUser(this);
    }
    
   

}