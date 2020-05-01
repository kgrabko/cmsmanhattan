package com.cbsinc.cms.controllers;

import java.io.IOException;

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
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.CheckPaymentResult;
import com.cbsinc.cms.PayStatus;
import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

public class FDResponceAction extends TemplateAction {

    static private Logger log = Logger.getLogger(CheckPaymentResult.class);
	

	public FDResponceAction() {

	}


	public void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts, Optional<ServletContext> servletContextOpts) throws Exception {


		HttpServletResponse response = responseOpts.get() ;
		HttpServletRequest request  = requestOpts.get() ;
		AuthorizationPageBean authorizationPageBean = getAuthorizationPageBean().get() ;
		AuthorizationPageFaced authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get() ;
		String x_invoice_num = request.getParameter("x_invoice_num");
		String x_response_code = request.getParameter("x_response_code");
		String x_po_num = request.getParameter("x_po_num");
		String site_id = x_po_num.split("_")[0];
		String order_id = x_po_num.split("_")[1];
		String x_response_reason_text = request.getParameter("x_response_reason_text");
		parserRequest(x_invoice_num, x_response_code, x_response_reason_text);
		System.out.println("pay respone: " + request.getRequestURI());
		authorizationPageBean.setSite_id(site_id, authorizationPageFaced);

		String url;
		if ((url = getCookiesValue(request, "payment_page")) != null) {
			response.sendRedirect(url);
			return;
		}

	}

	private String getCookiesValue(HttpServletRequest request, String name) {

		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			String _name = c.getName();
			if (name.equals(_name))
				return c.getValue();
		}

		return null;
	}

	public void parserRequest(String i_strNumerOrder, String i_strRezult, String i_strDecsription) {
		// final static int INPROCESS = 1 ;
		// final static int SUCCESS = 2 ;
		// final static int UNSUCCESS = 3 ;

		// dfis codes is 1 for “Approved”, 2 for “Declined”, 3 for “Error”
		int approved = 1;
		int declined = 2;
		int error = 3;

		System.out.println("invoice Id: " + i_strNumerOrder + " Rezalt: " + i_strRezult + " " + i_strDecsription);
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		i_strRezult = i_strRezult.trim();
		try {

			if (Long.parseLong(i_strRezult) == approved) {
				end_addmoney(Adp, i_strNumerOrder, i_strRezult, i_strRezult);
				getAuthorizationPageBean().ifPresent(action -> action.setStrMessage("Thank you, your order has been placed"));
			} else if (Long.parseLong(i_strRezult) == declined) {
				query = "UPDATE account_hist SET complete = ? , active = ? , rezult_cd = ? , decsription =  ?  WHERE  id =  "
						+ i_strNumerOrder + "";
				HashMap args = new HashMap();
				args.put("complete", false);
				args.put("active", false);
				args.put("rezult_cd", i_strRezult);
				args.put("decsription", i_strDecsription);
				Adp.executeUpdateWithArgs(query, args);
			} else if (Long.parseLong(i_strRezult) == error) {
				query = "UPDATE account_hist SET complete = ? , active = ? , rezult_cd = ? , decsription =  ?  WHERE  id =  "
						+ i_strNumerOrder + "";
				HashMap args = new HashMap();
				args.put("complete", false);
				args.put("active", false);
				args.put("rezult_cd", i_strRezult);
				args.put("decsription", i_strDecsription);
				Adp.executeUpdateWithArgs(query, args);
			}

			Adp.commit();
		} catch (SQLException ex) {
			log.error(query, ex);
			Adp.rollback();
		} catch (Exception ex) {
			log.error(ex);
			Adp.rollback();
			return;
		} finally {
			Adp.close();
		}

	}


	private void end_addmoney(QueryManager Adp, String i_strAccountHistory_id, String i_strRezult,
			String i_strDecsription) throws Exception {

		getAuthorizationPageBean().ifPresent(action -> action.setStrEMail("Your order was processed successfully. Here is your receipt."));

		double add_amount = 0;
		double old_amount = 0;
		double total_amount = 0;
		// String date_input = "" ;
		double amount = 0;
		double rate = 0;
		String date_input = null;
		String user_id = "";
		String query = "";
		String order_id = "";

		query = "select add_amount , old_amount ,  date_input , rate , date_end , user_id , order_id from  account_hist where  id =  "
				+ i_strAccountHistory_id;
		Adp.executeQuery(query);
		if (Adp.rows().size() > 0) {
			add_amount =  Double.parseDouble(((String) Adp.getValueAt(0, 0)));
			old_amount = Double.parseDouble(((String) Adp.getValueAt(0, 1)));
			date_input = (String) Adp.getValueAt(0, 2);
			// rate = new Double((String) Adp.getValueAt(0, 3)).doubleValue();
			user_id = (String) Adp.getValueAt(0, 5);
			order_id = (String) Adp.getValueAt(0, 6);
			total_amount = old_amount + add_amount;
		}

		query = "UPDATE account_hist SET complete = ? , active = ? , rezult_cd = ?  WHERE  id =  "
				+ i_strAccountHistory_id + "";
		HashMap args = new HashMap();
		args.put("complete", true);
		args.put("active", false);
		args.put("rezult_cd", i_strRezult);
		// args.put("decsription" , "the payment was processed successfully" );

		Adp.executeUpdateWithArgs(query, args);

		// total_amount = amount + (add_amount * rate);
		query = "UPDATE account SET amount = ? , curr = ? , date_input = ? WHERE  user_id = " + user_id;
		args = new HashMap();
		args.put("amount", Double.valueOf(total_amount));
		args.put("curr", Integer.valueOf(getAuthorizationPageBean().get().getCurrency_id()));
		args.put("date_input", new java.util.Date());
		Adp.executeUpdateWithArgs(query, args);

		// if (add_amount >= 0) { //? amount should be the same
		query = "update orders  set paystatus_id = " + PayStatus.SUCCESS + " where order_id = " + order_id;
		Adp.executeUpdate(query);
		// }

		// finally no use it here .

	}

}
