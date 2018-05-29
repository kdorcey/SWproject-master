package com.Team12.CS5800.VotingApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VotingApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void loginLoads() {
		try {
			HttpResponse<String> response = Unirest.get("http://localhost:8080/login")
					  .header("Cache-Control", "no-cache")
					  .header("Postman-Token", "a20cdac7-5c33-43e0-9af0-a82d1eaa68ab")
					  .asString();
			org.junit.Assert.assertEquals(response.getStatus(), 200);
		} catch (UnirestException e) {
			org.junit.Assert.fail("Failed to execute the Unirest");
			e.printStackTrace();
		}
	}
	
	@Test
	public void indexLoads() {
		try {
			HttpResponse<String> response = Unirest.get("http://localhost:8080/")
					  .header("Cache-Control", "no-cache")
					  .header("Postman-Token", "4a6ab756-d2d7-40d8-95f0-888c3489ce70")
					  .asString();
			org.junit.Assert.assertEquals(response.getStatus(), 200);
		} catch (UnirestException e) {
			org.junit.Assert.fail("Failed to execute the Unirest");
			e.printStackTrace();
		}
	}
	
	@Test
	public void registerLoads() {
		try {
			HttpResponse<String> response = Unirest.get("http://localhost:8080/register")
					  .header("Cache-Control", "no-cache")
					  .header("Postman-Token", "ce3a042b-ddba-44d4-b019-851aa34dd035")
					  .asString();
			org.junit.Assert.assertEquals(response.getStatus(), 200);
		} catch (UnirestException e) {
			org.junit.Assert.fail("Failed to execute the Unirest");
			e.printStackTrace();
		}
	}

}
