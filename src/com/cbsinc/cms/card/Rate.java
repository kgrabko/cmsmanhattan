package com.cbsinc.cms.card;

import java.net.*;
import java.util.*;
import org.apache.soap.*;
import org.apache.soap.rpc.*;

import org.apache.soap.util.xml.*;
import org.apache.soap.encoding.*;
import org.apache.soap.transport.http.SOAPHTTPConnection;

public class Rate {

	public static void main(String[] args) {
		Rate rate = new Rate();
		// params.addElement (new Parameter("shop_id", Integer.class, "84473",
		// null));
		// params.addElement (new Parameter("login", String.class, "gvidon",
		// null));
		// params.addElement (new Parameter("password", String.class, "231003",
		// null));
		// params.addElement (new Parameter("currency", String.class, "USD",
		// null));
		// params.addElement (new Parameter("date", String.class, "23.01.2006",
		// null));

		try {
			rate.getRateInfo("84473", "gvidon", "231003", "USD", "23.01.2006");
		} catch (Exception ex) {
		}
	}

	public RateInfo getRateInfo(String shop_id, String login, String password, String currency, String date)
			throws Exception {
		// public RateInfo getRateInfo (String shop_id ,String login, String
		// password ,String currency ,String date ) {
		RateInfo answer = null;
		String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
		// url = "http://secure.assist.ru/rate/rateusd.cfm?" + request;
		URL url = new URL("http://secure.assist.ru/rate/rateusd.cfm?format=4");
		String OBJECT_URI = "http://www.assist.ru/type/";

		System.out.println(encodingStyleURI);
		System.out.println(Constants.NS_URI_SOAP_ENC);
		System.out.println(Constants.NS_URI_CURRENT_SCHEMA_XSI);
		System.out.println(Constants.NS_URI_CURRENT_SCHEMA_XSD);

		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		RateinfoSerializer sd = new RateinfoSerializer();
		smr.mapTypes(Constants.NS_URI_SOAP_ENC, new QName(OBJECT_URI, "SOAPStruct"), RateInfo.class, null, sd);

		// create the transport and set parameters
		SOAPHTTPConnection st = new SOAPHTTPConnection();

		// Build the call.
		Call call = new Call();
		call.setTargetObjectURI("http://www.assist.ru/message/");
		call.setSOAPTransport(st);
		call.setSOAPMappingRegistry(smr);

		call.setMethodName("GetRate");
		call.setEncodingStyleURI(encodingStyleURI);

		Vector params = new Vector();
		params.addElement(new Parameter("shop_id", Integer.class, shop_id, null));
		params.addElement(new Parameter("login", String.class, login, null));
		params.addElement(new Parameter("password", String.class, password, null));
		params.addElement(new Parameter("currency", String.class, currency, null));
		params.addElement(new Parameter("date", String.class, date, null));
		call.setParams(params);

		// make the call: note that the action URI is empty because the
		// XML-SOAP rpc router does not need this. This may change in the
		// future.
		Response resp = call.invoke(/* router URL */url, /* actionURI */"");

		// Check the response.
		if (resp.generatedFault()) {
			Fault fault = resp.getFault();
			System.out.println("Fuult: ");
			System.out.println("  Fault Code   = " + fault.getFaultCode());
			System.out.println("  Fault String = " + fault.getFaultString());
		} else {
			Parameter result = resp.getReturnValue();
			answer = (RateInfo) result.getValue();

			System.out.println(result.getName());
			System.out.println("=" + answer.getcurrency());
			System.out.println("=" + answer.getdate());
			System.out.println("=" + answer.getrate());
		}
		return answer;
	}

}
