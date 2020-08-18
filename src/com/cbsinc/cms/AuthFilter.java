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

package com.cbsinc.cms;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Filter class
 * 
 * @web.filter name="Auth" display-name="Name for Auth" description="Description
 *             for Auth"
 * @web.filter-mapping url-pattern="/catalog_list.jsp"
 * @web.filter-mapping url-pattern="/catalog_add.jsp"
 * @web.filter-mapping url-pattern="/catalog_edit.jsp"
 * @web.filter-mapping url-pattern="/CreateShop.jsp"
 * @web.filter-mapping url-pattern="/CMusicPost.jsp"
 * @web.filter-mapping url-pattern="/ProductPost.jsp"
 * @web.filter-mapping url-pattern="/PayGatewaySetup.jsp"
 * @web.filter-mapping url-pattern="/SelectFile.jsp"
 * @web.filter-mapping url-pattern="/SelectBigImage.jsp"
 * @web.filter-mapping url-pattern="/SelectImage.jsp"
 * @web.filter-mapping url-pattern="/PayGatewayList.jsp"
 * @web.filter-mapping url-pattern="/uploadservlet"
 * @web.filter-mapping url-pattern="/bigimageservletupload"
 * @web.filter-mapping url-pattern="/downloadservlet"
 * @web.filter-mapping url-pattern="/downloadservletbyodrder"
 * @web.filter-mapping url-pattern="/imageservlet"
 * @web.filter-mapping url-pattern="/imageservletupload"
 * @web.filter-mapping url-pattern="/midletservletupload"
 * @web.filter-mapping url-pattern="/uploadservletxsl"
 * @web.filter-mapping url-pattern="/fileservletupload"
 * 
 */

public class AuthFilter implements Filter {
	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	private FilterConfig filterConfig;

	static private Logger log = Logger.getLogger(AuthFilter.class);

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
		try {

			HttpSession hsession = ((HttpServletRequest) request).getSession(false);
			if (hsession != null) {
				AuthorizationPageBean authorizationPageBeanId;
				if (hsession.getAttribute("authorizationPageBeanId") instanceof AuthorizationPageBean) {
					authorizationPageBeanId = ((AuthorizationPageBean) hsession
							.getAttribute("authorizationPageBeanId"));
					if (authorizationPageBeanId.getStrLogin().length() == 0) {
						((HttpServletResponse) response).sendRedirect("index.jsp");
						return;
					}

				} else {
					((HttpServletResponse) response).sendRedirect("index.jsp");
					return;
				}

			} else {
				((HttpServletResponse) response).sendRedirect("index.jsp");
				return;
			}

			filterChain.doFilter(request, response);
		} catch (ServletException sx) {
			log.error(sx);
			filterConfig.getServletContext().log(sx.getMessage());
		} catch (IOException iox) {
			log.error(iox);
			filterConfig.getServletContext().log(iox.getMessage());
		}
	}

	public void destroy() {
	}
}