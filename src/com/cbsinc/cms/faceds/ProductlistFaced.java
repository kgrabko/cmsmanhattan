package com.cbsinc.cms.faceds;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


import org.apache.log4j.Logger;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.ItemDescriptionBean;
import com.cbsinc.cms.ProductlistBean;
import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.SearchBean;
import com.cbsinc.cms.controllers.Layout;
import com.cbsinc.cms.controllers.SpecialCatalog;

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

public class ProductlistFaced extends com.cbsinc.cms.WebControls implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6384127888928704724L;

	/**
	 * 
	 */
	final static private Logger log = Logger.getLogger(ProductlistFaced.class);
	
	transient final ResourceBundle setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources" );
	
	private int limit_product_list =  10 ;
	private int limit_blog_list =  20 ;
	private int limit_co_one_list =  20 ;
	private int limit_bottom_list =  20 ;
	private int limit_co_two_list =  20 ;
	//private int limit_ext1_one_list =  20 ;
	//private int limit_ext2_two_list =  20 ;
	private int limit_news_list =  10 ;
	transient final ResourceBundle sequences_rs = PropertyResourceBundle.getBundle("sequence");
	
	
	public ProductlistFaced() 
	{
		try
		{
//		if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources" );
		//if( sequences_rs == null )  sequences_rs = PropertyResourceBundle.getBundle("sequence");
		limit_product_list =  Integer.parseInt(setup_resources.getString("limit_product_list").trim()) ;
		limit_blog_list =  Integer.parseInt(setup_resources.getString("limit_blog_list").trim()) ;
		limit_co_one_list =  Integer.parseInt(setup_resources.getString("limit_co_one_list").trim()) ;
		limit_co_two_list =  Integer.parseInt(setup_resources.getString("limit_co_two_list").trim()) ;
		///limit_ext1_one_list =  Integer.parseInt(setup_resources.getString("limit_ext1_one_list").trim()) ;
		//limit_ext2_two_list =  Integer.parseInt(setup_resources.getString("limit_ext2_two_list").trim()) ;
		limit_news_list =  Integer.parseInt(setup_resources.getString("limit_news_list").trim()) ;
		}
		catch (Exception e) 
		{
			log.error("You must add limits cretetia in file SetupApplicationResources.property" ,e);
		}
	}

	
	final public void getQuantityProducts( final ProductlistBean productlistBean) {
		
		QueryManager allqueryAdp = new QueryManager();
		productlistBean.setQuery_productlist( productlistBean.getQuery_productlist().substring(0, productlistBean.getQuery_productlist().indexOf("DESC")));
		
		try 
		{
			allqueryAdp.executeQuery(productlistBean.getQuery_productlist());
			//list =  Adp.executeQueryList(query_productlist, limit_product_list , productlistBean.getOffset());
			productlistBean.setAllFoundProducts( "" + allqueryAdp.getRowCount()) ;
		}
		catch (SQLException ex) 
		{
		log.error(productlistBean.getQuery_productlist(),ex);
		}
		catch (Exception ex) 
		{
		log.error(ex);
		}
		finally
		{
			allqueryAdp.close();
		}

	}
	
