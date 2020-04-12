package com.cbsinc.cms.faceds;

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
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: CENTER BUSINESS SOLUTIONS INC 
 * </p>
 * 
 * @author Konstantin Grabko
 * @version 1.0
 */
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.QueryManager;


public class FolderFaced implements Serializable {
	

	private static final long serialVersionUID = -195162207974958694L;
	transient ResourceBundle sequences_rs = null ;
	
	public FolderFaced() {
		if( sequences_rs == null )  sequences_rs = PropertyResourceBundle.getBundle("sequence");
	}
	
	transient static private Logger log = Logger.getLogger(FolderFaced.class);
	
	public String addFolder(final AuthorizationPageBean authorizationPageBeanId, final String name , String catalogParent_id ) 
	{
		QueryManager queryManager = new QueryManager();
		queryManager.BeginTransaction();
		String query = "";
		String catalog_id = "-1" ;
		query = sequences_rs.getString("catalog");
		//query = "SELECT NEXT VALUE FOR catalog_catalog_id_seq  AS ID  FROM ONE_SEQUENCES";
		
		try 
		{
			queryManager.executeQuery(query);
		    catalog_id = queryManager.getValueAt(0, 0);
		   query = "insert into catalog (catalog_id , parent_id , site_id , tax , lable , lang_id ,active ) "
				+ " values ( ? , ? , ? , ? , ? , ? , ? ) " ;

		   Map args = new HashMap();
			args.put("catalog_id" , Long.valueOf(catalog_id)  );
			//args.put("parent_id" ,  Long.valueOf(authorizationPageBeanId.getCatalogParent_id())  );
			args.put("parent_id" ,  Long.valueOf(catalogParent_id)  );
			args.put("site_id" ,  Long.valueOf(authorizationPageBeanId.getSite_id())  );
			args.put("tax" ,  1  );
			args.put("lable" ,  name );
			args.put("lang_id" ,  authorizationPageBeanId.getLang_id() );
			args.put("active" ,  true );
			queryManager.executeInsertWithArgs(query, args);
			queryManager.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			queryManager.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			queryManager.rollback();
		}
		finally
        {
			queryManager.close();        	
        }

		return catalog_id ;
	}

	
	public void editFolder(final AuthorizationPageBean authorizationPageBeanId , final String name ) {

		QueryManager queryManager = new QueryManager();
		queryManager.BeginTransaction();
		String query = "";
		query = "update catalog set  lable = ? , lang_id = ?  where catalog_id = " + authorizationPageBeanId.getCatalog_id() + " and site_id = " + authorizationPageBeanId.getSite_id()   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("lable" , name  );
			args.put("lang_id" ,  authorizationPageBeanId.getLang_id() );
			//args.put("parent_id" , Long.valueOf(authorizationPageBeanId.getCatalogParent_id())  );
			queryManager.executeUpdateWithArgs(query, args);
			queryManager.commit();
		} 
		catch (SQLException ex) 
		{
			queryManager.rollback();
		}
		finally
		{
			queryManager.close();			
		}

		return;
	}

	public void deleteFolder(String selectedCatalog_id, AuthorizationPageBean authorizationPageBeanId) {
		if(selectedCatalog_id.startsWith("-") || selectedCatalog_id.equals("2") ) return ;
		QueryManager queryManager = new QueryManager();
		String query = "";
		query = "delete FROM catalog WHERE site_id = " + authorizationPageBeanId.getSite_id() 
				+ " and catalog_id = " + selectedCatalog_id;
		try {
			queryManager.executeUpdate(query);
		} catch (SQLException ex) {
			System.err.println(query);
			System.err.println(ex);
			System.err.println("" + this.getClass());
			System.err.println("Method " + "delete(String catalog_id)");
		} finally {
			queryManager.close();
		}

	}

}
