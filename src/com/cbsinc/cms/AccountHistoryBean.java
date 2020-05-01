package com.cbsinc.cms;

import java.sql.*;
import java.text.NumberFormat;

import com.cbsinc.cms.Currency;
import com.cbsinc.cms.CurrencyHash;
import com.cbsinc.cms.QueryManager;

import org.apache.log4j.Logger;

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

public class AccountHistoryBean extends com.cbsinc.cms.WebControls implements java.io.Serializable {

	private static final long serialVersionUID = -5645998100032237469L;

	static private Logger log = Logger.getLogger(AccountHistoryBean.class);

	private String[] array_amount_id = new String[10];

	private Integer intUserID = 0;

	private String listup = "";

	private String listdown = "";

	private Integer offset = 0;

	private String type_id = "1";

	private Integer intLevelUp = 0;

	private String cururl;

	private String strLogin = "";

	private String curency = "$";

	private String add_amount = "0";

	private String old_amount = "0";

	private String date_input = "";

	private String date_end = "";

	private String sysdate = "";

	private String complete = "";

	private String decsription = "";

	private String active = "";

	private String amount_id = "";

	private String catalog_id = "1";

	private Float fltCredit_limit = Float.valueOf(-10);

	private String total_amount;

	private String currency_add_lable;

	private String currency_old_lable;

	private String currency_total_lable;

	private String rezult_cd = "";

	private NumberFormat nf;

	private java.util.Calendar calendar;

	public AccountHistoryBean() {
		nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		calendar = java.util.Calendar.getInstance();
	}

	transient long dateFrom = 0;
	transient long dateTo = 0;

	String selectAccountHistoryXML = "";
	String searchquery = "0";

	public String getPaymentlist(int intUserID) {

		cururl = "AccountHistory.jsp?offset=" + offset;
		listup = "AccountHistory.jsp?offset=" + (offset + 10);
		if (offset - 10 < 0)
			listdown = "AccountHistory.jsp?offset=0";
		else
			listdown = "AccountHistory.jsp?offset=" + (offset - 10);

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();

		String query = "SELECT  account_hist.add_amount, account_hist.old_amount, account_hist.date_input, account_hist.date_end, account_hist.sysdate, account_hist.complete, account_hist.decsription, account_hist.active , account_hist.id  , account_hist.total_amount , "
				+ " currency_add.currency_lable , currency_old.currency_lable ,currency_total.currency_lable , account_hist.rezult_cd"
				+ " FROM account_hist  "
				+ " LEFT OUTER   JOIN currency  currency_add ON account_hist.currency_id_add = currency_add.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_old ON account_hist.currency_id_old = currency_old.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_total ON account_hist.currency_id_total = currency_total.currency_id "
				+ " WHERE account_hist.user_id = " + intUserID + " limit 10 offset " + offset;

		try {
			Adp.executeQuery(query);

			table.append("<list>\n");
			for (int i = 0; Adp.rows().size() > i; i++) {
				add_amount = (String) Adp.getValueAt(i, 0);
				old_amount = (String) Adp.getValueAt(i, 1);
				date_input = (String) Adp.getValueAt(i, 2);
				date_end = (String) Adp.getValueAt(i, 3);
				sysdate = (String) Adp.getValueAt(i, 4);
				complete = (String) Adp.getValueAt(i, 5);
				decsription = (String) Adp.getValueAt(i, 6);
				active = (String) Adp.getValueAt(i, 7);
				amount_id = (String) Adp.getValueAt(i, 8);
				total_amount = (String) Adp.getValueAt(i, 9);
				currency_add_lable = (String) Adp.getValueAt(i, 10);
				currency_old_lable = (String) Adp.getValueAt(i, 11);
				currency_total_lable = (String) Adp.getValueAt(i, 12);
				rezult_cd = (String) Adp.getValueAt(i, 13);
				array_amount_id[i] = amount_id;

				table.append("<payment>\n");
				table.append("<amount_id>" + amount_id + "</amount_id>\n");
				table.append("<currency_add_lable>" + currency_add_lable + "</currency_add_lable>\n");
				table.append("<add_amount>" + add_amount + "</add_amount>\n");
				table.append("<sysdate>" + sysdate + "</sysdate>\n");
				table.append("<complete>" + complete + "</complete>\n");
				table.append("<rezult_cd>" + rezult_cd + "</rezult_cd>\n");
				table.append("</payment>\n");
			}

			table.append("</list>\n");

		} catch (SQLException ex) {

			log.error(query, ex);

		} catch (Exception ex) {

			log.error(ex);

		} finally {
			Adp.close();
		}

		return table.toString();

	}

