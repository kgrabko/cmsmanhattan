package com.cbsinc.cms.faceds;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map ;
import java.util.concurrent.ConcurrentHashMap ;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.cbsinc.cms.annotations.utils.PropertiesReadAndWriteSingletonBean;

public class CmsBeansFactoty {

	private static CmsBeansFactoty cmsBeansFactoty = new CmsBeansFactoty();
	private static final String propertiesFileName = "beans";
	private static OutputStream out = null;
	private static BufferedWriter bufferedWriter = null;
	private Map classes = new ConcurrentHashMap();
	private Lock lock = new ReentrantLock();
	

	private CmsBeansFactoty() {
	};

	public static CmsBeansFactoty getInstance() {
		return cmsBeansFactoty;
	}

	public Object getBean( String Id ) throws IOException, InterruptedException
	{
		Object obj = null ;
		
		try {
			if(lock.tryLock(50L, TimeUnit.MILLISECONDS)){
				obj = classes.get(Id);
				if( obj != null ) return obj ;
				else 
				{
				 Object obj2 =	PropertiesReadAndWriteSingletonBean.loadProps().getProperty(Id) ;
				 classes.put(Id,obj2);
				 obj = obj2 ;
				}
			}
		}
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
		finally{
			//release lock
			lock.unlock();
		}
		
		return obj;
	}
	
}
