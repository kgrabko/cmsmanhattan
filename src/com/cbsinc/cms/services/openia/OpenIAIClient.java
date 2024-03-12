package com.cbsinc.cms.services.openia;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.xml.bind.DatatypeConverter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.cbsinc.cms.client.rest.utils.RestTemplateConfig;
import com.cbsinc.cms.services.james.client.domain.ResponseRenameUsers;
import com.cbsinc.cms.services.james.client.domain.faceds.DomainApiClient;
import com.cbsinc.cms.services.openia.chatbot.dto.*;
import com.cbsinc.cms.services.openia.search.dto.RequestAISearch;
import com.cbsinc.cms.services.openia.search.dto.ResponseAISearch;

public class OpenIAIClient {
	
	private static OpenIAIClient openIASearch = new OpenIAIClient();
	
    private ResourceBundle resources_cms_settings = null ;
	private String  openip_key ;
	private String  openip_host ;
	private String  openip_port ;
	private String  openip_protocol ;
	

	// help customer find items and buy item , sales man
	private String openip_salesman_model = "gpt-3.5-turbo" ;
	// chat to site technical question and navigation
	private String  openip_tech_support_model = "gpt-3.5-turbo" ;
	// manager assistant
	private String  openip_manager_model = "gpt-3.5-turbo" ;
	// chat to product suppliers and shipping and delivery questions and accept pricing
	private String  openip_marketingman_model = "gpt-3.5-turbo"  ; 
	
	
	private OpenIAIClient() 
	{
		if( resources_cms_settings == null ) resources_cms_settings =  PropertyResourceBundle.getBundle("appconfig");
		openip_key = resources_cms_settings.getString("openip_key").trim() ;
		openip_host = resources_cms_settings.getString("openip_host").trim() ;
		openip_port = resources_cms_settings.getString("openip_port").trim() ;
		openip_protocol  = resources_cms_settings.getString("openip_protocol").trim() ;
		
		openip_salesman_model  = resources_cms_settings.getString("openip_salesman_model").trim() ;
		openip_tech_support_model  = resources_cms_settings.getString("openip_tech_support_model").trim() ;
		openip_manager_model  = resources_cms_settings.getString("openip_manager_model").trim() ;
		openip_marketingman_model  = resources_cms_settings.getString("openip_marketingman_model").trim() ;
	}
	
	
	public static OpenIAIClient getInstanse()
	{
		return openIASearch;
	}
	
	
	public ResponseAISearch OpenIASearch(String searchText  ) {
		RestTemplate restTemplate = RestTemplateConfig.getRestTemplate();
		//String endPoint = openip_protocol + "://" + openip_host + ":" + openip_port + "/v1/embeddings";
		String endPoint = openip_protocol + "://" + openip_host + "/v1/embeddings";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set( "Authorization", "Bearer " + openip_key ) ;


		RequestAISearch requestAISearch = new RequestAISearch();
		requestAISearch.setInput(searchText) ;
		HttpEntity<RequestAISearch> request = new HttpEntity<>(requestAISearch,headers);
		ResponseAISearch responseRenameUsers = restTemplate.postForObject(endPoint, request,ResponseAISearch.class);
		return responseRenameUsers;
	}
	
	
	public ChatbotMessageResponse OpenIAChatBot(String question , String previousQuestion , String iaModel) {
		RestTemplate restTemplate = RestTemplateConfig.getRestTemplate();
		//String endPoint = openip_protocol + "://" + openip_host + ":" + openip_port + "/v1/embeddings";
		String endPoint = openip_protocol + "://" + openip_host + "/v1/chat/completions";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set( "Authorization", "Bearer " + openip_key ) ;


		CharbotMessageRequest charbotMessageRequest = new CharbotMessageRequest();
		
		Message[] messages = new Message[] { new Message("system" ,question  ) , new Message("user" , previousQuestion )  } ;
		charbotMessageRequest.setMessages(messages) ;
		charbotMessageRequest.setModel(iaModel) ;
		
		
		HttpEntity<CharbotMessageRequest> request = new HttpEntity<>(charbotMessageRequest,headers);
		ChatbotMessageResponse responseRenameUsers = restTemplate.postForObject(endPoint, request,ChatbotMessageResponse.class);
		return responseRenameUsers;
	}
	
	public ChatbotMessageResponse OpenIAChatBotManager(String question , String previousQuestion) {
		return OpenIAChatBot( question ,  previousQuestion ,  openip_manager_model) ;
	}
	
	
	public ChatbotMessageResponse OpenIAChatBotSalesMan(String question , String previousQuestion) {
		return OpenIAChatBot( question ,  previousQuestion ,  openip_salesman_model) ;
	}

	public ChatbotMessageResponse OpenIAChatBotMarketingMan(String question , String previousQuestion) {
		return OpenIAChatBot( question ,  previousQuestion ,  openip_marketingman_model) ;
	}
	
	public ChatbotMessageResponse OpenIAChatBotTechSupport(String question , String previousQuestion) {
		return OpenIAChatBot( question ,  previousQuestion ,  openip_tech_support_model) ;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		/*
		 * ResponseAISearch responseAISearch =
		 * getInstanse().OpenIASearch(" who are you ? ") ; String test =
		 * responseAISearch.getData()[0].getEmbedding() ;
		 * 
		 * byte[] decodedBytes = Base64.getDecoder().decode(test); //test = new
		 * String(decodedBytes, "UTF-8"); test = new String(decodedBytes);
		 * System.out.println("text: " + test ) ;
		 */
		
		ChatbotMessageResponse chatbotMessageResponse =  getInstanse().OpenIAChatBot(" I like meat " , "I'm looking for a book on cooking traditional Italian pasta dishes.", getInstanse().openip_manager_model ) ; 
		Choice[]  choices = chatbotMessageResponse.getChoices() ;
		 for( Choice choice : choices )  {
			 Message message = choice.getMessage();
			 message.getContent();
			 System.out.println("Who: " + message.getRole() + " text: " +  message.getContent() ) ;
		 }

		
	}
}
