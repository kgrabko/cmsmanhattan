package com.cbsinc.cms;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

	public class QueryManagerSQL implements java.io.Serializable , IQueryManager {

	
		/**
		 * 
		 */
		private static final long serialVersionUID = -5583757095679256800L;

		/**
		 * <p>
		 * Title: Content Manager System
		 * </p>
		 * <p>
		 * Description: System building web application develop by Konstantin Grabko.
		 * Konstantin Grabko is Owner and author this code.
		 * You can not use it and you cannot change it without written permission from Konstantin Grabko
		 * Email: konstantin.grabko@yahoo.com or konstantin.grabko@gmail.com
		 * </p>
		 * <p>
		 * Copyright: Copyright (c) 2002-2014
		 * </p>
		 * <p>
		 * Company: CENTER BUSINESS SOLUTIONS INC 
		 * </p>
		 * 
		 * @author Konstantin Grabko
		 * @version 1.0
		 */

		
		final boolean debug = false ; 
		long sqltime = 0 ; 

		final private Logger log = Logger.getLogger(QueryManager.class);
		final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		final static   Map<String,GBSConnection> connection_pool = Collections.synchronizedMap(new HashMap<String,GBSConnection>() );
		final static   List<GBSConnection> free_connection_pool = Collections.synchronizedList(new LinkedList<GBSConnection>());
		
		transient public List rows = new LinkedList<String[]>();

		transient boolean isTrunsactionActive = false;

		transient public Connection conn = null;

		transient Statement stat = null;
		
		transient PreparedStatement prepa_stat = null;

		transient ResultSet rs = null;

		transient ResultSetMetaData metaData = null;

		transient String Qtable = "";

		transient String[] columnNames = {};

		final  static ResourceBundle resources_ds = PropertyResourceBundle.getBundle("driver");

		final private static ResourceBundle resources_localization = PropertyResourceBundle.getBundle("localization");

		static int max_connection = 1;

		//Input format  is "dd/mm/yyyy" 
		final public String dateFormat = "dd/MM/yyyy"  ;
		
		
		final private Map args = new HashMap();
		
		final public List rows()
		{
			return rows ;
		} 
		
		
		final public SimpleDateFormat formatte = new SimpleDateFormat(dateFormat, Locale.getDefault());
		
		
		
		final public SimpleDateFormat getSimpleDateFormat()
		{
			return  formatte ;
		}

		final  Connection getConnection() throws Throwable {

			GBSConnection conn_ ;

			readWriteLock.writeLock().lock();
			
			try{
			
			if(free_connection_pool.size() > 0)
			{
				conn_ = free_connection_pool.remove(0);
				( conn_).lock = true;
				return ( conn_).localconnection;
				
			}

			setPoolCountConnection(max_connection);
			conn_ = newInstanceConnection();
			if( conn_ == null ) return null ;
			conn_.lock = true;
			//connection_pool.add(conn_);
			connection_pool.put(conn_.key , conn_);
			if(conn_.localconnection.isClosed())
			{
				close();
				conn_.localconnection = getConnection();
			}
			}
			finally
			{
				readWriteLock.writeLock().unlock();
			}
			
			return conn_.localconnection;
		}

		final  void setPoolCountConnection(int count) {
			
			readWriteLock.writeLock().lock();
			
			try {
			int makeconn = count - connection_pool.size();
			for (int i = 0; makeconn > i; i++) {
				GBSConnection conn_ = newInstanceConnection();
				if(conn_ == null) return ;
				conn_.lock = false;
				connection_pool.put(conn_.key , conn_);
				free_connection_pool.add(conn_) ;
			}
			
			}
			finally
			{
				readWriteLock.writeLock().unlock();
			}
			
		}


		final GBSConnection newInstanceConnection() 
		{
			try 
			{
				Class.forName(resources_ds.getString("driver").trim()); // PG7.0
				if(resources_ds.getString("driver") != null)
				Class.forName( resources_ds.getString("driver").trim(), true, Thread.currentThread().getContextClassLoader() );

				Properties connProp = new Properties();
				
				String user =  System.getenv("CMS_DB_USER");
				String password = System.getenv("CMS_DB_PASSWORD");
				String url = System.getenv("CMS_DB_JDBC_URL");
				String useSSL = System.getenv("CMS_DB_USE_SSL");
				String autoReconnect = System.getenv("CMS_DB_AUTO_RECONECT");
				
				if( user != null && password != null && url != null   ) 
				{
					connProp.put("user", user.trim());
					connProp.put("password", password.trim());
					connProp.put("useSSL", useSSL.trim());
					connProp.put("autoReconnect", autoReconnect.trim());
					return new GBSConnection(DriverManager.getConnection(url.trim(), connProp));
				}
				
				connProp.put("user", resources_ds.getString("user").trim());
				connProp.put("password", resources_ds.getString("password").trim());
				connProp.put("useSSL", resources_ds.getString("useSSL").trim());
				connProp.put("autoReconnect", resources_ds.getString("autoReconnect").trim());
			
				return new GBSConnection(DriverManager.getConnection(resources_ds.getString("url").trim(), connProp));
			} catch (SQLException ex1) {
				log.error(ex1);
				log.debug(ex1);
				return null;
			} catch (ClassNotFoundException ex) {
				log.error(ex);
				log.debug(ex);
				return null;
			}
			catch (ClassCastException ex) {
				log.error(ex);
				log.debug(ex);
				return null;
			}

		}

		public QueryManagerSQL() 
		{
			try {
				if(debug) sqltime = System.currentTimeMillis() ;

				if (max_connection == 1) max_connection = Integer.parseInt(resources_ds.getString("max_connection").trim());
				conn = getConnection();
				stat = conn.createStatement();
				if(debug)System.out.println(conn.toString() + " pool size: " + connection_pool.size() );
				
				//DriverManager.setLogWriter(new PrintWriter(System.out));
				//DriverManager.setLogStream(new java.io.PrintStream(System.out));
				//formatte = new SimpleDateFormat();

			} catch (Throwable e) {
				log.error("ERROR " + conn.hashCode(),e);
				close();
			}
		}

		
		final  public void executeQuery(String query) throws SQLException {

			if(debug) System.out.println(query);
			try 
			{
				if(debug) System.out.println(query);
				
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return;
				}
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//if(stat.isClosed() ) stat = conn.createStatement();
				rs = stat.executeQuery(query);
				metaData = rs.getMetaData();
				int numberOfColumns = metaData.getColumnCount();
			    //metaData.getColumnType(numberOfColumns);
				columnNames = new String[numberOfColumns];
				for (int column = 0; column < numberOfColumns; column++) {
					columnNames[column] = metaData.getColumnLabel(column + 1);
				}

				// Get all rows.
				rows = new LinkedList();

				while (rs.next()) 
				{
					String[] Doc = new String[getColumnCount()];
					for (int i = 1; i <= getColumnCount(); i++) {
						if (rs.getString(i) == null) {
							Doc[i - 1] = "";
						} else {
							if(rs.getObject(i) instanceof java.sql.Timestamp || rs.getObject(i) instanceof java.sql.Date )
							{
								Doc[i - 1]  = formatte.format(rs.getDate(i));
							}
							else if( rs.getObject(i) instanceof Boolean  )
							{
								Doc[i - 1]  =   ((Boolean)rs.getObject(i)).toString() ;
							}
							else Doc[i - 1] = rs.getString(i) ;
						}
					}
					rows.add(Doc);
				}
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
		}

		
		final  public ResultSet executeQueryResultSet(String query) throws SQLException {

		   if(debug) System.out.println(query);
			try 
			{
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return null;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return null ;
				}
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//if(stat.isClosed() ) stat = conn.createStatement();
				rs = stat.executeQuery(query);
				return rs ;
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
		}

		

	final  public int executeInsertWithArgs(String query  , Object[] args ) throws SQLException 
		{
			if(debug) System.out.println(query);
			int keygen = 0 ;
			int result = 0 ;
			try 
			{
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return keygen;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return keygen;
				}
				
				//stat.close() ;
				
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//stat.
				prepa_stat = conn.prepareStatement(query,keygen) ;
				
				for(int i = 0 ; i < args.length ; i++ )
				{
					if( args[i] instanceof String ) prepa_stat.setString(i + 1, (String)args[i]) ;
					else if( args[i] instanceof Integer ) prepa_stat.setInt(i +1, (Integer)args[i]) ;
					else if( args[i] instanceof Long ) prepa_stat.setLong(i + 1, (Long)args[i]) ;
					else if( args[i] instanceof Float ) prepa_stat.setFloat(i +1, (Float)args[i]) ;
					else if( args[i] instanceof Double ) prepa_stat.setDouble(i + 1, (Double)args[i]) ;
					else if( args[i] instanceof Byte ) prepa_stat.setByte(i + 1, (Byte)args[i]) ;
					else if( args[i] instanceof Time ) prepa_stat.setTime(i + 1, (Time)args[i]) ;
					else if( args[i] instanceof java.sql.Date ) prepa_stat.setDate(i + 1 , (java.sql.Date)args[i]) ;
					else if( args[i] instanceof Object ) prepa_stat.setObject(i + 1, args[i]) ;
				}
				
				  result = prepa_stat.executeUpdate();
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
			
			return keygen == 0?result:keygen ;
		}
		
		
		
		
		final  public int executeInsertWithArgs(String query  , Map args ) throws SQLException 
		{
			if(debug) System.out.println(query);
			int keygen = 0 ;
			int result = 0 ;
			try 
			{
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return keygen;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return keygen;
				}
				
				//stat.close() ;
				
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//stat.
				//prepa_stat = conn.prepareStatement(query,keygen) ;
				prepa_stat = conn.prepareStatement(query) ;
				
				
				if( query.toLowerCase().indexOf("insert") == -1 ) throw new SQLException("Not found sql command insert in body query : " + query );
				String[] fields = query.substring(query.indexOf("(")+ 1, query.indexOf(")") ).split(",") ;
				
				for(int i = 0 ; i < fields.length ; i++ )
				{
					 Object obj = args.get(fields[i].trim()) ;
					 if(obj == null ) obj = args.get(fields[i].toLowerCase().trim()) ;
					 if( obj instanceof String ) prepa_stat.setString(i + 1, (String)obj) ;
						else if( obj instanceof Integer ) prepa_stat.setInt(i +1, (Integer)obj) ;
						else if( obj instanceof Long ) prepa_stat.setLong(i + 1, (Long)obj) ;
						else if( obj instanceof Float ) prepa_stat.setFloat(i +1, (Float)obj) ;
						else if( obj instanceof Double ) prepa_stat.setDouble(i + 1, (Double)obj) ;
						else if( obj instanceof Byte ) prepa_stat.setByte(i + 1, (Byte)obj) ;
						else if( obj instanceof Time ) prepa_stat.setTime(i + 1, (Time)obj) ;
						else if( obj instanceof java.sql.Date ) prepa_stat.setDate(i + 1 , (java.sql.Date)obj) ;
						//else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new java.sql.Date(((java.util.Date)obj)).getTime()) ;
						else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new java.sql.Date(((java.util.Date)obj).getTime())) ;
						else if( obj instanceof Object ) prepa_stat.setObject(i + 1, obj) ;
						else if( obj == null ) prepa_stat.setObject(i + 1, obj) ;
				}
				result = prepa_stat.executeUpdate();
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
			
			
			return keygen == 0?result:keygen ;
		}
		
		final  public int executeInsertWithJavaObject(String query  , Map args ) throws SQLException 
		{
			if(debug) System.out.println(query);
			int keygen = 0 ;
			int result = 0 ;
			try 
			{
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return keygen;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return keygen;
				}
				
				//stat.close() ;
				
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//stat.
				//prepa_stat = conn.prepareStatement(query,keygen) ;
				prepa_stat = conn.prepareStatement(query) ;
				
				
				if( query.toLowerCase().indexOf("insert") == -1 ) throw new SQLException("Not found sql command insert in body query : " + query );
				String[] fields = query.substring(query.indexOf("(")+ 1, query.indexOf(")") ).split(",") ;
				
				for(int i = 0 ; i < fields.length ; i++ )
				{
					 Object obj = args.get(fields[i].trim()) ;
					 if(obj == null ) obj = args.get(fields[i].toLowerCase().trim()) ;
					 if( obj instanceof String ) prepa_stat.setString(i + 1, (String)obj) ;
						else if( obj instanceof Integer ) prepa_stat.setInt(i +1, (Integer)obj) ;
						else if( obj instanceof Long ) prepa_stat.setLong(i + 1, (Long)obj) ;
						else if( obj instanceof Float ) prepa_stat.setFloat(i +1, (Float)obj) ;
						else if( obj instanceof Double ) prepa_stat.setDouble(i + 1, (Double)obj) ;
						else if( obj instanceof Byte ) prepa_stat.setByte(i + 1, (Byte)obj) ;
						else if( obj instanceof Time ) prepa_stat.setTime(i + 1, (Time)obj) ;
						else if( obj instanceof java.sql.Date ) prepa_stat.setDate(i + 1 , (java.sql.Date)obj) ;
						//else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new java.sql.Date(((java.util.Date)obj)).getTime()) ;
						else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new java.sql.Date(((java.util.Date)obj).getTime())) ;
						else if( obj instanceof Object ) 
						{
							prepa_stat.setObject(i + 1, obj) ;
						}
				}
				result = prepa_stat.executeUpdate();
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
			

			
			return keygen == 0?result:keygen ;
		}
		
		
		final 	public int executeUpdateWithArgs(String query  , Map args ) throws SQLException 
		{
			if(debug) System.out.println(query);
			int keygen = 0 ;
			int result = 0 ;
			try 
			{
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return keygen;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return keygen;
				}
				
				//stat.close() ;
				
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//stat.
				//prepa_stat = conn.prepareStatement(query,keygen) ;
				prepa_stat = conn.prepareStatement(query) ;
				
				
				if( query.toLowerCase().indexOf("update") == -1 ) throw new SQLException("Not found sql command update in body query : " + query );
				String[] fields = query.substring(query.toLowerCase().indexOf("set")+ 3, query.toLowerCase().indexOf("where") ).split(",") ;
				//String[] fields = query.substring(query.toLowerCase().indexOf("set")+ 3 ).split(",") ;

				String field = "" ;
				for(int i = 0 ; i < fields.length ; i++ )
				{
					field = fields[i].substring(0,fields[i].indexOf("=")).trim(); 
					Object obj = args.get(field) ;
					 if( obj instanceof String ) prepa_stat.setString(i + 1, (String)obj) ;
						else if( obj instanceof Integer ) prepa_stat.setInt(i +1, (Integer)obj) ;
						else if( obj instanceof Long ) prepa_stat.setLong(i + 1, (Long)obj) ;
						else if( obj instanceof Float ) prepa_stat.setFloat(i +1, (Float)obj) ;
						else if( obj instanceof Double ) prepa_stat.setDouble(i + 1, (Double)obj) ;
						else if( obj instanceof Byte ) prepa_stat.setByte(i + 1, (Byte)obj) ;
						else if( obj instanceof Time ) prepa_stat.setTime(i + 1, (Time)obj) ;
						else if( obj instanceof java.sql.Date ) prepa_stat.setDate(i + 1 , (java.sql.Date)obj) ;
						//else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new java.sql.Date(((java.util.Date)obj)).getTime()) ;
						else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new java.sql.Date(((java.util.Date)obj).getTime())) ;
						else if( obj instanceof Object ) prepa_stat.setObject(i + 1, obj) ;
				}
				result = prepa_stat.executeUpdate();
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
			

			return keygen == 0?result:keygen ;
		}
		
		
		

		
