package com.cbsinc.cms;

import java.sql.*;

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

public class XSLControlBean extends com.cbsinc.cms.WebControls implements java.io.Serializable {

	private static final long serialVersionUID = -5310948352204931322L;

	static private Logger log = Logger.getLogger(XSLControlBean.class);

	public String[][] rows = new String[10][2];

	transient public QueryManager Adp;

	private String listup = "";

	private String listdown = "";

	private Integer offset = 0;

	private String type_id = "1";

	private Integer intLevelUp = 0;

	private String phonetype_id = "1";

	private String progname_id = "1";

	private String phonemodel_id = "1";

	private String licence_id = "1";

	private String imgname;

	private String image_id;

	private String img_url;

	private String cururl;

	private String catalog_id = "-1";

	private String site_id = "0";

	// public String getTable( int offset , int sphera ) {
	public String getTable(String strUser_id) {
		if (strUser_id == null || strUser_id.length() == 0)
			strUser_id = "0";
		/*
		 * listup = "Softlisting.jsp?offset=" + (offset + 10) + "&type_id=" + type_id ;
		 * if( offset - 10 < 0 ) listdown = "Softlisting.jsp?offset=0&type_id=" +
		 * type_id ; else listdown = "Softlisting.jsp?offset=" + (offset - 10) +
		 * "&type_id=" + type_id ;
		 */

		cururl = "Softlisting.jsp?offset=" + offset + "&catalog_id=" + catalog_id + "&phonetype_id=" + phonetype_id
				+ "&licence_id=" + licence_id;

		listup = "Softlisting.jsp?offset=" + (offset + 10); // + "&catalog_id="
															// + catalog_id +
															// "&phonetype_id="
															// + phonetype_id +
															// "&licence_id=" +
															// licence_id ;
		if (offset - 10 < 0)
			listdown = "Softlisting.jsp?offset=0"; // &catalog_id=" +
													// catalog_id +
													// "&phonetype_id=" +
													// phonetype_id +
													// "&licence_id=" +
													// licence_id ;
		else
			listdown = "Softlisting.jsp?offset=" + (offset - 10); // +
																	// "&catalog_id="
																	// +
																	// catalog_id
																	// +
																	// "&phonetype_id="
																	// +
																	// phonetype_id
																	// +
																	// "&licence_id="
																	// +
																	// licence_id
																	// ;

		String strSoftName = "";
		String strSoftURL = "";
		String strImgURL = "";
		String strSoftDescription = "";
		String strSoftVersion = "";
		String strSoftCost = "";
		String strCurrency = "";
		StringBuffer table = new StringBuffer();
		Adp = new QueryManager();

		String query = "";

		query = "SELECT  \"soft\".\"soft_id\", \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\", \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\", \"soft\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" , \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\"  , \"soft\".\"image_id\" , \"images\".\"img_url\" FROM \"soft\" LEFT  JOIN \"images\" ON \"soft\".\"image_id\" = \"images\".\"image_id\" WHERE \"soft\".\"catalog_id\" = "
				+ catalog_id + " and \"soft\".\"phonetype_id\" = " + phonetype_id + " and  \"soft\".\"licence_id\" = "
				+ licence_id + " and  \"soft\".\"phonemodel_id\" = " + phonemodel_id + " limit 10 offset " + offset;
		query = "SELECT " + "public.xsl_style.xsl_style_id, " + "public.xsl_style.name, "
				+ "public.xsl_style.description, " + "public.xsl_style.active, " + "public.xsl_style.producer_id, "
				+ "public.xsl_style.owner_id,  " + "public.xsl_style.cost, " + "public.xsl_style.currency_id, "
				+ "public.xsl_style.site_id, " + "public.xsl_style.dirname " + "FROM public.xsl_style " + "WHERE "
				+ "public.xsl_style.active = true and " + "public.xsl_style.xsl_style_id = 1  " + "LIMIT 10 "
				+ "OFFSET " + offset;
		;

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

		try {
			Adp.executeQuery(query);

			// <%= JspPostPredictionBeanId.getComboBox("gteam_cd", "1" ,"select
			// team_cd , team_name from team where ( active = true) and ( sport_cd =
			// " + strSport_cd + " ) " ) %>
			// if(strUser_id.compareTo("1") != 0 )
			table.append(
					"<TABLE ALIGN=\"CENTER\" WIDTH=\"100%\"   border=\"1\"  CELLSPACING=\"0\" CELLPADDING=\"2\">\n");

			if (intLevelUp == 2) {
				table.append("<TR BGCOLOR=\"#808080\" >" + "<TD>Soft name </TD>" + "<TD>Description  </TD>"
						+ "<TD>Version </TD>" + "<TD>Cost </TD>" + "<TD>Currency </TD>"
						+ "<TD><A HREF=\"PostManager.jsp\" ><img SRC=\"images/post.jpg\" border=\"0\" alt=\"Post\" ></A></TD>"
						+ "</TR>\n");
				// "<TD height=\"*\" border=\"0\" >" + this.getComboBox("type_id",
				// "" + type_id ,"select type_id , type_lable from typesoft where
				// active = true") + "<input type=\"submit\" name=\"Submit\"
				// value=\"OK\"></TD>" +
				// postManager
			} else {
				table.append("<TR BGCOLOR=\"#808080\" >" + "<TD height=\"20%\" > Soft name </TD>"
						+ "<TD height=\"40%\" > Description </TD>" + "<TD height=\"5%\" > Version </TD>"
						+ "<TD height=\"5%\" > Cost </TD>" + "<TD height=\"20%\" > Currency </TD>"
						+ "<TD height=\"10%\" > </TD>" + "</TR>\n");
				// "<TD height=\"*\" border=\"0\" >" + this.getComboBox("type_id",
				// "" + type_id ,"select type_id , type_lable from typesoft where
				// active = true") + "<input type=\"submit\" name=\"Submit\"
				// value=\"OK\"></TD>" +
			}
			// "<TD><A HREF=\"/JspPostPrediction.jsp?offset=" + offset +
			// "&sport_cd=" + sport_cd + "\" ><img SRC=\"images/post.gif\"
			// border=\"0\" alt=\"Post\" ></A></TD>" +

			if (Adp.rows().size() < 10) {
				table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>"
						+ "</TR>\n");
			} else {
				table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>"
						+ "<TD><a href=\"" + listup + "\">Next 10</a>  </TD>" + "</TR>\n");
			}
			for (int i = 0; Adp.rows().size() > i; i++) {
				rows[i][0] = (String) Adp.getValueAt(i, 0);
				rows[i][1] = (String) Adp.getValueAt(i, 7);
				strSoftName = (String) Adp.getValueAt(i, 1);
				// strSoftURL = "downloadservlet?row=" + i + "&dev=html" ;;
				// strSoftURL = "downloadservlet?row=" + i ;
				strSoftURL = "Policy.jsp?row=" + i;
				img_url = (String) Adp.getValueAt(i, 13);
				if (img_url != null)
					strImgURL = img_url;
				else
					strImgURL = "images/Folder.jpg";
				strSoftDescription = (String) Adp.getValueAt(i, 2);
				strSoftVersion = (String) Adp.getValueAt(i, 3);
				strSoftCost = (String) Adp.getValueAt(i, 4);
				strCurrency = (String) Adp.getValueAt(i, 5);
				phonetype_id = (String) Adp.getValueAt(i, 10);
				progname_id = (String) Adp.getValueAt(i, 11);
				image_id = (String) Adp.getValueAt(i, 12);
				// , \"soft\".\"image_id\"
				// <A href="JspGetMail.jsp" ><IMG height=23 alt=""
				// src="images/select.gif" width=25 border=0><A>
				// strImgURL
				// "<IMG height=23 alt=\"\" src=\"" + strSoftURL + "\" width=25
				// border=0>
				// "<TD><A HREF=\""+ strSoftURL + "\">" + strSoftName + "</TD>" +
				table.append("<TR>" + "<TD><A HREF=\"" + strSoftURL + "\"><IMG height=23 alt=\"\" src=\"" + strImgURL
						+ "\" width=25 border=0>" + strSoftName + "</TD>" + "<TD>" + strSoftDescription + "</TD>"
						+ "<TD>" + strSoftVersion + "</TD>" + "<TD>" + strSoftCost + "</TD>" + "<TD>" + strCurrency
						+ "</TD>" + "<TD></TD>" + "</TR>\n");
			}

