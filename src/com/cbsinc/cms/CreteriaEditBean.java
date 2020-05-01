package com.cbsinc.cms;

import java.sql.*;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.cbsinc.cms.faceds.ApplicationContext;

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

public class CreteriaEditBean implements java.io.Serializable {

	private static final long serialVersionUID = 2800199928016319689L;

	private String query;

	private String name = "0";

	private String creteria_id = "0";

	private Integer indx_select = 0;

	private String table_name = "creteria1 ";

	private Integer link_id = 0;

	static private Logger log = Logger.getLogger(CreteriaAddBean.class);

	public void editCatalog() {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update " + table_name + " set  name = ? , link_id = ?  where " + table_name.trim() + "_id = "
				+ creteria_id;

		try {
			HashMap args = new HashMap();
			args.put("name", name);
			args.put("link_id", Long.valueOf(link_id));
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		} catch (SQLException ex) {
			log.error(query, ex);
			Adp.rollback();
		} catch (Exception ex) {
			log.error(ex);
			Adp.rollback();
		} finally {
			Adp.close();
		}

		return;
	}

	public String getEditForm(String catalog_id, String name) {

		StringBuffer buff = new StringBuffer();
		buff.append("<form method=\"post\"   name=\"creteria_edit\"  ACTION=\"Creteria.jsp\" >\n");
		buff.append("<table>\n");
		buff.append("<tbody>\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"creteria_id\"  value = " + creteria_id + " />\n");
		buff.append("<TR><TD></TD><TD><input type=\"text\" name=\"name\"  value = " + name + " />\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"action\"  value = \"edit\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"submit\" name=\"submit\"  value = \"Сохранить\" />\n");
		buff.append("</tbody>\n");
		buff.append("</table>\n");
		buff.append("</form>\n");
		return buff.toString();
	}

	public String getEditForm(String creteria_id, String name, String jspPage) {

		StringBuffer buff = new StringBuffer();
		buff.append("<form method=\"post\"   name=\"creteria_edit\"  ACTION=\"" + jspPage + "\" >\n");
		buff.append("<table>\n");
		buff.append("<tbody>\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"creteria_id\"  value = " + creteria_id + " />\n");
		buff.append("<TR><TD></TD><TD><input type=\"text\" name=\"name\"  value = " + name + " />\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"action\"  value = \"edit\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"submit\" name=\"submit\"  value = \"Сохранить\" />\n");
		buff.append("</tbody>\n");
		buff.append("</table>\n");
		buff.append("</form>\n");
		return buff.toString();
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndx_select() {
		return indx_select;
	}

	public void setIndx_select(int indx_select) {
		this.indx_select = indx_select;
	}

	public String getCreteria_id() {
		return creteria_id;
	}

	public void setCreteria_id(String creteria_id) {
		this.creteria_id = creteria_id;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

}
