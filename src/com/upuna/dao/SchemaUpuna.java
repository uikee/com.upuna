package com.upuna.dao;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.upuna.util.ToJSON;

public class SchemaUpuna extends OracleUpuna {
	
public JSONArray queryReturnAddress(String address) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oracleUpunaConnection();
			query = conn.prepareStatement("select NAME,COUNTRY,ADDRESS " +
											"from PERSON " +
											"where UPPER(ADDRESS) = ? ");
			
			query.setString(1, address.toUpperCase()); //protect against sql injection
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}

}
