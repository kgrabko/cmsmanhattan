package com.cbsinc.cms.controllers;

import java.util.Optional;

import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.OrderFaced;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;

public class ServiceLocator {

	private static ServiceLocator me;
	
	private Optional<AuthorizationPageFaced> authorizationPageFaced = null;
	private Optional<OrderFaced> orderFaced = null;
	private Optional<PolicyFaced> policyFaced = null;
	private Optional<ProductlistFaced> productlistFaced = null;
	private Optional<ProductPostAllFaced> productPostAllFaced = null;

	private ServiceLocator() {
		authorizationPageFaced = Optional.of(new AuthorizationPageFaced());
		orderFaced = Optional.of(new OrderFaced());
		policyFaced = Optional.of(new PolicyFaced());
		productlistFaced = Optional.of(new ProductlistFaced());
		productPostAllFaced = Optional.of(new ProductPostAllFaced());
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
	public Optional<AuthorizationPageFaced> getAuthorizationPageFaced() throws Exception {

		return authorizationPageFaced;
	}

	public Optional<OrderFaced> getOrderFaced() throws Exception {

		return orderFaced;
	}

	public Optional<PolicyFaced> getPolicyFaced() throws Exception {

		return policyFaced;
	}

	public Optional<ProductlistFaced> getProductlistFaced() throws Exception {

		return productlistFaced;
	}

	public Optional<ProductPostAllFaced> getProductPostAllFaced() throws Exception {

		return productPostAllFaced;
	}

}
