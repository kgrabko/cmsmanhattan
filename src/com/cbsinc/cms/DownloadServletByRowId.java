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
public class DownloadServletByRowId extends HttpServlet {
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

	String filename = "unknown.zip";

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
			localization = PropertyResourceBundle.getBundle("localization", request.getLocale());
		else if (!localization.getLocale().getLanguage().equals(request.getLocale().getLanguage()))
			localization = PropertyResourceBundle.getBundle("localization", request.getLocale());

		if (request.getParameter("productid") != null)
			soft_id = request.getParameter("productid");
		AuthorizationPageBeanId = (AuthorizationPageBean) request.getSession().getAttribute("authorizationPageBeanId");

		if (strDevice != null) {
			if (soft_id == null || soft_id.length() == 0) {
				AuthorizationPageBeanId.setStrMessage(localization.getString("user_not_autorization"));
				response.sendRedirect("Policy.jsp");
				return;
			}
		}

		// FileDownload fileDownload = setFileNameByFile_ID(file_id);
		FileDownload fileDownload = setFileNameByProductId(soft_id);
		filename = fileDownload.getName();
		// response.setContentType(CONTENT_TYPE);
		// response.setHeader("Content-Type", "application/zip");
		// response.setHeader("Content-Encoding", "zip");
		// response.setHeader("Content-disposition", "attachment;filename=" +
		// filename);
		String CONTENT_TYPE = "application/octet-stream";
		String ext = "";
		ext = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		ext = ext.toLowerCase();
		if (ext.compareTo("jar") == 0)
			CONTENT_TYPE = "application/java-archive";
		if (ext.compareTo("jad") == 0)
			CONTENT_TYPE = "text/vnd.sun.j2me.app-descriptor";
		if (ext.compareTo("wml") == 0)
			CONTENT_TYPE = "text/vnd.wap.wml";
		if (ext.compareTo("mid") == 0)
			CONTENT_TYPE = "audio/x-midi";
		if (ext.compareTo("midi") == 0)
			CONTENT_TYPE = "audio/x-midi";
		if (ext.compareTo("wbmp") == 0)
			CONTENT_TYPE = "image/vnd.wap.wbmp";
		if (ext.compareTo("wml") == 0)
			CONTENT_TYPE = "text/vnd.wap.wml";
		if (ext.compareTo("wmlc") == 0)
			CONTENT_TYPE = "application/vnd.wap.wmlc";
		if (ext.compareTo("wmlscriptc") == 0)
			CONTENT_TYPE = "application/vnd.wap.wmlscriptc";
		if (ext.compareTo("jpe") == 0)
			CONTENT_TYPE = "image/jpeg";
		if (ext.compareTo("jpeg") == 0)
			CONTENT_TYPE = "image/jpeg";
		if (ext.compareTo("jpg") == 0)
			CONTENT_TYPE = "image/jpeg";
		if (ext.compareTo("gif") == 0)
			CONTENT_TYPE = "image/gif";
		if (ext.compareTo("mov") == 0)
			CONTENT_TYPE = "video/quicktime";
		if (ext.compareTo("movie") == 0)
			CONTENT_TYPE = "video/x-sgi-movie";
		if (ext.compareTo("mp1") == 0)
			CONTENT_TYPE = "audio/x-mpeg";
		if (ext.compareTo("mp2") == 0)
			CONTENT_TYPE = "audio/x-mpeg";
		if (ext.compareTo("mp3") == 0)
			CONTENT_TYPE = "audio/x-mpeg";
		if (ext.compareTo("mp4") == 0)
			CONTENT_TYPE = "audio/x-mpeg";
		if (ext.compareTo("mpa") == 0)
			CONTENT_TYPE = "audio/x-mpeg";
		if (ext.compareTo("mpe") == 0)
			CONTENT_TYPE = "video/mpeg";
		if (ext.compareTo("mpeg") == 0)
			CONTENT_TYPE = "video/mpeg";
		if (ext.compareTo("mpega") == 0)
			CONTENT_TYPE = "audio/x-mpeg";
		if (ext.compareTo("mpg") == 0)
			CONTENT_TYPE = "video/mpeg";
		if (ext.compareTo("mpv2") == 0)
			CONTENT_TYPE = "video/mpeg2";
		if (ext.compareTo("divx") == 0)
			CONTENT_TYPE = "video/mpeg4";

		response.setContentType(CONTENT_TYPE);
		response.setHeader("Content-disposition", "attachment;filename=" + filename);
		java.nio.channels.WritableByteChannel strout = java.nio.channels.Channels
				.newChannel(response.getOutputStream());
		java.io.FileInputStream fInStreem = null;
		java.nio.channels.FileChannel infileChannel = null;
		java.nio.ByteBuffer buff = null;
		if (fileDownload.getPath() != null && fileDownload.getPath().length() > 0) {

			try {
				fInStreem = new java.io.FileInputStream(fileDownload.getPath());
				infileChannel = fInStreem.getChannel();
				buff = java.nio.ByteBuffer.allocate(2048);
				long count = infileChannel.size();
				while (count > 0) {
					// ///////if(limmit < downloadzise) break ;
					count = count - 2048;
					infileChannel.read(buff);
					buff.rewind();
					strout.write(buff);
					buff.rewind();
				}

				/// setPassiveRow(soft_id);
				AuthorizationPageBeanId.setStrMessage(
						filename + " " + localization.getString("download_servlet_by_order.has_downloaded"));
			} catch (Exception e) {
				log.error(e);
			} finally {
				if (infileChannel != null)
					infileChannel.close();
				if (fInStreem != null)
					fInStreem.close();
				if (strout != null)
					strout.close();
				if (buff != null)
					buff.clear();
			}
			return;
		}

		/*
		 * if (ext.compareTo("jad") == 0) { String jad = getBObj(strout, false); //
		 * servletoutputstream = response.getOutputStream(); int sjad =
		 * jad.indexOf("MIDlet-Jar-URL:"); String jad1 = jad.substring(0, sjad +
		 * "MIDlet-Jar-URL:".length()); int ejad = jad.indexOf("MIDlet-Name:"); String
		 * jad2 = jad.substring(ejad); String makejar = filename.substring(0,
		 * filename.length() - 1) + "r"; String midl_jar_url = " midlets/" + makejar +
		 * "\n"; jad = jad1 + midl_jar_url + jad2; java.nio.ByteBuffer buff =
		 * java.nio.ByteBuffer.wrap(jad.getBytes()); strout.write(buff); } else {
		 * getBObj(strout, true);
		 * 
		 * } close(); setPassiveRow(soft_id);
		 * AuthorizationPageBeanId.setStrMessage(filename + " " +
		 * setup_resources.getString("download_servlet_by_order.has_downloaded"));
		 */
		// response.sendRedirect("Order.jsp" );
		// return ;
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
		String path = "";
		try {
			Adp.executeQuery(query);

			if (Adp.rows().size() != 0) {
				fileDownload.setName(Adp.getValueAt(0, 0));
				path = Adp.getValueAt(0, 1);
				if (!path.startsWith("/"))
					path = "/" + path;
				fileDownload.setPath(path);
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
