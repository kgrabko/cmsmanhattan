package com.cbsinc.cms.services.statistics;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;


/**
 * @web.servlet name="gennumberservlet" display-name="Generator Image with code"  description="Description for generator of Image with code"
 * @web.servlet-mapping url-pattern="/gennumberservlet"
 */

public class GenNumberServlet extends HttpServlet 
{
  
	GenNumberProducer genNumberProducer = null ;
  
	public GenNumberServlet() 
	{
		genNumberProducer = new GenNumberProducer() ;
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)   throws IOException, ServletException
    {
      try
      {
       
    	 OutputStream stream =  response.getOutputStream() ; 
    	 String type = genNumberProducer.createImage(stream);
    	 genNumberProducer.count_char = 1 ;
    	 request.getSession().setAttribute("gen_number", genNumberProducer.code ) ;
    	 response.setContentType(type); 
    	 stream.close();
    	 response.flushBuffer() ;
      }
      catch (Exception e)
      {
       throw new ServletException(e);
      }
    }

}
