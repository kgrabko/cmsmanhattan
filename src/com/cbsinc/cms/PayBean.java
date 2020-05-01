package com.cbsinc.cms;

import java.sql.*;

import org.apache.log4j.Logger;

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

public class PayBean extends com.cbsinc.cms.WebControls implements java.io.Serializable {

	private static final long serialVersionUID = -5025445874531411579L;

	static private Logger log = Logger.getLogger(PayBean.class);

	public String getCurrency_code(String currency_id) {
		String currency_cd = "";
		// String query = "SELECT account.amount, currency.currency_lable,
		// currency.currency_desc FROM account LEFT OUTER JOIN public.currency
		// ON account.currency_id = currency.currency_id WHERE account.user_id =
		// " + currency_id ;
		String query = "SELECT  currency.currency_cd FROM currency WHERE currency.currency_id = " + currency_id;
		QueryManager Adp = new QueryManager();
		try {
			Adp.executeQuery(query);
			currency_cd = (String) Adp.getValueAt(0, 0);
		} catch (SQLException ex) {

			log.error(query, ex);
		} catch (Exception ex) {

			log.error(ex);
		} finally {
			Adp.close();
		}

		return currency_cd;
	}

	public String getCurrency_lable(String currency_id) {
		String currency_cd = "";
		// String query = "SELECT account.amount, currency.currency_lable,
		// currency.currency_desc FROM account LEFT OUTER JOIN public.currency
		// ON account.currency_id = currency.currency_id WHERE account.user_id =
		// " + currency_id ;
		String query = "SELECT  currency.currency_lable FROM currency WHERE currency.currency_id = " + currency_id;
		QueryManager Adp = new QueryManager();
		try {
			Adp.executeQuery(query);
			currency_cd = (String) Adp.getValueAt(0, 0);
		} catch (SQLException ex) {

			log.error(query, ex);
		} catch (Exception ex) {

			log.error(ex);
		} finally {
			Adp.close();
		}

		return currency_cd;
	}

	public String getPaySystem_code(String paysystem_id) {
		String paysystem_cd = "";
		String query = "SELECT  paysystem.paysystem_cd FROM paysystem WHERE paysystem.paysystem_id = " + paysystem_id;
		QueryManager Adp = new QueryManager();
		try {
			Adp.executeQuery(query);
			paysystem_cd = (String) Adp.getValueAt(0, 0);
		} catch (SQLException ex) {

			log.error(query, ex);
		} catch (Exception ex) {

			log.error(ex);
		} finally {
			Adp.close();
		}

		setPaySystem(paysystem_cd);
		return paysystem_cd;
	}

	public void setPaySystem(String paysystem_cd) {
		paysystem_cd = paysystem_cd.trim();
		choosenTypeCreditCard = "0";
		cardPayment = "0";
		walletPayment = "0";
		webMoneyPayment = "0";
		rapidaPayment = "0";
		payCashPayment = "0";
		EPortPayment = "0";
		kreditPilotPayment = "0";
		if ("CardPaymentVisa".compareTo(paysystem_cd) == 0) {
			cardPayment = "1";
			choosenTypeCreditCard = "1";
		} else if ("CardPaymentMaster".compareTo(paysystem_cd) == 0) {
			cardPayment = "1";
			choosenTypeCreditCard = "2";
		} else if ("WebMoneyPayment".compareTo(paysystem_cd) == 0) {
			webMoneyPayment = "1";
			walletPayment = "1";
		} else if ("RapidaPayment".compareTo(paysystem_cd) == 0) {
			rapidaPayment = "1";
			walletPayment = "1";
		} else if ("PayCashPayment".compareTo(paysystem_cd) == 0) {
			payCashPayment = "1";
			walletPayment = "1";
		} else if ("EPortPayment".compareTo(paysystem_cd) == 0) {
			EPortPayment = "1";
			walletPayment = "1";
		} else if ("KreditPilotPayment".compareTo(paysystem_cd) == 0) {
			kreditPilotPayment = "1";
			walletPayment = "1";
		}

	}

