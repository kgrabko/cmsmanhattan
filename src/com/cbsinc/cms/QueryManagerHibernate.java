package com.cbsinc.cms;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class QueryManagerHibernate implements java.io.Serializable, IQueryManager {

	private static final long serialVersionUID = -5583757095679256800L;

	/**
	 * <p>
	 * Title: Content Manager System
	 * </p>
	 * <p>
	 * Description: System building web application develop by Konstantin Grabko.
	 * Konstantin Grabko is Owner and author this code. You can not use it and you
	 * cannot change it without written permission from Konstantin Grabko Email:
	 * konstantin.grabko@yahoo.com or konstantin.grabko@gmail.com
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

	final boolean debug = false;
	long sqltime = 0;

	final private Logger log = Logger.getLogger(QueryManager.class);

	transient public List rows = new LinkedList<String[]>();

	transient boolean isTrunsactionActive = false;

	Session session = null;

	transient String Qtable = "";

	transient String[] columnNames = {};
	transient Object[] row = {};

	final private static ResourceBundle resources_localization = PropertyResourceBundle.getBundle("localization");

	static int max_connection = 1;

	// Input format is "dd/mm/yyyy"
	final public String dateFormat = "dd/MM/yyyy";

	final private Map args = new HashMap();

	final public List rows() {
		return rows;
	}

	final public SimpleDateFormat formatte = new SimpleDateFormat(dateFormat, Locale.getDefault());

	final public SimpleDateFormat getSimpleDateFormat() {
		return formatte;
	}

	public QueryManagerHibernate() {
		try {

			// session = HibernateUtil.getSessionFactory().openSession();
			session = getSessionFactory().openSession();

		} catch (Throwable e) {
			log.error("ERROR Session " + session.hashCode(), e);
			close();
		}
	}

	final public void executeQuery(String query) throws SQLException {
		query = query.toLowerCase();
		int index = query.indexOf("limit");
		if (index != -1) {
			query = query.substring(0, query.indexOf("limit"));
		}
		if (debug)
			System.out.println(query);
		try {
			if (debug)
				System.out.println(query);

			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());
			// Get all rows.
			rows = new LinkedList();

			Query hquery = session.createSQLQuery(query);
			hquery.setCacheable(true);
			List list = hquery.list();
			Iterator it = list.iterator();

			while (it.hasNext()) {

				Object obj = it.next();
				if (obj instanceof Object[])
					row = (Object[]) obj;
				else {
					row = new Object[1];
					row[0] = obj;
				}

				String[] Doc = new String[row.length];

				for (int i = 0; i < getColumnCount(); i++) {
					if (row[i] == null) {
						Doc[i] = "";
					} else {
						if (row[i] instanceof java.sql.Timestamp || row[i] instanceof java.sql.Date) {
							Doc[i] = formatte.format(row[i]);
						} else if (row[i] instanceof Boolean) {
							Doc[i] = ((Boolean) row[i]).toString();
						} else
							Doc[i] = row[i].toString();
					}
				}
				rows.add(Doc);

			}

		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}
	}

	@Deprecated
	final public ResultSet executeQueryResultSet(String query) throws SQLException {
		query = query.toLowerCase();
		int index = query.indexOf("limit");
		if (index != -1) {
			query = query.substring(0, query.indexOf("limit"));
		}
		if (debug)
			System.out.println(query);
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());
			// log.debug(buff.toString());
			System.out.println("bad qury");

		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}
		return null;
	}

	final public int executeInsertWithArgs(String query, Object[] args) throws SQLException {
		if (debug)
			System.out.println(query);
		int keygen = 0;
		int result = 0;
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return keygen;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query upadteQuery = session.createSQLQuery(query);

			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof String)
					upadteQuery.setString(i + 1, (String) args[i]);
				else if (args[i] instanceof Integer)
					upadteQuery.setInteger(i + 1, (Integer) args[i]);
				else if (args[i] instanceof Long)
					upadteQuery.setLong(i + 1, (Long) args[i]);
				else if (args[i] instanceof Float)
					upadteQuery.setFloat(i + 1, (Float) args[i]);
				else if (args[i] instanceof Double)
					upadteQuery.setDouble(i + 1, (Double) args[i]);
				else if (args[i] instanceof Byte)
					upadteQuery.setByte(i + 1, (Byte) args[i]);
				else if (args[i] instanceof Time)
					upadteQuery.setTime(i + 1, (Time) args[i]);
				else if (args[i] instanceof java.sql.Date)
					upadteQuery.setDate(i + 1, (java.sql.Date) args[i]);
				else if (args[i] instanceof Object)
					upadteQuery.setSerializable(i + 1, (Serializable) args[i]);
			}

			result = upadteQuery.executeUpdate();
		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}

		return keygen == 0 ? result : keygen;
	}

	final public int executeInsertWithArgs(String query, Map args) throws SQLException {
		if (debug)
			System.out.println(query);
		int keygen = 0;
		int result = 0;
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return keygen;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query upadteQuery = session.createSQLQuery(query);

			if (query.toLowerCase().indexOf("insert") == -1)
				throw new SQLException("Not found sql command insert in body query : " + query);
			String[] fields = query.substring(query.indexOf("(") + 1, query.indexOf(")")).split(",");

			for (int i = 0; i < fields.length; i++) {
				Object obj = args.get(fields[i].trim());
				if (obj == null)
					obj = args.get(fields[i].toLowerCase().trim());
				if (obj instanceof String)
					upadteQuery.setString(i + 1, (String) obj);
				else if (obj instanceof Integer)
					upadteQuery.setInteger(i + 1, (Integer) obj);
				else if (obj instanceof Long)
					upadteQuery.setLong(i + 1, (Long) obj);
				else if (obj instanceof Float)
					upadteQuery.setFloat(i + 1, (Float) obj);
				else if (obj instanceof Double)
					upadteQuery.setDouble(i + 1, (Double) obj);
				else if (obj instanceof Byte)
					upadteQuery.setByte(i + 1, (Byte) obj);
				else if (obj instanceof Time)
					upadteQuery.setTime(i + 1, (Time) obj);
				else if (obj instanceof java.sql.Date)
					upadteQuery.setDate(i + 1, (java.sql.Date) obj);
				// else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new
				// java.sql.Date(((java.util.Date)obj)).getTime()) ;
				else if (obj instanceof java.util.Date)
					upadteQuery.setDate(i + 1, new java.sql.Date(((java.util.Date) obj).getTime()));
				else if (obj instanceof Object)
					upadteQuery.setSerializable(i + 1, (Serializable) obj);
				else if (obj == null)
					upadteQuery.setSerializable(i + 1, (Serializable) obj);
			}

			result = upadteQuery.executeUpdate();
		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}

		return keygen == 0 ? result : keygen;
	}

	final public int executeInsertWithJavaObject(String query, Map args) throws SQLException {
		if (debug)
			System.out.println(query);
		int keygen = 0;
		int result = 0;
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return keygen;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query upadteQuery = session.createSQLQuery(query);

			if (query.toLowerCase().indexOf("insert") == -1)
				throw new SQLException("Not found sql command insert in body query : " + query);
			String[] fields = query.substring(query.indexOf("(") + 1, query.indexOf(")")).split(",");

			for (int i = 0; i < fields.length; i++) {
				Object obj = args.get(fields[i].trim());
				if (obj == null)
					obj = args.get(fields[i].toLowerCase().trim());
				if (obj instanceof String)
					upadteQuery.setString(i + 1, (String) obj);
				else if (obj instanceof Integer)
					upadteQuery.setInteger(i + 1, (Integer) obj);
				else if (obj instanceof Long)
					upadteQuery.setLong(i + 1, (Long) obj);
				else if (obj instanceof Float)
					upadteQuery.setFloat(i + 1, (Float) obj);
				else if (obj instanceof Double)
					upadteQuery.setDouble(i + 1, (Double) obj);
				else if (obj instanceof Byte)
					upadteQuery.setByte(i + 1, (Byte) obj);
				else if (obj instanceof Time)
					upadteQuery.setTime(i + 1, (Time) obj);
				else if (obj instanceof java.sql.Date)
					upadteQuery.setDate(i + 1, (java.sql.Date) obj);
				// else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new
				// java.sql.Date(((java.util.Date)obj)).getTime()) ;
				else if (obj instanceof java.util.Date)
					upadteQuery.setDate(i + 1, new java.sql.Date(((java.util.Date) obj).getTime()));
				else if (obj instanceof Object) {
					upadteQuery.setSerializable(i + 1, (Serializable) obj);
				}
			}

			result = upadteQuery.executeUpdate();
		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}

		return keygen == 0 ? result : keygen;
	}

	final public int executeUpdateWithArgs(String query, Map args) throws SQLException {
		if (debug)
			System.out.println(query);
		int keygen = 0;
		int result = 0;
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return keygen;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query upadteQuery = session.createSQLQuery(query);

			if (query.toLowerCase().indexOf("update") == -1)
				throw new SQLException("Not found sql command update in body query : " + query);
			String[] fields = query
					.substring(query.toLowerCase().indexOf("set") + 3, query.toLowerCase().indexOf("where")).split(",");
			String field = "";
			for (int i = 0; i < fields.length; i++) {
				field = fields[i].substring(0, fields[i].indexOf("=")).trim();
				Object obj = args.get(field);
				if (obj instanceof String)
					upadteQuery.setString(i + 1, (String) obj);
				else if (obj instanceof Integer)
					upadteQuery.setInteger(i + 1, (Integer) obj);
				else if (obj instanceof Long)
					upadteQuery.setLong(i + 1, (Long) obj);
				else if (obj instanceof Float)
					upadteQuery.setFloat(i + 1, (Float) obj);
				else if (obj instanceof Double)
					upadteQuery.setDouble(i + 1, (Double) obj);
				else if (obj instanceof Byte)
					upadteQuery.setByte(i + 1, (Byte) obj);
				else if (obj instanceof Time)
					upadteQuery.setTime(i + 1, (Time) obj);
				else if (obj instanceof java.sql.Date)
					upadteQuery.setDate(i + 1, (java.sql.Date) obj);
				// else if( obj instanceof java.util.Date ) prepa_stat.setDate(i + 1 , new
				// java.sql.Date(((java.util.Date)obj)).getTime()) ;
				else if (obj instanceof java.util.Date)
					upadteQuery.setDate(i + 1, new java.sql.Date(((java.util.Date) obj).getTime()));
				else if (obj instanceof Object)
					upadteQuery.setSerializable(i + 1, (Serializable) obj);
			}
			result = upadteQuery.executeUpdate();
		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}

		return keygen == 0 ? result : keygen;
	}

//		void setArgString( String value ) throws SQLException
//		{
//			if( prepa_stat == null  || prepa_stat.isClosed())  throw new SQLException("PrepaStatement colosed or null" );
//			prepa_stat. 
//			
//		}

	final public void executeQueryWithArgs(String query, Object[] args) throws SQLException {
		query = query.toLowerCase();
		int index = query.indexOf("limit");
		if (index != -1) {
			query = query.substring(0, query.indexOf("limit"));
		}
		if (debug)
			System.out.println(query);
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query hquery = session.createSQLQuery(query);
			hquery.setCacheable(false);

			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof String)
					hquery.setString(i + 1, (String) args[i]);
				else if (args[i] instanceof Integer)
					hquery.setInteger(i + 1, (Integer) args[i]);
				else if (args[i] instanceof Long)
					hquery.setLong(i + 1, (Long) args[i]);
				else if (args[i] instanceof Float)
					hquery.setFloat(i + 1, (Float) args[i]);
				else if (args[i] instanceof Double)
					hquery.setDouble(i + 1, (Double) args[i]);
				else if (args[i] instanceof Byte)
					hquery.setByte(i + 1, (Byte) args[i]);
				else if (args[i] instanceof Time)
					hquery.setTime(i + 1, (Time) args[i]);
				else if (args[i] instanceof java.sql.Date)
					hquery.setDate(i + 1, (java.sql.Date) args[i]);
				else if (args[i] instanceof Object)
					hquery.setSerializable(i + 1, (Serializable) args[i]);
			}

			// Get all rows.
			rows = new LinkedList();
			List list = hquery.list();
			Iterator it = list.iterator();

			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof Object[])
					row = (Object[]) obj;
				else {
					row = new Object[1];
					row[0] = obj;
				}

				String[] Doc = new String[row.length];

				for (int i = 0; i < getColumnCount(); i++) {
					if (row[i] == null) {
						Doc[i] = "";
					} else {
						if (row[i] instanceof java.sql.Timestamp || row[i] instanceof java.sql.Date) {
							Doc[i] = formatte.format(row[i]);
						} else if (row[i] instanceof Boolean) {
							Doc[i] = ((Boolean) row[i]).toString();
						} else
							Doc[i] = row[i].toString();
					}
				}
				rows.add(Doc);

			}
		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}
	}

	@Deprecated
	final public ResultSet executeQueryResultSet(String query, Object[] args) throws SQLException {
		query = query.toLowerCase();
		int index = query.indexOf("limit");
		if (index != -1) {
			query = query.substring(0, query.indexOf("limit"));
		}
		if (debug)
			System.out.println(query);
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return null;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query hquery = session.createSQLQuery(query);
			hquery.setCacheable(false);

			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof String)
					hquery.setString(i + 1, (String) args[i]);
				else if (args[i] instanceof Integer)
					hquery.setInteger(i + 1, (Integer) args[i]);
				else if (args[i] instanceof Long)
					hquery.setLong(i + 1, (Long) args[i]);
				else if (args[i] instanceof Float)
					hquery.setFloat(i + 1, (Float) args[i]);
				else if (args[i] instanceof Double)
					hquery.setDouble(i + 1, (Double) args[i]);
				else if (args[i] instanceof Byte)
					hquery.setByte(i + 1, (Byte) args[i]);
				else if (args[i] instanceof Time)
					hquery.setTime(i + 1, (Time) args[i]);
				else if (args[i] instanceof java.sql.Date)
					hquery.setDate(i + 1, (java.sql.Date) args[i]);
				else if (args[i] instanceof Object)
					hquery.setSerializable(i + 1, (Serializable) args[i]);
			}

			return null;

		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}
	}

	final public void executeQueryWithArgs(String query, Object[] args, int limmit, int offset) throws SQLException {
		query = query.toLowerCase();
		int index = query.indexOf("limit");
		if (index != -1) {
			query = query.substring(0, query.indexOf("limit"));
		}
		if (debug)
			System.out.println(query);
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query upadteQuery = session.createSQLQuery(query);
			upadteQuery.setCacheable(false);
			upadteQuery.setMaxResults(limmit);
			upadteQuery.setFetchSize(limmit * offset);

			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof String)
					upadteQuery.setString(i + 1, (String) args[i]);
				else if (args[i] instanceof Integer)
					upadteQuery.setInteger(i + 1, (Integer) args[i]);
				else if (args[i] instanceof Long)
					upadteQuery.setLong(i + 1, (Long) args[i]);
				else if (args[i] instanceof Float)
					upadteQuery.setFloat(i + 1, (Float) args[i]);
				else if (args[i] instanceof Double)
					upadteQuery.setDouble(i + 1, (Double) args[i]);
				else if (args[i] instanceof Byte)
					upadteQuery.setByte(i + 1, (Byte) args[i]);
				else if (args[i] instanceof Time)
					upadteQuery.setTime(i + 1, (Time) args[i]);
				else if (args[i] instanceof java.sql.Date)
					upadteQuery.setDate(i + 1, (java.sql.Date) args[i]);
				else if (args[i] instanceof Object)
					upadteQuery.setSerializable(i + 1, (Serializable) args[i]);
			}

			// Get all rows.
			rows = new LinkedList();
			List list = upadteQuery.list();
			Iterator it = list.iterator();

			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof Object[])
					row = (Object[]) obj;
				else {
					row = new Object[1];
					row[0] = obj;
				}

				String[] Doc = new String[row.length];

				for (int i = 0; i < getColumnCount(); i++) {
					if (row[i] == null) {
						Doc[i] = "";
					} else {
						if (row[i] instanceof java.sql.Timestamp || row[i] instanceof java.sql.Date) {
							Doc[i] = formatte.format(row[i]);
						} else if (row[i] instanceof Boolean) {
							Doc[i] = ((Boolean) row[i]).toString();
						} else
							Doc[i] = row[i].toString();
					}
				}
				rows.add(Doc);

			}

		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}
	}

	final public List executeQueryWithArgsList(String query, Object[] args, int limmit, int offset)
			throws SQLException {
		query = query.toLowerCase();
		int index = query.indexOf("limit");
		if (index != -1) {
			query = query.substring(0, query.indexOf("limit"));
		}
		if (debug)
			System.out.println(query);
		List list = new LinkedList();
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + "session == null");
				return list;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query upadteQuery = session.createSQLQuery(query);
			upadteQuery.setCacheable(false);
			upadteQuery.setMaxResults(limmit);
			upadteQuery.setFetchSize(limmit * offset);

			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof String)
					upadteQuery.setString(i + 1, (String) args[i]);
				else if (args[i] instanceof Integer)
					upadteQuery.setInteger(i + 1, (Integer) args[i]);
				else if (args[i] instanceof Long)
					upadteQuery.setLong(i + 1, (Long) args[i]);
				else if (args[i] instanceof Float)
					upadteQuery.setFloat(i + 1, (Float) args[i]);
				else if (args[i] instanceof Double)
					upadteQuery.setDouble(i + 1, (Double) args[i]);
				else if (args[i] instanceof Byte)
					upadteQuery.setByte(i + 1, (Byte) args[i]);
				else if (args[i] instanceof Time)
					upadteQuery.setTime(i + 1, (Time) args[i]);
				else if (args[i] instanceof java.sql.Date)
					upadteQuery.setDate(i + 1, (java.sql.Date) args[i]);
				else if (args[i] instanceof Object)
					upadteQuery.setSerializable(i + 1, (Serializable) args[i]);
			}
			// Get all rows.

			List listResult = upadteQuery.list();
			Iterator it = listResult.iterator();

			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof Object[])
					row = (Object[]) obj;
				else {
					row = new Object[1];
					row[0] = obj;
				}

				String[] Doc = new String[row.length];

				for (int i = 0; i < getColumnCount(); i++) {
					if (row[i] == null) {
						Doc[i] = "";
					} else {
						if (row[i] instanceof java.sql.Timestamp || row[i] instanceof java.sql.Date) {
							Doc[i] = formatte.format(row[i]);
						} else if (row[i] instanceof Boolean) {
							Doc[i] = ((Boolean) row[i]).toString();
						} else
							Doc[i] = row[i].toString();
					}
				}
				rows.add(Doc);

			}
		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}

		return list;
	}

	final public List executeQueryList(String query) throws SQLException {

		query = query.toLowerCase();
		int index = query.indexOf("limit");
		if (index != -1) {
			query = query.substring(0, query.indexOf("limit"));
		}
		if (debug)
			System.out.println(query);
		List list = new LinkedList();
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return list;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());
			Query upadteQuery = session.createSQLQuery(query);
			upadteQuery.setCacheable(false);
			List listResult = upadteQuery.list();
			Iterator it = listResult.iterator();

			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof Object[])
					row = (Object[]) obj;
				else {
					row = new Object[1];
					row[0] = obj;
				}

				String[] Doc = new String[row.length];

				for (int i = 0; i < getColumnCount(); i++) {
					if (row[i] == null) {
						Doc[i] = "";
					} else {
						if (row[i] instanceof java.sql.Timestamp || row[i] instanceof java.sql.Date) {
							Doc[i] = formatte.format(row[i]);
						} else if (row[i] instanceof Boolean) {
							Doc[i] = ((Boolean) row[i]).toString();
						} else
							Doc[i] = row[i].toString();
					}
				}
				rows.add(Doc);

			}

		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}

		return list;
	}

	/*
	 * 
	 */
	final public List executeQueryList(String query, int limmit, int offset) throws SQLException {

		query = query.toLowerCase();
		int index = query.indexOf("limit");
		if (index != -1) {
			query = query.substring(0, query.indexOf("limit"));
		}
		if (debug)
			System.out.println(query);
		List list = new LinkedList();
		try {
			StringBuffer buff = new StringBuffer();
			Qtable = query;
			if (session == null) {
				log.error("ERROR: " + query + " session == null");
				return list;
			}

			buff.append("begin query: ").append(query);
			log.debug(buff.toString());

			Query upadteQuery = session.createSQLQuery(query);
			upadteQuery.setCacheable(false);
			upadteQuery.setMaxResults(limmit);
			upadteQuery.setFetchSize(limmit * offset);
			// Get all rows.
			List listResult = upadteQuery.list();
			Iterator it = listResult.iterator();

			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof Object[])
					row = (Object[]) obj;
				else {
					row = new Object[1];
					row[0] = obj;
				}

				String[] Doc = new String[row.length];

				for (int i = 0; i < getColumnCount(); i++) {
					if (row[i] == null) {
						Doc[i] = "";
					} else {
						if (row[i] instanceof java.sql.Timestamp || row[i] instanceof java.sql.Date) {
							Doc[i] = formatte.format(row[i]);
						} else if (row[i] instanceof Boolean) {
							Doc[i] = ((Boolean) row[i]).toString();
						} else
							Doc[i] = row[i].toString();
					}
				}
				rows.add(Doc);

			}

		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}

		return list;
	}

	final public void executeUpdate(String query) throws SQLException {
		if (debug)
			System.out.println(query);
		Qtable = query;
		if (session == null) {
			log.error("ERROR: " + query + " session == null");
			return;
		}

		log.debug("begin update: " + query);

		try {
			Query upadteQuery = session.createSQLQuery(query);
			upadteQuery.executeUpdate();

		} catch (Exception ex) {
			log.error(query, ex);
			throw new SQLException(query + ex.getLocalizedMessage());
		}

	}

	final public int getColumnCount() {
		// Object obj = rows.get(1);
		// if(obj == null) return 0 ;
		// String[] row = (String[])obj;
		return row.length;
	}

	final public int getRowCount() {
		return rows.size();
	}

	final public String getValueAt(int aRow, int aColumn) throws SQLException {
		if (rows.size() == 0)
			throw new SQLException("QueryManager.getValueAt - error ArrayIndexOutOffBound");
		String[] row = (String[]) rows.get(aRow);
		return row[aColumn];
	}

	final public Object getValueObjectAt(int aRow, int aColumn) {
		Object[] row = (Object[]) rows.get(aRow);
		return row[aColumn];
	}

	final public void close() {
		if (debug) {
			sqltime = System.currentTimeMillis() - sqltime;
			System.out.println("querytime: " + sqltime + " ms.");
		}

		try {
			if (session != null)
				if (session.isOpen())
					session.close();

		} catch (Exception ex) {
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

	final public double string2Double(String s) {
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
			if (session == null)
				throw new SQLException("rollback , Session == null, query=" + Qtable);
			if (!session.getTransaction().isActive())
				throw new SQLException("rollback, Hebernate Transaction must be used, query=" + Qtable);
			log.debug("Transaction Rollback()");
			session.getTransaction().rollback();
		} catch (SQLException ex) {
			log.error(ex);
		} finally {
			isTrunsactionActive = false;
		}

	}

	final public void commit() {
		try {
			if (session == null)
				throw new SQLException("commit, Connention == null, query=" + Qtable);
			if (!session.getTransaction().isActive())
				throw new SQLException("commit, Hebernate Transaction must be used, query=" + Qtable);
			log.debug("Transaction Commit()");
			session.getTransaction().commit();
		} catch (SQLException ex) {
			log.error(ex);
		} finally {
			isTrunsactionActive = false;
		}

	}

	final public void BeginTransaction() {

		try {
			if (session == null)
				throw new SQLException("BeginTransaction , Session == null, query=" + Qtable);
			session.getTransaction().begin();
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

	final public Map getArgs() {
		args.clear();
		return args;
	}

	@Deprecated
	public Connection getCurrentConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

}
