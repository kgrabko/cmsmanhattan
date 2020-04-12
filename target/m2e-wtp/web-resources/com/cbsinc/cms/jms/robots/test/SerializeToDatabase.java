package com.cbsinc.cms.jms.robots.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.TestClass;


public class SerializeToDatabase {

	
	private static final String SQL_SERIALIZE_OBJECT = "INSERT INTO serialized_java_objects(object_name, serialized_object) VALUES (?, ?)"; 
	 
	private static final String SQL_DESERIALIZE_OBJECT = "SELECT serialized_object FROM serialized_java_objects WHERE object_name = ?"; 
	  
	public static long serializeJavaObjectToDB(Connection connection, 	Object objectToSerialize) throws SQLException { 
	    
	PreparedStatement pstmt = connection.prepareStatement(SQL_SERIALIZE_OBJECT); 
	pstmt.setString(1, objectToSerialize.getClass().getName()); 
	pstmt.setObject(2, objectToSerialize); 
	pstmt.executeUpdate(); 
	pstmt.close(); 
	System.out.println("Java object serialized to database. Object: " + objectToSerialize); 
	return 10; 
	
	} 
	
	
	public static Object deSerializeJavaObjectFromDB(Connection connection, long serialized_id) throws SQLException, IOException,ClassNotFoundException { 
    
			PreparedStatement pstmt = connection.prepareStatement(SQL_DESERIALIZE_OBJECT); 
			Object deSerializedObject  = null ;
			pstmt.setString(1, "mobilesoft.AuthorizationPageBean"); 
			ResultSet rs = pstmt.executeQuery(); 
			rs.next(); 
			try
			{
		     deSerializedObject = rs.getObject(1); 
		     rs.close(); 
			pstmt.close(); 
			System.out.println("Java object de-serialized from database. Object: "
			+ deSerializedObject + " Classname: "
			+ deSerializedObject.getClass().getName()); 
		    
			}
			catch (Exception e) 
			{
				e.printStackTrace() ;
			}
			
			
	      
			return deSerializedObject; 
	  
			} 
		
			 
	public static void main(String args[]) throws ClassNotFoundException, 
	            
	SQLException, IOException { 
	Connection connection = null; 
	String driver = "org.hsqldb.jdbcDriver"; 
	String url = "jdbc:hsqldb:hsql://localhost:9002"; 
	String username = "sa"; 
	String password = "vt79"; 
	Class.forName(driver); 
	connection = DriverManager.getConnection(url, username, password); 
	AuthorizationPageBean obj = new AuthorizationPageBean(); 
	long serialized_id = serializeJavaObjectToDB(connection, obj); 
	 deSerializeJavaObjectFromDB(connection, serialized_id); 
	connection.close(); 
	
	} 

	

}
