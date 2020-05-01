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

package com.cbsinc.cms;

import java.sql.SQLException;

import com.cbsinc.cms.QueryManager;

import org.apache.log4j.Logger;

public class AccountHistoryDetalBean extends com.cbsinc.cms.WebControls implements java.io.Serializable {

	private static final long serialVersionUID = -8230325849058152562L;

	private static Logger log = Logger.getLogger(AccountHistoryDetalBean.class);

	private Integer intUserID = 0;

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

	private String total_amount;

	private String currency_add_lable;

	private String currency_old_lable;

	private String currency_total_lable;

	private String user_ip = "";

	private String user_header = "";

	private String rezult_cd = "";

	String selectAccountHistoryDetalXML = "";

	public String getPayment(int intUserID) {

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		// Adp.BeginTransaction();
		String query = "SELECT  account_hist.add_amount, account_hist.old_amount, account_hist.date_input, account_hist.date_end, account_hist.sysdate, account_hist.complete, account_hist.decsription, account_hist.active , account_hist.id  , account_hist.total_amount , "
				+ " currency_add.currency_lable , currency_old.currency_lable ,currency_total.currency_lable , account_hist.user_ip , account_hist.user_header , account_hist.rezult_cd "
				+ " FROM account_hist  "
				+ " LEFT OUTER   JOIN currency  currency_add ON account_hist.currency_id_add = currency_add.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_old ON account_hist.currency_id_old = currency_old.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_total ON account_hist.currency_id_total = currency_total.currency_id "
				+ " WHERE account_hist.id = " + amount_id;

		try {
			Adp.executeQuery(query);

			add_amount = (String) Adp.getValueAt(0, 0);
			old_amount = (String) Adp.getValueAt(0, 1);
			date_input = (String) Adp.getValueAt(0, 2);
			date_end = (String) Adp.getValueAt(0, 3);
			sysdate = (String) Adp.getValueAt(0, 4);
			complete = (String) Adp.getValueAt(0, 5);
			decsription = (String) Adp.getValueAt(0, 6);
			active = (String) Adp.getValueAt(0, 7);
			amount_id = (String) Adp.getValueAt(0, 8);
			total_amount = (String) Adp.getValueAt(0, 9);
			currency_add_lable = (String) Adp.getValueAt(0, 10);
			currency_old_lable = (String) Adp.getValueAt(0, 11);
			currency_total_lable = (String) Adp.getValueAt(0, 12);
			user_ip = (String) Adp.getValueAt(0, 13);
			user_header = (String) Adp.getValueAt(0, 14);
			rezult_cd = (String) Adp.getValueAt(0, 15);

			table.append("<payment>\n");

			table.append("<add_amount>" + add_amount + "</add_amount>\n");
			table.append("<old_amount>" + old_amount + "</old_amount>\n");
			table.append("<date_input>" + date_input + "</date_input>\n");
			table.append("<date_end>" + date_end + "</date_end>\n");
			table.append("<sysdate>" + sysdate + "</sysdate>\n");
			table.append("<complete>" + complete + "</complete>\n");
			table.append("<decsription>" + decsription + "</decsription>\n");
			table.append("<active>" + active + "</active>\n");
			table.append("<amount_id>" + amount_id + "</amount_id>\n");
			table.append("<total_amount>" + total_amount + "</total_amount>\n");
			table.append("<currency_add_lable>" + currency_add_lable + "</currency_add_lable>\n");
			table.append("<currency_old_lable>" + currency_old_lable + "</currency_old_lable>\n");
			table.append("<currency_total_lable>" + currency_total_lable + "</currency_total_lable>\n");
			table.append("<user_ip>" + user_ip + "</user_ip>\n");
			table.append("<user_header>" + user_header + "</user_header>\n");
			table.append("<rezult_cd>" + rezult_cd + "</rezult_cd>\n");
			table.append("</payment>\n");
			// Adp.commit();
		} catch (SQLException ex) {
			System.err.println(query);
			System.err.println(ex);
			System.err.println("" + this.getClass());
			System.err.println("Method " + "getPayment()");
			// Adp.rollback();
			// Adp.close();
		} finally {
			Adp.close();
		}

		return table.toString();

	}

