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
import java.sql.SQLException;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


import org.apache.log4j.Logger;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.Currency;
import com.cbsinc.cms.CurrencyHash;
import com.cbsinc.cms.ItemDescriptionBean;
import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.controllers.Layout;
import com.cbsinc.cms.exceptions.LocalException;

public class PolicyFaced extends com.cbsinc.cms.WebControls implements
		java.io.Serializable {
	/**
	 * 
	 */
	 private static final long serialVersionUID = -6657917721785944014L;

	/**
	 * <p>
	 * Title: Content Manager System
	 * </p>
	 * <p>
	 * Description: System building web application develop by Konstantin Grabko.
	 * Konstantin Grabko is Owner and author this code.
	 * Программный код написан Грабко Константином Владимировичем и является его интеллектуальной 
	 * собственностью.
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2008
	 * </p>
	 * <p>
	 * Company: Предприниматель Грабко Константин Владимирович
	 * </p>
	 * 
	 * @author Konstantin Grabko
	 * @version 1.0
	 */

	

	

	
	final static private Logger log = Logger.getLogger(PolicyFaced.class);
	transient final ResourceBundle setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources");
	transient final ResourceBundle sequences_rs = PropertyResourceBundle.getBundle("sequence");
	float fltEnd_amount = (float) 0.01 ;
	
	public PolicyFaced() 
	{
		String amount = setup_resources.getString("pay_for_user_session") ;
		fltEnd_amount =  Float.parseFloat(amount!=null?amount:"0"); 
	}

	
	final public long incrementShowPage(final ItemDescriptionBean policyBean ) throws SQLException {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		long statictic = 0 ;
		String query = "SELECT STATISTIC_ID  FROM soft where soft_id = " + policyBean.getProduct_id() ;
		try {
			Adp.executeQuery(query);
			if(Adp.rows().size() > 0) 
			{
			statictic = Long.parseLong(Adp.getValueAt(0, 0));
			statictic = statictic + 1;
			
			query = "update soft set  STATISTIC_ID = " + statictic + " where soft_id = " +  policyBean.getProduct_id() ;

			Adp.executeUpdate(query);
			}
			Adp.commit();
		} catch (SQLException ex) {
			log.error(ex);
			System.err.println("Method " + "incrementShowPage()");
			System.err.println(query);
			Adp.rollback();
			throw ex;
		} finally {
			Adp.close();
		}
		return statictic ;
	}
	
	final	public long payMoneyForShowPage(final ItemDescriptionBean policyBean , final AuthorizationPageBean authorizationPageBean  , final String userIPAddress  ) throws Exception {
		
		if( authorizationPageBean.getIntLevelUp() == 2 ) return 0 ;
		
		QueryManager Adp = new QueryManager();
		
		long statictic = 0 ;
		long ownerSite = 0 ;
		String query = "" ;
		//query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
		//" FROM tuser  where  levelup_cd = 2 and site_id = "	+ authorizationPageBean.getSite_id() + "" ;
		//query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
		//" FROM tuser  where  levelup_cd = 1 and site_id = "	+ authorizationPageBean.getSite_id() + "" ;
		query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id FROM tuser  where  levelup_cd = 1 " +
				" and login in (	SELECT login  FROM tuser  where  levelup_cd = 2 and site_id = "	+ authorizationPageBean.getSite_id() + "  )" ;
		
		try 
		{
		Adp.BeginTransaction();
		Adp.executeQuery(query);
		
		if (Adp.rows().size() > 0) 
		{
			ownerSite =  Long.parseLong((String) Adp.getValueAt(0, 0));
		}
		else throw new LocalException() ;
		
		
		query = "SELECT STATISTIC_ID  FROM soft where soft_id = " + policyBean.getProduct_id() ;
			Adp.executeQuery(query);
			if (Adp.rows().size() == 0)  throw new LocalException() ;
			statictic = Long.parseLong(Adp.getValueAt(0, 0));
			statictic = statictic + 1;
			
			query = "update soft set  STATISTIC_ID = " + statictic + " where soft_id = " +  policyBean.getProduct_id() ;

			Adp.executeUpdate(query);
			
			//if (orderBean.getAccount_history_id().length() == 0) {
				//int intUser_id = Integer.parseInt(orderBean.getUser_ID());
				float Balans = getBalans(ownerSite);
				
				float fltTotal_amount = (Balans - fltEnd_amount);
				
				float fltWithtaxTotal_amount = fltTotal_amount ;
				//query = "SELECT NEXT VALUE FOR account_hist_id_seq  AS ID  FROM ONE_SEQUENCES";
				query = sequences_rs.getString("account_hist");
				Adp.executeQuery(query);
				String account_history_id = Adp.getValueAt(0, 0);
				String strAccountCurrency_id = "-1";
				query = "SELECT currency_id from account WHERE  user_id = " + ownerSite;
				Adp.executeQuery(query);
				strAccountCurrency_id = Adp.getValueAt(0, 0);
				
				CurrencyHash currencyHash = CurrencyHash.getInstance();
				Currency curr = currencyHash.getCurrency(strAccountCurrency_id);

				query = "insert into account_hist ( id  , "
						+ " user_id ,  order_id ,  add_amount , "
						+ " old_amount  ,  date_input ,  date_end , "
						+ " complete   ,  decsription  , "
						+ " currency_id_add  ,  currency_id_old  , "
						+ " currency_id_total  ,  active  , "
						+ " account_hist.sysdate  ,  total_amount ,  tax  , "
						+ " withtax_total_amount , rate )"
						+ " VALUES " + "( ?  , "
						+ " ?  ,  ? ,  ? , "
						+ " ?  ,  ? ,  ? , "
						+ " ?  ,  ?  , "
						+ " ?  ,  ?  , "
						+ " ?  ,  ?  , "
						+ " ?  ,  ? ,  ?  , "
						+ " ?  , ? )";

				///Adp.executeUpdate(query);
				
				Map args = Adp.getArgs();
				args.put("id" ,  Long.parseLong(account_history_id) );
				args.put("user_id" , ownerSite  );
				args.put("order_id" , 0);
				args.put("add_amount" , fltEnd_amount * -1);
				args.put("old_amount" , Balans);
				args.put("date_input" , new java.util.Date());
				args.put("date_end" , new java.util.Date());
				args.put("complete" , true);
				args.put("decsription" , " Payment  "+ fltEnd_amount +" for user which is visited site from IP  " + userIPAddress );
				args.put("currency_id_add" , Long.parseLong(strAccountCurrency_id));
				args.put("currency_id_old" , Long.parseLong(strAccountCurrency_id));
				args.put("currency_id_total" , Long.parseLong(strAccountCurrency_id));
				args.put("active" , false);
				args.put("account_hist.sysdate" , new java.util.Date());
				args.put("total_amount" , fltTotal_amount);
				args.put("tax" , 1);
				args.put("withtax_total_amount" , fltWithtaxTotal_amount);
				args.put("rate" , curr.getRate());
				Adp.executeInsertWithArgs(query, args);
				
				query = "update account set amount = ?  where  user_id = " + ownerSite ;
				args.clear();
				args.put("amount" , (Balans - fltEnd_amount)  );
				Adp.executeUpdateWithArgs(query, args);
				
			
			Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(ex);
			System.err.println("Method " + "payMoneyForShowPage()");
			System.err.println(query);
			Adp.rollback();
			throw ex;
		}
		catch (LocalException ex) 
		{
			Adp.rollback();
		}
		finally 
		{
			Adp.close();
		}
		
		return statictic ;
	}
	
//public long payMoneyForShowPage_old(final PolicyBean policyBean , final AuthorizationPageBean authorizationPageBean  , String userIPAddress  ) throws SQLException {
//		
//		QueryManager Adp = new QueryManager();
//		Adp.BeginTransaction();
//		long statictic = 0 ;
//		long userMainSite = 0 ;
//		String query = "" ;
//		query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
//		"FROM tuser  where  login = '" + authorizationPageBean.getStrLogin()  + "' and  site_id = " + SiteType.MAIN_SITE  ;
//		try 
//		{
//
//		Adp.executeQuery(query);
//		
//		if (Adp.rows().size() == 1) 
//		{
//			userMainSite =  Integer.parseInt((String) Adp.getValueAt(0, 0));
//		}
//		
//		query = "SELECT STATISTIC_ID  FROM soft where soft_id = " + policyBean.getProduct_id() ;
//			Adp.executeQuery(query);
//			statictic = Long.parseLong(Adp.getValueAt(0, 0));
//			statictic = statictic + 1;
//			
//			query = "update soft set  STATISTIC_ID = " + statictic + " where soft_id = " +  policyBean.getProduct_id() ;
//
//			Adp.executeUpdate(query);
//			
//			//if (orderBean.getAccount_history_id().length() == 0) {
//				//int intUser_id = Integer.parseInt(orderBean.getUser_ID());
//				float Balans = getBalans(userMainSite);
//				
//				float fltTotal_amount = (Balans - fltEnd_amount);
//				
//				float fltWithtaxTotal_amount = fltTotal_amount ;
//				//query = "SELECT NEXT VALUE FOR account_hist_id_seq  AS ID  FROM ONE_SEQUENCES";
//				query = sequences_rs.getString("account_hist");
//				Adp.executeQuery(query);
//				String account_history_id = Adp.getValueAt(0, 0);
//				String strAccountCurrency_id = "-1";
//				query = "SELECT currency_id from account WHERE  user_id = " + userMainSite;
//				Adp.executeQuery(query);
//				strAccountCurrency_id = Adp.getValueAt(0, 0);
//				
//				CurrencyHash currencyHash = CurrencyHash.getInstance();
//				Currency curr = currencyHash.getCurrency(strAccountCurrency_id);
//
//				query = "insert into account_hist " + "(" + " id  , "
//						+ " user_id , " + " order_id , " + " add_amount , "
//						+ " old_amount  , " + " date_input , " + " date_end , "
//						+ " complete   , " + " decsription  , "
//						+ " currency_id_add  , " + " currency_id_old  , "
//						+ " currency_id_total  , " + " active  , "
//						+ " sysdate  , " + " total_amount , " + " tax  , "
//						+ " withtax_total_amount ," + " rate " + ")"
//						+ " VALUES " + "( " + account_history_id + ", "
//						+ userMainSite + ", " + 0 + ", -" + fltEnd_amount + ", "
//						+ Balans + ", " + "now()" + ", " + "now()" + ", "
//						+ "true" + ", '" + " payment  "+ fltEnd_amount +" for user which is visited site from IP  " + userIPAddress + " ', " + strAccountCurrency_id + ", "
//						+ strAccountCurrency_id + ", " + strAccountCurrency_id
//						+ ", " + "false  , " + "now()" + ", " + fltTotal_amount
//						+ ", " + 1 + ", " + fltWithtaxTotal_amount
//						+ ", " + curr.getRate() + " " + ")";
//
//				Adp.executeUpdate(query);
//				query = "UPDATE account SET amount = "+ (Balans - fltEnd_amount) + " WHERE  user_id = " + userMainSite ;
//				Adp.executeUpdate(query);
//			//}
//			
//			Adp.commit();
//		} 
//		catch (SQLException ex) 
//		{
//			log.error(ex);
//			System.err.println("Method " + "incrementShowPage()");
//			System.err.println(query);
//			Adp.rollback();
//			throw ex;
//		}
//		finally 
//		{
//			Adp.close();
//		}
//		
//		return statictic ;
//	}
	
	
	final public void setRatring1(final int bal , final String product_id )  {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		long rating_summ = 0 ;
		long rating_count = 0 ;
		long midle_bal = 0 ;
		String query = "SELECT RATING_SUMM1 , COUNTPOST_RATING1  FROM soft where soft_id = " + product_id ;
		try {
			Adp.executeQuery(query);
			if(Adp.rows().size() > 0)
			{
			rating_summ = Long.parseLong(Adp.getValueAt(0, 0));
			rating_summ = rating_summ + bal;
			rating_count = Long.parseLong(Adp.getValueAt(0, 1));
			rating_count = rating_count + 1;
			midle_bal = rating_summ / rating_count ;
			
			query = "update soft set  RATING_SUMM1 = " + rating_summ + " , COUNTPOST_RATING1 = " + rating_count + " , MIDLE_BAL1 = "+midle_bal+" where soft_id = " +  product_id;
			Adp.executeUpdate(query);
			}
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		finally 
		{
			Adp.close();
		}

	}

	

	
	final public String getRatring1XML(final String product_id )  
	{
		long midle_bal = 0 ;
		int number = 0 ;
		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		String query = "SELECT MIDLE_BAL1  FROM soft where soft_id = " + product_id ;
		try 
		{
		 Adp.executeQuery(query);
		 if(Adp.rows().size() == 0) return "" ;
		 midle_bal = Long.parseLong(Adp.getValueAt(0, 0));
		 table.append("<rating1>\n");
		 for (int i = 0; 10 > i; i++) 
		 {
			 number = i + 1 ;
			if(midle_bal > i)
			{
			table.append("<show_star_"+number+">yes</show_star_"+number+">\n");
			}
			else 
			{
			table.append("<show_star_"+number+">no</show_star_"+number+">\n");
			}
		 }
		table.append("</rating1>\n");
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

	

	/**
	 * Extention info 2 for policy page
	 * 
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id - Установить родителя для записи обязательно
	 * @return
	 * @throws Exception 
	 */

	final public void  mergePolicyBean(final long user_id, final String productId	, final ItemDescriptionBean policyBeanId  ) 
	{
		
		QueryManager Adp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , tuser.FIRST_NAME , tuser.LAST_NAME , tuser.COMPANY , soft.tree_id , show_blog , show_rating1 , show_rating2 , show_rating3 , jsp_url , portlettype_id  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id LEFT  JOIN tuser  ON soft.user_id = tuser.user_id  WHERE  soft.soft_id = " + productId ;

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)	
			{
				/*
				size = (String) Adp.getValueAt(0, 0);

				file_id = position.rows[intRow_id][1];
				// file_id = (String)position.newsAdp.getValueAt(intRow_id , 7) == null
				// ?"":(String)position.newsAdp.getValueAt(intRow_id , 7) ;
				if (file_id.length() > 0)
					file_exist = "true";
				else
					file_exist = "";
				if (position.Adp.getRowCount() == 0) {
					return;
				}
				*/
				
				String file_id = Adp.getValueAt(0, 7) ;
				if (file_id.length() > 0) policyBeanId.setFile_exist("true");
				else policyBeanId.setFile_exist("");
				
				policyBeanId.setProduct_id(Adp.getValueAt(0, 0));
				
				String portlettype_id = Adp.getValueAt(0, 28).toLowerCase();
				if(portlettype_id != null && portlettype_id.length() > 0)
				policyBeanId.setPortlettype_id(Long.parseLong(portlettype_id));
				//policyBeanId.setParent_product_id(policyBeanId.getProduct_id()) ;
				//593
				String parentId = Adp.getValueAt(0, 22);
				if(parentId.equals("") )parentId = Adp.getValueAt(0, 0) ;
				policyBeanId.setParent_product_id(parentId) ;
				policyBeanId.setProductName( Adp.getValueAt(0, 1));
				
				String show_blog = Adp.getValueAt(0, 23).toLowerCase();
				policyBeanId.setStrShow_forum(show_blog);

				String show_ratimg1 = Adp.getValueAt(0, 24).toLowerCase();
				policyBeanId.setStrShow_ratimg1(show_ratimg1);

				String show_ratimg2 = Adp.getValueAt(0, 25).toLowerCase();
				policyBeanId.setStrShow_ratimg2(show_ratimg2);

				String show_ratimg3 = Adp.getValueAt(0, 26).toLowerCase();
				policyBeanId.setStrShow_ratimg3(show_ratimg3);
				
				String jsp_url = Adp.getValueAt(0, 27).toLowerCase();
				policyBeanId.setJsp_url(jsp_url);

				
				//policyBeanId.setProductURL("downloadservletbyrowid?row=" + 0);
				policyBeanId.setProductURL("downloadservletbyrowid?productid=" + policyBeanId.getProduct_id());
				policyBeanId.setImg_url( Adp.getValueAt(0, 13));
				if (policyBeanId.getImg_url() != null)
					policyBeanId.setImgURL(policyBeanId.getImg_url());
				else policyBeanId.setImgURL("images/Folder.jpg");
				policyBeanId.setProductDescription(Adp.getValueAt(0, 14));
				int http = policyBeanId.getProductDescription().indexOf("http://") ; 
				if(http != -1)
				{
				int space = policyBeanId.getProductDescription().indexOf(" ",http) ;
				if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",http) ;
				if(space != -1 )
				{
				String link = policyBeanId.getProductDescription().substring(http,space) ;
				String newlink = " <a href='"+link+"' >" + link + "</a>  " ;
				policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink) );
				}
				
				}
				
				int ftp = policyBeanId.getProductDescription().indexOf("ftp://") ; 
				if(ftp != -1)
				{
					int space = policyBeanId.getProductDescription().indexOf(" ",ftp) ;
					if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",ftp) ;
					if(space != -1 )
					{
						String link = policyBeanId.getProductDescription().substring(ftp,space) ;
						//String newlink = "<![CDATA[ <a href='"+link+"' >" + link + "</a> ]]> " ;
						 String newlink = " <a href='"+link+"' >" + link + "</a> " ;
						 policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink)) ;
					}
				}

				int email = policyBeanId.getProductDescription().indexOf("mailto://") ; 
				if(email != -1)
				{
					int space = policyBeanId.getProductDescription().indexOf(" ",email) ;
					if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",email) ;
					if(space != -1 )
					{
				String link = policyBeanId.getProductDescription().substring(email,space) ;
				//String newlink = "<![CDATA[ <a href='"+link+"' >" + link + "</a> ]]> " ;
				String newlink = " <a href='"+link+"' >" + link + "</a>  " ;
				policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink) );
					}
				}
				
				policyBeanId.setBigimgURL(Adp.getValueAt(0, 15));
				policyBeanId.setProductVersion(Adp.getValueAt(0, 3));
				policyBeanId.setProductCost( Adp.getValueAt(0, 4));
				policyBeanId.setStrCDate(Adp.getValueAt(0, 17));
				//policyBeanId.setStrCDate(policyBeanId.getStrCDate().substring(0,10)) ;
				policyBeanId.setStatistic(Adp.getValueAt(0, 18));
				
				policyBeanId.setCreator_info_user_id(Adp.getValueAt(0, 16)) ;
				//creator_info_user_id= (String) query_result.getValueAt(intRow_id, 16);
			
				String currency_id = (String) Adp.getValueAt(0, 5);
				CurrencyHash currencyHash = CurrencyHash.getInstance();
				Currency curr = currencyHash.getCurrency(currency_id);
				if (curr == null)
					throw new Exception("Currency curr == null and currency_id " + currency_id );
				policyBeanId.setCurrency_cd(curr.getCode());
				policyBeanId.setCurrency_desc(currencyHash.getCurrency_decs(currency_id));
				// Not momey not href
				if(getBalans(user_id) < 1 ) policyBeanId.setProductURL("");
				

			}
				
			///Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			//Adp.rollback();
			
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			//Adp.rollback();
			
		} 
		finally 
		{
			Adp.close();
		}


		
	}


	
	/**
	 * Extention info 2 for policy page
	 * 
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id - Установить родителя для записи обязательно
	 * @return
	 * @throws Exception 
	 */

	final public void  mergePolicyBeanForAboutPage(final String siteId	, final ItemDescriptionBean policyBeanId  ) 
	{
		
		QueryManager Adp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , tuser.FIRST_NAME , tuser.LAST_NAME , tuser.COMPANY , soft.tree_id ,  show_blog , show_rating1 , show_rating2 , show_rating3 , jsp_url  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id LEFT  JOIN tuser  ON soft.user_id = tuser.user_id  WHERE  soft.site_id = " + siteId  + " and soft.portlettype_id = " + Layout.PAGE_ABOUT_COMPANY ;

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)	
			{
				/*
				size = (String) Adp.getValueAt(0, 0);

				file_id = position.rows[intRow_id][1];
				// file_id = (String)position.newsAdp.getValueAt(intRow_id , 7) == null
				// ?"":(String)position.newsAdp.getValueAt(intRow_id , 7) ;
				if (file_id.length() > 0)
					file_exist = "true";
				else
					file_exist = "";
				if (position.Adp.getRowCount() == 0) {
					return;
				}
				*/
				
				String file_id = Adp.getValueAt(0, 7) ;
				if (file_id.length() > 0) policyBeanId.setFile_exist("true");
				else policyBeanId.setFile_exist("");
				
				policyBeanId.setProduct_id(Adp.getValueAt(0, 0));
				//policyBeanId.setParent_product_id(policyBeanId.getProduct_id()) ;
				//593
				String parentId = Adp.getValueAt(0, 22);
				if(parentId.equals("") )parentId = Adp.getValueAt(0, 0) ;
				policyBeanId.setParent_product_id(parentId) ;
				
				String show_blog = Adp.getValueAt(0, 23).toLowerCase();
				policyBeanId.setStrShow_forum(show_blog);

				String show_ratimg1 = Adp.getValueAt(0, 24).toLowerCase();
				policyBeanId.setStrShow_ratimg1(show_ratimg1);

				String show_ratimg2 = Adp.getValueAt(0, 25).toLowerCase();
				policyBeanId.setStrShow_ratimg2(show_ratimg2);

				String show_ratimg3 = Adp.getValueAt(0, 26).toLowerCase();
				policyBeanId.setStrShow_ratimg3(show_ratimg3);
				
				String jsp_url = Adp.getValueAt(0, 27).toLowerCase();
				policyBeanId.setJsp_url(jsp_url);
				
				policyBeanId.setProductName( Adp.getValueAt(0, 1));
				//policyBeanId.setProductURL("downloadservletbyrowid?row=" + 0);
				policyBeanId.setProductURL("downloadservletbyrowid?productid=" + policyBeanId.getProduct_id());
				policyBeanId.setImg_url( Adp.getValueAt(0, 13));
				if (policyBeanId.getImg_url() != null)
					policyBeanId.setImgURL(policyBeanId.getImg_url());
				else policyBeanId.setImgURL("images/Folder.jpg");
				policyBeanId.setProductDescription(Adp.getValueAt(0, 14));
				int http = policyBeanId.getProductDescription().indexOf("http://") ; 
				if(http != -1)
				{
				int space = policyBeanId.getProductDescription().indexOf(" ",http) ;
				if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",http) ;
				if(space != -1 )
				{
				String link = policyBeanId.getProductDescription().substring(http,space) ;
				String newlink = " <a href='"+link+"' >" + link + "</a>  " ;
				policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink) );
				}
				
				}
				
				int ftp = policyBeanId.getProductDescription().indexOf("ftp://") ; 
				if(ftp != -1)
				{
					int space = policyBeanId.getProductDescription().indexOf(" ",ftp) ;
					if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",ftp) ;
					if(space != -1 )
					{
						String link = policyBeanId.getProductDescription().substring(ftp,space) ;
						//String newlink = "<![CDATA[ <a href='"+link+"' >" + link + "</a> ]]> " ;
						 String newlink = " <a href='"+link+"' >" + link + "</a> " ;
						 policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink)) ;
					}
				}

				int email = policyBeanId.getProductDescription().indexOf("mailto://") ; 
				if(email != -1)
				{
					int space = policyBeanId.getProductDescription().indexOf(" ",email) ;
					if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",email) ;
					if(space != -1 )
					{
				String link = policyBeanId.getProductDescription().substring(email,space) ;
				//String newlink = "<![CDATA[ <a href='"+link+"' >" + link + "</a> ]]> " ;
				String newlink = " <a href='"+link+"' >" + link + "</a>  " ;
				policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink) );
					}
				}
				
				policyBeanId.setBigimgURL(Adp.getValueAt(0, 15));
				policyBeanId.setProductVersion(Adp.getValueAt(0, 3));
				policyBeanId.setProductCost( Adp.getValueAt(0, 4));
				policyBeanId.setStrCDate(Adp.getValueAt(0, 17));
				//policyBeanId.setStrCDate(policyBeanId.getStrCDate().substring(0,10)) ;
				policyBeanId.setStatistic(Adp.getValueAt(0, 18));
				
				policyBeanId.setCreator_info_user_id(Adp.getValueAt(0, 16)) ;
				//creator_info_user_id= (String) query_result.getValueAt(intRow_id, 16);
			
				String currency_id = (String) Adp.getValueAt(0, 5);
				CurrencyHash currencyHash = CurrencyHash.getInstance();
				Currency curr = currencyHash.getCurrency(currency_id);
				if (curr == null)	throw new Exception("Currency curr == null and currency_id = " + currency_id );
				policyBeanId.setCurrency_cd(curr.getCode());
				policyBeanId.setCurrency_desc(currencyHash.getCurrency_decs(currency_id));
				// Not momey not href
				//if(getBalans(user_id) < 1 ) policyBeanId.setProductURL("");
				

			}
				
			///Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			//Adp.rollback();
			
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			//Adp.rollback();
			
		} 
		finally 
		{
			Adp.close();
		}


		
	}


	
	/**
	 * Extention info 2 for policy page
	 * 
	 * @param strUser_id
	 * @param site_id
	 * @param tree_id - Установить родителя для записи обязательно
	 * @return
	 * @throws Exception 
	 */

	final public void  mergePolicyBeanForPayPageInfo(final String siteId	, final ItemDescriptionBean policyBeanId  ) 
	{
		
		QueryManager Adp = new QueryManager();
		String query = "";

		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , tuser.FIRST_NAME , tuser.LAST_NAME , tuser.COMPANY , soft.tree_id ,  show_blog , show_rating1 , show_rating2 , show_rating3 , jsp_url  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id LEFT  JOIN tuser  ON soft.user_id = tuser.user_id  WHERE  soft.site_id = " + siteId  + " and soft.portlettype_id = " + Layout.PAGE_ABOUT_PAY  ;

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)	
			{
				/*
				size = (String) Adp.getValueAt(0, 0);

				file_id = position.rows[intRow_id][1];
				// file_id = (String)position.newsAdp.getValueAt(intRow_id , 7) == null
				// ?"":(String)position.newsAdp.getValueAt(intRow_id , 7) ;
				if (file_id.length() > 0)
					file_exist = "true";
				else
					file_exist = "";
				if (position.Adp.getRowCount() == 0) {
					return;
				}
				*/
				
				String file_id = Adp.getValueAt(0, 7) ;
				if (file_id.length() > 0) policyBeanId.setFile_exist("true");
				else policyBeanId.setFile_exist("");
				
				policyBeanId.setProduct_id(Adp.getValueAt(0, 0));
				//policyBeanId.setParent_product_id(policyBeanId.getProduct_id()) ;
				//593
				String parentId = Adp.getValueAt(0, 22);
				if(parentId.equals("") )parentId = Adp.getValueAt(0, 0) ;
				policyBeanId.setParent_product_id(parentId) ;
				
				String show_blog = Adp.getValueAt(0, 23).toLowerCase();
				policyBeanId.setStrShow_forum(show_blog);

				String show_ratimg1 = Adp.getValueAt(0, 24).toLowerCase();
				policyBeanId.setStrShow_ratimg1(show_ratimg1);

				String show_ratimg2 = Adp.getValueAt(0, 25).toLowerCase();
				policyBeanId.setStrShow_ratimg2(show_ratimg2);

				String show_ratimg3 = Adp.getValueAt(0, 26).toLowerCase();
				policyBeanId.setStrShow_ratimg3(show_ratimg3);
				
				String jsp_url = Adp.getValueAt(0, 27).toLowerCase();
				policyBeanId.setJsp_url(jsp_url);
				
				policyBeanId.setProductName( Adp.getValueAt(0, 1));
				//policyBeanId.setProductURL("downloadservletbyrowid?row=" + 0);
				policyBeanId.setProductURL("downloadservletbyrowid?productid=" + policyBeanId.getProduct_id());
				policyBeanId.setImg_url( Adp.getValueAt(0, 13));
				if (policyBeanId.getImg_url() != null)
					policyBeanId.setImgURL(policyBeanId.getImg_url());
				else policyBeanId.setImgURL("images/Folder.jpg");
				policyBeanId.setProductDescription(Adp.getValueAt(0, 14));
				int http = policyBeanId.getProductDescription().indexOf("http://") ; 
				if(http != -1)
				{
				int space = policyBeanId.getProductDescription().indexOf(" ",http) ;
				if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",http) ;
				if(space != -1 )
				{
				String link = policyBeanId.getProductDescription().substring(http,space) ;
				String newlink = " <a href='"+link+"' >" + link + "</a>  " ;
				policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink) );
				}
				
				}
				
				int ftp = policyBeanId.getProductDescription().indexOf("ftp://") ; 
				if(ftp != -1)
				{
					int space = policyBeanId.getProductDescription().indexOf(" ",ftp) ;
					if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",ftp) ;
					if(space != -1 )
					{
						String link = policyBeanId.getProductDescription().substring(ftp,space) ;
						//String newlink = "<![CDATA[ <a href='"+link+"' >" + link + "</a> ]]> " ;
						 String newlink = " <a href='"+link+"' >" + link + "</a> " ;
						 policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink)) ;
					}
				}

				int email = policyBeanId.getProductDescription().indexOf("mailto://") ; 
				if(email != -1)
				{
					int space = policyBeanId.getProductDescription().indexOf(" ",email) ;
					if(space == -1 )space = policyBeanId.getProductDescription().indexOf("/t",email) ;
					if(space != -1 )
					{
				String link = policyBeanId.getProductDescription().substring(email,space) ;
				//String newlink = "<![CDATA[ <a href='"+link+"' >" + link + "</a> ]]> " ;
				String newlink = " <a href='"+link+"' >" + link + "</a>  " ;
				policyBeanId.setProductDescription(policyBeanId.getProductDescription().replaceAll(link,newlink) );
					}
				}
				
				policyBeanId.setBigimgURL(Adp.getValueAt(0, 15));
				policyBeanId.setProductVersion(Adp.getValueAt(0, 3));
				policyBeanId.setProductCost( Adp.getValueAt(0, 4));
				policyBeanId.setStrCDate(Adp.getValueAt(0, 17));
				//policyBeanId.setStrCDate(policyBeanId.getStrCDate().substring(0,10)) ;
				policyBeanId.setStatistic(Adp.getValueAt(0, 18));
				
				policyBeanId.setCreator_info_user_id(Adp.getValueAt(0, 16)) ;
				//creator_info_user_id= (String) query_result.getValueAt(intRow_id, 16);
			
				String currency_id = (String) Adp.getValueAt(0, 5);
				CurrencyHash currencyHash = CurrencyHash.getInstance();
				Currency curr = currencyHash.getCurrency(currency_id);
				if (curr == null) 	throw new Exception("Currency curr == null and currency_id = " + currency_id );
				policyBeanId.setCurrency_cd(curr.getCode());
				policyBeanId.setCurrency_desc(currencyHash.getCurrency_decs(currency_id));
				// Not momey not href
				//if(getBalans(user_id) < 1 ) policyBeanId.setProductURL("");
				

			}
				
			///Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			//Adp.rollback();
			
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			//Adp.rollback();
			
		} 
		finally 
		{
			Adp.close();
		}


		
	}

	
	final public String  getPayPageInfoId(final String siteId ) 
	{
		QueryManager Adp = new QueryManager();
		String query = "";
		String product_id = "0" ;
		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , tuser.FIRST_NAME , tuser.LAST_NAME , tuser.COMPANY , soft.tree_id ,  show_blog , show_rating1 , show_rating2 , show_rating3 , jsp_url  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id LEFT  JOIN tuser  ON soft.user_id = tuser.user_id  WHERE  soft.site_id = " + siteId  + " and soft.portlettype_id = " + Layout.PAGE_ABOUT_PAY ; ;

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)	
			{
				product_id = Adp.getValueAt(0, 0) ;
			}
				
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			//Adp.rollback();
			
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			//Adp.rollback();
			
		} 
		finally 
		{
			Adp.close();
		}

      return product_id ;
		
	}

	
	final public String  getAboutPageId(final String siteId ) 
	{
		QueryManager Adp = new QueryManager();
		String query = "";
		String product_id = "0" ;
		query = "SELECT  soft.soft_id, soft.name,soft.description, soft.version, soft.cost, soft.currency, soft.serial_nubmer, file.file_id, soft.type_id, soft.active , soft.phonetype_id , soft.progname_id  , soft.image_id , images.img_url , soft.fulldescription , big_images.img_url , soft.user_id  , soft.CDATE , soft.STATISTIC_ID , tuser.FIRST_NAME , tuser.LAST_NAME , tuser.COMPANY , soft.tree_id ,  show_blog , show_rating1 , show_rating2 , show_rating3 , jsp_url  FROM soft LEFT  JOIN images ON soft.image_id = images.image_id  LEFT  JOIN big_images ON soft.bigimage_id = big_images.big_images_id  LEFT  JOIN file  ON soft.file_id = file.file_id LEFT  JOIN tuser  ON soft.user_id = tuser.user_id  WHERE  soft.site_id = " + siteId  + " and soft.portlettype_id = " + Layout.PAGE_ABOUT_COMPANY ; 

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)	
			{
				product_id = Adp.getValueAt(0, 0) ;
			}
				
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			//Adp.rollback();
			
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			//Adp.rollback();
			
		} 
		finally 
		{
			Adp.close();
		}

      return product_id ;
		
	}
	

	// --------- Business logic functionality start -----

	final public int stringToInt(final String s) {
		int i;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			i = 0;
		}
		return i;
	}


	
	// --------- Business logic functionality end -----

	

}
