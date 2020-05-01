package com.cbsinc.cms.faceds;

import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.io.*;



import org.apache.log4j.Logger;


import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.ItemDescriptionBean;
import com.cbsinc.cms.PostType;
import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.PublisherBean;

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


public class ProductPostAllFaced extends com.cbsinc.cms.WebControls implements
		java.io.Serializable {

	
	private static final long serialVersionUID = 4142951782238438413L;
	
	transient final ResourceBundle sequences_rs = PropertyResourceBundle.getBundle("sequence");
	transient final ResourceBundle setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources" );
	//Calendar calendar ;

	
	public ProductPostAllFaced() 
	{
	}
	
	/**
	 * 
	 */


	 static private Logger log = Logger.getLogger(PublisherBean.class);

	

	

	

	final public void initPage(final String _soft_id , final PublisherBean publisherBeanId , final AuthorizationPageBean authorizationPageBeanId ) throws UnsupportedEncodingException {

	
		
		/*
		 * if( publisherBeanId.position_cd == 0 ) return false ; if( publisherBeanId.cost == 0 )
		 * return false ; if( publisherBeanId.currency_cd == 0 ) return false ; if(
		 * publisherBeanId.countposition == 0 ) return false ; if(
		 * publisherBeanId.deliverylength_ofday == 0 ) return false ; if( publisherBeanId.producer_cd ==
		 * 0 ) return false ;
		 */
		
		
		/*
		 * 
		 * 	+ " amount1 = " + publisherBeanId.getAmount1() + " , "
		 *	+ " amount2 = " + publisherBeanId.getAmount2() + " , "
		 *	+ " amount3 = " + publisherBeanId.getAmount3() + " , "
		 *	+ " search2 = '" + search2 + "' , "
		 *	+ " name2 = '" + publisherBeanId.getStrSoftName2() + "' , "
		 *	+ " show_rating1 = " + publisherBeanId.getStrShow_ratimg1() + " , "
		 *	+ " show_rating2 = " + publisherBeanId.getStrShow_ratimg1() + " , "
		 *	+ " show_rating3 = " + publisherBeanId.getStrShow_ratimg1() + " , "
		 *	+ " show_blog = " + publisherBeanId.getStrShow_forum() + " , "
		 *	+ " jsp_url = '" + publisherBeanId.getJsp_url() + "' , "
		 * 
		 */
		
		/*
		 * 
		 * 		String query = "select soft_id , name , description , fulldescription ,  version , "
		 *		+ " cost ,  currency ,  serial_nubmer  ,  file_id ,  catalog_id , "
		 *		+ " image_id ,  bigimage_id , user_id ,  phonemodel_id ,  salelogic_id , "
		 *		+ " site_id ,  product_code ,   portlettype_id , creteria1_id , "
		 *		+ " creteria2_id , creteria3_id , creteria4_id , creteria5_id , creteria6_id , "
		 *		+ " creteria7_id , creteria8_id , creteria9_id , "
		 *		+ " creteria10_id from soft where soft_id=" + _soft_id;
		 *
		 */
		
		QueryManager Adp = new QueryManager();
		//Adp.BeginTransaction();
		String query = "select soft.soft_id , soft.name , soft.description , soft.fulldescription ,  soft.version , "
				+ " soft.cost ,  soft.currency ,  soft.serial_nubmer  ,  soft.file_id ,  soft.catalog_id , "
				+ " soft.image_id ,  soft.bigimage_id , soft.user_id ,  soft.phonemodel_id ,  soft.salelogic_id , "
				+ " soft.site_id ,  soft.product_code ,   soft.portlettype_id , soft.creteria1_id , "
				+ " soft.creteria2_id , soft.creteria3_id , soft.creteria4_id , soft.creteria5_id , soft.creteria6_id , "
				+ " soft.creteria7_id , soft.creteria8_id , soft.creteria9_id , "
				+ " soft.creteria10_id , soft.amount1 , soft.amount2 , soft.amount3 , " 
				+ " soft.search2 , soft.name2 , soft.show_rating1 , soft.show_rating2 , soft.show_rating3 , " 
				+ " soft.show_blog , soft.jsp_url , images.imgname as imgname_s ,  big_images.imgname as imgname_b  , file.name , soft.type_id ,  soft1.portlettype_id  from soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN soft soft1 ON soft.soft_id = soft1.tree_id LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  where soft.soft_id=" + _soft_id;
		try {
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0) {
				publisherBeanId.setSoft_id((String) Adp.getValueAt(0, 0));
				publisherBeanId.setStrSoftName((String) Adp.getValueAt(0, 1));
				publisherBeanId.setStrSoftDescription((String) Adp.getValueAt(0, 2));
				publisherBeanId.setProduct_fulldescription((String) Adp.getValueAt(0, 3));
				publisherBeanId.setStrSoftVersion((String) Adp.getValueAt(0, 4));
				publisherBeanId.setStrSoftCost((String) Adp.getValueAt(0, 5));
				publisherBeanId.setStrCurrency((String) Adp.getValueAt(0, 6));
				publisherBeanId.setSerial_nubmer((String) Adp.getValueAt(0, 7));
				publisherBeanId.setFile_id((String) Adp.getValueAt(0, 8));
				authorizationPageBeanId.setCatalog_id((String) Adp.getValueAt(0, 9));
				authorizationPageBeanId.setCatalogParent_id((String) Adp.getValueAt(0, 9));
				//authorizationPageBeanId.setCatalog_parent_id(getCatalogParentId(authorizationPageBeanId)); // fixed for open editor in dir
				publisherBeanId.setImage_id((String) Adp.getValueAt(0, 10));
				publisherBeanId.setBigimage_id((String) Adp.getValueAt(0, 11));
				publisherBeanId.setUser_id((String) Adp.getValueAt(0, 12));
				publisherBeanId.setSalelogic_id((String) Adp.getValueAt(0, 14));
				publisherBeanId.setSite_id((String) Adp.getValueAt(0, 15));
				publisherBeanId.setProduct_code_id((String) Adp.getValueAt(0, 16));
				publisherBeanId.setPortlettype_id((String) Adp.getValueAt(0, 17));
				publisherBeanId.setCreteria1_id((String) Adp.getValueAt(0, 18));
				publisherBeanId.setCreteria2_id((String) Adp.getValueAt(0, 19));
				publisherBeanId.setCreteria3_id((String) Adp.getValueAt(0, 20));
				publisherBeanId.setCreteria4_id((String) Adp.getValueAt(0, 21));
				publisherBeanId.setCreteria5_id((String) Adp.getValueAt(0, 22));
				publisherBeanId.setCreteria6_id((String) Adp.getValueAt(0, 23));
				publisherBeanId.setCreteria7_id((String) Adp.getValueAt(0, 24));
				publisherBeanId.setCreteria8_id((String) Adp.getValueAt(0, 25));
				publisherBeanId.setCreteria9_id((String) Adp.getValueAt(0, 26));
				publisherBeanId.setCreteria10_id((String) Adp.getValueAt(0, 27));
				
				publisherBeanId.setAmount1((String) Adp.getValueAt(0, 28)) ;
				publisherBeanId.setAmount2((String) Adp.getValueAt(0, 29)) ;
				publisherBeanId.setAmount3((String) Adp.getValueAt(0, 30)) ;
				publisherBeanId.setStrSearch2((String) Adp.getValueAt(0, 31));				
				publisherBeanId.setStrSoftName2((String) Adp.getValueAt(0, 32)) ;
				publisherBeanId.setStrShow_ratimg1((String) Adp.getValueAt(0, 33)) ;
				publisherBeanId.setStrShow_ratimg2((String) Adp.getValueAt(0, 34)) ;
				publisherBeanId.setStrShow_ratimg2((String) Adp.getValueAt(0, 35)) ;
				publisherBeanId.setStrShow_forum((String) Adp.getValueAt(0, 36)) ;
				publisherBeanId.setJsp_url((String) Adp.getValueAt(0, 37)) ;

				publisherBeanId.setImgname((String) Adp.getValueAt(0, 38)) ;
				publisherBeanId.setBigimgname((String) Adp.getValueAt(0, 39)) ;
				publisherBeanId.setFilename((String) Adp.getValueAt(0, 40)) ;
				publisherBeanId.setType_id((String) Adp.getValueAt(0, 41));
				publisherBeanId.setParent_portlettype_id((String) Adp.getValueAt(0, 42));
			}
			else 
			{
				// add current catalog
				publisherBeanId.setSoft_id("-1");
				authorizationPageBeanId.setCatalogParent_id(authorizationPageBeanId.getCatalog_id());
			}

		}
		catch (SQLException ex) 
		{
			//Adp.rollback();
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			//Adp.rollback();
			log.error(ex);
		}
		finally
		{
			Adp.close();
		}


		if ( publisherBeanId.getImage_id().length() > 0)
			setImageNameByImage_ID(publisherBeanId.getImage_id(),publisherBeanId);
		if (publisherBeanId.getBigimage_id().length() > 0)
			setBigImageNameByImage_ID(publisherBeanId.getBigimage_id(), publisherBeanId);
		if (publisherBeanId.getFile_id().length() > 0)
			setFileNameByFile_ID(publisherBeanId.getFile_id(),publisherBeanId);
	}

	
	final public String getCatalogParentId(final AuthorizationPageBean authorizationPageBeanId) {
		QueryManager tmpAdp = new QueryManager();
		String query = "";
		String  _parent_id = "0" ;
		try 
		{
			
				query = "select a.catalog_id  from catalog a , catalog  b  where   b.parent_id = a.catalog_id  and b.catalog_id = " + authorizationPageBeanId.getCatalog_id()  ;
				tmpAdp.executeQuery(query);
				if ( tmpAdp.rows().size() > 0) 
				{
				    if( ((String) tmpAdp.getValueAt(0, 0)).length() > 0 ) _parent_id =  tmpAdp.getValueAt(0, 0);;
				}
		
				//authorizationPageBeanId.setCatalogParent_id( _parent_id < 0?"0":"" +_parent_id ) ;		
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
			tmpAdp.close();
		}
		
		return _parent_id  ;
	}
	
	final public String updateDescSoft(final PublisherBean publisherBeanId, final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String id = publisherBeanId.getSoft_id();
		String query = "" ;
		Map args = Adp.getArgs();
		if (publisherBeanId.getSoft_id().compareTo("-1") == 0) return "";
		
		if(publisherBeanId.getStrSoftName2() != null && publisherBeanId.getStrSoftName2().length() > 0 )  
			publisherBeanId.setStrSearch2(publisherBeanId.getStrSoftName2().substring(0, 1));
		
		//if( publisherBeanId.getType_id().compareTo("0") == 0 || publisherBeanId.getType_id().length() == 0 
		//		|| publisherBeanId.getType_id().compareTo("2") == 0   )
		
		try 
		{
	
			query = " update soft set soft_id = ?, " 
				+ " name = ?, "
				+ " description = ?, "
				+ " fulldescription = ?, "
				+ " version = ?, " 
				+ " cost = ?, " 
				+ " currency = ?, "
				+ " file_id = ?, " 
				+ " catalog_id = ?, " 
				+ " image_id = ?, "
				+ " bigimage_id = ?, " 
				+ " salelogic_id = ?, "
				+ " site_id = ?, " 
				+ " product_code = ?, " 
				+ " search = ?, "
				+ " type_id = ? , "
				+ " portlettype_id = ? , "
				+ " creteria1_id = ? , "
				+ " creteria2_id = ? , "
				+ " creteria3_id = ? , "
				+ " creteria4_id = ? , "
				+ " creteria5_id = ? , "
				+ " creteria6_id = ? , "
				+ " creteria7_id = ? , "
				+ " creteria8_id = ? , "
				+ " creteria9_id = ? , "
				+ " creteria10_id = ? , "
				+ " amount1 = ? , "
				+ " amount2 = ? , "
				+ " amount3 = ? , "
				+ " search2 = ? , "
				+ " name2 = ? , "
				+ " SHOW_RATING1 = ? , "
				+ " SHOW_RATING2 = ? , "
				+ " SHOW_RATING3 = ? , "
				+ " SHOW_BLOG = ? , "
				+ " lang_id = ? , "
				+ " jsp_url = ? , "
				+ " CDATE = ? "
				+ " where soft_id = " + publisherBeanId.getSoft_id() ;
		
		
		args = Adp.getArgs();
		args.put("soft_id" , Long.valueOf(publisherBeanId.getSoft_id()));
		args.put("name" , publisherBeanId.getStrSoftName());
		args.put("description" , publisherBeanId.strSoftDescription );
		args.put("fulldescription", publisherBeanId.product_fulldescription );
		args.put("version" , publisherBeanId.getStrSoftVersion() );
		args.put("cost"	, Double.valueOf(publisherBeanId.getStrSoftCost()) );
		args.put("currency" , Long.valueOf(publisherBeanId.getStrCurrency()) );
		args.put("file_id" , Long.valueOf(publisherBeanId.getFile_id() ));
		args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
		args.put("image_id" , Long.valueOf(publisherBeanId.getImage_id()));
		args.put("bigimage_id" , Long.valueOf(publisherBeanId.getBigimage_id() ));
		args.put("salelogic_id" , Long.valueOf(publisherBeanId.getSalelogic_id()) );
		args.put("site_id" , Long.parseLong(publisherBeanId.getSite_id()) ); 
		args.put("product_code" , Long.valueOf(publisherBeanId.getProduct_code_id() )); 
		args.put("search", publisherBeanId.getStrSoftName().substring(0, 1) );
		args.put("type_id" , Long.valueOf(publisherBeanId.getType_id()) );
		args.put("portlettype_id" ,Long.valueOf(publisherBeanId.getPortlettype_id()) );
		args.put("creteria1_id" , Long.valueOf(publisherBeanId.getCreteria1_id()));
		args.put("creteria2_id" , Long.valueOf(publisherBeanId.getCreteria2_id()));
		args.put("creteria3_id" , Long.valueOf(publisherBeanId.getCreteria3_id()));
		args.put("creteria4_id" , Long.valueOf(publisherBeanId.getCreteria4_id()));
		args.put("creteria5_id" , Long.valueOf(publisherBeanId.getCreteria5_id()));
		args.put("creteria6_id" , Long.valueOf(publisherBeanId.getCreteria6_id()));
		args.put("creteria7_id" , Long.valueOf(publisherBeanId.getCreteria7_id()));
		args.put("creteria8_id" , Long.valueOf(publisherBeanId.getCreteria8_id()));
		args.put("creteria9_id" , Long.valueOf(publisherBeanId.getCreteria9_id()));
		args.put("creteria10_id" , Long.valueOf(publisherBeanId.getCreteria10_id()));
		args.put("amount1" , Double.parseDouble(publisherBeanId.getAmount1()));
		args.put("amount2" , Double.parseDouble(publisherBeanId.getAmount2()));
		args.put("amount3" , Double.parseDouble(publisherBeanId.getAmount3()));
		args.put("search2" , publisherBeanId.getStrSearch2() );
		args.put("name2" , publisherBeanId.getStrSoftName2() );
		args.put("SHOW_RATING1" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg1()));
		args.put("SHOW_RATING2" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg2()));
		args.put("SHOW_RATING3" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg3()));
		args.put("SHOW_BLOG" , Boolean.valueOf(publisherBeanId.getStrShow_forum()));
		args.put("lang_id" ,authorizationPageBeanId.getLang_id());
		args.put("jsp_url" , publisherBeanId.getJsp_url() );
		args.put("CDATE", new java.util.Date() );
		
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			Adp.rollback();
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			Adp.rollback();
			log.error(ex);
		}
		finally
		{
			Adp.close();
		}
		
		publisherBeanId.setSoft_id("-1");
		return id;
	}

	
	final public String updateRowWithParent(final String tree_id , final PublisherBean publisherBeanId , final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String id = publisherBeanId.getSoft_id();
		String query = "" ;
		Map args = Adp.getArgs();
		if (publisherBeanId.getSoft_id().compareTo("-1") == 0) return "";
		
		
		if(publisherBeanId.getStrSoftName2() != null && publisherBeanId.getStrSoftName2().length() > 0 )  
			publisherBeanId.setStrSearch2(publisherBeanId.getStrSoftName2().substring(0, 1));
	
		
		//if( publisherBeanId.getType_id().compareTo("0") == 0 || publisherBeanId.getType_id().length() == 0 )
		//{
		query = " update soft set soft_id = ?, " 
			+ " name = ?, "
			+ " description = ?, "
			+ " fulldescription = ?, "
			+ " version = ?, " 
			+ " cost = ?, " 
			+ " currency = ?, "
			+ " file_id = ?, " 
			+ " catalog_id = ?, " 
			+ " image_id = ?, "
			+ " bigimage_id = ?, " 
			+ " salelogic_id = ?, "
			+ " site_id = ?, " 
			+ " product_code = ?, " 
			+ " search = ?, "
			+ " type_id = ? , "
			+ " portlettype_id = ? , "
			+ " tree_id = ? , "
			+ " creteria1_id = ? , "
			+ " creteria2_id = ? , "
			+ " creteria3_id = ? , "
			+ " creteria4_id = ? , "
			+ " creteria5_id = ? , "
			+ " creteria6_id = ? , "
			+ " creteria7_id = ? , "
			+ " creteria8_id = ? , "
			+ " creteria9_id = ? , "
			+ " creteria10_id = ? , "
			+ " amount1 = ? , "
			+ " amount2 = ? , "
			+ " amount3 = ? , "
			+ " search2 = ? , "
			+ " name2 = ? , "
			+ " SHOW_RATING1 = ? , "
			+ " SHOW_RATING2 = ? , "
			+ " SHOW_RATING3 = ? , "
			+ " SHOW_BLOG = ? , "
			+ " lang_id = ? , "
			+ " jsp_url = ? , "
			+ " CDATE = ? "
			+ " where soft_id = " + publisherBeanId.getSoft_id() ;
	
	
	args.put("soft_id" , Long.valueOf(publisherBeanId.getSoft_id()));
	args.put("name" , publisherBeanId.getStrSoftName());
	args.put("description" , publisherBeanId.strSoftDescription );
	args.put("fulldescription", publisherBeanId.product_fulldescription );
	args.put("version" , publisherBeanId.getStrSoftVersion() );
	args.put("cost"	, Double.valueOf(publisherBeanId.getStrSoftCost()) );
	args.put("currency" , Long.valueOf(publisherBeanId.getStrCurrency()) );
	args.put("file_id" , Long.valueOf(publisherBeanId.getFile_id() ));
	args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
	args.put("image_id" , Long.valueOf(publisherBeanId.getImage_id()));
	args.put("bigimage_id" , Long.valueOf(publisherBeanId.getBigimage_id() ));
	args.put("salelogic_id" , Long.valueOf(publisherBeanId.getSalelogic_id()) );
	args.put("site_id" , Long.parseLong(publisherBeanId.getSite_id()) ); 
	args.put("product_code" , Long.valueOf(publisherBeanId.getProduct_code_id() )); 
	args.put("search", publisherBeanId.getStrSoftName().substring(0, 1) );
	args.put("type_id" , Long.valueOf(publisherBeanId.getType_id()) );
	args.put("portlettype_id" ,Long.valueOf(publisherBeanId.getPortlettype_id()) );
	args.put("tree_id" ,Long.valueOf(tree_id) );
	args.put("creteria1_id" , Long.valueOf(publisherBeanId.getCreteria1_id()));
	args.put("creteria2_id" , Long.valueOf(publisherBeanId.getCreteria2_id()));
	args.put("creteria3_id" , Long.valueOf(publisherBeanId.getCreteria3_id()));
	args.put("creteria4_id" , Long.valueOf(publisherBeanId.getCreteria4_id()));
	args.put("creteria5_id" , Long.valueOf(publisherBeanId.getCreteria5_id()));
	args.put("creteria6_id" , Long.valueOf(publisherBeanId.getCreteria6_id()));
	args.put("creteria7_id" , Long.valueOf(publisherBeanId.getCreteria7_id()));
	args.put("creteria8_id" , Long.valueOf(publisherBeanId.getCreteria8_id()));
	args.put("creteria9_id" , Long.valueOf(publisherBeanId.getCreteria9_id()));
	args.put("creteria10_id" , Long.valueOf(publisherBeanId.getCreteria10_id()));
	args.put("amount1" , Double.parseDouble(publisherBeanId.getAmount1()));
	args.put("amount2" , Double.parseDouble(publisherBeanId.getAmount2()));
	args.put("amount3" , Double.parseDouble(publisherBeanId.getAmount3()));
	args.put("search2" , publisherBeanId.getStrSearch2() );
	args.put("name2" , publisherBeanId.getStrSoftName2() );
	args.put("SHOW_RATING1" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg1()));
	args.put("SHOW_RATING2" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg2()));
	args.put("SHOW_RATING3" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg3()));
	args.put("SHOW_BLOG" , Boolean.valueOf(publisherBeanId.getStrShow_forum()));
	args.put("lang_id" ,authorizationPageBeanId.getLang_id());
	args.put("jsp_url" , publisherBeanId.getJsp_url() );
	args.put("CDATE", new java.util.Date() );
	//}	
