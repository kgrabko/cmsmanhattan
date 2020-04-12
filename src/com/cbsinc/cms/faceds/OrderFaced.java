package com.cbsinc.cms.faceds;
/**
 * <p>
 * Title: Content Manager System
 * </p>
 * <p>
 * Description: System building web application develop by Konstantin Grabko.
 * Konstantin Grabko is Owner and author this code.
 * You can not use it and you cannot change without written permission from Konstantin Grabko
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.cbsinc.cms.AccountHistoryBean;
import com.cbsinc.cms.AccountHistoryDetalBean;
import com.cbsinc.cms.Currency;
import com.cbsinc.cms.CurrencyHash;
import com.cbsinc.cms.DeliveryStatus;
import com.cbsinc.cms.OrderBean;
import com.cbsinc.cms.OrderListBean;
import com.cbsinc.cms.PayStatus;
import com.cbsinc.cms.QueryManager;

public class OrderFaced extends com.cbsinc.cms.WebControls implements java.io.Serializable 
{


	transient private static final long serialVersionUID = 8216914184792733202L;

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
	
	transient static private Logger log = Logger.getLogger(OrderFaced.class);
	
	transient ResourceBundle sequences_rs = null ;
	//transient java.util.Calendar calendar;
	//SimpleDateFormat formatter ;
	
	public OrderFaced() 
	{
		if( sequences_rs == null )  sequences_rs = PropertyResourceBundle.getBundle("sequence");
		//calendar = java.util.Calendar.getInstance();
		//formatter = new SimpleDateFormat(datePattern, locale );
	}
	
	public String createOrder(final String user_currency_id , final OrderBean orderBean) throws Exception {
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		
		String query = sequences_rs.getString("orders");
		//String query = "SELECT NEXT VALUE FOR orders_order_id_seq  AS ID  FROM ONE_SEQUENCES";
		try {
			Adp.executeQuery(query);
			orderBean.setOrder_id( Adp.getValueAt(0, 0));
			query = "insert into orders ( order_id ,  cdate ,  end_amount ,  amount ,  tax ,  user_id ,  delivery_amount ,  paystatus_id , deliverystatus_id , currency_id ) " 
			+ " VALUES ( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? ,  ? , ? , ? )" ;
			
			Map args = Adp.getArgs();
			args.put("order_id" ,   Long.valueOf(orderBean.getOrder_id())  );
			args.put("cdate" ,  new java.util.Date()  );
			args.put("end_amount" ,  0  );
			args.put("amount" ,  0  );
			args.put("tax" ,  0 );
			args.put("user_id" ,   Long.valueOf(orderBean.getUser_ID()) );
			args.put("delivery_amount" ,  0  );
			args.put("paystatus_id" ,  PayStatus.CREATE_PAYMENT  );
			args.put("deliverystatus_id" ,   DeliveryStatus.FILLING_BASKET  );
			args.put("currency_id" ,   Long.valueOf(user_currency_id)  );
			
			Adp.executeInsertWithArgs(query, args);
			
			Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}
		return orderBean.getOrder_id();
	}

	
	
	
	
	
	
	public String deleteOrder(final String basket_id , final OrderBean orderBean) throws Exception {
		if (basket_id == null || basket_id.length() == 0) return "";
		if (orderBean.getorder_paystatus().compareTo("0") != 0)	return orderBean.getOrder_id();
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		float fltProduct_amount = 0;
		float fltOrder_amount = 0;
		float fltEnd_order_amount = 0;
		String strProduct_id = "";
		try {
			query = "select  product_id   from  basket where basket_id = " + basket_id;
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0)
				strProduct_id = (String) Adp.getValueAt(0, 0);
			else 
			{
				return orderBean.getOrder_id();
			}
			query = "select cost  from soft  where soft_id = " + strProduct_id;
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0)
				fltProduct_amount = (new Float((String) Adp.getValueAt(0, 0)))
						.floatValue(); // + " " + (String)Adp.getValueAt(0,1) ;
			query = "select amount , end_amount from orders  where order_id = "	+ orderBean.getOrder_id();
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) {
				fltOrder_amount = (new Float((String) Adp.getValueAt(0, 0))).floatValue(); // + " " + (String)Adp.getValueAt(0,1) ;
				fltEnd_order_amount = (new Float((String) Adp.getValueAt(0, 1))).floatValue(); // + " " + (String)Adp.getValueAt(0,1) ;
			}

			int quantity = 1 ;
			query = "select quantity  from  basket where basket_id = " + basket_id;
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0)
			{
				String value  = (String) Adp.getValueAt(0, 0);
				if(value != null) quantity = Integer.parseInt(value) ;
			}
			
			
			query = "update orders  set end_amount =  ? , amount = ?  where order_id = " + orderBean.getOrder_id();
			//Adp.executeUpdate(query);
			Map args = Adp.getArgs();
			args.put("end_amount" , (double)(fltEnd_order_amount - (fltProduct_amount * quantity ) )  );
			args.put("amount" , (double)(fltOrder_amount - (fltProduct_amount * quantity ))  );
			Adp.executeUpdateWithArgs(query, args);
			

			query = "delete  from  basket where basket_id = " + basket_id;
			Adp.executeUpdate(query);

			query = "select  product_id   from  basket where basket_id = "	+ basket_id;
			Adp.executeQuery(query);
			
			if (Adp.rows().size() == 0) 
			{
				orderBean.setend_amount("0");
				orderBean.setorder_amount("0");
			}
			Adp.commit();
			
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}
		return orderBean.getOrder_id();
	}

	
	

	/*
	 * если возврацается значение -1 то не
	 * совпадение валют
	 */

	final public String addGroupPosition(final String strPositions_id ,  final int quantity , final OrderBean orderBean) throws Exception {
		String[] positionsArray_id = strPositions_id.split("_");
		String rezult = "";
		for (int i = 0; positionsArray_id.length > i; i++) {
			rezult = addPosition(positionsArray_id[i], quantity, orderBean);
			if (rezult.compareTo("-1") == 0) {
				break;
			}
		}

		return rezult;
	}
	

	
	/*
	 * если возврацается значение -1 то не
	 * совпадение валют
	 */

	final public String addPosition(final String strPosition_id , final int quantity , final OrderBean orderBean) throws Exception {
		if (orderBean.getorder_paystatus().compareTo("0") != 0) return strPosition_id;
		if( quantity == 0 ) return strPosition_id; 
		String basket_id = "";
		String query = "";
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		float fltProduct_amount = 0;
		float fltOrder_amount = 0;
		float fltEnd_order_amount = 0;
		String strProduct_currency = "0";
		String strOrder_currency = "0";
		try {
			query = "select cost ,  currency from soft  where soft_id = " + strPosition_id;
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) {
				fltProduct_amount = (new Float((String) Adp.getValueAt(0, 0))).floatValue(); // + " " + (String)Adp.getValueAt(0,1) ;
				strProduct_currency = (String) Adp.getValueAt(0, 1); // + " "
																		// +
																		// (String)Adp.getValueAt(0,1)
																		// ;
			}
			query = "select currency_id  from orders   where order_id = "+ orderBean.getOrder_id();
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) 
			{
				strOrder_currency = (String) Adp.getValueAt(0, 0);
			}
			if (strProduct_currency.compareTo(strOrder_currency) != 0)	return "-1";
			query = "select amount , end_amount , currency_id   from orders  where order_id = "	+ orderBean.getOrder_id();
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) 
			{
				fltOrder_amount = (new Float((String) Adp.getValueAt(0, 0))).floatValue()  ; // + " " + (String)Adp.getValueAt(0,1) ;
				fltEnd_order_amount = (new Float((String) Adp.getValueAt(0, 1))).floatValue()  ; // + " " + (String)Adp.getValueAt(0,1) ;
			}

			query = "update orders  set end_amount =  ?, amount = ? where order_id = " + orderBean.getOrder_id();
			///Adp.executeUpdate(query);
			Map args = Adp.getArgs();
			args.put("end_amount" , (double)(fltEnd_order_amount + (fltProduct_amount *  quantity ) )  );
			args.put("amount" , (double)(fltOrder_amount + (fltProduct_amount * quantity) )  );
			Adp.executeUpdateWithArgs(query, args);
			

			
			//query = "SELECT NEXT VALUE FOR basket_basket_id_seq  AS ID  FROM ONE_SEQUENCES";
			query = sequences_rs.getString("basket");
			Adp.executeQuery(query);

			basket_id = Adp.getValueAt(0, 0);

