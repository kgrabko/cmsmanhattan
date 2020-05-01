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

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class PayGatewayBean extends com.cbsinc.cms.WebControls implements java.io.Serializable {

	private static final long serialVersionUID = 338258813101694289L;

	static private Logger log = Logger.getLogger(PayGatewayBean.class);

	private String shop_id = "";

	private String shop_cd = "";

	private String login = "";

	private String passwd = "";

	private String owner_id = "";

	private String pay_gateway_id = "";

	private String name_gateway = "";

	private String rertype_passwd = "";

	private String pay_url = "";

	public String getPay_url() {
		return pay_url;
	}

	public void setPay_url(String payUrl) {
		pay_url = payUrl;
	}

	public void mapmingShopBean(String site_id) {

		// String query = "select shop_id , shop_cd , owner_id , login , passwd
		// , pay_gateway_id from shop where site_id = " + site_id ;
		String query = "select shop_id , shop_cd ,  owner_id , login , passwd ,  pay_gateway.pay_gateway_id  , pay_gateway.name_gateway   from shop join pay_gateway on pay_gateway.pay_gateway_id = shop.pay_gateway_id  where active = true and  shop.site_id = "
				+ site_id;
		QueryManager Adp = new QueryManager();
		try {
			Adp.executeQuery(query);

			if (Adp.rows().size() != 0) {
				shop_id = (String) Adp.getValueAt(0, 0);
				shop_cd = (String) Adp.getValueAt(0, 1);
				owner_id = (String) Adp.getValueAt(0, 2);
				login = (String) Adp.getValueAt(0, 3);
				passwd = (String) Adp.getValueAt(0, 4);
				pay_gateway_id = (String) Adp.getValueAt(0, 5);
				name_gateway = (String) Adp.getValueAt(0, 6);
			} else
				log.error(
						"ERROR: select shop_id , shop_cd ,  owner_id , login , passwd , pay_gateway_id  , pay_gateway.name_gateway   from shop join pay_gateway on pay_gateway.pay_gateway_id = shop.pay_gateway_id  where active = true and  shop.site_id = "
								+ site_id + " \n   shop_cd = null ");

		} catch (SQLException ex) {

			log.error(query, ex);
		} catch (Exception ex) {

			log.error(ex);
		} finally {
			Adp.close();
		}

	}

	public void saveShopBeanBySiteId(String site_id) {
		StringBuffer buffquery = new StringBuffer();
		buffquery.append("update shop set shop_id = ").append(shop_id);
		buffquery.append(" , shop_cd  = ").append(shop_cd);
		buffquery.append(" , owner_id  = ").append(owner_id);
		buffquery.append(" , login  = '").append(login);
		buffquery.append("' , passwd  = '").append(passwd);
		buffquery.append("' , pay_gateway_id  = ").append(pay_gateway_id);
		buffquery.append(" from shop where site_id = ").append(site_id);

		QueryManager Adp = new QueryManager();
		try {
			Adp.executeUpdate(buffquery.toString());

			if (Adp.rows().size() != 0) {
				shop_id = (String) Adp.getValueAt(0, 0);
				shop_cd = (String) Adp.getValueAt(0, 1);
				owner_id = (String) Adp.getValueAt(0, 2);
				login = (String) Adp.getValueAt(0, 3);
				passwd = (String) Adp.getValueAt(0, 4);
				pay_gateway_id = (String) Adp.getValueAt(0, 5);
			}
		} catch (SQLException ex) {

			log.error(buffquery.toString(), ex);
		} catch (Exception ex) {

			log.error(ex);
		} finally {
			Adp.close();
		}

	}

	public void saveShopBean() {
		StringBuffer buffquery = new StringBuffer();
		buffquery.append("update shop set ");
		buffquery.append(" shop_cd  = ").append(shop_cd);
		// buffquery.append(" , owner_id = ").append(owner_id) ;
		buffquery.append(" , login  = '").append(login);
		buffquery.append("' , passwd  = '").append(passwd);
		buffquery.append("' , pay_gateway_id  = ").append(pay_gateway_id);
		buffquery.append("  where shop_id = ").append(shop_id);

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		try {
			Adp.executeUpdate(buffquery.toString());
			Adp.commit();

			if (Adp.rows().size() != 0) {
				shop_id = (String) Adp.getValueAt(0, 0);
				shop_cd = (String) Adp.getValueAt(0, 1);
				owner_id = (String) Adp.getValueAt(0, 2);
				login = (String) Adp.getValueAt(0, 3);
				passwd = (String) Adp.getValueAt(0, 4);
				pay_gateway_id = (String) Adp.getValueAt(0, 5);
			}
		} catch (SQLException ex) {
			log.error(buffquery.toString(), ex);
			Adp.rollback();
		} catch (Exception ex) {
			log.error(ex);
			Adp.rollback();
		} finally {
			Adp.close();
		}
	}

	public void addShopBean(String site_id) {
		StringBuffer buffquery = new StringBuffer();
		// insert into shop(shop_cd , login , passwd , pay_gateway_id , site_id
		// ) values( shop_cd , login , passwd , pay_gateway_id , site_id )
		buffquery.append("insert into shop(shop_cd , login , passwd , pay_gateway_id , site_id  ) values(");
		buffquery.append(shop_cd).append(",");
		buffquery.append("'").append(login).append("',");
		buffquery.append("'").append(passwd).append("',");
		buffquery.append(pay_gateway_id).append(",");
		buffquery.append(site_id).append(" )");

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		try {
			Adp.executeUpdate(buffquery.toString());

			if (Adp.rows().size() != 0) {
				shop_id = (String) Adp.getValueAt(0, 0);
				shop_cd = (String) Adp.getValueAt(0, 1);
				owner_id = (String) Adp.getValueAt(0, 2);
				login = (String) Adp.getValueAt(0, 3);
				passwd = (String) Adp.getValueAt(0, 4);
				pay_gateway_id = (String) Adp.getValueAt(0, 5);
			}

		} catch (SQLException ex) {
			log.error(buffquery.toString(), ex);
			Adp.rollback();
		} catch (Exception ex) {
			log.error(ex);
			Adp.rollback();
		} finally {
			Adp.close();
		}
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_cd() {
		return shop_cd;
	}

	public void setShop_cd(String shop_cd) {
		this.shop_cd = shop_cd;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getPay_gateway_id() {
		return pay_gateway_id;
	}

	public void setPay_gateway_id(String pay_gateway_id) {
		this.pay_gateway_id = pay_gateway_id;
	}

	public String getName_gateway() {
		return name_gateway;
	}

	public void setName_gateway(String name_gateway) {
		this.name_gateway = name_gateway;
	}

	public String getRertype_passwd() {
		return rertype_passwd;
	}

	public void setRertype_passwd(String rertype_passwd) {
		this.rertype_passwd = rertype_passwd;
	}

}