	public String getPaymentlist(int intUserID, int role_id) {

		cururl = "AccountHistory.jsp?offset=" + offset;
		listup = "AccountHistory.jsp?offset=" + (offset + 10);
		if (offset - 10 < 0)
			listdown = "AccountHistory.jsp?offset=0";
		else
			listdown = "AccountHistory.jsp?offset=" + (offset - 10);

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();

		String query = "SELECT  account_hist.add_amount, account_hist.old_amount, account_hist.date_input, account_hist.date_end, account_hist.sysdate, account_hist.complete, account_hist.decsription, account_hist.active , account_hist.id  , account_hist.total_amount , "
				+ " currency_add.currency_lable , currency_old.currency_lable ,currency_total.currency_lable , account_hist.rezult_cd "
				+ " FROM account_hist  "
				+ " LEFT OUTER   JOIN currency  currency_add ON account_hist.currency_id_add = currency_add.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_old ON account_hist.currency_id_old = currency_old.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_total ON account_hist.currency_id_total = currency_total.currency_id "
				+ " WHERE account_hist.user_id = " + intUserID + " ORDER BY account_hist.id DESC  limit 10 offset "
				+ offset;

		try {
			Adp.executeQuery(query);

			table.append("<list>\n");
			for (int i = 0; Adp.rows().size() > i; i++) {
				add_amount = (String) Adp.getValueAt(i, 0);
				old_amount = (String) Adp.getValueAt(i, 1);
				date_input = (String) Adp.getValueAt(i, 2);
				date_end = (String) Adp.getValueAt(i, 3);
				sysdate = (String) Adp.getValueAt(i, 4);
				complete = (String) Adp.getValueAt(i, 5);
				decsription = (String) Adp.getValueAt(i, 6);
				active = (String) Adp.getValueAt(i, 7);
				amount_id = (String) Adp.getValueAt(i, 8);
				total_amount = (String) Adp.getValueAt(i, 9);
				currency_add_lable = (String) Adp.getValueAt(i, 10);
				currency_old_lable = (String) Adp.getValueAt(i, 11);
				currency_total_lable = (String) Adp.getValueAt(i, 12);
				rezult_cd = (String) Adp.getValueAt(i, 13);
				array_amount_id[i] = amount_id;

				table.append("<payment>\n");
				table.append("<amount_id>" + amount_id + "</amount_id>\n");
				table.append("<currency_add_lable>" + currency_add_lable + "</currency_add_lable>\n");
				table.append("<add_amount>" + add_amount + "</add_amount>\n");
				table.append("<sysdate>" + sysdate + "</sysdate>\n");
				table.append("<complete>" + complete + "</complete>\n");
				table.append("<rezult_cd>" + rezult_cd + "</rezult_cd>\n");
				table.append("</payment>\n");
			}

			table.append("</list>\n");

		} catch (SQLException ex) {

			log.error(query, ex);

		} catch (Exception ex) {

			log.error(ex);

		} finally {
			Adp.close();
		}

		return table.toString();

	}

	// --------- Business logic functionality start -----