//	if( publisherBeanId.getType_id().compareTo("2") == 0  )
//	{
//		query = " update soft set soft_id = ?, " 
//			+ " name = ?, "
//			+ " description = ?, "
//			+ " fulldescription = ?, "
//			+ " version = ?, " 
//			+ " cost = ?, " 
//			+ " currency = ?, "
//			+ " file_id = ?, " 
//			+ " catalog_id = ?, " 
//			+ " image_id = ?, "
//			+ " bigimage_id = ?, " 
//			+ " salelogic_id = ?, "
//			+ " site_id = ?, " 
//			+ " product_code = ?, " 
//			+ " search = ?, "
//			+ " type_id = ? , "
//			+ " portlettype_id = ? , "
//			+ " creteria1_id = ? , "
//			+ " creteria2_id = ? , "
//			+ " creteria3_id = ? , "
//			+ " creteria4_id = ? , "
//			+ " creteria5_id = ? , "
//			+ " creteria6_id = ? , "
//			+ " creteria7_id = ? , "
//			+ " creteria8_id = ? , "
//			+ " creteria9_id = ? , "
//			+ " creteria10_id = ? , "
//			+ " amount1 = ? , "
//			+ " amount2 = ? , "
//			+ " amount3 = ? , "
//			+ " search2 = ? , "
//			+ " name2 = ? , "
//			+ " SHOW_RATING1 = ? , "
//			+ " SHOW_RATING2 = ? , "
//			+ " SHOW_RATING3 = ? , "
//			+ " SHOW_BLOG = ? , "
//			+ " jsp_url = ? , "
//			+ " CDATE = ? "
//			+ " where soft_id = " + publisherBeanId.getSoft_id() ;
//	
//	
//	
//	args.put("soft_id" , Long.valueOf(publisherBeanId.getSoft_id()));
//	args.put("name" , publisherBeanId.getStrSoftName());
//	args.put("description" , publisherBeanId.strSoftDescription );
//	args.put("fulldescription", publisherBeanId.product_fulldescription );
//	args.put("version" , publisherBeanId.getStrSoftVersion() );
//	args.put("cost"	, Double.valueOf(publisherBeanId.getStrSoftCost()) );
//	args.put("currency" , Long.valueOf(publisherBeanId.getStrCurrency()) );
//	args.put("file_id" , Long.valueOf(publisherBeanId.getFile_id() ));
//	args.put("catalog_id", Long.valueOf(publisherBeanId.getCatalog_id()) );
//	args.put("image_id" , Long.valueOf(publisherBeanId.getImage_id()));
//	args.put("bigimage_id" , Long.valueOf(publisherBeanId.getBigimage_id() ));
//	args.put("salelogic_id" , Long.valueOf(publisherBeanId.getSalelogic_id()) );
//	args.put("site_id" , Long.parseLong(publisherBeanId.getSite_id()) ); 
//	args.put("product_code" , Long.valueOf(publisherBeanId.getProduct_code_id() )); 
//	args.put("search", publisherBeanId.getStrSoftName().substring(0, 1) );
//	args.put("type_id" , Long.valueOf(publisherBeanId.getType_id()) );
//	args.put("portlettype_id" ,Long.valueOf(publisherBeanId.getPortlettype_id()) );
//	args.put("creteria1_id" , Long.valueOf(publisherBeanId.getCreteria1_id()));
//	args.put("creteria2_id" , Long.valueOf(publisherBeanId.getCreteria2_id()));
//	args.put("creteria3_id" , Long.valueOf(publisherBeanId.getCreteria3_id()));
//	args.put("creteria4_id" , Long.valueOf(publisherBeanId.getCreteria4_id()));
//	args.put("creteria5_id" , Long.valueOf(publisherBeanId.getCreteria5_id()));
//	args.put("creteria6_id" , Long.valueOf(publisherBeanId.getCreteria6_id()));
//	args.put("creteria7_id" , Long.valueOf(publisherBeanId.getCreteria7_id()));
//	args.put("creteria8_id" , Long.valueOf(publisherBeanId.getCreteria8_id()));
//	args.put("creteria9_id" , Long.valueOf(publisherBeanId.getCreteria9_id()));
//	args.put("creteria10_id" , Long.valueOf(publisherBeanId.getCreteria10_id()));
//	args.put("amount1" , Double.parseDouble(publisherBeanId.getAmount1()));
//	args.put("amount2" , Double.parseDouble(publisherBeanId.getAmount2()));
//	args.put("amount3" , Double.parseDouble(publisherBeanId.getAmount3()));
//	args.put("search2" , publisherBeanId.getStrSearch2() );
//	args.put("name2" , publisherBeanId.getStrSoftName2() );
//	args.put("SHOW_RATING1" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg1()));
//	args.put("SHOW_RATING2" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg2()));
//	args.put("SHOW_RATING3" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg3()));
//	args.put("SHOW_BLOG" , Boolean.valueOf(publisherBeanId.getStrShow_forum()));
//	args.put("jsp_url" , publisherBeanId.getJsp_url() );
//	args.put("CDATE", new java.util.Date() );
//	}
		try 
		{
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			Adp.rollback();
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			Adp.rollback();
			log.error(ex);
		}
		finally
		{
			Adp.close();
		}

		publisherBeanId.setSoft_id("-1");
		return id;
	}

	
	final public String saveDescSoft(final PublisherBean publisherBeanId , final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		
		if(publisherBeanId.getStrSoftName2() != null && publisherBeanId.getStrSoftName2().length() > 0 )  
			publisherBeanId.setStrSearch2(publisherBeanId.getStrSoftName2().substring(0, 1));
		
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String strID = null;
		//String query = "SELECT NEXT VALUE FOR soft_id_seq  AS ID  FROM ONE_SEQUENCES";
		String query = "";
		try 
		{
			
			
			
		query = sequences_rs.getString("soft");
		Adp.executeQuery(query);
		

		strID = Adp.getValueAt(0, 0);
		//SHOW_RATING1
		query = "insert into soft ( soft_id , "
				+ " name , "
				+ " description , " 
				+ " fulldescription , "
				+ " version , "
				+ " cost , " 
				+ " currency , " 
				+ " file_id , " 
				+ " catalog_id , " 
				+ " active , "
				+ " licence_id  , " 
				+ " image_id , "
				+ " bigimage_id , " 
				+ " user_id , " 
				+ " salelogic_id ," 
				+ " site_id , " 
				+ " product_code , "
				+ " search,  " 
				+ " portlettype_id  ,  " 
				+ " type_id  ,  "				
				+ " creteria1_id ,  "
				+ " creteria2_id ,  " 
				+ " creteria3_id ,  "
				+ " creteria4_id ,  " 
				+ " creteria5_id ,  "
				+ " creteria6_id ,  " 
				+ " creteria7_id ,  "
				+ " creteria8_id ,  " 
				+ " creteria9_id ,  "
				+ " creteria10_id , " 
				+ " show_rating1 ,  "
				+ " show_rating2 ,  " 
				+ " show_rating3 ,  "
				+ " show_blog ,  " 
				+ " search2 ,  "
				+ " amount1 , " 
				+ " amount2 , "
				+ " amount3 , " 
				+ " name2 ," 
				+ " lang_id ," 
				+ " jsp_url  ) " 
				+ " VALUES ( ? , "
				+ " ? , "
				+ " ? , "
				+ " ? , " 
				+ " ? , "
				+ " ? , "
				+ " ? , " 
				+ " ? , " 
				+ " ? , "
				+ " ? , " 
				+ " ? , " 
				+ " ? , "
				+ " ? , " 
				+ " ? , " 
				+ " ? , "
				+ " ? , " 
				+ " ? , " 
				+ " ? , "
				+ " ? , " 
				+ " ? , " 
				+ " ? , "
				+ " ? , " 
				+ " ? ,  " 
				+ " ? ,  "
				+ " ? ,  " 
				+ " ? ,  " 
				+ " ? ,  "
				+ " ? ,  " 
				+ " ? ,  "
				+ " ? ,  " 
				+ " ? ,  " 
				+ " ? ,  "
				+ " ? ,  " 
				+ " ? ,  "
				+ " ? , " 
				+ " ? ,  "
				+ " ? ,  " 
				+ " ? ,  "
				+ " ? ,  "				
				+ " ? , " 
				+ " ?  )";
		
			Map args = Adp.getArgs();
			args.put("soft_id",Long.valueOf(strID) );
			args.put("name",publisherBeanId.getStrSoftName() );
			args.put("description", publisherBeanId.strSoftDescription);
			args.put("fulldescription",publisherBeanId.product_fulldescription );
			args.put("version",publisherBeanId.getStrSoftVersion() );
			args.put("cost", Double.valueOf(publisherBeanId.getStrSoftCost() ));
			args.put("currency", Long.valueOf(publisherBeanId.getStrCurrency()) );
			args.put("file_id", Long.valueOf(publisherBeanId.getFile_id()) );
			args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
			args.put("active",true);
			args.put("licence_id" ,Long.valueOf(publisherBeanId.getLicence_id()) );
			args.put("image_id",Long.valueOf(publisherBeanId.getImage_id()) );
			args.put("bigimage_id" ,Long.valueOf(publisherBeanId.getBigimage_id()));
			args.put("user_id" ,Long.valueOf(publisherBeanId.getUser_id()));
			args.put("salelogic_id" ,Long.valueOf(publisherBeanId.getSalelogic_id()));
			args.put("site_id" , Long.valueOf(authorizationPageBeanId.getSite_id()));
			args.put("product_code",Long.valueOf(publisherBeanId.getProduct_code_id()));
			args.put("search", publisherBeanId.getStrSoftName().substring(0, 1));
			args.put("portlettype_id" ,Long.valueOf(publisherBeanId.getPortlettype_id()));
			if(authorizationPageBeanId.getCatalog_id().equals("-2"))args.put("type_id" ,3);
			else args.put("type_id" ,Long.valueOf(publisherBeanId.getType_id()));
			args.put("creteria1_id",Long.valueOf(publisherBeanId.getCreteria1_id()));
			args.put("creteria2_id" ,Long.valueOf(publisherBeanId.getCreteria2_id()));
			args.put("creteria3_id",Long.valueOf(publisherBeanId.getCreteria3_id()));
			args.put("creteria4_id" ,Long.valueOf(publisherBeanId.getCreteria4_id()));
			args.put("creteria5_id",Long.valueOf(publisherBeanId.getCreteria5_id()));
			args.put("creteria6_id" ,Long.valueOf(publisherBeanId.getCreteria6_id()));
			args.put("creteria7_id",Long.valueOf(publisherBeanId.getCreteria7_id()));
			args.put("creteria8_id" ,Long.valueOf(publisherBeanId.getCreteria8_id()));
			args.put("creteria9_id",Long.valueOf(publisherBeanId.getCreteria9_id()));
			args.put("creteria10_id" ,Long.valueOf(publisherBeanId.getCreteria10_id()));
			args.put("show_rating1",Boolean.valueOf(publisherBeanId.getStrShow_ratimg1()));
			args.put("show_rating2" ,Boolean.valueOf(publisherBeanId.getStrShow_ratimg2()));
			args.put("show_rating3",Boolean.valueOf(publisherBeanId.getStrShow_ratimg3()));
			args.put("show_blog" ,Boolean.valueOf(publisherBeanId.getStrShow_forum()) );
			args.put("search2",publisherBeanId.getStrSearch2());
			args.put("amount1" ,Double.valueOf(publisherBeanId.getAmount1()));
			args.put("amount2",Double.valueOf(publisherBeanId.getAmount2()));
			args.put("amount3" ,Double.valueOf(publisherBeanId.getAmount3()));
			args.put("name2" ,publisherBeanId.getStrSoftName2());
			args.put("lang_id" ,authorizationPageBeanId.getLang_id());
			args.put("jsp_url" ,publisherBeanId.getJsp_url());
		
		
		
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			Adp.rollback();
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			Adp.rollback();
			log.error(ex);
		}
		finally
		{
			Adp.close();
		}


		return strID;
	}

	final public String insertRowWithParent(final String tree_id , final PublisherBean publisherBeanId, final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String strID = "";
		//String query = "SELECT NEXT VALUE FOR soft_id_seq  AS ID  FROM ONE_SEQUENCES";
		String query = sequences_rs.getString("soft");
		try 
		{

		Adp.executeQuery(query);

		strID = Adp.getValueAt(0, 0);

		query = "insert into soft ( soft_id , "
			+ " name , "
			+ " description , " 
			+ " fulldescription , "
			+ " version , "
			+ " cost , " 
			+ " currency , " 
			+ " file_id , " 
			+ " catalog_id , " 
			+ " active , "
			+ " licence_id  , " 
			+ " image_id , "
			+ " bigimage_id , " 
			+ " user_id , " 
			+ " salelogic_id ," 
			+ " site_id , " 
			+ " product_code , "
			+ " search,  " 
			+ " portlettype_id  ,  " 
			+ " type_id  ,  "
			+ " tree_id  ,  "
			+ " creteria1_id ,  "
			+ " creteria2_id ,  " 
			+ " creteria3_id ,  "
			+ " creteria4_id ,  " 
			+ " creteria5_id ,  "
			+ " creteria6_id ,  " 
			+ " creteria7_id ,  "
			+ " creteria8_id ,  " 
			+ " creteria9_id ,  "
			+ " creteria10_id , " 
			+ " show_rating1 ,  "
			+ " show_rating2 ,  " 
			+ " show_rating3 ,  "
			+ " show_blog ,  " 
			+ " search2 ,  "
			+ " amount1 , " 
			+ " amount2 , "
			+ " amount3 , " 
			+ " name2 ," 
			+ " lang_id ,"
			+ " jsp_url  ) " 
			+ " VALUES ( ? , "
			+ " ? , "
			+ " ? , " 
			+ " ? , "
			+ " ? , "
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? , " 
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? , " 
			+ " ? , " 
			+ " ?  )";
	
		Map args = Adp.getArgs();
		args.put("soft_id",Long.valueOf(strID) );
		args.put("name",publisherBeanId.getStrSoftName() );
		args.put("description", publisherBeanId.strSoftDescription);
		args.put("fulldescription",publisherBeanId.product_fulldescription );
		args.put("version",publisherBeanId.getStrSoftVersion() );
		args.put("cost", Double.valueOf(publisherBeanId.getStrSoftCost() ));
		args.put("currency", Long.valueOf(publisherBeanId.getStrCurrency()) );
		args.put("file_id", Long.valueOf(publisherBeanId.getFile_id()) );
		args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
		args.put("active",true);
		args.put("licence_id" ,Long.valueOf(publisherBeanId.getLicence_id()) );
		args.put("image_id",Long.valueOf(publisherBeanId.getImage_id()) );
		args.put("bigimage_id" ,Long.valueOf(publisherBeanId.getBigimage_id()));
		args.put("user_id" ,Long.valueOf(publisherBeanId.getUser_id()));
		args.put("salelogic_id" ,Long.valueOf(publisherBeanId.getSalelogic_id()));
		args.put("site_id" , Long.valueOf(publisherBeanId.getSite_id()));
		args.put("product_code",Long.valueOf(publisherBeanId.getProduct_code_id()));
		args.put("search", publisherBeanId.getStrSoftName().substring(0, 1));
		args.put("portlettype_id" ,Long.valueOf(publisherBeanId.getPortlettype_id()));
		args.put("type_id" ,Long.valueOf(publisherBeanId.getType_id()));
		args.put("tree_id" ,Long.valueOf(tree_id));
		args.put("creteria1_id",Long.valueOf(publisherBeanId.getCreteria1_id()));
		args.put("creteria2_id" ,Long.valueOf(publisherBeanId.getCreteria2_id()));
		args.put("creteria3_id",Long.valueOf(publisherBeanId.getCreteria3_id()));
		args.put("creteria4_id" ,Long.valueOf(publisherBeanId.getCreteria4_id()));
		args.put("creteria5_id",Long.valueOf(publisherBeanId.getCreteria5_id()));
		args.put("creteria6_id" ,Long.valueOf(publisherBeanId.getCreteria6_id()));
		args.put("creteria7_id",Long.valueOf(publisherBeanId.getCreteria7_id()));
		args.put("creteria8_id" ,Long.valueOf(publisherBeanId.getCreteria8_id()));
		args.put("creteria9_id",Long.valueOf(publisherBeanId.getCreteria9_id()));
		args.put("creteria10_id" ,Long.valueOf(publisherBeanId.getCreteria10_id()));
		args.put("show_rating1",Boolean.valueOf(publisherBeanId.getStrShow_ratimg1()));
		args.put("show_rating2" ,Boolean.valueOf(publisherBeanId.getStrShow_ratimg2()));
		args.put("show_rating3",Boolean.valueOf(publisherBeanId.getStrShow_ratimg3()));
		args.put("show_blog" ,Boolean.valueOf(publisherBeanId.getStrShow_forum()) );
		args.put("search2",publisherBeanId.getStrSearch2());
		args.put("amount1" ,Double.valueOf(publisherBeanId.getAmount1()));
		args.put("amount2",Double.valueOf(publisherBeanId.getAmount2()));
		args.put("amount3" ,Double.valueOf(publisherBeanId.getAmount3()));
		args.put("name2" ,publisherBeanId.getStrSoftName2());
		args.put("lang_id" ,authorizationPageBeanId.getLang_id());
		args.put("jsp_url" ,publisherBeanId.getJsp_url());
		
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			Adp.rollback();
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			Adp.rollback();
			log.error(ex);
		}
		finally
		{
			Adp.close();
		}


		return strID;
	}

	
	
	
	
	
	
	/**
	 *  Save for aprouch administrator
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	
	final public String saveInformationWithCheck(final PublisherBean publisherBeanId, final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String strID= "";
		//String query = "SELECT NEXT VALUE FOR soft_id_seq  AS ID  FROM ONE_SEQUENCES";
		String query = sequences_rs.getString("soft");
		try 
		{
		Adp.executeQuery(query);
		strID = Adp.getValueAt(0, 0);
		query = "insert into soft ( soft_id , "
			+ " name , "
			+ " description , " 
			+ " fulldescription , "
			+ " version , "
			+ " cost , " 
			+ " currency , " 
			+ " file_id , " 
			+ " catalog_id , " 
			+ " active , "
			+ " licence_id  , " 
			+ " image_id , "
			+ " bigimage_id , " 
			+ " user_id , " 
			+ " salelogic_id ," 
			+ " site_id , " 
			+ " product_code , "
			+ " search,  " 
			+ " portlettype_id  ,  " 
			+ " tree_id  ,  "
			+ " creteria1_id ,  "
			+ " creteria2_id ,  " 
			+ " creteria3_id ,  "
			+ " creteria4_id ,  " 
			+ " creteria5_id ,  "
			+ " creteria6_id ,  " 
			+ " creteria7_id ,  "
			+ " creteria8_id ,  " 
			+ " creteria9_id ,  "
			+ " creteria10_id , " 
			+ " show_rating1 ,  "
			+ " show_rating2 ,  " 
			+ " show_rating3 ,  "
			+ " show_blog ,  " 
			+ " search2 ,  "
			+ " amount1 , " 
			+ " amount2 , "
			+ " amount3 , " 
			+ " name2 ," 
			+ " lang_id ,"			
			+ " jsp_url  ) " 
			+ " VALUES ( ? , "
			+ " ? , "
			+ " ? , " 
			+ " ? , "
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? , " 
			+ " ? , "
			+ " ? , " 
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? , " 
			+ " ? ,  "
			+ " ? ,  " 
			+ " ? ,  "
			+ " ? , " 
			+ " ? , " 			
			+ " ?  )";
	
		Map args = Adp.getArgs();
		args.put("soft_id",Long.valueOf(strID) );
		args.put("name",publisherBeanId.getStrSoftName() );
		args.put("description", publisherBeanId.strSoftDescription);
		args.put("fulldescription",publisherBeanId.product_fulldescription );
		args.put("version",publisherBeanId.getStrSoftVersion() );
		args.put("cost", Double.valueOf(publisherBeanId.getStrSoftCost() ));
		args.put("currency", Long.valueOf(publisherBeanId.getStrCurrency()) );
		args.put("file_id", Long.valueOf(publisherBeanId.getFile_id()) );
		args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
		args.put("active",true);
		args.put("licence_id" ,Long.valueOf(publisherBeanId.getLicence_id()) );
		args.put("image_id",Long.valueOf(publisherBeanId.getImage_id()) );
		args.put("bigimage_id" ,Long.valueOf(publisherBeanId.getBigimage_id()));
		args.put("user_id" , Long.valueOf(authorizationPageBeanId.getIntUserID()));
		args.put("salelogic_id" ,Long.valueOf(publisherBeanId.getSalelogic_id()));
		args.put("site_id" , Long.valueOf(publisherBeanId.getSite_id()));
		args.put("product_code",Long.valueOf(publisherBeanId.getProduct_code_id()));
		args.put("search", publisherBeanId.getStrSoftName().substring(0, 1));
		args.put("portlettype_id" ,Long.valueOf(publisherBeanId.getPortlettype_id()));
		args.put("type_id" ,Long.valueOf(PostType.FOR_APROVE ));
		args.put("creteria1_id",Long.valueOf(publisherBeanId.getCreteria1_id()));
		args.put("creteria2_id" ,Long.valueOf(publisherBeanId.getCreteria2_id()));
		args.put("creteria3_id",Long.valueOf(publisherBeanId.getCreteria3_id()));
		args.put("creteria4_id" ,Long.valueOf(publisherBeanId.getCreteria4_id()));
		args.put("creteria5_id",Long.valueOf(publisherBeanId.getCreteria5_id()));
		args.put("creteria6_id" ,Long.valueOf(publisherBeanId.getCreteria6_id()));
		args.put("creteria7_id",Long.valueOf(publisherBeanId.getCreteria7_id()));
		args.put("creteria8_id" ,Long.valueOf(publisherBeanId.getCreteria8_id()));
		args.put("creteria9_id",Long.valueOf(publisherBeanId.getCreteria9_id()));
		args.put("creteria10_id" ,Long.valueOf(publisherBeanId.getCreteria10_id()));
		args.put("show_rating1",Boolean.valueOf(publisherBeanId.getStrShow_ratimg1()));
		args.put("show_rating2" ,Boolean.valueOf(publisherBeanId.getStrShow_ratimg2()));
		args.put("show_rating3",Boolean.valueOf(publisherBeanId.getStrShow_ratimg3()));
		args.put("show_blog" ,Boolean.valueOf(publisherBeanId.getStrShow_forum()) );
		args.put("search2",publisherBeanId.getStrSearch2());
		args.put("amount1" ,Double.valueOf(publisherBeanId.getAmount1()));
		args.put("amount2",Double.valueOf(publisherBeanId.getAmount2()));
		args.put("amount3" ,Double.valueOf(publisherBeanId.getAmount3()));
		args.put("name2" ,publisherBeanId.getStrSoftName2());
		args.put("lang_id" ,authorizationPageBeanId.getLang_id());
		args.put("jsp_url" ,publisherBeanId.getJsp_url());
		
		Adp.executeInsertWithArgs(query, args);
		Adp.commit();

		}
		catch (SQLException ex) 
		{
			Adp.rollback();
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			Adp.rollback();
			log.error(ex);
		}
		finally
		{
			Adp.close();
		}
		
		return strID;
	}
	
	
	final public String updateInformationWithCheck(final PublisherBean publisherBeanId, final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String id = publisherBeanId.getSoft_id();
		String query = "" ;
		if (publisherBeanId.getSoft_id().compareTo("-1") == 0)	return "";
		
		if(publisherBeanId.getStrSoftName2() != null && publisherBeanId.getStrSoftName2().length() > 0 )  
			publisherBeanId.setStrSearch2(publisherBeanId.getStrSoftName2().substring(0, 1));
	
		
		query = " update soft set soft_id = ?, " 
			+ " name = ?, "
			+ " description = ?, "
			+ " fulldescription = ?, "
			+ " version = ?, " 
			+ " cost = ?, " 
			+ " currency = ?, "
			+ " file_id = ?, " 
			+ " catalog_id = ?, " 
			+ " image_id = ?, "
			+ " bigimage_id = ?, " 
			+ " salelogic_id = ?, "
			+ " site_id = ?, " 
			+ " product_code = ?, " 
			+ " search = ?, "
			+ " type_id = ? , "
			+ " portlettype_id = ? , "
			+ " creteria1_id = ? , "
			+ " creteria2_id = ? , "
			+ " creteria3_id = ? , "
			+ " creteria4_id = ? , "
			+ " creteria5_id = ? , "
			+ " creteria6_id = ? , "
			+ " creteria7_id = ? , "
			+ " creteria8_id = ? , "
			+ " creteria9_id = ? , "
			+ " creteria10_id = ? , "
			+ " amount1 = ? , "
			+ " amount2 = ? , "
			+ " amount3 = ? , "
			+ " search2 = ? , "
			+ " name2 = ? , "
			+ " SHOW_RATING1 = ? , "
			+ " SHOW_RATING2 = ? , "
			+ " SHOW_RATING3 = ? , "
			+ " SHOW_BLOG = ? , "
			+ " lang_id = ? , "
			+ " jsp_url = ? , "
			+ " CDATE = ? "
			+ " where soft_id = " + publisherBeanId.getSoft_id() ;
	
	
	Map args = Adp.getArgs();
	args.put("soft_id" , Long.valueOf(publisherBeanId.getSoft_id()));
	args.put("name" , publisherBeanId.getStrSoftName());
	args.put("description" , publisherBeanId.strSoftDescription );
	args.put("fulldescription", publisherBeanId.product_fulldescription );
	args.put("version" , publisherBeanId.getStrSoftVersion() );
	args.put("cost"	, Double.valueOf(publisherBeanId.getStrSoftCost()) );
	args.put("currency" , Long.valueOf(publisherBeanId.getStrCurrency()) );
	args.put("file_id" , Long.valueOf(publisherBeanId.getFile_id() ));
	args.put("catalog_id", Long.valueOf( authorizationPageBeanId.getCatalog_id()) );
	args.put("image_id" , Long.valueOf(publisherBeanId.getImage_id()));
	args.put("bigimage_id" , Long.valueOf(publisherBeanId.getBigimage_id() ));
	args.put("salelogic_id" , Long.valueOf(publisherBeanId.getSalelogic_id()) );
	args.put("site_id" , Long.parseLong(publisherBeanId.getSite_id()) ); 
	args.put("product_code" , Long.valueOf(publisherBeanId.getProduct_code_id() )); 
	args.put("search", publisherBeanId.getStrSoftName().substring(0, 1) );
	args.put("type_id" , PostType.FOR_APROVE );
	args.put("portlettype_id" ,Long.valueOf(publisherBeanId.getPortlettype_id()) );
	args.put("creteria1_id" , Long.valueOf(publisherBeanId.getCreteria1_id()));
	args.put("creteria2_id" , Long.valueOf(publisherBeanId.getCreteria2_id()));
	args.put("creteria3_id" , Long.valueOf(publisherBeanId.getCreteria3_id()));
	args.put("creteria4_id" , Long.valueOf(publisherBeanId.getCreteria4_id()));
	args.put("creteria5_id" , Long.valueOf(publisherBeanId.getCreteria5_id()));
	args.put("creteria6_id" , Long.valueOf(publisherBeanId.getCreteria6_id()));
	args.put("creteria7_id" , Long.valueOf(publisherBeanId.getCreteria7_id()));
	args.put("creteria8_id" , Long.valueOf(publisherBeanId.getCreteria8_id()));
	args.put("creteria9_id" , Long.valueOf(publisherBeanId.getCreteria9_id()));
	args.put("creteria10_id" , Long.valueOf(publisherBeanId.getCreteria10_id()));
	args.put("amount1" , Double.parseDouble(publisherBeanId.getAmount1()));
	args.put("amount2" , Double.parseDouble(publisherBeanId.getAmount2()));
	args.put("amount3" , Double.parseDouble(publisherBeanId.getAmount3()));
	args.put("search2" , publisherBeanId.getStrSearch2() );
	args.put("name2" , publisherBeanId.getStrSoftName2() );
	args.put("SHOW_RATING1" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg1()));
	args.put("SHOW_RATING2" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg2()));
	args.put("SHOW_RATING3" , Boolean.valueOf(publisherBeanId.getStrShow_ratimg3()));
	args.put("SHOW_BLOG" , Boolean.valueOf(publisherBeanId.getStrShow_forum()));
	args.put("lang_id" ,authorizationPageBeanId.getLang_id());
	args.put("jsp_url" , publisherBeanId.getJsp_url() );
	args.put("CDATE", new java.util.Date() );
		
		try {
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		} catch (SQLException ex) {
			Adp.rollback();
			log.error(query,ex);
		}
		catch (Exception ex) {
			Adp.rollback();
			log.error(ex);
		}
		finally
		{
		 Adp.close();			
		}


		publisherBeanId.setSoft_id("-1");
		return id;
	}
	
//	public String saveDescSoft(String tree_id , SoftPostBean publisherBeanId)
//			throws UnsupportedEncodingException {
//
//		/*
//		 * if( publisherBeanId.position_cd == 0 ) return false ; if( publisherBeanId.cost == 0 )
//		 * return false ; if( publisherBeanId.currency_cd == 0 ) return false ; if(
//		 * publisherBeanId.countposition == 0 ) return false ; if(
//		 * publisherBeanId.deliverylength_ofday == 0 ) return false ; if( publisherBeanId.producer_cd ==
//		 * 0 ) return false ;
//		 */
//		QueryManager Adp = new QueryManager();
//		Adp.BeginTransaction();
//		String strID;
//		//String query = "SELECT NEXT VALUE FOR soft_id_seq  AS ID  FROM ONE_SEQUENCES";
//		String query = sequences_rs.getString("soft");
//		try {
//			Adp.executeQuery(query);
//		} catch (SQLException ex) {
//			Adp.rollback();
//			Adp.close();
//			log.error(ex);
//		}
//
//		strID = Adp.getValueAt(0, 0);
//
//		query = "insert into soft ( soft_id , "
//			+ " name , "
//			+ " description , " 
//			+ " fulldescription , "
//			+ " version , "
//			+ " cost , " 
//			+ " currency , " 
//			+ " file_id , " 
//			+ " catalog_id , " 
//			+ " active , "
//			+ " licence_id  , " 
//			+ " image_id , "
//			+ " bigimage_id , " 
//			+ " user_id , " 
//			+ " salelogic_id ," 
//			+ " site_id , " 
//			+ " product_code , "
//			+ " search,  " 
//			+ " portlettype_id  ,  " 
//			+ " tree_id  ,  " 
//			+ " creteria1_id ,  "
//			+ " creteria2_id ,  " 
//			+ " creteria3_id ,  "
//			+ " creteria4_id ,  " 
//			+ " creteria5_id ,  "
//			+ " creteria6_id ,  " 
//			+ " creteria7_id ,  "
//			+ " creteria8_id ,  " 
//			+ " creteria9_id ,  "
//			+ " creteria10_id , " 
//			+ " show_rating1 ,  "
//			+ " show_rating2 ,  " 
//			+ " show_rating3 ,  "
//			+ " show_blog ,  " 
//			+ " search2 ,  "
//			+ " amount1 , " 
//			+ " amount2 , "
//			+ " amount3 , " 
//			+ " name2 ," 
//			+ " jsp_url  ) " 
//			+ " VALUES ( ? , "
//			+ " ? , "
//			+ " ? , " 
//			+ " ? , "
//			+ " ? , "
//			+ " ? , " 
//			+ " ? , " 
//			+ " ? , "
//			+ " ? , "
//			+ " ? , " 
//			+ " ? , " 
//			+ " ? , "
//			+ " ? , " 
//			+ " ? , " 
//			+ " ? , "
//			+ " ? , " 
//			+ " ? , " 
//			+ " ? , "
//			+ " ? , " 
//			+ " ? , " 
//			+ " ? , "
//			+ " ? , " 
//			+ " ? ,  " 
//			+ " ? ,  "
//			+ " ? ,  " 
//			+ " ? ,  " 
//			+ " ? ,  "
//			+ " ? ,  " 
//			+ " ? ,  "
//			+ " ? ,  " 
//			+ " ? ,  " 
//			+ " ? ,  "
//			+ " ? ,  " 
//			+ " ? ,  "
//			+ " ? , " 
//			+ " ? ,  "
//			+ " ? ,  " 
//			+ " ? ,  "
//			+ " ? , " 
//			+ " ?  )";
//	
//		HashMap args = new HashMap();
//		args.put("soft_id",Long.valueOf(strID) );
//		args.put("name",publisherBeanId.getStrSoftName() );
//		args.put("description", publisherBeanId.strSoftDescription);
//		args.put("fulldescription",publisherBeanId.product_fulldescription );
//		args.put("version",publisherBeanId.getStrSoftVersion() );
//		args.put("cost", Double.valueOf(publisherBeanId.getStrSoftCost() ));
//		args.put("currency", Long.valueOf(publisherBeanId.getStrCurrency()) );
//		args.put("file_id", Long.valueOf(publisherBeanId.getFile_id()) );
//		args.put("catalog_id", Long.valueOf(publisherBeanId.getCatalog_id()) );
//		args.put("active",true);
//		args.put("licence_id" ,Long.valueOf(publisherBeanId.getLicence_id()) );
//		args.put("image_id",Long.valueOf(publisherBeanId.getImage_id()) );
//		args.put("bigimage_id" ,Long.valueOf(publisherBeanId.getBigimage_id()));
//		args.put("user_id" ,Long.valueOf(publisherBeanId.getUser_id()));
//		args.put("salelogic_id" ,Long.valueOf(publisherBeanId.getSalelogic_id()));
//		args.put("site_id" , Long.valueOf(publisherBeanId.getSite_id()));
//		args.put("product_code",Long.valueOf(publisherBeanId.getProduct_code_id()));
//		args.put("search", publisherBeanId.getStrSoftName().substring(0, 1));
//		args.put("portlettype_id" ,Long.valueOf(publisherBeanId.getPortlettype_id()));
//		args.put("tree_id" ,Long.valueOf(tree_id));
//		args.put("creteria1_id",Long.valueOf(publisherBeanId.getCreteria1_id()));
//		args.put("creteria2_id" ,Long.valueOf(publisherBeanId.getCreteria2_id()));
//		args.put("creteria3_id",Long.valueOf(publisherBeanId.getCreteria3_id()));
//		args.put("creteria4_id" ,Long.valueOf(publisherBeanId.getCreteria4_id()));
//		args.put("creteria5_id",Long.valueOf(publisherBeanId.getCreteria5_id()));
//		args.put("creteria6_id" ,Long.valueOf(publisherBeanId.getCreteria6_id()));
//		args.put("creteria7_id",Long.valueOf(publisherBeanId.getCreteria7_id()));
//		args.put("creteria8_id" ,Long.valueOf(publisherBeanId.getCreteria8_id()));
//		args.put("creteria9_id",Long.valueOf(publisherBeanId.getCreteria9_id()));
//		args.put("creteria10_id" ,Long.valueOf(publisherBeanId.getCreteria10_id()));
//		args.put("show_rating1",Boolean.valueOf(publisherBeanId.getStrShow_ratimg1()));
//		args.put("show_rating2" ,Boolean.valueOf(publisherBeanId.getStrShow_ratimg2()));
//		args.put("show_rating3",Boolean.valueOf(publisherBeanId.getStrShow_ratimg3()));
//		args.put("show_blog" ,Boolean.valueOf(publisherBeanId.getStrShow_forum()) );
//		args.put("search2",publisherBeanId.getStrSearch2());
//		args.put("amount1" ,Double.valueOf(publisherBeanId.getAmount1()));
//		args.put("amount2",Double.valueOf(publisherBeanId.getAmount2()));
//		args.put("amount3" ,Double.valueOf(publisherBeanId.getAmount3()));
//		args.put("name2" ,publisherBeanId.getStrSoftName2());
//		args.put("jsp_url" ,publisherBeanId.getJsp_url());
//		try 
//		{
//			Adp.executeInsertWithArgs(query, args);
//			Adp.commit();
//		}
//		catch (SQLException ex) 
//		{
//		  Adp.rollback();
//		  log.error(query, ex);
//		}
//		catch (Exception ex) 
//		{
//		   Adp.rollback();
//		   log.error(ex);
//		}
//		 finally
//		{
//		Adp.close();	    	
//		}
//
//		return strID;
//	}
//
	
	final public void setFileNameByFile_ID(final String File_ID , final PublisherBean publisherBeanId) {

		QueryManager Adp = new QueryManager();
		String query = "select name  from file  where  file_id  = " + File_ID;
		try 
		{
			Adp.executeQuery(query);
			if( Adp.rows().size() > 0 ) publisherBeanId.setFilename( Adp.getValueAt(0, 0));
			else publisherBeanId.setFilename("") ;
			
		}
		catch (SQLException ex) 
		{
		  log.error(query, ex);
		}
	    catch (Exception ex) 
	    {
	 	  log.error(ex);
	    }
	    finally
	    {
		Adp.close();	    	
	    }

	}

	final public void setImageNameByImage_ID(final String Image_id , final PublisherBean publisherBeanId) {

		QueryManager Adp = new QueryManager();
		String query = "SELECT imgname FROM images WHERE image_id = "+ Image_id;

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)publisherBeanId.setImgname(Adp.getValueAt(0, 0));
		} 
		catch (SQLException ex) 
		{
			  log.error(query, ex);
		}
		 catch (Exception ex) 
		{
			 log.error(ex);
		}
		finally
		{
		Adp.close();	    	
		}

	}

	final public void setBigImageNameByImage_ID(final String Image_id , final PublisherBean publisherBeanId ) {

		QueryManager Adp = new QueryManager();
		String query = "SELECT imgname FROM big_images WHERE big_images_id = " + Image_id;
		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)publisherBeanId.setBigimgname(Adp.getValueAt(0, 0));
		}
		catch (SQLException ex) 
		{
			  log.error(query, ex);
		}
		 catch (Exception ex) 
		{
			  log.error(ex);
		}
		finally
		{
		Adp.close();	    	
		}
	}