			// listup = Adp.rows.
			/*
			 * if( Adp.rows.size() < 10 ) { table.append("<TR>" + "<TD></TD>" + "<TD>
			 * </TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></
			 * TD>" + "</TR>\n"); } else {
			 */
			table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "<TD><a href=\""
					+ listdown + "\">Back 10</a>  </TD>" + "</TR>\n");
			// }

			table.append("</TABLE>\n");

		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			Adp.close();
		}
		return table.toString();
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

	public void setPhonetype_id(String phonetype_id) {
		this.phonetype_id = phonetype_id;
	}

	public String getPhonetype_id() {
		return phonetype_id;
	}

	public void setProgname_id(String progname_id) {
		this.progname_id = progname_id;
	}

	public String getProgname_id() {
		return progname_id;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public String getImage_id() {
		return image_id;
	}

	public String getCururl() {
		return cururl;
	}

	public void setCururl(String cururl) {
		this.cururl = cururl;
	}

	public String getPhonemodel_id() {
		return phonemodel_id;
	}

	public void setPhonemodel_id(String phonemodel_id) {
		this.phonemodel_id = phonemodel_id;
	}

	public String getLicence_id() {
		return licence_id;
	}

	public void setLicence_id(String licence_id) {
		this.licence_id = licence_id;
	}

	public String getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

}
