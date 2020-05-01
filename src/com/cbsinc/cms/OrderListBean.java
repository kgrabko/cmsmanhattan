package com.cbsinc.cms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

public class OrderListBean extends com.cbsinc.cms.WebControls implements java.io.Serializable {

	private static final long serialVersionUID = 5492657921346633911L;

	transient static private Logger log = Logger.getLogger(OrderListBean.class);

	public String[][] rows = new String[10][2];

	private String listup = "";

	private String listdown = "";

	private Integer offset = 0;

	private Integer intLevelUp = 0;

	private String user_id;

	private String currency_lable = "0";

	private String datePattern = "dd/MM/yyyy";

	java.util.Calendar calendar;

	long dateFrom = 0;
	long dateTo = 0;

	String selectOrderlistXML = "";
	String searchquery = "0";

	public String getSelect_paystatus() {
		// System.out.println(select_paystatus);
		return select_paystatus;
	}

	public void setSelect_paystatus(String select_paystatus) {
		this.select_paystatus = select_paystatus;
	}

	public String getSelect_deliverystatus() {
		// System.out.println(select_deliverystatus);
		return select_deliverystatus;
	}

	public void setSelect_deliverystatus(String select_deliverystatus) {
		this.select_deliverystatus = select_deliverystatus;
	}

	private String select_paystatus = "";
	private String select_deliverystatus = "";
	private String deliverystatus_id = "";
	private String order_paystatus_id = "";

	public String getDeliverystatus_id() {
		return deliverystatus_id;
	}

	public void setDeliverystatus_id(String deliverystatus_id) {
		this.deliverystatus_id = deliverystatus_id;
	}

	public String getOrder_paystatus_id() {
		return order_paystatus_id;
	}

	public void setOrder_paystatus_id(String order_paystatus_id) {
		this.order_paystatus_id = order_paystatus_id;
	}

	public OrderListBean() {
		calendar = java.util.Calendar.getInstance();
		dateFrom = calendar.getTimeInMillis();
		dateTo = calendar.getTimeInMillis();
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	public int stringToInt(String s) {
		int i;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			i = 0;
		}
		return i;
	}

	public boolean setSelectedDemand() {
		QueryManager Adp = new QueryManager();
		// String query = "update tdemand set selected=true where id=" + demand
		// ;
		// Adp.executeUpdate(query);
		Adp.close();
		return true;
	}

	public boolean setPassiveDemand() {
		QueryManager Adp = new QueryManager();
		// String query = "update tdemand set active=false where id=" + demand ;
		// Adp.executeUpdate(query);
		Adp.close();
		return true;
	}

	public void setIntLevelUp(int intLevelUp) {
		this.intLevelUp = intLevelUp;
	}

	public int getIntLevelUp() {
		return intLevelUp;
	}

	public void setUser_ID(String strUserID) {
		this.user_id = strUserID;
	}

	public String getUser_ID() {
		return user_id;
	}

	public String getListup() {
		return listup;
	}

	public void setListup(String listup) {
		this.listup = listup;
	}

	public String getListdown() {
		return listdown;
	}

	public void setListdown(String listdown) {
		this.listdown = listdown;

	}

	public String getCurrency_lable() {
		return currency_lable;
	}

	public void setCurrency_lable(String currency_lable) {
		this.currency_lable = currency_lable;
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
		// getCalendar().setTimeInMillis(dateTo);
	}

	/*
	 * String dateTo Input format is "dd/mm/yyyy"
	 */
	public void setDateTo(String dateTo, String datePattern, Locale locale) {
		this.datePattern = datePattern;
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern, locale);
		try {
			this.dateTo = formatter.parse(dateTo).getTime();
		} catch (ParseException e) {
			log.error(e);
		}

	}

	/*
	 * String dateFrom Input format is "dd/mm/yyyy"
	 */
	public void setDateFrom(String dateFrom, String datePattern, Locale locale) {
		this.datePattern = datePattern;
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern, locale);
		try {
			this.dateFrom = formatter.parse(dateFrom).getTime();
		} catch (ParseException e) {
			log.error(e);
		}
	}

	public java.util.Date getSQLDateTo() {
		return new java.sql.Date(dateTo);
	}

	public java.sql.Date getSQLDateFrom() {
		return new java.sql.Date(dateFrom);
	}

	public String getFormatedDateTo(Locale locale) {
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern, locale);
		return formatter.format(getSQLDateTo());
	}

	public String getFormatedDateFrom(Locale locale) {
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern, locale);
		return formatter.format(getSQLDateFrom());
	}

	public SimpleDateFormat getSimpleDateFormat(Locale locale) {
		return new SimpleDateFormat(datePattern, locale);
	}

	public String getSelectOrderlistXML() {
		return selectOrderlistXML;
	}

	public void setSelectOrderlistXML(String selectOrderlistXML) {
		this.selectOrderlistXML = selectOrderlistXML;
	}

	public String getSearchquery() {
		return searchquery;
	}

	public void setSearchquery(String searchquery) {
		this.searchquery = searchquery;
	}

	public String getDatePattern() {
		return datePattern;
	}

	private String select_menu_catalog;

	public String getSelect_menu_catalog() {
		return select_menu_catalog;
	}

	public void setSelect_menu_catalog(String select_menu_catalog) {
		this.select_menu_catalog = select_menu_catalog;
	}

}
