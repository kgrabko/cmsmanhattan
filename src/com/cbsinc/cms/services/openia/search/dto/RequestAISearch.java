package com.cbsinc.cms.services.openia.search.dto;

public class RequestAISearch {

	private String input = "";
    private String model = "text-embedding-3-small" ;
    private String encoding_format = "base64" ;
    
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getEncoding_format() {
		return encoding_format;
	}
	public void setEncoding_format(String encoding_format) {
		this.encoding_format = encoding_format;
	}
	
	@Override
	public String toString() {
		return "RequestAISearch [input=" + input + ", model=" + model + ", encoding_format=" + encoding_format + "]";
	}

    
    
}
