package com.stock.core;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock.constants.KoreaInvestmentConst;
import com.stock.constants.KrxConst;
import com.stock.mvc.service.koreanInvestment.Hoga20CollectionService;
import com.stock.mvc.service.koreanInvestment.KoreanInvestmentService;
import com.stock.mvc.service.koreanInvestment.RankCollectionService;
import com.stock.mvc.service.krx.CompanyInfoService;
import com.stock.utils.Aes;
import com.stock.utils.CommonUtil;
import com.stock.utils.KrxApiLink;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class StockInitialize implements InitializingBean {

    private Logger logger= Logger.getLogger(StockInitialize.class.getName());
    
    @Autowired
    private CompanyInfoService companyInfoService;
    
    @Autowired
    private Hoga20CollectionService hoga20CollectionService;
    
    @Autowired
    private KoreanInvestmentService koreanInvestmentService;
    
    @Autowired
    private RankCollectionService rankCollectionService;
   
    @Value("${korea-investment.url}")
   	private String koreaInvestmentBagicUrl;
    
    @Value("${korea-investment.app-key}")
    private String koreaInvestmentAppKey; 

    @Value("${korea-investment.secret-key}")
    private String koreaInvestmentSecretKey;
    
    @Value("${krx.url}")
    private String krxBagicUrl;
    
    @Override
    public void afterPropertiesSet() throws Exception {
    	
    	KrxConst.KRX_BAGIC_URL = krxBagicUrl;
    	companyInfoService.mergeCompanyBagicInfo();
    	hoga20CollectionService.hoga20TableInit();
    	
    	
    	KoreaInvestmentConst.BAGIC_URL = koreaInvestmentBagicUrl;
    	KoreaInvestmentConst.APP_KEY = Aes.aesCBCDedoce(koreaInvestmentAppKey);
    	KoreaInvestmentConst.SECRET_KEY = Aes.aesCBCDedoce(koreaInvestmentSecretKey);
    	koreanInvestmentService.koreaInvestmentBearerTokenInit();
    	koreanInvestmentService.koreaInvestmentIsTraideDayYn();
    	
    	rankCollectionService.rankCollection();
    	
    	
    }
    
    
    

}