//			query = "insert into basket ( basket_id ,  product_id , order_id ) VALUES "
//					+ "( " + basket_id + ", " + strPosition_id + ", "
//					+ orderBean.getOrder_id() + " " + ")";
//			Adp.executeUpdate(query);
			
			query = "insert into basket ( basket_id ,  product_id , order_id , quantity ) VALUES ( ?, ?, ? , ? )";
			args = Adp.getArgs();
			args.put("basket_id" ,  Long.parseLong(basket_id) );
			args.put("product_id" , Long.parseLong(strPosition_id)  );
			args.put("order_id" , Long.parseLong(orderBean.getOrder_id()));
			args.put("quantity" , quantity);
			Adp.executeInsertWithArgs(query, args);
			
			Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}

		return strPosition_id;
	}






	final public String deletePosition(final String strPosition_id , final OrderBean orderBean) throws Exception {
		if (orderBean.getorder_paystatus().compareTo("0") != 0)
			return strPosition_id;
		if (strPosition_id == null || strPosition_id.length() == 0)
			return strPosition_id;
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		query = "update soft set order_id = null where soft_id = " + strPosition_id;
		try {
			Adp.executeUpdate(query);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}

		return strPosition_id;
	}


	final public int getProductsListSize(final javax.servlet.http.HttpServletRequest request, final OrderBean orderBean)	throws Exception {
		String size = "0";

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		query = "SELECT count( orders.order_id ) as size FROM orders "
		//+ "RIGHT  JOIN basket  ON orders.order_id = basket.order_id "
		+ " JOIN basket  ON orders.order_id = basket.order_id "
		+ "LEFT  JOIN soft  ON soft.soft_id = basket.product_id "
		+ "LEFT  JOIN file  ON soft.file_id = file.file_id  "
		+ "LEFT  JOIN images ON soft.image_id = images.image_id "
		+ "LEFT OUTER   JOIN currency  currency_order  ON orders.currency_id = currency_order.currency_id "
		+ "LEFT OUTER   JOIN currency  currency_product ON soft.currency = currency_product.currency_id "
		+ "LEFT OUTER   JOIN paystatus  ON orders.paystatus_id = paystatus.paystatus_id  "
		+ "WHERE orders.order_id = " + orderBean.getOrder_id();

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0)	size = (String) Adp.getValueAt(0, 0);
			Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}

		return Integer.parseInt(size);
	}

	
	
	final public String getOrderByAccount( final String account_history_id)	throws Exception {
		String order_id = "0";
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		query = "SELECT order_id  FROM account_hist WHERE account_hist.id = "+ account_history_id;

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() > 0) order_id = (String) Adp.getValueAt(0, 0);
			Adp.commit();
		}
		
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}
	
	return order_id;
	}

	final public int stringToInt(final String s) 
	{
		int i;
		try 
		{
			i = Integer.parseInt(s);
		}
		catch (NumberFormatException ex) 
		{
			i = 0;
		}
		return i;
	}

	final public boolean setSelectedDemand() 
	{
		QueryManager Adp = new QueryManager();
		// String query = "update tdemand set selected=true where id=" + demand
		// ;
		// Adp.executeUpdate(query);
		Adp.close();
		return true;
	}

	final public boolean setPassiveDemand() 
	{
		QueryManager Adp = new QueryManager();
		//String query = "update tdemand set active=false where id=" + demand ;
		// Adp.executeUpdate(query);
		Adp.close();
		return true;
	}


	
	
	
	final public int setSave_old(final OrderBean orderBean) throws Exception {
		QueryManager Adp = new QueryManager();
		String query = "";
		try {
			// if(shipment_address == null || shipment_address.length() == 0)
			// return 1 ;
			if (orderBean.getCountry_id() == null || orderBean.getCountry_id().length() == 0)
				return 3;
			if (orderBean.getCity_id() == null || orderBean.getCity_id() .length() == 0)
				return 4;
			if (orderBean.getshipment_phone() == null || orderBean.getshipment_phone().length() == 0)
				return 5;
			if (orderBean.getContact_person() == null || orderBean.getContact_person().length() == 0)
				return 6;
			// if(shipment_email == null || shipment_email.length() == 0) return
			// 7 ;
			// if(shipment_fax == null || shipment_fax.length() == 0) return 8 ;
			// if(shipment_description == null || shipment_description.length()
			// == 0) return 9 ;
			// if(shipment_zip == null || shipment_zip.length() == 0) return 10
			// ;
			
			Adp.BeginTransaction();
			
			query = "update orders  set user_id =  ? , amount = ? , tax = ? , end_amount = ? , " +
					" delivery_amount = ? , paystatus_id = ? , deliverystatus_id = ? , " +
					" delivery_start = ? , currency_id = ? , country_id = ? , city_id = ? , " +
					" address = ? , phone = ? , contact_person = ? , email = ?  , fax = ? , description = ? " +
					" where order_id = " + orderBean.getOrder_id();
			
			//Adp.executeUpdate(query);
			
			Map args = Adp.getArgs();
			args.put("user_id" ,   Long.valueOf(orderBean.getUser_ID()));
			args.put("amount" ,  Double.valueOf(orderBean.getorder_amount()));
			args.put("tax" ,  Long.valueOf(orderBean.getorder_tax()) );
			args.put("end_amount" ,  Double.valueOf(orderBean.getend_amount())  );
			args.put("delivery_amount" ,  Double.valueOf(orderBean.getDelivery_amoun()) );
			args.put("paystatus_id" ,  Long.valueOf(orderBean.getorder_paystatus())  );
			args.put("deliverystatus_id" ,  Long.valueOf(orderBean.getDeliverystatus_id()) );
			args.put("delivery_start" ,  new java.util.Date() );
			args.put("currency_id" ,  Long.valueOf(orderBean.getOrder_currency_id()) );
			args.put("country_id" ,  Long.valueOf(orderBean.getCountry_id()) );
			args.put("city_id" ,  Long.valueOf(orderBean.getCity_id())  );
			args.put("address" ,  orderBean.getshipment_address() );
			args.put("phone" ,  orderBean.getshipment_phone()  );
			args.put("contact_person" ,  orderBean.getContact_person()  );
			args.put("email" ,  orderBean.getshipment_email()   );
			args.put("fax" ,  orderBean.getshipment_fax()  );
			args.put("description" ,  orderBean.getshipment_description()  );
			
			Adp.executeUpdateWithArgs(query, args);
			
			
			if (orderBean.getAccount_history_id().length() == 0) {
				int intUser_id = Integer.parseInt(orderBean.getUser_ID());
				float Balans = getBalans(intUser_id);
				float fltEnd_amount = (new Float(orderBean.getend_amount())).floatValue();
				float fltTotal_amount = (Balans - fltEnd_amount);
				float fltOrder_tax = (new Float(orderBean.getorder_tax())).floatValue();
				float fltWithtaxTotal_amount = fltTotal_amount - fltOrder_tax;
				CurrencyHash currencyHash = CurrencyHash.getInstance();
				Currency curr = currencyHash.getCurrency(orderBean.getOrder_currency_id());
				//query = "SELECT NEXT VALUE FOR account_hist_id_seq  AS ID  FROM ONE_SEQUENCES";
				query = sequences_rs.getString("account_hist");
				Adp.executeQuery(query);
				orderBean.setAccount_history_id( Adp.getValueAt(0, 0));
				String strAccountCurrency_id = "-1";
				query = "SELECT currency_id from account WHERE  user_id = " + orderBean.getUser_ID();

				Adp.executeQuery(query);
				strAccountCurrency_id = Adp.getValueAt(0, 0);
				query = "insert into account_hist " + "(" + " id  , "
						+ " user_id , " + " order_id , " + " add_amount , "
						+ " old_amount  , " + " date_input , " + " date_end , "
						+ " complete   , " + " decsription  , "
						+ " currency_id_add  , " + " currency_id_old  , "
						+ " currency_id_total  , " + " active  , "
						+ " \"SYSDATE\"  , " + " total_amount , " + " tax  , "
						+ " withtax_total_amount ," + " rate " + ")"
						+ " VALUES " + "( " + orderBean.getAccount_history_id() + ", "
						+ orderBean.getUser_ID() + ", " + orderBean.getOrder_id() + ", -" + orderBean.getend_amount() + ", "
						+ Balans + ", " + "now()" + ", " + "now()" + ", "
						+ "true" + ", '" + "Credit operation for order  N " + orderBean.getOrder_id()
						+ "', " + orderBean.getOrder_currency_id() + ", "
						+ strAccountCurrency_id + ", " + strAccountCurrency_id
						+ ", " + "false  , " + "now()" + ", " + fltTotal_amount
						+ ", " + orderBean.getorder_tax() + ", " + fltWithtaxTotal_amount
						+ ", " + curr.getRate() + " " + ")";

				Adp.executeUpdate(query);
				query = "UPDATE account SET amount = "
						+ (Balans - fltEnd_amount) + " WHERE  user_id = "
						+ orderBean.getUser_ID();
				
				Adp.executeUpdate(query);
				
				//Adp.executeUpdate(query);
			}

			Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}
		return 0;
	}

	
	final public int setSave(final OrderBean orderBean) throws Exception {
		QueryManager Adp = new QueryManager();
		String query = "";
		try {
			// if(shipment_address == null || shipment_address.length() == 0)
			// return 1 ;
			if (orderBean.getCountry_id() == null || orderBean.getCountry_id().length() == 0)
				return 3;
			if (orderBean.getCity_id() == null || orderBean.getCity_id() .length() == 0)
				return 4;
			if (orderBean.getshipment_phone() == null || orderBean.getshipment_phone().length() == 0)
				return 5;
			if (orderBean.getContact_person() == null || orderBean.getContact_person().length() == 0)
				return 6;
			// if(shipment_email == null || shipment_email.length() == 0) return
			// 7 ;
			// if(shipment_fax == null || shipment_fax.length() == 0) return 8 ;
			// if(shipment_description == null || shipment_description.length()
			// == 0) return 9 ;
			// if(shipment_zip == null || shipment_zip.length() == 0) return 10
			// ;
			
			Adp.BeginTransaction();
			
			query = "update orders  set user_id =  ? , amount = ? , tax = ? , end_amount = ? , " +
					" delivery_amount = ? , paystatus_id = ? , deliverystatus_id = ? , " +
					" delivery_start = ? , currency_id = ? , country_id = ? , city_id = ? , " +
					" address = ? , phone = ? , contact_person = ? , email = ?  , fax = ? , description = ? " +
					" where order_id = " + orderBean.getOrder_id();
			
			Map args = Adp.getArgs();
			args.put("user_id" ,  Long.valueOf(orderBean.getUser_ID())  );
			args.put("amount" ,  Double.valueOf(orderBean.getorder_amount())  );
			args.put("tax" , Double.valueOf(orderBean.getorder_tax()) );
			args.put("end_amount" ,  Double.valueOf(orderBean.getend_amount())  );
			args.put("delivery_amount" ,  Double.valueOf(orderBean.getDelivery_amoun()) );
			args.put("paystatus_id" ,  Long.valueOf(orderBean.getorder_paystatus() ) );
			args.put("deliverystatus_id" ,  Long.valueOf(orderBean.getDeliverystatus_id()) );
			args.put("delivery_start" ,  new java.util.Date() );
			args.put("currency_id" ,  Long.valueOf(orderBean.getOrder_currency_id()) );
			args.put("country_id" , Long.valueOf( orderBean.getCountry_id()) );
			args.put("city_id" ,  Long.valueOf(orderBean.getCity_id())  );
			args.put("address" ,  orderBean.getshipment_address() );
			args.put("phone" ,  orderBean.getshipment_phone()  );
			args.put("contact_person" ,  orderBean.getContact_person()  );
			args.put("email" ,  orderBean.getshipment_email()   );
			args.put("fax" ,  orderBean.getshipment_fax()  );
			args.put("description" ,  orderBean.getshipment_description()  );
			
			Adp.executeUpdateWithArgs(query, args);
			
			
			if (orderBean.getAccount_history_id().length() == 0) {
				long intUser_id = Long.parseLong(orderBean.getUser_ID());
				float Balans = getBalans(intUser_id);
				float fltEnd_amount = (new Float(orderBean.getend_amount())).floatValue();
				float fltTotal_amount = (Balans - fltEnd_amount);
				float fltOrder_tax = (new Float(orderBean.getorder_tax())).floatValue();
				float fltWithtaxTotal_amount = fltTotal_amount - fltOrder_tax;
				CurrencyHash currencyHash = CurrencyHash.getInstance();
				Currency curr = currencyHash.getCurrency(orderBean.getOrder_currency_id());
				//query = "SELECT NEXT VALUE FOR account_hist_id_seq  AS ID  FROM ONE_SEQUENCES";
				query = sequences_rs.getString("account_hist");
				Adp.executeQuery(query);
				orderBean.setAccount_history_id( Adp.getValueAt(0, 0));
				String strAccountCurrency_id = "-1";
				query = "SELECT currency_id from account WHERE  user_id = " + orderBean.getUser_ID();

				Adp.executeQuery(query);
				strAccountCurrency_id = Adp.getValueAt(0, 0);
				query = "insert into account_hist (id  ,  user_id ,  order_id ,  add_amount , "
						+ " old_amount  ,  date_input ,  date_end , complete   ,  decsription  ,  currency_id_add  , " + " currency_id_old  , "
						+ " currency_id_total  ,  active  ,  account_hist.sysdate  ,  total_amount ,  tax  , " +
						" withtax_total_amount , rate ) "
						+ " VALUES ( ?  ,  ? ,  ? ,  ? , "
						+ " ?  ,  ? ,  ? , ?   ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ? ,  ?  ,  ? , ? )" ;
				
				
				args = Adp.getArgs();
				args.put("id" ,  Long.parseLong(orderBean.getAccount_history_id()) );
				args.put("user_id" ,  Long.parseLong(orderBean.getUser_ID())  );
				args.put("order_id" ,  Long.parseLong(orderBean.getOrder_id()) );
				args.put("add_amount" , Float.parseFloat(orderBean.getend_amount())  * -1 );
				args.put("old_amount" ,  Balans);
				args.put("date_input" ,   new java.util.Date()  );
				args.put("date_end" ,   new java.util.Date()  );
				args.put("complete" , true );
				args.put("decsription" ,  "Credit operation for order  N " + orderBean.getOrder_id() );
				args.put("currency_id_add" ,  Long.parseLong(orderBean.getOrder_currency_id()));
				args.put("currency_id_old" ,  Long.parseLong(strAccountCurrency_id ));
				args.put("currency_id_total" , Long.parseLong( strAccountCurrency_id ));
				args.put("active" ,  false );
				args.put("account_hist.sysdate" ,  new java.util.Date()  );
				args.put("total_amount" ,  fltTotal_amount );
				args.put("tax" ,  Double.parseDouble(orderBean.getorder_tax()) );
				args.put("withtax_total_amount" ,  fltWithtaxTotal_amount );
				args.put("rate" ,  curr.getRate()  );
				Adp.executeInsertWithArgs(query, args);
				
				query = "update account set amount = ? where  user_id = " + orderBean.getUser_ID();
				args = Adp.getArgs();
				args.put("amount" , (double)(Balans - fltEnd_amount)  );
				Adp.executeUpdateWithArgs(query, args);
				
			}

			Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}
		return 0;
	}
	
	final public int setSaveWithOutDeductMoney(final OrderBean orderBean) throws Exception {
		QueryManager Adp = new QueryManager();
		String query = "";
		try {
			// if(shipment_address == null || shipment_address.length() == 0)
			// return 1 ;
			if (orderBean.getCountry_id() == null || orderBean.getCountry_id().length() == 0)
				return 3;
			if (orderBean.getCity_id() == null || orderBean.getCity_id() .length() == 0)
				return 4;
			if (orderBean.getshipment_phone() == null || orderBean.getshipment_phone().length() == 0)
				return 5;
			if (orderBean.getContact_person() == null || orderBean.getContact_person().length() == 0)
				return 6;
			// if(shipment_email == null || shipment_email.length() == 0) return
			// 7 ;
			// if(shipment_fax == null || shipment_fax.length() == 0) return 8 ;
			// if(shipment_description == null || shipment_description.length()
			// == 0) return 9 ;
			// if(shipment_zip == null || shipment_zip.length() == 0) return 10
			// ;
			
			Adp.BeginTransaction();
			
			query = "update orders  set user_id =  ? , amount = ? , tax = ? , end_amount = ? , " +
					" delivery_amount = ? , paystatus_id = ? , deliverystatus_id = ? , " +
					" delivery_start = ? , currency_id = ? , country_id = ? , city_id = ? , " +
					" address = ? , phone = ? , contact_person = ? , email = ?  , fax = ? , description = ? " +
					" where order_id = " + orderBean.getOrder_id();
			
			Map args = Adp.getArgs();
			args.put("user_id" ,  Long.valueOf(orderBean.getUser_ID())  );
			args.put("amount" ,  Double.valueOf(orderBean.getorder_amount())  );
			args.put("tax" , Double.valueOf(orderBean.getorder_tax()) );
			args.put("end_amount" ,  Double.valueOf(orderBean.getend_amount())  );
			args.put("delivery_amount" ,  Double.valueOf(orderBean.getDelivery_amoun()) );
			args.put("paystatus_id" ,  Long.valueOf(orderBean.getorder_paystatus() ) );
			args.put("deliverystatus_id" ,  Long.valueOf(orderBean.getDeliverystatus_id()) );
			args.put("delivery_start" ,  new java.util.Date() );
			args.put("currency_id" ,  Long.valueOf(orderBean.getOrder_currency_id()) );
			args.put("country_id" , Long.valueOf( orderBean.getCountry_id()) );
			args.put("city_id" ,  Long.valueOf(orderBean.getCity_id())  );
			args.put("address" ,  orderBean.getshipment_address() );
			args.put("phone" ,  orderBean.getshipment_phone()  );
			args.put("contact_person" ,  orderBean.getContact_person()  );
			args.put("email" ,  orderBean.getshipment_email()   );
			args.put("fax" ,  orderBean.getshipment_fax()  );
			args.put("description" ,  orderBean.getshipment_description()  );
			
			Adp.executeUpdateWithArgs(query, args);

			Adp.commit();
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
			throw ex;
		} 
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
			throw ex;
		} 
		finally 
		{
			Adp.close();
		}
		return 0;
	}
