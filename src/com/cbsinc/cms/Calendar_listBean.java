package com.cbsinc.cms;

import java.sql.*;

import javax.servlet.ServletContext;

import com.cbsinc.cms.QueryManager;

import org.apache.log4j.Logger;

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


public class Calendar_listBean extends com.cbsinc.cms.WebControls implements
		java.io.Serializable {

	

	 private static final long serialVersionUID = -3430180944717439685L;

	 private static  Logger log = Logger.getLogger(Calendar_listBean.class);

	public String[][] rows = new String[10][5];

	transient public QueryManager Adp;

	private String listup = "";

	private String listdown = "";

	private Integer offset = 0;


	private String cururl;

	private String calendar_id = "-1";

	private String site_id = "2";

	private String row_id = "0";

	private Integer indx_select = 0;

	// Access sample property
	// Access sample property

	private String mount_id = "0";

	private String year_id = "0";

	transient java.util.Calendar calendar;

	String holddate_from = "0";

	String holddate_to = "0";

	private String product_id = "";

	private String descrition = "";
	
	transient ServletContext applicationContext ;

	public Calendar_listBean() {
		calendar = java.util.Calendar.getInstance();
		mount_id = "" + (calendar.get(java.util.Calendar.MONTH) + 1);
		year_id = "" + calendar.get(java.util.Calendar.YEAR);
	}

	public void init_calendar(String holddate) {
		calendar.setTimeInMillis(Long.parseLong(holddate));
		// day_id = "" + calendar.get(java.util.Calendar.DAY_OF_MONTH) ;
		// mount_id = "" + calendar.get(java.util.Calendar.MONTH) ;
		// year_id = "" + calendar.get(java.util.Calendar.YEAR) ;
	}

	public String calendar_day(String holddate) {
		calendar.setTimeInMillis(Long.parseLong(holddate));
		return "" + calendar.get(java.util.Calendar.DAY_OF_MONTH);
		// mount_id = "" + calendar.get(java.util.Calendar.MONTH) ;
		// year_id = "" + calendar.get(java.util.Calendar.YEAR) ;
	}

	public String calendar_date(String holddate) {
		calendar.setTimeInMillis(Long.parseLong(holddate));
		return "" + calendar.getTime();
		// mount_id = "" + calendar.get(java.util.Calendar.MONTH) ;
		// year_id = "" + calendar.get(java.util.Calendar.YEAR) ;
	}

	public void calendar_change() {

		calendar.set(Integer.parseInt(year_id), Integer.parseInt(mount_id) - 1,
				1);
		holddate_from = "" + calendar.getTimeInMillis();
		System.out.println(calendar.getTime());
		if (Integer.parseInt(mount_id) > 11)
			calendar.set(Integer.parseInt(year_id) + 1, 1, 1);
		else
			calendar.set(Integer.parseInt(year_id), Integer.parseInt(mount_id),
					1);
		holddate_to = "" + calendar.getTimeInMillis();
		System.out.println(calendar.getTime());
		// mount_id = "" + calendar.get(java.util.Calendar.MONTH) ;
		// year_id = "" + calendar.get(java.util.Calendar.YEAR) ;
	}

	public String getTable(int intLevelUp) {

		/*
		 * listup = "Softlisting.jsp?offset=" + (offset + 10) + "&type_id=" +
		 * type_id ; if( offset - 10 < 0 ) listdown =
		 * "Softlisting.jsp?offset=0&type_id=" + type_id ; else listdown =
		 * "Softlisting.jsp?offset=" + (offset - 10) + "&type_id=" + type_id ;
		 */

		cururl = "calendar_list.jsp?offset=" + offset;

		listup = "calendar_list.jsp?offset=" + (offset + 10); // +
																// "&calendar_id="
																// + calendar_id
																// +
																// "&phonetype_id="
																// +
																// phonetype_id
																// +
																// "&licence_id="
																// + licence_id
																// ;
		if (offset - 10 < 0)
			listdown = "calendar_list.jsp?offset=0"; // &calendar_id=" +
														// calendar_id +
														// "&phonetype_id=" +
														// phonetype_id +
														// "&licence_id=" +
														// licence_id ;
		else
			listdown = "calendar_list.jsp?offset=" + (offset - 10); // +
																	// "&calendar_id="
																	// +
																	// calendar_id
																	// +
																	// "&phonetype_id="
																	// +
																	// phonetype_id
																	// +
																	// "&licence_id="
																	// +
																	// licence_id
																	// ;

		StringBuffer table = new StringBuffer();
		Adp = new QueryManager();

		String query = "";
		// query = "select calendar_id , holddate FROM calendar WHERE active =
		// true and site_id = " + site_id + " limit 10 offset " + offset ;
		query = "select calendar_id ,  holddate   FROM calendar WHERE active = true and holddate > "
				+ holddate_from
				+ " and holddate < "
				+ holddate_to
				+ "  and soft_id = "
				+ product_id
				+ " limit 10 offset "
				+ offset;

		try 
		{
			Adp.executeQuery(query);
		

		// <%= JspPostPredictionBeanId.getComboBox("gteam_cd", "1" ,"select
		// team_cd , team_name from team where ( active = true) and ( sport_cd =
		// " + strSport_cd + " ) " ) %>
		// if(strUser_id.compareTo("1") != 0 )
		table.append("<table class=\"columns\">\n");
		table.append("<tbody>\n");
		if (intLevelUp == 2) {
			table
					.append("<TR BGCOLOR=\"#8CACBB\" >"
							+ "<TD WIDTH=\"10%\" >№ </TD>"
							+ "<TD WIDTH=\"70%\" >Число  </TD>"
							+ "<TD WIDTH=\"20%\" ><a href =\"calendar_add.jsp\">добавить</a> </TD>"
							+ "</TR>\n");

			// "<TD height=\"*\" border=\"0\" >" + this.getComboBox("type_id",
			// "" + type_id ,"select type_id , type_lable from typesoft where
			// active = true") + "<input type=\"submit\" name=\"Submit\"
			// value=\"OK\"></TD>" +
			// postManager
		} else {
			table.append("<TR BGCOLOR=\"#8CACBB\" >"
					+ "<TD WIDTH=\"10%\" >№ </TD>"
					+ "<TD WIDTH=\"70%\" >Число  </TD>"
					+ "<TD WIDTH=\"20%\" ></TD>" + "</TR>\n");
		}

		if (Adp.rows().size() < 10) {
			table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>"
					+ "</TR>\n");
		} else {
			table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD><a href=\""
					+ listup + "\">след 10</a>  </TD>" + "</TR>\n");
		}
		for (int i = 0; Adp.rows().size() > i; i++) {
			rows[i][0] = (String) Adp.getValueAt(i, 0);
			rows[i][1] = (String) Adp.getValueAt(i, 1);
			rows[i][2] = calendar_day((String) Adp.getValueAt(i, 1));
			rows[i][3] = calendar_date((String) Adp.getValueAt(i, 1));

			if (intLevelUp == 2) {
				table
						.append("<TR>"
								+ "<TD  >"
								+ rows[i][0]
								+ "</TD>"
								+ "<TD  >"
								+ rows[i][2]
								+ " ( "
								+ rows[i][3]
								+ " ) </TD>"
								+ "<TD algin=\"rigth\" ><a href =\"calendar_edit.jsp?row="
								+ i
								+ "\">редактировать</a> </TD>"
								+ "<TD algin=\"rigth\" ><a href =\"calendar_list.jsp?del="
								+ i + "\">удалить</a> </TD>" + "</TR>\n");
			} else {
				table.append("<TR>" + "<TD>" + rows[i][0] + "</TD>" + "<TD>"
						+ rows[i][2] + " ( " + rows[i][3] + " ) </TD>"
						+ "<TD algin=\"rigth\" ></TD>"
						+ "<TD algin=\"rigth\" ></TD>" + "</TR>\n");
			}

		}

		table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD><a href=\""
				+ listdown + "\">назад 10</a>  </TD>" + "</TR>\n");
		table.append("</tbody>\n");
		table.append("</TABLE>\n");
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			log.error(ex);
		}
		finally
		{
			Adp.close();			
		}
		return table.toString();
	}

	public void delete(String calendar_id) {
		Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		query = "delete FROM calendar WHERE site_id = " + site_id + " and calendar_id = " + calendar_id;
		try 
		{
			Adp.executeUpdate(query);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(ex);
			Adp.rollback();
			
		} finally {
			Adp.close();
		}

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

	
	public String getCururl() {
		return cururl;
	}

	public void setCururl(String cururl) {
		this.cururl = cururl;
	}

	public String getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(String calendar_id) {
		this.calendar_id = calendar_id;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getRow_id() {
		return row_id;
	}

	public void setRow_id(String row_id) {
		this.row_id = row_id;
	}

	public int getIndx_select() {
		return indx_select;
	}

	public void setIndx_select(int indx_select) {
		this.indx_select = indx_select;
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

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return applicationContext;
	}


	public void setServletContext(ServletContext applicationContext) {
		this.applicationContext = applicationContext ;
		
	}
}
