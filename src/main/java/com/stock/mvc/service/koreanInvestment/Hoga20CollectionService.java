package com.stock.mvc.service.koreanInvestment;

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
public class Hoga20CollectionService {
	private Logger logger= Logger.getLogger(Hoga20CollectionService.class.getName());
	 
	@Autowired
	CompanyInfoDao companyInfoDao;
	@Autowired
	Hoga20Dao hoga20Dao;
	
	@Scheduled(fixedDelay = 60000) // 1분마다 실행
	public void Hoga20Collection() {
		List<String> isuSrtCdList = new ArrayList<String>();   
		KoreaInvestmentConst.NOW_RANK_MAP.forEach((keyKey, keyValue) ->{
			 if(!KoreaInvestmentConst.NOW_RANK_MAP.containsKey(keyValue)) {
				 isuSrtCdList.add(keyKey);
			 }
		});
		if(CommonUtil.isEmpty(isuSrtCdList)) {
			logger.info("Hoga20Collection > isuSrtCdList is none!");
		}else {
			List<CompanyBagicInfoVO> companyInfoList =  companyInfoDao.companyBagicInfoHoga20ListByisuSrtCdList(isuSrtCdList);
			
			for(int i = 0 ; i < companyInfoList.size(); i++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				CompanyBagicInfoVO  companyBagicInfoVO = companyInfoList.get(i);
				Runnable runnalble = new Runnable() {
					@Override
					public void run() {
						challengeThreadExcutor(companyBagicInfoVO);
					}
				};
				ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
				service.scheduleAtFixedRate(runnalble, 0, 60, TimeUnit.SECONDS);
			}
		}
	}
	 
	 public void challengeThreadExcutor(CompanyBagicInfoVO companyBagicInfoVO) {
		 if(CommonUtil.isTraideTime() && "Y".equals(KoreaInvestmentConst.TRAIDE_DAY_YN)) {
			 String subUrlPath = KoreaInvestmentConst.HOGA20_URL.replaceAll("@iscd", companyBagicInfoVO.getIsuSrtCd());
			 try {
				String resultResponse = KoreaInvestmentApiLink.get(subUrlPath, KoreaInvestmentConst.HOGA20_TR_ID);
				JsonObject jsonObject = (JsonObject) JsonParser.parseString(resultResponse);
				Hoga20VO hoga20VO = new Hoga20VO();
				hoga20VO.setIsuSrtCd(companyBagicInfoVO.getIsuSrtCd());
				hoga20VO.setId(CommonUtil.getNowDateByFormat(UtilConst.DATE_FORMAT_YYYYMMDDHHMMSS));
				hoga20VO.setInfo(jsonObject.toString());
				hoga20Dao.insertHoga20(hoga20VO);   	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			logger.info(" challengeThreadExcutor is not traide time ");
		}
	}
	 
	 
	 public void hoga20TableInit() {
		 List<CompanyBagicInfoVO> companyInfoList =  companyInfoDao.companyBagicInfoList();
		 
		 for(CompanyBagicInfoVO companyBagicInfoVO : companyInfoList) {
			 try {
				 Hoga20VO result =  hoga20Dao.selectHoga20TableCheck(companyBagicInfoVO);
				 if(!CommonUtil.isEmpty(result)) {
					 result.getId();
				 }
				 
			} catch (Exception e1) {
				String creatSQL = "CREATE TABLE stock.hoga20_".concat(companyBagicInfoVO.getIsuSrtCd()).concat(" ( id varchar NOT NULL, info varchar NULL, CONSTRAINT idx_hoga20_").concat(companyBagicInfoVO.getIsuSrtCd()).concat("_id PRIMARY KEY (id))");
				try {
					hoga20Dao.createhoga20Table(creatSQL);
				} catch (Exception e2) {
					logger.log(Level.WARNING, companyBagicInfoVO.getIsuCd().concat("/").concat(companyBagicInfoVO.getIsuNm().concat(" -> ").concat(e2.toString())));
				}
			}
		 }
	}
}
