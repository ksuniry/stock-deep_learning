package com.stock.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonObject;
import com.stock.constants.KoreaInvestmentConst;

public class KoreaInvestmentApiLink {
	
	private static Logger logger = Logger.getLogger(KoreaInvestmentApiLink.class.getName());
	
	public static String get(String urlPath, String trId) throws Exception{
		
		String requestURL = KoreaInvestmentConst.BAGIC_URL.concat(urlPath);
		String resultBody = "";
		try {
			HttpClient client = HttpClientBuilder.create().build();
		    HttpGet getRequest = new HttpGet(requestURL); 
		    getRequest.addHeader("Content-Type", "application/json;charset=UTF-8");
		    getRequest.setHeader("Connection", "keep-alive");
		    getRequest.setHeader("Accept-Encoding", "gzip, deflate, br");
		    getRequest.setHeader("Cache-Control", "no-cache");
		   
		    getRequest.addHeader("authorization", "Bearer ".concat(KoreaInvestmentConst.BEARER_TOKEN));
		    getRequest.addHeader("appkey", KoreaInvestmentConst.APP_KEY);
		    getRequest.addHeader("appsecret", KoreaInvestmentConst.SECRET_KEY);
		    getRequest.addHeader("tr_id", trId);
		    HttpResponse response = client.execute(getRequest);

		    if (response.getStatusLine().getStatusCode() == 200) {
		        ResponseHandler<String> handler = new BasicResponseHandler();
		        resultBody = handler.handleResponse(response);
		    } else {
		       logger.log(Level.WARNING, "response is error : "+urlPath+" "+response.toString());
		    }
		 } catch (Exception e){
			 logger.log(Level.WARNING, "is system error : "+e.toString());
		 }
		
		return resultBody;
	}
	
	
	public static String post(String urlPath, JsonObject jsonObjectData) throws Exception{
		
		String requestURL = KoreaInvestmentConst.BAGIC_URL.concat(urlPath);
		String resultBody = "";
		try {
	         HttpClient client = HttpClientBuilder.create().build(); 
	         HttpPost postRequest = new HttpPost(requestURL);  
	         postRequest.addHeader("Content-Type", "application/json;charset=UTF-8");
	         postRequest.setHeader("Connection", "keep-alive");
	         

	         postRequest.setEntity(new StringEntity(jsonObjectData.toString())); 

	         HttpResponse response = client.execute(postRequest);

	         if (response.getStatusLine().getStatusCode() == 200) {
	             ResponseHandler<String> handler = new BasicResponseHandler();
	             resultBody = handler.handleResponse(response);
	         } else {
	             System.out.println("response is error : " + response.getStatusLine().getStatusCode());
	         }
	     } catch (Exception e){
	         System.err.println(e.toString());
	     }
		return resultBody;
	}
	
}