	public void setPaySystemOld(String paysystem_cd) {
		paysystem_cd = paysystem_cd.trim();
		if ("CardPaymentVisa".compareTo(paysystem_cd) == 0) {
			cardPayment = "1";
			choosenTypeCreditCard = "1";
			return;
		} else
			cardPayment = "0";
		if ("CardPaymentMaster".compareTo(paysystem_cd) == 0) {
			cardPayment = "1";
			choosenTypeCreditCard = "2";
			return;
		} else
			cardPayment = "0";
		if ("WalletPayment".compareTo(paysystem_cd) == 0) {
			walletPayment = "1";
			return;
		} else
			walletPayment = "0";
		if ("WebMoneyPayment".compareTo(paysystem_cd) == 0) {
			webMoneyPayment = "1";
			return;
		} else
			webMoneyPayment = "0";
		if ("RapidaPayment".compareTo(paysystem_cd) == 0) {
			rapidaPayment = "1";
			return;
		} else
			rapidaPayment = "0";
		if ("PayCashPayment".compareTo(paysystem_cd) == 0) {
			payCashPayment = "1";
			return;
		} else
			payCashPayment = "0";
		if ("EPortPayment".compareTo(paysystem_cd) == 0) {
			EPortPayment = "1";
			return;
		} else
			EPortPayment = "0";
		if ("KreditPilotPayment".compareTo(paysystem_cd) == 0) {
			kreditPilotPayment = "1";
			return;
		} else
			kreditPilotPayment = "0";
	}

	public void setStatusInrocess(String order_id) throws Exception {

		String query = "";
		QueryManager Adp = new QueryManager();
		try {
			Adp.BeginTransaction();

			query = "update orders  set paystatus_id = 2 where order_id = " + order_id;
			Adp.executeUpdate(query);
			Adp.commit();
		} catch (SQLException ex) {
			log.error(query, ex);
			Adp.rollback();
		} catch (Exception ex) {
			log.error(ex);
			Adp.rollback();
		} finally {
			Adp.close();
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public float string2Float(String s) {
		if (s == null)
			return 0.0F;
		float d;
		try {
			d = Float.parseFloat(s);
		} catch (NumberFormatException ex) {
			d = -1F;
		}
		return d;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAccount_hist_id() {
		return account_hist_id;
	}

	public void setAccount_hist_id(String account_hist_id) {
		this.account_hist_id = account_hist_id;
	}

	public String getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}

	public String getPaysystem_id() {
		return paysystem_id;
	}

	public void setPaysystem_id(String paysystem_id) {
		this.paysystem_id = paysystem_id;
	}

	public String getSubtotal_P() {
		return subtotal_P;
	}

	public void setSubtotal_P(String subtotal_P) {
		this.subtotal_P = subtotal_P;
	}

	public String getShop_IDP() {
		return shop_IDP;
	}

	public void setShop_IDP(String shop_IDP) {
		this.shop_IDP = shop_IDP;
	}

	public String getCurrency_cd() {
		return currency_cd;
	}

	public void setCurrency_cd(String currency_cd) {
		this.currency_cd = currency_cd;
	}

	public String getPaysystem_cd() {
		return paysystem_cd;
	}

	public void setPaysystem_cd(String paysystem_cd) {
		this.paysystem_cd = paysystem_cd;
	}

	public String getCardPayment() {
		return cardPayment;
	}

	public void setCardPayment(String cardPayment) {
		this.cardPayment = cardPayment;
	}

	public String getWalletPayment() {
		return walletPayment;
	}

	public void setWalletPayment(String walletPayment) {
		this.walletPayment = walletPayment;
	}

	public String getWebMoneyPayment() {
		return webMoneyPayment;
	}

	public void setWebMoneyPayment(String webMoneyPayment) {
		this.webMoneyPayment = webMoneyPayment;
	}

	public String getRapidaPayment() {
		return rapidaPayment;
	}

	public void setRapidaPayment(String rapidaPayment) {
		this.rapidaPayment = rapidaPayment;
	}

	public String getPayCashPayment() {
		return payCashPayment;
	}

	public void setPayCashPayment(String payCashPayment) {
		this.payCashPayment = payCashPayment;
	}

	public String getEPortPayment() {
		return EPortPayment;
	}

	public void setEPortPayment(String EPortPayment) {
		this.EPortPayment = EPortPayment;
	}

	public String getKreditPilotPayment() {
		return kreditPilotPayment;
	}

	public void setKreditPilotPayment(String kreditPilotPayment) {
		this.kreditPilotPayment = kreditPilotPayment;
	}

	public String getChoosenTypeCreditCard() {
		return choosenTypeCreditCard;
	}

	public void setChoosenTypeCreditCard(String choosenTypeCreditCard) {
		this.choosenTypeCreditCard = choosenTypeCreditCard;
	}

	private String description;

	private String amount;

	private String account_hist_id;

	private String currency_id;

	private String paysystem_id;

	private String subtotal_P;

	private String shop_IDP;

	private String currency_cd;

	private String paysystem_cd;

	private String cardPayment = "0";

	private String walletPayment = "0";

	private String webMoneyPayment = "0";

	private String rapidaPayment = "0";

	private String payCashPayment = "0";

	private String EPortPayment = "0";

	private String kreditPilotPayment = "0";

	private String choosenTypeCreditCard = "1";

}
