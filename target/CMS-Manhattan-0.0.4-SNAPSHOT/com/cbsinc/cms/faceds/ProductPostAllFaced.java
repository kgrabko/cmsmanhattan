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
import com.cbsinc.cms.PolicyBean;
import com.cbsinc.cms.PostType;
import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.SoftPostBean;

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


	 static private Logger log = Logger.getLogger(SoftPostBean.class);

	

	

	

	final public void initPage(final String _soft_id , final SoftPostBean softPostBean , final AuthorizationPageBean authorizationPageBeanId ) throws UnsupportedEncodingException {

	
		
		/*
		 * if( softPostBean.position_cd == 0 ) return false ; if( softPostBean.cost == 0 )
		 * return false ; if( softPostBean.currency_cd == 0 ) return false ; if(
		 * softPostBean.countposition == 0 ) return false ; if(
		 * softPostBean.deliverylength_ofday == 0 ) return false ; if( softPostBean.producer_cd ==
		 * 0 ) return false ;
		 */
		
		
		/*
		 * 
		 * 	+ " amount1 = " + softPostBean.getAmount1() + " , "
		 *	+ " amount2 = " + softPostBean.getAmount2() + " , "
		 *	+ " amount3 = " + softPostBean.getAmount3() + " , "
		 *	+ " search2 = '" + search2 + "' , "
		 *	+ " name2 = '" + softPostBean.getStrSoftName2() + "' , "
		 *	+ " show_rating1 = " + softPostBean.getStrShow_ratimg1() + " , "
		 *	+ " show_rating2 = " + softPostBean.getStrShow_ratimg1() + " , "
		 *	+ " show_rating3 = " + softPostBean.getStrShow_ratimg1() + " , "
		 *	+ " show_blog = " + softPostBean.getStrShow_forum() + " , "
		 *	+ " jsp_url = '" + softPostBean.getJsp_url() + "' , "
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
				softPostBean.setSoft_id((String) Adp.getValueAt(0, 0));
				softPostBean.setStrSoftName((String) Adp.getValueAt(0, 1));
				softPostBean.setStrSoftDescription((String) Adp.getValueAt(0, 2));
				softPostBean.setProduct_fulldescription((String) Adp.getValueAt(0, 3));
				softPostBean.setStrSoftVersion((String) Adp.getValueAt(0, 4));
				softPostBean.setStrSoftCost((String) Adp.getValueAt(0, 5));
				softPostBean.setStrCurrency((String) Adp.getValueAt(0, 6));
				softPostBean.setSerial_nubmer((String) Adp.getValueAt(0, 7));
				softPostBean.setFile_id((String) Adp.getValueAt(0, 8));
				authorizationPageBeanId.setCatalog_id((String) Adp.getValueAt(0, 9));
				authorizationPageBeanId.setCatalogParent_id((String) Adp.getValueAt(0, 9));
				//authorizationPageBeanId.setCatalog_parent_id(getCatalogParentId(authorizationPageBeanId)); // fixed for open editor in dir
				softPostBean.setImage_id((String) Adp.getValueAt(0, 10));
				softPostBean.setBigimage_id((String) Adp.getValueAt(0, 11));
				softPostBean.setUser_id((String) Adp.getValueAt(0, 12));
				softPostBean.setSalelogic_id((String) Adp.getValueAt(0, 14));
				softPostBean.setSite_id((String) Adp.getValueAt(0, 15));
				softPostBean.setProduct_code_id((String) Adp.getValueAt(0, 16));
				softPostBean.setPortlettype_id((String) Adp.getValueAt(0, 17));
				softPostBean.setCreteria1_id((String) Adp.getValueAt(0, 18));
				softPostBean.setCreteria2_id((String) Adp.getValueAt(0, 19));
				softPostBean.setCreteria3_id((String) Adp.getValueAt(0, 20));
				softPostBean.setCreteria4_id((String) Adp.getValueAt(0, 21));
				softPostBean.setCreteria5_id((String) Adp.getValueAt(0, 22));
				softPostBean.setCreteria6_id((String) Adp.getValueAt(0, 23));
				softPostBean.setCreteria7_id((String) Adp.getValueAt(0, 24));
				softPostBean.setCreteria8_id((String) Adp.getValueAt(0, 25));
				softPostBean.setCreteria9_id((String) Adp.getValueAt(0, 26));
				softPostBean.setCreteria10_id((String) Adp.getValueAt(0, 27));
				
				softPostBean.setAmount1((String) Adp.getValueAt(0, 28)) ;
				softPostBean.setAmount2((String) Adp.getValueAt(0, 29)) ;
				softPostBean.setAmount3((String) Adp.getValueAt(0, 30)) ;
				softPostBean.setStrSearch2((String) Adp.getValueAt(0, 31));				
				softPostBean.setStrSoftName2((String) Adp.getValueAt(0, 32)) ;
				softPostBean.setStrShow_ratimg1((String) Adp.getValueAt(0, 33)) ;
				softPostBean.setStrShow_ratimg2((String) Adp.getValueAt(0, 34)) ;
				softPostBean.setStrShow_ratimg2((String) Adp.getValueAt(0, 35)) ;
				softPostBean.setStrShow_forum((String) Adp.getValueAt(0, 36)) ;
				softPostBean.setJsp_url((String) Adp.getValueAt(0, 37)) ;

				softPostBean.setImgname((String) Adp.getValueAt(0, 38)) ;
				softPostBean.setBigimgname((String) Adp.getValueAt(0, 39)) ;
				softPostBean.setFilename((String) Adp.getValueAt(0, 40)) ;
				softPostBean.setType_id((String) Adp.getValueAt(0, 41));
				softPostBean.setParent_portlettype_id((String) Adp.getValueAt(0, 42));
			}
			else 
			{
				// add current catalog
				softPostBean.setSoft_id("-1");
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


		if ( softPostBean.getImage_id().length() > 0)
			setImageNameByImage_ID(softPostBean.getImage_id(),softPostBean);
		if (softPostBean.getBigimage_id().length() > 0)
			setBigImageNameByImage_ID(softPostBean.getBigimage_id(), softPostBean);
		if (softPostBean.getFile_id().length() > 0)
			setFileNameByFile_ID(softPostBean.getFile_id(),softPostBean);
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
	
	final public String updateDescSoft(final SoftPostBean softPostBean, final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String id = softPostBean.getSoft_id();
		String query = "" ;
		Map args = Adp.getArgs();
		if (softPostBean.getSoft_id().compareTo("-1") == 0) return "";
		
		if(softPostBean.getStrSoftName2() != null && softPostBean.getStrSoftName2().length() > 0 )  
			softPostBean.setStrSearch2(softPostBean.getStrSoftName2().substring(0, 1));
		
		//if( softPostBean.getType_id().compareTo("0") == 0 || softPostBean.getType_id().length() == 0 
		//		|| softPostBean.getType_id().compareTo("2") == 0   )
		
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
				+ " where soft_id = " + softPostBean.getSoft_id() ;
		
		
		args = Adp.getArgs();
		args.put("soft_id" , Long.valueOf(softPostBean.getSoft_id()));
		args.put("name" , softPostBean.getStrSoftName());
		args.put("description" , softPostBean.strSoftDescription );
		args.put("fulldescription", softPostBean.product_fulldescription );
		args.put("version" , softPostBean.getStrSoftVersion() );
		args.put("cost"	, Double.valueOf(softPostBean.getStrSoftCost()) );
		args.put("currency" , Long.valueOf(softPostBean.getStrCurrency()) );
		args.put("file_id" , Long.valueOf(softPostBean.getFile_id() ));
		args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
		args.put("image_id" , Long.valueOf(softPostBean.getImage_id()));
		args.put("bigimage_id" , Long.valueOf(softPostBean.getBigimage_id() ));
		args.put("salelogic_id" , Long.valueOf(softPostBean.getSalelogic_id()) );
		args.put("site_id" , Long.parseLong(softPostBean.getSite_id()) ); 
		args.put("product_code" , Long.valueOf(softPostBean.getProduct_code_id() )); 
		args.put("search", softPostBean.getStrSoftName().substring(0, 1) );
		args.put("type_id" , Long.valueOf(softPostBean.getType_id()) );
		args.put("portlettype_id" ,Long.valueOf(softPostBean.getPortlettype_id()) );
		args.put("creteria1_id" , Long.valueOf(softPostBean.getCreteria1_id()));
		args.put("creteria2_id" , Long.valueOf(softPostBean.getCreteria2_id()));
		args.put("creteria3_id" , Long.valueOf(softPostBean.getCreteria3_id()));
		args.put("creteria4_id" , Long.valueOf(softPostBean.getCreteria4_id()));
		args.put("creteria5_id" , Long.valueOf(softPostBean.getCreteria5_id()));
		args.put("creteria6_id" , Long.valueOf(softPostBean.getCreteria6_id()));
		args.put("creteria7_id" , Long.valueOf(softPostBean.getCreteria7_id()));
		args.put("creteria8_id" , Long.valueOf(softPostBean.getCreteria8_id()));
		args.put("creteria9_id" , Long.valueOf(softPostBean.getCreteria9_id()));
		args.put("creteria10_id" , Long.valueOf(softPostBean.getCreteria10_id()));
		args.put("amount1" , Double.parseDouble(softPostBean.getAmount1()));
		args.put("amount2" , Double.parseDouble(softPostBean.getAmount2()));
		args.put("amount3" , Double.parseDouble(softPostBean.getAmount3()));
		args.put("search2" , softPostBean.getStrSearch2() );
		args.put("name2" , softPostBean.getStrSoftName2() );
		args.put("SHOW_RATING1" , Boolean.valueOf(softPostBean.getStrShow_ratimg1()));
		args.put("SHOW_RATING2" , Boolean.valueOf(softPostBean.getStrShow_ratimg2()));
		args.put("SHOW_RATING3" , Boolean.valueOf(softPostBean.getStrShow_ratimg3()));
		args.put("SHOW_BLOG" , Boolean.valueOf(softPostBean.getStrShow_forum()));
		args.put("lang_id" ,authorizationPageBeanId.getLang_id());
		args.put("jsp_url" , softPostBean.getJsp_url() );
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
		
		softPostBean.setSoft_id("-1");
		return id;
	}

	
	final public String updateRowWithParent(final String tree_id , final SoftPostBean softPostBean , final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String id = softPostBean.getSoft_id();
		String query = "" ;
		Map args = Adp.getArgs();
		if (softPostBean.getSoft_id().compareTo("-1") == 0) return "";
		
		
		if(softPostBean.getStrSoftName2() != null && softPostBean.getStrSoftName2().length() > 0 )  
			softPostBean.setStrSearch2(softPostBean.getStrSoftName2().substring(0, 1));
	
		
		//if( softPostBean.getType_id().compareTo("0") == 0 || softPostBean.getType_id().length() == 0 )
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
			+ " where soft_id = " + softPostBean.getSoft_id() ;
	
	
	args.put("soft_id" , Long.valueOf(softPostBean.getSoft_id()));
	args.put("name" , softPostBean.getStrSoftName());
	args.put("description" , softPostBean.strSoftDescription );
	args.put("fulldescription", softPostBean.product_fulldescription );
	args.put("version" , softPostBean.getStrSoftVersion() );
	args.put("cost"	, Double.valueOf(softPostBean.getStrSoftCost()) );
	args.put("currency" , Long.valueOf(softPostBean.getStrCurrency()) );
	args.put("file_id" , Long.valueOf(softPostBean.getFile_id() ));
	args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
	args.put("image_id" , Long.valueOf(softPostBean.getImage_id()));
	args.put("bigimage_id" , Long.valueOf(softPostBean.getBigimage_id() ));
	args.put("salelogic_id" , Long.valueOf(softPostBean.getSalelogic_id()) );
	args.put("site_id" , Long.parseLong(softPostBean.getSite_id()) ); 
	args.put("product_code" , Long.valueOf(softPostBean.getProduct_code_id() )); 
	args.put("search", softPostBean.getStrSoftName().substring(0, 1) );
	args.put("type_id" , Long.valueOf(softPostBean.getType_id()) );
	args.put("portlettype_id" ,Long.valueOf(softPostBean.getPortlettype_id()) );
	args.put("tree_id" ,Long.valueOf(tree_id) );
	args.put("creteria1_id" , Long.valueOf(softPostBean.getCreteria1_id()));
	args.put("creteria2_id" , Long.valueOf(softPostBean.getCreteria2_id()));
	args.put("creteria3_id" , Long.valueOf(softPostBean.getCreteria3_id()));
	args.put("creteria4_id" , Long.valueOf(softPostBean.getCreteria4_id()));
	args.put("creteria5_id" , Long.valueOf(softPostBean.getCreteria5_id()));
	args.put("creteria6_id" , Long.valueOf(softPostBean.getCreteria6_id()));
	args.put("creteria7_id" , Long.valueOf(softPostBean.getCreteria7_id()));
	args.put("creteria8_id" , Long.valueOf(softPostBean.getCreteria8_id()));
	args.put("creteria9_id" , Long.valueOf(softPostBean.getCreteria9_id()));
	args.put("creteria10_id" , Long.valueOf(softPostBean.getCreteria10_id()));
	args.put("amount1" , Double.parseDouble(softPostBean.getAmount1()));
	args.put("amount2" , Double.parseDouble(softPostBean.getAmount2()));
	args.put("amount3" , Double.parseDouble(softPostBean.getAmount3()));
	args.put("search2" , softPostBean.getStrSearch2() );
	args.put("name2" , softPostBean.getStrSoftName2() );
	args.put("SHOW_RATING1" , Boolean.valueOf(softPostBean.getStrShow_ratimg1()));
	args.put("SHOW_RATING2" , Boolean.valueOf(softPostBean.getStrShow_ratimg2()));
	args.put("SHOW_RATING3" , Boolean.valueOf(softPostBean.getStrShow_ratimg3()));
	args.put("SHOW_BLOG" , Boolean.valueOf(softPostBean.getStrShow_forum()));
	args.put("lang_id" ,authorizationPageBeanId.getLang_id());
	args.put("jsp_url" , softPostBean.getJsp_url() );
	args.put("CDATE", new java.util.Date() );
	//}	
//	if( softPostBean.getType_id().compareTo("2") == 0  )
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
//			+ " where soft_id = " + softPostBean.getSoft_id() ;
//	
//	
//	
//	args.put("soft_id" , Long.valueOf(softPostBean.getSoft_id()));
//	args.put("name" , softPostBean.getStrSoftName());
//	args.put("description" , softPostBean.strSoftDescription );
//	args.put("fulldescription", softPostBean.product_fulldescription );
//	args.put("version" , softPostBean.getStrSoftVersion() );
//	args.put("cost"	, Double.valueOf(softPostBean.getStrSoftCost()) );
//	args.put("currency" , Long.valueOf(softPostBean.getStrCurrency()) );
//	args.put("file_id" , Long.valueOf(softPostBean.getFile_id() ));
//	args.put("catalog_id", Long.valueOf(softPostBean.getCatalog_id()) );
//	args.put("image_id" , Long.valueOf(softPostBean.getImage_id()));
//	args.put("bigimage_id" , Long.valueOf(softPostBean.getBigimage_id() ));
//	args.put("salelogic_id" , Long.valueOf(softPostBean.getSalelogic_id()) );
//	args.put("site_id" , Long.parseLong(softPostBean.getSite_id()) ); 
//	args.put("product_code" , Long.valueOf(softPostBean.getProduct_code_id() )); 
//	args.put("search", softPostBean.getStrSoftName().substring(0, 1) );
//	args.put("type_id" , Long.valueOf(softPostBean.getType_id()) );
//	args.put("portlettype_id" ,Long.valueOf(softPostBean.getPortlettype_id()) );
//	args.put("creteria1_id" , Long.valueOf(softPostBean.getCreteria1_id()));
//	args.put("creteria2_id" , Long.valueOf(softPostBean.getCreteria2_id()));
//	args.put("creteria3_id" , Long.valueOf(softPostBean.getCreteria3_id()));
//	args.put("creteria4_id" , Long.valueOf(softPostBean.getCreteria4_id()));
//	args.put("creteria5_id" , Long.valueOf(softPostBean.getCreteria5_id()));
//	args.put("creteria6_id" , Long.valueOf(softPostBean.getCreteria6_id()));
//	args.put("creteria7_id" , Long.valueOf(softPostBean.getCreteria7_id()));
//	args.put("creteria8_id" , Long.valueOf(softPostBean.getCreteria8_id()));
//	args.put("creteria9_id" , Long.valueOf(softPostBean.getCreteria9_id()));
//	args.put("creteria10_id" , Long.valueOf(softPostBean.getCreteria10_id()));
//	args.put("amount1" , Double.parseDouble(softPostBean.getAmount1()));
//	args.put("amount2" , Double.parseDouble(softPostBean.getAmount2()));
//	args.put("amount3" , Double.parseDouble(softPostBean.getAmount3()));
//	args.put("search2" , softPostBean.getStrSearch2() );
//	args.put("name2" , softPostBean.getStrSoftName2() );
//	args.put("SHOW_RATING1" , Boolean.valueOf(softPostBean.getStrShow_ratimg1()));
//	args.put("SHOW_RATING2" , Boolean.valueOf(softPostBean.getStrShow_ratimg2()));
//	args.put("SHOW_RATING3" , Boolean.valueOf(softPostBean.getStrShow_ratimg3()));
//	args.put("SHOW_BLOG" , Boolean.valueOf(softPostBean.getStrShow_forum()));
//	args.put("jsp_url" , softPostBean.getJsp_url() );
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

		softPostBean.setSoft_id("-1");
		return id;
	}

	
	final public String saveDescSoft(final SoftPostBean softPostBean , final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		
		if(softPostBean.getStrSoftName2() != null && softPostBean.getStrSoftName2().length() > 0 )  
			softPostBean.setStrSearch2(softPostBean.getStrSoftName2().substring(0, 1));
		
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
			args.put("name",softPostBean.getStrSoftName() );
			args.put("description", softPostBean.strSoftDescription);
			args.put("fulldescription",softPostBean.product_fulldescription );
			args.put("version",softPostBean.getStrSoftVersion() );
			args.put("cost", Double.valueOf(softPostBean.getStrSoftCost() ));
			args.put("currency", Long.valueOf(softPostBean.getStrCurrency()) );
			args.put("file_id", Long.valueOf(softPostBean.getFile_id()) );
			args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
			args.put("active",true);
			args.put("licence_id" ,Long.valueOf(softPostBean.getLicence_id()) );
			args.put("image_id",Long.valueOf(softPostBean.getImage_id()) );
			args.put("bigimage_id" ,Long.valueOf(softPostBean.getBigimage_id()));
			args.put("user_id" ,Long.valueOf(softPostBean.getUser_id()));
			args.put("salelogic_id" ,Long.valueOf(softPostBean.getSalelogic_id()));
			args.put("site_id" , Long.valueOf(authorizationPageBeanId.getSite_id()));
			args.put("product_code",Long.valueOf(softPostBean.getProduct_code_id()));
			args.put("search", softPostBean.getStrSoftName().substring(0, 1));
			args.put("portlettype_id" ,Long.valueOf(softPostBean.getPortlettype_id()));
			if(authorizationPageBeanId.getCatalog_id().equals("-2"))args.put("type_id" ,3);
			else args.put("type_id" ,Long.valueOf(softPostBean.getType_id()));
			args.put("creteria1_id",Long.valueOf(softPostBean.getCreteria1_id()));
			args.put("creteria2_id" ,Long.valueOf(softPostBean.getCreteria2_id()));
			args.put("creteria3_id",Long.valueOf(softPostBean.getCreteria3_id()));
			args.put("creteria4_id" ,Long.valueOf(softPostBean.getCreteria4_id()));
			args.put("creteria5_id",Long.valueOf(softPostBean.getCreteria5_id()));
			args.put("creteria6_id" ,Long.valueOf(softPostBean.getCreteria6_id()));
			args.put("creteria7_id",Long.valueOf(softPostBean.getCreteria7_id()));
			args.put("creteria8_id" ,Long.valueOf(softPostBean.getCreteria8_id()));
			args.put("creteria9_id",Long.valueOf(softPostBean.getCreteria9_id()));
			args.put("creteria10_id" ,Long.valueOf(softPostBean.getCreteria10_id()));
			args.put("show_rating1",Boolean.valueOf(softPostBean.getStrShow_ratimg1()));
			args.put("show_rating2" ,Boolean.valueOf(softPostBean.getStrShow_ratimg2()));
			args.put("show_rating3",Boolean.valueOf(softPostBean.getStrShow_ratimg3()));
			args.put("show_blog" ,Boolean.valueOf(softPostBean.getStrShow_forum()) );
			args.put("search2",softPostBean.getStrSearch2());
			args.put("amount1" ,Double.valueOf(softPostBean.getAmount1()));
			args.put("amount2",Double.valueOf(softPostBean.getAmount2()));
			args.put("amount3" ,Double.valueOf(softPostBean.getAmount3()));
			args.put("name2" ,softPostBean.getStrSoftName2());
			args.put("lang_id" ,authorizationPageBeanId.getLang_id());
			args.put("jsp_url" ,softPostBean.getJsp_url());
		
		
		
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

	final public String insertRowWithParent(final String tree_id , final SoftPostBean softPostBean, final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		
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
		args.put("name",softPostBean.getStrSoftName() );
		args.put("description", softPostBean.strSoftDescription);
		args.put("fulldescription",softPostBean.product_fulldescription );
		args.put("version",softPostBean.getStrSoftVersion() );
		args.put("cost", Double.valueOf(softPostBean.getStrSoftCost() ));
		args.put("currency", Long.valueOf(softPostBean.getStrCurrency()) );
		args.put("file_id", Long.valueOf(softPostBean.getFile_id()) );
		args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
		args.put("active",true);
		args.put("licence_id" ,Long.valueOf(softPostBean.getLicence_id()) );
		args.put("image_id",Long.valueOf(softPostBean.getImage_id()) );
		args.put("bigimage_id" ,Long.valueOf(softPostBean.getBigimage_id()));
		args.put("user_id" ,Long.valueOf(softPostBean.getUser_id()));
		args.put("salelogic_id" ,Long.valueOf(softPostBean.getSalelogic_id()));
		args.put("site_id" , Long.valueOf(softPostBean.getSite_id()));
		args.put("product_code",Long.valueOf(softPostBean.getProduct_code_id()));
		args.put("search", softPostBean.getStrSoftName().substring(0, 1));
		args.put("portlettype_id" ,Long.valueOf(softPostBean.getPortlettype_id()));
		args.put("type_id" ,Long.valueOf(softPostBean.getType_id()));
		args.put("tree_id" ,Long.valueOf(tree_id));
		args.put("creteria1_id",Long.valueOf(softPostBean.getCreteria1_id()));
		args.put("creteria2_id" ,Long.valueOf(softPostBean.getCreteria2_id()));
		args.put("creteria3_id",Long.valueOf(softPostBean.getCreteria3_id()));
		args.put("creteria4_id" ,Long.valueOf(softPostBean.getCreteria4_id()));
		args.put("creteria5_id",Long.valueOf(softPostBean.getCreteria5_id()));
		args.put("creteria6_id" ,Long.valueOf(softPostBean.getCreteria6_id()));
		args.put("creteria7_id",Long.valueOf(softPostBean.getCreteria7_id()));
		args.put("creteria8_id" ,Long.valueOf(softPostBean.getCreteria8_id()));
		args.put("creteria9_id",Long.valueOf(softPostBean.getCreteria9_id()));
		args.put("creteria10_id" ,Long.valueOf(softPostBean.getCreteria10_id()));
		args.put("show_rating1",Boolean.valueOf(softPostBean.getStrShow_ratimg1()));
		args.put("show_rating2" ,Boolean.valueOf(softPostBean.getStrShow_ratimg2()));
		args.put("show_rating3",Boolean.valueOf(softPostBean.getStrShow_ratimg3()));
		args.put("show_blog" ,Boolean.valueOf(softPostBean.getStrShow_forum()) );
		args.put("search2",softPostBean.getStrSearch2());
		args.put("amount1" ,Double.valueOf(softPostBean.getAmount1()));
		args.put("amount2",Double.valueOf(softPostBean.getAmount2()));
		args.put("amount3" ,Double.valueOf(softPostBean.getAmount3()));
		args.put("name2" ,softPostBean.getStrSoftName2());
		args.put("lang_id" ,authorizationPageBeanId.getLang_id());
		args.put("jsp_url" ,softPostBean.getJsp_url());
		
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
	
	final public String saveInformationWithCheck(final SoftPostBean softPostBean, final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {

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
		args.put("name",softPostBean.getStrSoftName() );
		args.put("description", softPostBean.strSoftDescription);
		args.put("fulldescription",softPostBean.product_fulldescription );
		args.put("version",softPostBean.getStrSoftVersion() );
		args.put("cost", Double.valueOf(softPostBean.getStrSoftCost() ));
		args.put("currency", Long.valueOf(softPostBean.getStrCurrency()) );
		args.put("file_id", Long.valueOf(softPostBean.getFile_id()) );
		args.put("catalog_id", Long.valueOf(authorizationPageBeanId.getCatalog_id()) );
		args.put("active",true);
		args.put("licence_id" ,Long.valueOf(softPostBean.getLicence_id()) );
		args.put("image_id",Long.valueOf(softPostBean.getImage_id()) );
		args.put("bigimage_id" ,Long.valueOf(softPostBean.getBigimage_id()));
		args.put("user_id" , Long.valueOf(authorizationPageBeanId.getIntUserID()));
		args.put("salelogic_id" ,Long.valueOf(softPostBean.getSalelogic_id()));
		args.put("site_id" , Long.valueOf(softPostBean.getSite_id()));
		args.put("product_code",Long.valueOf(softPostBean.getProduct_code_id()));
		args.put("search", softPostBean.getStrSoftName().substring(0, 1));
		args.put("portlettype_id" ,Long.valueOf(softPostBean.getPortlettype_id()));
		args.put("type_id" ,Long.valueOf(PostType.FOR_APROVE ));
		args.put("creteria1_id",Long.valueOf(softPostBean.getCreteria1_id()));
		args.put("creteria2_id" ,Long.valueOf(softPostBean.getCreteria2_id()));
		args.put("creteria3_id",Long.valueOf(softPostBean.getCreteria3_id()));
		args.put("creteria4_id" ,Long.valueOf(softPostBean.getCreteria4_id()));
		args.put("creteria5_id",Long.valueOf(softPostBean.getCreteria5_id()));
		args.put("creteria6_id" ,Long.valueOf(softPostBean.getCreteria6_id()));
		args.put("creteria7_id",Long.valueOf(softPostBean.getCreteria7_id()));
		args.put("creteria8_id" ,Long.valueOf(softPostBean.getCreteria8_id()));
		args.put("creteria9_id",Long.valueOf(softPostBean.getCreteria9_id()));
		args.put("creteria10_id" ,Long.valueOf(softPostBean.getCreteria10_id()));
		args.put("show_rating1",Boolean.valueOf(softPostBean.getStrShow_ratimg1()));
		args.put("show_rating2" ,Boolean.valueOf(softPostBean.getStrShow_ratimg2()));
		args.put("show_rating3",Boolean.valueOf(softPostBean.getStrShow_ratimg3()));
		args.put("show_blog" ,Boolean.valueOf(softPostBean.getStrShow_forum()) );
		args.put("search2",softPostBean.getStrSearch2());
		args.put("amount1" ,Double.valueOf(softPostBean.getAmount1()));
		args.put("amount2",Double.valueOf(softPostBean.getAmount2()));
		args.put("amount3" ,Double.valueOf(softPostBean.getAmount3()));
		args.put("name2" ,softPostBean.getStrSoftName2());
		args.put("lang_id" ,authorizationPageBeanId.getLang_id());
		args.put("jsp_url" ,softPostBean.getJsp_url());
		
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
	
	
	final public String updateInformationWithCheck(final SoftPostBean softPostBean, final AuthorizationPageBean authorizationPageBeanId) throws UnsupportedEncodingException {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String id = softPostBean.getSoft_id();
		String query = "" ;
		if (softPostBean.getSoft_id().compareTo("-1") == 0)	return "";
		
		if(softPostBean.getStrSoftName2() != null && softPostBean.getStrSoftName2().length() > 0 )  
			softPostBean.setStrSearch2(softPostBean.getStrSoftName2().substring(0, 1));
	
		
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
			+ " where soft_id = " + softPostBean.getSoft_id() ;
	
	
	Map args = Adp.getArgs();
	args.put("soft_id" , Long.valueOf(softPostBean.getSoft_id()));
	args.put("name" , softPostBean.getStrSoftName());
	args.put("description" , softPostBean.strSoftDescription );
	args.put("fulldescription", softPostBean.product_fulldescription );
	args.put("version" , softPostBean.getStrSoftVersion() );
	args.put("cost"	, Double.valueOf(softPostBean.getStrSoftCost()) );
	args.put("currency" , Long.valueOf(softPostBean.getStrCurrency()) );
	args.put("file_id" , Long.valueOf(softPostBean.getFile_id() ));
	args.put("catalog_id", Long.valueOf( authorizationPageBeanId.getCatalog_id()) );
	args.put("image_id" , Long.valueOf(softPostBean.getImage_id()));
	args.put("bigimage_id" , Long.valueOf(softPostBean.getBigimage_id() ));
	args.put("salelogic_id" , Long.valueOf(softPostBean.getSalelogic_id()) );
	args.put("site_id" , Long.parseLong(softPostBean.getSite_id()) ); 
	args.put("product_code" , Long.valueOf(softPostBean.getProduct_code_id() )); 
	args.put("search", softPostBean.getStrSoftName().substring(0, 1) );
	args.put("type_id" , PostType.FOR_APROVE );
	args.put("portlettype_id" ,Long.valueOf(softPostBean.getPortlettype_id()) );
	args.put("creteria1_id" , Long.valueOf(softPostBean.getCreteria1_id()));
	args.put("creteria2_id" , Long.valueOf(softPostBean.getCreteria2_id()));
	args.put("creteria3_id" , Long.valueOf(softPostBean.getCreteria3_id()));
	args.put("creteria4_id" , Long.valueOf(softPostBean.getCreteria4_id()));
	args.put("creteria5_id" , Long.valueOf(softPostBean.getCreteria5_id()));
	args.put("creteria6_id" , Long.valueOf(softPostBean.getCreteria6_id()));
	args.put("creteria7_id" , Long.valueOf(softPostBean.getCreteria7_id()));
	args.put("creteria8_id" , Long.valueOf(softPostBean.getCreteria8_id()));
	args.put("creteria9_id" , Long.valueOf(softPostBean.getCreteria9_id()));
	args.put("creteria10_id" , Long.valueOf(softPostBean.getCreteria10_id()));
	args.put("amount1" , Double.parseDouble(softPostBean.getAmount1()));
	args.put("amount2" , Double.parseDouble(softPostBean.getAmount2()));
	args.put("amount3" , Double.parseDouble(softPostBean.getAmount3()));
	args.put("search2" , softPostBean.getStrSearch2() );
	args.put("name2" , softPostBean.getStrSoftName2() );
	args.put("SHOW_RATING1" , Boolean.valueOf(softPostBean.getStrShow_ratimg1()));
	args.put("SHOW_RATING2" , Boolean.valueOf(softPostBean.getStrShow_ratimg2()));
	args.put("SHOW_RATING3" , Boolean.valueOf(softPostBean.getStrShow_ratimg3()));
	args.put("SHOW_BLOG" , Boolean.valueOf(softPostBean.getStrShow_forum()));
	args.put("lang_id" ,authorizationPageBeanId.getLang_id());
	args.put("jsp_url" , softPostBean.getJsp_url() );
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


		softPostBean.setSoft_id("-1");
		return id;
	}
	
//	public String saveDescSoft(String tree_id , SoftPostBean softPostBean)
//			throws UnsupportedEncodingException {
//
//		/*
//		 * if( softPostBean.position_cd == 0 ) return false ; if( softPostBean.cost == 0 )
//		 * return false ; if( softPostBean.currency_cd == 0 ) return false ; if(
//		 * softPostBean.countposition == 0 ) return false ; if(
//		 * softPostBean.deliverylength_ofday == 0 ) return false ; if( softPostBean.producer_cd ==
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
//		args.put("name",softPostBean.getStrSoftName() );
//		args.put("description", softPostBean.strSoftDescription);
//		args.put("fulldescription",softPostBean.product_fulldescription );
//		args.put("version",softPostBean.getStrSoftVersion() );
//		args.put("cost", Double.valueOf(softPostBean.getStrSoftCost() ));
//		args.put("currency", Long.valueOf(softPostBean.getStrCurrency()) );
//		args.put("file_id", Long.valueOf(softPostBean.getFile_id()) );
//		args.put("catalog_id", Long.valueOf(softPostBean.getCatalog_id()) );
//		args.put("active",true);
//		args.put("licence_id" ,Long.valueOf(softPostBean.getLicence_id()) );
//		args.put("image_id",Long.valueOf(softPostBean.getImage_id()) );
//		args.put("bigimage_id" ,Long.valueOf(softPostBean.getBigimage_id()));
//		args.put("user_id" ,Long.valueOf(softPostBean.getUser_id()));
//		args.put("salelogic_id" ,Long.valueOf(softPostBean.getSalelogic_id()));
//		args.put("site_id" , Long.valueOf(softPostBean.getSite_id()));
//		args.put("product_code",Long.valueOf(softPostBean.getProduct_code_id()));
//		args.put("search", softPostBean.getStrSoftName().substring(0, 1));
//		args.put("portlettype_id" ,Long.valueOf(softPostBean.getPortlettype_id()));
//		args.put("tree_id" ,Long.valueOf(tree_id));
//		args.put("creteria1_id",Long.valueOf(softPostBean.getCreteria1_id()));
//		args.put("creteria2_id" ,Long.valueOf(softPostBean.getCreteria2_id()));
//		args.put("creteria3_id",Long.valueOf(softPostBean.getCreteria3_id()));
//		args.put("creteria4_id" ,Long.valueOf(softPostBean.getCreteria4_id()));
//		args.put("creteria5_id",Long.valueOf(softPostBean.getCreteria5_id()));
//		args.put("creteria6_id" ,Long.valueOf(softPostBean.getCreteria6_id()));
//		args.put("creteria7_id",Long.valueOf(softPostBean.getCreteria7_id()));
//		args.put("creteria8_id" ,Long.valueOf(softPostBean.getCreteria8_id()));
//		args.put("creteria9_id",Long.valueOf(softPostBean.getCreteria9_id()));
//		args.put("creteria10_id" ,Long.valueOf(softPostBean.getCreteria10_id()));
//		args.put("show_rating1",Boolean.valueOf(softPostBean.getStrShow_ratimg1()));
//		args.put("show_rating2" ,Boolean.valueOf(softPostBean.getStrShow_ratimg2()));
//		args.put("show_rating3",Boolean.valueOf(softPostBean.getStrShow_ratimg3()));
//		args.put("show_blog" ,Boolean.valueOf(softPostBean.getStrShow_forum()) );
//		args.put("search2",softPostBean.getStrSearch2());
//		args.put("amount1" ,Double.valueOf(softPostBean.getAmount1()));
//		args.put("amount2",Double.valueOf(softPostBean.getAmount2()));
//		args.put("amount3" ,Double.valueOf(softPostBean.getAmount3()));
//		args.put("name2" ,softPostBean.getStrSoftName2());
//		args.put("jsp_url" ,softPostBean.getJsp_url());
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
	
	final public void setFileNameByFile_ID(final String File_ID , final SoftPostBean softPostBean) {

		QueryManager Adp = new QueryManager();
		String query = "select name  from file  where  file_id  = " + File_ID;
		try 
		{
			Adp.executeQuery(query);
			if( Adp.rows().size() > 0 ) softPostBean.setFilename( Adp.getValueAt(0, 0));
			else softPostBean.setFilename("") ;
			
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

	final public void setImageNameByImage_ID(final String Image_id , final SoftPostBean softPostBean) {

		QueryManager Adp = new QueryManager();
		String query = "SELECT imgname FROM images WHERE image_id = "+ Image_id;

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)softPostBean.setImgname(Adp.getValueAt(0, 0));
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

	final public void setBigImageNameByImage_ID(final String Image_id , final SoftPostBean softPostBean ) {

		QueryManager Adp = new QueryManager();
		String query = "SELECT imgname FROM big_images WHERE big_images_id = " + Image_id;
		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)softPostBean.setBigimgname(Adp.getValueAt(0, 0));
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

//	public String getCountActiveRow( SoftPostBean softPostBean ) {
//
//
//		QueryManager Adp = new QueryManager();
//		String strID = "";
//		String query = "SELECT count(soft_id) FROM soft WHERE catalog_id = ? and  active = ? ";
//		
//		Object[] args = new Object[2];
//		args[0] =  Long.valueOf(softPostBean.getCatalog_id()) ;
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
	
	
	final public String getComboBoxWithJavaScriptBigImage(final String name,   String selected_cd, final String query , final String javascript_statment , final SoftPostBean softPostBean ) 
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
			softPostBean.setBigimage_id(strCD) ;
			softPostBean.setBigimgname(strLable) ;
		}
		for (int i = 0; Adp.rows().size() > i; i++) {
			// if(i==0) if(((String)selected_cd).length()==0 ) selected_cd =
			// (String)Adp.getValueAt(i,0) ;
			strCD = (String) Adp.getValueAt(i, 0);
			strLable = (String) Adp.getValueAt(i, 1);
			if (selected_cd.compareTo(strCD) == 0)
			{
				table.append("<option value=\"" + strCD + "\" selected >"	+ strLable + "\n");
				softPostBean.setBigimage_id(strCD) ;
				softPostBean.setBigimgname(strLable) ;
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
	
	
	
	final public String getComboBoxWithJavaScriptSmallImage(final String name, String selected_cd, final String query , final String javascript_statment , final SoftPostBean softPostBean )
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
			softPostBean.setImage_id(strCD) ;
			softPostBean.setImgname(strLable) ;
		}
		for (int i = 0; Adp.rows().size() > i; i++) 
		{

			strCD = (String) Adp.getValueAt(i, 0);
			strLable = (String) Adp.getValueAt(i, 1);
			if (selected_cd.compareTo(strCD) == 0)
			{
				table.append("<option value=\"" + strCD + "\" selected >"	+ strLable + "\n");
				softPostBean.setImage_id(strCD) ;
				softPostBean.setImgname(strLable) ;
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
