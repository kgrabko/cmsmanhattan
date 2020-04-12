package com.cbsinc.cms.jms.robots.test;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.TestClass;


public class TestStore 
{


	
	public void loadClassesSessionScopeFromBase() 
	{
		QueryManager Adp = new QueryManager();
		String query = "" ;
		String key = "";
		try 
		{
		query = "select USER_ID , TYPE , CLASSBODY  from STORE_SESSION WHERE  USER_ID = 10000 " ;
		ResultSet rs  = Adp.executeQueryResultSet(query);
			while (rs.next()) 
			{
			 Object obj = rs.getObject("CLASSBODY") ;
			 System.out.println("type: " +obj.getClass().getName());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace() ;
		}
		finally
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	public void loadClassesSessionScopeFromBase1() 
	{
		//HttpServletRequest servletRequest = 
			//session.getServletContext().getNamedDispatcher("/").  
		
		QueryManager Adp = new QueryManager();
		String query = "" ;
		String key = "";
		try 
		{
		query = "select USER_ID , TYPE , CLASSBODY  from STORE_SESSION WHERE  USER_ID = ? " ;
		Object[] obj1 = new Object[1];
		obj1[0] = 10000 ;
		ResultSet rs  = Adp.executeQueryResultSet(query, obj1 );
			while (rs.next()) 
			{
			 //Object obj = rs.getObject("CLASSBODY") ;
			 Object obj = rs.getObject(3) ;
			 System.out.println("type: " +obj.getClass().getName());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace() ;
		}
		finally
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	public void loadClassesSessionScopeFromBase2() 
	{
		//HttpServletRequest servletRequest = 
			//session.getServletContext().getNamedDispatcher("/").  
		
		QueryManager Adp = new QueryManager();
		String query = "" ;
		String key = "";
		try 
		{
		query = "select USER_ID , TYPE , CLASSBODY  from STORE_SESSION WHERE  USER_ID = ? " ;
		Object[] obj1 = new Object[1];
		obj1[0] = 10000 ;
		ResultSet rs  = Adp.executeQueryResultSet(query, obj1 );
			while (rs.next()) 
			{
			 //Object obj = rs.getObject("CLASSBODY") ;
			 Object obj = rs.getObject(3) ;
			 System.out.println("type: " +obj.getClass().getName());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace() ;
		}
		finally
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	public void saveClassesSessionScope() 
	{
		java.util.HashMap ss = new HashMap();
		
		QueryManager Adp = new QueryManager();
		
		String query = "" ;
		try 
		{
					Adp.BeginTransaction();
					query = "insert into STORE_SESSION ( USER_ID ,  TYPE ,  CLASSBODY ,  ACTIVE ) "
						+ " VALUES ( ? ,  ? ,  ? , ? )" ;
					Map args = new HashMap();
					args.put("user_id" , 10000 );
					args.put("type" , "mobilesoft.AuthorizationPageBean" );
					args.put("classbody" , new TestClass());
					args.put("active" , true );
					Adp.executeInsertWithArgs(query, args);
					Adp.commit() ;	
		}
		catch (Exception e) 
		{
			Adp.rollback();
		}
		finally
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	public static void main(String[] args) 
	{
		//listFilesAsArray( new File("c://") , filenameFilter , true ) ;
		//listFiles( new File("c://") , filenameFilter , true ) ;
		TestStore testStore = new TestStore();
		testStore.saveClassesSessionScope();
		testStore.loadClassesSessionScopeFromBase1() ;
		
	}
	
	
}