//	public List getProducts1(javax.servlet.http.HttpServletRequest request, OrderBean orderBean )
//	throws Exception {
//
//		
//		List list = new  LinkedList();
//		StringBuffer table = new StringBuffer();
//		QueryManager Adp = new QueryManager();
//		Adp.BeginTransaction();
//		String query = "";
//		query = "SELECT "
//		+ "orders.order_id,"
//		+ "orders.delivery_timeend,"
//		+ "orders.end_amount, "
//		+ "orders.amount, "
//		+ "orders.tax, "
//		+ "orders.delivery_long ,"
//		+ "orders.delivery_start,"
//		+ "orders.cdate , "
//		+ "soft.name, "
//		+ "soft.description ,"
//		+ "soft.cost, "
//		+ "soft.weight, "
//		+ "soft.count, "
//		+ "images.img_url ,"
//		+ "currency_product.currency_lable,"
//		+ "orders.address,"
//		+ "orders.phone,"
//		+ "orders.contact_person, "
//		+ "orders.email, "
//		+ "orders.fax, "
//		+ "orders.description, "
//		+ "orders.zip, "
//		+ "currency_product.currency_id, "
//		+ "orders.delivery_amount, "
//		+ "orders.country_id, "
//		+ "orders.city_id , "
//		+ "currency_product.currency_lable As curr_lable ,"
//		+ "currency_product.currency_id As curr_cd ,"
//		+ "basket.basket_id ,"
//		+ "paystatus.lable ,"
//		+ "paystatus.paystatus_id , "
//		+ "file.file_id  "
//		+ " FROM orders "
//		//+ "RIGHT  JOIN basket  ON orders.order_id = basket.order_id "
//		+ " JOIN basket  ON orders.order_id = basket.order_id "
//		+ "LEFT  JOIN soft  ON soft.soft_id = basket.product_id "
//		+ "LEFT  JOIN file  ON soft.file_id = file.file_id  "
//		+ "LEFT  JOIN images ON soft.image_id = images.image_id "
//		+ "LEFT OUTER   JOIN currency  currency_order  ON orders.currency_id = currency_order.currency_id "
//		+ "LEFT OUTER   JOIN currency  currency_product ON soft.currency = currency_product.currency_id "
//		+ "LEFT OUTER   JOIN paystatus  ON orders.paystatus_id = paystatus.paystatus_id  "
//		+ "WHERE orders.order_id = " + orderBean.getOrder_id() ; 
//				// LIMIT 10  OFFSET "+ orderBean.getOffset();
//
//		try {
//			list = Adp.executeQueryList(query,10,orderBean.getOffset());
//			orderBean.setPagecount(Adp.rows().size());
//	//pagecount = Adp.rows().size();
//			Adp.commit();
//		}
//		catch (SQLException ex) 
//		{
//			log.error(query,ex);
//			Adp.rollback();
//			throw ex;
//		} 
//		catch (Exception ex) 
//		{
//			log.error(ex);
//			Adp.rollback();
//			throw ex;
//		} 
//		finally 
//		{
//			Adp.close();
//		}
//	 return list;
//	}
//	
	

	final public String getProducts(final javax.servlet.http.HttpServletRequest request , final OrderBean orderBean )
	throws Exception {
		orderBean.setListup("Order.jsp?offset=" + (orderBean.getOffset() + 10));
		if (orderBean.getOffset() - 10 < 0)	orderBean.setListdown("Order.jsp?offset=0");
		else orderBean.setListdown("Order.jsp?offset=" + (orderBean.getOffset()  - 10));
		boolean file_exist = false ;
		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		query = "SELECT "
		+ "orders.order_id,"
		+ "orders.delivery_timeend,"
		+ "orders.end_amount, "
		+ "orders.amount, "
		+ "orders.tax, "
		+ "orders.delivery_long ,"
		+ "orders.delivery_start,"
		+ "orders.cdate , "
		+ "soft.name, "
		+ "soft.description ,"
		+ "soft.cost, "
		+ "soft.weight, "
		//+ "soft.\"count\" , "
		//+ "orders.amount, "
		+  "soft.count , "
		+ "images.img_url ,"
		+ "currency_product.currency_lable,"
		+ "orders.address,"
		+ "orders.phone,"
		+ "orders.contact_person, "
		+ "orders.email, "
		+ "orders.fax, "
		+ "orders.description, "
		+ "orders.zip, "
		+ "currency_product.currency_id, "
		+ "orders.delivery_amount, "
		+ "orders.country_id, "
		+ "orders.city_id , "
		+ "currency_product.currency_lable As curr_lable ,"
		+ "currency_product.currency_id As curr_cd ,"
		+ "basket.basket_id ,"
		+ "paystatus.lable as order_paystatus ,"
		+ "paystatus.paystatus_id , "
		+ "file.file_id , "
		+ "deliverystatus.deliverystatus_id , "
		+ "deliverystatus.lable as order_status,  "
		+ "basket.quantity "
		+ " FROM orders "
		//+ "RIGHT  JOIN basket  ON orders.order_id = basket.order_id "
		+ " JOIN basket  ON orders.order_id = basket.order_id "
		+ "LEFT  JOIN soft  ON soft.soft_id = basket.product_id "
		+ "LEFT  JOIN file  ON soft.file_id = file.file_id  "
		+ "LEFT  JOIN images ON soft.image_id = images.image_id "
		+ "LEFT OUTER   JOIN currency  currency_order  ON orders.currency_id = currency_order.currency_id "
		+ "LEFT OUTER   JOIN currency  currency_product ON soft.currency = currency_product.currency_id "
		+ "LEFT OUTER   JOIN paystatus  ON orders.paystatus_id = paystatus.paystatus_id  "
		+ "LEFT OUTER   JOIN deliverystatus  ON orders.deliverystatus_id = deliverystatus.deliverystatus_id  "
		+ "WHERE orders.order_id = ? " ;// + orderBean.getOrder_id() + " LIMIT 10  OFFSET "	+ orderBean.getOffset();
// GROUP BY orders.order_id,orders.delivery_timeend,orders.end_amount, orders.amount, orders.tax, orders.delivery_long ,orders.delivery_start,orders.cdate ,soft.name ,soft.description ,soft.cost , soft.weight , images.img_url , currency_product.currency_lable , orders.address , orders.phone , orders.contact_person , orders.email , orders.fax , orders.description , orders.zip , orders.zip , currency_product.currency_id , orders.delivery_amount , orders.country_id , orders.country_id , orders.city_id , basket.basket_id , paystatus.lable , paystatus.paystatus_id , file.file_id , deliverystatus.deliverystatus_id , deliverystatus.lable

		try 
		{
			
			
			Object[] args = new Object[1];
			args[0] =  Long.valueOf(orderBean.getOrder_id()) ;
			//Adp.executeQuery(query);
			Adp.executeQueryWithArgs(query, args, 10, orderBean.getOffset());
			orderBean.setPagecount(Adp.rows().size());
			table.append("<list>\n");
			for (int i = 0; Adp.rows().size() > i; i++) 
			{
		// order_id = (String) Adp.getValueAt(i, 0);
		//String delivery_timeend = (String) Adp.getValueAt(i, 1);
		String end_amount = (String) Adp.getValueAt(i, 2);
		end_amount = "" + Float.parseFloat(end_amount);
		   orderBean.setend_amount(end_amount) ;
		String order_amount = (String) Adp.getValueAt(i, 3);
		order_amount = "" + Float.parseFloat(order_amount);
		   orderBean.setorder_amount(order_amount) ;
		String order_tax = (String) Adp.getValueAt(i, 4);
		order_tax = "" + Float.parseFloat(order_tax);
		   orderBean.setorder_tax(order_tax) ;
		String order_delivery_long = (String) Adp.getValueAt(i, 5);
		   orderBean.setorder_delivery_long(order_delivery_long) ;
		//String delivery_start = (String) Adp.getValueAt(i, 6);
		   //orderBean.setdelivery_start(delivery_start) ;
		String cdate = (String) Adp.getValueAt(i, 7);
		   orderBean.setcdate(cdate) ;
		String product_name = (String) Adp.getValueAt(i, 8);
		   orderBean.setproduct_name(product_name) ;
		String product_description = (String) Adp.getValueAt(i, 9);
		   orderBean.setproduct_description(product_description) ;
		String product_cost = (String) Adp.getValueAt(i, 10);
		product_cost = "" + Float.parseFloat(product_cost);
		   orderBean.setproduct_cost(product_cost) ;
		String product_weight = (String) Adp.getValueAt(i, 11);
		   orderBean.setproduct_weight(product_weight) ;
		String  product_count = (String) Adp.getValueAt(i, 12);
		   orderBean.setproduct_count(product_count) ;
		String img_url = (String) Adp.getValueAt(i, 13);
		if(img_url.equals("")){orderBean.setimg_url("images/logo.gif") ;}
		else orderBean.setimg_url(img_url) ;
		String currency_lable = (String) Adp.getValueAt(i, 14);
		   orderBean.setcurrency_lable(currency_lable) ;
		//
		String shipment_address = (String) Adp.getValueAt(i, 15);
		if (shipment_address.length() > 0)  orderBean.setshipment_address(shipment_address) ;
		String shipment_phone = (String) Adp.getValueAt(i, 16);
		if (shipment_phone.length() > 0)  orderBean.setshipment_phone(shipment_phone) ;
		String contact_person = (String) Adp.getValueAt(i, 17);
		if (contact_person.length() > 0 )  orderBean.setContact_person(contact_person) ;
		String shipment_email = (String) Adp.getValueAt(i, 18);
		if (shipment_email.length() > 0 )   orderBean.setshipment_email(shipment_email) ;
		String shipment_fax = (String) Adp.getValueAt(i, 19);
		if (shipment_fax.length() > 0 )  orderBean.setshipment_fax(shipment_fax) ;
		String shipment_description = (String) Adp.getValueAt(i, 20);
		if (shipment_description.length() > 0 )   orderBean.setshipment_description(shipment_description) ;
		String shipment_zip = (String) Adp.getValueAt(i, 21);
		if (shipment_zip.length() > 0 )  orderBean.setshipment_zip(shipment_zip) ;
		String order_currency_id = (String) Adp.getValueAt(i, 22);
		if (order_currency_id.length() > 0 )    orderBean.setOrder_currency_id(order_currency_id) ;
		
		String country_id = (String) Adp.getValueAt(i, 24);
		if (country_id.length() > 0) orderBean.setCountry_id(country_id);
		
		String city_id = (String) Adp.getValueAt(i, 25);
		if (city_id.length() > 0)orderBean.setCity_id(city_id);  
		
		String product_currency_lable = (String) Adp.getValueAt(i, 26);
		String product_currency_cd = (String) Adp.getValueAt(i, 27);
		
		String basket_id = (String) Adp.getValueAt(i, 28);
			orderBean.setBasket_id(basket_id) ;
		String paystatus_lable = (String) Adp.getValueAt(i, 29);
			orderBean.setPaystatus_lable(paystatus_lable) ;
		String order_paystatus = (String) Adp.getValueAt(i, 30);
			orderBean.setorder_paystatus(order_paystatus) ;
		String file_id = Adp.getValueAt(i, 31) == null ? "" : Adp.getValueAt(i, 31);

		String deliverystatus_id = (String) Adp.getValueAt(i, 32);
		orderBean.setDeliverystatus_id(deliverystatus_id) ;
		
		String order_status = (String) Adp.getValueAt(i, 33);
		orderBean.setOrder_status(order_status) ;
		
		String quantity = (String) Adp.getValueAt(i, 34);

		
		if (file_id.length() > 0) 
		{
			file_exist = true;
		}
		else 
		{
			file_exist = false;
		}

//		String strImgURL;
//		if (img_url != null) strImgURL = img_url;
//		else strImgURL = "images/Folder.jpg";

		table.append("<product>\n");
		table.append("<basket_id>" + basket_id + "</basket_id>\n");
		// <product_url>http://<%= request.getServerName()
		// %>:<%=request.getServerPort()%>/<jsp:getProperty
		// name="policyBeanId" property="productURL" /></product_url>
		table.append("<product_url>" + "http://"
				+ request.getServerName() + ":"
				+ request.getServerPort()
				+ "/downloadservletbyodrder?basket_id=" + basket_id
				+ "</product_url>\n");
		table.append("<file_exist>" + (file_exist ? "true" : "")
				+ "</file_exist>\n");
		table.append("<name>" + product_name + "</name>\n");
		table.append("<icon>" + orderBean.getimg_url() + "</icon>\n");
		table.append("<image></image>\n");
		table.append("<policy_url>" + "" + "</policy_url>\n");
		table.append("<description>" + product_description
				+ "</description>\n");
		table.append("<amount>" + product_cost + "</amount>\n");
		table.append("<quantity>" + quantity + "</quantity>\n");
		table.append("<currency>\n");
		table.append("<code>" + product_currency_cd + "</code>\n");
		table.append("<description>" + product_currency_lable
				+ "</description>\n");
		table.append("</currency>\n");
		table.append("<version>" + "" + "</version>\n");
		table.append("</product>\n");
	}

	table.append("</list>\n");
	Adp.commit();
  } 
	catch (SQLException ex) 
	{
	log.error(query,ex);
	Adp.rollback();
	throw ex;
	} 
	catch (Exception ex) 
	{
	log.error(ex);
	Adp.rollback();
	throw ex;
	} 
	finally 
	{
	Adp.close();
	}
	
	return table.toString();
	}
	
	
	final public String getOrderlist(long user_id , final OrderListBean orderListBean, Locale locale ) {
		String order_id = "";
		String end_amount = "0";
		String paystatus_id = "0";
		String cdate = "";
		String paystatus_lable = "";
		orderListBean.setListup("OrderList.jsp?offset=" + (orderListBean.getOffset() + 10));
		if (orderListBean.getOffset() - 10 < 0) orderListBean.setListdown("OrderList.jsp?offset=0");
		else orderListBean.setListdown("OrderList.jsp?offset=" + (orderListBean.getOffset() - 10));

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "SELECT "
				+ "orders.order_id,"
				+ "orders.end_amount, "
				+ "orders.cdate , "
				+ "paystatus.lable , "
				+ "paystatus.paystatus_id  , "
				+ "currency.currency_lable "
				+ " FROM orders "
				+ " LEFT  JOIN paystatus ON orders.paystatus_id  =  paystatus.paystatus_id "
				+ " LEFT OUTER   JOIN currency ON orders.currency_id = currency.currency_id "
				+ "WHERE orders.user_id = ? ORDER BY orders.order_id DESC  " ; // + user_id ; //+ " LIMIT 10  OFFSET "+ orderListBean.getOffset();

		Object[] args =  new Object[1] ;
		args[0] = Long.valueOf(user_id) ;
		
		try 
		{
			Adp.executeQueryWithArgs(query, args , 10, orderListBean.getOffset()) ;
		

		table.append("<list>\n");
		for (int i = 0; Adp.rows().size() > i; i++) {
			order_id = (String) Adp.getValueAt(i, 0);
			end_amount = (String) Adp.getValueAt(i, 1);
			cdate = (String) Adp.getValueAt(i, 2);
			paystatus_lable = (String) Adp.getValueAt(i, 3);
			paystatus_id = (String) Adp.getValueAt(i, 4);
			orderListBean.setCurrency_lable((String) Adp.getValueAt(i, 5));
			table.append("<order>\n");
			table.append("<order_id>" + order_id + "</order_id>\n");
			table.append("<end_amount>" + getStrFormatNumberFloat(end_amount) + "</end_amount>\n");
			try 
			{
				table.append("<cdate>" + orderListBean.getSimpleDateFormat(locale).format(Adp.getSimpleDateFormat().parse(cdate))  + "</cdate>\n");
			} 
			catch (ParseException ex) 
			{
				log.error(ex) ;
			}
			table.append("<paystatus_id>" + paystatus_id + "</paystatus_id>\n");
			table.append("<paystatus_lable>" + paystatus_lable + "</paystatus_lable>\n");
			table.append("<currency_lable>" + orderListBean.getCurrency_lable()	+ "</currency_lable>\n");
			table.append("</order>\n");
		}

		table.append("</list>\n");
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
		  Adp.close();
		}
		return table.toString();

	}
	
	
	
	final public String getOrderlistByDate(final long user_id , final OrderListBean orderListBean , final Locale locale , long role_id , String site_id )  {
		
		
		String order_id = "";
		String end_amount = "0";
		String paystatus_id = "0";
		String cdate = "";
		String paystatus_lable = "";
		orderListBean.setListup("OrderList.jsp?offset=" + (orderListBean.getOffset() + 10));
		if (orderListBean.getOffset() - 10 < 0) orderListBean.setListdown("OrderList.jsp?offset=0");
		else orderListBean.setListdown("OrderList.jsp?offset=" + (orderListBean.getOffset() - 10));

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		String query = "";
		Object[] args = null ;
		if(role_id == 2)
		{
			query = "SELECT "
					+ "orders.order_id,"
					+ "orders.end_amount, "
					+ "orders.cdate , "
					+ "paystatus.lable , "
					+ "paystatus.paystatus_id  , "
					+ "currency.currency_lable "
					+ " FROM orders "
					+ " LEFT  JOIN paystatus ON orders.paystatus_id  =  paystatus.paystatus_id "
					+ " LEFT OUTER   JOIN currency ON orders.currency_id = currency.currency_id "
					+ " LEFT JOIN tuser ON orders.user_id = tuser.user_id "
					+ "WHERE tuser.site_id  = ? and orders.cdate >= ? and orders.cdate <=  ? " ; //  LIMIT 10  OFFSET ? " ;
		
			args = new Object[3];
			args[0] =  Long.valueOf(site_id) ;
			args[1] =  orderListBean.getSQLDateFrom() ; 
			args[2] =  orderListBean.getSQLDateTo() ; 
		
		}
		else
		{
		query = "SELECT "
				+ "orders.order_id,"
				+ "orders.end_amount, "
				+ "orders.cdate , "
				+ "paystatus.lable , "
				+ "paystatus.paystatus_id  , "
				+ "currency.currency_lable "
				+ " FROM orders "
				+ " LEFT  JOIN paystatus ON orders.paystatus_id  =  paystatus.paystatus_id "
				+ " LEFT OUTER   JOIN currency ON orders.currency_id = currency.currency_id "
				+ "WHERE orders.user_id = ? and orders.cdate >= ? and orders.cdate <=  ? " ; //  LIMIT 10  OFFSET ? " ;
		
		args = new Object[3];
		args[0] =  Long.valueOf(user_id) ;
		args[1] =  orderListBean.getSQLDateFrom() ; 
		args[2] =  orderListBean.getSQLDateTo() ; 
		
		}
		
		//orders.cdate > ? and orders.cdate <  ?

		// else query = "SELECT \"soft\".\"soft_id\",
		// \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\",
		// \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\",
		// \"soft\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" ,
		// \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\" FROM \"soft\"
		// WHERE \"soft\".\"type_id\" = " + type_id + " and
		// \"soft\".\"phonetype_id\" = " + phonetype_id + " and
		// \"soft\".\"progname_id\" = " + progname_id + " and
		// \"soft\".\"soft_id\" = func_soft_position_id(\"soft\".\"type_id\")
		// limit 10 offset " + offset ;
		// query = "SELECT \"soft\".\"soft_id\",
		// \"soft\".\"name\",\"soft\".\"description\", \"soft\".\"version\",
		// \"soft\".\"cost\", \"soft\".\"currency\", \"soft\".\"serial_nubmer\",
		// \"soft\".\"file_id\", \"soft\".\"type_id\", \"soft\".\"active\" ,
		// \"soft\".\"phonetype_id\" , \"soft\".\"progname_id\" FROM \"soft\"
		// WHERE \"soft\".\"type_id\" = " + type_id + " and
		// \"soft\".\"phonetype_id\" = " + phonetype_id + " and
		// \"soft\".\"soft_id\" = func_soft_position_id(\"soft\".\"type_id\")
		// limit 5 offset " + offset ;
		
		
		//args[3] =  orderListBean.getOffset();

		
		try 
		{
			Adp.executeQueryWithArgs(query,args,10,orderListBean.getOffset());
			//Adp.executeQuery(query);
		

		table.append("<list>\n");
		for (int i = 0; Adp.rows().size() > i; i++) {
			order_id = (String) Adp.getValueAt(i, 0);
			end_amount = (String) Adp.getValueAt(i, 1);
			cdate = (String) Adp.getValueAt(i, 2);
			paystatus_lable = (String) Adp.getValueAt(i, 3);
			paystatus_id = (String) Adp.getValueAt(i, 4);
			orderListBean.setCurrency_lable((String) Adp.getValueAt(i, 5));
			table.append("<order>\n");
			table.append("<order_id>" + order_id + "</order_id>\n");
			table.append("<end_amount>" + getStrFormatNumberFloat(end_amount) + "</end_amount>\n");
			try 
			{
				table.append("<cdate>" + orderListBean.getSimpleDateFormat(locale).format(Adp.getSimpleDateFormat().parse(cdate))  + "</cdate>\n");
			} 
			catch (Exception ex) 
			{
				log.error(ex) ;
			}
			table.append("<paystatus_id>" + paystatus_id + "</paystatus_id>\n");
			table.append("<paystatus_lable>" + paystatus_lable + "</paystatus_lable>\n");
			table.append("<currency_lable>" + orderListBean.getCurrency_lable()	+ "</currency_lable>\n");
			table.append("</order>\n");
		}

		table.append("</list>\n");
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
		  Adp.close();
		}
		return table.toString();

	}
	
