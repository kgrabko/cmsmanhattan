package com.cbsinc.cms.services.james.client.domain;

public class Status {
	
   private  String A ;
   private  String B ;
   private  String C ;
   
   
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	
	@Override
	public String toString() {
		return "Status [A=" + A + ", B=" + B + ", C=" + C + "]";
	}
   
	
   

}
