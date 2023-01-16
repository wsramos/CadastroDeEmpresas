package br.com.cadastrarempresas.model.dao.dbaccess;

/*	import java.io.FileInputStream;
import java.io.IOException;*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.datasource.impl.OracleDataSource;
/*import java.util.Properties;*/

	public class DB {

		private static Connection conn = null;

		public static Connection getConnection() {
			if (conn == null) {
				try {
					OracleDataSource ods = new OracleDataSource();
					ods.setURL("jdbc:oracle:thin:@//192.168.12.46:1521/oracle"); // jdbc:oracle:thin@//[hostname]:[port]/[DB service name]
					ods.setUser("appcadastrar");
					ods.setPassword("appcadastrar");
					conn = ods.getConnection();
				} 
				catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
			return conn;
		}
		
		public static void closeConnection() {
			if (conn != null) {
				try {
					conn.close();
				}
				catch(SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}

		/*
		 * private static Properties loadProperties() { try (FileInputStream fs = new
		 * FileInputStream("db.properties")) {
		 * 
		 * Properties props = new Properties(); props.load(fs);
		 * 
		 * return props; } catch (IOException e) { throw new
		 * DbException(e.getMessage()); } }
		 */
		
		public static void closeStatement(Statement st) {
			if(st != null) {
				try {
					st.close();
				}catch (SQLException e){
					throw new DbException(e.getMessage());
				}	
			}
		}
		
		public static void closeResultSet(ResultSet rs) {
			if(rs != null) {
				try {
					rs.close();
				}catch (SQLException e){
					throw new DbException(e.getMessage());
				}	
			}
		}
	}	
