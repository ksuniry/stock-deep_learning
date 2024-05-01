package com.stock.mvc.service.koreanInvestment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock.constants.KoreaInvestmentConst;
import com.stock.constants.UtilConst;
import com.stock.mvc.dao.koreanInvestment.Hoga20Dao;
import com.stock.mvc.dao.krx.CompanyInfoDao;
import com.stock.utils.CommonUtil;
import com.stock.utils.KoreaInvestmentApiLink;

@Service
public class RankCollectionService {
	private Logger logger= Logger.getLogger(RankCollectionService.class.getName());
	 
	@Autowired
	CompanyInfoDao companyInfoDao;
	@Autowired
	Hoga20CollectionService hoga20CollectionService;
	@Autowired
	Hoga20Dao hoga20Dao;
	 
	public void rankCollection() {
		Map<String, String> NOW_RANK_PARAMETER_MAP = new  HashMap<String, String>();
		NOW_RANK_PARAMETER_MAP.put("fidRankSortClsCodeHigh", "0");
		NOW_RANK_PARAMETER_MAP.put("fidPrcClsCodeHigh", "0");
		NOW_RANK_PARAMETER_MAP.put("fidRankSortClsCodeLow", "1");
		NOW_RANK_PARAMETER_MAP.put("fidPrcClsCodeLow", "1");
		
		String[] collectionTypeArray = {"High", "Low"};
		 
		for(int i = 0 ; i < collectionTypeArray.length ; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			String	conllectionType = collectionTypeArray[i];
			
			Runnable runnalble = new Runnable() {
				@Override
				public void run() {
					String	subUrlPath = KoreaInvestmentConst.NOW_RANK_URL.replaceAll("@fidRankSortClsCode", NOW_RANK_PARAMETER_MAP.get("fidRankSortClsCode".concat(conllectionType)))
							.replaceAll("@fidPrcClsCode", NOW_RANK_PARAMETER_MAP.get("fidPrcClsCode".concat(conllectionType)));
					rankCollectionThreadExcutor(subUrlPath);
				}
			};
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.scheduleAtFixedRate(runnalble, 0, 30, TimeUnit.SECONDS);
		}
	}
	 
	 public void rankCollectionThreadExcutor(String subUrlPath) {
		 if(CommonUtil.isTraideTime()) {
			 try {
				 String resultResponse = KoreaInvestmentApiLink.get(subUrlPath, KoreaInvestmentConst.NOW_RANK_TR_ID);
				 //logger.info(resultResponse);
				 JsonObject jsonObject = (JsonObject) JsonParser.parseString(resultResponse);
				 JsonArray rankJsonArray =  jsonObject.get("output").getAsJsonArray();
				 rankJsonArray.forEach(jsonItem -> {
					 JsonObject rankJsonObject = jsonItem.getAsJsonObject();
					 String stckShrnIscd= rankJsonObject.get("stck_shrn_iscd").getAsString();
					 KoreaInvestmentConst.NOW_RANK_MAP.put(stckShrnIscd, CommonUtil.getNowDateByFormat(UtilConst.DATE_FORMAT_YYYYMMDDHHMMSS));
				 });
				 KoreaInvestmentConst.NOW_RANK_MAP.forEach((strKey, strValue)->{
						long diffSecond = CommonUtil.getBetweenSecondByYyyyMMddHHmmss(strValue);
						if(diffSecond > 120){
							KoreaInvestmentConst.NOW_RANK_MAP.remove(strKey);
							if(KoreaInvestmentConst.HOGA20_THREAD_MAP.get(strKey) != null) {
								KoreaInvestmentConst.HOGA20_THREAD_MAP.get(strKey).shutdown();	
								KoreaInvestmentConst.HOGA20_THREAD_MAP.remove(strKey);
							}
						}
				 });
				 logger.info("KoreaInvestmentConst.NOW_RANK_MAP.size : "+KoreaInvestmentConst.NOW_RANK_MAP.size());
			 } catch ( Exception error ) {
				 logger.log(Level.WARNING, error.toString());
			 }
		}else {
			logger.info(" is not traide time ");
		}
	}
}