	public String getPayment(int intUserID, int role_id) {

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		// Adp.BeginTransaction();
		String query = "SELECT  account_hist.add_amount, account_hist.old_amount, account_hist.date_input, account_hist.date_end, account_hist.sysdate, account_hist.complete, account_hist.decsription, account_hist.active , account_hist.id  , account_hist.total_amount , "
				+ " currency_add.currency_lable , currency_old.currency_lable ,currency_total.currency_lable , account_hist.user_ip , account_hist.user_header , account_hist.rezult_cd "
				+ " FROM account_hist  "
				+ " LEFT OUTER   JOIN currency  currency_add ON account_hist.currency_id_add = currency_add.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_old ON account_hist.currency_id_old = currency_old.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_total ON account_hist.currency_id_total = currency_total.currency_id "
				+ " WHERE account_hist.id = " + amount_id;

		try {

			Adp.executeQuery(query);
			add_amount = (String) Adp.getValueAt(0, 0);
			old_amount = (String) Adp.getValueAt(0, 1);
			date_input = (String) Adp.getValueAt(0, 2);
			date_end = (String) Adp.getValueAt(0, 3);
			sysdate = (String) Adp.getValueAt(0, 4);
			complete = (String) Adp.getValueAt(0, 5);
			decsription = (String) Adp.getValueAt(0, 6);
			active = (String) Adp.getValueAt(0, 7);
			amount_id = (String) Adp.getValueAt(0, 8);
			total_amount = (String) Adp.getValueAt(0, 9);
			currency_add_lable = (String) Adp.getValueAt(0, 10);
			currency_old_lable = (String) Adp.getValueAt(0, 11);
			currency_total_lable = (String) Adp.getValueAt(0, 12);
			user_ip = (String) Adp.getValueAt(0, 13);
			user_header = (String) Adp.getValueAt(0, 14);
			rezult_cd = (String) Adp.getValueAt(0, 15);

			table.append("<payment>\n");

			table.append("<add_amount>" + getStrFormatNumberFloat(add_amount) + "</add_amount>\n");
			table.append("<old_amount>" + getStrFormatNumberFloat(old_amount) + "</old_amount>\n");
			table.append("<date_input>" + date_input + "</date_input>\n");
			table.append("<date_end>" + date_end + "</date_end>\n");
			table.append("<sysdate>" + sysdate + "</sysdate>\n");
			table.append("<complete>" + complete + "</complete>\n");
			table.append("<decsription>" + decsription + "</decsription>\n");
			table.append("<active>" + active + "</active>\n");
			table.append("<amount_id>" + amount_id + "</amount_id>\n");
			table.append("<total_amount>" + getStrFormatNumberFloat(total_amount) + "</total_amount>\n");
			table.append("<currency_add_lable>" + currency_add_lable + "</currency_add_lable>\n");
			table.append("<currency_old_lable>" + currency_old_lable + "</currency_old_lable>\n");
			table.append("<currency_total_lable>" + currency_total_lable + "</currency_total_lable>\n");
			table.append("<user_ip>" + user_ip + "</user_ip>\n");
			table.append("<user_header>" + user_header + "</user_header>\n");
			table.append("<rezult_cd>" + rezult_cd + "</rezult_cd>\n");
			table.append("</payment>\n");

		} catch (SQLException ex) {
			System.err.println(query);
			System.err.println(ex);
			System.err.println("" + this.getClass());
			System.err.println("Method " + "getPayment()");
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

	public String getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
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

	public String getAmount_id() {
		return amount_id;
	}

	public void setAmount_id(String amount_id) {
		this.amount_id = amount_id;
	}

	public String getUser_header() {
		return user_header;
	}

	public void setUser_header(String user_header) {
		this.user_header = user_header;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public void setIntLevelUp(Integer intLevelUp) {
		this.intLevelUp = intLevelUp;
	}

	public void setIntUserID(Integer intUserID) {
		this.intUserID = intUserID;
	}

	public String getRezult_cd() {
		return rezult_cd;
	}

	public void setRezult_cd(String rezult_cd) {
		this.rezult_cd = rezult_cd;
	}

	public String getSelectAccountHistoryDetalXML() {
		return selectAccountHistoryDetalXML;
	}

	public void setSelectAccountHistoryDetalXML(String selectAccountHistoryDetalXML) {
		this.selectAccountHistoryDetalXML = selectAccountHistoryDetalXML;
	}

}
