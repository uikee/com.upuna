package com.upuna.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.upuna.dao.OracleUpuna;
import com.upuna.util.ToJSON;

@Path("/v1/person")
public class V1_person {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPersons() throws Exception {
		
		PreparedStatement query = null;
		String returnString = null;
		Connection conn =null;
		Response rb = null;
		
		try {
			
			conn = OracleUpuna.OracleUpunaConn().getConnection();
			query = conn.prepareStatement("select * " + "from person");
			ResultSet rs = query.executeQuery();
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if (query != null) query.close();
				
		}
		
		return rb;
	}

}
