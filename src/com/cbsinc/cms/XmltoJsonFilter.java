package com.cbsinc.cms;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class XmltoJsonFilter implements Filter {
 
    public void destroy() {
        // do nothing
    }
 
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if (res instanceof HttpServletResponse) {
            HttpServletResponse response = (HttpServletResponse) res;
            if ("json".equals(req.getParameter("format"))) {
            	XmlToJsonResponseWrapper wrappedResponse = new XmlToJsonResponseWrapper(response);
                chain.doFilter(req, wrappedResponse);
                wrappedResponse.finishResponse();
            } else {
                chain.doFilter(req, res);
            }
        }
    }
 
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }
}