package com.Team12.CS5800.VotingApplication;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Team12.CS5800.VotingApplication.model.SessionGrabber;
import com.Team12.CS5800.VotingApplication.model.DataConnection.MyConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

//EmailAuthGrabber
//SessionGrabber
//UserDAOImpl
//UserGrabber

@RunWith(MockitoJUnitRunner.class)
public class VotingApplicationDAOTests {
	
    @InjectMocks SessionGrabber mockSG; //@InjectMocks automatically instantiates too
	@Mock Connection mockConnection;
	@Mock PreparedStatement mockPreparedStmnt;

    
    public VotingApplicationDAOTests() {
    	
    	
    }
    
    
    @Test
    public void testStoreSession() {
    //LinkedList mockedList = mock(LinkedList.class);
    	
    	

    		Connection mockConn = mock(Connection.class);
    		PreparedStatement mockPreparedStmnt = mock(PreparedStatement.class);
    		try {

				when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
				doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
				doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
				when(mockPreparedStmnt.executeUpdate()).thenReturn(1);
				doNothing().when(mockPreparedStmnt).close();
				doNothing().when(mockConn).close();
				
				SessionGrabber sg = mock(SessionGrabber.class);
				sg.storeSession("testID", 999);
				
				verify(mockConn, times(1)).prepareStatement(anyString());
				verify(mockPreparedStmnt, times(1)).setString(anyInt(), anyString());
				verify(mockPreparedStmnt, times(1)).setInt(anyInt(), anyInt());
				verify(mockPreparedStmnt, times(1)).executeUpdate();
				verify(mockPreparedStmnt, times(1)).close();
				verify(mockConn, times(1)).close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	
    	/*	
		con = MyConnectionProvider.getCon();
			ps = con.prepareStatement("insert into sessions (sessionid, userid) values (?,?)");
			ps.setString(1, sessionID);
			ps.setInt(2, userID);
			status = ps.executeUpdate();
			ps.close();
			con.close();
		*/
    }

	@Test
	public void contextLoads() {
		
	}
	
}
