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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;


import com.cbsinc.cms.QueryManager;

import org.apache.log4j.Logger;

import com.cbsinc.cms.faceds.ApplicationContext;



public class Catalog_addBean implements java.io.Serializable  { 

	
	
	private static final long serialVersionUID = -7221967667229682339L;

	static private Logger log = Logger.getLogger(Catalog_addBean.class);

	private String query;

	private String name = "0";

	private Integer indx_select = 0;

	private String holddate = "0";
	
	
	transient ResourceBundle sequences_rs = null ;
	transient ResourceBundle localization = null ;
	
	public Catalog_addBean() 
	{
		if( sequences_rs == null )  sequences_rs = PropertyResourceBundle.getBundle("sequence");
		if( localization == null )  localization = PropertyResourceBundle.getBundle("localization");

	}
	
	public Catalog_addBean(Locale locale) 
	{
		if( sequences_rs == null )  sequences_rs = PropertyResourceBundle.getBundle("sequence");
		if( localization == null )  localization = PropertyResourceBundle.getBundle("localization", locale);

	}
	
	public void addCatalog(AuthorizationPageBean authorizationPageBeanId) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		query = sequences_rs.getString("catalog");
		//query = "SELECT NEXT VALUE FOR catalog_catalog_id_seq  AS ID  FROM ONE_SEQUENCES";
		
		try 
		{
		   Adp.executeQuery(query);
		   String catalog_id = Adp.getValueAt(0, 0);
		   query = "insert into catalog (catalog_id , parent_id , site_id , tax , lable , lang_id ,active ) "
				+ " values ( ? , ? , ? , ? , ? , ? , ? ) " ;

		   Map args = new HashMap();
			args.put("catalog_id" , Long.valueOf(catalog_id)  );
			args.put("parent_id" ,  Long.valueOf(authorizationPageBeanId.getCatalogParent_id())  );
			args.put("site_id" ,  Long.valueOf(authorizationPageBeanId.getSite_id())  );
			args.put("tax" ,  1  );
			args.put("lable" ,  name );
			args.put("lang_id" ,  authorizationPageBeanId.getLang_id() );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
        {
    		Adp.close();        	
        }

		return;
	}

	
	public String  getAddForm( String _name  )
	{
		
		//localization.getString("add_catalog") 
		 
		StringBuffer buff = new StringBuffer();
		buff.append("<form method=\"post\"   name=\"catalog_add\"  ACTION=\"ProductPostCre.jsp\" >\n");
		buff.append("<table>\n");	
		buff.append("<tbody>\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"action\"  value = \"add\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"text\" name=\"name\"  value = " +  _name + " />\n");
		buff.append("<TR><TD></TD><TD><input type=\"submit\" name=\"submit\"  value = \""+localization.getString("save") +"\" />\n");
		buff.append("</tbody>\n");
		buff.append("</table>\n");
		buff.append("</form>\n");
		return buff.toString();
	}	
	

	public String  getAddFormWhere( String title , ResourceBundle localization )
	{
		
		StringBuffer buff = new StringBuffer();
		
		buff.append("<h1>"+localization.getString("add_catalog") +" "+title+"</h1><br/> \n");
		buff.append("<div class='box'>\n");
		buff.append("<div class='body'>\n");
		buff.append("<div>\n");
		buff.append("<form method=\"post\"   name=\"catalog_add\"  ACTION=\"ProductPostCre.jsp\" >\n");
		buff.append("<table>\n");	
		buff.append("<tbody>\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"action\"  value = \"add\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"text\" name=\"name\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"submit\" name=\"submit\"  value = \""+localization.getString("save") +"\" />\n");
		buff.append("</tbody>\n");
		buff.append("</table>\n");
		buff.append("</form>\n");
		buff.append("</div>\n");
		buff.append("</div>\n");
		buff.append("</div>\n");
		return buff.toString();
	}	
	
	
	public String  getAddUserCatalog( String title )
	{
		
		
		 
		StringBuffer buff = new StringBuffer();
		
		buff.append("<h1>"+localization.getString("add_catalog") +" "+title+"</h1><br/> \n");
		buff.append("<div class='box'>\n");
		buff.append("<div class='body'>\n");
		buff.append("<div>\n");
		buff.append("<form method=\"post\"   name=\"catalog_add\"  ACTION=\"ProductUserPost.jsp\" >\n");
		buff.append("<table>\n");	
		buff.append("<tbody>\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"action\"  value = \"add\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"text\" name=\"name\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"submit\" name=\"submit\"  value = \""+localization.getString("save") +"\" />\n");
		buff.append("</tbody>\n");
		buff.append("</table>\n");
		buff.append("</form>\n");
		buff.append("</div>\n");
		buff.append("</div>\n");
		buff.append("</div>\n");
		return buff.toString();
	}	
	
	
	
	public String  getAddForm()
	{
		
		
		 
		StringBuffer buff = new StringBuffer();
		
		buff.append("<h1>"+localization.getString("add_catalog") +" </h1><br/> \n");
		buff.append("<div class='box'>\n");
		buff.append("<div class='body'>\n");
		buff.append("<div>\n");
		buff.append("<form method=\"post\"   name=\"catalog_add\"  ACTION=\"ProductPostCre.jsp\" >\n");
		buff.append("<table>\n");	
		buff.append("<tbody>\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"action\"  value = \"add\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"text\" name=\"name\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"submit\" name=\"submit\"  value = \""+localization.getString("save") +"\" />\n");
		buff.append("</tbody>\n");
		buff.append("</table>\n");
		buff.append("</form>\n");
		buff.append("</div>\n");
		buff.append("</div>\n");
		buff.append("</div>\n");
		return buff.toString();
	}	
	
	public String  getAddFormWithJsp(String jspPage)
	{
		
		
		 
		StringBuffer buff = new StringBuffer();
		buff.append("<form method=\"post\"   name=\"catalog_add\"  ACTION=\""+jspPage+"\" >\n");
		buff.append("<table>\n");	
		buff.append("<tbody>\n");
		buff.append("<TR><TD></TD><TD><input type=\"hidden\" name=\"action\"  value = \"add\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"text\" name=\"name\"  />\n");
		buff.append("<TR><TD></TD><TD><input type=\"submit\" name=\"submit\"  value = \""+localization.getString("add_catalog") +"\" />\n");
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

	public String getHolddate() {
		return holddate;
	}

	public void setHolddate(String holddate) {
		this.holddate = holddate;
	}




}
