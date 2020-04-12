package com.cbsinc.cms.services.ajax;

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
 * Company: Grabko Business (Предприниматель Грабко Константин Владимирович)  
 * </p>
 * @author Konstantin Grabko
 * @version 1.0
 */


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.ProductlistBean;
import com.cbsinc.cms.faceds.ProductlistFaced;
import com.cbsinc.cms.faceds.SearchFilesFaced;

/**
 * Servlet Class
 *
 * @web.servlet              name="searchfiles"
 *                           display-name="Name for searchfiles"
 *                           description="Description for searchfiles"
 * @web.servlet-mapping      url-pattern="/searchfiles"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class SearchFilesServlet extends HttpServlet {
	
	ResourceBundle setup_resources = null ;
	ResourceBundle localization = null ;
	SearchFilesThread searchFilesThread  = null ;
	Vector vector = null ;

	public SearchFilesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources", response.getLocale());
		if( localization == null )  localization = PropertyResourceBundle.getBundle("localization", response.getLocale());
		//HttpSession session  = request.getSession(false);
		HttpSession session  = request.getSession();
		
		if( !(session.getAttribute("result") instanceof  Vector) )
		{ 
			response.getWriter().write("<document>") ;
			response.getWriter().write("</document>") ;
			return ;
		}	
		
		response.getWriter().write("<document>") ;

		vector  = (Vector)session.getAttribute("result") ;
		int size =  vector.size() ;
		if(size > 100 ) size  = 100 ; 
		
		for(int i = 0 ; i < size ; i++ )
		{
			String file_tag =  (String)vector.remove(i) ;
			response.getWriter().write(file_tag);
			System.out.print(file_tag) ;
		}

		
		response.getWriter().write("</document>") ;

	}	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources", response.getLocale());
		if( localization == null )  localization = PropertyResourceBundle.getBundle("localization", response.getLocale());
		//HttpSession session  = request.getSession(false);
		HttpSession session  = request.getSession();
		String dir = "" ;
		if( request.getParameter("dir") !=null && request.getParameter("dir").length() > 0  )
		{
			dir = request.getParameter("dir") ;			
		} 
		else
		{ 
			if( !(session.getAttribute("result") instanceof  Vector) )
			{ 
				response.getWriter().write("<document>") ;
				response.getWriter().write("</document>") ;
				return ;
			}	
			
			response.getWriter().write("<document>") ;
 
			vector  = (Vector)session.getAttribute("result") ;
			int size =  vector.size() ;
			if(size > 100 ) size  = 100 ; 
			
			for(int i = 0 ; i < size ; i++ )
			{
				String file_tag =  (String)vector.remove(i) ;
				response.getWriter().write(file_tag);
				System.out.print(file_tag) ;
			}

			
			response.getWriter().write("</document>") ;

			return ;
		}	
			
		
		if( session.getAttribute("searchFilesThread") instanceof  SearchFilesThread )
		{
			searchFilesThread = (SearchFilesThread)session.getAttribute("searchFilesThread") ;
		}
		else 
		{ 
			session.setAttribute("searchFilesThread", new SearchFilesThread()) ;
			searchFilesThread = (SearchFilesThread)session.getAttribute("searchFilesThread") ;
		}
		
		if(!searchFilesThread.isRunning())
		{
			searchFilesThread.listFilesXMLThread( new File(dir) , searchFilesThread.filenameFilter , true , session ) ;
		}
	
	}
	
	
	
	
	
	
}
