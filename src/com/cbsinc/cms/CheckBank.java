package com.cbsinc.cms;

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

import java.sql.*;
import java.util.*;
import java.security.*;
import java.net.*;
import java.io.*;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.cbsinc.cms.faceds.ApplicationContext;

public class CheckBank implements java.io.Serializable {

	private static final long serialVersionUID = -3492879607551630598L;

	transient static private Logger log = Logger.getLogger(CheckBank.class);

	public CheckBank() {
		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(t, 0, 60000);
	}

	transient java.util.TimerTask t = new java.util.TimerTask() {
		public void run() {
			OrderBank objOrderBank = getOrder();
			if (objOrderBank != null) {
				System.out.println("Order : " + objOrderBank.getOrder_ID());
				System.out.println("Work Time: " + scheduledExecutionTime());
				System.out.println("System Time: " + System.currentTimeMillis());
				System.out.println("Rezalt: " + CheckOrder(objOrderBank));
			}
		}
	};

	public OrderBank getOrder() {
		String query = "";
		OrderBank objOrderBank = null;
		QueryManager Adp = new QueryManager();
		try {
			query = "SELECT id, date_input FROM account_hist WHERE complete = false and active = true ORDER BY id ASC LIMIT 1  OFFSET 0";
			Adp.executeQuery(query);
		} catch (SQLException ex) {
			log.error(ex);
			return null;
		} finally {
			Adp.close();
		}

		if (Adp.rows().size() > 0) {
			try {
				objOrderBank = new OrderBank();
				objOrderBank.setOrder_ID((String) Adp.getValueAt(0, 0));
				objOrderBank.setBeginData((String) Adp.getValueAt(0, 1));
			} catch (Exception ex) {
				log.error(ex);
				return null;
			} finally {
				Adp.close();
			}
		}

		Adp.close();
		return objOrderBank;
	}

	public String CheckOrder(OrderBank objOrderBank) {

		try {
			URL url1 = new URL("http://www.assist.ru");
			InputStreamReader inputstreamreader = new InputStreamReader(url1.openStream());
			String request = "ShopOrderNumber=" + objOrderBank.getOrder_ID()
					+ "&Shop_ID=84473&login=gvidon&password=231003&SUCCESS=2&STARTDAY="
					+ objOrderBank.getBeginData_Day() + "&STARTMONTH=" + objOrderBank.getBeginData_Month()
					+ "&STARTYEAR=" + objOrderBank.getBeginData_Year() + "&ENDDAY=" + objOrderBank.getEndData_Day()
					+ "&ENDMONTH=" + objOrderBank.getEndData_Month() + "&ENDYEAR=" + objOrderBank.getEndData_Year()
					+ "&MEANTYPE=0&PAYMENTTYPE=0&FORMAT=1&ZIPFLAG=0&ENGLISH=0&HEADER1=0&Delimiter=;&RowDelimiter=13,10&S_FIELDS=ORDERNUMBER;RESPONSE_CODE;RECOMMENDATION;DATE;TOTAL";
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			bufferedreader = postData("http://secure.assist.ru/results/results_long.cfm", request);
			String nextLine = "";
			while ((nextLine = bufferedreader.readLine()) != null) {
				String tmp = java.net.URLDecoder.decode(nextLine, "UTF-8");
				System.err.println(tmp);
				if (tmp.indexOf(";") != -1) {
					parserRequest(objOrderBank.getOrder_ID(), tokenize(tmp, ";")[1], tokenize(tmp, ";")[2]);
					return tokenize(tmp, ";")[1];
				}
			}
		} catch (Exception ex) {
			log.error(ex);
			System.out.println(ex.toString());
		}
		return null;
	}

