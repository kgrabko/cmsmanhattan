package com.cbsinc.cms.services.openia.chatbot.dto;

import java.util.Arrays;

public class ChatbotMessageResponse {
	
	
	public ChatbotMessageResponse() {}
	
	public ChatbotMessageResponse(String id, String object, Integer created, String model, Choice[] choices,
			Usage usage, String system_fingerprint) {
		super();
		this.id = id;
		this.object = object;
		this.created = created;
		this.model = model;
		this.choices = choices;
		this.usage = usage;
		this.system_fingerprint = system_fingerprint;
	}


	private String id ;
	private String object;
	private Integer created ;
	private String model ;
	private Choice[] choices ;
	private Usage usage ;
	private String system_fingerprint ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public Integer getCreated() {
		return created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Choice[] getChoices() {
		return choices;
	}
	public void setChoices(Choice[] choices) {
		this.choices = choices;
	}
	public Usage getUsage() {
		return usage;
	}
	public void setUsage(Usage usage) {
		this.usage = usage;
	}
	public String getSystem_fingerprint() {
		return system_fingerprint;
	}
	public void setSystem_fingerprint(String system_fingerprint) {
		this.system_fingerprint = system_fingerprint;
	}
	
	
	@Override
	public String toString() {
		return "ChatbotMessageResponse [id=" + id + ", object=" + object + ", created=" + created + ", model=" + model
				+ ", choices=" + Arrays.toString(choices) + ", usage=" + usage + ", system_fingerprint="
				+ system_fingerprint + "]";
	}
	
	

}
