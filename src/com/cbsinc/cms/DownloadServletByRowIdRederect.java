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
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Class
 * 
 * @web.servlet name="downloadservletbyrowid" display-name="Name for
 *              DownloadServletByRowId" description="Description for
 *              DownloadServletByRowId"
 * @web.servlet-mapping url-pattern="/downloadservletbyrowid"
 * @web.servlet-init-param name="A parameter" value="A value"
 */
public class DownloadServletByRowIdRederect extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private Logger log = Logger.getLogger(DownloadServletByRowId.class);

	String strDevice = "";

	// OperationAmountBean OperationAmountBeanId = null ;
	AuthorizationPageBean AuthorizationPageBeanId = null;

	String file_id = "1"; // "unknown.zip" ;

	String soft_id = "1"; // "unknown.zip" ;

	int row = 0;

	// LargeObject obj = null ;
	// LargeObjectManager lobj = null ;
	// int oid = 0 ;

	String Qtable = "";

	Vector rows = new Vector();

	String[] columnNames = {};

	// Class[] columnTpyes = {};

	/////// ProductlistBean ProductlistBeanId = null;

	String type_page = "";

	// private static final String CONTENT_TYPE = "text/html;
	// charset=windows-1251";
	// private static final String CONTENT_TYPE = "application/x-zip-compressed;
	// ";
	// private static final String CONTENT_TYPE = "application/zip";
	// application/zip
	// application/x-zip-compressed
	// Initialize global variables

	transient ResourceBundle localization = null;
	String basePath = "";

	public void init() throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		if (localization == null)
			localization = PropertyResourceBundle.getBundle("localization", response.getLocale());
		else if (!localization.getLocale().equals(response.getLocale()))
			localization = PropertyResourceBundle.getBundle("localization", response.getLocale());

		if (request.getParameter("productid") != null)
			soft_id = request.getParameter("productid");
		AuthorizationPageBeanId = (AuthorizationPageBean) request.getSession().getAttribute("authorizationPageBeanId");

		if (strDevice != null) {
			if (soft_id == null || soft_id.length() == 0) {
				AuthorizationPageBeanId.setStrMessage("We are requied autorization on site for download files .");
				response.sendRedirect("Policy.jsp");
				return;
			}
		}

		// FileDownload fileDownload = setFileNameByFile_ID(file_id);
		FileDownload fileDownload = setFileNameByProductId(soft_id);
		response.setHeader("Content-disposition", "attachment;filename=" + fileDownload.getName());
		if (fileDownload.getPath() != null && fileDownload.getPath().length() > 0) {

			String path = request.getContextPath();
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
					+ "/";
			String file_ext = fileDownload.getName().substring(fileDownload.getName().lastIndexOf("."));
			String fileurl = basePath + "//files//" + fileDownload.getFile_id() + file_ext;
			// ((HttpServletRequest) request).getRequestDispatcher(fileurl).forward(
			// request, response) ;
			response.sendRedirect(fileurl);
			AuthorizationPageBeanId.setStrMessage(
					fileDownload.getName() + " " + localization.getString("download_servlet_by_order.has_downloaded"));
			return;
		}

	}

	// Clean up resources
	public void destroy() {

	}

	// protected void finalize() throws Throwable {
	// close();
	// super.finalize();
	// }

	/**
	 * Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "Short description";
	}

	public FileDownload setFileNameByFile_ID(String File_ID) {
		FileDownload fileDownload = new FileDownload();
		fileDownload.setFile_id(File_ID);
		QueryManager Adp = new QueryManager();
		String query = "select name , path  from file  where  file_id  = " + File_ID;
		try {
			Adp.executeQuery(query);

			if (Adp.rows().size() != 0) {
				fileDownload.setName(Adp.getValueAt(0, 0));
				fileDownload.setPath(Adp.getValueAt(0, 1));
			}

		} catch (SQLException ex) {

			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			Adp.close();
		}

		return fileDownload;
	}

	public FileDownload setFileNameByProductId(String productId) {
		FileDownload fileDownload = new FileDownload();
		QueryManager Adp = new QueryManager();
		String query = "select file.name , file.path , file.file_id from soft LEFT  JOIN file  ON  soft.file_id = file.file_id   where  soft.soft_id  = "
				+ productId;
		try {
			Adp.executeQuery(query);

			if (Adp.rows().size() != 0) {
				fileDownload.setName(Adp.getValueAt(0, 0));
				fileDownload.setPath(Adp.getValueAt(0, 1));
				fileDownload.setFile_id(Adp.getValueAt(0, 2));
			}

		} catch (SQLException ex) {

			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			Adp.close();
		}

		return fileDownload;
	}

	public void setPassiveRow(String soft_id) {
		QueryManager Adp = new QueryManager();
		String query = "UPDATE soft SET active = true  WHERE soft_id = " + soft_id;
		// select 0 AS test ;
		try {
			Adp.executeUpdate(query);
		} catch (SQLException ex) {

			log.error(query, ex);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			Adp.close();
		}

		return;
	}

}