	public int stringToInt(String s) {
		int i;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			i = 0;
			log.error(ex);
		}
		return i;
	}

	protected float getBalans() {
		return getBalans(intUserID);
	}

	public boolean isCreditable(int strUser_id) {
		return isCreditable(strUser_id, fltCredit_limit);
	}

	public boolean isCreditable(int strUser_id, float fltCredit_limit) {
		boolean rezalt = false;
		String strBalans = "0";
		float fltBalans = 0;
		String strCurrency_ID = "1";
		float floRate = 0;
		String query = "SELECT  account.amount, currency.currency_id, currency.currency_desc FROM account LEFT OUTER JOIN currency ON account.currency_id = currency.currency_id WHERE account.user_id = "
				+ strUser_id;

		QueryManager Adp = new QueryManager();

		try {
			Adp.executeQuery(query);

			strBalans = (String) Adp.getValueAt(0, 0);
			/// strBalans = "" + Float.parseFloat(strBalans);
			// strBalans = nf.format(Double.parseDouble(strBalans)) ;
			fltBalans = Float.parseFloat(strBalans);
			strCurrency_ID = (String) Adp.getValueAt(0, 1);
			Adp.close();
			CurrencyHash currencyHash = CurrencyHash.getInstance();
			Currency curr = currencyHash.getCurrency(strCurrency_ID);
			if (curr == null) {
				rezalt = false;
				throw new java.lang.UnsupportedOperationException("Object Currency == null");
			}

			floRate = curr.getRate();
			if (floRate == 0) {
				rezalt = false;
				throw new java.lang.UnsupportedOperationException("Currency rate = 0");
			}

			if (fltCredit_limit < fltBalans * floRate)
				rezalt = true;
		} catch (SQLException ex) {

			log.error(query, ex);
		} catch (Exception ex) {

			log.error(ex);
		} finally {
			Adp.close();
		}

		return rezalt;
	}

	// --------- Business logic functionality end -----
	// --------- Properties begib ---------------------

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getType_id() {
		return type_id;
	}

	public void setIntLevelUp(int intLevelUp) {
		this.intLevelUp = intLevelUp;
	}

	public int getIntLevelUp() {
		return intLevelUp;
	}

	public String getCururl() {
		return cururl;
	}

	public void setCururl(String cururl) {
		this.cururl = cururl;
	}

	public String getStrLogin() {
		return strLogin;
	}

	public void setStrLogin(String strLogin) {
		this.strLogin = strLogin;
	}

	public String getCurency() {
		return curency;
	}

	public void setCurency(String curency) {
		this.curency = curency;
	}

	public void setIntUserID(int intUserID) {
		this.intUserID = intUserID;
	}

	public int getIntUserID() {
		return intUserID;
	}

	public String getAdd_amount() {
		return add_amount;
	}

	public void setAdd_amount(String add_amount) {
		this.add_amount = add_amount;
	}

	public String getOld_amount() {
		return old_amount;
	}

	public void setOld_amount(String old_amount) {
		this.old_amount = old_amount;
	}

	public String getDate_input() {
		return date_input;
	}

	public void setDate_input(String date_input) {
		this.date_input = date_input;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}

	public String getSysdate() {
		return sysdate;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public String getDecsription() {
		return decsription;
	}

	public void setDecsription(String decsription) {
		this.decsription = decsription;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String[] getArray_amount_id() {
		return array_amount_id;
	}

	public String getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}

	public float getIntCredit_limit() {
		return fltCredit_limit;
	}

	public void setIntCredit_limit(float fltCredit_limit) {
		this.fltCredit_limit = fltCredit_limit;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	public String getListdown() {
		return listdown;
	}

	public String getListup() {
		return listup;
	}

	public void setListdown(String listdown) {
		this.listdown = listdown;
	}

	public void setListup(String listup) {
		this.listup = listup;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getCurrency_add_lable() {
		return currency_add_lable;
	}

	public void setCurrency_add_lable(String currency_add_lable) {
		this.currency_add_lable = currency_add_lable;
	}

	public String getCurrency_old_lable() {
		return currency_old_lable;
	}

	public void setCurrency_old_lable(String currency_old_lable) {
		this.currency_old_lable = currency_old_lable;
	}

	public String getCurrency_total_lable() {
		return currency_total_lable;
	}

	public void setCurrency_total_lable(String currency_total_lable) {
		this.currency_total_lable = currency_total_lable;
	}

	public java.util.Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(java.util.Calendar calendar) {
		this.calendar = calendar;
	}

	public long getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(long dateFrom) {
		this.dateFrom = dateFrom;
	}

	public long getDateTo() {
		return dateTo;
	}

	public void setDateTo(long dateTo) {
		this.dateTo = dateTo;
	}

	/*
	 * String dateTo Input format is "dd/mm/yyyy"
	 */
	public void setStrDateTo(String dateTo) {
		if (dateTo.split("/").length > 2) {
			getCalendar().set(Integer.parseInt(dateTo.split("/")[2]), Integer.parseInt(dateTo.split("/")[1]) - 1,
					Integer.parseInt(dateTo.split("/")[0]), 0, 1);
			this.dateTo = getCalendar().getTimeInMillis();
		}
	}

	/*
	 * String dateFrom Input format is "dd/mm/yyyy"
	 */
	public void setStrDateFrom(String dateFrom) {
		if (dateFrom.split("/").length > 2) {
			getCalendar().set(Integer.parseInt(dateFrom.split("/")[2]), Integer.parseInt(dateFrom.split("/")[1]) - 1,
					Integer.parseInt(dateFrom.split("/")[0]), 0, 1);
			this.dateFrom = getCalendar().getTimeInMillis();
		}
	}

	public java.util.Date getSQLDateTo() {
		return new java.sql.Date(dateTo);
	}

	public java.sql.Date getSQLDateFrom() {
		return new java.sql.Date(dateFrom);
	}

	public String getSearchquery() {
		return searchquery;
	}

	public void setSearchquery(String searchquery) {
		this.searchquery = searchquery;
	}

	public String getSelectAccountHistoryXML() {
		return selectAccountHistoryXML;
	}

	public void setSelectAccountHistoryXML(String selectAccountHistoryXML) {
		this.selectAccountHistoryXML = selectAccountHistoryXML;
	}

}