//	public String getCountActiveRow( SoftPostBean publisherBeanId ) {
//
//
//		QueryManager Adp = new QueryManager();
//		String strID = "";
//		String query = "SELECT count(soft_id) FROM soft WHERE catalog_id = ? and  active = ? ";
//		
//		Object[] args = new Object[2];
//		args[0] =  Long.valueOf(publisherBeanId.getCatalog_id()) ;
//		args[1] =  true ; 
//		
//		try 
//		{
//			Adp.executeQueryWithArgs(query,args,10,0);
//			if (Adp.rows().size() > 0)strID = Adp.getValueAt(0, 0);
//		}
//		catch (SQLException ex) 
//		{
//			 log.error(query, ex);
//		}
//		 catch (Exception ex) 
//		{
//			 log.error(ex);
//		}
//		finally
//		{
//		Adp.close();	    	
//		}
//		
//		return strID;
//	}
//	
	
	
	final public String getComboBoxWithJavaScriptBigImage(final String name,   String selected_cd, final String query , final String javascript_statment , final PublisherBean publisherBeanId ) 
	{
		String strCD = "0";
		String strLable = "Other";
		if (selected_cd == null) selected_cd = "";
		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		
		try 
		{
			Adp.executeQuery(query);
		// onChange=\"return document.forms[0].submit()\"
		table.append("<select name=\"" + name+ "\"   id=\"" + name+ "\"    " + javascript_statment + "   > \n");
		if( Adp.rows().size() > 0 ) 
		{
			strCD = (String) Adp.getValueAt(0, 0);
			strLable = (String) Adp.getValueAt(0, 1);
			publisherBeanId.setBigimage_id(strCD) ;
			publisherBeanId.setBigimgname(strLable) ;
		}
		for (int i = 0; Adp.rows().size() > i; i++) {
			// if(i==0) if(((String)selected_cd).length()==0 ) selected_cd =
			// (String)Adp.getValueAt(i,0) ;
			strCD = (String) Adp.getValueAt(i, 0);
			strLable = (String) Adp.getValueAt(i, 1);
			if (selected_cd.compareTo(strCD) == 0)
			{
				table.append("<option value=\"" + strCD + "\" selected >"	+ strLable + "\n");
				publisherBeanId.setBigimage_id(strCD) ;
				publisherBeanId.setBigimgname(strLable) ;
			}
			else
				table.append("<option value=\"" + strCD + "\">" + strLable
						+ "\n");
		}
		table.append("</select> \n");
	}
	catch (SQLException ex) {
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
	
	
	
	final public String getComboBoxWithJavaScriptSmallImage(final String name, String selected_cd, final String query , final String javascript_statment , final PublisherBean publisherBeanId )
	{
		String strCD = "0";
		String strLable = "Other";
		if (selected_cd == null) selected_cd = "";

		StringBuffer table = new StringBuffer();
		// QueryManager Adp = new QueryManager("jdbc:postgresql://192.168.0.10:5432/nnt")
		// ;
		// QueryManager Adp = new QueryManager("jdbc:postgresql://nntsport.com:5432/nnt")
		// ;
		QueryManager Adp = new QueryManager();
		try 
		{
			Adp.executeQuery(query);
		// onChange=\"return document.forms[0].submit()\"
		table.append("<select name=\"" + name+ "\"   id=\"" + name+ "\"    " + javascript_statment + "   > \n");
		if( Adp.rows().size() > 0 ) 
		{
			strCD = (String) Adp.getValueAt(0, 0);
			strLable = (String) Adp.getValueAt(0, 1);
			publisherBeanId.setImage_id(strCD) ;
			publisherBeanId.setImgname(strLable) ;
		}
		for (int i = 0; Adp.rows().size() > i; i++) 
		{

			strCD = (String) Adp.getValueAt(i, 0);
			strLable = (String) Adp.getValueAt(i, 1);
			if (selected_cd.compareTo(strCD) == 0)
			{
				table.append("<option value=\"" + strCD + "\" selected >"	+ strLable + "\n");
				publisherBeanId.setImage_id(strCD) ;
				publisherBeanId.setImgname(strLable) ;
			}
			else
				table.append("<option value=\"" + strCD + "\">" + strLable + "\n");
		}
		table.append("</select> \n");
	}
	catch (SQLException ex) {
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
	
	
	
	
	
	
	
	
	
	final public String getComboBoxAutoSubmitLocale(final String name,  String selected_cd , final String defaultLabel ,  final String query) 
	{

		String strCD = "0";
		String strLable = "Other";
		if (selected_cd == null) selected_cd = "";

		StringBuffer table = new StringBuffer();

		QueryManager Adp = new QueryManager();
		try 
		{

		Adp.executeQuery(query);

		table.append("<select name=\"" + name+ "\"   id=\"" + name+ "\"    onChange=\"doChangeCreteria('"+ name + "', this.value)\"    > \n");
		for (int i = 0; Adp.rows().size() > i; i++) 
		{

			strCD = (String) Adp.getValueAt(i, 0);
			strLable = (String) Adp.getValueAt(i, 1);
			if( strCD.equals("0") ) strLable = defaultLabel ;
			
			
			if (selected_cd.compareTo(strCD) == 0)
				
				table.append("<option value=\"" + strCD + "\" selected >"	+ strLable + "\n");
			else
				
				table.append("<option value=\"" + strCD + "\">" + strLable	+ "\n");
		}
		
		table.append("</select> \n");
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


	final public String getOneLabel(final String query) {
		String name = "";
		
		QueryManager Adp = new QueryManager();
		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0)	name = (String) Adp.getValueAt(0, 0);
		}
		catch (SQLException ex) {
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
		return name ;
	}
	
	
	
	final public boolean isLimmitPostedMessages(final AuthorizationPageBean authorizationBean, final boolean updateStatus) 
	{
		Calendar calendar = Calendar.getInstance() ;
		boolean rezult = false ;
		int limmitPostedMessages = 0 ;
		String state_limmit = "" ;
		String state_day = "" ; 
		String limmit = setup_resources.getString("limmit_posted_messages");
		if(isNumber(limmit)) limmitPostedMessages = Integer.parseInt(limmit);
		else return false ;
		
		String query = "select state  from tuser where site_id = " + authorizationBean.getSite_id();
		QueryManager Adp = new QueryManager();
		Map args = Adp.getArgs();
		//Adp.BeginTransaction();
		try {
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) 
			{
				String state_field = Adp.getValueAt(0, 0) ;
				String[] arrey  = state_field.split("_") ;
				if(state_field.equals("") || arrey.length != 2)
				{
					state_day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
					state_limmit = "0" ;
				} 
				else 
				{
					state_limmit = arrey[0] ;	
					state_day = arrey[1] ;	
				} 
				
				if(state_day.compareTo(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)))!=0)
				{
					state_limmit = "0" ;
					state_day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
				}
				
				if(isNumber( state_limmit))	authorizationBean.setNumberPostedMessages(Integer.parseInt(state_limmit)+1); // + " " +
				if(limmitPostedMessages < authorizationBean.getNumberPostedMessages() ) rezult =  true ;

				
			}
			
			if(updateStatus)
			{
			query = "update tuser set  state = ?  where site_id = " + authorizationBean.getSite_id();
			args = Adp.getArgs();
			args.put("state" , authorizationBean.getNumberPostedMessages().toString().concat("_").concat(state_day));
			Adp.executeUpdateWithArgs(query, args);
			}
			//Adp.commit();

		}
		catch (SQLException ex) {
			log.error(query,ex);
			//Adp.rollback();
		}
		catch (Exception ex) {
			log.error(ex);
			//Adp.rollback();
		}

		finally {
			Adp.close();
		}

		
		//global_has_limmit_forsite
		
	return rezult ;
	}
	
	
	public long saveSmallImgURL(String fileName, long user_id) {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String strID = null;
		String query = sequences_rs.getString("images");
		try 
		{
		
		Adp.executeQuery(query);
		strID = Adp.getValueAt(0, 0);
		query = "insert into images ( image_id ,  imgname , img_url ,  user_id ) VALUES " 
			+ "( ? ,  ? , ? ,  ? )";
		 Map args = new HashMap();
			args.put("image_id" , Long.valueOf(strID)  );
			args.put("imgname" ,  fileName  );
			args.put("img_url" ,  "imgpositions/" + strID + fileName.substring(fileName.lastIndexOf("."))  );
			args.put("user_id" ,  Long.valueOf(user_id)   );
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
			
		return Long.parseLong(strID);
	}
	
	public String deleteSmallImgURL(long image_id) {
		
		QueryManager Adp = new QueryManager();
		String path = "" ;
		Adp.BeginTransaction();
		String query = "select img_url from images where image_id = " + image_id ;
		
		try 
		{
		Adp.executeQuery(query);
		path = Adp.getValueAt(0, 0);
		query = query = "delete from images where image_id = " + image_id ;
		//Adp.executeQuery(query);
		Adp.executeUpdate(query);
		Adp.commit() ;
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
			
		return path ;
	}
	
	public long saveBigImgURL(String fileName, long user_id) {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String strID = null ;
		String query = sequences_rs.getString("big_images");
		try 
		{
		Adp.executeQuery(query);
		strID = Adp.getValueAt(0, 0);
		query = "insert into big_images ( big_images_id , imgname ,  img_url ,  user_id  )"
				+ " VALUES ( ?, ?, ? , ? )";

			
		 Map args = new HashMap();
			args.put("big_images_id" , Long.valueOf(strID)  );
			args.put("imgname" ,  fileName  );
			args.put("img_url" ,  "big_imgpositions/" + strID + fileName.substring(fileName.lastIndexOf("."))  );
			args.put("user_id" ,  Long.valueOf(user_id)   );
			Adp.executeInsertWithArgs(query, args);

			Adp.commit() ;
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
			
		return Long.parseLong(strID);
	}

	public String deleteBigImgURL(long big_images_id) {
		QueryManager Adp = new QueryManager();
		String path = "" ;
		Adp.BeginTransaction();
		String query = "select img_url from big_images where big_images_id = " + big_images_id ;
		
		try 
		{
		Adp.executeQuery(query);
		path = Adp.getValueAt(0, 0);
		query = "delete from big_images where big_images_id = " + big_images_id ;
		Adp.executeUpdate(query);
		Adp.commit() ;
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
			
		return path ;
	}
	
	public long saveFileURL(String fileName, long user_id) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String strID = "-1";
		String path ;
		String query = sequences_rs.getString("file");
		try 
		{
		
		Adp.executeQuery(query);

		strID = Adp.getValueAt(0, 0);
		
		path = this.getClass().getResource("").getPath();
		path = path.substring(0, path.indexOf("/WEB-INF/"));
		path = path.substring(1) + "/files/"  + strID +  fileName.substring(fileName.lastIndexOf(".")) ;
		
		query = "insert into file " + "(" + " file_id , " + " name , "
				+ " path , " + " user_id " + ")" + " VALUES " + "( " + strID
				+ ", '" + fileName + "', '" + path + "' , "	+ user_id + " )";

			Adp.executeUpdate(query);
			Adp.commit();
		} 
		catch (SQLException ex) 
		{
			Adp.rollback();
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			Adp.rollback();
			log.error(ex) ;
		}
		finally 
		{
		  Adp.close();
		}

		return Long.parseLong(strID);
	}
	
	
	public String deleteFileURL(long file_id) 
	{
		QueryManager Adp = new QueryManager();
		String path = "" ;
		Adp.BeginTransaction();
		String query = "select path from file where file_id = " + file_id ;
		
		try 
		{
		Adp.executeQuery(query);
		path = Adp.getValueAt(0, 0);
		query = "delete from file where file_id = " + file_id ;
		Adp.executeUpdate(query);
		Adp.commit() ;
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

		return path ;
	}
	
	
	public String getNavigator(AuthorizationPageBean authorizationPageBeanId , int offset) {
		boolean  folder = false ;
		String id = "" ;
		String name = "" ;
		StringBuffer table = new StringBuffer();
		
		QueryManager queryManager = new QueryManager();

		String query = "";

		query = "select catalog_id ,  lable   FROM catalog WHERE active = true and lang_id = " + authorizationPageBeanId.getLang_id() + " and site_id = " + authorizationPageBeanId.getSite_id() + " and parent_id = " + authorizationPageBeanId.getCatalogParent_id() +" limit 50 offset " + offset;

		try 
		{
		
		queryManager.executeQuery(query);
		
		for (int i = 0; queryManager.rows().size() > i; i++) 
		{
			id = (String) queryManager.getValueAt(i, 0);
			name = (String) queryManager.getValueAt(i, 1);
			folder = isFolder(id) ;
		
		}
	

		}
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally
		{
			queryManager.close();			
		}

		return table.toString();
	}
	

public boolean isFolder(String parent_id) 
{
	QueryManager queryManager = new QueryManager();
	String query = "";
	long count = 0 ;
	try 
	{
		
		query = "select count (catalog_id) as number  FROM catalog WHERE  parent_id  = " + parent_id  ;
		queryManager.executeQuery(query);
		if ( queryManager.rows().size() > 0) 
		{
			count =  Long.parseLong((String) queryManager.getValueAt(0, 0));
		}
	}
	catch (SQLException ex) 
	{
		log.error(query,ex) ;
	}
	catch (Exception ex) 
	{
		log.error(ex) ;
	}
	finally
	{
		queryManager.close();			
	}

return count == 0 ? false:true ;
}
	


public String getCatalogUrlPath(AuthorizationPageBean authorizationPageBeanId) 
{
	//if( localization == null )  localization = PropertyResourceBundle.getBundle("localization", locale);
	QueryManager queryManager = new QueryManager();
	String query = "";
	long  _parent_id = 0 ;
	long parent_id_last = 0 ;
	long  _catalog_id = 0 ;
	String lable = "" ;
	//String lable_last = authorizationPageBeanId.getLocalization(applicationContext).getString("section_of_list_catalog")  ;
	String lable_last = "" ;
	boolean doWhile = true ;
	StringBuffer path = new StringBuffer();
	if( authorizationPageBeanId.getCatalog_id().length() > 0 ) _parent_id =  Long.parseLong(authorizationPageBeanId.getCatalogParent_id());
    if(_parent_id ==  0 ) return lable_last ;
    String item = "" ;
	
	try 
	{
		
		while(doWhile)
		{
		//Adp.BeginTransaction();
		query = "select parent_id , lable   FROM catalog WHERE  catalog_id  = " + _parent_id  ;
		queryManager.executeQuery(query);
			if ( queryManager.rows().size() > 0) 
			{
			    if( ((String) queryManager.getValueAt(0, 0)).length() > 0 ) parent_id_last =  Long.parseLong((String) queryManager.getValueAt(0, 0));
			    lable = (String) queryManager.getValueAt(0, 1);
			    doWhile = true ;
			    //path.insert(0,"/" + lable) ;
			    item = "<a href=\"ProductPostCre.jsp?parent_id=" + _parent_id +"\" >"+"/" + lable + "</a>" ;
			    path.insert(0,item) ;
			    _parent_id = parent_id_last ;
			    
			}
			else doWhile = false ;
			//Adp.commit();
		}
	
	    item = "<a href=\"ProductPostCre.jsp?parent_id=" + parent_id_last +"\" >"+"/" + lable_last + "</a>" ;
	    path.insert(0,item) ;
	
	}
	catch (SQLException ex) 
	{
		//Adp.rollback();
		log.error(query,ex) ;
	}
	catch (Exception ex) 
	{
		//Adp.rollback();
		log.error(ex) ;
	}
	finally
	{
		queryManager.close();			
	}

return path.toString() ;
}

}