	public String CheckOrder1(OrderBank objOrderBank) {
		try {
			URL url1 = new URL("http://www.assist.ru");
			InputStreamReader inputstreamreader = new InputStreamReader(url1.openStream());
			String request = "ShopOrderNumber=%&Shop_ID=84473&login=gvidon&password=231003&SUCCESS=2&STARTDAY="
					+ objOrderBank.getBeginData_Day() + "&STARTMONTH=" + objOrderBank.getBeginData_Month()
					+ "&STARTYEAR=" + objOrderBank.getBeginData_Year() + "&ENDDAY=" + objOrderBank.getEndData_Day()
					+ "&ENDMONTH=" + objOrderBank.getEndData_Month() + "&ENDYEAR=" + objOrderBank.getEndData_Year()
					+ "&MEANTYPE=0&PAYMENTTYPE=0&FORMAT=1&ZIPFLAG=0&ENGLISH=1&HEADER1=0&Delimiter=;&RowDelimiter=13,10&S_FIELDS=ORDERNUMBER;RESPONSE_CODE;RECOMMENDATION;DATE;TOTAL";
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			bufferedreader = postData("http://secure.assist.ru/results/results_long.cfm", request);
			LinkedList linkedList = new LinkedList();
			String nextLine = "";
			while ((nextLine = bufferedreader.readLine()) != null) {
				String tmp = java.net.URLDecoder.decode(nextLine, "UTF-8");
				if (tmp.indexOf("ERROR") != -1)
					return null;
				System.err.println(tmp);
				if (tmp.length() > 0)
					linkedList.add(tmp);
			}

			for (int i = 0; linkedList.size() > i; i++) {
				String tmp = (String) linkedList.get(i);
				if (tmp.indexOf(";") != -1) {
					parserRequest(objOrderBank.getOrder_ID(), tokenize(tmp, ";")[1], tokenize(tmp, ";")[2]);
					return tokenize(tmp, ";")[1];
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return null;
	}

	synchronized public void parserRequest(String i_strNumerOrder, String i_strRezult, String i_strDecsription) {
		System.out.println("Order: " + i_strNumerOrder + " Rezalt: " + i_strRezult + " " + i_strDecsription);
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		try {
			if (i_strRezult.compareTo("AS000") == 0) {
				end_addmoney(Adp, i_strNumerOrder, i_strRezult, i_strRezult);
			}

			if (i_strRezult.compareTo("AS001") == 0) {
				end_addmoney(Adp, i_strNumerOrder, i_strRezult, i_strRezult);
			}
			if (i_strRezult.compareTo("AS000") != 0 && i_strRezult.compareTo("AS001") != 0
					&& i_strRezult.compareTo("AS300") != 0) {
				query = "UPDATE account_hist SET complete = ? , active = ? , rezult_cd = ? , decsription =  ?  WHERE  id =  "
						+ i_strNumerOrder + "";
				HashMap args = new HashMap();
				args.put("complete", false);
				args.put("active", false);
				args.put("rezult_cd", i_strRezult);
				args.put("decsription", i_strDecsription);
				Adp.executeUpdateWithArgs(query, args);
				Adp.commit();
			}
		} catch (SQLException ex) {
			log.error(ex);
			Adp.rollback();
			return;
		} catch (Exception ex) {
			log.error(ex);
			Adp.rollback();
			return;
		} finally {
			Adp.close();
		}

		return;
	}

	public String[] tokenize(String s, String d) {
		Vector vector = new Vector();
		for (StringTokenizer stringtokenizer = new StringTokenizer(s, d); stringtokenizer.hasMoreTokens(); vector
				.addElement(stringtokenizer.nextToken()))
			;

		String as[] = new String[vector.size()];
		for (int i = 0; i < as.length; i++)
			as[i] = (String) vector.elementAt(i);
		return as;
	}

	public BufferedReader postData(String s, String s1) {
		BufferedReader bufferedreader = null;
		try {
			System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
			try {
				Class clsFactory = Class.forName("com.sun.net.ssl.internal.ssl.Provider");
				if ((null != clsFactory) && (null == Security.getProvider("SunJSSE")))
					Security.addProvider((Provider) clsFactory.newInstance());
			} catch (ClassNotFoundException cfe) {
				throw new Exception("Unable to load the JSSE SSL stream handler.  Check classpath." + cfe.toString());
			}

			URL url1 = new URL(s);
			java.net.HttpURLConnection urlconnection = (java.net.HttpURLConnection) url1.openConnection();
			urlconnection.setUseCaches(false);
			urlconnection.setDoOutput(true);
			ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(s1.length() * 2);
			PrintWriter printwriter = new PrintWriter(bytearrayoutputstream, true);
			printwriter.print(s1);
			printwriter.flush();
			urlconnection.setRequestProperty("Content-Length", String.valueOf(bytearrayoutputstream.size()));
			urlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			bytearrayoutputstream.writeTo(urlconnection.getOutputStream());
			bufferedreader = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
		return bufferedreader;
	}

	public static void main(String[] args) {

	}

	void end_addmoney(QueryManager Adp, String i_strAccountHistory_id, String i_strRezult, String i_strDecsription)
			throws Exception {

		double add_amount = 0;
		double old_amount = 0;
		double total_amount = 0;
		// String date_input = "" ;
		double amount = 0;
		double rate = 0;
		String date_input = null;
		String user_id = "";
		String query = "";

		query = "select add_amount , old_amount ,  date_input , rate , date_end , user_id  from  account_hist where  id =  "
				+ i_strAccountHistory_id;
		Adp.executeQuery(query);
		if (Adp.rows().size() > 0) {
			add_amount = new Double((String) Adp.getValueAt(0, 0)).doubleValue();
			old_amount = new Double((String) Adp.getValueAt(0, 1)).doubleValue();
			date_input = (String) Adp.getValueAt(0, 2);
			rate = new Double((String) Adp.getValueAt(0, 3)).doubleValue();
			user_id = (String) Adp.getValueAt(0, 5);
			total_amount = old_amount + add_amount;
		}

		query = "UPDATE account_hist SET complete = true  , active = false , rezult_cd = '" + i_strRezult
				+ "'  WHERE  id = " + i_strAccountHistory_id;
		Adp.executeUpdate(query);

		total_amount = amount + (add_amount * rate);
		query = "UPDATE account SET amount = " + total_amount + " , curr = " + rate + " , date_input = '" + date_input
				+ "' WHERE  user_id = " + user_id;
		Adp.executeUpdate(query);
	}

}
