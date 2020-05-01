package com.cbsinc.cms.card;

import org.w3c.dom.*;
import org.apache.soap.rpc.*;
import org.apache.soap.util.*;
import org.apache.soap.util.xml.*;

public class Paymentinfoserializer implements Deserializer {
	public Bean unmarshall(String inScopeEncStyle, QName elementType, Node src, XMLJavaMappingRegistry xjmr,
			SOAPContext ctx) throws IllegalArgumentException {
		Element svcElement = (Element) src;
		Element tempEl = DOMUtils.getFirstChildElement(svcElement);
		Paymentinfo svc = new Paymentinfo();

		while (tempEl != null) {
			String tagName = tempEl.getTagName();

			if (tagName.equals("ordernumber")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setordernumber((String) param.getValue());
			} else if (tagName.equals("total")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.settotal((String) param.getValue());
			} else if (tagName.equals("status")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setstatus((String) param.getValue());
			} else if (tagName.equals("comment")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setcomment((String) param.getValue());
			} else if (tagName.equals("response_code")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setresponse_code((String) param.getValue());
			} else if (tagName.equals("country")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setcountry((String) param.getValue());
			} else if (tagName.equals("cardholder")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setcardholder((String) param.getValue());
			} else if (tagName.equals("cardsubtype")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setcardsubtype((String) param.getValue());
			} else if (tagName.equals("cvc2")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setcvc2((String) param.getValue());
			} else if (tagName.equals("approvalcode")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setapprovalcode((String) param.getValue());
			} else if (tagName.equals("rate")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setrate((String) param.getValue());
			} else if (tagName.equals("ipaddress")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setipaddress((String) param.getValue());
			} else if (tagName.equals("recommendation")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setrecommendation((String) param.getValue());
			} else if (tagName.equals("message")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setmessage((String) param.getValue());
			} else if (tagName.equals("date")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setdate((String) param.getValue());
			} else if (tagName.equals("currency")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setcurrency((String) param.getValue());
			} else if (tagName.equals("cardtype")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setcardtype((String) param.getValue());
			} else if (tagName.equals("cardnumber")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setcardnumber((String) param.getValue());
			} else if (tagName.equals("lastname")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setlastname((String) param.getValue());
			} else if (tagName.equals("firstname")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setfirstname((String) param.getValue());
			} else if (tagName.equals("middlename")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setmiddlename((String) param.getValue());
			} else if (tagName.equals("address")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setaddress((String) param.getValue());
			} else if (tagName.equals("email")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setemail((String) param.getValue());
			} else if (tagName.equals("protocoltypename")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setprotocoltypename((String) param.getValue());
			} else if (tagName.equals("billnumber")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setbillnumber((String) param.getValue());
			} else if (tagName.equals("bankname")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.setbankname((String) param.getValue());
			} else if (tagName.equals("error_code")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.seterror_code((String) param.getValue());
			} else if (tagName.equals("error_comment")) {
				Bean bean = xjmr.unmarshall(inScopeEncStyle, RPCConstants.Q_ELEM_PARAMETER, tempEl, ctx);
				Parameter param = (Parameter) bean.value;

				svc.seterror_comment((String) param.getValue());
			}

			tempEl = DOMUtils.getNextSiblingElement(tempEl);
		}

		return new Bean(CardInfo.class, svc);
	}
}
