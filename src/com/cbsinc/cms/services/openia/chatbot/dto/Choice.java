package com.cbsinc.cms.services.openia.chatbot.dto;

public class Choice {
	
	
	public Choice() { }
	
	public Choice(Integer index, Message message, Integer logprobs, String finish_reason) {
		super();
		this.index = index;
		this.message = message;
		this.logprobs = logprobs;
		this.finish_reason = finish_reason;
	}


	private Integer index ;
	private Message message ;
	private Integer logprobs;
	private String finish_reason;
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Integer getLogprobs() {
		return logprobs;
	}
	public void setLogprobs(Integer logprobs) {
		this.logprobs = logprobs;
	}
	public String getFinish_reason() {
		return finish_reason;
	}
	public void setFinish_reason(String finish_reason) {
		this.finish_reason = finish_reason;
	}
	
	
	@Override
	public String toString() {
		return "Choice [index=" + index + ", message=" + message + ", logprobs=" + logprobs + ", finish_reason="
				+ finish_reason + "]";
	}
	
	
	
	
	
	

}
