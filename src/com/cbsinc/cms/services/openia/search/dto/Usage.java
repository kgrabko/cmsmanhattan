package com.cbsinc.cms.services.openia.search.dto;

public class Usage {
	
	private String prompt_tokens = "" ;
	private String total_tokens = "" ;
	
	public String getPrompt_tokens() {
		return prompt_tokens;
	}
	public void setPrompt_tokens(String prompt_tokens) {
		this.prompt_tokens = prompt_tokens;
	}
	public String getTotal_tokens() {
		return total_tokens;
	}
	public void setTotal_tokens(String total_tokens) {
		this.total_tokens = total_tokens;
	}
	
	
	@Override
	public String toString() {
		return "Usage [prompt_tokens=" + prompt_tokens + ", total_tokens=" + total_tokens + "]";
	}
	
	

}