final public String getOrderlistByStatus(final String site_id , final OrderListBean orderListBean , final Locale locale  )  {
		
		
		String order_id = "";
		String end_amount = "0";
		String paystatus_id = "0";
		String cdate = "";
		String paystatus_lable = "";
		orderListBean.setListup("OrderList.jsp?offset=" + (orderListBean.getOffset() + 10));
		if (orderListBean.getOffset() - 10 < 0) orderListBean.setListdown("OrderList.jsp?offset=0");
		else orderListBean.setListdown("OrderList.jsp?offset=" + (orderListBean.getOffset() - 10));

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "SELECT "
				+ "orders.order_id,"
				+ "orders.end_amount, "
				+ "orders.cdate , "
				+ "paystatus.lable , "
				+ "paystatus.paystatus_id  , "
				+ "currency.currency_lable "
				+ " FROM orders "
				+ " LEFT  JOIN paystatus ON orders.paystatus_id  =  paystatus.paystatus_id "
				+ " LEFT OUTER   JOIN currency ON orders.currency_id = currency.currency_id "
				+ " LEFT JOIN tuser ON orders.user_id = tuser.user_id "
				+ "WHERE tuser.site_id  = ? and ( orders.paystatus_id = ? or orders.deliverystatus_id =  ? )" ; //  LIMIT 10  OFFSET ? " ;
		
	
		
		Object[] args = new Object[3];
		args[0] =  Long.valueOf(site_id) ;
		args[1] =  orderListBean.getOrder_paystatus_id();
		args[2] =  orderListBean.getDeliverystatus_id(); 
		//args[3] =  orderListBean.getOffset();

		
		try 
		{
			Adp.executeQueryWithArgs(query,args,10,orderListBean.getOffset());
			//Adp.executeQuery(query);
		

		table.append("<list>\n");
		for (int i = 0; Adp.rows().size() > i; i++) {
			order_id = (String) Adp.getValueAt(i, 0);
			end_amount = (String) Adp.getValueAt(i, 1);
			cdate = (String) Adp.getValueAt(i, 2);
			paystatus_lable = (String) Adp.getValueAt(i, 3);
			paystatus_id = (String) Adp.getValueAt(i, 4);
			orderListBean.setCurrency_lable((String) Adp.getValueAt(i, 5));
			table.append("<order>\n");
			table.append("<order_id>" + order_id + "</order_id>\n");
			table.append("<end_amount>" + getStrFormatNumberFloat(end_amount) + "</end_amount>\n");
			try 
			{
				table.append("<cdate>" + orderListBean.getSimpleDateFormat(locale).format(Adp.getSimpleDateFormat().parse(cdate))  + "</cdate>\n");
			} 
			catch (Exception ex) 
			{
				log.error(ex) ;
			}
			table.append("<paystatus_id>" + paystatus_id + "</paystatus_id>\n");
			table.append("<paystatus_lable>" + paystatus_lable + "</paystatus_lable>\n");
			table.append("<currency_lable>" + orderListBean.getCurrency_lable()	+ "</currency_lable>\n");
			table.append("</order>\n");
		}

		table.append("</list>\n");
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
		  Adp.close();
		}
		return table.toString();

	}
	
	
	final public String getPaymentlistByDate(final long intUserID, final long role_id , final AccountHistoryBean accountHistoryBean) {

		
		String[] array_amount_id = new String[10];
		String add_amount = "";
		String old_amount = "";
		String date_input = "";
		String date_end = "";
		String sysdate = "";
		String complete = "";
		String decsription = "";
		String active = "";
		String amount_id = "";
		String total_amount = "";
		String currency_add_lable = "";
		String currency_old_lable = "";
		String currency_total_lable = "";
		String rezult_cd = "";
		
		accountHistoryBean.setCururl("AccountHistory.jsp?offset=" + accountHistoryBean.getOffset());
		accountHistoryBean.setListup("AccountHistory.jsp?offset=" + (accountHistoryBean.getOffset() + 10));
		
		if (accountHistoryBean.getOffset() - 10 < 0) accountHistoryBean.setListdown ("AccountHistory.jsp?offset=0");
		else accountHistoryBean.setListdown ("AccountHistory.jsp?offset=" + (accountHistoryBean.getOffset() - 10));

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();

		String query = "SELECT  account_hist.add_amount, account_hist.old_amount, account_hist.date_input, account_hist.date_end, account_hist.sysdate, account_hist.complete, account_hist.decsription, account_hist.active , account_hist.id  , account_hist.total_amount , "
				+ " currency_add.currency_lable , currency_old.currency_lable ,currency_total.currency_lable , account_hist.rezult_cd "
				+ " FROM account_hist  "
				+ " LEFT OUTER   JOIN currency  currency_add ON account_hist.currency_id_add = currency_add.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_old ON account_hist.currency_id_old = currency_old.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_total ON account_hist.currency_id_total = currency_total.currency_id "
				+ " WHERE account_hist.user_id = ? and account_hist.sysdate >= ? and account_hist.sysdate <=  ? "
				+ " ORDER BY account_hist.id DESC " ; //  limit 10 offset " + accountHistoryBean.getOffset();

		Object[] args = new Object[3];
		args[0] =  intUserID ;
		args[1] =  accountHistoryBean.getSQLDateFrom() ; 
		args[2] =  accountHistoryBean.getSQLDateTo() ; 

		try 
		{
			Adp.executeQueryWithArgs(query,args,10,accountHistoryBean.getOffset());
			
			table.append("<list>\n");
			for (int i = 0; Adp.rows().size() > i; i++) {
				
				
				add_amount = (String) Adp.getValueAt(i, 0);
				old_amount = (String) Adp.getValueAt(i, 1);
				date_input = (String) Adp.getValueAt(i, 2);
				date_end = (String) Adp.getValueAt(i, 3);
				sysdate = (String) Adp.getValueAt(i, 4);
				complete = (String) Adp.getValueAt(i, 5);
				decsription = (String) Adp.getValueAt(i, 6);
				active = (String) Adp.getValueAt(i, 7);
				amount_id = (String) Adp.getValueAt(i, 8);
				total_amount = (String) Adp.getValueAt(i, 9);
				currency_add_lable = (String) Adp.getValueAt(i, 10);
				currency_old_lable = (String) Adp.getValueAt(i, 11);
				currency_total_lable = (String) Adp.getValueAt(i, 12);
				rezult_cd = (String) Adp.getValueAt(i, 13);
				array_amount_id[i] = amount_id;

				table.append("<payment>\n");
				table.append("<amount_id>" + amount_id + "</amount_id>\n");
				table.append("<currency_add_lable>" + currency_add_lable
						+ "</currency_add_lable>\n");
				table.append("<add_amount>" + add_amount + "</add_amount>\n");
				table.append("<sysdate>" + sysdate + "</sysdate>\n");
				table.append("<complete>" + complete + "</complete>\n");
				table.append("<rezult_cd>" + rezult_cd + "</rezult_cd>\n");
				table.append("</payment>\n");
			}

			table.append("</list>\n");

			
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
		  Adp.close();
		}

		
		return table.toString();

	}

	
