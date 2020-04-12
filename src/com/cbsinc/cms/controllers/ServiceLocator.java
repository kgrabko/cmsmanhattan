package com.cbsinc.cms.controllers;
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
import com.cbsinc.cms.CurrencyHash;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.OrderFaced;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;

public class ServiceLocator {
	
	
		  private static ServiceLocator me;
		  AuthorizationPageFaced authorizationPageFaced = null ;
		  OrderFaced orderFaced = null ;
		  PolicyFaced policyFaced = null ;
		  ProductlistFaced productlistFaced = null ;
		  ProductPostAllFaced productPostAllFaced = null ;
		  //SendMailAgent sendMailAgent = null ;
		    
		  private ServiceLocator() {
		 		      
		      authorizationPageFaced = new AuthorizationPageFaced() ;
		      orderFaced = new OrderFaced() ;
		      policyFaced = new PolicyFaced() ;
		      productlistFaced =  new ProductlistFaced() ;
		      productPostAllFaced = new ProductPostAllFaced() ;
		     // sendMailAgent = new SendMailAgent();
		  }
		    
		  // Returns the instance of ServiceLocator class
		  public static ServiceLocator getInstance() 
		  throws Exception {
			  
			  synchronized (ServiceLocator.class) {
				  if (me == null)   me = new ServiceLocator();
				}
			  
		    return me;
		  }
		    
		  // Converts the serialized string into EJBHandle 
		  // then to EJBObject.
		  public  AuthorizationPageFaced getAuthorizationPageFaced() 
		  throws Exception {
			  
			return authorizationPageFaced;
		  }
		  
		  public  OrderFaced getOrderFaced() 
		  throws Exception {
			 
			return orderFaced;
		  }

		 
		  
		  public  PolicyFaced getPolicyFaced() 
		  throws Exception {
			  
			return policyFaced;
		  }

		  
		  public  ProductlistFaced getProductlistFaced() 
		  throws Exception {
	    
			return productlistFaced;
		  }
		  
		  public  ProductPostAllFaced getProductPostAllFaced() 
		  throws Exception {
			 
			return productPostAllFaced;
		  }
		  
}
