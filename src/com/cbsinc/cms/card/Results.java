package com.cbsinc.cms.card;

import java.net.*;
import java.util.*;
import org.apache.soap.*;
import org.apache.soap.rpc.*;

import org.apache.soap.util.xml.*;
import org.apache.soap.encoding.*;
import org.apache.soap.encoding.soapenc.*;
import org.apache.soap.transport.http.SOAPHTTPConnection;

public class Results {
	public static void main(String[] args) throws Exception {

		String encodingStyleURI = Constants.NS_URI_SOAP_ENC;

		URL url = new URL("http://secure.assist.ru/results/results.cfm?format=5");

		String OBJECT_URI = "http://www.assist.ru/type/";

		System.out.println(encodingStyleURI);
		System.out.println(Constants.NS_URI_SOAP_ENC);
		System.out.println(Constants.NS_URI_CURRENT_SCHEMA_XSI);
		System.out.println(Constants.NS_URI_CURRENT_SCHEMA_XSD);

		SOAPMappingRegistry smr = new SOAPMappingRegistry();

		ArraySerializer sd = new ArraySerializer();
		smr.mapTypes(Constants.NS_URI_SOAP_ENC, new QName(OBJECT_URI, "return"), null, null, sd);

		Paymentinfoserializer sd1 = new Paymentinfoserializer();
		smr.mapTypes(Constants.NS_URI_SOAP_ENC, new QName(OBJECT_URI, "SOAPStruct"), Paymentinfo.class, null, sd1);

		// create the transport and set parameters
		SOAPHTTPConnection st = new SOAPHTTPConnection();

		// Build the call.
		Call call = new Call();
		call.setTargetObjectURI("http://www.assist.ru/message/");
		call.setSOAPTransport(st);
		call.setSOAPMappingRegistry(smr);

		call.setMethodName("GetPaymentsResult");
		call.setEncodingStyleURI(encodingStyleURI);

		Vector params = new Vector();
		params.addElement(new Parameter("shop_id", Integer.class, "84473", null));
		params.addElement(new Parameter("login", String.class, "gvidon", null));
		params.addElement(new Parameter("password", String.class, "231003", null));
		params.addElement(new Parameter("shopordernumber", String.class, "193", null));
		params.addElement(new Parameter("success", String.class, "2", null));
		params.addElement(new Parameter("startday", String.class, "24", null));
		params.addElement(new Parameter("startmonth", String.class, "01", null));
		params.addElement(new Parameter("startyear", String.class, "2006", null));
		params.addElement(new Parameter("endday", String.class, "23", null));
		params.addElement(new Parameter("endmonth", String.class, "01", null));
		params.addElement(new Parameter("endyear", String.class, "2006", null));
		params.addElement(new Parameter("meantype", String.class, "0", null));
		params.addElement(new Parameter("paymenttype", String.class, "16", null));
		params.addElement(new Parameter("english", String.class, "0", null));

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

			System.out.println(result.getName());

			Paymentinfo[] test = (Paymentinfo[]) result.getValue();

			for (int i = 0; i < test.length; i++) {
				System.out.println("payment");
				System.out.println("==" + test[i].getordernumber());
				System.out.println("==" + test[i].getresponse_code());
				System.out.println("==" + test[i].getrecommendation());
				System.out.println("==" + test[i].getmessage());
				System.out.println("==" + test[i].getcomment());
				System.out.println("==" + test[i].getdate());
				System.out.println("==" + test[i].gettotal());
				System.out.println("==" + test[i].getcurrency());
				System.out.println("==" + test[i].getcardtype());
				System.out.println("==" + test[i].getcardnumber());
				System.out.println("==" + test[i].getlastname());
				System.out.println("==" + test[i].getfirstname());
				System.out.println("==" + test[i].getmiddlename());
				System.out.println("==" + test[i].getaddress());
				System.out.println("==" + test[i].getemail());
				System.out.println("==" + test[i].getcountry());
				System.out.println("==" + test[i].getrate());
				System.out.println("==" + test[i].getapprovalcode());
				System.out.println("==" + test[i].getcardsubtype());
				System.out.println("==" + test[i].getcvc2());
				System.out.println("==" + test[i].getcardholder());
				System.out.println("==" + test[i].getipaddress());
				System.out.println("==" + test[i].getprotocoltypename());
				System.out.println("==" + test[i].getbillnumber());
				System.out.println("==" + test[i].getbankname());
				System.out.println("==" + test[i].getstatus());
				System.out.println("==" + test[i].geterror_code());
				System.out.println("==" + test[i].geterror_comment());
				System.out.println("");
			}

		}
	}
}
