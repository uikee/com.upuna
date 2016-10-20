package com.upuna.dao;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OracleUpuna {
	
	private static DataSource OracleUpuna = null;
	private static Context context = null;
	
	public static DataSource OracleUpunaConn() throws Exception {
		
		if (OracleUpuna != null){
			return OracleUpuna;
		}
		
		try {
			
			if (context == null) {
				context = new InitialContext();
			}
			
			OracleUpuna = (DataSource) context.lookup("upunaOracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return OracleUpuna;
	}
	
	protected static Connection oracleUpunaConnection() {
		Connection conn = null;
		try {
			conn = OracleUpunaConn().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
}

}
