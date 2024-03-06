package com.cbsinc.cms;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cbsinc.cms.faceds.RedisUtils;

/**
 * Filter class
 * 
 * @web.filter name="FilesFiterController" display-name="Name for FilesFiterController"
 *             description="Description for files if those exist in local web module cache directory "
 * @web.filter-mapping url-pattern="*files/*"
 * 
 */

public class FilesCache implements Filter {

	static private Logger log = Logger.getLogger(FilesCache.class);

	public FilesCache() {

	}

	public void init(FilterConfig filterConfig) {

		checkFilesDir() ;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
		String fileName = "" ; 
		try {
			HttpSession hsession = ((HttpServletRequest) request).getSession(false);
			if (hsession == null)
				hsession = ((HttpServletRequest) request).getSession(true);

			// ++++++++++++++++++++++ start +++++++++++++++++++++++++++++++++++
			String path = ((HttpServletRequest) request).getRequestURI();
			int index = path.lastIndexOf("/") + 1;
			fileName = path.substring(index);
			checkFileInCacheRepo(fileName );
		}
		catch (Exception e) {
			log.error(e.getMessage(),e) ;
		}
	}


	void checkFilesDir() {
		try {
			String pageStorePath = this.getClass().getResource("").getPath();
			pageStorePath = pageStorePath.substring(1, pageStorePath.indexOf("/WEB-INF/"));

			File file = new File(pageStorePath + File.separatorChar + "files"  );
			if (!file.exists()) {
				file.mkdirs();
			}


		} catch (Exception e) {
			log.error(e);
		}
	}
	
	void checkFileInCacheRepo(String fileName ) {
		try {
			String pageStorePath = this.getClass().getResource("").getPath();
			pageStorePath = pageStorePath.substring(1, pageStorePath.indexOf("/WEB-INF/"));
			File file = new File(pageStorePath + File.separatorChar + "files" +  File.separatorChar + fileName );
			if (!file.exists()) {
				byte[] key = RedisUtils.getInstance().getKey(file.getName() );
				RedisUtils.getInstance().writeFileInFS(file.getAbsoluteFile().getPath(), key);
			}

		} catch (Exception e) {
			log.error(e);
		}
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}