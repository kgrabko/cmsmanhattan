package com.cbsinc.cms;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ShopListBean implements java.io.Serializable {

	/**
	 * 
	 */
	transient private static final long serialVersionUID = 4782598326136441203L;

	transient static private Logger log = Logger.getLogger(WebControls.class);
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

	private Boolean searchquery = false;

	private String searchValueArg = "";

	public String[][] rows = new String[10][2];

	public String[][] newsrows = new String[10][2];

	transient public QueryManager Adp;

	transient public QueryManager newsAdp;

	private Integer offset = 0;

	private String img_url;

	private String img_url2;

	private String catalog_id = "-1";

	private String currency_id = "";

	private String currency_id2 = "";

	private String currency_cd = "";

	private String currency_desc = "";

	private String currency_desc2 = "";

	private String product_name = "";

	private String product_name2 = "";

	private String product_url = "";

	private String product_url2 = "";

	private String product_iconurl = "";

	private String product_iconurl2 = "";

	private String product_description = "";

	private String product_description2 = "";

	private String product_cost = "";

	private String product_cost2 = "";

	public ShopListBean() {
	}

	public String getProductlist(String strUser_id, String site_id) {
		if (strUser_id == null || strUser_id.length() == 0)
			strUser_id = "0";
		// openlist
		StringBuffer table = new StringBuffer();
		table.append("<list>\n");
		if (offset - 10 < 0) {
		} else {
		}
		Adp = new QueryManager();
		String query = "";

		// if( searchquery) query = "SELECT \"soft\".\"soft_id\",
		// \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\",
		// \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\",
		// \"soft\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" ,
		// \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\" ,
		// \"soft\".\"image_id\" , \"images\".\"img_url\" ,
		// \"soft\".\"fulldescription\" , \"big_images\".\"img_url\" FROM
		// \"soft\" LEFT JOIN \"images\" ON \"soft\".\"image_id\" =
		// \"images\".\"image_id\" LEFT JOIN \"big_images\" ON
		// \"soft\".\"bigimage_id\" = \"big_images\".\"big_images_id\" WHERE
		// \"soft\".\"site_id\" = " + site_id+ " and \"soft\".\"name\" LIKE '%"
		// + searchValueArg +"' ORDER BY \"soft\".\"soft_id\" DESC limit 10
		// offset " + offset ;
		// else query = "SELECT \"soft\".\"soft_id\",
		// \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\",
		// \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\",
		// \"soft\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" ,
		// \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\" ,
		// \"soft\".\"image_id\" , \"images\".\"img_url\" ,
		// \"soft\".\"fulldescription\" , \"big_images\".\"img_url\" FROM
		// \"soft\" LEFT JOIN \"images\" ON \"soft\".\"image_id\" =
		// \"images\".\"image_id\" LEFT JOIN \"big_images\" ON
		// \"soft\".\"bigimage_id\" = \"big_images\".\"big_images_id\" WHERE
		// \"soft\".\"catalog_id\" = " + catalog_id + " and \"soft\".\"active\"
		// = true and \"soft\".\"site_id\" = " + site_id+ " ORDER BY
		// \"soft\".\"soft_id\" DESC limit 10 offset " + offset ;

		if (searchquery)
			query = "SELECT  \"soft\".\"soft_id\", \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\", \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\", \"file\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" , \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\"  , \"soft\".\"image_id\" , \"images\".\"img_url\" , \"soft\".\"fulldescription\" , \"big_images\".\"img_url\" FROM \"soft\" LEFT  JOIN \"images\" ON \"soft\".\"image_id\" = \"images\".\"image_id\"  LEFT  JOIN \"big_images\" ON \"soft\".\"bigimage_id\" = \"big_images\".\"big_images_id\"  LEFT  JOIN file  ON soft.file_id = file.file_id   WHERE  \"soft\".\"site_id\" = "
					+ site_id + " and \"soft\".\"name\" LIKE '%" + searchValueArg
					+ "' ORDER BY \"soft\".\"soft_id\" DESC limit 10 offset " + offset;
		else
			query = "SELECT  \"soft\".\"soft_id\", \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\", \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\", \"file\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" , \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\"  , \"soft\".\"image_id\" , \"images\".\"img_url\" , \"soft\".\"fulldescription\" , \"big_images\".\"img_url\"  FROM \"soft\" LEFT  JOIN \"images\" ON \"soft\".\"image_id\" = \"images\".\"image_id\"  LEFT  JOIN \"big_images\" ON \"soft\".\"bigimage_id\" = \"big_images\".\"big_images_id\"  LEFT  JOIN file  ON soft.file_id = file.file_id  WHERE \"soft\".\"catalog_id\" = "
					+ catalog_id + " and  \"soft\".\"active\" = true  and \"soft\".\"site_id\" = " + site_id
					+ "   ORDER BY \"soft\".\"soft_id\" DESC limit 10 offset " + offset;

		// "LEFT JOIN file ON soft.file_id = file.file_id " +

		try {
			Adp.executeQuery(query);

			for (int i = 0; Adp.rows().size() > i; i = i + 2) {
				rows[i][0] = (String) Adp.getValueAt(i, 0);
				rows[i][1] = Adp.getValueAt(i, 7) == null ? "" : Adp.getValueAt(i, 7);
				// rows[i][1] = Adp.getValueAt(i, 7) ; //== null
				// ?"":Adp.getValueAt(i, 7) ;

				product_name = (String) Adp.getValueAt(i, 1);
				// strSoftURL = "downloadservlet?row=" + i + "&dev=html" ;;
				// strSoftURL = "downloadservlet?row=" + i ;
				product_url = "Policy.jsp?row=" + i;

				img_url = (String) Adp.getValueAt(i, 13);
				if (img_url != null)
					product_iconurl = img_url;
				else
					product_iconurl = "images/Folder.jpg";
				product_description = (String) Adp.getValueAt(i, 2);
				product_cost = (String) Adp.getValueAt(i, 4);
				currency_id = (String) Adp.getValueAt(i, 5);
				CurrencyHash currencyHash = CurrencyHash.getInstance();
				currency_desc = currencyHash.getCurrency_decs(currency_id);
				// Currency curr = CurrencyHash.getCurrency(currency_id);
				// if(curr == null) throw new
				// java.lang.UnsupportedOperationException("Currency curr == null
				// ");
				// currency_cd = curr.getCode();
				// currency_cd = curr.getCode();

				table.append("<product>\n");

				table.append("<rigth>\n");
				table.append("<product_id>" + rows[i][0] + "</product_id>\n");
				table.append("<row_id>" + i + "</row_id>\n");
				table.append("<name>" + product_name + "</name>\n");
				table.append("<icon>" + product_iconurl + "</icon>\n");
				table.append("<image></image>\n");
				// Referece to pruduct
				table.append("<policy_url>" + product_url + "</policy_url>\n");
				table.append("<description>" + product_description + "</description>\n");
				// table.append("<fulldescription>" + product_fulldescription +
				// "</fulldescription>\n") ;
				table.append("<amount>" + product_cost + "</amount>\n");
				table.append("<currency>\n");
				table.append("<code>" + currency_cd + "</code>\n");
				table.append("<description>dollar us</description>\n");
				table.append("</currency>\n");
				table.append("<version>" + currency_desc + "</version>\n");
				table.append("</rigth>\n");

				if (Adp.rows().size() > (i + 1)) {
					product_url2 = "Policy.jsp?row=" + (i + 1);
					rows[i + 1][0] = (String) Adp.getValueAt(i + 1, 0);
					rows[i + 1][1] = Adp.getValueAt(i + 1, 7) == null ? "" : Adp.getValueAt(i + 1, 7);
					// rows[i+1][1] = (String)Adp.getValueAt(i+1,7) ;
					product_name2 = (String) Adp.getValueAt(i + 1, 1);
					img_url2 = (String) Adp.getValueAt(i + 1, 13);
					if (img_url2 != null)
						product_iconurl2 = img_url2;
					else
						product_iconurl2 = "images/Folder.jpg";
					product_description2 = (String) Adp.getValueAt(i + 1, 2);
					product_cost2 = (String) Adp.getValueAt(i + 1, 4);
					currency_id2 = (String) Adp.getValueAt(i + 1, 5);
					currency_desc2 = currencyHash.getCurrency_decs(currency_id2);
					table.append("<left>\n");
					table.append("<product_id>" + rows[i + 1][0] + "</product_id>\n");
					table.append("<row_id>" + (i + 1) + "</row_id>\n");
					table.append("<name>" + product_name2 + "</name>\n");
					table.append("<icon>" + product_iconurl2 + "</icon>\n");
					table.append("<image></image>\n");
					table.append("<policy_url>" + product_url2 + "</policy_url>\n");
					table.append("<description>" + product_description2 + "</description>\n");
					table.append("<amount>" + product_cost2 + "</amount>\n");
					table.append("<currency>\n");
					table.append("<code>" + currency_cd + "</code>\n");
					table.append("<description>dollar us</description>\n");
					table.append("</currency>\n");
					table.append("<version>" + currency_desc2 + "</version>\n");
					table.append("</left>\n");
				}

				table.append("</product>\n");
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

}