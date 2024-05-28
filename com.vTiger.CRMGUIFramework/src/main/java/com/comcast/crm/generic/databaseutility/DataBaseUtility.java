package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;



public class DataBaseUtility {
	Connection con=null;
	ResultSet result=null;
	int result1;
public void getConnection() throws SQLException {
	try{Driver d=new Driver();
	DriverManager.registerDriver(d);
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	}catch(Exception e) {
		
	}
}
public void getConnection(String url,String username,String password) throws SQLException {
	try{Driver d=new Driver();
	DriverManager.registerDriver(d);
	Connection con=DriverManager.getConnection(url,username,password);
	}catch(Exception e) {
		
	}
}
public void closeDBConnection() throws SQLException {
	try{
		con.close();
	}catch(Exception e) {		
	}
}
public ResultSet executeSelectQuery(String Query) throws SQLException {
	try{
		Statement state=con.createStatement();
		result=state.executeQuery(Query);
	    return result;
	    }catch(Exception e){
		}
	return result;
}
public int executeNonSelectQuery(String Query) {
	try{
		Statement state=con.createStatement();
		result1=state.executeUpdate(Query);
	    }catch(Exception e){
		}
	return result1;
}
}
