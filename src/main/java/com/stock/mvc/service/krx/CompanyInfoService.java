package com.stock.mvc.service.krx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.stock.constants.KrxConst;
import com.stock.core.StockInitialize;
import com.stock.mvc.dao.krx.CompanyInfoDao;
import com.stock.mvc.vo.krx.CompanyBagicInfoVO;
import com.stock.utils.KrxApiLink;

@Service
public class CompanyInfoService {
	 private Logger logger= Logger.getLogger(CompanyInfoService.class.getName());
	  
	@Autowired
	CompanyInfoDao companyInfoDao;
	
	public void mergeCompanyBagicInfo() throws Exception{
		
		List<CompanyBagicInfoVO> selectCompanyBagicInfoList = companyInfoDao.companyBagicInfoList();
		
    	List<NameValuePair> krxKospiCompanyInfoList = new ArrayList<NameValuePair>();

    	krxKospiCompanyInfoList.add(new BasicNameValuePair("locale", KrxConst.KRX_LOCALE));
    	krxKospiCompanyInfoList.add(new BasicNameValuePair("share", KrxConst.KRX_SHARE));
    	krxKospiCompanyInfoList.add(new BasicNameValuePair("csvxls_isNo", KrxConst.KRX_CSVXLS_IS_NO));
    	krxKospiCompanyInfoList.add(new BasicNameValuePair("bld", KrxConst.KRX_KOSPI_BID));
    	krxKospiCompanyInfoList.add(new BasicNameValuePair("mktId", KrxConst.KRX_KOSPI_MKT_ID));

    	String krxKospiResponse = KrxApiLink.post(KrxConst.KRX_COMPANY_BAGIC_INFO_URL, krxKospiCompanyInfoList);
		JsonObject kospiJsonObject = (JsonObject) JsonParser.parseString(krxKospiResponse);
		JsonArray kospiJsonArray = kospiJsonObject.get("OutBlock_1").getAsJsonArray();
		
		CompanyBagicInfoVO kospiVO = null;
		Iterator<JsonElement> kospiJsonElementArray =  kospiJsonArray.iterator();
		List<CompanyBagicInfoVO> kospiList = new ArrayList<CompanyBagicInfoVO>();
		while(kospiJsonElementArray.hasNext()) {
			kospiVO = new CompanyBagicInfoVO();
			JsonElement item = kospiJsonElementArray.next();
			kospiVO.setIsuCd(item.getAsJsonObject().get("ISU_CD").getAsString());
			kospiVO.setIsuSrtCd(item.getAsJsonObject().get("ISU_SRT_CD").getAsString());
			kospiVO.setIsuNm(item.getAsJsonObject().get("ISU_NM").getAsString());
			kospiVO.setIsuAbbrv(item.getAsJsonObject().get("ISU_ABBRV").getAsString());
			kospiVO.setIsuEngNm(item.getAsJsonObject().get("ISU_ENG_NM").getAsString());
			kospiVO.setListDd(item.getAsJsonObject().get("LIST_DD").getAsString());
			kospiVO.setMktTpNm(item.getAsJsonObject().get("MKT_TP_NM").getAsString());
			kospiVO.setSecugrpNm(item.getAsJsonObject().get("SECUGRP_NM").getAsString());
			kospiVO.setSecugrpNm(item.getAsJsonObject().get("SECT_TP_NM").getAsString());
			kospiVO.setKindStkcertTpNm(item.getAsJsonObject().get("KIND_STKCERT_TP_NM").getAsString());
			kospiVO.setParval(item.getAsJsonObject().get("PARVAL").getAsString());
			kospiVO.setListShrs(item.getAsJsonObject().get("LIST_SHRS").getAsString());
			kospiList.add(kospiVO);
		}
		
		List<NameValuePair> krxKosdaqCompanyInfoList = new ArrayList<NameValuePair>();
		krxKosdaqCompanyInfoList.add(new BasicNameValuePair("locale", KrxConst.KRX_LOCALE));
		krxKosdaqCompanyInfoList.add(new BasicNameValuePair("share", KrxConst.KRX_SHARE));
		krxKosdaqCompanyInfoList.add(new BasicNameValuePair("csvxls_isNo", KrxConst.KRX_CSVXLS_IS_NO));
		krxKosdaqCompanyInfoList.add(new BasicNameValuePair("bld", KrxConst.KRX_KOSDAQ_BID));
		krxKosdaqCompanyInfoList.add(new BasicNameValuePair("mktId", KrxConst.KRX_KOSDAQ_MKT_ID));
		krxKosdaqCompanyInfoList.add(new BasicNameValuePair("segTpCd", KrxConst.KRX_KOSDAQ_SEQ_TP_CD));
    	
		String krxKosdaqResponse = KrxApiLink.post(KrxConst.KRX_COMPANY_BAGIC_INFO_URL, krxKosdaqCompanyInfoList);
		JsonObject kosdaqJsonObject = (JsonObject) JsonParser.parseString(krxKosdaqResponse);
		JsonArray kosdaqJsonArray = kosdaqJsonObject.get("OutBlock_1").getAsJsonArray();
		
		CompanyBagicInfoVO kosdaqVO = null;
		Iterator<JsonElement> kosdaqJsonElementArray =  kosdaqJsonArray.iterator();
		List<CompanyBagicInfoVO> kosdaqList = new ArrayList<CompanyBagicInfoVO>();
		while(kosdaqJsonElementArray.hasNext()) {
			kosdaqVO = new CompanyBagicInfoVO();
			JsonElement item = kosdaqJsonElementArray.next();
			kosdaqVO.setIsuCd(item.getAsJsonObject().get("ISU_CD").getAsString());
			kosdaqVO.setIsuSrtCd(item.getAsJsonObject().get("ISU_SRT_CD").getAsString());
			kosdaqVO.setIsuNm(item.getAsJsonObject().get("ISU_NM").getAsString());
			kosdaqVO.setIsuAbbrv(item.getAsJsonObject().get("ISU_ABBRV").getAsString());
			kosdaqVO.setIsuEngNm(item.getAsJsonObject().get("ISU_ENG_NM").getAsString());
			kosdaqVO.setListDd(item.getAsJsonObject().get("LIST_DD").getAsString());
			kosdaqVO.setMktTpNm(item.getAsJsonObject().get("MKT_TP_NM").getAsString());
			kosdaqVO.setSecugrpNm(item.getAsJsonObject().get("SECUGRP_NM").getAsString());
			kosdaqVO.setSectTpNm(item.getAsJsonObject().get("SECT_TP_NM").getAsString());
			kosdaqVO.setKindStkcertTpNm(item.getAsJsonObject().get("KIND_STKCERT_TP_NM").getAsString());
			kosdaqVO.setParval(item.getAsJsonObject().get("PARVAL").getAsString());
			kosdaqVO.setListShrs(item.getAsJsonObject().get("LIST_SHRS").getAsString());
			kosdaqList.add(kosdaqVO);
		}
		
		List<CompanyBagicInfoVO> krxCompayBagicInfoList = new ArrayList<CompanyBagicInfoVO>();
		krxCompayBagicInfoList.addAll(kospiList);
		krxCompayBagicInfoList.addAll(kosdaqList);
		
		List<CompanyBagicInfoVO> insertCompanyBagicInfoList = new ArrayList<CompanyBagicInfoVO>();
		for( CompanyBagicInfoVO krcCompay : krxCompayBagicInfoList ) {
			int duplication_count = 0;
			for( CompanyBagicInfoVO selectCompany : selectCompanyBagicInfoList ) {
				if(krcCompay.getIsuCd().equals(selectCompany.getIsuCd())){
					duplication_count++;
				}
			}
			if(duplication_count == 0) {
				krcCompay.setUseYn("Y");
				insertCompanyBagicInfoList.add(krcCompay);
			}
		}
		
		List<CompanyBagicInfoVO> deleteCompanyBagicInfoList = new ArrayList<CompanyBagicInfoVO>();
		for( CompanyBagicInfoVO selectCompany : selectCompanyBagicInfoList ) {
			int duplication_count = 0;
			for( CompanyBagicInfoVO krcCompay : krxCompayBagicInfoList ) {
				if(selectCompany.getIsuCd() != null && selectCompany.getIsuCd().equals(krcCompay.getIsuCd())){
					duplication_count++;
				}
			}
			if(duplication_count ==0) {
				selectCompany.setUseYn("N");
				deleteCompanyBagicInfoList.add(selectCompany);
			}
		}
		
		for(int i = 0 ; i < insertCompanyBagicInfoList.size() ; i++) {
			for(int j = i+1 ; j < insertCompanyBagicInfoList.size() ; j++) {
				
				if(insertCompanyBagicInfoList.get(i).getIsuCd().equals(insertCompanyBagicInfoList.get(j).getIsuCd())) {
					logger.info(insertCompanyBagicInfoList.get(i).getIsuCd()+" "+i+"//"+insertCompanyBagicInfoList.get(j).getIsuCd()+ " "+j);
					logger.info(insertCompanyBagicInfoList.get(i).getIsuSrtCd()+"//"+insertCompanyBagicInfoList.get(j).getIsuSrtCd());
					logger.info(insertCompanyBagicInfoList.get(i).getIsuNm()+"//"+insertCompanyBagicInfoList.get(j).getIsuNm());
				}
			}
		}
		if(insertCompanyBagicInfoList != null && insertCompanyBagicInfoList.size() > 0)
		companyInfoDao.insertCompanyBagicInfoList(insertCompanyBagicInfoList);
		if(deleteCompanyBagicInfoList != null && deleteCompanyBagicInfoList.size() > 0)
		companyInfoDao.updateUseYnCompanyBagicInfoList(deleteCompanyBagicInfoList);
		
	}
}
