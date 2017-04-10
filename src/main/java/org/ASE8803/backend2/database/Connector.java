package org.ASE8803.backend2.database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Connector {
	public Connection con;
    
	public Connector() {
		String url = "jdbc:google:mysql://playlist-backend2:us-east1:playlist-database/ASE8803?user=root&password=ase8803database";
		try {
			Class.forName("com.mysql.jdbc.GoogleDriver").newInstance();
			this.con = DriverManager.getConnection(url);
			if(!con.isClosed()) System.out.println("Successfully connected to " +
			                "MySQL server using TCP/IP...");
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage() + url);
		} 
	}

	public void close() {
		try { con.close(); } catch (Exception e) { /* ignored */ }
	}
	
	public List<String> login(String email, String password) {
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<String> result = new LinkedList<>();
		try {
			if(!con.isClosed()) {
				ps = con.prepareStatement( "SELECT * FROM User WHERE email = ? AND password = ?" );
				ps.setString(1, email);
	            ps.setString(2, password );
	            
	            rs = ps.executeQuery();
	            
	            if ( rs.next() ) {
	            	result.add(rs.getString("userName"));
	            	result.add(rs.getString("firstName"));
	            	result.add(rs.getString("lastName"));
	            	result.add(rs.getString("email"));
	                return result;
	            } else {
	            	result.add("404");
	            	return result;
	            }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
		return result;
	}
	
	public boolean register(String email, String password, String username, String lastname, String firstname) {
		PreparedStatement ps = null;
		try {
			if(!con.isClosed()) {
				ps = con.prepareStatement( "INSERT INTO User (userName, firstName, lastName, password, email) VALUES (?,?,?,?,?)" );
				ps.setString(1, username);
	            ps.setString(2, firstname);
	            ps.setString(3, lastname);
	            ps.setString(4, password);
	            ps.setString(5, email);
	            
	            ps.executeUpdate();
	            
	            return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
		return false;
	}

	public List<String> getInfo(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> result = new LinkedList<>();
		try {
			if(!con.isClosed()) {
				ps = con.prepareStatement( "SELECT * FROM User WHERE email = ?" );
				ps.setString(1, email);

				rs = ps.executeQuery();

				if ( rs.next() ) {
					result.add(rs.getString("email"));
					result.add(rs.getString("userName"));
					result.add(rs.getString("firstName"));
					result.add(rs.getString("lastName"));
					return result;
				} else {
					result.add("404");
					return result;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
		return result;
	}

	public boolean delete(String email) {
		PreparedStatement ps = null;
		try {
			if(!con.isClosed()) {
				ps = con.prepareStatement( "DELETE FROM User WHERE email = ?" );
				ps.setString(1, email);

				int k = ps.executeUpdate();
				if (k == 0) {
					return false;
				}
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
		return false;
	}
}
