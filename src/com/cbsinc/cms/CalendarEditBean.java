package com.cbsinc.cms;

import java.sql.*;

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

public class CalendarEditBean extends com.cbsinc.cms.WebControls implements java.io.Serializable {

	private static final long serialVersionUID = 8381891800819464179L;

	static private Logger log = Logger.getLogger(CalendarEditBean.class);

	private String query;

	private String holddate = "0";

	private String site_id = "0";

	private String calendar_id = "0";

	private Integer indx_select = 0;

	private String day_id = "0";

	private String mount_id = "0";

	private String year_id = "0";

	private String product_id = "";

	transient java.util.Calendar calendar;

	private String first_name = "";
	private String last_name = "";
	private String father_name = "";
	private String document_number = "";
	private String document_type = "";
	private String age = "";
	private String note = "";

	public CalendarEditBean() {
		calendar = java.util.Calendar.getInstance();
		day_id = "" + calendar.get(java.util.Calendar.DAY_OF_MONTH);
		mount_id = "" + calendar.get(java.util.Calendar.MONTH);
		year_id = "" + calendar.get(java.util.Calendar.YEAR);
	}

	public void init_calendar(String holddate) {
		calendar.setTimeInMillis(Long.parseLong(holddate));
		day_id = "" + calendar.get(java.util.Calendar.DAY_OF_MONTH);
		mount_id = "" + (calendar.get(java.util.Calendar.MONTH) + 1);
		year_id = "" + calendar.get(java.util.Calendar.YEAR);
	}

	public void editCalendar() {
		if (product_id == null || product_id.length() == 0)
			return;
		calendar.set(Integer.parseInt(year_id), Integer.parseInt(mount_id) - 1, Integer.parseInt(day_id));
		holddate = "" + calendar.getTimeInMillis();
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		// if(intLevelUp == 2 ) query = "SELECT \"soft\".\"soft_id\",
		// \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\",
		// \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\",
		// \"soft\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" ,
		// \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\" ,
		// \"soft\".\"image_id\" , \"images\".\"img_url\" FROM \"soft\" LEFT
		// JOIN \"images\" ON \"soft\".\"image_id\" = \"images\".\"image_id\"
		// WHERE \"soft\".\"type_id\" = " + type_id + " and
		// \"soft\".\"phonetype_id\" = " + phonetype_id + " and
		// \"soft\".\"progname_id\" = " + progname_id + " and
		// \"soft\".\"phonemodel_id\" = " + phonemodel_id + " limit 10 offset "
		// + offset ;
		// else query = "SELECT \"soft\".\"soft_id\",
		// \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\",
		// \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\",
		// \"soft\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" ,
		// \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\" ,
		// \"soft\".\"image_id\" , \"images\".\"img_url\" FROM \"soft\" LEFT
		// JOIN \"images\" ON \"soft\".\"image_id\" = \"images\".\"image_id\"
		// WHERE \"soft\".\"type_id\" = " + type_id + " and
		// \"soft\".\"phonetype_id\" = " + phonetype_id + " and
		// \"soft\".\"progname_id\" = " + progname_id + " and
		// \"soft\".\"phonemodel_id\" = " + phonemodel_id + " and
		// \"soft\".\"soft_id\" = func_soft_file_id(\"soft\".\"file_id\") limit
		// 10 offset " + offset ;

		query = "update calendar set soft_id = " + product_id + " , holddate = " + holddate + " where calendar_id = "
				+ calendar_id;

		try {
			Adp.executeUpdate(query);
			Adp.commit();
		} catch (SQLException ex) {
			log.error(ex);
			Adp.rollback();
		} finally {
			Adp.close();
		}
		return;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public int getIndx_select() {
		return indx_select;
	}

	public void setIndx_select(int indx_select) {
		this.indx_select = indx_select;
	}

	public String getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(String calendar_id) {
		this.calendar_id = calendar_id;
	}

	public String getHolddate() {
		return holddate;
	}

	public void setHolddate(String holddate) {
		this.holddate = holddate;
	}

	public String getDay_id() {
		return day_id;
	}

	public void setDay_id(String day_id) {
		this.day_id = day_id;
	}

	public String getMount_id() {
		return mount_id;
	}

	public void setMount_id(String mount_id) {
		this.mount_id = mount_id;
	}

	public String getYear_id() {
		return year_id;
	}

	public void setYear_id(String year_id) {
		this.year_id = year_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
