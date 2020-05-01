package com.cbsinc.cms;

import java.sql.*;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import com.cbsinc.cms.QueryManager;

import org.apache.log4j.Logger;

import com.cbsinc.cms.controllers.SiteType;
import com.cbsinc.cms.faceds.ApplicationContext;

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

public class CatalogListBean implements java.io.Serializable, ApplicationContext {

	private static final long serialVersionUID = 6603561134477886219L;

	public String[][] rows = new String[50][2];

	transient public QueryManager queryManager;

	private String listup = "";

	private String listdown = "";

	private Integer offset = 0;

	private String cururl;

	private String row_id = "0";

	private Integer indx_select = 0;

	private String holddate = "0";

	private Boolean isWriteble = true;

	private String message = "";

	static private Logger log = Logger.getLogger(CatalogListBean.class);

	public CatalogListBean() {

	}

	public CatalogListBean(ServletContext applicationContext) {
		this.applicationContext = applicationContext;

	}

	public String getTable(String type_id, AuthorizationPageBean authorizationPageBeanId,
			ServletContext applicationContext) {
		if (type_id == null || type_id.length() == 0)
			type_id = "0";

		String urlParent = "";

		cururl = "catalog_list.jsp?offset=" + offset;

		listup = "catalog_list.jsp?offset=" + (offset + 50);
		if (offset - 50 < 0)
			listdown = "catalog_list.jsp?offset=0";
		else
			listdown = "catalog_list.jsp?offset=" + (offset - 50);

		StringBuffer table = new StringBuffer();

		queryManager = new QueryManager();

		String query = "";

		query = "select catalog_id ,  lable   FROM catalog WHERE active = ? and site_id = ? and parent_id = ? ";

		// query = "select catalog_id , lable FROM catalog WHERE active = true and
		// site_id = " + site_id + " limit 50 offset " + offset;

		try {
			Object[] args = new Object[3];
			args[0] = true;
			args[1] = Long.valueOf(authorizationPageBeanId.getSite_id());
			args[2] = Long.valueOf(authorizationPageBeanId.getCatalog_id());
			// args[2] = Long.valueOf(parent_id) ;
			// queryManager.executeQuery(query);
			queryManager.executeQueryWithArgs(query, args, 50, offset);

			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			if (authorizationPageBeanId.getIntLevelUp() == 2) {
				table.append("<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"10%\" >№ </TD>" + "<TD WIDTH=\"70%\" >"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("section_catalog")
						+ "  </TD>" + "<TD WIDTH=\"20%\" ><a href =\"catalog_add.jsp\">"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("add_catalog")
						+ "</a> </TD>" + "</TR>\n");

			} else {
				table.append("<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"10%\" >№ </TD>" + "<TD WIDTH=\"70%\" >"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("section_catalog")
						+ "  </TD>" + "<TD WIDTH=\"20%\" ><a href =\"catalog_add.jsp\">"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("add_catalog")
						+ "</a> </TD>" + "</TR>\n");
			}

			if (queryManager.rows().size() < 50) {
				table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD></TD>" + "</TR>\n");
			} else {
				table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD><a href=\"" + listup + "\">"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("next_catalog")
						+ " 50</a>  </TD>" + "</TR>\n");
			}
			for (int i = 0; queryManager.rows().size() > i; i++) {
				rows[i][0] = (String) queryManager.getValueAt(i, 0);
				rows[i][1] = (String) queryManager.getValueAt(i, 1);

				urlParent = "<a href=\"catalog_list.jsp?parent_id=" + rows[i][0] + "\" >" + rows[i][1] + "</a>";

				table.append("<TR>" + "<TD>" + rows[i][0] + "</TD>" + "<TD>" + urlParent + "</TD>"
						+ "<TD algin=\"rigth\" ><a href =\"catalog_edit.jsp?row=" + i + "\">"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("edit_catalog")
						+ "</a> </TD>" + "<TD algin=\"rigth\" ><a href =\"catalog_list.jsp?del=" + i + "\">"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("del_catalog")
						+ "</a> </TD>" + "</TR>\n");
			}

			table.append("<TR>" + "<TD></TD>" + "<TD></TD>" + "<TD><a href=\"" + listdown + "\">"
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("back_catalog")
					+ " 50</a>  </TD>" + "</TR>\n");
			table.append("</tbody>\n");
			table.append("</TABLE>\n");

		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			queryManager.close();
		}
		return table.toString();
	}

	public String getNavigator(AuthorizationPageBean authorizationPageBeanId) {

		// if( localization == null ) localization =
		// PropertyResourceBundle.getBundle("localization", locale);
		String urlParent = "";
		String urlimgParent = "";
		String jspPage = "ProductPostCre.jsp";
		boolean folder = false;
		String image = "";

		cururl = "ProductPostCre.jsp?offset=" + offset;

		listup = "ProductPostCre.jsp?offset=" + (offset + 50);

		if (offset - 50 < 0)
			listdown = "ProductPostCre.jsp?offset=0";
		else
			listdown = "ProductPostCre.jsp?offset=" + (offset - 50);

		StringBuffer table = new StringBuffer();

		queryManager = new QueryManager();

		String query = "";

		query = "select catalog_id ,  lable   FROM catalog WHERE active = true and lang_id = "
				+ authorizationPageBeanId.getLang_id() + " and site_id = " + authorizationPageBeanId.getSite_id()
				+ " and parent_id = " + authorizationPageBeanId.getCatalogParent_id() + " limit 50 offset " + offset;

		// query = "select catalog_id , lable FROM catalog WHERE active = true and
		// site_id = " + site_id + " limit 50 offset " + offset;

		try {

			queryManager.executeQuery(query);
			table.append("<div class='box'>\n");
			table.append("<div class='body'>\n");
			table.append("<div style='overflow-y:auto; width:100%; height:200px;' >\n");
			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			if (authorizationPageBeanId.getIntLevelUp() == 2 && isWriteble) {
				table.append(
						"<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"3%\" > </TD>" + "<TD WIDTH=\"77%\" >"
								+ authorizationPageBeanId.getLocalization(applicationContext)
										.getString("section_catalog")
								+ "  </TD>"
								+ "<TD WIDTH=\"10%\" ><a href =\"ProductPostCre.jsp?action=add\"><font color='white' >"
								+ authorizationPageBeanId.getLocalization(applicationContext).getString("add_catalog")
								+ "</font></a> </TD>" + "<TD WIDTH=\"10%\" ></TD>" + "</TR>\n");

			} else {
				table.append("<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"3%\" > </TD>" + "<TD WIDTH=\"77%\" >"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("section_catalog")
						+ "  </TD>" + "<TD WIDTH=\"10%\" ></TD>" + "<TD WIDTH=\"10%\" ></TD>" + "</TR>\n");
			}

			for (int i = 0; queryManager.rows().size() > i; i++) {
				rows[i][0] = (String) queryManager.getValueAt(i, 0);
				rows[i][1] = (String) queryManager.getValueAt(i, 1);
				folder = isFolder(rows[i][0]);
				image = folder
						? "<img alt='"
								+ authorizationPageBeanId.getLocalization(applicationContext)
										.getString("this_is_folder_catalog")
								+ "'  width='22' height='22' src ='images/folder.png' ></img>"
						: "<img alt='Нет вложений' width='22' height='22' src ='images/file.png' ></img>";

				urlimgParent = "<a   href=\"ProductPostCre.jsp?parent_id=" + rows[i][0] + "\" >" + image + "</a>";
				urlParent = "<a   href=\"ProductPostCre.jsp?parent_id=" + rows[i][0] + "\" >" + rows[i][1] + "</a>";

				if (authorizationPageBeanId.getIntLevelUp() == 2 && isWriteble) {
					table.append("<TR id='" + rows[i][0] + "'  onMouseOver=\"setColor( '#DFE3EF' , '" + rows[i][0]
							+ "' )\"  onMouseOut=\"setColor( 'white' , '" + rows[i][0]
							+ "' )\"   onMouseDown=\"selected( '#FFEFFF' , '" + rows[i][0] + "' )\"  >" + "<TD>"
							+ urlimgParent + "</TD>" + "<TD>" + urlParent + "</TD>"
							+ "<TD algin=\"rigth\" ><a href =\"ProductPostCre.jsp?action=edit&row=" + i + "\">"
							+ authorizationPageBeanId.getLocalization(applicationContext).getString("edit_catalog")
							+ "</a> </TD>" + "<TD algin=\"rigth\" ><a href =\"ProductPostCre.jsp?del=" + i + "\">"
							+ authorizationPageBeanId.getLocalization(applicationContext).getString("del_catalog")
							+ "</a> </TD>" + "</TR>\n");
				} else {
					table.append("<TR id='" + rows[i][0] + "'  onMouseOver=\"setColor( '#DFE3EF' , '" + rows[i][0]
							+ "' )\"  onMouseOut=\"setColor( 'white' , '" + rows[i][0]
							+ "' )\"   onMouseDown=\"selected( '#FFEFFF' , '" + rows[i][0] + "' )\"  >" + "<TD>"
							+ urlimgParent + "</TD>" + "<TD>" + urlParent + "</TD>" + "<TD algin=\"rigth\" ></TD>"
							+ "<TD algin=\"rigth\" ></TD>" + "</TR>\n");
				}

			}

			// table.append("<TR>" + "<TD>Страница "+ getPageNumber() +"</TD>" + "<TD> " +
			// authorizationPageBeanId.getLocalization(applicationContext).getString("open_page_catalog")
			// + " <a href=\""+ jspPage+"?offset=0" + "\">1</a>.<a href=\""+
			// jspPage+"?offset=50" + "\">2</a>.<a href=\""+ jspPage+"?offset=20" +
			// "\">3</a>.<a href=\""+ jspPage+"?offset=30" + "\">4</a>.<a href=\""+
			// jspPage+"?offset=40" + "\">5</a>.<a href=\""+ jspPage+"?offset=50" +
			// "\">6</a>.<a href=\""+ jspPage+"?offset=60" + "\">7</a></TD>" + "<TD><a
			// href=\"" + listup + "\">>></a> " +
			// authorizationPageBeanId.getLocalization(applicationContext).getString("next_page")
			// + " </TD>" + "<TD><a href=\"" + listdown + "\"><<</a> </TD>" + "</TR>\n");

			table.append("</tbody>\n");
			table.append("</TABLE>\n");
			table.append("</div>\n");
			table.append("</div>\n");
			table.append("</div>\n");

			table.append("<div class='box'>\n");
			table.append("<div class='body'>\n");
			table.append("<div>\n");
			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			table.append("<TR>" + "<TD  align='left' WIDTH='33%' >"
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("page_catalog") + " "
					+ getPageNumber() + " </TD> <TD align='center' WIDTH='34%' > "
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("open_page_catalog")
					+ " <a href=\"" + jspPage + "?offset=0" + "\">1</a>.<a href=\"" + jspPage + "?offset=50"
					+ "\">2</a>.<a href=\"" + jspPage + "?offset=100" + "\">3</a>.<a href=\"" + jspPage + "?offset=150"
					+ "\">4</a>.<a href=\"" + jspPage + "?offset=200" + "\">5</a>.<a href=\"" + jspPage + "?offset=250"
					+ "\">6</a>.<a href=\"" + jspPage + "?offset=300" + "\">7</a></TD>"
					+ "<TD WIDTH='33%' align='right' ><a href=\"" + listup + "\"> >> </a> "
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("next_page") + " <a href=\""
					+ listdown + "\"> << </a>  </TD>" + "</TR>\n");
			table.append("</tbody>\n");
			table.append("</table>\n");
			table.append("</div>\n");
			table.append("</div>\n");
			table.append("</div>\n");

		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return table.toString();
	}

	public String getUserNavigator(AuthorizationPageBean authorizationPageBeanId) {

		String urlParent = "";
		String urlimgParent = "";
		String jspPage = "ProductUserPost.jsp";
		boolean folder = false;
		String image = "";
		// if( localization == null ) localization =
		// PropertyResourceBundle.getBundle("localization", locale);

		cururl = "ProductUserPost.jsp?offset=" + offset;

		listup = "ProductUserPost.jsp?offset=" + (offset + 50);

		if (offset - 50 < 0)
			listdown = "ProductUserPost.jsp?offset=0";
		else
			listdown = "ProductUserPost.jsp?offset=" + (offset - 50);

		StringBuffer table = new StringBuffer();

		queryManager = new QueryManager();

		String query = "";

		query = "select catalog_id ,  lable   FROM catalog WHERE active = true  and lang_id = "
				+ authorizationPageBeanId.getLang_id() + " and site_id = " + authorizationPageBeanId.getSite_id()
				+ " and parent_id = " + authorizationPageBeanId.getCatalog_id() + " limit 50 offset " + offset;

		// query = "select catalog_id , lable FROM catalog WHERE active = true and
		// site_id = " + site_id + " limit 50 offset " + offset;

		try {

			queryManager.executeQuery(query);
			table.append("<div class='box'>\n");
			table.append("<div class='body'>\n");
			table.append("<div style='overflow-y:auto; width:100%; height:200px;' >\n");
			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			if (authorizationPageBeanId.getIntLevelUp() == 2 && isWriteble) {
				table.append(
						"<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"3%\" >№ </TD>" + "<TD WIDTH=\"77%\" >"
								+ authorizationPageBeanId.getLocalization(applicationContext)
										.getString("section_catalog")
								+ "  </TD>"
								+ "<TD WIDTH=\"10%\" ><a href =\"ProductUserPost.jsp?action=add\"><font color='white' >"
								+ authorizationPageBeanId.getLocalization(applicationContext).getString("add_catalog")
								+ "</font></a> </TD>" + "<TD WIDTH=\"10%\" ></TD>" + "</TR>\n");

			} else {
				table.append("<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"3%\" >№ </TD>" + "<TD WIDTH=\"77%\" >"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("section_catalog")
						+ "  </TD>" + "<TD WIDTH=\"10%\" ></TD>" + "<TD WIDTH=\"10%\" ></TD>" + "</TR>\n");
			}

			for (int i = 0; queryManager.rows().size() > i; i++) {
				rows[i][0] = (String) queryManager.getValueAt(i, 0);
				rows[i][1] = (String) queryManager.getValueAt(i, 1);
				folder = isFolder(rows[i][0]);
				image = folder
						? "<img alt='"
								+ authorizationPageBeanId.getLocalization(applicationContext)
										.getString("this_is_folder_catalog")
								+ "'  width='22' height='22' src ='images/folder.png' ></img>"
						: "<img alt='Нет вложений' width='22' height='22' src ='images/file.png' ></img>";

				urlimgParent = "<a   href=\"ProductUserPost.jsp?parent_id=" + rows[i][0] + "\" >" + image + "</a>";
				urlParent = "<a   href=\"ProductUserPost.jsp?parent_id=" + rows[i][0] + "\" >" + rows[i][1] + "</a>";

				if (authorizationPageBeanId.getIntLevelUp() == 2 && isWriteble) {
					table.append("<TR id='" + rows[i][0] + "'  onMouseOver=\"setColor( '#DFE3EF' , '" + rows[i][0]
							+ "' )\"  onMouseOut=\"setColor( 'white' , '" + rows[i][0]
							+ "' )\"   onMouseDown=\"selected( '#FFEFFF' , '" + rows[i][0] + "' )\"  >" + "<TD>"
							+ urlimgParent + "</TD>" + "<TD>" + urlParent + "</TD>"
							+ "<TD algin=\"rigth\" ><a href =\"ProductUserPost.jsp?action=edit&row=" + i + "\">"
							+ authorizationPageBeanId.getLocalization(applicationContext).getString("edit_catalog")
							+ "</a> </TD>" + "<TD algin=\"rigth\" ><a href =\"ProductUserPost.jsp?del=" + i + "\">"
							+ authorizationPageBeanId.getLocalization(applicationContext).getString("del_catalog")
							+ "</a> </TD>" + "</TR>\n");
				} else {
					table.append("<TR id='" + rows[i][0] + "'  onMouseOver=\"setColor( '#DFE3EF' , '" + rows[i][0]
							+ "' )\"  onMouseOut=\"setColor( 'white' , '" + rows[i][0]
							+ "' )\"   onMouseDown=\"selected( '#FFEFFF' , '" + rows[i][0] + "' )\"  >" + "<TD>"
							+ urlimgParent + "</TD>" + "<TD>" + urlParent + "</TD>" + "<TD algin=\"rigth\" ></TD>"
							+ "<TD algin=\"rigth\" ></TD>" + "</TR>\n");
				}

			}

			// table.append("<TR>" + "<TD>Страница "+ getPageNumber() +"</TD>" + "<TD>
			// Открыть страницу <a href=\""+ jspPage+"?offset=0" + "\">1</a>.<a href=\""+
			// jspPage+"?offset=50" + "\">2</a>.<a href=\""+ jspPage+"?offset=20" +
			// "\">3</a>.<a href=\""+ jspPage+"?offset=30" + "\">4</a>.<a href=\""+
			// jspPage+"?offset=40" + "\">5</a>.<a href=\""+ jspPage+"?offset=50" +
			// "\">6</a>.<a href=\""+ jspPage+"?offset=60" + "\">7</a></TD>" + "<TD><a
			// href=\"" + listup + "\">>></a> " +
			// authorizationPageBeanId.getLocalization(applicationContext).getString("next_page")
			// + " </TD>" + "<TD><a href=\"" + listdown + "\"><<</a> </TD>" + "</TR>\n");

			table.append("</tbody>\n");
			table.append("</TABLE>\n");
			table.append("</div>\n");
			table.append("</div>\n");
			table.append("</div>\n");

			table.append("<div class='box'>\n");
			table.append("<div class='body'>\n");
			table.append("<div>\n");
			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			table.append("<TR>" + "<TD  align='left' WIDTH='33%' >"
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("page_catalog") + " "
					+ getPageNumber() + " </TD> <TD align='center' WIDTH='34%' > "
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("open_page_catalog")
					+ " <a href=\"" + jspPage + "?offset=0" + "\">1</a>.<a href=\"" + jspPage + "?offset=50"
					+ "\">2</a>.<a href=\"" + jspPage + "?offset=100" + "\">3</a>.<a href=\"" + jspPage + "?offset=150"
					+ "\">4</a>.<a href=\"" + jspPage + "?offset=200" + "\">5</a>.<a href=\"" + jspPage + "?offset=250"
					+ "\">6</a>.<a href=\"" + jspPage + "?offset=300" + "\">7</a></TD>"
					+ "<TD WIDTH='33%' align='right' ><a href=\"" + listup + "\"> >> </a> "
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("next_page") + " <a href=\""
					+ listdown + "\"> << </a>  </TD>" + "</TR>\n");
			table.append("</tbody>\n");
			table.append("</table>\n");
			table.append("</div>\n");
			table.append("</div>\n");
			table.append("</div>\n");

		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return table.toString();
	}

	public String getNavigator(String jspPage, AuthorizationPageBean authorizationPageBeanId) {

		// if( localization == null ) localization =
		// PropertyResourceBundle.getBundle("localization", locale);
		String urlParent = "";
		String urlimgParent = "";
		// String jspPage = "ProductPostCre.jsp" ;
		boolean folder = false;
		String image = "";

		cururl = jspPage + "?offset=" + offset;

		listup = jspPage + "?offset=" + (offset + 50);

		if (offset - 50 < 0)
			listdown = jspPage + "?offset=0";
		else
			listdown = jspPage + "?offset=" + (offset - 50);

		StringBuffer table = new StringBuffer();

		queryManager = new QueryManager();

		String query = "";

		query = "select catalog_id ,  lable   FROM catalog WHERE active = true and lang_id = "
				+ authorizationPageBeanId.getLang_id() + " and site_id = " + authorizationPageBeanId.getSite_id()
				+ " and parent_id = " + authorizationPageBeanId.getCatalogParent_id() + " limit 50 offset " + offset;

		// query = "select catalog_id , lable FROM catalog WHERE active = true and
		// site_id = " + site_id + " limit 50 offset " + offset;

		try {

			queryManager.executeQuery(query);
			table.append("<div class='box'>\n");
			table.append("<div class='body'>\n");
			table.append("<div style='overflow-y:auto; width:100%; height:200px;' >\n");
			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			if (authorizationPageBeanId.getIntLevelUp() == 2 && isWriteble) {
				table.append("<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"3%\" > </TD>" + "<TD WIDTH=\"77%\" >"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("section_catalog")
						+ "  </TD>" + "<TD WIDTH=\"10%\" ><a href =\"" + jspPage + "?action=add\"><font color='white' >"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("add_catalog")
						+ "</font></a> </TD>" + "<TD WIDTH=\"10%\" ></TD>" + "</TR>\n");

			} else {
				table.append("<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"3%\" ></TD>" + "<TD WIDTH=\"77%\" >"
						+ authorizationPageBeanId.getLocalization(applicationContext).getString("section_catalog")
						+ "  </TD>" + "<TD WIDTH=\"10%\" ></TD>" + "<TD WIDTH=\"10%\" ></TD>" + "</TR>\n");
			}

			for (int i = 0; queryManager.rows().size() > i; i++) {
				rows[i][0] = (String) queryManager.getValueAt(i, 0);
				rows[i][1] = (String) queryManager.getValueAt(i, 1);
				folder = isFolder(rows[i][0]);
				image = folder
						? "<img alt='"
								+ authorizationPageBeanId.getLocalization(applicationContext)
										.getString("this_is_folder_catalog")
								+ "'  width='22' height='22' src ='images/folder.png' ></img>"
						: "<img alt='Нет вложений' width='22' height='22' src ='images/file.png' ></img>";

				urlimgParent = "<a   href=\"" + jspPage + "?parent_id=" + rows[i][0] + "\" >" + image + "</a>";
				urlParent = "<a   href=\"" + jspPage + "?parent_id=" + rows[i][0] + "\" >" + rows[i][1] + "</a>";

				if (authorizationPageBeanId.getIntLevelUp() == 2 && isWriteble) {
					table.append("<TR id='" + rows[i][0] + "'  onMouseOver=\"setColor( '#DFE3EF' , '" + rows[i][0]
							+ "' )\"  onMouseOut=\"setColor( 'white' , '" + rows[i][0]
							+ "' )\"   onMouseDown=\"selected( '#FFEFFF' , '" + rows[i][0] + "' )\"  >" + "<TD>"
							+ urlimgParent + "</TD>" + "<TD>" + urlParent + "</TD>" + "<TD algin=\"rigth\" ><a href =\""
							+ jspPage + "?action=edit&row=" + i + "\">"
							+ authorizationPageBeanId.getLocalization(applicationContext).getString("edit_catalog")
							+ "</a> </TD>" + "<TD algin=\"rigth\" ><a href =\"" + jspPage + "?del=" + i + "\">"
							+ authorizationPageBeanId.getLocalization(applicationContext).getString("del_catalog")
							+ "</a> </TD>" + "</TR>\n");
				} else {
					table.append("<TR id='" + rows[i][0] + "'  onMouseOver=\"setColor( '#DFE3EF' , '" + rows[i][0]
							+ "' )\"  onMouseOut=\"setColor( 'white' , '" + rows[i][0]
							+ "' )\"   onMouseDown=\"selected( '#FFEFFF' , '" + rows[i][0] + "' )\"  >" + "<TD>"
							+ urlimgParent + "</TD>" + "<TD>" + urlParent + "</TD>" + "<TD algin=\"rigth\" ></TD>"
							+ "<TD algin=\"rigth\" ></TD>" + "</TR>\n");
				}

			}

			// table.append("<TR>" + "<TD>Страница "+ getPageNumber() +"</TD>" + "<TD>
			// Открыть страницу <a href=\""+ jspPage+"?offset=0" + "\">1</a>.<a href=\""+
			// jspPage+"?offset=50" + "\">2</a>.<a href=\""+ jspPage+"?offset=20" +
			// "\">3</a>.<a href=\""+ jspPage+"?offset=30" + "\">4</a>.<a href=\""+
			// jspPage+"?offset=40" + "\">5</a>.<a href=\""+ jspPage+"?offset=50" +
			// "\">6</a>.<a href=\""+ jspPage+"?offset=60" + "\">7</a></TD>" + "<TD><a
			// href=\"" + listup + "\">>></a> " +
			// authorizationPageBeanId.getLocalization(applicationContext).getString("next_page")
			// + " </TD>" + "<TD><a href=\"" + listdown + "\"><<</a> </TD>" + "</TR>\n");

			table.append("</tbody>\n");
			table.append("</TABLE>\n");
			table.append("</div>\n");
			table.append("</div>\n");
			table.append("</div>\n");

			table.append("<div class='box'>\n");
			table.append("<div class='body'>\n");
			table.append("<div>\n");
			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			table.append("<TR>" + "<TD  align='left' WIDTH='33%' >"
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("page_catalog") + " "
					+ getPageNumber() + " </TD> <TD align='center' WIDTH='34%' > "
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("open_page_catalog")
					+ " <a href=\"" + jspPage + "?offset=0" + "\">1</a>.<a href=\"" + jspPage + "?offset=50"
					+ "\">2</a>.<a href=\"" + jspPage + "?offset=100" + "\">3</a>.<a href=\"" + jspPage + "?offset=150"
					+ "\">4</a>.<a href=\"" + jspPage + "?offset=200" + "\">5</a>.<a href=\"" + jspPage + "?offset=250"
					+ "\">6</a>.<a href=\"" + jspPage + "?offset=300" + "\">7</a></TD>"
					+ "<TD WIDTH='33%' align='right' ><a href=\"" + listup + "\"> >> </a> "
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("next_page")
					+ " 50 <a href=\"" + listdown + "\"> << </a>  </TD>" + "</TR>\n");
			table.append("</tbody>\n");
			table.append("</table>\n");
			table.append("</div>\n");
			table.append("</div>\n");
			table.append("</div>\n");

		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return table.toString();
	}

	String getPageNumber() {
		if (offset == 0)
			return "1";
		if (offset == 50)
			return "2";
		if (offset == 100)
			return "3";
		if (offset == 150)
			return "4";
		if (offset == 200)
			return "5";
		if (offset == 250)
			return "6";
		if (offset == 300)
			return "7";
		if (offset == 350)
			return "8";
		if (offset == 400)
			return "9";
		if (offset == 450)
			return "10";
		if (offset == 500)
			return "11";
		if (offset == 550)
			return "12";
		if (offset == 600)
			return "13";
		if (offset == 650)
			return "14";
		if (offset == 700)
			return "15";
		if (offset == 750)
			return "16";
		if (offset == 800)
			return "17";
		if (offset == 850)
			return "18";
		if (offset == 900)
			return "19";
		if (offset == 950)
			return "20";
		if (offset == 1000)
			return "21";
		return "0";
	}

	/**
	 * For forum functionality
	 * 
	 * @return
	 */

	public String getNavigatorMainSiteRead(AuthorizationPageBean authorizationPageBeanId) {

		// if( localization == null ) localization =
		// PropertyResourceBundle.getBundle("localization", locale);
		String urlParent = "";
		String jspPage = "ProductPostCre.jsp";

		cururl = "ProductUserPost.jsp?offset=" + offset;

		listup = "ProductUserPost.jsp?offset=" + (offset + 50);
		if (offset - 50 < 0)
			listdown = "ProductUserPost.jsp?offset=0";
		else
			listdown = "ProductUserPost.jsp?offset=" + (offset - 50);

		StringBuffer table = new StringBuffer();

		queryManager = new QueryManager();

		String query = "";

		query = "select catalog_id ,  lable   FROM catalog WHERE active = true and site_id = " + SiteType.MAIN_SITE
				+ " and parent_id = " + authorizationPageBeanId.getCatalog_id() + " limit 50 offset " + offset;

		// query = "select catalog_id , lable FROM catalog WHERE active = true and
		// site_id = " + site_id + " limit 50 offset " + offset;

		try {

			queryManager.executeQuery(query);

			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");

			table.append("<TR BGCOLOR=\"#8CACBB\" >" + "<TD WIDTH=\"10%\" >№ </TD>" + "<TD WIDTH=\"70%\" >"
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("section_catalog")
					+ "  </TD>" + "<TD WIDTH=\"10%\" ></TD>" + "<TD WIDTH=\"10%\" ></TD>" + "</TR>\n");

			for (int i = 0; queryManager.rows().size() > i; i++) {
				rows[i][0] = (String) queryManager.getValueAt(i, 0);
				rows[i][1] = (String) queryManager.getValueAt(i, 1);

				// urlParent = "<a href=\"catalog_list.jsp?parent_id="+ rows[i][0] +"\"
				// >"+rows[i][1]+"</a>";
				urlParent = "<a href=\"ProductUserPost.jsp?parent_id=" + rows[i][0] + "\" >" + rows[i][1] + "</a>";

				table.append("<TR>" + "<TD>" + rows[i][0] + "</TD>" + "<TD>" + urlParent + "</TD>"
						+ "<TD algin=\"rigth\" ></TD>" + "<TD algin=\"rigth\" ></TD>" + "</TR>\n");
			}

			table.append("<TR>" + "<TD>"
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("page_catalog") + " "
					+ getPageNumber() + "</TD>" + "<TD> "
					+ authorizationPageBeanId.getLocalization(applicationContext).getString("open_page_catalog")
					+ " <a href=\"" + jspPage + "?offset=0" + "\">1</a>.<a href=\"" + jspPage + "?offset=50"
					+ "\">2</a>.<a href=\"" + jspPage + "?offset=20" + "\">3</a>.<a href=\"" + jspPage + "?offset=30"
					+ "\">4</a>.<a href=\"" + jspPage + "?offset=40" + "\">5</a>.<a href=\"" + jspPage + "?offset=50"
					+ "\">6</a>.<a href=\"" + jspPage + "?offset=60" + "\">7</a></TD>" + "<TD><a href=\"" + listup
					+ "\">>></a> " + authorizationPageBeanId.getLocalization(applicationContext).getString("next_page")
					+ " </TD>" + "<TD><a href=\"" + listdown + "\"><<</a>  </TD>" + "</TR>\n");

			table.append("</tbody>\n");
			table.append("</TABLE>\n");

		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			queryManager.close();
		}
		return table.toString();
	}

	public void delete(String selectedCatalog_id, AuthorizationPageBean authorizationPageBeanId) {
		if (selectedCatalog_id.startsWith("-") || selectedCatalog_id.equals("2"))
			return;
		queryManager = new QueryManager();
		String query = "";
		query = "delete FROM catalog WHERE site_id = " + authorizationPageBeanId.getSite_id() + " and catalog_id = "
				+ selectedCatalog_id;
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

	public boolean isFolder(String parent_id) {
		QueryManager queryManager = new QueryManager();
		String query = "";
		long count = 0;
		try {

			query = "select count(catalog_id) as number  FROM catalog WHERE  parent_id  = " + parent_id;
			queryManager.executeQuery(query);
			if (queryManager.rows().size() > 0) {
				count = Long.parseLong((String) queryManager.getValueAt(0, 0));
			}
		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return count == 0 ? false : true;
	}

	public String getCatalogPath(String _catalog_id) {
		queryManager = new QueryManager();
		String query = "";
		long _parent_id = 0;
		String lable = "";
		boolean doWhile = true;
		StringBuffer path = new StringBuffer();

		try {

			while (doWhile) {
				query = "select parent_id , lable   FROM catalog WHERE  catalog_id  = " + _catalog_id;
				queryManager.executeQuery(query);
				if (queryManager.rows().size() > 0) {
					if (((String) queryManager.getValueAt(0, 0)).length() > 0)
						_parent_id = Long.parseLong((String) queryManager.getValueAt(0, 0));
					lable = (String) queryManager.getValueAt(0, 1);
					_catalog_id = "" + _parent_id;
					doWhile = true;
					path.append("/" + lable);
				} else
					doWhile = false;
			}

		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return path.toString();
	}

	public String getCatalogPath(AuthorizationPageBean authorizationPageBeanId) {
		queryManager = new QueryManager();
		String query = "";
		long _parent_id = 0;
		long _catalog_id = 0;
		String lable = "";
		boolean doWhile = true;
		StringBuffer path = new StringBuffer();
		if (authorizationPageBeanId.getCatalog_id().length() > 0)
			_parent_id = Long.parseLong(authorizationPageBeanId.getCatalog_id());
		if (_parent_id == 0)
			return "/";

		try {

			while (doWhile) {
				// Adp.BeginTransaction();
				query = "select parent_id , lable   FROM catalog WHERE  catalog_id  = " + _parent_id;
				queryManager.executeQuery(query);
				if (queryManager.rows().size() > 0) {
					if (((String) queryManager.getValueAt(0, 0)).length() > 0)
						_parent_id = Long.parseLong((String) queryManager.getValueAt(0, 0));
					lable = (String) queryManager.getValueAt(0, 1);
					doWhile = true;
					path.insert(0, "/" + lable);
				} else
					doWhile = false;
				// Adp.commit();
			}

		} catch (SQLException ex) {
			// Adp.rollback();
			log.error(query, ex);
		} catch (Exception ex) {
			// Adp.rollback();
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return path.toString();
	}

	public String getCatalogUrlPath(AuthorizationPageBean authorizationPageBeanId) {
		// if( localization == null ) localization =
		// PropertyResourceBundle.getBundle("localization", locale);
		queryManager = new QueryManager();
		String query = "";
		long _parent_id = 0;
		long parent_id_last = 0;
		long _catalog_id = 0;
		String lable = "";
		String lable_last = authorizationPageBeanId.getLocalization(applicationContext)
				.getString("section_of_list_catalog");
		boolean doWhile = true;
		StringBuffer path = new StringBuffer();
		if (authorizationPageBeanId.getCatalog_id().length() > 0)
			_parent_id = Long.parseLong(authorizationPageBeanId.getCatalogParent_id());
		if (_parent_id == 0)
			return lable_last;
		String item = "";

		try {

			while (doWhile) {
				// Adp.BeginTransaction();
				query = "select parent_id , lable   FROM catalog WHERE  catalog_id  = " + _parent_id;
				queryManager.executeQuery(query);
				if (queryManager.rows().size() > 0) {
					if (((String) queryManager.getValueAt(0, 0)).length() > 0)
						parent_id_last = Long.parseLong((String) queryManager.getValueAt(0, 0));
					lable = (String) queryManager.getValueAt(0, 1);
					doWhile = true;
					// path.insert(0,"/" + lable) ;
					item = "<a href=\"ProductPostCre.jsp?parent_id=" + _parent_id + "\" >" + "/" + lable + "</a>";
					path.insert(0, item);
					_parent_id = parent_id_last;

				} else
					doWhile = false;
				// Adp.commit();
			}

			item = "<a href=\"ProductPostCre.jsp?parent_id=" + parent_id_last + "\" >" + "/" + lable_last + "</a>";
			path.insert(0, item);

		} catch (SQLException ex) {
			// Adp.rollback();
			log.error(query, ex);
		} catch (Exception ex) {
			// Adp.rollback();
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return path.toString();
	}

	public String getCatalogUrlPath(String jspPage, AuthorizationPageBean authorizationPageBeanId) {
		// if( localization == null ) localization =
		// PropertyResourceBundle.getBundle("localization", locale);
		queryManager = new QueryManager();
		String query = "";
		long _parent_id = 0;
		long parent_id_last = 0;
		long _catalog_id = 0;
		String lable = "";
		String lable_last = authorizationPageBeanId.getLocalization(applicationContext)
				.getString("section_of_list_catalog");
		boolean doWhile = true;
		StringBuffer path = new StringBuffer();
		if (authorizationPageBeanId.getCatalog_id().length() > 0)
			_parent_id = Long.parseLong(authorizationPageBeanId.getCatalogParent_id());
		if (_parent_id == 0)
			return lable_last;
		String item = "";

		try {

			while (doWhile) {
				// Adp.BeginTransaction();
				query = "select parent_id , lable   FROM catalog WHERE  catalog_id  = " + _parent_id;
				queryManager.executeQuery(query);
				if (queryManager.rows().size() > 0) {
					if (((String) queryManager.getValueAt(0, 0)).length() > 0)
						parent_id_last = Long.parseLong((String) queryManager.getValueAt(0, 0));
					lable = (String) queryManager.getValueAt(0, 1);
					doWhile = true;
					// path.insert(0,"/" + lable) ;
					item = "<a href=\"" + jspPage + "?parent_id=" + _parent_id + "\" >" + "/" + lable + "</a>";
					path.insert(0, item);
					_parent_id = parent_id_last;

				} else
					doWhile = false;
				// Adp.commit();
			}

			item = "<a href=\"" + jspPage + "?parent_id=" + parent_id_last + "\" >" + "/" + lable_last + "</a>";
			path.insert(0, item);

		} catch (SQLException ex) {
			// Adp.rollback();
			log.error(query, ex);
		} catch (Exception ex) {
			// Adp.rollback();
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return path.toString();
	}

	public String getCatalogUrlPathMain(AuthorizationPageBean authorizationPageBeanId) {
		// if( localization == null ) localization =
		// PropertyResourceBundle.getBundle("localization", locale);
		queryManager = new QueryManager();
		String query = "";
		long _parent_id = 0;
		long parent_id_last = 0;
		long _catalog_id = 0;
		String lable = "";
		String lable_last = authorizationPageBeanId.getLocalization(applicationContext)
				.getString("section_of_list_catalog");
		boolean doWhile = true;
		StringBuffer path = new StringBuffer();
		if (authorizationPageBeanId.getCatalog_id().length() > 0)
			_parent_id = Long.parseLong(authorizationPageBeanId.getCatalog_id());
		if (_parent_id == 0)
			return lable_last;
		String item = "";

		try {

			while (doWhile) {
				// Adp.BeginTransaction();
				query = "select parent_id , lable   FROM catalog WHERE  catalog_id  = " + _parent_id;
				queryManager.executeQuery(query);
				if (queryManager.rows().size() > 0) {
					if (((String) queryManager.getValueAt(0, 0)).length() > 0)
						parent_id_last = Long.parseLong((String) queryManager.getValueAt(0, 0));
					lable = (String) queryManager.getValueAt(0, 1);
					doWhile = true;
					// path.insert(0,"/" + lable) ;
					item = "<a href=\"ProductUserPost.jsp?parent_id=" + _parent_id + "\" >" + "/" + lable + "</a>";
					path.insert(0, item);
					_parent_id = parent_id_last;

				} else
					doWhile = false;
				// Adp.commit();
			}

			item = "<a href=\"ProductUserPost.jsp?parent_id=" + parent_id_last + "\" >" + "/" + lable_last + "</a>";
			path.insert(0, item);

		} catch (SQLException ex) {
			// Adp.rollback();
			log.error(query, ex);
		} catch (Exception ex) {
			// Adp.rollback();
			log.error(ex);
		} finally {
			queryManager.close();
		}

		return path.toString();
	}

	public String getXMLDBList(String pagejsp, String name, String selected_cd, String query) {

		String strCD = "0";
		String strLable = "Other";
		StringBuffer table = new StringBuffer();
		QueryManager Adp = new QueryManager();
		try {

			Adp.executeQuery(query);

			table.append("<" + name + ">\n");
			table.append("<" + name + "-item>");
			table.append("<selected>" + selected_cd + "</selected>");
			table.append("<item></item>");
			table.append("<code>" + "-1" + "</code>");
			table.append("<url>" + pagejsp + "=" + strCD + "</url>");
			table.append("</" + name + "-item>\n");

			for (int i = 0; Adp.rows().size() > i; i++) {
				strCD = (String) Adp.getValueAt(i, 0);
				strLable = (String) Adp.getValueAt(i, 1);
				table.append("<" + name + "-item>");
				table.append("<selected>" + selected_cd + "</selected>");
				table.append("<item>" + strLable + "</item>");
				table.append("<code>" + strCD + "</code>");
				table.append("<url>" + pagejsp + "=" + strCD + "</url>");
				table.append("</" + name + "-item>\n");
			}

			table.append("</" + name + ">\n");
		} catch (SQLException ex) {
			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			Adp.close();
		}
		return table.toString();
	}

	/**
	 * Output path (catalog) for navigation by ctalog warrning parent 0 replace at
	 * -2
	 * 
	 * @param pagejsp
	 * @param name
	 * @param selected_cd
	 * @return
	 */

	public String getCatalogXMLUrlPath(String pagejsp, String name, String selected_cd,
			AuthorizationPageBean authorizationPageBeanId) {
		queryManager = new QueryManager();
		String query = "";
		long _parent_id = 0;
		long parent_id_last = 0;
		String lable = "";
		String lable_last = authorizationPageBeanId.getLocalization(applicationContext)
				.getString("section_of_list_catalog");
		boolean doWhile = true;
		StringBuffer path = new StringBuffer();

		StringBuffer rowItem = new StringBuffer();
		rowItem.append("<" + name + ">\n");
		rowItem.append("<" + name + "-item>");
		rowItem.append("<selected>" + selected_cd + "</selected>");
		rowItem.append("<item>" + lable_last + "</item>");
		rowItem.append("<code>" + -2 + "</code>");
		rowItem.append("<url>" + pagejsp + "=" + -2 + "</url>");
		rowItem.append("</" + name + "-item>\n");
		rowItem.append("</" + name + ">\n");

		if (authorizationPageBeanId.getCatalog_id().length() > 0)
			_parent_id = Long.parseLong(selected_cd);
		if (_parent_id == 0)
			return rowItem.toString();
		// String item = "" ;

		try {

			while (doWhile) {
				// Adp.BeginTransaction();
				query = "select parent_id , lable   FROM catalog WHERE  catalog_id  = " + _parent_id;
				queryManager.executeQuery(query);
				if (queryManager.rows().size() > 0) {
					if (((String) queryManager.getValueAt(0, 0)).length() > 0)
						parent_id_last = Long.parseLong((String) queryManager.getValueAt(0, 0));
					lable = (String) queryManager.getValueAt(0, 1);
					doWhile = true;
					// item = "<a href=\"ProductPostCre.jsp?parent_id=" + _parent_id +"\" >"+"/" +
					// lable + "</a>" ;
					rowItem = new StringBuffer();
					rowItem.append("<" + name + "-item>");
					rowItem.append("<selected>" + selected_cd + "</selected>");
					rowItem.append("<item>" + lable + "</item>");
					_parent_id = _parent_id == 0 ? -2 : _parent_id;
					rowItem.append("<code>" + _parent_id + "</code>");
					rowItem.append("<url>" + pagejsp + "=" + _parent_id + "</url>");
					rowItem.append("</" + name + "-item>\n");
					path.insert(0, rowItem);
					_parent_id = parent_id_last;
				} else
					doWhile = false;
				// Adp.commit();
			}

			rowItem = new StringBuffer();
			rowItem.append("<" + name + "-item>");
			rowItem.append("<selected>" + selected_cd + "</selected>");
			rowItem.append("<item>" + lable_last + "</item>");
			parent_id_last = parent_id_last == 0 ? -2 : parent_id_last;
			rowItem.append("<code>" + parent_id_last + "</code>");
			rowItem.append("<url>" + pagejsp + "=" + parent_id_last + "</url>");
			rowItem.append("</" + name + "-item>\n");
			path.insert(0, rowItem);

		} catch (SQLException ex) {
			// Adp.rollback();
			log.error(query, ex);
		} catch (Exception ex) {
			// Adp.rollback();
			log.error(ex);
		} finally {
			queryManager.close();
		}

		path.insert(0, "<" + name + ">\n");
		path.append("</" + name + ">\n");
		return path.toString();
	}

	/**
	 * Output path (catalog) for navigation by ctalog warrning parent 0 replace at
	 * -2
	 * 
	 * @param pagejsp
	 * @param name
	 * @param selected_cd
	 * @return
	 */

	public String getCatalogXMLUrlPath(String pagejsp, String name, String selected_cd, String catalog_id,
			AuthorizationPageBean authorizationPageBeanId) {
		queryManager = new QueryManager();
		String query = "";
		long _parent_id = 0;
		long parent_id_last = 0;
		String lable = "";
		String lable_last = authorizationPageBeanId.getLocalization(applicationContext)
				.getString("section_of_list_catalog");
		boolean doWhile = true;
		StringBuffer path = new StringBuffer();

		StringBuffer rowItem = new StringBuffer();
		if (catalog_id.compareTo("-2") != 0) {
			rowItem.append("<" + name + ">\n");
			rowItem.append("<" + name + "-item>");
			// rowItem.append("<selected>" + selected_cd + "</selected>");
			rowItem.append("<selected>-2</selected>");
			rowItem.append("<item>" + lable_last + "</item>");
			rowItem.append("<code>" + -2 + "</code>");
			rowItem.append("<url>" + pagejsp + "=" + -2 + "</url>");
			rowItem.append("</" + name + "-item>\n");
			rowItem.append("</" + name + ">\n");
		} else {
			rowItem = new StringBuffer();
			rowItem.append("<" + name + ">\n");
			rowItem.append("</" + name + ">\n");
		}

		if (authorizationPageBeanId.getCatalog_id().length() > 0)
			_parent_id = Long.parseLong(selected_cd);
		if (_parent_id == 0)
			return rowItem.toString();
		// String item = "" ;

		try {

			while (doWhile) {
				// Adp.BeginTransaction();
				query = "select parent_id , lable   FROM catalog WHERE  catalog_id  = " + _parent_id;
				queryManager.executeQuery(query);
				if (queryManager.rows().size() > 0) {
					if (((String) queryManager.getValueAt(0, 0)).length() > 0)
						parent_id_last = Long.parseLong((String) queryManager.getValueAt(0, 0));
					lable = (String) queryManager.getValueAt(0, 1);
					doWhile = true;
					// item = "<a href=\"ProductPostCre.jsp?parent_id=" + _parent_id +"\" >"+"/" +
					// lable + "</a>" ;
					rowItem = new StringBuffer();
					rowItem.append("<" + name + "-item>");
					rowItem.append("<selected>" + selected_cd + "</selected>");
					rowItem.append("<item>" + lable + "</item>");
					_parent_id = _parent_id == 0 ? -2 : _parent_id;
					rowItem.append("<code>" + _parent_id + "</code>");
					rowItem.append("<url>" + pagejsp + "=" + _parent_id + "</url>");
					rowItem.append("</" + name + "-item>\n");
					path.insert(0, rowItem);
					_parent_id = parent_id_last;
				} else
					doWhile = false;
				// Adp.commit();
			}

			rowItem = new StringBuffer();
			rowItem.append("<" + name + "-item>");
			rowItem.append("<selected>" + selected_cd + "</selected>");
			rowItem.append("<item>" + lable_last + "</item>");
			parent_id_last = parent_id_last == 0 ? -2 : parent_id_last;
			rowItem.append("<code>" + parent_id_last + "</code>");
			rowItem.append("<url>" + pagejsp + "=" + parent_id_last + "</url>");
			rowItem.append("</" + name + "-item>\n");
			path.insert(0, rowItem);

		} catch (SQLException ex) {
			// Adp.rollback();
			log.error(query, ex);
		} catch (Exception ex) {
			// Adp.rollback();
			log.error(ex);
		} finally {
			queryManager.close();
		}

		path.insert(0, "<" + name + ">\n");
		path.append("</" + name + ">\n");
		return path.toString();
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	public int stringToInt(String s) {
		int i;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			i = 0;
		}
		return i;
	}

	public boolean setSelectedDemand() {
		QueryManager Adp = new QueryManager();
		// String query = "update tdemand set selected=true where id=" + demand
		// ;
		// Adp.executeUpdate(query);
		Adp.close();
		return true;
	}

	public boolean setPassiveDemand() {
		QueryManager Adp = new QueryManager();
		// String query = "update tdemand set active=false where id=" + demand ;
		// Adp.executeUpdate(query);
		Adp.close();
		return true;
	}

//	public void setIntLevelUp(int intLevelUp) {
//		this.intLevelUp = intLevelUp;
//	}
//
//	public int getIntLevelUp() {
//		return intLevelUp;
//	}

	public String getCururl() {
		return cururl;
	}

	public void setCururl(String cururl) {
		this.cururl = cururl;
	}

//	public String getCatalog_id() {
//		return catalog_id;
//	}
//
//	public void setCatalog_id(String catalog_id) {
//		this.catalog_id = catalog_id;
//	}

//	public String getSite_id() {
//		return site_id;
//	}
//
//	public void setSite_id(String site_id) {
//		this.site_id = site_id;
//	}

	public String getRow_id() {
		return row_id;
	}

	public void setRow_id(String row_id) {
		this.row_id = row_id;
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

//	public String getParent_id() {
//		return parent_id;
//	}
//
//	public void setParent_id(String parent_id) {
//		this.parent_id = parent_id;
//	}

	transient ServletContext applicationContext = null;

	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return applicationContext;
	}

	public void setServletContext(ServletContext applicationContext) {
		this.applicationContext = applicationContext;

	}

}
