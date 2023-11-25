package com.cbsinc.cms.faceds;
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
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: CENTER BUSINESS SOLUTIONS INC 
 * </p>
 * 
 * @author Konstantin Grabko
 * @version 1.0
 */
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.annotations.SingletonBean;


public class CreteriaFaced   {
	

	 final private static Logger log = Logger.getLogger(CreteriaFaced.class);
	 final private static  String CLASS_NAME = "com.cbsinc.cms.faceds.CreteriaFaced" ;
	
	
	 @Profiled(logger = CLASS_NAME , tag = "addCatalog" , message = "siteId: {$0}  , tableName: {@1} , linkId: {@2}, name: {@3}, label: {@4} "  )
	 public void addCatalog(String siteId , String tableName , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(" + tableName + "_id ) + 1  as ID FROM "+tableName+"";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into " + tableName + " (" + tableName + "_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put(tableName + "_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}

	

	 @Profiled(logger = CLASS_NAME , tag = "editCatalog" , message = "tableName: {$0}  , creteriaId: {@1} , name: {@2} , linkId: {@3} "  )
	 public void editCatalog(String tableName , String creteriaId , String name , String linkId  ) {
		 
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";


		query = "update " + tableName + " set  name = ? , link_id = ?  where "+tableName.trim()+"_id = " + creteriaId   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}

	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable1" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable1( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";

		query = "update creteria1 set  label = ?  where CATALOG_ID = " + siteId   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable2" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable2( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";

