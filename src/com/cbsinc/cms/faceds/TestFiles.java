package com.cbsinc.cms.faceds;
/**
 * <p>
 * Title: Content Manager System
 * </p>
 * <p>
 * Description: System building web application develop by Konstantin Grabko.
 * Konstantin Grabko is Owner and author this code.
 * You can not use it and you cannot change without written permission from Konstantin Grabko
 * Email: konstantin.grabko@yahoo.com or konstantin.grabko@gmail.com
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: CENTER BUSINESS SOLUTIONS INC 
 * </p>
 * 
 * @author Konstantin Grabko
 * @version 1.0
 */
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

public class TestFiles  implements Serializable
{


	/**
	 * 
	 */
	transient private static final long serialVersionUID = 4824974619873268186L;
	public static FilenameFilter filenameFilter	= new FilenameFilter() 
    {
        public boolean accept(File dir, String name) 
        {
            return name.endsWith(".txt") || name.endsWith(".htm")  || dir.isDirectory() ;
        }
    };

		public static File[] listFilesAsArray(	File directory,	FilenameFilter filter,	boolean recurse)
		{
			Collection files = listFiles(directory,	filter, recurse);
			File[] arr = new File[files.size()];
			return (File[])files.toArray(arr);
		}

		public static Collection listFiles(File directory,FilenameFilter filter,boolean recurse)
		{
			Vector files = new Vector();
			File[] entries = directory.listFiles(filter);
			//File[] entries = directory.listFiles();
			for (File entry : entries)
			{

/*
				if (filter == null || filter.accept(directory, entry.getName()))
				{
					files.add(entry);
					System.out.println(entry.getAbsolutePath()) ;
				}
*/
				if (filter != null && filter.accept(directory, entry.getName()))
				{
					files.add(entry);
					System.out.println(entry.getAbsolutePath()) ;
				}

				
				if (recurse && entry.isDirectory())
				{
					files.addAll(listFiles(entry, filter, recurse));
				}
			}
			return files;		
		}

		public static void main(String[] args) 
		{
			//listFilesAsArray( new File("c://") , filenameFilter , true ) ;
			listFiles( new File("c://") , filenameFilter , true ) ;
		}
		
	}
	
	

