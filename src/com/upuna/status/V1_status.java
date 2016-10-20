package com.upuna.status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import java.sql.*;
import com.upuna.dao.*;

@Path("/v1/status")
public class V1_status {
	
	private static final String virsion = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p> Java Web Service</p>";
	}
	
	@GET
	@Path("/virsion")
	@Produces(MediaType.TEXT_HTML)
	public String virsion(){
		return "<p> Java Web Service virsion " + virsion + "</p>" ;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn =null;
		
		try {
			
			conn = OracleUpuna.OracleUpunaConn().getConnection();
			query = conn.prepareStatement("SELECT NOW() DATETIME");
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				myString = rs.getString("DATETIME");
			}
			
			query.close();
			
			returnString = "<p>Database Status</p> " + "<p>Database Date/Time return: " + myString + "</p>";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if (query != null) query.close();
				
		}
		
		return returnString;
	}

}
