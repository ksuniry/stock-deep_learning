package com.stock.utils;

import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonObject;
import com.stock.constants.KrxConst;

public class KrxApiLink {
	
	private static Logger logger = Logger.getLogger(KrxApiLink.class.getName());
	
	public static String post(String urlPath, List<NameValuePair> paramList) throws Exception{
		
		String requestURL = KrxConst.KRX_BAGIC_URL.concat(urlPath);
		String resultBody = "";
		try {
	         HttpClient client = HttpClientBuilder.create().build(); 
	         HttpPost postRequest = new HttpPost(requestURL);  
	         postRequest.addHeader("Cookie", "JSESSIONID=GacG9Sg5Z0rBr9cHv9XHFvvs4tlQ11XKSarEgFdQa6JNOkkEzdwqR6DgTry6CB1m.bWRjX2RvbWFpbi9tZGNvd2FwMi1tZGNhcHAxMQ==; __smVisitorID=mEdn5rxlECQ");
	         postRequest.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	         postRequest.setHeader("Connection", "keep-alive");
	         postRequest.setHeader("Accept-Encoding", "gzip, deflate");

	         postRequest.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8")); 

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


