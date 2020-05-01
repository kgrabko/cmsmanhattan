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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class CreteriaAddBean implements java.io.Serializable {

	private static final long serialVersionUID = 8448583709739712289L;

	static private Logger log = Logger.getLogger(CreteriaAddBean.class);

	private String query;

	private String name = "0";

	private String label = "0";

	private String creteria_id = "0";

	private Integer indx_select = 0;

	private String table_name = "creteria1";

	private Integer link_id = 0;

	transient ResourceBundle sequences_rs = null;

	public CreteriaAddBean() {
		if (sequences_rs == null)
			sequences_rs = PropertyResourceBundle.getBundle("sequence");
	}

	public void addCatalog(String site_id) {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		// query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq
		// AS ID FROM ONE_SEQUENCES";
		query = "SELECT MAX(" + table_name + "_id ) + 1  as ID FROM " + table_name + "";

		try {

			Adp.executeQuery(query);

			creteria_id = Adp.getValueAt(0, 0);

			query = "insert into " + table_name + " (" + table_name
					+ "_id , catalog_id , link_id , name , label , active ) " + " values ( ? , ? , ? , ? , ? , ? ) ";

			Map args = new HashMap();
			args.put(table_name + "_id", creteria_id);
			args.put("catalog_id", site_id);
			args.put("link_id", link_id);
			args.put("name", name);
			args.put("label", label);
			args.put("active", true);
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();

		} catch (SQLException ex) {
			log.error(query, ex);
			Adp.rollback();
		} catch (Exception ex) {
			log.error(query, ex);
			Adp.rollback();
		}

		finally {
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

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public int getLink_id() {
		return link_id;
	}

	public void setLink_id(int link_id) {
		this.link_id = link_id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