		query = "update creteria2 set  label = ?  where CATALOG_ID = " + siteId   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable3" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable3( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query =  "update creteria3 set  label = ?  where CATALOG_ID = " + siteId   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable4" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable4( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query =  "update creteria4 set  label = ?  where CATALOG_ID = " + siteId   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable5" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable5( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "update creteria5 set  label = ? where CATALOG_ID = " + siteId   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable6" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable6( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";

		query = "update creteria6 set  label = ? where CATALOG_ID = " + siteId ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable7" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable7( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";

		query = "update creteria7 set  label = ? where CATALOG_ID = " + siteId ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable8" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable8( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query =  "update creteria8 set  label = ? where CATALOG_ID = " + siteId ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable9" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable9( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query =  "update creteria9 set  label = ? where CATALOG_ID = " + siteId ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteriaLable10" , message = "siteId: {$0}  , label: {@1}"  )
	 public void editCreteriaLable10( String siteId , String label  ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";

		query = "update creteria10 set  label = ?  where CATALOG_ID = " + siteId ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteria1" , message = "creteria1Id: {$0}  , name: {@1} , , linkId: {@2}"  )
	 public void editCreteria1( String creteria1Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";

		query = "update creteria1 set  name = ? , link_id = ?   where creteria1_id = " + creteria1Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteria2" , message = "creteria2Id: {$0}  , name: {@1} , linkId: {@2}"  )
	 public void editCreteria2( String creteria2Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query =  "update creteria2 set  name = ? , link_id = ?   where creteria2_id = " + creteria2Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteria3" , message = "creteria3Id: {$0}  , name: {@1} , linkId: {@2} "  )
	 public void editCreteria3( String creteria3Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";

		query = "update creteria3 set  name = ? , link_id = ?   where creteria3_id = " + creteria3Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 @Profiled(logger = CLASS_NAME , tag = "editCreteria4" , message = "creteria4Id: {$0}  , name: {@1} , linkId: {@1} "  )
	 public void editCreteria4( String creteria4Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query  = "update creteria4 set  name = ? , link_id = ?   where creteria4_id = " + creteria4Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void editCreteria5( String creteria5Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "update creteria5 set  name = ? , link_id = ?   where creteria5_id = " + creteria5Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void editCreteria6( String creteria6Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query =  "update creteria6 set  name = ? , link_id = ?   where creteria6_id = " + creteria6Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void editCreteria7( String creteria7Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query  = "update creteria7 set  name = ? , link_id = ?   where creteria7_id = " + creteria7Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	// @Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void editCreteria8(String creteria8Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query  = "update creteria8 set  name = ? , link_id = ?   where creteria8_id = " + creteria8Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void editCreteria9( String creteria9Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query =  "update creteria9 set  name = ? , link_id = ?   where creteria9_id = " + creteria9Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void editCreteria10(String creteria10Id , String name , String linkId   ) {

		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "update creteria10 set  name = ? , link_id = ?   where creteria10_id = " + creteria10Id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(linkId)  );
			qm.executeUpdateWithArgs(query, args);
			qm.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			qm.rollback();
		}
		finally
		{
			qm.close();			
		}

		return;
	}
	
	
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public String getPartCriteria(String _criteriaId , boolean  isSpace )
	{
		
		try
		{
		if( Long.parseLong(_criteriaId)  < 0 )   _criteriaId = "0" ;
		}
		catch (Exception ex) 
		{
			log.error(ex);
		}
		
		return !isSpace?"":" and catalog_id = " + _criteriaId ;	
	}
	
	
	
	
	
	
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public String getOneLabel(String query) {
		String name = "";
		
		QueryManager qm = new QueryManager();
		try 
		{
			qm.executeQuery(query);
			if (qm.rows().size() != 0)	name = (String) qm.getValueAt(0, 0);
		}
		catch (SQLException ex) {
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			log.error(ex);
		}
		finally
		{
		qm.close();
		}
		return name ;
	}
	

	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria1(String creteria1Id) 
	{
		if(creteria1Id.startsWith("-") || creteria1Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query =  "delete FROM creteria1 WHERE  creteria1_ID = " + creteria1Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}

	}


	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria2(String creteria2Id) 
	{
		if(creteria2Id.startsWith("-") || creteria2Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query = "";
		query = "delete FROM creteria2 WHERE  creteria2_ID = " + creteria2Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}

	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria3(String creteria3Id) 
	{
		if(creteria3Id.startsWith("-") || creteria3Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query = "";
		query = "delete FROM creteria3 WHERE  creteria3_ID = " + creteria3Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}
	}
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria4(String creteria4Id) 
	{
		if(creteria4Id.startsWith("-") || creteria4Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query = "";
		query = "delete FROM creteria4 WHERE  creteria4_ID = " + creteria4Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}
	}
	
	// @Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria5(String creteria5Id) 
	{
		if(creteria5Id.startsWith("-") || creteria5Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query = "";
		query = "delete FROM creteria5 WHERE  creteria5_ID = " + creteria5Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}
	}
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria6(String creteria6Id) 
	{
		if(creteria6Id.startsWith("-") || creteria6Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query = "";
		query = "delete FROM creteria6 WHERE  creteria6_ID = " + creteria6Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}
	}
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria7(String creteria7Id) 
	{
		if(creteria7Id.startsWith("-") || creteria7Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query = "";
		query = "delete FROM creteria7 WHERE  creteria7_ID = " + creteria7Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}
	}


	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria8(String creteria8Id) 
	{
		if(creteria8Id.startsWith("-") || creteria8Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query =  "delete FROM creteria8 WHERE  creteria8_ID = " + creteria8Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}
	}

	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria9(String creteria9Id) 
	{
		if(creteria9Id.startsWith("-") || creteria9Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query =  "delete FROM creteria9 WHERE  creteria9_ID = " + creteria9Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}
	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void deleteCreteria10(String creteria10Id) 
	{
		if(creteria10Id.startsWith("-") || creteria10Id.equals("0") ) return ;
		QueryManager qm = new QueryManager();
		String query =  "delete FROM creteria10 WHERE  creteria10_ID = " + creteria10Id;
		try 
		{
			qm.executeUpdate(query);
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex) ;
		}
		catch (Exception ex) 
		{
			log.error(ex) ;
		}
		finally 
		{
			qm.close();
		}
	}
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria1(String siteId ,  String linkI , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria1_ID ) + 1  as ID FROM creteria1";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria1 (creteria1_ID , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria1_ID" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkI  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}



	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria2(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria2_id ) + 1  as ID FROM creteria2";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria2 (creteria2_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria2_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}


	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria3(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX( creteria3_id ) + 1  as ID FROM creteria3";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria3 (creteria3_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria3_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}

	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria4(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX( creteria4_id ) + 1  as ID FROM creteria4";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria4 ( creteria4_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria4_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria5(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX( creteria5_id ) + 1  as ID FROM creteria5";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria5 (creteria5_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria5_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria6(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX( creteria6_id ) + 1  as ID FROM creteria6";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria6 (creteria6_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria6_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}


	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria7(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria7_id ) + 1  as ID FROM creteria7";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria7 (creteria7_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria7_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}


	 //@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria8(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria8_id ) + 1  as ID FROM creteria8";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria8 (creteria8_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria8_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}


	
	 //@Profiled(logger = CLASS_NAME , tag = "addCreteria9" , message = "httpSession: {$0}  , userId: {@1}"  )
	 public void addCreteria9(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria9_id ) + 1  as ID FROM creteria9";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria9 (creteria9_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria9_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}
	
	
	 //@Profiled(logger = CLASS_NAME , tag = "addCreteria10" , message = "siteId: {$0}  , linkId: {@1} , name: {@1} , label: {@1} "  )
	 public void addCreteria10(String siteId , String linkId , String name ,  String label  ) 
	{
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria10_id ) + 1  as ID FROM creteria10";
		
		try 
		{

		qm.executeQuery(query);
		
		creteria_id = qm.getValueAt(0, 0);

		query = "insert into creteria10 (creteria10_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria10_id" , creteria_id  );
			args.put("catalog_id" ,  siteId  );
			args.put("link_id" ,  linkId  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			qm.executeInsertWithArgs(query, args);
			qm.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			qm.rollback() ;
		}

		finally
        {
    		qm.close();        	
        }  

		return;
	}
}
