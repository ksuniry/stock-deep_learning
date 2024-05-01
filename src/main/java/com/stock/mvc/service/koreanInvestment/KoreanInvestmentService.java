package com.stock.mvc.service.koreanInvestment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock.constants.KoreaInvestmentConst;
import com.stock.constants.UtilConst;
import com.stock.mvc.dao.koreanInvestment.Hoga20Dao;
import com.stock.mvc.dao.krx.CompanyInfoDao;
import com.stock.mvc.vo.koreanInvestment.Hoga20VO;
import com.stock.mvc.vo.krx.CompanyBagicInfoVO;
import com.stock.utils.CommonUtil;
import com.stock.utils.KoreaInvestmentApiLink;

@Service
public class KoreanInvestmentService {
	private Logger logger= Logger.getLogger(KoreanInvestmentService.class.getName());
	
	public void koreaInvestmentBearerTokenInit() {		
		Runnable runnalble = new Runnable() {
			@Override
			public void run() {
				koreaInvestmentBearerTokenInitThreadExcutor();
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnalble, 0, 23, TimeUnit.HOURS);
	}
	 
	 public void koreaInvestmentBearerTokenInitThreadExcutor() {
		 	String koreaInvestmentBearerToken = null;
	    	Boolean isReGetkoreaInvestmentBearToken = false;
	    	try {
	    		String koreaInvestmentBearerTokenInfo = CommonUtil.readFile(System.getProperty("user.dir"),"koreaInvestmentBearerToken.txt");
	    		String[] infoArray = koreaInvestmentBearerTokenInfo.split("@@@@");
	    		String tokenDate = infoArray[0];
	    		long diffHour = CommonUtil.getBetweenHourByYyyyMMddHHmmss(tokenDate);
	    		if(diffHour >= 23) {
	    			isReGetkoreaInvestmentBearToken = true;
	    		}else {
	    			koreaInvestmentBearerToken = infoArray[1];
	    		}
	    	}catch (Exception error){
	    		isReGetkoreaInvestmentBearToken = true;
	    	}
	    	
	    	if(isReGetkoreaInvestmentBearToken){
		    	JsonObject koreaInvestmentJsonObjectData = new JsonObject();
		    	koreaInvestmentJsonObjectData.addProperty("grant_type", "client_credentials");
		    	koreaInvestmentJsonObjectData.addProperty("appkey", KoreaInvestmentConst.APP_KEY);
		    	koreaInvestmentJsonObjectData.addProperty("appsecret", KoreaInvestmentConst.SECRET_KEY);
		    	try {
			    	String resultResponse = KoreaInvestmentApiLink.post(KoreaInvestmentConst.BEARER_TOKEN_URL, koreaInvestmentJsonObjectData);
			    	JsonObject jsonObject = (JsonObject) JsonParser.parseString(resultResponse);
			    	koreaInvestmentBearerToken= jsonObject.get("access_token").getAsString();
			    	String nowTime = CommonUtil.getNowDateByFormat(UtilConst.DATE_FORMAT_YYYYMMDDHHMMSS);
			    	CommonUtil.writeFile(System.getProperty("user.dir"),"koreaInvestmentBearerToken.txt",nowTime.concat("@@@@").concat(koreaInvestmentBearerToken));
			    	
		    	} catch (Exception error ) {
		    		
		    	}
		    			
	    	}
	    	
	    	KoreaInvestmentConst.BEARER_TOKEN = koreaInvestmentBearerToken;
	    	logger.info(" KoreaInvestmentInfo.BEARER_TOKEN : "+KoreaInvestmentConst.BEARER_TOKEN);
		
	}
	 
	 public void koreaInvestmentIsTraideDayYn() {		
			Runnable runnalble = new Runnable() {
				@Override
				public void run() {
					koreaInvestmentIsTradeDayYnThreadExcutor();
				}
			};
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleAtFixedRate(runnalble, 0, 1, TimeUnit.HOURS);
	}
	 
	 
	 public void koreaInvestmentIsTradeDayYnThreadExcutor() {
		 
		 String bassDtYyyymmdd = CommonUtil.getNowDateByFormat(UtilConst.DATE_FORMAT_YYYYMMDD);
		 String	subUrlPath = KoreaInvestmentConst.TRAIDE_YN_URL.replaceAll("@bassDtYyyymmdd", bassDtYyyymmdd);
		 try {
			 String resultResponse = KoreaInvestmentApiLink.get(subUrlPath, KoreaInvestmentConst.TRAIDE_YN_TR_ID);
			 
			 JsonObject jsonObject = (JsonObject) JsonParser.parseString(resultResponse);
		     JsonArray outputArray= jsonObject.get("output").getAsJsonArray();
		     
		     JsonObject traidDay = (JsonObject) outputArray.get(0);
		     String bzdyYn =  traidDay.get("bzdy_yn").getAsString();
		     
		     KoreaInvestmentConst.TRAIDE_DAY_YN = bzdyYn;
		 } catch (Exception error) {
			 
		 }
	     		
	}
	 
}
