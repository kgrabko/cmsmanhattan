package com.cbsinc.cms.jms.robots.test;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cbsinc.cms.AuthorizationPageBean;


public class SerializeToDatabase3 implements java.io.Serializable  {

	
	private static final String SQL_SERIALIZE_OBJECT = "INSERT INTO serialized_java_objects(object_name, serialized_object) VALUES (?, ?)"; 
	 
	private static final String SQL_DESERIALIZE_OBJECT = "SELECT serialized_object FROM serialized_java_objects WHERE object_name = ?"; 
	  
	
	transient Connection connection = null; 
	transient PreparedStatement pstmt2 = null ;
		String driver = "org.hsqldb.jdbcDriver"; 
		String url = "jdbc:hsqldb:hsql://localhost:9002"; 
		String username = "sa"; 
		String password = "vt79"; 

		
		public SerializeToDatabase3() {
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username, password); 
				AuthorizationPageBean obj = new AuthorizationPageBean(); 
				//long serialized_id = serializeJavaObjectToDB(connection, obj); 
				 deSerializeJavaObjectFromDB(connection, 1); 
				connection.close(); 

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 


		}
		
	public  long serializeJavaObjectToDB(Connection connection, 	Object objectToSerialize) throws SQLException { 
	    
	PreparedStatement pstmt = connection.prepareStatement(SQL_SERIALIZE_OBJECT); 
	pstmt.setString(1, objectToSerialize.getClass().getName()); 
	pstmt.setObject(2, objectToSerialize); 
	pstmt.executeUpdate(); 
	pstmt.close(); 
	System.out.println("Java object serialized to database. Object: " + objectToSerialize); 
	return 10; 
	
	} 
	
	
	public  Object deSerializeJavaObjectFromDB(Connection connection, long serialized_id) throws SQLException, IOException,ClassNotFoundException { 
    
		    String query = "select USER_ID , TYPE , CLASSBODY  from STORE_SESSION WHERE  USER_ID = ? " ;
		    pstmt2 = connection.prepareStatement(query); 
			Object deSerializedObject  = null ;
			//pstmt.setString(1, "mobilesoft.AuthorizationPageBean"); 
			pstmt2.setInt(1, 10000);
			ResultSet rs = pstmt2.executeQuery(); 
			rs.next(); 
			try
			{
		     deSerializedObject = rs.getObject(3); 
		     rs.close(); 
			pstmt2.close(); 
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
		
			 
	public static void main(String args[]) 
	{
	new SerializeToDatabase3();
	} 

	

}
