package com.cbsinc.cms.controllers;

import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.OrderFaced;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;

public class ServiceLocator {

	private static ServiceLocator me;
	
	private AuthorizationPageFaced authorizationPageFaced = null;
	private OrderFaced orderFaced = null;
	private PolicyFaced policyFaced = null;
	private ProductlistFaced productlistFaced = null;
	private ProductPostAllFaced productPostAllFaced = null;

	private ServiceLocator() {
		authorizationPageFaced = new AuthorizationPageFaced() ;
		orderFaced = new OrderFaced() ;
		policyFaced = new PolicyFaced() ;
		productlistFaced = new ProductlistFaced() ;
		productPostAllFaced = new ProductPostAllFaced() ;
	}

	// Returns the instance of ServiceLocator class
	public static ServiceLocator getInstance() throws Exception {

		synchronized (ServiceLocator.class) {
			if (me == null)
				me = new ServiceLocator();
		}

		return me;
	}

	// Converts the serialized string into EJBHandle
	// then to EJBObject.
	public AuthorizationPageFaced getAuthorizationPageFaced() throws Exception {

		return authorizationPageFaced;
	}

	public OrderFaced getOrderFaced() throws Exception {

		return orderFaced;
	}

	public PolicyFaced getPolicyFaced() throws Exception {

		return policyFaced;
	}

	public ProductlistFaced getProductlistFaced() throws Exception {

		return productlistFaced;
	}

	public ProductPostAllFaced getProductPostAllFaced() throws Exception {

		return productPostAllFaced;
	}

}