//		void setArgString( String value ) throws SQLException
//		{
//			if( prepa_stat == null  || prepa_stat.isClosed())  throw new SQLException("PrepaStatement colosed or null" );
//			prepa_stat. 
//			
//		}
		
		
		final  public void executeQueryWithArgs(String query  , Object[] args ) throws SQLException 
		{
			
			if(debug) System.out.println(query);
			try 
			{
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return;
				}
				
				//stat.close() ;
				
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//stat.
				prepa_stat = conn.prepareStatement(query) ;
				
				for(int i = 0 ; i < args.length ; i++ )
				{
					if( args[i] instanceof String ) prepa_stat.setString(i + 1, (String)args[i]) ;
					else if( args[i] instanceof Integer ) prepa_stat.setInt(i +1, (Integer)args[i]) ;
					else if( args[i] instanceof Long ) prepa_stat.setLong(i + 1, (Long)args[i]) ;
					else if( args[i] instanceof Float ) prepa_stat.setFloat(i +1, (Float)args[i]) ;
					else if( args[i] instanceof Double ) prepa_stat.setDouble(i + 1, (Double)args[i]) ;
					else if( args[i] instanceof Byte ) prepa_stat.setByte(i + 1, (Byte)args[i]) ;
					else if( args[i] instanceof Time ) prepa_stat.setTime(i + 1, (Time)args[i]) ;
					else if( args[i] instanceof java.sql.Date ) prepa_stat.setDate(i + 1 , (java.sql.Date)args[i]) ;
					else if( args[i] instanceof Object ) prepa_stat.setObject(i + 1, args[i]) ;
				}
				
				rs = prepa_stat.executeQuery();
				//rs = stat.executeQuery(query);
				metaData = rs.getMetaData();
				int numberOfColumns = metaData.getColumnCount();
			    //metaData.getColumnType(numberOfColumns);
				columnNames = new String[numberOfColumns];
				for (int column = 0; column < numberOfColumns; column++) {
					columnNames[column] = metaData.getColumnLabel(column + 1);
				}

				// Get all rows.
				rows = new LinkedList();

				while (rs.next()) 
				{
					String[] Doc = new String[getColumnCount()];
					for (int i = 1; i <= getColumnCount(); i++) {
						if (rs.getString(i) == null) {
							Doc[i - 1] = "";
						} else {
							if(rs.getObject(i) instanceof java.sql.Timestamp || rs.getObject(i) instanceof java.sql.Date )
							{
								Doc[i - 1]  = formatte.format(rs.getDate(i));
							}
							else if( rs.getObject(i) instanceof Boolean  )
							{
								Doc[i - 1]  =   ((Boolean)rs.getObject(i)).toString() ;
							}
							else Doc[i - 1] = rs.getString(i) ;
						}
					}
					rows.add(Doc);
				}
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
		}


		final  public ResultSet executeQueryResultSet(String query  , Object[] args ) throws SQLException 
		{
			if(debug) System.out.println(query);
			try 
			{
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return null;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return null ;
				}
				
				//stat.close() ;
				
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//stat.
				prepa_stat = conn.prepareStatement(query) ;
				
				for(int i = 0 ; i < args.length ; i++ )
				{
					if( args[i] instanceof String ) prepa_stat.setString(i + 1, (String)args[i]) ;
					else if( args[i] instanceof Integer ) prepa_stat.setInt(i +1, (Integer)args[i]) ;
					else if( args[i] instanceof Long ) prepa_stat.setLong(i + 1, (Long)args[i]) ;
					else if( args[i] instanceof Float ) prepa_stat.setFloat(i +1, (Float)args[i]) ;
					else if( args[i] instanceof Double ) prepa_stat.setDouble(i + 1, (Double)args[i]) ;
					else if( args[i] instanceof Byte ) prepa_stat.setByte(i + 1, (Byte)args[i]) ;
					else if( args[i] instanceof Time ) prepa_stat.setTime(i + 1, (Time)args[i]) ;
					else if( args[i] instanceof java.sql.Date ) prepa_stat.setDate(i + 1 , (java.sql.Date)args[i]) ;
					else if( args[i] instanceof Object ) prepa_stat.setObject(i + 1, args[i]) ;
				}
				

				return prepa_stat.executeQuery() ;
				
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
		}
		
		final  public void executeQueryWithArgs(String query  , Object[] args , int limmit , int offset ) throws SQLException 
		{
			if(debug) System.out.println(query);
			try 
			{
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return;
				}
				
				//stat.close() ;
				
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//stat.
				//stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				//rs = stat.executeQuery(query);


				prepa_stat = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
				
				for(int i = 0 ; i < args.length ; i++ )
				{
					if( args[i] instanceof String ) prepa_stat.setString(i + 1, (String)args[i]) ;
					else if( args[i] instanceof Integer ) prepa_stat.setInt(i +1, (Integer)args[i]) ;
					else if( args[i] instanceof Long ) prepa_stat.setLong(i + 1, (Long)args[i]) ;
					else if( args[i] instanceof Float ) prepa_stat.setFloat(i +1, (Float)args[i]) ;
					else if( args[i] instanceof Double ) prepa_stat.setDouble(i + 1, (Double)args[i]) ;
					else if( args[i] instanceof Byte ) prepa_stat.setByte(i + 1, (Byte)args[i]) ;
					else if( args[i] instanceof Time ) prepa_stat.setTime(i + 1, (Time)args[i]) ;
					else if( args[i] instanceof java.sql.Date ) prepa_stat.setDate(i + 1 , (java.sql.Date)args[i]) ;
					else if( args[i] instanceof Object ) prepa_stat.setObject(i + 1, args[i]) ;
				}
				
				rs = prepa_stat.executeQuery();
				rs.absolute(offset);
				
				//rs = stat.executeQuery(query);
				metaData = rs.getMetaData();
				int numberOfColumns = metaData.getColumnCount();
			    //metaData.getColumnType(numberOfColumns);
				columnNames = new String[numberOfColumns];
				for (int column = 0; column < numberOfColumns; column++) {
					columnNames[column] = metaData.getColumnLabel(column + 1);
				}

				// Get all rows.
				rows = new LinkedList();

				while (rs.next()&& limmit--!=0  ) 
				{
					String[] Doc = new String[getColumnCount()];
					for (int i = 1; i <= getColumnCount(); i++) {
						if (rs.getString(i) == null) {
							Doc[i - 1] = "";
						} else {
							if(rs.getObject(i) instanceof java.sql.Timestamp || rs.getObject(i) instanceof java.sql.Date )
							{
								Doc[i - 1]  = formatte.format(rs.getDate(i));
							}
							else if( rs.getObject(i) instanceof Boolean  )
							{
								Doc[i - 1]  =   ((Boolean)rs.getObject(i)).toString() ;
							}
							else Doc[i - 1] = rs.getString(i) ;
						}
					}
					rows.add(Doc);
				}
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
		}

		final  public List executeQueryWithArgsList(String query  , Object[] args , int limmit , int offset ) throws SQLException 
		{
			
			if(debug) System.out.println(query);
			List list = new LinkedList();
			try {
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return list ;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return list ;
				}
				
				//stat.close() ;
				
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				//stat.
				//stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				//rs = stat.executeQuery(query);


				prepa_stat = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY) ;
				
				for(int i = 0 ; i < args.length ; i++ )
				{
					if( args[i] instanceof String ) prepa_stat.setString(i + 1, (String)args[i]) ;
					else if( args[i] instanceof Integer ) prepa_stat.setInt(i +1, (Integer)args[i]) ;
					else if( args[i] instanceof Long ) prepa_stat.setLong(i + 1, (Long)args[i]) ;
					else if( args[i] instanceof Float ) prepa_stat.setFloat(i +1, (Float)args[i]) ;
					else if( args[i] instanceof Double ) prepa_stat.setDouble(i + 1, (Double)args[i]) ;
					else if( args[i] instanceof Byte ) prepa_stat.setByte(i + 1, (Byte)args[i]) ;
					else if( args[i] instanceof Time ) prepa_stat.setTime(i + 1, (Time)args[i]) ;
					else if( args[i] instanceof java.sql.Date ) prepa_stat.setDate(i + 1 , (java.sql.Date)args[i]) ;
					else if( args[i] instanceof Object ) prepa_stat.setObject(i + 1, args[i]) ;
				}
				
				rs = prepa_stat.executeQuery();
				rs.absolute(offset);
				
				//rs = stat.executeQuery(query);
				metaData = rs.getMetaData();
				int numberOfColumns = metaData.getColumnCount();
			    //metaData.getColumnType(numberOfColumns);
				columnNames = new String[numberOfColumns];
				for (int column = 0; column < numberOfColumns; column++) {
					columnNames[column] = metaData.getColumnLabel(column + 1);
				}

				// Get all rows.
				list = new LinkedList();

				while (rs.next()&& limmit--!=0  ) 
				{
					String[] Doc = new String[getColumnCount()];
					for (int i = 1; i <= getColumnCount(); i++) {
						if (rs.getString(i) == null) {
							Doc[i - 1] = "";
						} else {
							if(rs.getObject(i) instanceof java.sql.Timestamp || rs.getObject(i) instanceof java.sql.Date )
							{
								Doc[i - 1]  = formatte.format(rs.getDate(i));
							}
							else if( rs.getObject(i) instanceof Boolean  )
							{
								Doc[i - 1]  =   ((Boolean)rs.getObject(i)).toString() ;
							}
							else Doc[i - 1] = rs.getString(i) ;
						}
					}
					rows.add(Doc);
				}
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}
			
			return list ;
		}

		
		final  public List executeQueryList(String query) throws SQLException {

			if(debug) System.out.println(query);
			List list = new LinkedList();
			try {
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return list ;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return list ;
				}
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				///if(stat.isClosed() ) stat = conn.createStatement();
				rs = stat.executeQuery(query);
				//rs.absolute(row)
				metaData = rs.getMetaData();

				int numberOfColumns = metaData.getColumnCount();
			    //metaData.getColumnType(numberOfColumns);
				columnNames = new String[numberOfColumns];
				for (int column = 0; column < numberOfColumns; column++) {
					columnNames[column] = metaData.getColumnLabel(column + 1);
				}

				// Get all rows.
				list = new LinkedList();

				while (rs.next()) {
					String[] Doc = new String[getColumnCount()];
					for (int i = 1; i <= getColumnCount(); i++) {
						if (rs.getString(i) == null) {
							Doc[i - 1] = "";
						}
						else 
						{
							if(rs.getObject(i) instanceof java.sql.Timestamp || rs.getObject(i) instanceof java.sql.Date )
							{
								Doc[i - 1] = formatte.format(rs.getDate(i));
							}
							else if( rs.getObject(i) instanceof Boolean  )
							{
								Doc[i - 1]  =   ((Boolean)rs.getObject(i)).toString() ;
							}
							else Doc[i - 1] = rs.getString(i) ;
						}
					}
					list.add(Doc);

				}

			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());		}

			return list ;
		}
		

		/*
		 * 
		 */
		final  public List executeQueryList(String query , int limmit , int offset ) throws SQLException {

			if(debug) System.out.println(query);
			List list = new LinkedList();
			try {
				StringBuffer buff = new StringBuffer();
				Qtable = query;
				if (conn == null) {
					log.error("ERROR: " + query + " conn == null");
					return list ;
				}

				if (conn == null || stat == null) {
					log.error("ERROR: " + query + "  stat == null");
					return list ;
				}
				buff.append("begin query: ").append(query);
				log.debug( buff.toString());
				// log.debug(buff.toString());
				stat.close() ; // rigth close
				stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = stat.executeQuery(query);
				rs.absolute(offset);
				//Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

				//rs.absolute(row)
				metaData = rs.getMetaData();

				int numberOfColumns = metaData.getColumnCount();
			    //metaData.getColumnType(numberOfColumns);
				columnNames = new String[numberOfColumns];
				for (int column = 0; column < numberOfColumns; column++) {
					columnNames[column] = metaData.getColumnLabel(column + 1);
				}

				// Get all rows.
				list = new LinkedList();

				while (rs.next()&& limmit--!=0  ) {
					String[] Doc = new String[getColumnCount()];
					for (int i = 1; i <= getColumnCount(); i++) {
						if (rs.getString(i) == null) {
							Doc[i - 1] = "";
						}
						else 
						{
							if(rs.getObject(i) instanceof java.sql.Timestamp || rs.getObject(i) instanceof java.sql.Date )
							{
								Doc[i - 1] = formatte.format(rs.getDate(i));
							}
							else if( rs.getObject(i) instanceof Boolean  )
							{
								Doc[i - 1]  =   ((Boolean)rs.getObject(i)).toString() ;
							}
							else Doc[i - 1] = rs.getString(i) ;
						}
					}
					list.add(Doc);

				}

			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());		}

			return list ;
		}
		
		
		final  public void executeUpdate(String query) throws SQLException {
			if(debug) System.out.println(query);
			Qtable = query;
			if (conn == null) {
				log.error("ERROR: " + query + " conn == null");
				return;
			}

			if (stat == null) {
				log.error("ERROR: " + query + "  stat == null");
				return;
			}

					
			log.debug("begin update: " + query);
			
			try
			{
				stat.executeUpdate(query);
			
			}
			catch (Exception ex) 
			{
				log.error(query,ex);
				throw new SQLException(query + ex.getLocalizedMessage());
			}

			
			
			// stat.executeUpdate(norm(query)) ;
		}

		

		final  public int getColumnCount() {
			return columnNames.length;
		}

		final  public int getRowCount() {
			return rows.size();
		}

		final  public String getValueAt(int aRow, int aColumn) throws SQLException {
			if( rows.size() == 0 )	throw new SQLException("QueryManager.getValueAt - error ArrayIndexOutOffBound");
			String[] row = (String[]) rows.get(aRow);
			return row[aColumn];
		}

		final  public Object getValueObjectAt(int aRow, int aColumn) {
			Object[] row = (Object[]) rows.get(aRow);
			return row[aColumn];
		}
		
		final  public void close() {
			
			if(debug)
			{
			sqltime =   System.currentTimeMillis() - sqltime ;
			System.out.println("querytime: " + sqltime + " ms." );
			}
			
			readWriteLock.writeLock().lock();
			
			try {
				if (rs != null)
					rs.close();
				if (isTrunsactionActive)
					return;

				if (stat != null)
					stat.close();
				
				if (prepa_stat != null)
					prepa_stat.close();
				
				if (conn != null ) 
				{
					
					    if( max_connection < connection_pool.size() )
					    {
					    	connection_pool.remove("" + conn.hashCode());
					    	conn.close() ;
					    }
					    
						if (connection_pool.get("" + conn.hashCode()) instanceof GBSConnection) {
							if (((GBSConnection) connection_pool.get("" + conn.hashCode())).localconnection.equals(conn)) 
							{
								((GBSConnection) connection_pool.get("" + conn.hashCode())).lock = false;
								((GBSConnection) connection_pool.get("" + conn.hashCode())).localconnection.setAutoCommit(true);
								free_connection_pool.add(((GBSConnection) connection_pool.get("" + conn.hashCode()))) ;
								if(conn.isClosed())
								{
									connection_pool.remove("" + conn.hashCode());
									GBSConnection conn_ = newInstanceConnection();
									conn_.lock = false;
									free_connection_pool.add(conn_) ;
									connection_pool.put(conn_.key , conn_);	
								}   
							}
						}
				}
				else 
				{
					connection_pool.remove(null);
					GBSConnection conn_ = newInstanceConnection();
					conn_.lock = false;
					connection_pool.put(conn_.key , conn_);	
					free_connection_pool.add(conn_) ;
				} 
				
				

			} catch (SQLException ex) {
				log.error(ex);
				log.assertLog(false, "Method close()");
			}
			finally
			{
				readWriteLock.writeLock().unlock();
			}
		}

		final public void close_old() {
			try {
				if (rs != null)
					rs.close();
				if (isTrunsactionActive)
					return;

				if (stat != null)
					stat.close();
				if (conn != null) 
				{
					for (int i = 0; connection_pool.size() > i; i++) {
						if (connection_pool.get(i) instanceof GBSConnection) {
							if (((GBSConnection) connection_pool.get(i)).localconnection.equals(conn)) 
							{
								((GBSConnection) connection_pool.get(i)).lock = false;
								((GBSConnection) connection_pool.get(i)).localconnection.setAutoCommit(true);
							}
						}
					}
				}

			} catch (SQLException ex) {
				log.error(ex);
				log.assertLog(false, "Method close()");
			}
		}

		
		// conn

		protected void finalize() throws Throwable {
			close();
			super.finalize();
		}

		final public int string2Integer(String s) {
			int i;
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException ex) {
				i = -1;
			}
			return i;
		}

		final public double string2Double(String s) 
		{
			double d;
			try {
				d = Double.parseDouble(s);
			} catch (NumberFormatException ex) {
				d = -1.0;
			}
			return d;
		}

		

		final public void rollback() {

			try {
				if (conn == null) throw new SQLException("rollback , Connention == null, query=" + Qtable);
				if (conn.getAutoCommit()) throw new SQLException("rollback, AutoCommit must be == false, query=" + Qtable);
				conn.rollback();
				log.debug("Transaction Rollback()");
			} 
			catch (SQLException ex) 
			{
				log.error(ex);
			}
			finally 
			{
				isTrunsactionActive = false;
			}

		}

		final public void commit() {
			try 
			{
				if (conn == null) throw new SQLException("commit, Connention == null, query=" + Qtable);
				if (conn.getAutoCommit()) throw new SQLException("commit, AutoCommit must be == false , query=" + Qtable);
				conn.commit();
				log.debug("Transaction Commit()");
			}
			catch (SQLException ex) 
			{
				log.error(ex);
			}
			finally 
			{
				isTrunsactionActive = false;
			}

		}

		final public void beginTransaction() {

			try {
				if (conn == null) throw new SQLException("BeginTransaction , Connention == null, query=" + Qtable);
				conn.setAutoCommit(false);
				isTrunsactionActive = true;
				log.debug("BeginTransaction()");
			} catch (SQLException ex) {
				isTrunsactionActive = false;
				log.error(ex);
			}

		}

		final public ResourceBundle getResources_localization() {
			return resources_localization;
		}

//		final public void setResources_localization(ResourceBundle resources_localization) {
//			this.resources_localization = resources_localization;
//		}

		final public Map getArgs() {
			args.clear();
			return args;
		}

		final public Connection getCurrentConnection() {
			// TODO Auto-generated method stub
			return conn;
		}

		

	}


	final class GBSConnection {
		public Connection localconnection;

		public boolean lock = false;
		public String key = "" ;

		public GBSConnection(final Connection connection) {
			this.localconnection = connection;
			key = "" + connection.hashCode() ;
			
		}
	}