final public void getQuantitySearch( final SearchBean productlistBean) {
		
		QueryManager allqueryAdp = new QueryManager();
		productlistBean.setQuery_productlist( productlistBean.getQuery_productlist().substring(0, productlistBean.getQuery_productlist().indexOf("DESC")));
		
		try 
		{
			allqueryAdp.executeQuery(productlistBean.getQuery_productlist());
			//list =  Adp.executeQueryList(query_productlist, limit_product_list , productlistBean.getOffset());
			productlistBean.setAllFoundProducts( "" + allqueryAdp.getRowCount()) ;
		}
		catch (SQLException ex) 
		{
		log.error(productlistBean.getQuery_productlist(),ex);
		}
		catch (Exception ex) 
		{
		log.error(ex);
		}
		finally
		{
			allqueryAdp.close();
		}

	}


	
	
	
	final public List getProduct(final String _soft_id) throws SQLException {

		List list  = new LinkedList();
		QueryManager tmpAdp = new QueryManager();
		String query = "";
		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE   soft.soft_id = "
				+ _soft_id;
		try 
		{
			 tmpAdp.executeQuery(query);
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

		return list;
	}

	final public List getCoOneProductlist(String strUser_id, final String site_id , final String catalog_id , final ProductlistBean productlistBean , final AuthorizationPageBean authorizationPageBean  ) {
		if (strUser_id == null || strUser_id.length() == 0)
			strUser_id = "0";

		List list  = new LinkedList();
		QueryManager co1Adp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE soft.catalog_id = "
				+ catalog_id
				+ "  and   soft.tree_id IS NULL  and  soft.active = true  and   soft.portlettype_id = "+Layout.PORTLET_TYPE_LEFTTOP+"   and  soft.lang_id = " + authorizationPageBean.getLang_id()  +"  and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC " ; // limit " + limit_co_one_list + " offset 0" ;
				//+ productlistBean.getOffset();
		try
		{
			//list = co1Adp.executeQueryList(query);
			list =  co1Adp.executeQueryList(query, limit_co_one_list , 0);
			
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
  		 co1Adp.close();
		}

		return list ;
	}

	
	final public List getBottomlist(String strUser_id, final String site_id ,   final AuthorizationPageBean authorizationPageBean  ) {
		if (strUser_id == null || strUser_id.length() == 0)
			strUser_id = "0";

		List list  = new LinkedList();
		QueryManager bottomAdp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id " +
				" LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id " +
				" LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  " +
				" WHERE  soft.active = true  and   soft.portlettype_id = "+Layout.PORTLET_TYPE_BOTTOM+"  and  soft.lang_id = " + authorizationPageBean.getLang_id()  +"  and   soft.site_id = "
				+ site_id + "   ORDER BY soft.soft_id DESC " ; // limit " + limit_co_one_list + " offset 0" ;
				//+ productlistBean.getOffset();
		try
		{
			//list = co1Adp.executeQueryList(query);
			list =  bottomAdp.executeQueryList(query, limit_bottom_list , 0);
			
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
			bottomAdp.close();
		}

		return list ;
	}
	
	final public List getCoOneSearchDirect(String strUser_id, final String site_id , final String catalog_id , final SearchBean productlistBean , final AuthorizationPageBean authorizationPageBean  ) {
		if (strUser_id == null || strUser_id.length() == 0)
			strUser_id = "0";

		List list  = new LinkedList();
		QueryManager co1Adp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE soft.catalog_id = "
				+ catalog_id
				+ "  and   soft.tree_id IS NULL  and  soft.active = true  and   soft.portlettype_id = 1  and lang_id = " + authorizationPageBean.getLang_id()  +"  and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC " ; // limit " + limit_co_one_list + " offset 0" ;
				//+ productlistBean.getOffset();
		try
		{
			//list = co1Adp.executeQueryList(query);
			list =  co1Adp.executeQueryList(query, limit_co_one_list , 0);
			
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
  		 co1Adp.close();
		}

		return list ;
	}
	
	final public List getCoTwoProductlist( String strUser_id, final String site_id , final String catalog_id , final ProductlistBean productlistBean , final  AuthorizationPageBean authorizationPageBean) {
		if (strUser_id == null || strUser_id.length() == 0)
			strUser_id = "0";

		List list  = new LinkedList();
		QueryManager co2Adp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
				"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE soft.catalog_id = "
				+ catalog_id
				+ " and   soft.tree_id IS NULL and  soft.active = true  and   soft.portlettype_id = "+Layout.PORTLET_TYPE_RIGHTTOP+" and soft.lang_id = " + authorizationPageBean.getLang_id()  +" and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC " ; //  limit " + limit_co_two_list + " offset 0 " ;
				//+ productlistBean.getOffset();

		try 
		{
			//list = co2Adp.executeQueryList(query);
			list =  co2Adp.executeQueryList(query, limit_co_two_list , 0);
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
	  	 co2Adp.close();
		}

		return list ;
	}

	
	final public List getCoTwoSearchDirect( String strUser_id, final String site_id , final String catalog_id , final SearchBean productlistBean , final  AuthorizationPageBean authorizationPageBean) {
		if (strUser_id == null || strUser_id.length() == 0)
			strUser_id = "0";

		List list  = new LinkedList();
		QueryManager co2Adp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
				"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE soft.catalog_id = "
				+ catalog_id
				+ " and   soft.tree_id IS NULL and  soft.active = true  and   soft.portlettype_id = 2 and lang_id = " + authorizationPageBean.getLang_id()  +" and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC " ; //  limit " + limit_co_two_list + " offset 0 " ;
				//+ productlistBean.getOffset();

		try 
		{
			//list = co2Adp.executeQueryList(query);
			list =  co2Adp.executeQueryList(query, limit_co_two_list , 0);
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
	  	 co2Adp.close();
		}

		return list ;
	}
	
	/**
	 * 
	 * Extention info 1 for policy page
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id
	 * @return
	 */

	final public List getExtPolicyOneProductlist(String strUser_id, final String site_id, final	String tree_id , final ItemDescriptionBean productlistBean ) {
		if (strUser_id == null || strUser_id.length() == 0)	strUser_id = "0";
		
		List list  = new LinkedList();
		QueryManager ext1Adp = new QueryManager();
		String query = "";
		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name  as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9, creteria10.name as creteria10 " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  and   soft.portlettype_id = 1 and   soft.tree_id = "
				+ tree_id
				+ "    and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC ";
		try 
		{
			list = ext1Adp.executeQueryList(query);
			productlistBean.setPagecount_ext1(ext1Adp.getRowCount());
			
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
		 ext1Adp.close();
		}

		return list;
	}

	/**
	 * Extention info 2 for policy page
	 * limit_ext1_one_list
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id - Установить родителя для записи обязательно
	 * @return
	 */

	final public List getExtPolicyTwoProductlist( String strUser_id, final String site_id,
			final String tree_id , final ItemDescriptionBean productlistBean  ) {
		if (strUser_id == null || strUser_id.length() == 0)		strUser_id = "0";

		List list  = new LinkedList();
		
		QueryManager ext2Adp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria1  " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE   soft.active = true  and   soft.portlettype_id = 2 and   soft.tree_id = "
				+ tree_id
				+ "  and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC ";

		try 
		{
			list = ext2Adp.executeQueryList(query);
			productlistBean.setPagecount_ext2(ext2Adp.getRowCount());
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
		 ext2Adp.close();
		}

		return list ;
	}

	

	/**
	 * Extention info 2 for policy page
	 * limit_ext1_one_list
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id - Установить родителя для записи обязательно
	 * @return
	 */

	final	public List getExtPolicyFilesProductlist(String strUser_id, final String site_id,
			final String tree_id , final ItemDescriptionBean productlistBean  ) {
		if (strUser_id == null || strUser_id.length() == 0)		strUser_id = "0";

		List list  = new LinkedList();
		
		QueryManager extFilesAdp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9, creteria10.name as creteria10 " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE   soft.active = true  and   soft.portlettype_id = 5 and   soft.tree_id = "
				+ tree_id
				+ "  and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC ";

		try 
		{
			list = extFilesAdp.executeQueryList(query);
			productlistBean.setPagecount_ext_files(extFilesAdp.getRowCount());
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
			extFilesAdp.close();
		}

		return list ;
	}


	
	/**
	 * Extention info 2 for policy page
	 * limit_ext1_one_list
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id - Установить родителя для записи обязательно
	 * @return
	 */

	final public List getExtPolicyTabsProductlist(String strUser_id, final String site_id,
			final String tree_id , final ItemDescriptionBean productlistBean  ) {
		if (strUser_id == null || strUser_id.length() == 0)		strUser_id = "0";

		List list  = new LinkedList();
		
		QueryManager extTabsAdp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE   soft.active = true  and   soft.portlettype_id = 4 and   soft.tree_id = "
				+ tree_id
				+ "  and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC ";

		try 
		{
			list = extTabsAdp.executeQueryList(query);
			productlistBean.setPagecount_ext_tabs(extTabsAdp.getRowCount());
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
			extTabsAdp.close();
		}

		return list ;
	}

	
	
	/**
	 * Extention info 2 for policy page
	 * 
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id - Установить родителя для записи обязательно
	 * @return
	 * 
	 * 
	 * 
	 */

	final public List getBlogExtPolicyProductlist( String strUser_id, final String site_id,
			final String tree_id , final ItemDescriptionBean productlistBean  ) {
		if (strUser_id == null || strUser_id.length() == 0)		strUser_id = "0";

		List list  = new LinkedList();
		
		QueryManager blogAdp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , tuser.FIRST_NAME , tuser.LAST_NAME , tuser.COMPANY , soft.tree_id  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN tuser  ON soft.user_id = tuser.user_id  WHERE   soft.active = true  and   soft.portlettype_id = 3 and   soft.tree_id = "
				+ tree_id
				+ "  and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC ";

		try 
		{
			list = blogAdp.executeQueryList(query);
			productlistBean.setPagecount_blog(blogAdp.getRowCount());
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
		 blogAdp.close();
		}

		return list ;
	}


	
	/**
	 * Extention info 2 for policy page
	 * 
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id - Установить родителя для записи обязательно
	 * @return
	 */

	final public List getBlogTopProductlist(final String site_id,
			final ProductlistBean productlistBean , final  AuthorizationPageBean authorizationPageBean ) {
		
		List list  = new LinkedList();
		
		QueryManager blogAdp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , tuser.FIRST_NAME , tuser.LAST_NAME , tuser.COMPANY , soft.tree_id  , soft_parent.name as soft_parent_name  " +
				"FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  " +
				"LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  " +
				"LEFT  JOIN file  ON soft.file_id = file.file_id " +
				"LEFT  JOIN tuser  ON soft.user_id = tuser.user_id  " +
				"LEFT  JOIN soft soft_parent  ON soft.tree_id = soft_parent.soft_id  " +
				" WHERE   soft.active = true  and   soft.portlettype_id = 3 and soft.lang_id = " + authorizationPageBean.getLang_id()  +"  and soft.site_id = " + site_id
				+ " ORDER BY soft.CDATE DESC " ; // limit  "  + limit_blog_list ;
		try 
		{
			//list = blogAdp.executeQueryList(query);
			list = blogAdp.executeQueryList(query,limit_blog_list,0 );
			productlistBean.setPagecount_blog(blogAdp.getRowCount());
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
		 blogAdp.close();
		}

		return list ;
	}

	
	final public List getNewslist(String strUser_id, final String site_id , final AuthorizationPageBean authorizationPageBean ) {
		if (strUser_id == null || strUser_id.length() == 0)	strUser_id = "0";
		//productlistBean.setSite_id(site_id);

		List list  = new LinkedList();
		
		QueryManager newsAdp = new QueryManager();
		String query = "";
		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7, creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
				" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE soft.catalog_id = -1 and  soft.active = true and soft.lang_id = " + authorizationPageBean.getLang_id()  +"  and soft.site_id = "
				+ site_id
				+ " ORDER BY soft.soft_id DESC " ; // limit " +  limit_news_list + " offset "	+ productlistBean.getOffset();
		try 
		{
			//list = newsAdp.executeQueryList(query);
			list = newsAdp.executeQueryList(query,limit_news_list,0);
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
		 newsAdp.close();
		}

		return list;
	}

	

	final public int stringToInt(String s) {
		int i;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			i = 0;
			log.error(ex);
		}
		return i;
	}

	
	
	final public String deletePosition(final String strPosition_id) {
		// if( order_paystatus.compareTo("0") != 0) return strPosition_id ;
		if (strPosition_id == null || strPosition_id.length() == 0)
			return strPosition_id;
		QueryManager queryManager = new QueryManager();
		queryManager.BeginTransaction();
		String query = "";
		query = "update soft set active = ? where soft_id = " + strPosition_id;
		try 
		{
			//Adptmp.executeUpdate(query);
			Map args = queryManager.getArgs();
			args.put("active" , false );
			queryManager.executeUpdateWithArgs(query, args);
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
		return strPosition_id;
	}

	
	
	final public String colorPosition(final String strPosition_id , final String color) {
		// if( order_paystatus.compareTo("0") != 0) return strPosition_id ;
		if (strPosition_id == null || strPosition_id.length() == 0)
			return strPosition_id;
		QueryManager queryManager = new QueryManager();
		queryManager.BeginTransaction();
		String query = "";
		query = "update soft set color = ? where soft_id = " + strPosition_id;
		try 
		{
			Map args = queryManager.getArgs();
			args.put("color" , color );
			queryManager.executeUpdateWithArgs(query, args);
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
		return strPosition_id;
	}
	
	
	final public String upPosition(String strPosition_id) {
		// if( order_paystatus.compareTo("0") != 0) return strPosition_id ;
		if (strPosition_id == null || strPosition_id.length() == 0)	return strPosition_id;
		
		String query = "";
		QueryManager Adptmp = new QueryManager();
		Adptmp.BeginTransaction();
		String strID = "";
		try 
		{
			query = sequences_rs.getString("soft");
			//query = "SELECT NEXT VALUE FOR soft_id_seq  AS ID  FROM ONE_SEQUENCES";
			Adptmp.executeQuery(query);
			strID = Adptmp.getValueAt(0, 0);
			query = "update soft set soft_id = " + strID + " where soft_id = " + strPosition_id;
			Adptmp.executeUpdate(query);
			query = "update soft set tree_id = " + strID + " where tree_id = " + strPosition_id;			
			Adptmp.executeUpdate(query);
			Adptmp.commit();
		}
		catch (SQLException ex) 
		{
			 log.error(query,ex);
			 Adptmp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adptmp.rollback();
		}
		finally
		{
			Adptmp.close();
		}
		return strID;
	}
	
	
	
	final public List getProductlist(String strUser_id, final String site_id , final long catalog_id , final  ProductlistBean productlistBean , final AuthorizationPageBean authorizationPageBean ) {
			if (strUser_id == null || strUser_id.length() == 0)
				strUser_id = "0";
	
			String query_productlist = "" ; 
	        List list = new LinkedList();
			QueryManager Adp = new QueryManager();
			if (productlistBean.getSearchquery() == 0)
			{
	
				
				if(catalog_id ==SpecialCatalog.OUTPUT_PAGES_SORT_BY_VISIT_STATISTICS)
					// вывод наиболее посещаемых страниц
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 "  + " , soft.COLOR " +
							" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
							" and   soft.portlettype_id = 0 " +  
							" and   soft.type_id = 3 " +  
							" and   soft.catalog_id  <> -1 " +  
					        " and    soft.site_id = " + site_id   
							+ " ORDER BY soft.STATISTIC_ID DESC ";
				
	//			 вывод всех с служебных страниц				
				else if(catalog_id == SpecialCatalog.FOR_EXTERNAL_PAGE)
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
					",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name  as creteria6, creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 "  + " , soft.COLOR " +
					" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
					" and   soft.catalog_id  =  " + SpecialCatalog.FOR_EXTERNAL_PAGE +  
			        " and    soft.site_id = " + site_id   
					+ " ORDER BY soft.soft_id DESC " ;
				
				// вывод всех с сортировкой по ID				
				else if(catalog_id == SpecialCatalog.OUTPUT_PAGES_SORT_BY_SOFT_ID)
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
							"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
							" and   soft.portlettype_id = 0 " +  
							//" and   soft.catalog_id  <> -1 " +  
							" and   soft.catalog_id  = -2 " +
							" and   soft.type_id  = 3 " +  
							" and soft.lang_id = "	+ authorizationPageBean.getLang_id() + 
					        " and    soft.site_id = " + site_id   
							+ " ORDER BY soft.soft_id DESC ";
				
//				 показывать в новосном модуле				
/*
				else if(catalog_id == SpecialCatalog.OUTPUT_PAGES_FROM_NEWS_CATALOG)
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name , creteria2.name , creteria3.name , creteria4.name , creteria5.name , creteria6.name , creteria7.name , creteria8.name , creteria9.name , creteria10.name  " +  " , soft.COLOR " +
							"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
  
							" and   soft.type_id  = 4 " +  
					        " and    soft.site_id = " + site_id   
							+ " ORDER BY soft.soft_id DESC ";
*/	
				
				//вывод новинок которые  ввел один пользователь из своего кабинета
				else if(catalog_id == SpecialCatalog.OUTPUT_PAGES_NEW_USER_CONTENT_FOR_APROVEMENT_SORT_BY_DATE)
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
							"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
							" and  soft.portlettype_id = 0 " +  
							" and   soft.catalog_id  <> -1 " +  
					        " and soft.site_id = " + site_id +
					        " and  soft.user_id= " + strUser_id 
					        + " ORDER BY soft.CDATE DESC " ;
				//вывод новинок
				else if(catalog_id ==SpecialCatalog.OUTPUT_PAGES_SORT_BY_CREATED_DATE)
					// forum mess
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
							"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
							" and   soft.portlettype_id = 0 " +  
							" and   soft.type_id = 3 " +  
							" and   soft.catalog_id  <> -1 " +  
							" and   soft.lang_id = "	+ authorizationPageBean.getLang_id() + 
					        " and    soft.site_id = " + site_id   
							+ " ORDER BY soft.CDATE DESC " ;
	
				//вывод всех по рейтингу
				else  if(catalog_id ==SpecialCatalog.OUTPUT_PAGES_SORT_BY_RATING)
					// forum mess
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +	 " , soft.COLOR " +					
							"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
							" and   soft.portlettype_id = 0 " +  
							" and   soft.catalog_id  <> -1 " +  
					        " and    soft.site_id = " + site_id   
							+ " ORDER BY soft.MIDLE_BAL1 DESC " ;
				
				/////вывод всех по рейтингу
				
				/*
				else  if(productlistBean.getCatalog_id().compareTo("-8")==0)
					// forum mess
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID ,  count( product_id )  as number  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url  " +
							" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id  LEFT JOIN  basket  ON soft.soft_id = basket.product_id " +
							"  WHERE  soft.portlettype_id = 0 " +  
					        "  and    soft.site_id = " + site_id   
							+ " group by  basket.product_id , soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   order by  number  DESC limit " + limit_product_list + " offset "
							+ productlistBean.getOffset();
				*/
				
				
				else if(catalog_id ==SpecialCatalog.OUTPUT_PAGES_AREA_FROM_USERSITE_TO_MAIN_SITE)
				// forum mess
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
						" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE soft.catalog_id = "
						+ catalog_id
						+ " and  soft.active = true  and   soft.portlettype_id = 0 "  
						+ "   ORDER BY soft.soft_id DESC " ; 
	
				else if(catalog_id ==SpecialCatalog.CONTENT_WAITING_FOR_APROVEMENT)
				// posted message is for aprove admin 
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
							"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  " +
							" WHERE  soft.active = true  and   soft.portlettype_id = 0 and   soft.type_id = 1 "  
							+ "   ORDER BY soft.soft_id DESC" ;
	
				else if(catalog_id ==SpecialCatalog.CONTENT_REJECTED_PUBLICATION)
					// posted message  is no aprove admin 
					query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
								",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name  as creteria3, creteria4.name as creteria4 , creteria5.name  as creteria5, creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +				
								"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  " +
								" WHERE soft.active = true  and   soft.portlettype_id = 0 and   soft.type_id = 2 "  
								+ "   ORDER BY soft.soft_id DESC " ;
	
				else query_productlist = "SELECT DISTINCT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name  as creteria10 " +  " , soft.COLOR " +
						"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE " +
						"( soft.catalog_id = "	+ catalog_id + " and soft.active = true  and   soft.portlettype_id = 0  and   soft.site_id = " + site_id + " ) " +
						" or ( soft.catalog_id = "	+ catalog_id + " and  soft.active = true and   soft.portlettype_id = 0  and   soft.user_id = " + strUser_id + " ) " +
						" or ( soft.catalog_id IN ( SELECT CATALOG_ID FROM catalog WHERE  PARENT_ID =  "	+ catalog_id + " and  soft.active = true and   soft.portlettype_id = 0  )) " +
							"  ORDER BY soft.soft_id DESC " ;
				
				/*
				 * else query_productlist = "SELECT DISTINCT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name , creteria2.name , creteria3.name , creteria4.name , creteria5.name , creteria6.name , creteria7.name , creteria8.name , creteria9.name , creteria10.name  " +  " , soft.COLOR " +
						"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE " +
						"( soft.catalog_id = "	+ catalog_id + " and soft.lang_id = "	+ authorizationPageBean.getLang_id() + " and soft.active = true  and   soft.portlettype_id = 0  and   soft.site_id = " + site_id + " ) " +
						" or ( soft.catalog_id = "	+ catalog_id + " and soft.lang_id = "	+ authorizationPageBean.getLang_id() + " and  soft.active = true and   soft.portlettype_id = 0  and   soft.user_id = " + strUser_id + " ) " +
						" or ( soft.catalog_id IN ( SELECT CATALOG_ID FROM catalog WHERE  PARENT_ID =  "	+ catalog_id + " and soft.lang_id = "	+ authorizationPageBean.getLang_id() + " and  soft.active = true and   soft.portlettype_id = 0  )) " +
							"  ORDER BY soft.soft_id DESC " ;
				 */
				
				/*
				else query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id  WHERE soft.catalog_id = "
					+ catalog_id
					+ " and  soft.active = true  and   soft.portlettype_id = 0  and   soft.site_id = "
					+ site_id
					+ "   ORDER BY soft.soft_id DESC limit 10 offset "
					+ offset;
					*/
				
			}	
			if (productlistBean.getSearchquery() == 1)
			{
				String wordWithBigChar = "" ;
				String wordWithSmallChar = "" ;
				if(productlistBean.getSearchValueArg().length() > 0)
				{
					wordWithBigChar = productlistBean.getSearchValueArg().substring(0,1).toUpperCase() +  productlistBean.getSearchValueArg().substring(1);
					wordWithSmallChar = productlistBean.getSearchValueArg().substring(0,1).toLowerCase() +  productlistBean.getSearchValueArg().substring(1);
				}
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
						" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  soft.site_id = "
						+ site_id
						+ " and   soft.active = true and ( soft.name LIKE '%"	+ wordWithSmallChar + "%' or soft.name LIKE '%"	+  wordWithBigChar + "%'  or soft.name LIKE '%"	+  productlistBean.getSearchValueArg().toLowerCase() + "%'  )" 
						+ " ORDER BY soft.soft_id DESC " ;
			}
			if (productlistBean.getSearchquery() == 2)
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b, soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
						"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  soft.site_id = "
						+ site_id
						+ " and  soft.active = true and ( soft.search = '" + productlistBean.getSearchValueArg().toLowerCase() + "' or soft.search = '" + productlistBean.getSearchValueArg().toUpperCase() 
						+ "' ) and soft.portlettype_id = 0   ORDER BY soft.soft_id DESC " ;
			if (productlistBean.getSearchquery() == 3) {
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name  as creteria10 " +  " , soft.COLOR " +
						"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  soft.site_id = "
						+ site_id
						+ " and   soft.active = true and soft.cost >= 0   ";
				if (authorizationPageBean.getCreteria1_id() != 0)
					query_productlist = query_productlist + " and soft.creteria1_id = "
							+ authorizationPageBean.getCreteria1_id();
				if (authorizationPageBean.getCreteria2_id() != 0)
					query_productlist = query_productlist + " and soft.creteria2_id = "
							+ authorizationPageBean.getCreteria2_id();
				if (authorizationPageBean.getCreteria3_id() != 0)
					query_productlist = query_productlist + " and soft.creteria3_id = "
							+ authorizationPageBean.getCreteria3_id();
				if (authorizationPageBean.getCreteria4_id() != 0)
					query_productlist = query_productlist + " and soft.creteria4_id = "
							+ authorizationPageBean.getCreteria4_id();
				if (authorizationPageBean.getCreteria5_id() != 0)
					query_productlist = query_productlist + " and soft.creteria5_id = "
							+ authorizationPageBean.getCreteria5_id();
				if (authorizationPageBean.getCreteria6_id() != 0)
					query_productlist = query_productlist + " and soft.creteria6_id = "
							+ authorizationPageBean.getCreteria6_id();
				if (authorizationPageBean.getCreteria7_id() != 0)
					query_productlist = query_productlist + " and soft.creteria7_id = "
							+ authorizationPageBean.getCreteria7_id();
				if (authorizationPageBean.getCreteria8_id() != 0)
					query_productlist = query_productlist + " and soft.creteria8_id = "
							+ authorizationPageBean.getCreteria8_id();
				if (authorizationPageBean.getCreteria9_id() != 0)
					query_productlist = query_productlist + " and soft.creteria9_id = "
							+ authorizationPageBean.getCreteria9_id();
				if (authorizationPageBean.getCreteria10_id() != 0)
					query_productlist = query_productlist + " and soft.creteria10_id = "
							+ authorizationPageBean.getCreteria10_id();
				query_productlist = query_productlist
						+ " ORDER BY soft.soft_id DESC " ;
				
				if(query_productlist.indexOf("creteria") == -1 )
				{
					productlistBean.setQuery_productlist(query_productlist) ;
					return list ;
				}
			}
	
			if (productlistBean.getSearchquery() == 4) // hotel serach
			{
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria1  " +  " , soft.COLOR " +
						"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id    WHERE  soft.site_id = "
						+ site_id
						+ " and   soft.active = true  and soft.cost >= 0   ";
				if (authorizationPageBean.getCreteria1_id() != 0)
					query_productlist = query_productlist + " and soft.creteria1_id = "
							+ authorizationPageBean.getCreteria1_id();
				if (authorizationPageBean.getCreteria2_id() != 0)
					query_productlist = query_productlist + " and soft.creteria2_id = "
							+ authorizationPageBean.getCreteria2_id();
				if (authorizationPageBean.getCreteria3_id() != 0)
					query_productlist = query_productlist + " and soft.creteria3_id = "
							+ authorizationPageBean.getCreteria3_id();
				if (authorizationPageBean.getCreteria4_id()!= 0)
					query_productlist = query_productlist + " and soft.creteria4_id = "
							+ authorizationPageBean.getCreteria4_id();
				if (authorizationPageBean.getCreteria5_id() != 0)
					query_productlist = query_productlist + " and soft.creteria5_id = "
							+ authorizationPageBean.getCreteria5_id();
				if (authorizationPageBean.getCreteria6_id() != 0)
					query_productlist = query_productlist + " and soft.creteria6_id = "
							+ authorizationPageBean.getCreteria6_id();
				if (authorizationPageBean.getCreteria7_id() != 0)
					query_productlist = query_productlist + " and soft.creteria7_id = "
							+ authorizationPageBean.getCreteria7_id();
				if (authorizationPageBean.getCreteria8_id() != 0)
					query_productlist = query_productlist + " and soft.creteria8_id = "
							+ authorizationPageBean.getCreteria8_id();
				if (authorizationPageBean.getCreteria9_id()!= 0)
					query_productlist = query_productlist + " and soft.creteria9_id = "
							+ authorizationPageBean.getCreteria9_id();
				if (authorizationPageBean.getCreteria10_id() != 0)
					query_productlist = query_productlist + " and soft.creteria10_id = "
							+ authorizationPageBean.getCreteria10_id();
				if (catalog_id != 0)
					query_productlist = query_productlist + " and soft.catalog_id = " + catalog_id;
				if (authorizationPageBean.getYearfrom_id() != 0
						&& authorizationPageBean.getMountfrom_id() != 0
						&& authorizationPageBean.getDayfrom_id() != 0
						&& authorizationPageBean.getYearto_id()!= 0
						&& authorizationPageBean.getMountto_id() != 0
						&& authorizationPageBean.getDayto_id()!= 0) {
					authorizationPageBean.getCalendar().set((int)(authorizationPageBean.getYearfrom_id()), (int)(authorizationPageBean.getMountfrom_id()) - 1, (int)(authorizationPageBean.getDayfrom_id()), 0, 1);
					long fromDate = authorizationPageBean.getCalendar().getTimeInMillis();
					// System.out.println(calendar.getTime());
					authorizationPageBean.getCalendar().set((int)(authorizationPageBean.getYearto_id()), (int)(authorizationPageBean.getMountto_id()) - 1, (int)(authorizationPageBean.getDayto_id()),
							23, 59);
					long toDate = authorizationPageBean.getCalendar().getTimeInMillis();
					// System.out.println(calendar.getTime());
					// query = query + " and calendar.holddate > " + fromDate + "
					// and calendar.holddate < " + toDate + " " ;
					query_productlist = query_productlist
							+ "  and soft.soft_id  NOT IN ( SELECT  soft.soft_id  FROM soft LEFT  JOIN calendar  ON soft.soft_id = calendar.soft_id WHERE  soft.site_id  = "
							+ site_id
							+ "  and   soft.active = true  and calendar.holddate >=  "
							+ fromDate + " and calendar.holddate <=  " + toDate
							+ " ) ";
				}
	
				query_productlist = query_productlist
						+ " ORDER BY soft.soft_id DESC ";
	
				if(query_productlist.indexOf("creteria") == -1 )
				{
					productlistBean.setQuery_productlist(query_productlist) ;
					return list ;
				}
			}
	
			if (productlistBean.getSearchquery() == 5) {
				// criteria with catalog_id
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria1  " +  " , soft.COLOR " +
				"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
				"soft.site_id = "+ site_id+ " and  " +
				"soft.catalog_id = "+ catalog_id + " and  " +
				" soft.active = true " ;
				//if (AuthorizationPageBean.getFromCost() > 0 || AuthorizationPageBean.getFromCost()  >  0  )	query_productlist = query_productlist +	" and   soft.cost > " + "";
				if (authorizationPageBean.getFromCost().longValue() > 0 || authorizationPageBean.getFromCost().longValue()  >  0  )	query_productlist = query_productlist +	" and   soft.cost >= " + authorizationPageBean.getFromCost() ;
				
				if (authorizationPageBean.getToCost().longValue() > 0 || authorizationPageBean.getToCost().longValue()  >  0 )	query_productlist = query_productlist +	" and soft.cost <=  " + authorizationPageBean.getToCost() ;
				
				if (authorizationPageBean.getCreteria1_id() != 0)
					query_productlist = query_productlist + " and soft.creteria1_id = "
							+ authorizationPageBean.getCreteria1_id();
				if (authorizationPageBean.getCreteria2_id() != 0)
					query_productlist = query_productlist + " and soft.creteria2_id = "
							+ authorizationPageBean.getCreteria2_id();
				if (authorizationPageBean.getCreteria3_id() != 0)
					query_productlist = query_productlist + " and soft.creteria3_id = "
							+ authorizationPageBean.getCreteria3_id();
				if (authorizationPageBean.getCreteria4_id() != 0)
					query_productlist = query_productlist + " and soft.creteria4_id = "
							+ authorizationPageBean.getCreteria4_id();
				if (authorizationPageBean.getCreteria5_id() != 0)
					query_productlist = query_productlist + " and soft.creteria5_id = "
							+ authorizationPageBean.getCreteria5_id();
				if (authorizationPageBean.getCreteria6_id() != 0)
					query_productlist = query_productlist + " and soft.creteria6_id = "
							+ authorizationPageBean.getCreteria6_id();
				if (authorizationPageBean.getCreteria7_id() != 0)
					query_productlist = query_productlist + " and soft.creteria7_id = "
							+ authorizationPageBean.getCreteria7_id();
				if (authorizationPageBean.getCreteria8_id() != 0)
					query_productlist = query_productlist + " and soft.creteria8_id = "
							+ authorizationPageBean.getCreteria8_id();
				if (authorizationPageBean.getCreteria9_id() != 0)
					query_productlist = query_productlist + " and soft.creteria9_id = "
							+ authorizationPageBean.getCreteria9_id();
				if (authorizationPageBean.getCreteria10_id() != 0)
					query_productlist = query_productlist + " and soft.creteria10_id = "
							+ authorizationPageBean.getCreteria10_id();
				query_productlist = query_productlist
						+ " ORDER BY soft.soft_id DESC ";
				
				if(query_productlist.indexOf("creteria") == -1 )
				{
					productlistBean.setQuery_productlist(query_productlist) ;
					return list ;
				}
			}
	
			
			
			if (productlistBean.getSearchquery() == 6) {
				// Search only by criteria
				// criteria without catalog_id
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria1  " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
				"soft.site_id = "+ site_id+ " and soft.portlettype_id = 0  and " +
				" soft.active = true " ; 
				
				if (  authorizationPageBean.getFromCost().longValue()   >  0  )	query_productlist = query_productlist +	" and   soft.cost >= " + authorizationPageBean.getFromCost() ; 
				
				if ( authorizationPageBean.getToCost().longValue()  >  0 )	query_productlist = query_productlist +	" and soft.cost <=  " + authorizationPageBean.getToCost() ;

				
				if (authorizationPageBean.getCreteria1_id() > 0)
					query_productlist = query_productlist + " and soft.creteria1_id = "
							+ authorizationPageBean.getCreteria1_id();
				if (authorizationPageBean.getCreteria2_id() > 0)
					query_productlist = query_productlist + " and soft.creteria2_id = "
							+ authorizationPageBean.getCreteria2_id();
				if (authorizationPageBean.getCreteria3_id() > 0)
					query_productlist = query_productlist + " and soft.creteria3_id = "
							+ authorizationPageBean.getCreteria3_id();
				if (authorizationPageBean.getCreteria4_id() > 0)
					query_productlist = query_productlist + " and soft.creteria4_id = "
							+ authorizationPageBean.getCreteria4_id();
				if (authorizationPageBean.getCreteria5_id() > 0)
					query_productlist = query_productlist + " and soft.creteria5_id = "
							+ authorizationPageBean.getCreteria5_id();
				if (authorizationPageBean.getCreteria6_id() > 0)
					query_productlist = query_productlist + " and soft.creteria6_id = "
							+ authorizationPageBean.getCreteria6_id();
				if (authorizationPageBean.getCreteria7_id() > 0)
					query_productlist = query_productlist + " and soft.creteria7_id = "
							+ authorizationPageBean.getCreteria7_id();
				if (authorizationPageBean.getCreteria8_id() > 0)
					query_productlist = query_productlist + " and soft.creteria8_id = "
							+ authorizationPageBean.getCreteria8_id();
				if (authorizationPageBean.getCreteria9_id() > 0)
					query_productlist = query_productlist + " and soft.creteria9_id = "
							+ authorizationPageBean.getCreteria9_id();
				if (authorizationPageBean.getCreteria10_id() > 0)
					query_productlist = query_productlist + " and soft.creteria10_id = "
							+ authorizationPageBean.getCreteria10_id();
				query_productlist = query_productlist
						+ " ORDER BY soft.soft_id DESC " ;
				if(query_productlist.indexOf("creteria") == -1 )
				{
					productlistBean.setQuery_productlist(query_productlist) ;
					return list ;
				}
				
			}
			
			
			if (productlistBean.getSearchquery() == 9) {
				// Search only by criteria
				// criteria without catalog_id
				String wordWithBigChar = "" ;
				String wordWithSmallChar = "" ;
				if(productlistBean.getSearchValueArg().length() > 0)
				{
					wordWithBigChar = productlistBean.getSearchValueArg().substring(0,1).toUpperCase() +  productlistBean.getSearchValueArg().substring(1);
					wordWithSmallChar = productlistBean.getSearchValueArg().substring(0,1).toLowerCase() +  productlistBean.getSearchValueArg().substring(1);
				}

				
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria1  " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
				"soft.site_id = "+ site_id+ " and soft.portlettype_id = 0  and " +
				" soft.active = true and ( soft.description LIKE '%"	+ wordWithSmallChar + "%' or soft.description LIKE '%"	+  wordWithBigChar + "%'  or soft.description LIKE '%"	+  productlistBean.getSearchValueArg().toLowerCase() + "%'  )" ; 
				
				if (  authorizationPageBean.getFromCost().longValue()   >  0  )	query_productlist = query_productlist +	" and   soft.cost >= " + authorizationPageBean.getFromCost() ; 
				
				if ( authorizationPageBean.getToCost().longValue()  >  0 )	query_productlist = query_productlist +	" and soft.cost <=  " + authorizationPageBean.getToCost() ;

				query_productlist = query_productlist	+ " ORDER BY soft.soft_id DESC " ;
				
			}
			
			//arenda
			if (productlistBean.getSearchquery() == 7) {
				// criteria with catalog_id
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria1  " +  " , soft.COLOR " +
				"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
				"soft.site_id = "+ site_id+ " and soft.portlettype_id = 0  and " +
				" soft.active = true " ;
	
				//Number.this.
				
				if (  authorizationPageBean.getFromCost().longValue()   >  0  )	query_productlist = query_productlist +	" and   soft.cost >= " + authorizationPageBean.getFromCost() ; 
				
				if ( authorizationPageBean.getToCost().longValue()  >  0 )	query_productlist = query_productlist +	" and soft.cost <=  " + authorizationPageBean.getToCost() ;
	
				
				
				if (authorizationPageBean.getCreteria1_id() > 0)
					query_productlist = query_productlist + " and soft.creteria1_id = "
							+ authorizationPageBean.getCreteria1_id();
				if (authorizationPageBean.getCreteria2_id() > 0)
					query_productlist = query_productlist + " and soft.creteria2_id = "
							+ authorizationPageBean.getCreteria2_id();
				if (authorizationPageBean.getCreteria3_id() > 0)
					query_productlist = query_productlist + " and soft.creteria3_id = "
							+ authorizationPageBean.getCreteria3_id();
				if (authorizationPageBean.getCreteria4_id() > 0)
					query_productlist = query_productlist + " and soft.creteria4_id = "
							+ authorizationPageBean.getCreteria4_id();
				if (authorizationPageBean.getCreteria5_id() > 0)
					query_productlist = query_productlist + " and soft.creteria5_id = "
							+ authorizationPageBean.getCreteria5_id();
				if (authorizationPageBean.getCreteria6_id() > 0)
					query_productlist = query_productlist + " and soft.creteria6_id = "
							+ authorizationPageBean.getCreteria6_id();
				if (authorizationPageBean.getCreteria7_id() > 0)
					query_productlist = query_productlist + " and soft.creteria7_id = "
							+ authorizationPageBean.getCreteria7_id();
				if (authorizationPageBean.getCreteria8_id() > 0)
					query_productlist = query_productlist + " and soft.creteria8_id = "
							+ authorizationPageBean.getCreteria8_id();
				if (authorizationPageBean.getCreteria9_id() > 0)
					query_productlist = query_productlist + " and soft.creteria9_id = "
							+ authorizationPageBean.getCreteria9_id();
				if (authorizationPageBean.getCreteria10_id() >  0)
					query_productlist = query_productlist + " and soft.creteria10_id = "
							+ authorizationPageBean.getCreteria10_id();
				query_productlist = query_productlist
						+ " ORDER BY soft.soft_id DESC " ;
				
				if(query_productlist.indexOf("creteria") == -1 && query_productlist.indexOf("cost") == -1  )
				{
					productlistBean.setQuery_productlist(query_productlist) ;
					return list ;
				}
			}
			
			
			
			// spravochnik
			
			if (productlistBean.getSearchquery() == 8) {
				// criteria with catalog_id
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria1  " +  " , soft.COLOR " +
				"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
				"soft.site_id = "+ site_id+ " and soft.portlettype_id = 0  and  " +
				" soft.active = true " ;
				
				if (authorizationPageBean.getCreteria1_id()!= 0)
					query_productlist = query_productlist + " and soft.creteria1_id = "
							+ authorizationPageBean.getCreteria1_id();
				if (authorizationPageBean.getCreteria2_id() != 0)
					query_productlist = query_productlist + " and soft.creteria2_id = "
							+ authorizationPageBean.getCreteria2_id();
				if (authorizationPageBean.getCreteria3_id() != 0)
					query_productlist = query_productlist + " and soft.creteria3_id = "
							+ authorizationPageBean.getCreteria3_id();
				
				if (authorizationPageBean.getCreteria4_id() != 0) 
				{
					query_productlist = query_productlist + " and ( soft.creteria4_id = " + authorizationPageBean.getCreteria4_id();
					query_productlist = query_productlist + " or soft.creteria6_id = " + authorizationPageBean.getCreteria4_id();
					query_productlist = query_productlist + " or soft.creteria8_id = " + authorizationPageBean.getCreteria4_id() + " ) ";
				}
				
				if (authorizationPageBean.getCreteria5_id() != 0)
				{
					query_productlist = query_productlist + " and ( soft.creteria5_id = "	+ authorizationPageBean.getCreteria5_id();
					query_productlist = query_productlist + " or soft.creteria7_id = "	+ authorizationPageBean.getCreteria5_id();
					query_productlist = query_productlist + " or soft.creteria9_id = "	+ authorizationPageBean.getCreteria5_id() + " ) ";
				}
				
				
				query_productlist = query_productlist
						+ " ORDER BY soft.soft_id DESC " ;
				
				if(query_productlist.indexOf("creteria") == -1 && query_productlist.indexOf("cost") == -1  )
				{
					productlistBean.setQuery_productlist(query_productlist) ;
					return list ;
				}
			}
			
			
			try 
			{
				list =  Adp.executeQueryList(query_productlist, limit_product_list , productlistBean.getOffset());
				productlistBean.setQuery_productlist(query_productlist) ;
			}
			catch (SQLException ex) 
			{
			log.error(query_productlist,ex);
			}
			catch (Exception ex) 
			{
			log.error(ex);
			}
			finally
			{
			Adp.close();
			}
	
			return list ;
		}


	
	final public List getSearchList(String strUser_id, final String site_id , final long catalog_id , final  SearchBean productlistBean , final AuthorizationPageBean authorizationPageBean ) {
		if (strUser_id == null || strUser_id.length() == 0)
			strUser_id = "0";

		String query_productlist = "" ; 
        List list = new LinkedList();
		QueryManager Adp = new QueryManager();
		if (productlistBean.getSearchquery() == 0)
		{

			
			if(catalog_id ==SpecialCatalog.OUTPUT_PAGES_SORT_BY_VISIT_STATISTICS)
				// вывод наиболее посещаемых страниц
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 "  + " , soft.COLOR " +
						" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
						" and   soft.portlettype_id = 0 " +  
						" and   soft.type_id = 3 " +  
						" and   soft.catalog_id  <> -1 " +  
				        " and    soft.site_id = " + site_id   
						+ " ORDER BY soft.STATISTIC_ID DESC ";
			
//			 вывод всех с служебных страниц				
			else if(catalog_id == SpecialCatalog.FOR_EXTERNAL_PAGE)
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
				",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 "  + " , soft.COLOR " +
				" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
				" and   soft.catalog_id  =  " + SpecialCatalog.FOR_EXTERNAL_PAGE +  
		        " and    soft.site_id = " + site_id   
				+ " ORDER BY soft.soft_id DESC " ;
			
			// вывод всех с сортировкой по ID				
			else if(catalog_id == SpecialCatalog.OUTPUT_PAGES_SORT_BY_SOFT_ID)
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
						"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
						" and   soft.portlettype_id = 0 " +  
						" and   soft.catalog_id  <> -1 " +  
						" and   soft.type_id  = 3 " +  
						" and soft.lang_id = "	+ authorizationPageBean.getLang_id() + 
				        " and    soft.site_id = " + site_id   
						+ " ORDER BY soft.soft_id DESC ";
			
//			 показывать в новосном модуле				
/*
			else if(catalog_id == SpecialCatalog.OUTPUT_PAGES_FROM_NEWS_CATALOG)
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name , creteria2.name , creteria3.name , creteria4.name , creteria5.name , creteria6.name , creteria7.name , creteria8.name , creteria9.name , creteria10.name  " +  " , soft.COLOR " +
						"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +

						" and   soft.type_id  = 4 " +  
				        " and    soft.site_id = " + site_id   
						+ " ORDER BY soft.soft_id DESC ";
*/	
			
			//вывод новинок которые  ввел один пользователь из своего кабинета
			else if(catalog_id == SpecialCatalog.OUTPUT_PAGES_NEW_USER_CONTENT_FOR_APROVEMENT_SORT_BY_DATE)
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria1 " +  " , soft.COLOR " +
						"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
						" and  soft.portlettype_id = 0 " +  
						" and   soft.catalog_id  <> -1 " +  
				        " and soft.site_id = " + site_id +
				        " and  soft.user_id= " + strUser_id 
				        + " ORDER BY soft.CDATE DESC " ;
			//вывод новинок
			else if(catalog_id ==SpecialCatalog.OUTPUT_PAGES_SORT_BY_CREATED_DATE)
				// forum mess
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
						"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
						" and   soft.portlettype_id = 0 " +  
						" and   soft.type_id = 3 " +  
						" and   soft.catalog_id  <> -1 " +  
						" and   soft.lang_id = "	+ authorizationPageBean.getLang_id() + 
				        " and    soft.site_id = " + site_id   
						+ " ORDER BY soft.CDATE DESC " ;

			//вывод всех по рейтингу
			else  if(catalog_id ==SpecialCatalog.OUTPUT_PAGES_SORT_BY_RATING)
				// forum mess
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +	 " , soft.COLOR " +					
						"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE  soft.active = true  " +
						" and   soft.portlettype_id = 0 " +  
						" and   soft.catalog_id  <> -1 " +  
				        " and    soft.site_id = " + site_id   
						+ " ORDER BY soft.MIDLE_BAL1 DESC " ;
			
			/////вывод всех по рейтингу
			
			/*
			else  if(productlistBean.getCatalog_id().compareTo("-8")==0)
				// forum mess
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID ,  count( product_id )  as number  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url  " +
						" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id  LEFT JOIN  basket  ON soft.soft_id = basket.product_id " +
						"  WHERE  soft.portlettype_id = 0 " +  
				        "  and    soft.site_id = " + site_id   
						+ " group by  basket.product_id , soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID   order by  number  DESC limit " + limit_product_list + " offset "
						+ productlistBean.getOffset();
			*/
			
			
			else if(catalog_id ==SpecialCatalog.OUTPUT_PAGES_AREA_FROM_USERSITE_TO_MAIN_SITE)
			// forum mess
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
					",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
					" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE soft.catalog_id = "
					+ catalog_id
					+ " and  soft.active = true  and   soft.portlettype_id = 0 "  
					+ "   ORDER BY soft.soft_id DESC " ; 

			else if(catalog_id ==SpecialCatalog.CONTENT_WAITING_FOR_APROVEMENT)
			// posted message is for aprove admin 
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
						",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
						"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  " +
						" WHERE  soft.active = true  and   soft.portlettype_id = 0 and   soft.type_id = 1 "  
						+ "   ORDER BY soft.soft_id DESC" ;

			else if(catalog_id ==SpecialCatalog.CONTENT_REJECTED_PUBLICATION)
				// posted message  is no aprove admin 
				query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
							",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1  , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4  , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +				
							"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  " +
							" WHERE soft.active = true  and   soft.portlettype_id = 0 and   soft.type_id = 2 "  
							+ "   ORDER BY soft.soft_id DESC " ;

			else query_productlist = "SELECT DISTINCT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
					",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
					"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE " +
					"( soft.catalog_id = "	+ catalog_id + " and soft.active = true  and   soft.portlettype_id = 0  and   soft.site_id = " + site_id + " ) " +
					" or ( soft.catalog_id = "	+ catalog_id + " and  soft.active = true and   soft.portlettype_id = 0  and   soft.user_id = " + strUser_id + " ) " +
					" or ( soft.catalog_id IN ( SELECT CATALOG_ID FROM catalog WHERE  PARENT_ID =  "	+ catalog_id + " and  soft.active = true and   soft.portlettype_id = 0  )) " +
						"  ORDER BY soft.soft_id DESC " ;
			
			/*
			 * else query_productlist = "SELECT DISTINCT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
					",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name , creteria2.name , creteria3.name , creteria4.name , creteria5.name , creteria6.name , creteria7.name , creteria8.name , creteria9.name , creteria10.name  " +  " , soft.COLOR " +
					"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id  WHERE " +
					"( soft.catalog_id = "	+ catalog_id + " and soft.lang_id = "	+ authorizationPageBean.getLang_id() + " and soft.active = true  and   soft.portlettype_id = 0  and   soft.site_id = " + site_id + " ) " +
					" or ( soft.catalog_id = "	+ catalog_id + " and soft.lang_id = "	+ authorizationPageBean.getLang_id() + " and  soft.active = true and   soft.portlettype_id = 0  and   soft.user_id = " + strUser_id + " ) " +
					" or ( soft.catalog_id IN ( SELECT CATALOG_ID FROM catalog WHERE  PARENT_ID =  "	+ catalog_id + " and soft.lang_id = "	+ authorizationPageBean.getLang_id() + " and  soft.active = true and   soft.portlettype_id = 0  )) " +
						"  ORDER BY soft.soft_id DESC " ;
			 */
			
			/*
			else query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id  WHERE soft.catalog_id = "
				+ catalog_id
				+ " and  soft.active = true  and   soft.portlettype_id = 0  and   soft.site_id = "
				+ site_id
				+ "   ORDER BY soft.soft_id DESC limit 10 offset "
				+ offset;
				*/
			
		}	
		if (productlistBean.getSearchquery() == 1)
		{
			String wordWithBigChar = "" ;
			String wordWithSmallChar = "" ;
			if(productlistBean.getSearchValueArg().length() > 0)
			{
				wordWithBigChar = productlistBean.getSearchValueArg().substring(0,1).toUpperCase() +  productlistBean.getSearchValueArg().substring(1);
				wordWithSmallChar = productlistBean.getSearchValueArg().substring(0,1).toLowerCase() +  productlistBean.getSearchValueArg().substring(1);
			}
			query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
					",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8  , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
					" FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  soft.site_id = "
					+ site_id
					+ " and   soft.active = true and ( soft.name LIKE '%"	+ wordWithSmallChar + "%' or soft.name LIKE '%"	+  wordWithBigChar + "%' )" 
					+ " ORDER BY soft.soft_id DESC " ;
		}
		if (productlistBean.getSearchquery() == 2)
			query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
					",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
					"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  soft.site_id = "
					+ site_id
					+ " and  soft.active = true and ( soft.search = '" + productlistBean.getSearchValueArg().toLowerCase() + "' or soft.search = '" + productlistBean.getSearchValueArg().toUpperCase() 
					+ "' ) and soft.portlettype_id = 0   ORDER BY soft.soft_id DESC " ;
		if (productlistBean.getSearchquery() == 3) {
			query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_s ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
					",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
					"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  soft.site_id = "
					+ site_id
					+ " and   soft.active = true and soft.cost >= 0   ";
			if (authorizationPageBean.getCreteria1_id() != 0)
				query_productlist = query_productlist + " and soft.creteria1_id = "
						+ authorizationPageBean.getCreteria1_id();
			if (authorizationPageBean.getCreteria2_id() != 0)
				query_productlist = query_productlist + " and soft.creteria2_id = "
						+ authorizationPageBean.getCreteria2_id();
			if (authorizationPageBean.getCreteria3_id() != 0)
				query_productlist = query_productlist + " and soft.creteria3_id = "
						+ authorizationPageBean.getCreteria3_id();
			if (authorizationPageBean.getCreteria4_id() != 0)
				query_productlist = query_productlist + " and soft.creteria4_id = "
						+ authorizationPageBean.getCreteria4_id();
			if (authorizationPageBean.getCreteria5_id() != 0)
				query_productlist = query_productlist + " and soft.creteria5_id = "
						+ authorizationPageBean.getCreteria5_id();
			if (authorizationPageBean.getCreteria6_id() != 0)
				query_productlist = query_productlist + " and soft.creteria6_id = "
						+ authorizationPageBean.getCreteria6_id();
			if (authorizationPageBean.getCreteria7_id() != 0)
				query_productlist = query_productlist + " and soft.creteria7_id = "
						+ authorizationPageBean.getCreteria7_id();
			if (authorizationPageBean.getCreteria8_id() != 0)
				query_productlist = query_productlist + " and soft.creteria8_id = "
						+ authorizationPageBean.getCreteria8_id();
			if (authorizationPageBean.getCreteria9_id() != 0)
				query_productlist = query_productlist + " and soft.creteria9_id = "
						+ authorizationPageBean.getCreteria9_id();
			if (authorizationPageBean.getCreteria10_id() != 0)
				query_productlist = query_productlist + " and soft.creteria10_id = "
						+ authorizationPageBean.getCreteria10_id();
			query_productlist = query_productlist
					+ " ORDER BY soft.soft_id DESC " ;
			
			if(query_productlist.indexOf("creteria") == -1 )
			{
				productlistBean.setQuery_productlist(query_productlist) ;
				return list ;
			}
		}

		if (productlistBean.getSearchquery() == 4) // hotel serach
		{
			query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b , soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
					",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
					"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id    WHERE  soft.site_id = "
					+ site_id
					+ " and   soft.active = true  and soft.cost >= 0   ";
			if (authorizationPageBean.getCreteria1_id() != 0)
				query_productlist = query_productlist + " and soft.creteria1_id = "
						+ authorizationPageBean.getCreteria1_id();
			if (authorizationPageBean.getCreteria2_id() != 0)
				query_productlist = query_productlist + " and soft.creteria2_id = "
						+ authorizationPageBean.getCreteria2_id();
			if (authorizationPageBean.getCreteria3_id() != 0)
				query_productlist = query_productlist + " and soft.creteria3_id = "
						+ authorizationPageBean.getCreteria3_id();
			if (authorizationPageBean.getCreteria4_id()!= 0)
				query_productlist = query_productlist + " and soft.creteria4_id = "
						+ authorizationPageBean.getCreteria4_id();
			if (authorizationPageBean.getCreteria5_id() != 0)
				query_productlist = query_productlist + " and soft.creteria5_id = "
						+ authorizationPageBean.getCreteria5_id();
			if (authorizationPageBean.getCreteria6_id() != 0)
				query_productlist = query_productlist + " and soft.creteria6_id = "
						+ authorizationPageBean.getCreteria6_id();
			if (authorizationPageBean.getCreteria7_id() != 0)
				query_productlist = query_productlist + " and soft.creteria7_id = "
						+ authorizationPageBean.getCreteria7_id();
			if (authorizationPageBean.getCreteria8_id() != 0)
				query_productlist = query_productlist + " and soft.creteria8_id = "
						+ authorizationPageBean.getCreteria8_id();
			if (authorizationPageBean.getCreteria9_id()!= 0)
				query_productlist = query_productlist + " and soft.creteria9_id = "
						+ authorizationPageBean.getCreteria9_id();
			if (authorizationPageBean.getCreteria10_id() != 0)
				query_productlist = query_productlist + " and soft.creteria10_id = "
						+ authorizationPageBean.getCreteria10_id();
			if (catalog_id != 0)
				query_productlist = query_productlist + " and soft.catalog_id = " + catalog_id;
			if (authorizationPageBean.getYearfrom_id() != 0
					&& authorizationPageBean.getMountfrom_id() != 0
					&& authorizationPageBean.getDayfrom_id() != 0
					&& authorizationPageBean.getYearto_id()!= 0
					&& authorizationPageBean.getMountto_id() != 0
					&& authorizationPageBean.getDayto_id()!= 0) {
				authorizationPageBean.getCalendar().set((int)(authorizationPageBean.getYearfrom_id()), (int)(authorizationPageBean.getMountfrom_id()) - 1, (int)(authorizationPageBean.getDayfrom_id()), 0, 1);
				long fromDate = authorizationPageBean.getCalendar().getTimeInMillis();
				// System.out.println(calendar.getTime());
				authorizationPageBean.getCalendar().set((int)(authorizationPageBean.getYearto_id()), (int)(authorizationPageBean.getMountto_id()) - 1, (int)(authorizationPageBean.getDayto_id()),
						23, 59);
				long toDate = authorizationPageBean.getCalendar().getTimeInMillis();
				// System.out.println(calendar.getTime());
				// query = query + " and calendar.holddate > " + fromDate + "
				// and calendar.holddate < " + toDate + " " ;
				query_productlist = query_productlist
						+ "  and soft.soft_id  NOT IN ( SELECT  soft.soft_id  FROM soft LEFT  JOIN calendar  ON soft.soft_id = calendar.soft_id WHERE  soft.site_id  = "
						+ site_id
						+ "  and   soft.active = true  and calendar.holddate >=  "
						+ fromDate + " and calendar.holddate <=  " + toDate
						+ " ) ";
			}

			query_productlist = query_productlist
					+ " ORDER BY soft.soft_id DESC ";

			if(query_productlist.indexOf("creteria") == -1 )
			{
				productlistBean.setQuery_productlist(query_productlist) ;
				return list ;
			}
		}

		if (productlistBean.getSearchquery() == 5) {
			// criteria with catalog_id
			query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
			",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
			"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
			"soft.site_id = "+ site_id+ " and  " +
			"soft.catalog_id = "+ catalog_id + " and  " +
			" soft.active = true " ;
			//if (AuthorizationPageBean.getFromCost() > 0 || AuthorizationPageBean.getFromCost()  >  0  )	query_productlist = query_productlist +	" and   soft.cost > " + "";
			if (authorizationPageBean.getFromCost().longValue() > 0 || authorizationPageBean.getFromCost().longValue()  >  0  )	query_productlist = query_productlist +	" and   soft.cost >= " + authorizationPageBean.getFromCost() ;
			
			if (authorizationPageBean.getToCost().longValue() > 0 || authorizationPageBean.getToCost().longValue()  >  0 )	query_productlist = query_productlist +	" and soft.cost <=  " + authorizationPageBean.getToCost() ;
			
			if (authorizationPageBean.getCreteria1_id() != 0)
				query_productlist = query_productlist + " and soft.creteria1_id = "
						+ authorizationPageBean.getCreteria1_id();
			if (authorizationPageBean.getCreteria2_id() != 0)
				query_productlist = query_productlist + " and soft.creteria2_id = "
						+ authorizationPageBean.getCreteria2_id();
			if (authorizationPageBean.getCreteria3_id() != 0)
				query_productlist = query_productlist + " and soft.creteria3_id = "
						+ authorizationPageBean.getCreteria3_id();
			if (authorizationPageBean.getCreteria4_id() != 0)
				query_productlist = query_productlist + " and soft.creteria4_id = "
						+ authorizationPageBean.getCreteria4_id();
			if (authorizationPageBean.getCreteria5_id() != 0)
				query_productlist = query_productlist + " and soft.creteria5_id = "
						+ authorizationPageBean.getCreteria5_id();
			if (authorizationPageBean.getCreteria6_id() != 0)
				query_productlist = query_productlist + " and soft.creteria6_id = "
						+ authorizationPageBean.getCreteria6_id();
			if (authorizationPageBean.getCreteria7_id() != 0)
				query_productlist = query_productlist + " and soft.creteria7_id = "
						+ authorizationPageBean.getCreteria7_id();
			if (authorizationPageBean.getCreteria8_id() != 0)
				query_productlist = query_productlist + " and soft.creteria8_id = "
						+ authorizationPageBean.getCreteria8_id();
			if (authorizationPageBean.getCreteria9_id() != 0)
				query_productlist = query_productlist + " and soft.creteria9_id = "
						+ authorizationPageBean.getCreteria9_id();
			if (authorizationPageBean.getCreteria10_id() != 0)
				query_productlist = query_productlist + " and soft.creteria10_id = "
						+ authorizationPageBean.getCreteria10_id();
			query_productlist = query_productlist
					+ " ORDER BY soft.soft_id DESC ";
			
			if(query_productlist.indexOf("creteria") == -1 )
			{
				productlistBean.setQuery_productlist(query_productlist) ;
				return list ;
			}
		}

		
		
		if (productlistBean.getSearchquery() == 6) {
			// Search only by criteria
			// criteria without catalog_id
			query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
			",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
			"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
			"soft.site_id = "+ site_id+ " and soft.portlettype_id = 0  and " +
			" soft.active = true " ; 
			
			if (authorizationPageBean.getCreteria1_id() != 0)
				query_productlist = query_productlist + " and soft.creteria1_id = "
						+ authorizationPageBean.getCreteria1_id();
			if (authorizationPageBean.getCreteria2_id() != 0)
				query_productlist = query_productlist + " and soft.creteria2_id = "
						+ authorizationPageBean.getCreteria2_id();
			if (authorizationPageBean.getCreteria3_id() != 0)
				query_productlist = query_productlist + " and soft.creteria3_id = "
						+ authorizationPageBean.getCreteria3_id();
			if (authorizationPageBean.getCreteria4_id() != 0)
				query_productlist = query_productlist + " and soft.creteria4_id = "
						+ authorizationPageBean.getCreteria4_id();
			if (authorizationPageBean.getCreteria5_id() != 0)
				query_productlist = query_productlist + " and soft.creteria5_id = "
						+ authorizationPageBean.getCreteria5_id();
			if (authorizationPageBean.getCreteria6_id() != 0)
				query_productlist = query_productlist + " and soft.creteria6_id = "
						+ authorizationPageBean.getCreteria6_id();
			if (authorizationPageBean.getCreteria7_id() != 0)
				query_productlist = query_productlist + " and soft.creteria7_id = "
						+ authorizationPageBean.getCreteria7_id();
			if (authorizationPageBean.getCreteria8_id() != 0)
				query_productlist = query_productlist + " and soft.creteria8_id = "
						+ authorizationPageBean.getCreteria8_id();
			if (authorizationPageBean.getCreteria9_id() != 0)
				query_productlist = query_productlist + " and soft.creteria9_id = "
						+ authorizationPageBean.getCreteria9_id();
			if (authorizationPageBean.getCreteria10_id() != 0)
				query_productlist = query_productlist + " and soft.creteria10_id = "
						+ authorizationPageBean.getCreteria10_id();
			query_productlist = query_productlist
					+ " ORDER BY soft.soft_id DESC " ;
			if(query_productlist.indexOf("creteria") == -1 )
			{
				productlistBean.setQuery_productlist(query_productlist) ;
				return list ;
			}
			
		}
		//arenda
		if (productlistBean.getSearchquery() == 7) {
			// criteria with catalog_id
			query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
			",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10  " +  " , soft.COLOR " +
			"   FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
			"soft.site_id = "+ site_id+ " and soft.portlettype_id = 0  and " +
			" soft.active = true " ;

			//Number.this.
			
			if (  authorizationPageBean.getFromCost().longValue()   >  0  )	query_productlist = query_productlist +	" and   soft.cost >= " + authorizationPageBean.getFromCost() ; 
			
			if ( authorizationPageBean.getToCost().longValue()  >  0 )	query_productlist = query_productlist +	" and soft.cost <=  " + authorizationPageBean.getToCost() ;

			
			
			if (authorizationPageBean.getCreteria1_id() > 0)
				query_productlist = query_productlist + " and soft.creteria1_id = "
						+ authorizationPageBean.getCreteria1_id();
			if (authorizationPageBean.getCreteria2_id() > 0)
				query_productlist = query_productlist + " and soft.creteria2_id = "
						+ authorizationPageBean.getCreteria2_id();
			if (authorizationPageBean.getCreteria3_id() > 0)
				query_productlist = query_productlist + " and soft.creteria3_id = "
						+ authorizationPageBean.getCreteria3_id();
			if (authorizationPageBean.getCreteria4_id() > 0)
				query_productlist = query_productlist + " and soft.creteria4_id = "
						+ authorizationPageBean.getCreteria4_id();
			if (authorizationPageBean.getCreteria5_id() > 0)
				query_productlist = query_productlist + " and soft.creteria5_id = "
						+ authorizationPageBean.getCreteria5_id();
			if (authorizationPageBean.getCreteria6_id() > 0)
				query_productlist = query_productlist + " and soft.creteria6_id = "
						+ authorizationPageBean.getCreteria6_id();
			if (authorizationPageBean.getCreteria7_id() > 0)
				query_productlist = query_productlist + " and soft.creteria7_id = "
						+ authorizationPageBean.getCreteria7_id();
			if (authorizationPageBean.getCreteria8_id() > 0)
				query_productlist = query_productlist + " and soft.creteria8_id = "
						+ authorizationPageBean.getCreteria8_id();
			if (authorizationPageBean.getCreteria9_id() > 0)
				query_productlist = query_productlist + " and soft.creteria9_id = "
						+ authorizationPageBean.getCreteria9_id();
			if (authorizationPageBean.getCreteria10_id() >  0)
				query_productlist = query_productlist + " and soft.creteria10_id = "
						+ authorizationPageBean.getCreteria10_id();
			query_productlist = query_productlist
					+ " ORDER BY soft.soft_id DESC " ;
			
			if(query_productlist.indexOf("creteria") == -1 && query_productlist.indexOf("cost") == -1  )
			{
				productlistBean.setQuery_productlist(query_productlist) ;
				return list ;
			}
		}
		
		
		
		// spravochnik
		
		if (productlistBean.getSearchquery() == 8) {
			// criteria with catalog_id
			query_productlist = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url as img_url_s , soft.fulldescription , big_images.img_url as img_url_b ,  soft.user_id  , soft.CDATE , soft.STATISTIC_ID  , soft.MIDLE_BAL1  " +
			",  soft.amount1 ,  soft.amount2 ,  soft.amount3 ,   soft.search2 ,  soft.name2 ,  soft.show_rating1 ,  soft.show_rating2 ,  soft.show_rating3 ,  soft.show_blog ,  soft.jsp_url , creteria1.name as creteria1 , creteria2.name as creteria2 , creteria3.name as creteria3 , creteria4.name as creteria4 , creteria5.name  as creteria5 , creteria6.name as creteria6 , creteria7.name as creteria7 , creteria8.name as creteria8 , creteria9.name as creteria9 , creteria10.name as creteria10 " +  " , soft.COLOR " +
			"  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON  soft.file_id = file.file_id  LEFT  JOIN  creteria1  ON soft.creteria1_id  = creteria1.creteria1_id  LEFT  JOIN  creteria2  ON soft.creteria2_id  = creteria2.creteria2_id  LEFT  JOIN  creteria3  ON soft.creteria3_id  = creteria3.creteria3_id  LEFT  JOIN  creteria4  ON soft.creteria4_id  = creteria4.creteria4_id  LEFT  JOIN  creteria5  ON soft.creteria5_id  = creteria5.creteria5_id  LEFT  JOIN  creteria6  ON soft.creteria6_id  = creteria6.creteria6_id  LEFT  JOIN  creteria7  ON soft.creteria7_id  = creteria7.creteria7_id  LEFT  JOIN  creteria8  ON soft.creteria8_id  = creteria8.creteria8_id  LEFT  JOIN  creteria9  ON soft.creteria9_id  = creteria9.creteria9_id  LEFT  JOIN  creteria10  ON soft.creteria10_id  = creteria10.creteria10_id   WHERE  " +
			"soft.site_id = "+ site_id+ " and soft.portlettype_id = 0  and  " +
			" soft.active = true " ;
			
			if (authorizationPageBean.getCreteria1_id()!= 0)
				query_productlist = query_productlist + " and soft.creteria1_id = "
						+ authorizationPageBean.getCreteria1_id();
			if (authorizationPageBean.getCreteria2_id() != 0)
				query_productlist = query_productlist + " and soft.creteria2_id = "
						+ authorizationPageBean.getCreteria2_id();
			if (authorizationPageBean.getCreteria3_id() != 0)
				query_productlist = query_productlist + " and soft.creteria3_id = "
						+ authorizationPageBean.getCreteria3_id();
			
			if (authorizationPageBean.getCreteria4_id() != 0) 
			{
				query_productlist = query_productlist + " and ( soft.creteria4_id = " + authorizationPageBean.getCreteria4_id();
				query_productlist = query_productlist + " or soft.creteria6_id = " + authorizationPageBean.getCreteria4_id();
				query_productlist = query_productlist + " or soft.creteria8_id = " + authorizationPageBean.getCreteria4_id() + " ) ";
			}
			
			if (authorizationPageBean.getCreteria5_id() != 0)
			{
				query_productlist = query_productlist + " and ( soft.creteria5_id = "	+ authorizationPageBean.getCreteria5_id();
				query_productlist = query_productlist + " or soft.creteria7_id = "	+ authorizationPageBean.getCreteria5_id();
				query_productlist = query_productlist + " or soft.creteria9_id = "	+ authorizationPageBean.getCreteria5_id() + " ) ";
			}
			
			
			query_productlist = query_productlist
					+ " ORDER BY soft.soft_id DESC " ;
			
			if(query_productlist.indexOf("creteria") == -1 && query_productlist.indexOf("cost") == -1  )
			{
				productlistBean.setQuery_productlist(query_productlist) ;
				return list ;
			}
		}
		
		
		try 
		{
			list =  Adp.executeQueryList(query_productlist, limit_product_list , productlistBean.getOffset());
			productlistBean.setQuery_productlist(query_productlist) ;
		}
		catch (SQLException ex) 
		{
		log.error(query_productlist,ex);
		}
		catch (Exception ex) 
		{
		log.error(ex);
		}
		finally
		{
		Adp.close();
		}

		return list ;
	}


	
	final public String getProductName(final String product_id) {
		String name = "";
		String query = "SELECT  soft.name  FROM soft  WHERE soft.soft_id = "
				+ product_id;

		QueryManager Adptmp = new QueryManager();
		try 
		{
			Adptmp.executeQuery(query);
			if (Adptmp.rows().size() != 0) name = (String) Adptmp.getValueAt(0, 0);
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
			Adptmp.close();
		}

		return name;
	}

	
	final public float getProductCost( final String product_id) {
		float cost = 0;
		String query = "SELECT  soft.cost  FROM soft  WHERE soft.soft_id = " + product_id;

		QueryManager Adptmp = new QueryManager();
		try 
		{
			Adptmp.executeQuery(query);
			if (Adptmp.rows().size() > 0) cost = Float.parseFloat( Adptmp.getValueAt(0, 0));
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
			Adptmp.close();
		}

		return cost;
	}
	
	
	final public String getCatalogId( final String product_id) {
		String name = "";
		String query = "SELECT  soft.catalog_id  FROM soft  WHERE soft.soft_id = "
				+ product_id;

		QueryManager Adptmp = new QueryManager();
		try 
		{
			Adptmp.executeQuery(query);
			if (Adptmp.rows().size() != 0) name = (String) Adptmp.getValueAt(0, 0);
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
			Adptmp.close();
		}
		return name;
	}
	
	/**
	 * 
	 * @param product_id
	 * @return user_id
	 */
	final public int getWhoseProduct(final String product_id) {
		String name = "-1";
		String query = "SELECT  soft.user_id  FROM soft  WHERE soft.soft_id = "
				+ product_id;

		QueryManager Adptmp = new QueryManager();
		try {
			Adptmp.executeQuery(query);
			if (Adptmp.rows().size() != 0)
				name = (String) Adptmp.getValueAt(0, 0);
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
			Adptmp.close();
		}
		
		
		
		return Integer.parseInt(name) ;
	}
	
	/**
	 * 
	 * @param product_id
	 * @return user_id
	 */
	final public String getSiteByProduct(final String product_id) {
		String site_id = "-1";
		String query = "SELECT  soft.site_id  FROM soft  WHERE soft.soft_id = "
				+ product_id;

		QueryManager Adptmp = new QueryManager();
		try {
			Adptmp.executeQuery(query);
			if (Adptmp.rows().size() != 0)
				site_id = (String) Adptmp.getValueAt(0, 0);
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
			Adptmp.close();
		}
		
		
		return site_id ;
		//return Integer.parseInt(site_id) ;
	}
	
	
	final public String getPartCriteria( String _criteriaId , final boolean  isSpace )
	{
		
		try
		{
		if( Long.parseLong(_criteriaId)  < 0 )   _criteriaId = "0" ;
		}
		catch (Exception ex) 
		{
			log.error(ex);
		}
		
		return !isSpace?"":" and catalog_id = " + _criteriaId ;	
	}
	

	final public long getCatalogParentId(final AuthorizationPageBean authorizationPageBeanId) {
		QueryManager tmpAdp = new QueryManager();
		String query = "";
		long  _parent_id = 0 ;
		try 
		{
			
			query = "select a.catalog_id  from catalog a , catalog  b  where   b.parent_id = a.catalog_id  and b.catalog_id = " + authorizationPageBeanId.getCatalog_id()  ;
			tmpAdp.executeQuery(query);
				if ( tmpAdp.rows().size() > 0) 
				{
				    if( ((String) tmpAdp.getValueAt(0, 0)).length() > 0 ) _parent_id =  Long.parseLong((String) tmpAdp.getValueAt(0, 0));
				}
		
				authorizationPageBeanId.setCatalogParent_id( _parent_id < 0?"0":"" +_parent_id ) ;		
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
		
		return _parent_id < 0?0:_parent_id  ;
	}

	final public boolean isNumber(String tmp) {
		if (tmp == null)
			return false;
		String IntField = "0123456789.";
		for (int i = 0; i < tmp.length(); i++) {

			if (IntField.indexOf(tmp.charAt(i)) == -1) {
				if (tmp.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}
	
	

}
