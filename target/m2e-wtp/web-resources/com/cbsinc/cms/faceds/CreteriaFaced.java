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

import com.cbsinc.cms.QueryManager;


public class CreteriaFaced  implements java.io.Serializable {
	

	private static final long serialVersionUID = -22514501660649295L;
	transient static private Logger log = Logger.getLogger(CreteriaFaced.class);
	
	public void addCatalog(String site_id , String table_name , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(" + table_name + "_id ) + 1  as ID FROM "+table_name+"";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into " + table_name + " (" + table_name + "_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put(table_name + "_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}

	

	public void editCatalog(String table_name , String creteria_id , String name , String link_id  ) {

		
		 
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";


		query = "update " + table_name + " set  name = ? , link_id = ?  where "+table_name.trim()+"_id = " + creteria_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}

	
	public void editCreteriaLable1( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria1 set  label = ?  where CATALOG_ID = " + site_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	public void editCreteriaLable2( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria2 set  label = ?  where CATALOG_ID = " + site_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteriaLable3( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria3 set  label = ?  where CATALOG_ID = " + site_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteriaLable4( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria4 set  label = ?  where CATALOG_ID = " + site_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteriaLable5( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria5 set  label = ? where CATALOG_ID = " + site_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	
	public void editCreteriaLable6( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria6 set  label = ? where CATALOG_ID = " + site_id ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteriaLable7( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria7 set  label = ? where CATALOG_ID = " + site_id ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	
	public void editCreteriaLable8( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria8 set  label = ? where CATALOG_ID = " + site_id ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteriaLable9( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria9 set  label = ? where CATALOG_ID = " + site_id ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteriaLable10( String site_id , String label  ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria10 set  label = ?  where CATALOG_ID = " + site_id ;

		try 
		{
			HashMap args = new HashMap();
			args.put("label" , label  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	public void editCreteria1( String creteria1_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria1 set  name = ? , link_id = ?   where creteria1_id = " + creteria1_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteria2( String creteria2_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria2 set  name = ? , link_id = ?   where creteria2_id = " + creteria2_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteria3( String creteria3_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria3 set  name = ? , link_id = ?   where creteria3_id = " + creteria3_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteria4( String creteria4_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria4 set  name = ? , link_id = ?   where creteria4_id = " + creteria4_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteria5( String creteria5_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria5 set  name = ? , link_id = ?   where creteria5_id = " + creteria5_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteria6( String creteria6_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria6 set  name = ? , link_id = ?   where creteria6_id = " + creteria6_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	
	public void editCreteria7( String creteria7_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria7 set  name = ? , link_id = ?   where creteria7_id = " + creteria7_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteria8(String creteria8_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria8 set  name = ? , link_id = ?   where creteria8_id = " + creteria8_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteria9( String creteria9_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria9 set  name = ? , link_id = ?   where creteria9_id = " + creteria9_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	public void editCreteria10(String creteria10_id , String name , String link_id   ) {

		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";

		query = "update creteria10 set  name = ? , link_id = ?   where creteria10_id = " + creteria10_id   ;

		try 
		{
			HashMap args = new HashMap();
			args.put("name" , name  );
			args.put("link_id" , Long.valueOf(link_id)  );
			Adp.executeUpdateWithArgs(query, args);
			Adp.commit();
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback();
		}
		catch (Exception ex) 
		{
			log.error(ex);
			Adp.rollback();
		}
		finally
		{
			Adp.close();			
		}

		return;
	}
	
	
	
	
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
	
	
	
	
	
	
	
	
	public String getOneLabel(String query) {
		String name = "";
		
		QueryManager Adp = new QueryManager();
		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0)	name = (String) Adp.getValueAt(0, 0);
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
		Adp.close();
		}
		return name ;
	}
	

	public void deleteCreteria1(String creteria1Id) 
	{
		if(creteria1Id.startsWith("-") || creteria1Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria1 WHERE  creteria1_ID = " + creteria1Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}

	}


	public void deleteCreteria2(String creteria2Id) 
	{
		if(creteria2Id.startsWith("-") || creteria2Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria2 WHERE  creteria2_ID = " + creteria2Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}

	}
	
	
	public void deleteCreteria3(String creteria3Id) 
	{
		if(creteria3Id.startsWith("-") || creteria3Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria3 WHERE  creteria3_ID = " + creteria3Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}
	}
	
	public void deleteCreteria4(String creteria4Id) 
	{
		if(creteria4Id.startsWith("-") || creteria4Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria4 WHERE  creteria4_ID = " + creteria4Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}
	}
	
	public void deleteCreteria5(String creteria5Id) 
	{
		if(creteria5Id.startsWith("-") || creteria5Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria5 WHERE  creteria5_ID = " + creteria5Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}
	}
	
	public void deleteCreteria6(String creteria6Id) 
	{
		if(creteria6Id.startsWith("-") || creteria6Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria6 WHERE  creteria6_ID = " + creteria6Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}
	}
	
	public void deleteCreteria7(String creteria7Id) 
	{
		if(creteria7Id.startsWith("-") || creteria7Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria7 WHERE  creteria7_ID = " + creteria7Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}
	}


	public void deleteCreteria8(String creteria8Id) 
	{
		if(creteria8Id.startsWith("-") || creteria8Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria8 WHERE  creteria8_ID = " + creteria8Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}
	}

	
	public void deleteCreteria9(String creteria9Id) 
	{
		if(creteria9Id.startsWith("-") || creteria9Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria9 WHERE  creteria9_ID = " + creteria9Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}
	}
	
	
	public void deleteCreteria10(String creteria10Id) 
	{
		if(creteria10Id.startsWith("-") || creteria10Id.equals("0") ) return ;
		QueryManager Adp = new QueryManager();
		String query = "";
		query = "delete FROM creteria10 WHERE  creteria10_ID = " + creteria10Id;
		try 
		{
			Adp.executeUpdate(query);
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
			Adp.close();
		}
	}
	
	public void addCreteria1(String site_id ,  String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria1_ID ) + 1  as ID FROM creteria1";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria1 (creteria1_ID , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria1_ID" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}



	public void addCreteria2(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria2_id ) + 1  as ID FROM creteria2";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria2 (creteria2_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria2_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}


	public void addCreteria3(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX( creteria3_id ) + 1  as ID FROM creteria3";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria3 (creteria3_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria3_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}

	public void addCreteria4(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX( creteria4_id ) + 1  as ID FROM creteria4";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria4 ( creteria4_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria4_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}
	
	
	public void addCreteria5(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX( creteria5_id ) + 1  as ID FROM creteria5";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria5 (creteria5_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria5_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}
	
	
	public void addCreteria6(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX( creteria6_id ) + 1  as ID FROM creteria6";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria6 (creteria6_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria6_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}


	public void addCreteria7(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria7_id ) + 1  as ID FROM creteria7";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria7 (creteria7_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria7_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}


	public void addCreteria8(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria8_id ) + 1  as ID FROM creteria8";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria8 (creteria8_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria8_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}


	
	public void addCreteria9(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria9_id ) + 1  as ID FROM creteria9";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria9 (creteria9_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria9_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}
	
	
	public void addCreteria10(String site_id , String link_id , String name ,  String label  ) 
	{
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		String query = "";
		String creteria_id = "" ;
		//query = "SELECT NEXT VALUE FOR " + table_name + "_" + table_name + "_id_seq  AS ID  FROM ONE_SEQUENCES";
		query = "SELECT MAX(creteria10_id ) + 1  as ID FROM creteria10";
		
		try 
		{

		Adp.executeQuery(query);
		
		creteria_id = Adp.getValueAt(0, 0);

		query = "insert into creteria10 (creteria10_id , catalog_id , link_id , name , label , active ) "
				+ " values ( ? , ? , ? , ? , ? , ? ) " ;
		 	
			Map args = new HashMap();
			args.put("creteria10_id" , creteria_id  );
			args.put("catalog_id" ,  site_id  );
			args.put("link_id" ,  link_id  );
			args.put("name" ,  name );
			args.put("label" ,  label );
			args.put("active" ,  true );
			Adp.executeInsertWithArgs(query, args);
			Adp.commit();
		
		} 
		catch (SQLException ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}
		catch (Exception ex) 
		{
			log.error(query,ex);
			Adp.rollback() ;
		}

		finally
        {
    		Adp.close();        	
        }  

		return;
	}
}