final public String getPaymentlist(final long intUserID, final  long role_id , final AccountHistoryBean accountHistoryBean) {

		
		String[] array_amount_id = new String[10];
		String add_amount = "";
		String old_amount = "";
		String date_input = "";
		String date_end = "";
		String sysdate = "";
		String complete = "";
		String decsription = "";
		String active = "";
		String amount_id = "";
		String total_amount = "";
		String currency_add_lable = "";
		String currency_old_lable = "";
		String currency_total_lable = "";
		String rezult_cd = "";
		
		accountHistoryBean.setCururl("AccountHistory.jsp?offset=" + accountHistoryBean.getOffset());
		accountHistoryBean.setListup("AccountHistory.jsp?offset=" + (accountHistoryBean.getOffset() + 10));
		
		if (accountHistoryBean.getOffset() - 10 < 0) accountHistoryBean.setListdown ("AccountHistory.jsp?offset=0");
		else accountHistoryBean.setListdown ("AccountHistory.jsp?offset=" + (accountHistoryBean.getOffset() - 10));

		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();

		String query = "SELECT  account_hist.add_amount, account_hist.old_amount, account_hist.date_input, account_hist.date_end, account_hist.sysdate, account_hist.complete, account_hist.decsription, account_hist.active , account_hist.id  , account_hist.total_amount , "
				+ " currency_add.currency_lable , currency_old.currency_lable ,currency_total.currency_lable , account_hist.rezult_cd "
				+ " FROM account_hist  "
				+ " LEFT OUTER   JOIN currency  currency_add ON account_hist.currency_id_add = currency_add.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_old ON account_hist.currency_id_old = currency_old.currency_id "
				+ " LEFT OUTER   JOIN currency  currency_total ON account_hist.currency_id_total = currency_total.currency_id "
				+ " WHERE account_hist.user_id = ?  and account_hist.complete = true  ORDER BY account_hist.id DESC " ; //  limit 10 offset " + accountHistoryBean.getOffset();

		
		
		try 
		{

			Object[] args = new Object[1];
			args[0] =  intUserID ;
			Adp.executeQueryWithArgs(query, args, 10, accountHistoryBean.getOffset());			
			table.append("<list>\n");
			for (int i = 0; Adp.rows().size() > i; i++) {
				
				
				add_amount = (String) Adp.getValueAt(i, 0);
				old_amount = (String) Adp.getValueAt(i, 1);
				date_input = (String) Adp.getValueAt(i, 2);
				date_end = (String) Adp.getValueAt(i, 3);
				sysdate = (String) Adp.getValueAt(i, 4);
				complete = (String) Adp.getValueAt(i, 5);
				decsription = (String) Adp.getValueAt(i, 6);
				active = (String) Adp.getValueAt(i, 7);
				amount_id = (String) Adp.getValueAt(i, 8);
				total_amount = (String) Adp.getValueAt(i, 9);
				currency_add_lable = (String) Adp.getValueAt(i, 10);
				currency_old_lable = (String) Adp.getValueAt(i, 11);
				currency_total_lable = (String) Adp.getValueAt(i, 12);
				rezult_cd = (String) Adp.getValueAt(i, 13);
				array_amount_id[i] = amount_id;

				table.append("<payment>\n");
				table.append("<amount_id>" + amount_id + "</amount_id>\n");
				table.append("<currency_add_lable>" + currency_add_lable
						+ "</currency_add_lable>\n");
				table.append("<add_amount>" + add_amount + "</add_amount>\n");
				table.append("<sysdate>" + sysdate + "</sysdate>\n");
				table.append("<complete>" + complete + "</complete>\n");
				table.append("<rezult_cd>" + rezult_cd + "</rezult_cd>\n");
				table.append("</payment>\n");
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
			table.append("</list>\n");
			Adp.close();
		}

		
		return table.toString();

	}
	
public void setStatusInrocess( String order_id	) throws Exception {

	   String query = "" ; 
	   QueryManager Adp = new QueryManager();
	try {
		Adp.BeginTransaction();

		query = "update orders  set paystatus_id = 2 where order_id = " 	+ order_id;
		Adp.executeUpdate(query);
		Adp.commit();
	} 
	catch (SQLException ex) 
	{
		log.error(query,ex) ;
		Adp.rollback();
	}
	catch (Exception ex) {
		log.error(ex);
		Adp.rollback();
	} 
	finally 
	{
		Adp.close();
	}
}


final public String getPayment(final long intUserID, final long role_id , final AccountHistoryDetalBean accountHistoryDetalBean ) {

	StringBuffer table = new StringBuffer();
	QueryManager Adp = new QueryManager();
	
	String query = "SELECT  account_hist.add_amount, account_hist.old_amount, account_hist.date_input, account_hist.date_end, account_hist.sysdate, account_hist.complete, account_hist.decsription, account_hist.active , account_hist.id  , account_hist.total_amount , "
			+ " currency_add.currency_lable , currency_old.currency_lable ,currency_total.currency_lable , account_hist.user_ip , account_hist.user_header , account_hist.rezult_cd "
			+ " FROM account_hist  "
			+ " LEFT OUTER   JOIN currency  currency_add ON account_hist.currency_id_add = currency_add.currency_id "
			+ " LEFT OUTER   JOIN currency  currency_old ON account_hist.currency_id_old = currency_old.currency_id "
			+ " LEFT OUTER   JOIN currency  currency_total ON account_hist.currency_id_total = currency_total.currency_id "
			+ " WHERE account_hist.id = ? " ;

	try {
		Object[] args = new Object[1];
		args[0] =  Long.valueOf(accountHistoryDetalBean.getAmount_id()) ;
		Adp.executeQueryWithArgs(query, args);			
		
	

	accountHistoryDetalBean.setAdd_amount((String) Adp.getValueAt(0, 0));
	
	//add_amount = (String) Adp.getValueAt(0, 0);
	accountHistoryDetalBean.setOld_amount((String) Adp.getValueAt(0, 1));
	//old_amount = (String) Adp.getValueAt(0, 1);
	
	//date_input = (String) Adp.getValueAt(0, 2);
	
	accountHistoryDetalBean.setDate_input((String) Adp.getValueAt(0, 2));		
	
	//date_end = (String) Adp.getValueAt(0, 3);

	accountHistoryDetalBean.setDate_end((String) Adp.getValueAt(0, 3));
	
	//sysdate = (String) Adp.getValueAt(0, 4);
	accountHistoryDetalBean.setSysdate((String) Adp.getValueAt(0, 4));		
	
	//complete = (String) Adp.getValueAt(0, 5);
	
	accountHistoryDetalBean.setComplete((String) Adp.getValueAt(0, 5));
	
	//decsription = (String) Adp.getValueAt(0, 6);
	
	accountHistoryDetalBean.setDecsription((String) Adp.getValueAt(0, 6));
	
	//active = (String) Adp.getValueAt(0, 7);
	
	accountHistoryDetalBean.setActive((String) Adp.getValueAt(0, 7));
	
	//amount_id = (String) Adp.getValueAt(0, 8);
	
	accountHistoryDetalBean.setAmount_id((String) Adp.getValueAt(0, 8));
	
	//total_amount = (String) Adp.getValueAt(0, 9);
	
	accountHistoryDetalBean.setTotal_amount((String) Adp.getValueAt(0, 9));
	
	//currency_add_lable = (String) Adp.getValueAt(0, 10);
	
	accountHistoryDetalBean.setCurrency_add_lable((String) Adp.getValueAt(0, 10));
	
	//currency_old_lable = (String) Adp.getValueAt(0, 11);
	
	accountHistoryDetalBean.setCurrency_old_lable((String) Adp.getValueAt(0, 11));
	
	//currency_total_lable = (String) Adp.getValueAt(0, 12);

	accountHistoryDetalBean.setCurrency_total_lable((String) Adp.getValueAt(0, 12));
	
	//user_ip = (String) Adp.getValueAt(0, 13);
	
	accountHistoryDetalBean.setUser_ip((String) Adp.getValueAt(0, 13));
	
	//user_header = (String) Adp.getValueAt(0, 14);
	
	accountHistoryDetalBean.setUser_header((String) Adp.getValueAt(0, 14));
	
	//rezult_cd = (String) Adp.getValueAt(0, 15);

	accountHistoryDetalBean.setRezult_cd((String) Adp.getValueAt(0, 15));
	
	table.append("<payment>\n");

	table.append("<add_amount>" + getStrFormatNumberFloat(accountHistoryDetalBean.getAdd_amount()) + "</add_amount>\n");
	table.append("<old_amount>" + getStrFormatNumberFloat(accountHistoryDetalBean.getOld_amount()) + "</old_amount>\n");
	table.append("<date_input>" + accountHistoryDetalBean.getDate_input() + "</date_input>\n");
	table.append("<date_end>" + accountHistoryDetalBean.getDate_end() + "</date_end>\n");
	table.append("<sysdate>" + accountHistoryDetalBean.getSysdate() + "</sysdate>\n");
	table.append("<complete>" + accountHistoryDetalBean.getComplete() + "</complete>\n");
	table.append("<decsription>" + accountHistoryDetalBean.getDecsription() + "</decsription>\n");
	table.append("<active>" + accountHistoryDetalBean.getActive() + "</active>\n");
	table.append("<amount_id>" + accountHistoryDetalBean.getAmount_id() + "</amount_id>\n");
	table.append("<total_amount>" + getStrFormatNumberFloat(accountHistoryDetalBean.getTotal_amount()) + "</total_amount>\n");
	table.append("<currency_add_lable>" + accountHistoryDetalBean.getCurrency_add_lable()
			+ "</currency_add_lable>\n");
	table.append("<currency_old_lable>" + accountHistoryDetalBean.getCurrency_old_lable()
			+ "</currency_old_lable>\n");
	table.append("<currency_total_lable>" + accountHistoryDetalBean.getCurrency_total_lable()
			+ "</currency_total_lable>\n");
	table.append("<user_ip>" + accountHistoryDetalBean.getUser_ip() + "</user_ip>\n");
	table.append("<user_header>" + accountHistoryDetalBean.getUser_header() + "</user_header>\n");
	table.append("<rezult_cd>" + accountHistoryDetalBean.getRezult_cd() + "</rezult_cd>\n");
	table.append("</payment>\n");

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
  Adp.close();
}
	return table.toString();

}



}
